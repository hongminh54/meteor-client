// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template.cdn;

import io.fairyproject.log.Log;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.Objects;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import java.util.function.Function;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.BiMap;
import java.lang.reflect.Array;
import java.util.function.Supplier;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassPathResolver;
import java.util.Iterator;
import java.util.IdentityHashMap;
import java.util.Map;

public class MojangSpigotRemapper
{
    private static final ClassRemapper[] NO_REMAPPERS;
    private final Map<Class<?>, ClassRemapper> remappersByDeclaringClassName;
    private Map<Class<?>, ClassRemapper[]> recurseRemappersByDeclaringClassName;
    private MojangMappings mappings;
    private final LazyClassLookup classLookup;
    
    public MojangSpigotRemapper() {
        this.remappersByDeclaringClassName = new IdentityHashMap<Class<?>, ClassRemapper>();
        this.recurseRemappersByDeclaringClassName = new IdentityHashMap<Class<?>, ClassRemapper[]>();
        this.mappings = null;
        this.classLookup = new LazyClassLookup();
    }
    
    public String remapFieldName(final Class<?> clazz, final String s, final String s2) {
        final ClassRemapper[] remappers = this.remappersFor(clazz);
        for (int length = remappers.length, i = 0; i < length; ++i) {
            final String s3 = (String)remappers[i].fields_name_to_obfuscated.get((Object)s);
            if (s3 != null) {
                return s3;
            }
        }
        return s2;
    }
    
    public String remapFieldNameReverse(final Class<?> clazz, final String s, final String s2) {
        final ClassRemapper[] remappers = this.remappersFor(clazz);
        for (int length = remappers.length, i = 0; i < length; ++i) {
            final String s3 = (String)remappers[i].fields_obfuscated_to_name.get((Object)s);
            if (s3 != null) {
                return s3;
            }
        }
        return s2;
    }
    
    public String remapMethodName(final Class<?> clazz, final String s, final Class<?>[] this2, final String s2) {
        final ClassRemapper[] remappers = this.remappersFor(clazz);
        for (int length = remappers.length, i = 0; i < length; ++i) {
            for (final MethodDetails methodDetails : remappers[i].methods_by_name.get((Object)s)) {
                if (methodDetails.canAcceptParameters(this2)) {
                    return methodDetails.name_obfuscated;
                }
            }
        }
        return s2;
    }
    
    public String remapMethodNameReverse(final Class<?> clazz, final String s, final Class<?>[] this2, final String s2) {
        final ClassRemapper[] remappers = this.remappersFor(clazz);
        for (int length = remappers.length, i = 0; i < length; ++i) {
            for (final MethodDetails methodDetails : remappers[i].methods_by_obfuscated.get((Object)s)) {
                if (methodDetails.canAcceptParameters(this2)) {
                    return methodDetails.name;
                }
            }
        }
        return s2;
    }
    
    public void removeMethodMapping(final Class<?> clazz, final String s, final Class<?>... array) {
        final ClassRemapper[] remappers = this.remappersFor(clazz);
        for (int length = remappers.length, i = 0; i < length; ++i) {
            remappers[i].removeMethodMapping(s, array);
        }
    }
    
    private ClassRemapper[] remappersFor(final Class<?> clazz) {
        final ClassRemapper[] array = this.recurseRemappersByDeclaringClassName.get(clazz);
        if (array != null) {
            return array;
        }
        final ClassRemapper initRemapper = this.initRemapper(clazz);
        if (initRemapper == null) {
            return MojangSpigotRemapper.NO_REMAPPERS;
        }
        final ClassRemapper[] recurse = initRemapper.recurse(this::initRemapper);
        synchronized (this) {
            final IdentityHashMap recurseRemappersByDeclaringClassName = new IdentityHashMap((Map<? extends K, ? extends V>)this.recurseRemappersByDeclaringClassName);
            recurseRemappersByDeclaringClassName.put(clazz, recurse);
            this.recurseRemappersByDeclaringClassName = recurseRemappersByDeclaringClassName;
        }
        return recurse;
    }
    
