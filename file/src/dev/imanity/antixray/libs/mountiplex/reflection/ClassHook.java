// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedHook;
import dev.imanity.antixray.libs.mountiplex.reflection.util.InputTypeMap;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedCodeInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassDeclarationResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Declaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import java.util.Collection;
import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import java.lang.reflect.Method;
import java.util.Map;

public class ClassHook<T extends ClassHook<?>> extends ClassInterceptor
{
    private static Map<Class<?>, HookMethodList> hookMethodMap;
    public T base;
    private final HookMethodList methods;
    
    public ClassHook(final ClassLoader classLoader) {
        super(classLoader);
        this.methods = loadMethodList(this.getClass());
        this.base = this.initBaseHook();
    }
    
    public ClassHook() {
        this.methods = loadMethodList(this.getClass());
        this.base = this.initBaseHook();
    }
    
    private T initBaseHook() {
        return new BaseClassInterceptor(this.getHookClassLoader(), this.methods).hook(this);
    }
    
    @Override
    protected final Invoker<?> getCallback(final Method method) {
        throw new UnsupportedOperationException("Should not be called");
    }
    
    @Override
    protected Invoker<?> getCallback(final Class<?> clazz, final Method method) {
        final TypeDeclaration fromClass = TypeDeclaration.fromClass(method.getDeclaringClass());
        final MethodDeclaration resolveMethodAlias = Resolver.resolveMethodAlias(TypeDeclaration.fromClass(clazz), method);
        ClassResolver classResolver = resolveMethodAlias.getResolver();
        classResolver.setClassLoader(this.methods.hookClassLoader);
        if (this.methods.classImports.length > 0 || this.methods.classPackage != null) {
            classResolver = classResolver.clone();
            if (this.methods.classPackage != null) {
                classResolver.setPackage(this.methods.classPackage, false);
            }
            classResolver.addImports(Arrays.asList(this.methods.classImports));
        }
        for (final HookMethodEntry hookMethodEntry : this.methods.entries) {
            ClassResolver clone = classResolver;
            if (hookMethodEntry.hookImports.length > 0 || hookMethodEntry.hookPackage != null || hookMethodEntry.variablesResolver != null) {
                clone = clone.clone();
                if (hookMethodEntry.hookPackage != null) {
                    clone.setPackage(hookMethodEntry.hookPackage, false);
                }
                if (hookMethodEntry.hookImports.length > 0) {
                    clone.addImports(Arrays.asList(hookMethodEntry.hookImports));
                }
                if (hookMethodEntry.variablesResolver != null) {
                    clone.setAllVariables(hookMethodEntry.variablesResolver);
                }
            }
            final MethodDeclaration resolveName = new MethodDeclaration(clone, hookMethodEntry.declaration).resolveName();
            if (resolveName.isValid() && resolveName.isResolved() && resolveName.match(resolveMethodAlias)) {
                hookMethodEntry.setMethod(fromClass, method);
                return hookMethodEntry;
            }
        }
        return null;
    }
    
    @Override
    protected void onClassGenerated(final Class<?> clazz) {
        super.onClassGenerated(clazz);
        final TypeDeclaration fromClass = TypeDeclaration.fromClass(clazz);
        for (final HookMethodEntry hookMethodEntry : this.methods.entries) {
            if (!hookMethodEntry.optional && !hookMethodEntry.foundMethod(fromClass)) {
                Class superclass = clazz;
                if (EnhancedObject.class.isAssignableFrom(clazz)) {
                    superclass = clazz.getSuperclass();
                    if (superclass.equals(Object.class) && clazz.getInterfaces().length > 1) {
                        for (final Class clazz2 : clazz.getInterfaces()) {
                            if (clazz2 != EnhancedObject.class) {
                                superclass = clazz2;
                                break;
                            }
                        }
                    }
                }
                MountiplexUtil.LOGGER.warning("Hooked method " + hookMethodEntry.toString() + " was not found in " + MPLType.getName(superclass));
            }
        }
    }
    
    private static HookMethodList loadMethodList(final Class<?> clazz) {
        if (!ClassHook.class.isAssignableFrom(clazz)) {
            return new HookMethodList();
        }
        HookMethodList list = ClassHook.hookMethodMap.get(clazz);
        if (list == null) {
            list = new HookMethodList(clazz);
            for (final Method method : clazz.getDeclaredMethods()) {
                final HookMethod hookMethod = method.getAnnotation(HookMethod.class);
                if (hookMethod != null) {
                    final HookMethodEntry hookMethodEntry = new HookMethodEntry(list, method, hookMethod.value(), hookMethod.optional());
                    if (hookMethodEntry.enabled) {
                        list.entries.add(hookMethodEntry);
                    }
                }
            }
            list.entries.addAll(loadMethodList(clazz.getSuperclass()).entries);
            ClassHook.hookMethodMap.put(clazz, list);
        }
        return list;
    }
    
