// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion;

import java.util.function.Consumer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.conversion.type.CastingConverter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.InputTypeMap;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.StreamConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.BooleanConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.VoidTypeConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.ArrayConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.MapConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.CollectionConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.EnumConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.ToStringConversion;
import dev.imanity.antixray.libs.mountiplex.conversion.builtin.NumberConversion;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.HashMap;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.conversion.type.ChainConverter;
import java.util.Arrays;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import dev.imanity.antixray.libs.mountiplex.conversion.type.NullConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.FieldDeclaration;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.conversion.type.AnnotatedProvider;
import dev.imanity.antixray.libs.mountiplex.conversion.annotations.ProviderMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.conversion.type.AnnotatedConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.annotations.ConverterMethod;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.ArrayList;
import java.util.Map;

public class Conversion
{
    private static final DeferLock deferLock;
    private static final Map<TypeTuple, Converter<Object, Object>> converters;
    private static final ArrayList<ConverterProvider> providers;
    
    public static void registerConverter(final Converter<?, ?> converter) {
        try {
            verifyConverter(converter);
            registerConverterImpl(converter);
            if (converter instanceof DuplexConverter) {
                registerConverterImpl(((DuplexConverter)converter).reverse());
            }
        }
        catch (final InvalidConverterException ex) {
            MountiplexUtil.LOGGER.warning(ex.getMessage() + ": " + converter);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "An error occurred registering " + converter, thrown);
        }
    }
    
    public static void registerProvider(final ConverterProvider converterProvider) {
        if (converterProvider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        Conversion.deferLock.schedule(() -> {
            Conversion.providers.add(e);
            OutputConverterList.resetAll();
            OutputConverterTree.resetAll();
            Conversion.converters.clear();
        });
    }
    
    public static void registerConverters(final Class<?> clazz) {
        for (final Method method : clazz.getDeclaredMethods()) {
            Label_0252: {
                if (method.getAnnotation(ConverterMethod.class) != null) {
                    try {
                        final TypeDeclaration type = AnnotatedConverter.parseType(method, true);
                        final TypeDeclaration type2 = AnnotatedConverter.parseType(method, false);
                        if (type == null || type2 == null) {
                            break Label_0252;
                        }
                        final MethodDeclaration methodDeclaration = new MethodDeclaration(ClassResolver.DEFAULT, method);
                        if (type.hasTypeVariables() || type2.hasTypeVariables()) {
                            registerProvider(new AnnotatedConverter.GenericProvider(methodDeclaration, type, type2));
                        }
                        else {
                            registerConverter(new AnnotatedConverter(methodDeclaration, type, type2));
                        }
                    }
                    catch (final Throwable thrown) {
                        MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to register static converter method " + new MethodDeclaration(ClassResolver.DEFAULT, method).toString(), thrown);
                    }
                }
                if (method.getAnnotation(ProviderMethod.class) != null) {
                    try {
                        registerProvider(new AnnotatedProvider(method));
                    }
                    catch (final Throwable thrown2) {
                        MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to register static provider method " + new MethodDeclaration(ClassResolver.DEFAULT, method).toString(), thrown2);
                    }
                }
            }
        }
        for (final Field field : clazz.getDeclaredFields()) {
            Label_0481: {
                if (Modifier.isStatic(field.getModifiers())) {
                    if (Converter.class.isAssignableFrom(field.getType())) {
                        Converter converter;
                        try {
                            field.setAccessible(true);
                            converter = (Converter)field.get(null);
                        }
                        catch (final Throwable thrown3) {
                            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to register static converter field " + new FieldDeclaration(ClassResolver.DEFAULT, field).toString(), thrown3);
                            break Label_0481;
                        }
                        registerConverter(converter);
                    }
                    if (ConverterProvider.class.isAssignableFrom(field.getType())) {
                        ConverterProvider converterProvider;
                        try {
                            field.setAccessible(true);
                            converterProvider = (ConverterProvider)field.get(null);
                        }
                        catch (final Throwable thrown4) {
                            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to register static provider field " + new FieldDeclaration(ClassResolver.DEFAULT, field).toString(), thrown4);
                            break Label_0481;
                        }
                        registerProvider(converterProvider);
                    }
                }
            }
        }
    }
    
    public static <T> InputConverter<T> find(final Class<T> clazz) {
        return (InputConverter<T>)find(TypeDeclaration.fromClass(clazz));
    }
    
    public static <I, O> Converter<I, O> find(final Class<I> clazz, final Class<O> clazz2) {
        return (Converter<I, O>)find(TypeDeclaration.fromClass(clazz), TypeDeclaration.fromClass(clazz2));
    }
    
    public static InputConverter<?> find(final TypeDeclaration typeDeclaration) {
        Conversion.deferLock.lock();
        try {
            final OutputConverterTree ifExists = OutputConverterTree.getIfExists(typeDeclaration);
            if (ifExists != null) {
                return ifExists.converter;
            }
        }
        finally {
            Conversion.deferLock.unlock();
        }
        initType(typeDeclaration);
        Conversion.deferLock.lock();
        try {
            return OutputConverterTree.get(typeDeclaration).converter;
        }
        finally {
            Conversion.deferLock.unlock();
        }
    }
    
    public static Converter<Object, Object> find(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        TypeTuple key;
        try {
            key = new TypeTuple(typeDeclaration, typeDeclaration2);
        }
        catch (final RuntimeException ex) {
            if (typeDeclaration == null) {
                throw new IllegalArgumentException("Input type is null");
            }
            if (typeDeclaration2 == null) {
                throw new IllegalArgumentException("Output type is null");
            }
            throw ex;
        }
        OutputConverterTree outputConverterTree = null;
        OutputConverterTree.Node access$300 = null;
        Conversion.deferLock.lock();
        try {
            Object discoverConverter = Conversion.converters.get(key);
            if (discoverConverter != null) {
                return (Converter<Object, Object>)discoverConverter;
            }
            if (typeDeclaration.isInstanceOf(typeDeclaration2)) {
                discoverConverter = new NullConverter(typeDeclaration, typeDeclaration2);
                Conversion.converters.put(key, (Converter<Object, Object>)discoverConverter);
                return (Converter<Object, Object>)discoverConverter;
            }
            outputConverterTree = OutputConverterTree.getIfExists(typeDeclaration2);
            if (outputConverterTree != null) {
                access$300 = outputConverterTree.findInMapping(typeDeclaration);
            }
        }
        finally {
            Conversion.deferLock.unlock();
        }
        if (outputConverterTree == null) {
            initType(typeDeclaration2);
        }
        if (access$300 == null) {
            initType(typeDeclaration);
        }
        Conversion.deferLock.lock();
        Object discoverConverter;
        try {
            final Converter converter = Conversion.converters.get(key);
            if (converter != null) {
                return converter;
            }
            if (outputConverterTree == null) {
                outputConverterTree = OutputConverterTree.get(typeDeclaration2);
            }
            discoverConverter = outputConverterTree.discoverConverter(typeDeclaration);
            if (discoverConverter == null) {
                return null;
            }
            if (((OutputConverterTree.NodeConverterProvider)discoverConverter).isToConverterLockSafe()) {
                final Converter<Object, Object> converter2 = ((OutputConverterTree.NodeConverterProvider)discoverConverter).toConverter(typeDeclaration, typeDeclaration2);
                if (converter2 == null) {
                    return null;
                }
                Conversion.converters.put(key, converter2);
                if (converter2 instanceof DuplexConverter) {
                    Conversion.converters.put(key.reverse(), ((DuplexConverter)converter2).reverse());
                }
                return converter2;
            }
        }
        finally {
            Conversion.deferLock.unlock();
        }
        final Converter<Object, Object> converter3 = ((OutputConverterTree.NodeConverterProvider)discoverConverter).toConverter(typeDeclaration, typeDeclaration2);
        if (converter3 == null) {
            return null;
        }
        Conversion.deferLock.lock();
        try {
            final Converter<Object, Object> converter4 = Conversion.converters.putIfAbsent(key, (DuplexConverter)converter3);
            if (converter4 != null) {
                return converter4;
            }
            if (converter3 instanceof DuplexConverter) {
                Conversion.converters.put(key.reverse(), ((DuplexConverter)converter3).reverse());
            }
        }
        finally {
            Conversion.deferLock.unlock();
        }
        return converter3;
    }
    
    public static <I, O> DuplexConverter<I, O> findDuplex(final Class<I> clazz, final Class<O> clazz2) {
        return DuplexConverter.pair((Converter<?, O>)find((Class<?>)clazz, (Class<B>)clazz2), (Converter<?, I>)find((Class<?>)clazz2, (Class<A>)clazz));
    }
    
    public static DuplexConverter<Object, Object> findDuplex(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        return DuplexConverter.pair(find(typeDeclaration, typeDeclaration2), find(typeDeclaration2, typeDeclaration));
    }
    
    public static void debugTree(final Class<?> clazz, final Class<?> clazz2) {
        debugTree(TypeDeclaration.fromClass(clazz), TypeDeclaration.fromClass(clazz2));
    }
    
    public static void debugTree(final TypeDeclaration obj, final TypeDeclaration obj2) {
        final OutputConverterTree value = OutputConverterTree.get(obj2);
        final StringBuilder x = new StringBuilder();
        final String string = "====== Converting " + obj + " -> " + obj2 + " ======";
        x.append(string).append('\n');
        value.root.debugPrint((OutputConverterTree.Node)value.mapping.get(obj), x, 2);
        for (int i = 0; i < string.length(); ++i) {
            x.append('=');
        }
        x.append('\n');
        System.out.println(x);
    }
    
    public static void debugExportConverterTree(final String name) {
        try {
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(name));
            try {
                Conversion.deferLock.lock();
                try {
                    final Iterator<Converter<Object, Object>> iterator = Conversion.converters.values().iterator();
                    while (iterator.hasNext()) {
                        outputStreamWriter.write(iterator.next().toString());
                        outputStreamWriter.write("\r\n\r\n");
                    }
                }
                finally {
                    Conversion.deferLock.unlock();
                }
            }
            finally {
                outputStreamWriter.close();
            }
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "[Debug] Failed to export converter tree", thrown);
        }
    }
    
    private static void registerConverterImpl(final Converter<?, ?> converter) {
        Conversion.deferLock.schedule(() -> {
            addConverterToMapping(converter2);
            if (converter2.output.genericTypes.length == 0) {
                final int length = converter2.output.type.getTypeParameters().length;
                if (length > 0) {
                    final TypeDeclaration[] array = new TypeDeclaration[length];
                    Arrays.fill(array, TypeDeclaration.ANY);
                    converter2.output.setGenericTypes(array);
                    final TypeDeclaration typeDeclaration;
                    new ChainConverter(Arrays.asList(converter2, new NullConverter(converter2.output, typeDeclaration)));
                    final ChainConverter chainConverter;
                    addConverterToMapping(chainConverter);
                }
            }
        });
    }
    
    private static void addConverterToMapping(final Converter<?, ?> converter) {
        OutputConverterList.get(converter.output).addConverter(converter);
        OutputConverterTree.resetTypeChange(converter.input);
        OutputConverterTree.resetTypeChange(converter.output);
        Conversion.converters.put(new TypeTuple(converter), (Converter<Object, Object>)converter);
    }
    
    private static boolean initType(final TypeDeclaration typeDeclaration) {
        if (typeDeclaration.type != null && Template.Handle.class.isAssignableFrom(typeDeclaration.type)) {
            Resolver.initializeClass(typeDeclaration.type);
            return true;
        }
        return false;
    }
    
    private static void initTypeAndProcessPending(final TypeDeclaration typeDeclaration) {
        if (initType(typeDeclaration)) {
            Conversion.deferLock.processPending();
        }
    }
    
    private static void verifyConverter(final Converter<?, ?> converter) {
        if (converter == null) {
            throw new InvalidConverterException("Converter is null");
        }
        if (!converter.input.isValid()) {
            throw new InvalidConverterException("Converter has invalid input");
        }
        if (!converter.output.isValid()) {
            throw new InvalidConverterException("Converter has invalid output");
        }
        if (!converter.input.isResolved()) {
            throw new InvalidConverterException("Converter has unresolved input");
        }
        if (!converter.output.isResolved()) {
            throw new InvalidConverterException("Converter has unresolved output");
        }
    }
    
    static {
        deferLock = new DeferLock();
        converters = new HashMap<TypeTuple, Converter<Object, Object>>();
        providers = new ArrayList<ConverterProvider>();
        for (final Class clazz : BoxedType.getUnboxedTypes()) {
            final Class<?> boxedType = BoxedType.getBoxedType(clazz);
            registerConverter(new NullConverter(clazz, boxedType));
            registerConverter(new NullConverter(boxedType, clazz));
        }
        NumberConversion.register();
        ToStringConversion.register();
        EnumConversion.register();
        CollectionConversion.register();
        MapConversion.register();
        ArrayConversion.register();
        VoidTypeConverter.register();
        BooleanConversion.register();
        StreamConversion.register();
        MountiplexUtil.registerUnloader(new Runnable() {
            @Override
            public void run() {
                OutputConverterTree.trees = (HashMap<TypeDeclaration, OutputConverterTree>)new HashMap(0);
                OutputConverterList.mapping = (Map<TypeDeclaration, OutputConverterList>)new HashMap(0);
            }
        });
    }
    
    private static class InvalidConverterException extends IllegalStateException
    {
        private static final long serialVersionUID = 2555039929011231357L;
        
        public InvalidConverterException(final String s) {
            super(s);
        }
    }
    
    private static final class OutputConverterTree
    {
        private static HashMap<TypeDeclaration, OutputConverterTree> trees;
        private final Node root;
        private final InputTypeMap<Node> mapping;
        private final ArrayList<Node> lastNodes;
        private final ArrayList<Node> nextNodes;
        private Converter<?, Object> nullConverter;
        private boolean nullConverterSearched;
        private boolean isReset;
        public final InputConverter<Object> converter;
        private int stepStuckCounter;
        
        public OutputConverterTree(final TypeDeclaration typeDeclaration) {
            this.mapping = new InputTypeMap<Node>();
            this.lastNodes = new ArrayList<Node>();
            this.nextNodes = new ArrayList<Node>();
            this.nullConverter = null;
            this.nullConverterSearched = false;
            this.isReset = false;
            this.stepStuckCounter = 0;
            this.root = new Node(null, new NullConverter(typeDeclaration, typeDeclaration));
            this.reset();
            this.converter = new InputConverter<Object>(typeDeclaration) {
                @Override
                public Converter<Object, Object> getConverter(final TypeDeclaration typeDeclaration) {
                    Conversion.deferLock.lock();
                    NodeConverterProvider discoverConverter;
                    try {
                        discoverConverter = OutputConverterTree.this.discoverConverter(typeDeclaration);
                        if (discoverConverter == null) {
                            return null;
                        }
                        if (discoverConverter.isToConverterLockSafe()) {
                            return discoverConverter.toConverter(typeDeclaration, this.output);
                        }
                    }
                    finally {
                        Conversion.deferLock.unlock();
                    }
                    return discoverConverter.toConverter(typeDeclaration, this.output);
                }
                
                @Override
                public Converter<?, Object> getNullConverter() {
                    Conversion.deferLock.lock();
                    try {
                        if (!OutputConverterTree.this.nullConverterSearched) {
                            OutputConverterTree.this.nullConverterSearched = true;
                            OutputConverterTree.this.nullConverter = null;
                            for (final Converter converter : OutputConverterList.get(this.output).getConverters()) {
                                if (converter.acceptsNullInput()) {
                                    OutputConverterTree.this.nullConverter = converter;
                                    break;
                                }
                            }
                        }
                        return OutputConverterTree.this.nullConverter;
                    }
                    finally {
                        Conversion.deferLock.unlock();
                    }
                }
            };
        }
        
        public final NodeConverterProvider discoverConverter(final TypeDeclaration typeDeclaration) {
            if (typeDeclaration.type == null) {
                throw new IllegalArgumentException("Unresolved input type: " + typeDeclaration.toString());
            }
            if (typeDeclaration.type.equals(Object.class)) {
                return (p0, p1) -> this.converter;
            }
            while (!this.generateDeeper()) {}
            NodeConverterProvider inMapping = this.findInMapping(typeDeclaration);
            if (inMapping == null && this.converter.output.isInstanceOf(typeDeclaration)) {
                inMapping = CastingConverter::new;
            }
            return inMapping;
        }
        
        public boolean generateDeeper() {
            this.stepStuckCounter = 0;
            this.isReset = false;
            while (!this.nextNodes.isEmpty()) {
                this.lastNodes.clear();
                this.lastNodes.addAll(this.nextNodes);
                this.nextNodes.clear();
                for (final Node node : this.lastNodes) {
                    node.step();
                    if (this.isReset) {
                        return false;
                    }
                    this.nextNodes.addAll(node.children);
                }
                if (this.nextNodes.isEmpty()) {
                    this.lastNodes.clear();
                    this.lastNodes.trimToSize();
                    this.nextNodes.trimToSize();
                }
            }
            return true;
        }
        
        private Node findInMapping(final TypeDeclaration typeDeclaration) {
            return this.mapping.get(typeDeclaration);
        }
        
        private static final Converter<Object, Object> resolveInput(final Converter<?, ?> converter, final TypeDeclaration typeDeclaration) {
            if (converter instanceof InputConverter) {
                final Converter converter2 = ((InputConverter)converter).getConverter(typeDeclaration);
                if (converter2 != null) {
                    return converter2;
                }
            }
            return (Converter<Object, Object>)converter;
        }
        
        public final void reset() {
            this.nullConverterSearched = false;
            this.mapping.clear();
            this.mapping.put(this.root.converter.input, this.root);
            this.lastNodes.clear();
            this.nextNodes.clear();
            this.nextNodes.add(this.root);
            this.isReset = true;
        }
        
        public static void resetTypeChange(final TypeDeclaration typeDeclaration) {
            for (final OutputConverterTree outputConverterTree : OutputConverterTree.trees.values()) {
                if (outputConverterTree.mapping.containsKey(typeDeclaration)) {
                    outputConverterTree.reset();
                }
            }
        }
        
        public static void resetAll() {
            final Iterator<OutputConverterTree> iterator = OutputConverterTree.trees.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
        }
        
        public static OutputConverterTree getIfExists(final TypeDeclaration key) {
            return OutputConverterTree.trees.get(key);
        }
        
        public static OutputConverterTree get(final TypeDeclaration key) {
            return OutputConverterTree.trees.computeIfAbsent(key, OutputConverterTree::new);
        }
        
        static {
            OutputConverterTree.trees = new HashMap<TypeDeclaration, OutputConverterTree>();
        }
        
        @FunctionalInterface
        private interface NodeConverterProvider
        {
            Converter<Object, Object> toConverter(final TypeDeclaration p0, final TypeDeclaration p1);
            
            default boolean isToConverterLockSafe() {
                return true;
            }
        }
        
        private final class Node implements NodeConverterProvider
        {
            private static final int MAX_STEPS = 10000;
            public final Node previous;
            public final Converter<Object, Object> converter;
            public final ArrayList<Node> children;
            public final int cost;
            
            public Node(final Node previous, final Converter<?, ?> converter) {
                this.children = new ArrayList<Node>();
                this.converter = (Converter<Object, Object>)converter;
                this.previous = previous;
                this.cost = ((previous == null) ? 0 : (previous.cost + converter.getCost() + 1));
            }
            
            @Override
            public Converter<Object, Object> toConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
                if (this.converter instanceof InputConverter && !((InputConverter)this.converter).canConvert(typeDeclaration)) {
                    if (typeDeclaration2.isInstanceOf(typeDeclaration)) {
                        return (Converter<Object, Object>)new CastingConverter(typeDeclaration, typeDeclaration2);
                    }
                    return null;
                }
                else {
                    if (this == OutputConverterTree.this.root || this.previous == OutputConverterTree.this.root) {
                        return resolveInput(this.converter, typeDeclaration);
                    }
                    TypeDeclaration output = typeDeclaration;
                    final ArrayList list = new ArrayList();
                    Node previous = this;
                    do {
                        final Converter access$900 = resolveInput(previous.converter, output);
                        list.add(access$900);
                        output = access$900.output;
                        previous = previous.previous;
                    } while (previous != OutputConverterTree.this.root);
                    return new ChainConverter<Object, Object>(list);
                }
            }
            
            @Override
            public boolean isToConverterLockSafe() {
                Node previous = this;
                while (!(previous.converter instanceof InputConverter)) {
                    previous = previous.previous;
                    if (previous == OutputConverterTree.this.root) {
                        return true;
                    }
                }
                return false;
            }
            
            public final void step() {
                if (OutputConverterTree.this.stepStuckCounter >= 10010) {
                    return;
                }
                for (final Converter converter : OutputConverterList.get(this.converter.input).getConverters()) {
                    final Node e = new Node(this, converter);
                    final Node node = (Node)OutputConverterTree.this.mapping.get(converter.input);
                    if (node == null || node.cost > e.cost) {
                        OutputConverterTree.this.mapping.put(converter.input, e);
                        this.children.add(e);
                        ++OutputConverterTree.this.stepStuckCounter;
                        if (OutputConverterTree.this.stepStuckCounter == 10000) {
                            MountiplexUtil.LOGGER.severe("Cyclical Conversion step() detected!");
                            if (this.previous != null) {
                                MountiplexUtil.LOGGER.severe("Previous: " + this.previous.converter);
                            }
                        }
                        if (OutputConverterTree.this.stepStuckCounter < 10000) {
                            continue;
                        }
                        MountiplexUtil.LOGGER.severe("Next [" + e.cost + "]: " + e.converter);
                    }
                }
            }
            
            public final void debugPrint(final Node node, final StringBuilder sb, final int n) {
                final StringBuilder s = new StringBuilder();
                boolean b = false;
                for (Node previous = node; previous != null; previous = previous.previous) {
                    if (previous == this) {
                        b = true;
                        break;
                    }
                }
                final Iterator<Node> iterator = this.children.iterator();
                while (iterator.hasNext()) {
                    iterator.next().debugPrint(node, s, n + 1);
                }
                if (b) {
                    for (int i = 1; i < n; ++i) {
                        sb.append("  ");
                    }
                    sb.append(">>");
                }
                else {
                    for (int j = 0; j < n; ++j) {
                        sb.append("  ");
                    }
                }
                sb.append(this.converter.input.toString());
                if (b) {
                    sb.append("<<");
                }
                sb.append('\n');
                sb.append((CharSequence)s);
            }
        }
    }
    
    private static final class OutputConverterList
    {
        private static Map<TypeDeclaration, OutputConverterList> mapping;
        private final TypeDeclaration output;
        private final HashMap<TypeDeclaration, Converter<?, ?>> single;
        private final LinkedHashMap<TypeDeclaration, Converter<?, ?>> converters;
        private final ArrayList<OutputConverterList> parents;
        private final HashSet<OutputConverterList> children;
        private boolean regen;
        
        public OutputConverterList(final TypeDeclaration output) {
            this.single = new HashMap<TypeDeclaration, Converter<?, ?>>();
            this.converters = new LinkedHashMap<TypeDeclaration, Converter<?, ?>>();
            this.parents = new ArrayList<OutputConverterList>();
            this.children = new HashSet<OutputConverterList>();
            this.output = output;
            this.reset();
        }
        
        public final void addConverter(final Converter<?, ?> value) {
            this.single.put(value.input, value);
            this.reset();
        }
        
        public final void makeParent(final OutputConverterList e) {
            if (this.children.add(e)) {
                e.parents.add(this);
                this.reset();
            }
        }
        
        public final void reset() {
            this.regen = true;
            this.converters.clear();
            final Iterator<OutputConverterList> iterator = this.children.iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
        }
        
        public final Collection<Converter<?, ?>> getConverters() {
            this.genConverters();
            return this.converters.values();
        }
        
        private final void genConverters() {
            if (this.regen) {
                this.regen = false;
                this.converters.putAll((Map<?, ?>)this.single);
                final ArrayList list = new ArrayList();
                final Iterator iterator = Conversion.providers.iterator();
                while (iterator.hasNext()) {
                    ((ConverterProvider)iterator.next()).getConverters(this.output, list);
                    if (!list.isEmpty()) {
                        for (final Converter obj : list) {
                            if (this.converters.putIfAbsent(obj.input, obj) == null) {
                                try {
                                    verifyConverter(obj);
                                }
                                catch (final InvalidConverterException ex) {
                                    this.converters.remove(obj.input);
                                    MountiplexUtil.LOGGER.warning(ex.getMessage() + ": " + obj);
                                }
                                catch (final Throwable thrown) {
                                    this.converters.remove(obj.input);
                                    MountiplexUtil.LOGGER.log(Level.SEVERE, "An error occurred registering " + obj, thrown);
                                }
                            }
                        }
                        list.clear();
                    }
                }
                for (final OutputConverterList list2 : this.parents) {
                    list2.genConverters();
                    for (final Converter value : list2.converters.values()) {
                        this.converters.putIfAbsent(value.input, value);
                    }
                }
            }
        }
        
        public static OutputConverterList get(final TypeDeclaration obj) {
            if (!obj.isResolved()) {
                throw new IllegalArgumentException("Requested type is not resolved: " + obj);
            }
            OutputConverterList list = OutputConverterList.mapping.get(obj);
            if (list == null) {
                list = new OutputConverterList(obj);
                OutputConverterList.mapping.put(obj, list);
                final Class<?> superclass = obj.type.getSuperclass();
                if (superclass != null) {
                    list.makeParent(get(TypeDeclaration.fromClass(superclass)));
                }
                final Class<?>[] interfaces = obj.type.getInterfaces();
                for (int length = interfaces.length, i = 0; i < length; ++i) {
                    list.makeParent(get(TypeDeclaration.fromClass(interfaces[i])));
                }
            }
            return list;
        }
        
        public static void resetAll() {
            final Iterator<OutputConverterList> iterator = OutputConverterList.mapping.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
        }
        
        static {
            OutputConverterList.mapping = new HashMap<TypeDeclaration, OutputConverterList>();
        }
    }
    
    private static final class TypeTuple
    {
        public final TypeDeclaration t1;
        public final TypeDeclaration t2;
        private final int hashcode;
        
        public TypeTuple(final Converter<?, ?> converter) {
            this(converter.input, converter.output);
        }
        
        public TypeTuple(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            this.t1 = typeDeclaration.getBoxedType();
            this.t2 = typeDeclaration2.getBoxedType();
            this.hashcode = 961 + 31 * this.t1.hashCode() + this.t2.hashCode();
        }
        
        public final TypeTuple reverse() {
            return new TypeTuple(this.t2, this.t1);
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof TypeTuple) {
                final TypeTuple typeTuple = (TypeTuple)o;
                return typeTuple.t1.equals(this.t1) && typeTuple.t2.equals(this.t2);
            }
            return false;
        }
        
        @Override
        public final int hashCode() {
            return this.hashcode;
        }
    }
    
    private static final class DeferLock extends ReentrantLock
    {
        private static final long serialVersionUID = 3302331820484906636L;
        private final ArrayList<Runnable> pending;
        private volatile boolean hasPending;
        
        private DeferLock() {
            this.pending = new ArrayList<Runnable>();
            this.hasPending = false;
        }
        
        @Override
        public void lock() {
            super.lock();
            this.processPending();
        }
        
        @Override
        public void unlock() {
            try {
                this.processPending();
            }
            finally {
                super.unlock();
            }
        }
        
        public void schedule(final Runnable e) {
            if (super.tryLock()) {
                try {
                    this.processPending();
                    e.run();
                    this.processPending();
                }
                finally {
                    super.unlock();
                }
            }
            else {
                synchronized (this.pending) {
                    this.pending.add(e);
                    this.hasPending = true;
                }
                if (super.tryLock()) {
                    try {
                        this.processPending();
                    }
                    finally {
                        super.unlock();
                    }
                }
            }
        }
        
        public void processPending() {
            while (this.hasPending) {
                final ArrayList list;
                synchronized (this.pending) {
                    if (!this.hasPending) {
                        return;
                    }
                    list = new ArrayList(this.pending);
                    this.pending.clear();
                    this.hasPending = false;
                }
                list.forEach(Runnable::run);
            }
        }
    }
}