    private ClassRemapper initRemapper(final Class<?> key) {
        final MojangMappings.ClassMappings forClassIfExists = this.mappings.forClassIfExists(key.getName());
        if (forClassIfExists == null) {
            return null;
        }
        ClassRemapper create;
        synchronized (this.remappersByDeclaringClassName) {
            create = this.remappersByDeclaringClassName.get(key);
        }
        if (create == null) {
            create = ClassRemapper.create(forClassIfExists, this.classLookup);
            synchronized (this.remappersByDeclaringClassName) {
                final ClassRemapper classRemapper = this.remappersByDeclaringClassName.putIfAbsent(key, create);
                if (classRemapper != null) {
                    create = classRemapper;
                }
            }
        }
        return create;
    }
    
    protected synchronized void loadMappings(final MojangMappings mappings) {
        this.remappersByDeclaringClassName.clear();
        this.recurseRemappersByDeclaringClassName = new IdentityHashMap<Class<?>, ClassRemapper[]>();
        this.classLookup.reset();
        for (final MojangMappings.ClassMappings classMappings : mappings.classes()) {
            this.classLookup.add(classMappings.name);
            for (final MojangMappings.MethodSignature methodSignature : classMappings.methods) {
                this.classLookup.add(methodSignature.returnType.name);
                final Iterator<MojangMappings.ClassSignature> iterator3 = methodSignature.parameterTypes.iterator();
                while (iterator3.hasNext()) {
                    this.classLookup.add(iterator3.next().name);
                }
            }
        }
        this.mappings = mappings;
    }
    
    public static MojangSpigotRemapper load(final String s2, final ClassPathResolver classPathResolver) {
        final MojangMappings translateClassNames = MojangMappings.fromCacheOrDownload(s2).translateClassNames(s4 -> {
            SpigotMappings.fromCacheOrDownload(s2);
            return classPathResolver2.resolveClassPath(classMappings.toSpigot(s4));
        });
        final MojangSpigotRemapper mojangSpigotRemapper = new MojangSpigotRemapper();
        mojangSpigotRemapper.loadMappings(translateClassNames);
        return mojangSpigotRemapper;
    }
    
    public static <T> Supplier<T> constantSupplier(final T t) {
        return (Supplier<T>)(() -> o);
    }
    
    public static Class<?> getArrayType(final Class<?> componentType) {
        if (componentType.isPrimitive()) {
            try {
                return Array.newInstance(componentType, 0).getClass();
            }
            catch (final IllegalArgumentException ex) {
                if (componentType == Void.TYPE) {
                    throw new IllegalArgumentException("Cannot create an array of void");
                }
                throw ex;
            }
        }
        try {
            return Class.forName("[L" + componentType.getName() + ";");
        }
        catch (final ClassNotFoundException ex2) {
            return Object[].class;
        }
    }
    
    public static Class<?> getArrayType(final Class<?> clazz, int n) {
        Class<?> arrayType = clazz;
        while (n-- > 0) {
            arrayType = getArrayType(arrayType);
        }
        return arrayType;
    }
    
    public static <T> Supplier<T> nullSupplier() {
        return (Supplier<T>)(() -> null);
    }
    
    static {
        NO_REMAPPERS = new ClassRemapper[0];
    }
    
    private static class ClassRemapper
    {
        public final Class<?> type;
        public final BiMap<String, String> fields_obfuscated_to_name;
        public final BiMap<String, String> fields_name_to_obfuscated;
        public final ListMultimap<String, MethodDetails> methods_by_name;
        public final ListMultimap<String, MethodDetails> methods_by_obfuscated;
        
