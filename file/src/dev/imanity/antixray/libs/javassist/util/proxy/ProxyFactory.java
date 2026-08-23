// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.lang.ref.WeakReference;
import java.lang.ref.Reference;
import java.util.WeakHashMap;
import dev.imanity.antixray.libs.javassist.bytecode.ExceptionsAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.StackMapTable;
import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.Collection;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.DuplicateMemberException;
import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.bytecode.AttributeInfo;
import dev.imanity.antixray.libs.javassist.bytecode.SignatureAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.FieldInfo;
import java.security.ProtectionDomain;
import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.util.HashMap;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.List;

public class ProxyFactory
{
    private Class<?> superClass;
    private Class<?>[] interfaces;
    private MethodFilter methodFilter;
    private MethodHandler handler;
    private List<Map.Entry<String, Method>> signatureMethods;
    private boolean hasGetHandler;
    private byte[] signature;
    private String classname;
    private String basename;
    private String superName;
    private Class<?> thisClass;
    private String genericSignature;
    private boolean factoryUseCache;
    private boolean factoryWriteReplace;
    public static boolean onlyPublicMethods;
    public String writeDirectory;
    private static final Class<?> OBJECT_TYPE;
    private static final String HOLDER = "_methods_";
    private static final String HOLDER_TYPE = "[Ljava/lang/reflect/Method;";
    private static final String FILTER_SIGNATURE_FIELD = "_filter_signature";
    private static final String FILTER_SIGNATURE_TYPE = "[B";
    private static final String HANDLER = "handler";
    private static final String NULL_INTERCEPTOR_HOLDER = "dev.imanity.antixray.libs.javassist.util.proxy.RuntimeSupport";
    private static final String DEFAULT_INTERCEPTOR = "default_interceptor";
    private static final String HANDLER_TYPE;
    private static final String HANDLER_SETTER = "setHandler";
    private static final String HANDLER_SETTER_TYPE;
    private static final String HANDLER_GETTER = "getHandler";
    private static final String HANDLER_GETTER_TYPE;
    private static final String SERIAL_VERSION_UID_FIELD = "serialVersionUID";
    private static final String SERIAL_VERSION_UID_TYPE = "J";
    private static final long SERIAL_VERSION_UID_VALUE = -1L;
    public static volatile boolean useCache;
    public static volatile boolean useWriteReplace;
    private static Map<ClassLoader, Map<String, ProxyDetails>> proxyCache;
    private static char[] hexDigits;
    public static ClassLoaderProvider classLoaderProvider;
    public static UniqueName nameGenerator;
    private static final String packageForJavaBase = "dev.imanity.antixray.libs.javassist.util.proxy.";
    private static Comparator<Map.Entry<String, Method>> sorter;
    private static final String HANDLER_GETTER_KEY = "getHandler:()";
    
    public boolean isUseCache() {
        return this.factoryUseCache;
    }
    
    public void setUseCache(final boolean factoryUseCache) {
        if (this.handler != null && factoryUseCache) {
            throw new RuntimeException("caching cannot be enabled if the factory default interceptor has been set");
        }
        this.factoryUseCache = factoryUseCache;
    }
    
    public boolean isUseWriteReplace() {
        return this.factoryWriteReplace;
    }
    
    public void setUseWriteReplace(final boolean factoryWriteReplace) {
        this.factoryWriteReplace = factoryWriteReplace;
    }
    
    public static boolean isProxyClass(final Class<?> clazz) {
        return Proxy.class.isAssignableFrom(clazz);
    }
    
    public ProxyFactory() {
        this.superClass = null;
        this.interfaces = null;
        this.methodFilter = null;
        this.handler = null;
        this.signature = null;
        this.signatureMethods = null;
        this.hasGetHandler = false;
        this.thisClass = null;
        this.genericSignature = null;
        this.writeDirectory = null;
        this.factoryUseCache = ProxyFactory.useCache;
        this.factoryWriteReplace = ProxyFactory.useWriteReplace;
    }
    
    public void setSuperclass(final Class<?> superClass) {
        this.superClass = superClass;
        this.signature = null;
    }
    
    public Class<?> getSuperclass() {
        return this.superClass;
    }
    
    public void setInterfaces(final Class<?>[] interfaces) {
        this.interfaces = interfaces;
        this.signature = null;
    }
    
    public Class<?>[] getInterfaces() {
        return this.interfaces;
    }
    
    public void setFilter(final MethodFilter methodFilter) {
        this.methodFilter = methodFilter;
        this.signature = null;
    }
    
    public void setGenericSignature(final String genericSignature) {
        this.genericSignature = genericSignature;
    }
    
    public Class<?> createClass() {
        if (this.signature == null) {
            this.computeSignature(this.methodFilter);
        }
        return this.createClass1(null);
    }
    
