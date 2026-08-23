// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util;

import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.MethodEntryEvent;
import java.util.List;
import java.util.HashMap;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;
import java.util.Iterator;
import java.io.IOException;
import com.sun.jdi.Bootstrap;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.ReferenceType;
import java.util.Map;
import com.sun.jdi.request.MethodEntryRequest;
import com.sun.jdi.VirtualMachine;

public class HotSwapper
{
    private VirtualMachine jvm;
    private MethodEntryRequest request;
    private Map<ReferenceType, byte[]> newClassFiles;
    private Trigger trigger;
    private static final String HOST_NAME = "localhost";
    private static final String TRIGGER_NAME;
    
    public HotSwapper(final int i) {
        this(Integer.toString(i));
    }
    
    public HotSwapper(final String value) {
        this.jvm = null;
        this.request = null;
        this.newClassFiles = null;
        this.trigger = new Trigger();
        final AttachingConnector attachingConnector = (AttachingConnector)this.findConnector("com.sun.jdi.SocketAttach");
        final Map<String, Connector.Argument> defaultArguments = attachingConnector.defaultArguments();
        defaultArguments.get("hostname").setValue("localhost");
        defaultArguments.get("port").setValue(value);
        this.jvm = attachingConnector.attach(defaultArguments);
        this.request = methodEntryRequests(this.jvm.eventRequestManager(), HotSwapper.TRIGGER_NAME);
    }
    
    private Connector findConnector(final String s) {
        for (final Connector connector : Bootstrap.virtualMachineManager().allConnectors()) {
            if (connector.name().equals(s)) {
                return connector;
            }
        }
        throw new IOException("Not found: " + s);
    }
    
    private static MethodEntryRequest methodEntryRequests(final EventRequestManager eventRequestManager, final String s) {
        final MethodEntryRequest methodEntryRequest = eventRequestManager.createMethodEntryRequest();
        methodEntryRequest.addClassFilter(s);
        methodEntryRequest.setSuspendPolicy(1);
        return methodEntryRequest;
    }
    
    private void deleteEventRequest(final EventRequestManager eventRequestManager, final MethodEntryRequest methodEntryRequest) {
        eventRequestManager.deleteEventRequest(methodEntryRequest);
    }
    
    public void reload(final String s, final byte[] array) {
        final ReferenceType refType = this.toRefType(s);
        final HashMap hashMap = new HashMap();
        hashMap.put(refType, array);
        this.reload2(hashMap, s);
    }
    
    public void reload(final Map<String, byte[]> map) {
        final HashMap hashMap = new HashMap();
        String str = null;
        for (final Map.Entry entry : map.entrySet()) {
            str = (String)entry.getKey();
            hashMap.put(this.toRefType(str), entry.getValue());
        }
        if (str != null) {
            this.reload2(hashMap, str + " etc.");
        }
    }
    
    private ReferenceType toRefType(final String str) {
        final List<ReferenceType> classesByName = this.jvm.classesByName(str);
        if (classesByName == null || classesByName.isEmpty()) {
            throw new RuntimeException("no such class: " + str);
        }
        return (ReferenceType)classesByName.get(0);
    }
    
    private void reload2(final Map<ReferenceType, byte[]> newClassFiles, final String str) {
        synchronized (this.trigger) {
            this.startDaemon();
            this.newClassFiles = newClassFiles;
            this.request.enable();
            this.trigger.doSwap();
            this.request.disable();
            if (this.newClassFiles != null) {
                this.newClassFiles = null;
                throw new RuntimeException("failed to reload: " + str);
            }
        }
    }
    
    private void startDaemon() {
        new Thread() {
            private void errorMsg(final Throwable t) {
                System.err.print("Exception in thread \"HotSwap\" ");
                t.printStackTrace(System.err);
            }
            
            @Override
            public void run() {
                EventSet waitEvent = null;
                try {
                    waitEvent = HotSwapper.this.waitEvent();
                    final EventIterator eventIterator = waitEvent.eventIterator();
                    while (eventIterator.hasNext()) {
                        if (eventIterator.nextEvent() instanceof MethodEntryEvent) {
                            HotSwapper.this.hotswap();
                            break;
                        }
                    }
                }
                catch (final Throwable t) {
                    this.errorMsg(t);
                }
                try {
                    if (waitEvent != null) {
                        waitEvent.resume();
                    }
                }
                catch (final Throwable t2) {
                    this.errorMsg(t2);
                }
            }
        }.start();
    }
    
    EventSet waitEvent() {
        return this.jvm.eventQueue().remove();
    }
    
    void hotswap() {
        this.jvm.redefineClasses(this.newClassFiles);
        this.newClassFiles = null;
    }
    
    static {
        TRIGGER_NAME = Trigger.class.getName();
    }
}