    private static ClassDeclarationResolver loadHookVariablesResolver(final Class<?> clazz, final String s) {
        if (s == null) {
            return null;
        }
        try {
            final ClassResolver classResolver = new ClassResolver();
            classResolver.setClassLoader(clazz.getClassLoader());
            classResolver.setDeclaredClass(ClassDeclarationResolver.class);
            return (ClassDeclarationResolver)GeneratedCodeInvoker.create(new MethodDeclaration(classResolver, "public static ClassDeclarationResolver run() {\n    return " + s + ";\n}")).invoke(null);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize hook load variables: " + s, thrown);
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to load Hook Variables for " + clazz.getName());
            return null;
        }
    }
    
    static {
        ClassHook.hookMethodMap = new HashMap<Class<?>, HookMethodList>();
        MountiplexUtil.registerUnloader(new Runnable() {
            @Override
            public void run() {
                ClassHook.hookMethodMap = (Map<Class<?>, HookMethodList>)new HashMap(0);
            }
        });
    }
    
    private static class HookMethodList
    {
        public final ClassLoader hookClassLoader;
        public final List<HookMethodEntry> entries;
        public final String[] classImports;
        public final String classPackage;
        public final ClassDeclarationResolver variablesResolver;
        
        public HookMethodList() {
            this.entries = new ArrayList<HookMethodEntry>();
            this.hookClassLoader = HookMethodList.class.getClassLoader();
            this.classImports = new String[0];
            this.classPackage = null;
            this.variablesResolver = null;
        }
        
        public HookMethodList(final Class<?> clazz) {
            this.entries = new ArrayList<HookMethodEntry>();
            this.hookClassLoader = clazz.getClassLoader();
            this.classImports = ReflectionUtil.getAllClassesAndInterfaces(clazz).flatMap(clazz2 -> Stream.of((Object[])clazz2.getDeclaredAnnotationsByType(HookImport.class))).map((Function<? super Object, ?>)HookImport::value).toArray(String[]::new);
            final HookPackage hookPackage = clazz.getAnnotation(HookPackage.class);
            this.classPackage = ((hookPackage == null) ? null : hookPackage.value());
            this.variablesResolver = loadHookVariablesResolver(clazz, ReflectionUtil.recurseFindAnnotationValue(clazz, HookLoadVariables.class, HookLoadVariables::value, null));
        }
    }
    
    private static class BaseClassInterceptor extends ClassInterceptor
    {
        private final HookMethodList methodList;
        
        public BaseClassInterceptor(final ClassLoader classLoader, final HookMethodList methodList) {
            super(classLoader);
            this.methodList = methodList;
        }
        
        @Override
        protected Invoker<?> getCallback(final Method obj) {
            final Iterator<HookMethodEntry> iterator = this.methodList.entries.iterator();
            while (iterator.hasNext()) {
                final HookMethodEntry hookMethodEntry;
                if ((hookMethodEntry = iterator.next()).method.equals(obj)) {
                    return hookMethodEntry.baseInvokable;
                }
            }
            return null;
        }
    }
    
    private static class HookMethodEntry extends InterceptorCallback
    {
        public final InputTypeMap<Method> superMethodMap;
        public final Map<Class<?>, Invoker<?>> superInvokerMap;
        public final ClassDeclarationResolver variablesResolver;
        public final HookMethodList owner;
        public final String declaration;
        public final boolean optional;
        public final boolean enabled;
        public final Method method;
        public final String[] hookImports;
        public final String hookPackage;
        public final Invoker<?> baseInvokable;
        