        private ClassRemapper(final MojangMappings.ClassMappings classMappings, final Class<?> type) {
            this.methods_by_name = (ListMultimap<String, MethodDetails>)LinkedListMultimap.create(64);
            this.methods_by_obfuscated = (ListMultimap<String, MethodDetails>)LinkedListMultimap.create(64);
            this.type = type;
            this.fields_name_to_obfuscated = classMappings.fields_name_to_obfuscated;
            this.fields_obfuscated_to_name = classMappings.fields_obfuscated_to_name;
        }
        
        public ClassRemapper[] recurse(final Function<Class<?>, ClassRemapper> function) {
            return ReflectionUtil.getAllClassesAndInterfaces(this.type).map((Function<? super Class<?>, ?>)function).filter(Objects::nonNull).toArray(ClassRemapper[]::new);
        }
        
        public static ClassRemapper create(final MojangMappings.ClassMappings classMappings, final LazyClassLookup lazyClassLookup) {
            final Class<?> value = lazyClassLookup.get(classMappings.name);
            if (value != null) {
                final ClassRemapper classRemapper = new ClassRemapper(classMappings, value);
                final Iterator<MojangMappings.MethodSignature> iterator = classMappings.methods.iterator();
                while (iterator.hasNext()) {
                    final MethodDetails create = MethodDetails.create(iterator.next(), lazyClassLookup);
                    if (create != null) {
                        classRemapper.methods_by_name.put((Object)create.name, (Object)create);
                        classRemapper.methods_by_obfuscated.put((Object)create.name_obfuscated, (Object)create);
                    }
                }
                return classRemapper;
            }
            return null;
        }
        
        public void removeMethodMapping(final String s, final Class<?>[] this2) {
            final Iterator iterator = this.methods_by_name.get((Object)s).iterator();
            while (iterator.hasNext()) {
                final MethodDetails methodDetails = (MethodDetails)iterator.next();
                if (methodDetails.canAcceptParameters(this2)) {
                    iterator.remove();
                    this.methods_by_obfuscated.remove((Object)methodDetails.name_obfuscated, (Object)methodDetails);
                }
            }
        }
    }
    
    private static class MethodDetails
    {
        private static final Class<?>[] NO_ARGS;
        public final String name;
        public final String name_obfuscated;
        public final Class<?> returnType;
        public final Class<?>[] parameterTypes;
        
        public MethodDetails(final MojangMappings.MethodSignature methodSignature, final Class<?> returnType, final Class<?>[] parameterTypes) {
            this.name = methodSignature.name;
            this.name_obfuscated = methodSignature.name_obfuscated;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
        }
        
