// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.io.InputStream;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.security.ProtectionDomain;
import java.util.Vector;
import java.util.HashMap;

public class Loader extends ClassLoader
{
    private HashMap<String, ClassLoader> notDefinedHere;
    private Vector<String> notDefinedPackages;
    private ClassPool source;
    private Translator translator;
    private ProtectionDomain domain;
    public boolean doDelegation;
    
    public Loader() {
        this((ClassPool)null);
    }
    
    public Loader(final ClassPool classPool) {
        this.doDelegation = true;
        this.init(classPool);
    }
    
    public Loader(final ClassLoader parent, final ClassPool classPool) {
        super(parent);
        this.doDelegation = true;
        this.init(classPool);
    }
    
    private void init(final ClassPool source) {
        this.notDefinedHere = new HashMap<String, ClassLoader>();
        this.notDefinedPackages = new Vector<String>();
        this.source = source;
        this.translator = null;
        this.domain = null;
        this.delegateLoadingOf("dev.imanity.antixray.libs.javassist.Loader");
    }
    
    public void delegateLoadingOf(final String s) {
        if (s.endsWith(".")) {
            this.notDefinedPackages.addElement(s);
        }
        else {
            this.notDefinedHere.put(s, this);
        }
    }
    
    public void setDomain(final ProtectionDomain domain) {
        this.domain = domain;
    }
    
    public void setClassPool(final ClassPool source) {
        this.source = source;
    }
    
    public void addTranslator(final ClassPool source, final Translator translator) {
        this.source = source;
        (this.translator = translator).start(source);
    }
    
    public static void main(final String[] array) {
        new Loader().run(array);
    }
    
    public void run(final String[] original) {
        if (original.length >= 1) {
            this.run(original[0], Arrays.copyOfRange(original, 1, original.length));
        }
    }
    
    public void run(final String name, final String[] array) {
        final Class<?> loadClass = this.loadClass(name);
        try {
            loadClass.getDeclaredMethod("main", String[].class).invoke(null, array);
        }
        catch (final InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }
    
    @Override
    protected Class<?> loadClass(String intern, final boolean b) {
        intern = intern.intern();
        synchronized (intern) {
            Class<?> c = this.findLoadedClass(intern);
            if (c == null) {
                c = this.loadClassByDelegation(intern);
            }
            if (c == null) {
                c = this.findClass(intern);
            }
            if (c == null) {
                c = this.delegateToParent(intern);
            }
            if (b) {
                this.resolveClass(c);
            }
            return c;
        }
    }
    
    @Override
    protected Class<?> findClass(final String name) {
        byte[] array = null;
        try {
            Label_0101: {
                if (this.source != null) {
                    if (this.translator != null) {
                        this.translator.onLoad(this.source, name);
                    }
                    try {
                        array = this.source.get(name).toBytecode();
                        break Label_0101;
                    }
                    catch (final NotFoundException ex) {
                        return null;
                    }
                }
                final InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + name.replace('.', '/') + ".class");
                if (resourceAsStream == null) {
                    return null;
                }
                array = ClassPoolTail.readStream(resourceAsStream);
            }
        }
        catch (final Exception ex2) {
            throw new ClassNotFoundException("caught an exception while obtaining a class file for " + name, ex2);
        }
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex != -1) {
            final String substring = name.substring(0, lastIndex);
            if (this.isDefinedPackage(substring)) {
                try {
                    this.definePackage(substring, null, null, null, null, null, null, null);
                }
                catch (final IllegalArgumentException ex3) {}
            }
        }
        if (this.domain == null) {
            return this.defineClass(name, array, 0, array.length);
        }
        return this.defineClass(name, array, 0, array.length, this.domain);
    }
    
    private boolean isDefinedPackage(final String s) {
        if (ClassFile.MAJOR_VERSION >= 53) {
            return this.getDefinedPackage(s) == null;
        }
        return this.getPackage(s) == null;
    }
    
    protected Class<?> loadClassByDelegation(final String s) {
        Class<?> delegateToParent = null;
        if (this.doDelegation && (s.startsWith("java.") || s.startsWith("javax.") || s.startsWith("jdk.internal.") || s.startsWith("sun.") || s.startsWith("com.sun.") || s.startsWith("org.w3c.") || s.startsWith("org.xml.") || this.notDelegated(s))) {
            delegateToParent = this.delegateToParent(s);
        }
        return delegateToParent;
    }
    
    private boolean notDelegated(final String key) {
        if (this.notDefinedHere.containsKey(key)) {
            return true;
        }
        final Iterator<String> iterator = this.notDefinedPackages.iterator();
        while (iterator.hasNext()) {
            if (key.startsWith(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    protected Class<?> delegateToParent(final String s) {
        final ClassLoader parent = this.getParent();
        if (parent != null) {
            return parent.loadClass(s);
        }
        return this.findSystemClass(s);
    }
    
    public static class Simple extends ClassLoader
    {
        public Simple() {
        }
        
        public Simple(final ClassLoader parent) {
            super(parent);
        }
        
        public Class<?> invokeDefineClass(final CtClass ctClass) {
            final byte[] bytecode = ctClass.toBytecode();
            return this.defineClass(ctClass.getName(), bytecode, 0, bytecode.length);
        }
    }
}