    public Class<?> createClass(final MethodFilter methodFilter) {
        this.computeSignature(methodFilter);
        return this.createClass1(null);
    }
    
    Class<?> createClass(final byte[] array) {
        this.installSignature(array);
        return this.createClass1(null);
    }
    
    public Class<?> createClass(final MethodHandles.Lookup lookup) {
        if (this.signature == null) {
            this.computeSignature(this.methodFilter);
        }
        return this.createClass1(lookup);
    }
    
    public Class<?> createClass(final MethodHandles.Lookup lookup, final MethodFilter methodFilter) {
        this.computeSignature(methodFilter);
        return this.createClass1(lookup);
    }
    
    Class<?> createClass(final MethodHandles.Lookup lookup, final byte[] array) {
        this.installSignature(array);
        return this.createClass1(lookup);
    }
    
    private Class<?> createClass1(final MethodHandles.Lookup lookup) {
        Class<?> clazz = this.thisClass;
        if (clazz == null) {
            final ClassLoader classLoader = this.getClassLoader();
            synchronized (ProxyFactory.proxyCache) {
                if (this.factoryUseCache) {
                    this.createClass2(classLoader, lookup);
                }
                else {
                    this.createClass3(classLoader, lookup);
                }
                clazz = this.thisClass;
                this.thisClass = null;
            }
        }
        return clazz;
    }
    