        public HookMethodEntry(final HookMethodList p0, final Method p1, final String p2, final boolean p3) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokespecial   dev/imanity/antixray/libs/mountiplex/reflection/ClassInterceptor$InterceptorCallback.<init>:()V
            //     4: aload_0        
            //     5: new             Ldev/imanity/antixray/libs/mountiplex/reflection/util/InputTypeMap;
            //     8: dup            
            //     9: invokespecial   dev/imanity/antixray/libs/mountiplex/reflection/util/InputTypeMap.<init>:()V
            //    12: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.superMethodMap:Ldev/imanity/antixray/libs/mountiplex/reflection/util/InputTypeMap;
            //    15: aload_0        
            //    16: new             Ljava/util/HashMap;
            //    19: dup            
            //    20: invokespecial   java/util/HashMap.<init>:()V
            //    23: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.superInvokerMap:Ljava/util/Map;
            //    26: aload_0        
            //    27: aload_0        
            //    28: invokedynamic   BootstrapMethod #0, invokeVA:(Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry;)Ldev/imanity/antixray/libs/mountiplex/reflection/util/fast/Invoker;
            //    33: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.baseInvokable:Ldev/imanity/antixray/libs/mountiplex/reflection/util/fast/Invoker;
            //    36: aload_0        
            //    37: aload_1        
            //    38: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.owner:Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodList;
            //    41: aload_0        
            //    42: aload_2        
            //    43: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.method:Ljava/lang/reflect/Method;
            //    46: aload_0        
            //    47: aload_3        
            //    48: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.declaration:Ljava/lang/String;
            //    51: aload_0        
            //    52: iload           4
            //    54: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.optional:Z
            //    57: aload_0        
            //    58: aload_0        
            //    59: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
            //    62: invokevirtual   java/lang/Class.getClassLoader:()Ljava/lang/ClassLoader;
            //    65: aload_0        
            //    66: ldc             "interceptorCallback"
            //    68: aload_2        
            //    69: invokestatic    dev/imanity/antixray/libs/mountiplex/reflection/util/fast/InitInvoker.forMethod:(Ljava/lang/ClassLoader;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/reflect/Method;)Ldev/imanity/antixray/libs/mountiplex/reflection/util/fast/InitInvoker;
            //    72: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.interceptorCallback:Ldev/imanity/antixray/libs/mountiplex/reflection/util/fast/Invoker;
            //    75: aload_0        
            //    76: aload_2        
            //    77: ldc             Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookImport;.class
            //    79: invokevirtual   java/lang/reflect/Method.getDeclaredAnnotationsByType:(Ljava/lang/Class;)[Ljava/lang/annotation/Annotation;
            //    82: checkcast       [Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookImport;
            //    85: invokestatic    java/util/stream/Stream.of:([Ljava/lang/Object;)Ljava/util/stream/Stream;
            //    88: invokedynamic   BootstrapMethod #1, apply:()Ljava/util/function/Function;
            //    93: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
            //    98: invokedynamic   BootstrapMethod #2, apply:()Ljava/util/function/IntFunction;
            //   103: invokeinterface java/util/stream/Stream.toArray:(Ljava/util/function/IntFunction;)[Ljava/lang/Object;
            //   108: checkcast       [Ljava/lang/String;
            //   111: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.hookImports:[Ljava/lang/String;
            //   114: aload_2        
            //   115: ldc             Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookPackage;.class
            //   117: invokevirtual   java/lang/reflect/Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
            //   120: checkcast       Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookPackage;
            //   123: astore          5
            //   125: aload_0        
            //   126: aload           5
            //   128: ifnonnull       135
            //   131: aconst_null    
            //   132: goto            142
            //   135: aload           5
            //   137: invokeinterface dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookPackage.value:()Ljava/lang/String;
            //   142: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.hookPackage:Ljava/lang/String;
            //   145: aload_2        
            //   146: ldc             Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookLoadVariables;.class
            //   148: invokevirtual   java/lang/reflect/Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
            //   151: checkcast       Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookLoadVariables;
            //   154: astore          6
            //   156: aload_0        
            //   157: aload           6
            //   159: ifnonnull       169
            //   162: aload_1        
            //   163: getfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodList.variablesResolver:Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;
            //   166: goto            183
            //   169: aload_2        
            //   170: invokevirtual   java/lang/reflect/Method.getDeclaringClass:()Ljava/lang/Class;
            //   173: aload           6
            //   175: invokeinterface dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookLoadVariables.value:()Ljava/lang/String;
            //   180: invokestatic    dev/imanity/antixray/libs/mountiplex/reflection/ClassHook.access$100:(Ljava/lang/Class;Ljava/lang/String;)Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;
            //   183: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.variablesResolver:Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;
            //   186: aload_2        
            //   187: ldc             Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodCondition;.class
            //   189: invokevirtual   java/lang/reflect/Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
            //   192: checkcast       Ldev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodCondition;
            //   195: astore          7
            //   197: aload           7
            //   199: ifnull          253
            //   202: new             Ldev/imanity/antixray/libs/mountiplex/reflection/declarations/ClassResolver;
            //   205: dup            
            //   206: invokespecial   dev/imanity/antixray/libs/mountiplex/reflection/declarations/ClassResolver.<init>:()V
            //   209: astore          8
            //   211: aload           8
            //   213: ldc             Ljava/lang/Object;.class
            //   215: invokevirtual   dev/imanity/antixray/libs/mountiplex/reflection/declarations/ClassResolver.setDeclaredClass:(Ljava/lang/Class;)V
            //   218: aload_0        
            //   219: getfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.variablesResolver:Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;
            //   222: ifnull          234
            //   225: aload           8
            //   227: aload_0        
            //   228: getfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.variablesResolver:Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;
            //   231: invokevirtual   dev/imanity/antixray/libs/mountiplex/reflection/declarations/ClassResolver.setAllVariables:(Ldev/imanity/antixray/libs/mountiplex/reflection/resolver/ClassDeclarationResolver;)V
            //   234: aload_0        
            //   235: aload           8
            //   237: aload           7
            //   239: invokeinterface dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodCondition.value:()Ljava/lang/String;
            //   244: invokevirtual   dev/imanity/antixray/libs/mountiplex/reflection/declarations/ClassResolver.evaluateExpression:(Ljava/lang/String;)Z
            //   247: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.enabled:Z
            //   250: goto            258
            //   253: aload_0        
            //   254: iconst_1       
            //   255: putfield        dev/imanity/antixray/libs/mountiplex/reflection/ClassHook$HookMethodEntry.enabled:Z
            //   258: return         
            //    StackMapTable: 00 07 FF 00 87 00 06 07 00 02 07 00 0D 07 00 7B 00 00 07 00 13 00 01 07 00 02 FF 00 06 00 03 07 00 02 07 00 0D 07 00 7B 00 02 07 00 02 07 00 B1 FF 00 1A 00 07 07 00 02 00 07 00 7B 00 00 00 07 00 16 00 01 07 00 02 FF 00 0D 00 03 07 00 02 00 07 00 7B 00 02 07 00 02 07 00 BF FF 00 32 00 09 07 00 02 00 00 00 00 00 00 07 00 19 07 00 C2 00 00 FF 00 12 00 01 07 00 02 00 00 FA 00 04
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Could not infer any expression.
            //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:382)
            //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:95)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:206)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:93)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:868)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:799)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:635)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:662)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:162)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:137)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:333)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:254)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:144)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @Override
        public String toString() {
            return this.declaration;
        }
        
