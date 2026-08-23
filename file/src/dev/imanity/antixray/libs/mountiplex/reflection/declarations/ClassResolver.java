// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.HashMap;
import java.util.function.Consumer;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedCodeInvoker;
import java.util.Collections;
import dev.imanity.antixray.libs.mountiplex.logic.TextValueSequence;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassDeclarationResolver;
import java.util.Map;
import java.util.stream.Stream;
import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class ClassResolver
{
    private static final List<String> default_imports;
    private static final Runnable[] default_bootstrap;
    public static final ClassResolver DEFAULT;
    private final ArrayList<String> imports;
    private final ArrayList<String> manualImports;
    private final List<Requirement> requirements;
    private final Remapping.Lookup remappings;
    private VariablesMap variables;
    private String classDeclarationResolverName;
    private String packagePath;
    private String declaredClassName;
    private Class<?> declaredClass;
    private ClassLoader classLoader;
    private boolean logErrors;
    private boolean isGenerating;
    private Runnable[] bootstrap;
    
    private ClassResolver(final ClassResolver classResolver) {
        this.classDeclarationResolverName = classResolver.classDeclarationResolverName;
        this.variables = classResolver.variables;
        this.imports = new ArrayList<String>(classResolver.imports);
        this.manualImports = new ArrayList<String>(classResolver.manualImports);
        this.requirements = new ArrayList<Requirement>(classResolver.requirements);
        this.remappings = classResolver.remappings.clone();
        this.packagePath = classResolver.packagePath;
        this.declaredClassName = classResolver.declaredClassName;
        this.declaredClass = classResolver.declaredClass;
        this.classLoader = classResolver.classLoader;
        this.logErrors = classResolver.logErrors;
        this.isGenerating = classResolver.isGenerating;
        this.bootstrap = classResolver.bootstrap;
    }
    
    public ClassResolver() {
        this.classDeclarationResolverName = "null";
        this.variables = VariablesMap.EMPTY;
        this.imports = new ArrayList<String>();
        this.manualImports = new ArrayList<String>();
        this.requirements = new ArrayList<Requirement>();
        this.remappings = Remapping.createLookup();
        this.packagePath = "";
        this.declaredClassName = null;
        this.declaredClass = null;
        this.classLoader = ClassResolver.class.getClassLoader();
        this.logErrors = true;
        this.isGenerating = false;
        this.bootstrap = ClassResolver.default_bootstrap;
        this.regenImports();
    }
    
    public ClassResolver(final String package1) {
        this.classDeclarationResolverName = "null";
        this.variables = VariablesMap.EMPTY;
        this.imports = new ArrayList<String>();
        this.manualImports = new ArrayList<String>();
        this.requirements = new ArrayList<Requirement>();
        this.remappings = Remapping.createLookup();
        this.packagePath = "";
        this.logErrors = true;
        this.isGenerating = false;
        this.bootstrap = ClassResolver.default_bootstrap;
        this.declaredClassName = null;
        this.declaredClass = null;
        this.classLoader = ClassResolver.class.getClassLoader();
        this.setPackage(package1);
    }
    
    public boolean getLogErrors() {
        return this.logErrors;
    }
    
    public void setLogErrors(final boolean logErrors) {
        this.logErrors = logErrors;
    }
    
    public boolean isGenerating() {
        return this.isGenerating;
    }
    
    public void setGenerating(final boolean isGenerating) {
        this.isGenerating = isGenerating;
    }
    
    public ClassResolver clone() {
        return new ClassResolver(this);
    }
    
    public ClassResolver immutable() {
        return new ImmutableClassResolver(this);
    }
    
    public void setClassLoader(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public void setDeclaredClassName(final String s) {
        final ResolveResult resolve = this.resolve(s);
        this.setDeclaredClass(resolve.classType, resolve.classPath);
    }
    
    public void setDeclaredClass(final Class<?> clazz) {
        if (clazz != null) {
            this.setDeclaredClass(clazz, MPLType.getName(clazz));
        }
    }
    
    public void setDeclaredClass(final Class<?> declaredClass, final String declaredClassName) {
        this.declaredClass = declaredClass;
        this.declaredClassName = declaredClassName;
        final String packagePathFromClassPath = MountiplexUtil.getPackagePathFromClassPath(declaredClassName);
        if (!packagePathFromClassPath.isEmpty()) {
            this.packagePath = packagePathFromClassPath;
        }
        this.regenImports();
    }
    
    public Class<?> getDeclaredClass() {
        return this.declaredClass;
    }
    
    public String getDeclaredClassName() {
        return this.declaredClassName;
    }
    
    public void setPackage(final String s) {
        this.setPackage(s, true);
    }
    
    public void setPackage(final String packagePath, final boolean b) {
        this.packagePath = packagePath;
        if (b) {
            this.declaredClass = null;
            this.declaredClassName = null;
            this.manualImports.clear();
        }
        this.regenImports();
    }
    
    public String getPackage() {
        return this.packagePath;
    }
    
    public boolean hasPackage() {
        return !this.packagePath.isEmpty();
    }
    
    public void setClassDeclarationResolverName(final String classDeclarationResolverName) {
        this.classDeclarationResolverName = classDeclarationResolverName;
    }
    
    public String getClassDeclarationResolverName() {
        return this.classDeclarationResolverName;
    }
    
    public List<Runnable> getBootstrap() {
        return Arrays.asList(this.bootstrap);
    }
    
    public void runBootstrap() {
        for (int i = 0; i < this.bootstrap.length; ++i) {
            try {
                this.bootstrap[i].run();
            }
            catch (final Throwable t) {
                throw new BootstrapException(this.bootstrap[i], t);
            }
        }
    }
    
    public void addBootstrap(final Runnable runnable) {
        (this.bootstrap = Arrays.copyOf(this.bootstrap, this.bootstrap.length + 1))[this.bootstrap.length - 1] = new BootstrapRunnable(runnable);
    }
    
    public void addBootstrap(final String s) {
        (this.bootstrap = Arrays.copyOf(this.bootstrap, this.bootstrap.length + 1))[this.bootstrap.length - 1] = new BootstrapCode(this, s);
    }
    
    public Stream<String> getAllImports() {
        return Stream.concat(this.manualImports.stream(), ClassResolver.default_imports.stream());
    }
    
    public void addImport(final String e) {
        this.manualImports.add(e);
        this.regenImports();
    }
    
    public void addImports(final Collection<String> c) {
        this.manualImports.addAll(c);
        this.regenImports();
    }
    
    public String saveDeclaration() {
        return this.variables.getDeclaration();
    }
    
    public void setVariable(final String s, final String s2) {
        this.variables = this.variables.modify(map -> map.put(s3, s4));
    }
    
    public String getVariable(final String s, final String s2) {
        final String value = this.variables.get(s);
        return (value != null) ? value : s2;
    }
    
    public void includeSourceDetails(final ClassResolver classResolver) {
        this.remappings.assign(classResolver.remappings);
        this.requirements.clear();
        this.requirements.addAll(classResolver.requirements);
        this.variables = classResolver.variables;
        this.bootstrap = classResolver.bootstrap;
    }
    
    public void setAllVariables(final Map<String, String> map) {
        this.variables = this.variables.modify(map3 -> map3.putAll(map2));
    }
    
    public void setAllVariables(final ClassDeclarationResolver classDeclarationResolver) {
        if (classDeclarationResolver == null) {
            throw new IllegalArgumentException("Resolver cannot be null");
        }
        if (this.declaredClassName == null || this.declaredClassName.isEmpty() || this.declaredClass == null) {
            throw new IllegalStateException("Class Resolver has no declared Class");
        }
        this.variables = this.variables.modify(map -> classDeclarationResolver2.resolveClassVariables(this.declaredClassName, this.declaredClass, map));
        final Iterator<Remapping> iterator = classDeclarationResolver.getRootClassResolver(this.declaredClassName, this.declaredClass).getRemappings().getAllStoredRemappings().iterator();
        while (iterator.hasNext()) {
            this.remappings.addRemapping(iterator.next());
        }
    }
    
    public Map<String, String> getAllVariables() {
        return this.variables.getAll();
    }
    
    public boolean evaluateExpression(final String s) {
        boolean b = false;
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < s.length() - 1; ++i) {
            final char char1 = s.charAt(i);
            int n3;
            if (char1 == '&' && s.charAt(i + 1) == '&') {
                n3 = 1;
            }
            else {
                if (char1 != '|' || s.charAt(i + 1) != '|') {
                    continue;
                }
                n3 = 0;
            }
            if (n2 != 0) {
                b &= this.evaluateExpression_part(s.substring(n, i));
            }
            else {
                b |= this.evaluateExpression_part(s.substring(n, i));
            }
            ++i;
            n2 = n3;
            n = i + 1;
        }
        if (n2 != 0) {
            return b && this.evaluateExpression_part(s.substring(n));
        }
        return b || this.evaluateExpression_part(s.substring(n));
    }
    
    private boolean evaluateExpression_part(String s) {
        s = s.trim();
        String s2 = null;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == ' ' || (!Character.isLetter(char1) && char1 != '_' && char1 != '!')) {
                s2 = s.substring(0, i);
                s = s.substring(i).trim();
                break;
            }
        }
        if (s2 == null) {
            s2 = s;
            s = "";
        }
        boolean b = false;
        while (s2.startsWith("!")) {
            b = !b;
            s2 = s2.substring(1);
        }
        if (s2.equals("classexists") || s2.equals("methodexists") || s2.equals("fieldexists") || s2.equals("exists")) {
            int n = s.indexOf(32);
            String s3;
            if (n == -1) {
                s3 = s;
                n = s.length();
            }
            else {
                s3 = s.substring(0, n);
                ++n;
            }
            while (s3.endsWith(";")) {
                s3 = s3.substring(0, s3.length() - 1);
            }
            final Class<?> loadClass = Resolver.loadClass(s3, false, this.classLoader);
            if (loadClass == null) {
                return b;
            }
            final String trim = s.substring(n).trim();
            if (trim.isEmpty()) {
                return !b;
            }
            final ClassResolver classResolver = new ClassResolver(this);
            classResolver.setDeclaredClass(loadClass, s3);
            classResolver.setLogErrors(false);
            final Declaration declaration = Declaration.parseDeclaration(classResolver, trim);
            if (declaration == null && this.getLogErrors()) {
                MountiplexUtil.LOGGER.warning("Failed to parse declaration to check existance: " + trim);
                return b;
            }
            if (!declaration.isResolved()) {
                return b;
            }
            return declaration.discover() != null != b;
        }
        else if (s2.equals("assignable")) {
            final int index = s.indexOf(32);
            String substring;
            String s4;
            if (index == -1) {
                substring = s;
                s4 = Object.class.getName();
            }
            else {
                substring = s.substring(0, index);
                s4 = s.substring(index + 1).trim();
            }
            final String resolvePath = this.resolvePath(substring);
            final String resolvePath2 = this.resolvePath(s4);
            final Class<?> loadClass2 = Resolver.loadClass(resolvePath, false, this.classLoader);
            final Class<?> loadClass3 = Resolver.loadClass(resolvePath2, false, this.classLoader);
            if (loadClass2 == null || loadClass3 == null) {
                return !b;
            }
            return loadClass2.isAssignableFrom(loadClass3) != b;
        }
        else {
            final String value = this.variables.get(s2);
            if (value == null) {
                if (s2.equals("1") || s2.equalsIgnoreCase("true")) {
                    return !b;
                }
                return b;
            }
            else {
                final int index2 = s.indexOf(32);
                if (index2 == -1) {
                    return !b;
                }
                return TextValueSequence.evaluateText(value, s.substring(0, index2), s.substring(index2 + 1).trim()) != b;
            }
        }
    }
    
    public String resolvePath(final String s) {
        return this.resolve(s).classPath;
    }
    
    public Class<?> resolveClass(final String s) {
        return this.resolve(s).classType;
    }
    
    public ResolveResult resolve(String substring) {
        if (substring.length() == 1) {
            return new ResolveResult(substring, Object.class);
        }
        if (substring.endsWith("[]")) {
            int n = 0;
            do {
                ++n;
                substring = substring.substring(0, substring.length() - 2);
            } while (substring.endsWith("[]"));
            final ResolveResult resolve = this.resolve(substring);
            final StringBuilder sb = new StringBuilder(resolve.classPath);
            for (int i = 0; i < n; ++i) {
                sb.append("[]");
            }
            if (resolve.classType != null) {
                return new ResolveResult(sb.toString(), MountiplexUtil.getArrayType(resolve.classType, n));
            }
            return new ResolveResult(sb.toString(), null);
        }
        else {
            final Class<?> loadClass = Resolver.loadClass(substring, false, this.classLoader);
            if (loadClass != null) {
                return new ResolveResult(substring, loadClass);
            }
            String s = null;
            final String string = "." + substring;
            for (final String s2 : this.imports) {
                String string2;
                if (s2.endsWith(".*") || s2.endsWith("$*")) {
                    string2 = s2.substring(0, s2.length() - 1) + substring;
                }
                else {
                    if (!s2.endsWith(string)) {
                        continue;
                    }
                    string2 = s2;
                    s = s2;
                }
                final Class<?> loadClass2 = Resolver.loadClass(string2, false, this.classLoader);
                if (loadClass2 != null) {
                    return new ResolveResult(string2, loadClass2);
                }
            }
            String string3;
            if (!this.packagePath.isEmpty() && (!Character.isLowerCase(substring.charAt(0)) || !substring.contains("."))) {
                string3 = this.packagePath + "." + substring;
                final Class<?> loadClass3 = Resolver.loadClass(string3, false, this.classLoader);
                if (loadClass3 != null) {
                    return new ResolveResult(string3, loadClass3);
                }
            }
            else {
                string3 = substring;
            }
            if (s != null) {
                string3 = s;
            }
            return new ResolveResult(string3, null);
        }
    }
    
    public String resolvePath(final Class<?> clazz) {
        if (clazz.isArray()) {
            return this.resolvePath(clazz.getComponentType()) + "[]";
        }
        return MPLType.getName(clazz).replace('$', '.');
    }
    
    public String resolveName(final Class<?> clazz) {
        if (clazz == null) {
            return "NULL";
        }
        if (clazz.isArray()) {
            return this.resolveName(clazz.getComponentType()) + "[]";
        }
        final String replace = MPLType.getName(clazz).replace('$', '.');
        for (final String s : this.imports) {
            if (equalsIgnoreDollarSign(s, replace)) {
                return clazz.getSimpleName();
            }
            if (!s.endsWith(".*") && !s.endsWith("$*")) {
                continue;
            }
            final int beginIndex = s.length() - 1;
            if (replace.length() >= beginIndex && equalsIgnoreDollarSign(replace, s, beginIndex)) {
                return replace.substring(beginIndex);
            }
        }
        return replace;
    }
    
    private static boolean equalsIgnoreDollarSign(final String s, final String s2) {
        final int length = s.length();
        return length == s2.length() && equalsIgnoreDollarSign(s, s2, length);
    }
    
    private static boolean equalsIgnoreDollarSign(final String s, final String s2, final int n) {
        for (int i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            final char char2 = s2.charAt(i);
            if (char1 != char2 && ((char1 != '.' && char1 != '$') || (char2 != '.' && char2 != '$'))) {
                return false;
            }
        }
        return true;
    }
    
    public void storeRequirement(final Requirement requirement) {
        this.requirements.add(0, requirement);
    }
    
    public List<Requirement> getRequirements() {
        return this.requirements;
    }
    
    public void storeRemapping(final Remapping remapping) {
        this.remappings.addRemapping(remapping);
    }
    
    public Remapping.Lookup getRemappings() {
        return this.remappings;
    }
    
    private void regenImports() {
        this.imports.clear();
        this.imports.addAll(this.manualImports);
        Collections.reverse(this.imports);
        if (this.declaredClassName != null) {
            this.imports.add(this.declaredClassName + "$*");
        }
        if (this.packagePath != null && !this.packagePath.isEmpty()) {
            this.imports.add(this.packagePath + ".*");
        }
        this.imports.addAll(ClassResolver.default_imports);
    }
    
    static {
        default_imports = Arrays.asList("java.lang.*", "java.util.*");
        default_bootstrap = new Runnable[0];
        DEFAULT = new ClassResolver().immutable();
    }
    
    public static class ResolveResult
    {
        public final String classPath;
        public final Class<?> classType;
        
        public ResolveResult(final String classPath, final Class<?> classType) {
            this.classPath = classPath;
            this.classType = classType;
        }
        
        public boolean success() {
            return this.classType != null;
        }
    }
    
    private static class ImmutableClassResolver extends ClassResolver
    {
        public ImmutableClassResolver(final ClassResolver classResolver) {
            super(classResolver, null);
        }
        
        @Override
        public void setDeclaredClass(final Class<?> clazz) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
        
        @Override
        public void setPackage(final String s) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
        
        @Override
        public void addImport(final String s) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
        
        @Override
        public void setVariable(final String s, final String s2) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
        
        @Override
        public void storeRequirement(final Requirement requirement) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
        
        @Override
        public void storeRemapping(final Remapping remapping) {
            throw new UnsupportedOperationException("Class Resolver is immutable");
        }
    }
    
    private static class BootstrapRunnable implements Runnable
    {
        private final Runnable runnable;
        private boolean needsExecuting;
        
        public BootstrapRunnable(final Runnable runnable) {
            this.needsExecuting = true;
            this.runnable = runnable;
        }
        
        @Override
        public synchronized void run() {
            if (this.needsExecuting) {
                this.runnable.run();
                this.needsExecuting = false;
            }
        }
        
        @Override
        public String toString() {
            return "RUNNABLE{" + this.runnable + "}";
        }
    }
    
    private static class BootstrapCode implements Runnable
    {
        private final ClassResolver resolver;
        private final String code;
        private boolean needsExecuting;
        
        public BootstrapCode(final ClassResolver resolver, final String code) {
            this.needsExecuting = true;
            this.resolver = resolver;
            this.code = code;
        }
        
        @Override
        public void run() {
            if (this.needsExecuting) {
                GeneratedCodeInvoker.create(new MethodDeclaration(this.resolver, "public static void run() {\n" + this.code + "\n}")).invoke(null);
                this.needsExecuting = false;
            }
        }
        
        @Override
        public String toString() {
            return "CODE" + this.code;
        }
    }
    
    private static class BootstrapException extends RuntimeException
    {
        private static final long serialVersionUID = -1827332615527781142L;
        
        public BootstrapException(final Runnable obj, final Throwable cause) {
            super("Failed to bootstrap " + obj, cause);
        }
    }
    
    private static class VariablesMap
    {
        private final Map<String, String> _map;
        private String _decl;
        public static final VariablesMap EMPTY;
        
        private VariablesMap() {
            this._map = Collections.emptyMap();
            this._decl = "";
        }
        
        public VariablesMap(final Map<String, String> map) {
            this._map = map;
            this._decl = null;
        }
        
        public Map<String, String> getAll() {
            return Collections.unmodifiableMap((Map<? extends String, ? extends String>)this._map);
        }
        
        public String get(final String s) {
            return this._map.get(s);
        }
        
        public VariablesMap modify(final Consumer<Map<String, String>> consumer) {
            final HashMap hashMap = new HashMap((Map<? extends K, ? extends V>)this._map);
            consumer.accept(hashMap);
            return new VariablesMap(hashMap);
        }
        
        public String getDeclaration() {
            if (this._decl == null) {
                final StringBuilder sb = new StringBuilder();
                for (final Map.Entry entry : this._map.entrySet()) {
                    sb.append("#set ").append((String)entry.getKey()).append(' ').append((String)entry.getValue()).append('\n');
                }
                this._decl = sb.toString();
            }
            return this._decl;
        }
        
        static {
            EMPTY = new VariablesMap();
        }
    }
}
