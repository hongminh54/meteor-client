// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools.rmi;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.Translator;
import java.util.Vector;
import java.util.Hashtable;
import dev.imanity.antixray.libs.javassist.ClassPool;
import java.util.List;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.tools.web.Webserver;

public class AppletServer extends Webserver
{
    private StubGenerator stubGen;
    private Map<String, ExportedObject> exportedNames;
    private List<ExportedObject> exportedObjects;
    private static final byte[] okHeader;
    
    public AppletServer(final String s) {
        this(Integer.parseInt(s));
    }
    
    public AppletServer(final int n) {
        this(ClassPool.getDefault(), new StubGenerator(), n);
    }
    
    public AppletServer(final int n, final ClassPool classPool) {
        this(new ClassPool(classPool), new StubGenerator(), n);
    }
    
    private AppletServer(final ClassPool classPool, final StubGenerator stubGen, final int n) {
        super(n);
        this.exportedNames = new Hashtable<String, ExportedObject>();
        this.exportedObjects = new Vector<ExportedObject>();
        this.addTranslator(classPool, this.stubGen = stubGen);
    }
    
    @Override
    public void run() {
        super.run();
    }
    
    public synchronized int exportObject(final String s, final Object object) {
        final Class<?> class1 = object.getClass();
        final ExportedObject exportedObject = new ExportedObject();
        exportedObject.object = object;
        exportedObject.methods = class1.getMethods();
        this.exportedObjects.add(exportedObject);
        exportedObject.identifier = this.exportedObjects.size() - 1;
        if (s != null) {
            this.exportedNames.put(s, exportedObject);
        }
        try {
            this.stubGen.makeProxyClass(class1);
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        return exportedObject.identifier;
    }
    
    @Override
    public void doReply(final InputStream inputStream, final OutputStream outputStream, final String s) {
        if (s.startsWith("POST /rmi ")) {
            this.processRMI(inputStream, outputStream);
        }
        else if (s.startsWith("POST /lookup ")) {
            this.lookupName(s, inputStream, outputStream);
        }
        else {
            super.doReply(inputStream, outputStream, s);
        }
    }
    
    private void processRMI(final InputStream in, final OutputStream out) {
        final ObjectInputStream objectInputStream = new ObjectInputStream(in);
        final int int1 = objectInputStream.readInt();
        final int int2 = objectInputStream.readInt();
        Throwable t = null;
        Object convertRvalue = null;
        try {
            final ExportedObject exportedObject = this.exportedObjects.get(int1);
            convertRvalue = this.convertRvalue(exportedObject.methods[int2].invoke(exportedObject.object, this.readParameters(objectInputStream)));
        }
        catch (final Exception ex) {
            t = ex;
            this.logging2(ex.toString());
        }
        out.write(AppletServer.okHeader);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        if (t != null) {
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeUTF(t.toString());
        }
        else {
            try {
                objectOutputStream.writeBoolean(true);
                objectOutputStream.writeObject(convertRvalue);
            }
            catch (final NotSerializableException ex2) {
                this.logging2(ex2.toString());
            }
            catch (final InvalidClassException ex3) {
                this.logging2(ex3.toString());
            }
        }
        objectOutputStream.flush();
        objectOutputStream.close();
        objectInputStream.close();
    }
    
    private Object[] readParameters(final ObjectInputStream objectInputStream) {
        final int int1 = objectInputStream.readInt();
        final Object[] array = new Object[int1];
        for (int i = 0; i < int1; ++i) {
            Object o = objectInputStream.readObject();
            if (o instanceof RemoteRef) {
                o = this.exportedObjects.get(((RemoteRef)o).oid).object;
            }
            array[i] = o;
        }
        return array;
    }
    
    private Object convertRvalue(final Object o) {
        if (o == null) {
            return null;
        }
        final String name = o.getClass().getName();
        if (this.stubGen.isProxyClass(name)) {
            return new RemoteRef(this.exportObject(null, o), name);
        }
        return o;
    }
    
    private void lookupName(final String s, final InputStream in, final OutputStream out) {
        final ObjectInputStream in2 = new ObjectInputStream(in);
        final String utf = DataInputStream.readUTF(in2);
        final ExportedObject exportedObject = this.exportedNames.get(utf);
        out.write(AppletServer.okHeader);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        if (exportedObject == null) {
            this.logging2(utf + "not found.");
            objectOutputStream.writeInt(-1);
            objectOutputStream.writeUTF("error");
        }
        else {
            this.logging2(utf);
            objectOutputStream.writeInt(exportedObject.identifier);
            objectOutputStream.writeUTF(exportedObject.object.getClass().getName());
        }
        objectOutputStream.flush();
        objectOutputStream.close();
        in2.close();
    }
    
    static {
        okHeader = "HTTP/1.0 200 OK\r\n\r\n".getBytes();
    }
}