    public String getKey(final Class<?> clazz, final Class<?>[] array, final byte[] array2, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        if (clazz != null) {
            sb.append(clazz.getName());
        }
        sb.append(':');
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i].getName());
            sb.append(':');
        }
        for (int j = 0; j < array2.length; ++j) {
            final byte b2 = array2[j];
            final int n = b2 & 0xF;
            final int n2 = b2 >> 4 & 0xF;
            sb.append(ProxyFactory.hexDigits[n]);
            sb.append(ProxyFactory.hexDigits[n2]);
        }
        if (b) {
            sb.append(":w");
        }
        return sb.toString();
    }
    
    private void createClass2(final ClassLoader classLoader, final MethodHandles.Lookup lookup) {
        final String key = this.getKey(this.superClass, this.interfaces, this.signature, this.factoryWriteReplace);
        Map map = ProxyFactory.proxyCache.get(classLoader);
        if (map == null) {
            map = new HashMap();
            ProxyFactory.proxyCache.put(classLoader, map);
        }
        final ProxyDetails proxyDetails = (ProxyDetails)map.get(key);
        if (proxyDetails != null) {
            this.thisClass = proxyDetails.proxyClass.get();
            if (this.thisClass != null) {
                return;
            }
        }
        this.createClass3(classLoader, lookup);
        map.put(key, new ProxyDetails(this.signature, this.thisClass, this.factoryWriteReplace));
    }
    
    private void createClass3(final ClassLoader classLoader, final MethodHandles.Lookup lookup) {
        this.allocateClassName();
        try {
            final ClassFile make = this.make();
            if (this.writeDirectory != null) {
                FactoryHelper.writeFile(make, this.writeDirectory);
            }
            if (lookup == null) {
                this.thisClass = FactoryHelper.toClass(make, this.getClassInTheSamePackage(), classLoader, this.getDomain());
            }
            else {
                this.thisClass = FactoryHelper.toClass(make, lookup);
            }
            this.setField("_filter_signature", this.signature);
            if (!this.factoryUseCache) {
                this.setField("default_interceptor", this.handler);
            }
        }
        catch (final CannotCompileException cause) {
            throw new RuntimeException(cause.getMessage(), cause);
        }
    }
    
    private Class<?> getClassInTheSamePackage() {
        if (this.basename.startsWith("dev.imanity.antixray.libs.javassist.util.proxy.")) {
            return this.getClass();
        }
        if (this.superClass != null && this.superClass != ProxyFactory.OBJECT_TYPE) {
            return this.superClass;
        }
        if (this.interfaces != null && this.interfaces.length > 0) {
            return this.interfaces[0];
        }
        return this.getClass();
    }
    
    private void setField(final String name, final Object value) {
        if (this.thisClass != null && value != null) {
            try {
                final Field field = this.thisClass.getField(name);
                SecurityActions.setAccessible(field, true);
                field.set(null, value);
                SecurityActions.setAccessible(field, false);
            }
            catch (final Exception cause) {
                throw new RuntimeException(cause);
            }
        }
    }
    
    static byte[] getFilterSignature(final Class<?> clazz) {
        return (byte[])getField(clazz, "_filter_signature");
    }
    
    private static Object getField(final Class<?> clazz, final String name) {
        try {
            final Field field = clazz.getField(name);
            field.setAccessible(true);
            final Object value = field.get(null);
            field.setAccessible(false);
            return value;
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public static MethodHandler getHandler(final Proxy obj) {
        try {
            final Field declaredField = obj.getClass().getDeclaredField("handler");
            declaredField.setAccessible(true);
            final Object value = declaredField.get(obj);
            declaredField.setAccessible(false);
            return (MethodHandler)value;
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    protected ClassLoader getClassLoader() {
        return ProxyFactory.classLoaderProvider.get(this);
    }
    
    protected ClassLoader getClassLoader0() {
        ClassLoader classLoader = null;
        if (this.superClass != null && !this.superClass.getName().equals("java.lang.Object")) {
            classLoader = this.superClass.getClassLoader();
        }
        else if (this.interfaces != null && this.interfaces.length > 0) {
            classLoader = this.interfaces[0].getClassLoader();
        }
        if (classLoader == null) {
            classLoader = this.getClass().getClassLoader();
            if (classLoader == null) {
                classLoader = Thread.currentThread().getContextClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
            }
        }
        return classLoader;
    }
    
    protected ProtectionDomain getDomain() {
        Class<?> clazz;
        if (this.superClass != null && !this.superClass.getName().equals("java.lang.Object")) {
            clazz = this.superClass;
        }
        else if (this.interfaces != null && this.interfaces.length > 0) {
            clazz = this.interfaces[0];
        }
        else {
            clazz = this.getClass();
        }
        return clazz.getProtectionDomain();
    }
    
    public Object create(final Class<?>[] array, final Object[] array2, final MethodHandler handler) {
        final Object create = this.create(array, array2);
        ((Proxy)create).setHandler(handler);
        return create;
    }
    
    public Object create(final Class<?>[] parameterTypes, final Object[] initargs) {
        return this.createClass().getConstructor(parameterTypes).newInstance(initargs);
    }
    
    @Deprecated
    public void setHandler(final MethodHandler handler) {
        if (this.factoryUseCache && handler != null) {
            this.factoryUseCache = false;
            this.thisClass = null;
        }
        this.setField("default_interceptor", this.handler = handler);
    }
    
    private static String makeProxyName(final String s) {
        synchronized (ProxyFactory.nameGenerator) {
            return ProxyFactory.nameGenerator.get(s);
        }
    }
    
    private ClassFile make() {
        final ClassFile classFile = new ClassFile(false, this.classname, this.superName);
        classFile.setAccessFlags(1);
        setInterfaces(classFile, this.interfaces, (Class<?>)(this.hasGetHandler ? Proxy.class : ProxyObject.class));
        final ConstPool constPool = classFile.getConstPool();
        if (!this.factoryUseCache) {
            final FieldInfo fieldInfo = new FieldInfo(constPool, "default_interceptor", ProxyFactory.HANDLER_TYPE);
            fieldInfo.setAccessFlags(9);
            classFile.addField(fieldInfo);
        }
        final FieldInfo fieldInfo2 = new FieldInfo(constPool, "handler", ProxyFactory.HANDLER_TYPE);
        fieldInfo2.setAccessFlags(2);
        classFile.addField(fieldInfo2);
        final FieldInfo fieldInfo3 = new FieldInfo(constPool, "_filter_signature", "[B");
        fieldInfo3.setAccessFlags(9);
        classFile.addField(fieldInfo3);
        final FieldInfo fieldInfo4 = new FieldInfo(constPool, "serialVersionUID", "J");
        fieldInfo4.setAccessFlags(25);
        classFile.addField(fieldInfo4);
        if (this.genericSignature != null) {
            classFile.addAttribute(new SignatureAttribute(constPool, this.genericSignature));
        }
        this.makeConstructors(this.classname, classFile, constPool, this.classname);
        final ArrayList<Find2MethodsArgs> list = new ArrayList<Find2MethodsArgs>();
        addClassInitializer(classFile, constPool, this.classname, this.overrideMethods(classFile, constPool, this.classname, list), list);
        addSetter(this.classname, classFile, constPool);
        if (!this.hasGetHandler) {
            addGetter(this.classname, classFile, constPool);
        }
        if (this.factoryWriteReplace) {
            try {
                classFile.addMethod(makeWriteReplace(constPool));
            }
            catch (final DuplicateMemberException ex) {}
        }
        this.thisClass = null;
        return classFile;
    }
    
    private void checkClassAndSuperName() {
        if (this.interfaces == null) {
            this.interfaces = new Class[0];
        }
        if (this.superClass == null) {
            this.superClass = ProxyFactory.OBJECT_TYPE;
            this.superName = this.superClass.getName();
            this.basename = ((this.interfaces.length == 0) ? this.superName : this.interfaces[0].getName());
        }
        else {
            this.superName = this.superClass.getName();
            this.basename = this.superName;
        }
        if (Modifier.isFinal(this.superClass.getModifiers())) {
            throw new RuntimeException(this.superName + " is final");
        }
        if (this.basename.startsWith("java.") || this.basename.startsWith("jdk.") || ProxyFactory.onlyPublicMethods) {
            this.basename = "dev.imanity.antixray.libs.javassist.util.proxy." + this.basename.replace('.', '_');
        }
    }
    
    private void allocateClassName() {
        this.classname = makeProxyName(this.basename);
    }
    
    private void makeSortedMethodList() {
        this.checkClassAndSuperName();
        this.hasGetHandler = false;
        Collections.sort(this.signatureMethods = new ArrayList<Map.Entry<String, Method>>(this.getMethods(this.superClass, this.interfaces).entrySet()), ProxyFactory.sorter);
    }
    
    private void computeSignature(final MethodFilter methodFilter) {
        this.makeSortedMethodList();
        final int size = this.signatureMethods.size();
        this.signature = new byte[size + 7 >> 3];
        for (int i = 0; i < size; ++i) {
            final Method method = (Method)this.signatureMethods.get(i).getValue();
            final int modifiers = method.getModifiers();
            if (!Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers) && isVisible(modifiers, this.basename, method) && (methodFilter == null || methodFilter.isHandled(method))) {
                this.setBit(this.signature, i);
            }
        }
    }
    
    private void installSignature(final byte[] signature) {
        this.makeSortedMethodList();
        if (signature.length != this.signatureMethods.size() + 7 >> 3) {
            throw new RuntimeException("invalid filter signature length for deserialized proxy class");
        }
        this.signature = signature;
    }
    
    private boolean testBit(final byte[] array, final int n) {
        final int n2 = n >> 3;
        return n2 <= array.length && (array[n2] & 1 << (n & 0x7)) != 0x0;
    }
    
    private void setBit(final byte[] array, final int n) {
        final int n2 = n >> 3;
        if (n2 < array.length) {
            array[n2] |= (byte)(1 << (n & 0x7));
        }
    }
    
    private static void setInterfaces(final ClassFile classFile, final Class<?>[] array, final Class<?> clazz) {
        final String name = clazz.getName();
        String[] interfaces;
        if (array == null || array.length == 0) {
            interfaces = new String[] { name };
        }
        else {
            interfaces = new String[array.length + 1];
            for (int i = 0; i < array.length; ++i) {
                interfaces[i] = array[i].getName();
            }
            interfaces[array.length] = name;
        }
        classFile.setInterfaces(interfaces);
    }
    
    private static void addClassInitializer(final ClassFile classFile, final ConstPool constPool, final String s, final int n, final List<Find2MethodsArgs> list) {
        final FieldInfo fieldInfo = new FieldInfo(constPool, "_methods_", "[Ljava/lang/reflect/Method;");
        fieldInfo.setAccessFlags(10);
        classFile.addField(fieldInfo);
        final MethodInfo methodInfo = new MethodInfo(constPool, "<clinit>", "()V");
        methodInfo.setAccessFlags(8);
        setThrows(methodInfo, constPool, new Class[] { ClassNotFoundException.class });
        final Bytecode bytecode = new Bytecode(constPool, 0, 2);
        bytecode.addIconst(n * 2);
        bytecode.addAnewarray("java.lang.reflect.Method");
        bytecode.addAstore(0);
        bytecode.addLdc(s);
        bytecode.addInvokestatic("java.lang.Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
        bytecode.addAstore(1);
        for (final Find2MethodsArgs find2MethodsArgs : list) {
            callFind2Methods(bytecode, find2MethodsArgs.methodName, find2MethodsArgs.delegatorName, find2MethodsArgs.origIndex, find2MethodsArgs.descriptor, 1, 0);
        }
        bytecode.addAload(0);
        bytecode.addPutstatic(s, "_methods_", "[Ljava/lang/reflect/Method;");
        bytecode.addLconst(-1L);
        bytecode.addPutstatic(s, "serialVersionUID", "J");
        bytecode.addOpcode(177);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        classFile.addMethod(methodInfo);
    }
    
    private static void callFind2Methods(final Bytecode bytecode, final String s, final String s2, final int n, final String s3, final int n2, final int n3) {
        final String name = RuntimeSupport.class.getName();
        final String s4 = "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;[Ljava/lang/reflect/Method;)V";
        bytecode.addAload(n2);
        bytecode.addLdc(s);
        if (s2 == null) {
            bytecode.addOpcode(1);
        }
        else {
            bytecode.addLdc(s2);
        }
        bytecode.addIconst(n);
        bytecode.addLdc(s3);
        bytecode.addAload(n3);
        bytecode.addInvokestatic(name, "find2Methods", s4);
    }
    
    private static void addSetter(final String s, final ClassFile classFile, final ConstPool constPool) {
        final MethodInfo methodInfo = new MethodInfo(constPool, "setHandler", ProxyFactory.HANDLER_SETTER_TYPE);
        methodInfo.setAccessFlags(1);
        final Bytecode bytecode = new Bytecode(constPool, 2, 2);
        bytecode.addAload(0);
        bytecode.addAload(1);
        bytecode.addPutfield(s, "handler", ProxyFactory.HANDLER_TYPE);
        bytecode.addOpcode(177);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        classFile.addMethod(methodInfo);
    }
    
    private static void addGetter(final String s, final ClassFile classFile, final ConstPool constPool) {
        final MethodInfo methodInfo = new MethodInfo(constPool, "getHandler", ProxyFactory.HANDLER_GETTER_TYPE);
        methodInfo.setAccessFlags(1);
        final Bytecode bytecode = new Bytecode(constPool, 1, 1);
        bytecode.addAload(0);
        bytecode.addGetfield(s, "handler", ProxyFactory.HANDLER_TYPE);
        bytecode.addOpcode(176);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        classFile.addMethod(methodInfo);
    }
    
    private int overrideMethods(final ClassFile classFile, final ConstPool constPool, final String s, final List<Find2MethodsArgs> list) {
        final String uniqueName = makeUniqueName("_d", this.signatureMethods);
        final Iterator<Map.Entry<String, Method>> iterator = this.signatureMethods.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Map.Entry entry = iterator.next();
            if ((ClassFile.MAJOR_VERSION < 49 || !isBridge((Method)entry.getValue())) && this.testBit(this.signature, n)) {
                this.override(s, (Method)entry.getValue(), uniqueName, n, keyToDesc((String)entry.getKey(), (Method)entry.getValue()), classFile, constPool, list);
            }
            ++n;
        }
        return n;
    }
    
    private static boolean isBridge(final Method method) {
        return method.isBridge();
    }
    
    private void override(final String s, final Method method, final String str, final int i, final String s2, final ClassFile classFile, final ConstPool constPool, final List<Find2MethodsArgs> list) {
        final Class<?> declaringClass = method.getDeclaringClass();
        String string = str + i + method.getName();
        if (Modifier.isAbstract(method.getModifiers())) {
            string = null;
        }
        else {
            final MethodInfo delegator = this.makeDelegator(method, s2, constPool, declaringClass, string);
            delegator.setAccessFlags(delegator.getAccessFlags() & 0xFFFFFFBF);
            classFile.addMethod(delegator);
        }
        classFile.addMethod(makeForwarder(s, method, s2, constPool, declaringClass, string, i, list));
    }
    
    private void makeConstructors(final String s, final ClassFile classFile, final ConstPool constPool, final String s2) {
        final Constructor<?>[] declaredConstructors = SecurityActions.getDeclaredConstructors(this.superClass);
        final boolean b = !this.factoryUseCache;
        for (int i = 0; i < declaredConstructors.length; ++i) {
            final Constructor<?> constructor = declaredConstructors[i];
            final int modifiers = constructor.getModifiers();
            if (!Modifier.isFinal(modifiers) && !Modifier.isPrivate(modifiers) && isVisible(modifiers, this.basename, constructor)) {
                classFile.addMethod(makeConstructor(s, constructor, constPool, this.superClass, b));
            }
        }
    }
    
    private static String makeUniqueName(final String str, final List<Map.Entry<String, Method>> list) {
        if (makeUniqueName0(str, list.iterator())) {
            return str;
        }
        for (int i = 100; i < 999; ++i) {
            final String string = str + i;
            if (makeUniqueName0(string, list.iterator())) {
                return string;
            }
        }
        throw new RuntimeException("cannot make a unique method name");
    }
    
    private static boolean makeUniqueName0(final String prefix, final Iterator<Map.Entry<String, Method>> iterator) {
        while (iterator.hasNext()) {
            if (iterator.next().getKey().startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isVisible(final int n, final String s, final Member member) {
        if ((n & 0x2) != 0x0) {
            return false;
        }
        if ((n & 0x5) != 0x0) {
            return true;
        }
        final String packageName = getPackageName(s);
        final String packageName2 = getPackageName(member.getDeclaringClass().getName());
        if (packageName == null) {
            return packageName2 == null;
        }
        return packageName.equals(packageName2);
    }
    
    private static String getPackageName(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex < 0) {
            return null;
        }
        return s.substring(0, lastIndex);
    }
    
    private Map<String, Method> getMethods(final Class<?> clazz, final Class<?>[] array) {
        final HashMap hashMap = new HashMap();
        final HashSet set = new HashSet();
        for (int i = 0; i < array.length; ++i) {
            this.getMethods(hashMap, array[i], set);
        }
        this.getMethods(hashMap, clazz, set);
        return hashMap;
    }
    
    private void getMethods(final Map<String, Method> map, final Class<?> clazz, final Set<Class<?>> set) {
        if (!set.add(clazz)) {
            return;
        }
        final Class<?>[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            this.getMethods(map, interfaces[i], set);
        }
        final Class superclass = clazz.getSuperclass();
        if (superclass != null) {
            this.getMethods(map, superclass, set);
        }
        final Method[] declaredMethods = SecurityActions.getDeclaredMethods(clazz);
        for (int j = 0; j < declaredMethods.length; ++j) {
            if (!Modifier.isPrivate(declaredMethods[j].getModifiers())) {
                final Method method = declaredMethods[j];
                final String string = method.getName() + ':' + RuntimeSupport.makeDescriptor(method);
                if (string.startsWith("getHandler:()")) {
                    this.hasGetHandler = true;
                }
                final Method method2 = map.put(string, method);
                if (null != method2 && isBridge(method) && !Modifier.isPublic(method2.getDeclaringClass().getModifiers()) && !Modifier.isAbstract(method2.getModifiers()) && !isDuplicated(j, declaredMethods)) {
                    map.put(string, method2);
                }
                if (null != method2 && Modifier.isPublic(method2.getModifiers()) && !Modifier.isPublic(method.getModifiers())) {
                    map.put(string, method2);
                }
            }
        }
    }
    
    private static boolean isDuplicated(final int n, final Method[] array) {
        final String name = array[n].getName();
        for (int i = 0; i < array.length; ++i) {
            if (i != n && name.equals(array[i].getName()) && areParametersSame(array[n], array[i])) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean areParametersSame(final Method method, final Method method2) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Class<?>[] parameterTypes2 = method2.getParameterTypes();
        if (parameterTypes.length == parameterTypes2.length) {
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (!parameterTypes[i].getName().equals(parameterTypes2[i].getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static String keyToDesc(final String s, final Method method) {
        return s.substring(s.indexOf(58) + 1);
    }
    
    private static MethodInfo makeConstructor(final String s, final Constructor<?> constructor, final ConstPool constPool, final Class<?> clazz, final boolean b) {
        final String descriptor = RuntimeSupport.makeDescriptor(constructor.getParameterTypes(), Void.TYPE);
        final MethodInfo methodInfo = new MethodInfo(constPool, "<init>", descriptor);
        methodInfo.setAccessFlags(1);
        setThrows(methodInfo, constPool, constructor.getExceptionTypes());
        final Bytecode bytecode = new Bytecode(constPool, 0, 0);
        if (b) {
            bytecode.addAload(0);
            bytecode.addGetstatic(s, "default_interceptor", ProxyFactory.HANDLER_TYPE);
            bytecode.addPutfield(s, "handler", ProxyFactory.HANDLER_TYPE);
            bytecode.addGetstatic(s, "default_interceptor", ProxyFactory.HANDLER_TYPE);
            bytecode.addOpcode(199);
            bytecode.addIndex(10);
        }
        bytecode.addAload(0);
        bytecode.addGetstatic("dev.imanity.antixray.libs.javassist.util.proxy.RuntimeSupport", "default_interceptor", ProxyFactory.HANDLER_TYPE);
        bytecode.addPutfield(s, "handler", ProxyFactory.HANDLER_TYPE);
        final int currentPc = bytecode.currentPc();
        bytecode.addAload(0);
        final int addLoadParameters = addLoadParameters(bytecode, constructor.getParameterTypes(), 1);
        bytecode.addInvokespecial(clazz.getName(), "<init>", descriptor);
        bytecode.addOpcode(177);
        bytecode.setMaxLocals(addLoadParameters + 1);
        final CodeAttribute codeAttribute = bytecode.toCodeAttribute();
        methodInfo.setCodeAttribute(codeAttribute);
        final StackMapTable.Writer writer = new StackMapTable.Writer(32);
        writer.sameFrame(currentPc);
        codeAttribute.setAttribute(writer.toStackMapTable(constPool));
        return methodInfo;
    }
    
    private MethodInfo makeDelegator(final Method method, final String s, final ConstPool constPool, final Class<?> clazz, final String s2) {
        final MethodInfo methodInfo = new MethodInfo(constPool, s2, s);
        methodInfo.setAccessFlags(0x11 | (method.getModifiers() & 0xFFFFFAD9));
        setThrows(methodInfo, constPool, method);
        final Bytecode bytecode = new Bytecode(constPool, 0, 0);
        bytecode.addAload(0);
        int addLoadParameters = addLoadParameters(bytecode, method.getParameterTypes(), 1);
        final Class<?> invokespecialTarget = this.invokespecialTarget(clazz);
        bytecode.addInvokespecial(invokespecialTarget.isInterface(), constPool.addClassInfo(invokespecialTarget.getName()), method.getName(), s);
        addReturn(bytecode, method.getReturnType());
        bytecode.setMaxLocals(++addLoadParameters);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        return methodInfo;
    }
    
    private Class<?> invokespecialTarget(final Class<?> clazz) {
        if (clazz.isInterface()) {
            for (final Class<?> clazz2 : this.interfaces) {
                if (clazz.isAssignableFrom(clazz2)) {
                    return clazz2;
                }
            }
        }
        return this.superClass;
    }
    
    private static MethodInfo makeForwarder(final String s, final Method method, final String s2, final ConstPool constPool, final Class<?> clazz, final String s3, final int n, final List<Find2MethodsArgs> list) {
        final MethodInfo methodInfo = new MethodInfo(constPool, method.getName(), s2);
        methodInfo.setAccessFlags(0x10 | (method.getModifiers() & 0xFFFFFADF));
        setThrows(methodInfo, constPool, method);
        final int paramSize = Descriptor.paramSize(s2);
        final Bytecode bytecode = new Bytecode(constPool, 0, paramSize + 2);
        final int n2 = n * 2;
        final int n3 = n * 2 + 1;
        final int n4 = paramSize + 1;
        bytecode.addGetstatic(s, "_methods_", "[Ljava/lang/reflect/Method;");
        bytecode.addAstore(n4);
        list.add(new Find2MethodsArgs(method.getName(), s3, s2, n2));
        bytecode.addAload(0);
        bytecode.addGetfield(s, "handler", ProxyFactory.HANDLER_TYPE);
        bytecode.addAload(0);
        bytecode.addAload(n4);
        bytecode.addIconst(n2);
        bytecode.addOpcode(50);
        bytecode.addAload(n4);
        bytecode.addIconst(n3);
        bytecode.addOpcode(50);
        makeParameterList(bytecode, method.getParameterTypes());
        bytecode.addInvokeinterface(MethodHandler.class.getName(), "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", 5);
        final Class<?> returnType = method.getReturnType();
        addUnwrapper(bytecode, returnType);
        addReturn(bytecode, returnType);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        return methodInfo;
    }
    
    private static void setThrows(final MethodInfo methodInfo, final ConstPool constPool, final Method method) {
        setThrows(methodInfo, constPool, method.getExceptionTypes());
    }
    
    private static void setThrows(final MethodInfo methodInfo, final ConstPool constPool, final Class<?>[] array) {
        if (array.length == 0) {
            return;
        }
        final String[] exceptions = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            exceptions[i] = array[i].getName();
        }
        final ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute(constPool);
        exceptionsAttribute.setExceptions(exceptions);
        methodInfo.setExceptionsAttribute(exceptionsAttribute);
    }
    
    private static int addLoadParameters(final Bytecode bytecode, final Class<?>[] array, final int n) {
        int n2 = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            n2 += addLoad(bytecode, n2 + n, array[i]);
        }
        return n2;
    }
    
    private static int addLoad(final Bytecode bytecode, final int n, final Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == Long.TYPE) {
                bytecode.addLload(n);
                return 2;
            }
            if (clazz == Float.TYPE) {
                bytecode.addFload(n);
            }
            else {
                if (clazz == Double.TYPE) {
                    bytecode.addDload(n);
                    return 2;
                }
                bytecode.addIload(n);
            }
        }
        else {
            bytecode.addAload(n);
        }
        return 1;
    }
    
    private static int addReturn(final Bytecode bytecode, final Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == Long.TYPE) {
                bytecode.addOpcode(173);
                return 2;
            }
            if (clazz == Float.TYPE) {
                bytecode.addOpcode(174);
            }
            else {
                if (clazz == Double.TYPE) {
                    bytecode.addOpcode(175);
                    return 2;
                }
                if (clazz == Void.TYPE) {
                    bytecode.addOpcode(177);
                    return 0;
                }
                bytecode.addOpcode(172);
            }
        }
        else {
            bytecode.addOpcode(176);
        }
        return 1;
    }
    
    private static void makeParameterList(final Bytecode bytecode, final Class<?>[] array) {
        int wrapper = 1;
        final int length = array.length;
        bytecode.addIconst(length);
        bytecode.addAnewarray("java/lang/Object");
        for (int i = 0; i < length; ++i) {
            bytecode.addOpcode(89);
            bytecode.addIconst(i);
            final Class<?> clazz = array[i];
            if (clazz.isPrimitive()) {
                wrapper = makeWrapper(bytecode, clazz, wrapper);
            }
            else {
                bytecode.addAload(wrapper);
                ++wrapper;
            }
            bytecode.addOpcode(83);
        }
    }
    
    private static int makeWrapper(final Bytecode bytecode, final Class<?> clazz, final int n) {
        final int typeIndex = FactoryHelper.typeIndex(clazz);
        final String s = FactoryHelper.wrapperTypes[typeIndex];
        bytecode.addNew(s);
        bytecode.addOpcode(89);
        addLoad(bytecode, n, clazz);
        bytecode.addInvokespecial(s, "<init>", FactoryHelper.wrapperDesc[typeIndex]);
        return n + FactoryHelper.dataSize[typeIndex];
    }
    
    private static void addUnwrapper(final Bytecode bytecode, final Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == Void.TYPE) {
                bytecode.addOpcode(87);
            }
            else {
                final int typeIndex = FactoryHelper.typeIndex(clazz);
                final String s = FactoryHelper.wrapperTypes[typeIndex];
                bytecode.addCheckcast(s);
                bytecode.addInvokevirtual(s, FactoryHelper.unwarpMethods[typeIndex], FactoryHelper.unwrapDesc[typeIndex]);
            }
        }
        else {
            bytecode.addCheckcast(clazz.getName());
        }
    }
    
    private static MethodInfo makeWriteReplace(final ConstPool constPool) {
        final MethodInfo methodInfo = new MethodInfo(constPool, "writeReplace", "()Ljava/lang/Object;");
        final String[] exceptions = { "java.io.ObjectStreamException" };
        final ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute(constPool);
        exceptionsAttribute.setExceptions(exceptions);
        methodInfo.setExceptionsAttribute(exceptionsAttribute);
        final Bytecode bytecode = new Bytecode(constPool, 0, 1);
        bytecode.addAload(0);
        bytecode.addInvokestatic("dev.imanity.antixray.libs.javassist.util.proxy.RuntimeSupport", "makeSerializedProxy", "(Ljava/lang/Object;)Ljavassist/util/proxy/SerializedProxy;");
        bytecode.addOpcode(176);
        methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
        return methodInfo;
    }
    
    static {
        ProxyFactory.onlyPublicMethods = false;
        OBJECT_TYPE = Object.class;
        HANDLER_TYPE = 'L' + MethodHandler.class.getName().replace('.', '/') + ';';
        HANDLER_SETTER_TYPE = "(" + ProxyFactory.HANDLER_TYPE + ")V";
        HANDLER_GETTER_TYPE = "()" + ProxyFactory.HANDLER_TYPE;
        ProxyFactory.useCache = true;
        ProxyFactory.useWriteReplace = true;
        ProxyFactory.proxyCache = new WeakHashMap<ClassLoader, Map<String, ProxyDetails>>();
        ProxyFactory.hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        ProxyFactory.classLoaderProvider = new ClassLoaderProvider() {
            @Override
            public ClassLoader get(final ProxyFactory proxyFactory) {
                return proxyFactory.getClassLoader0();
            }
        };
        ProxyFactory.nameGenerator = new UniqueName() {
            private final String sep = "_$$_jvst" + Integer.toHexString(this.hashCode() & 0xFFF) + "_";
            private int counter = 0;
            
            @Override
            public String get(final String str) {
                return str + this.sep + Integer.toHexString(this.counter++);
            }
        };
        ProxyFactory.sorter = new Comparator<Map.Entry<String, Method>>() {
            @Override
            public int compare(final Map.Entry<String, Method> entry, final Map.Entry<String, Method> entry2) {
                return entry.getKey().compareTo((String)entry2.getKey());
            }
        };
    }
    
    static class ProxyDetails
    {
        byte[] signature;
        Reference<Class<?>> proxyClass;
        boolean isUseWriteReplace;
        
        ProxyDetails(final byte[] signature, final Class<?> referent, final boolean isUseWriteReplace) {
            this.signature = signature;
            this.proxyClass = new WeakReference<Class<?>>(referent);
            this.isUseWriteReplace = isUseWriteReplace;
        }
    }
    
    static class Find2MethodsArgs
    {
        String methodName;
        String delegatorName;
        String descriptor;
        int origIndex;
        
        Find2MethodsArgs(final String methodName, final String delegatorName, final String descriptor, final int origIndex) {
            this.methodName = methodName;
            this.delegatorName = delegatorName;
            this.descriptor = descriptor;
            this.origIndex = origIndex;
        }
    }
    
    public interface ClassLoaderProvider
    {
        ClassLoader get(final ProxyFactory p0);
    }
    
    public interface UniqueName
    {
        String get(final String p0);
    }
}