        public boolean foundMethod(final TypeDeclaration typeDeclaration) {
            return this.superMethodMap.containsKey(typeDeclaration);
        }
        
        public void setMethod(final TypeDeclaration typeDeclaration, final Method method) {
            this.superMethodMap.put(typeDeclaration, method);
        }
        
        public ClassResolver createResolver(final Class<?> declaredClass) {
            final ClassResolver classResolver = new ClassResolver();
            classResolver.setDeclaredClass(declaredClass);
            if (this.hookPackage != null) {
                classResolver.setPackage(this.hookPackage);
            }
            else if (this.owner.classPackage != null) {
                classResolver.setPackage(this.owner.classPackage);
            }
            classResolver.addImports(Arrays.asList(this.owner.classImports));
            classResolver.addImports(Arrays.asList(this.hookImports));
            classResolver.setLogErrors(true);
            classResolver.setClassLoader(this.owner.hookClassLoader);
            return classResolver;
        }
        
        public Method findMethodIn(final TypeDeclaration typeDeclaration) {
            if (typeDeclaration == null || !typeDeclaration.isResolved()) {
                return null;
            }
            Method method = this.superMethodMap.get(typeDeclaration);
            if (method == null) {
                final MethodDeclaration discover = new MethodDeclaration(this.createResolver(typeDeclaration.type), this.declaration).discover();
                if (discover != null) {
                    method = discover.method;
                    if (method != null) {
                        this.superMethodMap.put(typeDeclaration, method);
                    }
                }
            }
            return method;
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HookPackage {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HookLoadVariables {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(HookImportList.class)
    public @interface HookImport {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HookImportList {
        HookImport[] value();
    }
    
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HookMethodCondition {
        String value();
    }
    
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HookMethod {
        String value();
        
        boolean optional() default false;
    }
}