        @umwLDqRKqHZhDbQEHQ4srBR2MiuH5Km8euY(m = "c0V5FmNmF2bPNGZtB3XrB1dvxGV3V0RTFmRXJ2ToVVWwUkMYV1RO1GS0AjWhV1RO1GS0gHe")
        public boolean canAcceptParameters(final Class<?>[] this) {
            final int i2 = this.length;
            if (this.parameterTypes.length != i2) {
                return false;
            }
            for (int i3 = 0; i3 < i2; ++i3) {
                if (!this.parameterTypes[i3].isAssignableFrom(this[i3])) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.returnType.getName()).append(' ');
            sb.append(this.name).append(':').append(this.name_obfuscated);
            sb.append("(");
            int n = 1;
            for (final Class<?> clazz : this.parameterTypes) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(", ");
                }
                sb.append(clazz.getName());
            }
            sb.append(')');
            return sb.toString();
        }
        
        public static MethodDetails create(final MojangMappings.MethodSignature methodSignature, final LazyClassLookup lazyClassLookup) {
            final Class<?> value = lazyClassLookup.get(methodSignature.returnType.name);
            if (value == null) {
                return null;
            }
            if (methodSignature.parameterTypes.isEmpty()) {
                return new MethodDetails(methodSignature, value, MethodDetails.NO_ARGS);
            }
            final Class[] array = new Class[methodSignature.parameterTypes.size()];
            for (int i = 0; i < array.length; ++i) {
                final Class<?> value2 = lazyClassLookup.get(methodSignature.parameterTypes.get(i).name);
                if (value2 == null) {
                    return null;
                }
                array[i] = value2;
            }
            return new MethodDetails(methodSignature, value, array);
        }
        
        static {
            NO_ARGS = new Class[0];
        }
    }
    
    private static final class LazyClassLookup
    {
        private final Map<String, Supplier<Class<?>>> map;
        
        private LazyClassLookup() {
            this.map = new ConcurrentHashMap<String, Supplier<Class<?>>>();
        }
        
        public void reset() {
            this.map.clear();
            for (final Class clazz : BoxedType.getUnboxedTypes()) {
                this.map.put(clazz.getSimpleName(), (Supplier<Class<?>>)MojangSpigotRemapper.constantSupplier(clazz));
                if (clazz != Void.TYPE) {
                    String str = clazz.getSimpleName();
                    Class<?> arrayType = clazz;
                    for (int i = 0; i < 10; ++i) {
                        str += "[]";
                        arrayType = MojangSpigotRemapper.getArrayType(arrayType);
                        this.map.put(str, MojangSpigotRemapper.constantSupplier(arrayType));
                    }
                }
            }
            for (final Class clazz2 : BoxedType.getBoxedTypes()) {
                this.map.put(clazz2.getName(), (Supplier<Class<?>>)MojangSpigotRemapper.constantSupplier(clazz2));
            }
            this.map.put(String.class.getName(), (Supplier<Class<?>>)MojangSpigotRemapper.constantSupplier(String.class));
            this.map.put(List.class.getName(), (Supplier<Class<?>>)MojangSpigotRemapper.constantSupplier(List.class));
            this.map.put(Map.class.getName(), (Supplier<Class<?>>)MojangSpigotRemapper.constantSupplier(Map.class));
        }
        
        public Class<?> get(final String key) {
            return this.map.getOrDefault(key, MojangSpigotRemapper.nullSupplier()).get();
        }
        
        public void add(final String key) {
            this.map.computeIfAbsent(key, this::createNewLookup);
        }
        
        private GeneratingLookup createNewLookup(final String s) {
            if (s.endsWith("[]")) {
                int n = 0;
                String substring = s;
                do {
                    substring = substring.substring(0, substring.length() - 2);
                    ++n;
                } while (substring.endsWith("[]"));
                return new GeneratingArrayTypeLookup(this.map, s, n);
            }
            return new GeneratingLookup(this.map, s);
        }
        
        private static final class GeneratingArrayTypeLookup extends GeneratingLookup
        {
            private final int numDims;
            
            public GeneratingArrayTypeLookup(final Map<String, Supplier<Class<?>>> map, final String s, final int numDims) {
                super(map, s);
                this.numDims = numDims;
            }
            
            @Override
            public Class<?> load(String substring) {
                substring = substring.substring(0, substring.length() - this.numDims * 2);
                Class<?> clazz = super.load(substring);
                if (clazz != null) {
                    clazz = MojangSpigotRemapper.getArrayType(clazz, this.numDims);
                }
                return clazz;
            }
        }
        
        private static class GeneratingLookup implements Supplier<Class<?>>
        {
            private final Map<String, Supplier<Class<?>>> map;
            private final String name;
            
            public GeneratingLookup(final Map<String, Supplier<Class<?>>> map, final String name) {
                this.map = map;
                this.name = name;
            }
            
            public Class<?> load(final String str) {
                try {
                    return MPLType.getClassByName(str, false, this.getClass().getClassLoader());
                }
                catch (final ClassNotFoundException ex) {
                    return null;
                }
                catch (final Throwable t) {
                    Log.warn("An error occurred loading server class " + str, t, new Object[0]);
                    return null;
                }
            }
            
            @Override
            public final Class<?> get() {
                final Class<?> load = this.load(this.name);
                if (load != null) {
                    this.map.put(this.name, MojangSpigotRemapper.constantSupplier(load));
                }
                else {
                    this.map.remove(this.name);
                }
                return load;
            }
        }
    }
}
