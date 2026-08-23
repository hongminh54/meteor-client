// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import java.util.WeakHashMap;
import dev.imanity.antixray.libs.javassist.compiler.ast.Keyword;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import dev.imanity.antixray.libs.javassist.compiler.ast.Declarator;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.Modifier;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.lang.ref.Reference;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.ClassPool;

public class MemberResolver implements TokenId
{
    private ClassPool classPool;
    private static final int YES = 0;
    private static final int NO = -1;
    private static final String INVALID = "<invalid>";
    private static Map<ClassPool, Reference<Map<String, String>>> invalidNamesMap;
    private Map<String, String> invalidNames;
    
    public MemberResolver(final ClassPool classPool) {
        this.invalidNames = null;
        this.classPool = classPool;
    }
    
    public ClassPool getClassPool() {
        return this.classPool;
    }
    
    private static void fatal() {
        throw new CompileError("fatal");
    }
    
    public Method lookupMethod(final CtClass ctClass, final CtClass ctClass2, final MethodInfo methodInfo, final String anObject, final int[] array, final int[] array2, final String[] array3) {
        Method method = null;
        if (methodInfo != null && ctClass == ctClass2 && methodInfo.getName().equals(anObject)) {
            final int compareSignature = this.compareSignature(methodInfo.getDescriptor(), array, array2, array3);
            if (compareSignature != -1) {
                final Method method2 = new Method(ctClass, methodInfo, compareSignature);
                if (compareSignature == 0) {
                    return method2;
                }
                method = method2;
            }
        }
        final Method lookupMethod = this.lookupMethod(ctClass, anObject, array, array2, array3, method != null);
        if (lookupMethod != null) {
            return lookupMethod;
        }
        return method;
    }
    
    private Method lookupMethod(final CtClass ctClass, final String anObject, final int[] array, final int[] array2, final String[] array3, final boolean b) {
        Method method = null;
        final ClassFile classFile2 = ctClass.getClassFile2();
        if (classFile2 != null) {
            for (final MethodInfo methodInfo : classFile2.getMethods()) {
                if (methodInfo.getName().equals(anObject) && (methodInfo.getAccessFlags() & 0x40) == 0x0) {
                    final int compareSignature = this.compareSignature(methodInfo.getDescriptor(), array, array2, array3);
                    if (compareSignature == -1) {
                        continue;
                    }
                    final Method method2 = new Method(ctClass, methodInfo, compareSignature);
                    if (compareSignature == 0) {
                        return method2;
                    }
                    if (method != null && method.notmatch <= compareSignature) {
                        continue;
                    }
                    method = method2;
                }
            }
        }
        if (b) {
            method = null;
        }
        final boolean interface1 = Modifier.isInterface(ctClass.getModifiers());
        try {
            if (!interface1) {
                final CtClass superclass = ctClass.getSuperclass();
                if (superclass != null) {
                    final Method lookupMethod = this.lookupMethod(superclass, anObject, array, array2, array3, b);
                    if (lookupMethod != null && (method == null || method.notmatch > lookupMethod.notmatch)) {
                        method = lookupMethod;
                    }
                }
            }
        }
        catch (final NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final Method lookupMethod2 = this.lookupMethod(interfaces[i], anObject, array, array2, array3, b);
                if (lookupMethod2 != null && (method == null || method.notmatch > lookupMethod2.notmatch)) {
                    method = lookupMethod2;
                }
            }
            if (interface1) {
                final CtClass superclass2 = ctClass.getSuperclass();
                if (superclass2 != null) {
                    final Method lookupMethod3 = this.lookupMethod(superclass2, anObject, array, array2, array3, b);
                    if (lookupMethod3 != null && (method == null || method.notmatch > lookupMethod3.notmatch)) {
                        method = lookupMethod3;
                    }
                }
            }
        }
        catch (final NotFoundException ex2) {}
        return method;
    }
    
    private int compareSignature(final String s, final int[] array, final int[] array2, final String[] array3) {
        int n = 0;
        int i = 1;
        final int length = array.length;
        if (length != Descriptor.numOfParameters(s)) {
            return -1;
        }
        final int length2 = s.length();
        int n2 = 0;
        while (i < length2) {
            char c = s.charAt(i++);
            if (c == ')') {
                return (n2 == length) ? n : -1;
            }
            if (n2 >= length) {
                return -1;
            }
            int n3 = 0;
            while (c == '[') {
                ++n3;
                c = s.charAt(i++);
            }
            if (array[n2] == 412) {
                if (n3 == 0 && c != 'L') {
                    return -1;
                }
                if (c == 'L') {
                    i = s.indexOf(59, i) + 1;
                }
            }
            else if (array2[n2] != n3) {
                if (n3 != 0 || c != 'L' || !s.startsWith("java/lang/Object;", i)) {
                    return -1;
                }
                i = s.indexOf(59, i) + 1;
                ++n;
                if (i <= 0) {
                    return -1;
                }
            }
            else if (c == 'L') {
                final int index = s.indexOf(59, i);
                if (index < 0 || array[n2] != 307) {
                    return -1;
                }
                final String substring = s.substring(i, index);
                if (!substring.equals(array3[n2])) {
                    final CtClass lookupClassByJvmName = this.lookupClassByJvmName(array3[n2]);
                    try {
                        if (!lookupClassByJvmName.subtypeOf(this.lookupClassByJvmName(substring))) {
                            return -1;
                        }
                        ++n;
                    }
                    catch (final NotFoundException ex) {
                        ++n;
                    }
                }
                i = index + 1;
            }
            else {
                final int descToType = descToType(c);
                final int n4 = array[n2];
                if (descToType != n4) {
                    if (descToType != 324 || (n4 != 334 && n4 != 303 && n4 != 306)) {
                        return -1;
                    }
                    ++n;
                }
            }
            ++n2;
        }
        return -1;
    }
    
    public CtField lookupFieldByJvmName2(String javaToJvmName, final Symbol symbol, final ASTree asTree) {
        final String value = symbol.get();
        CtClass lookupClass;
        try {
            lookupClass = this.lookupClass(jvmToJavaName(javaToJvmName), true);
        }
        catch (final CompileError compileError) {
            throw new NoFieldException(javaToJvmName + "/" + value, asTree);
        }
        try {
            return lookupClass.getField(value);
        }
        catch (final NotFoundException ex) {
            javaToJvmName = javaToJvmName(lookupClass.getName());
            throw new NoFieldException(javaToJvmName + "$" + value, asTree);
        }
    }
    
    public CtField lookupFieldByJvmName(final String s, final Symbol symbol) {
        return this.lookupField(jvmToJavaName(s), symbol);
    }
    
    public CtField lookupField(final String s, final Symbol symbol) {
        final CtClass lookupClass = this.lookupClass(s, false);
        try {
            return lookupClass.getField(symbol.get());
        }
        catch (final NotFoundException ex) {
            throw new CompileError("no such field: " + symbol.get());
        }
    }
    
    public CtClass lookupClassByName(final ASTList list) {
        return this.lookupClass(Declarator.astToClassName(list, '.'), false);
    }
    
    public CtClass lookupClassByJvmName(final String s) {
        return this.lookupClass(jvmToJavaName(s), false);
    }
    
    public CtClass lookupClass(final Declarator declarator) {
        return this.lookupClass(declarator.getType(), declarator.getArrayDim(), declarator.getClassName());
    }
    
    public CtClass lookupClass(final int n, int n2, final String s) {
        String str;
        if (n == 307) {
            final CtClass lookupClassByJvmName = this.lookupClassByJvmName(s);
            if (n2 <= 0) {
                return lookupClassByJvmName;
            }
            str = lookupClassByJvmName.getName();
        }
        else {
            str = getTypeName(n);
        }
        while (n2-- > 0) {
            str += "[]";
        }
        return this.lookupClass(str, false);
    }
    
    static String getTypeName(final int n) {
        String s = "";
        switch (n) {
            case 301: {
                s = "boolean";
                break;
            }
            case 306: {
                s = "char";
                break;
            }
            case 303: {
                s = "byte";
                break;
            }
            case 334: {
                s = "short";
                break;
            }
            case 324: {
                s = "int";
                break;
            }
            case 326: {
                s = "long";
                break;
            }
            case 317: {
                s = "float";
                break;
            }
            case 312: {
                s = "double";
                break;
            }
            case 344: {
                s = "void";
                break;
            }
            default: {
                fatal();
                break;
            }
        }
        return s;
    }
    
    public CtClass lookupClass(final String str, final boolean b) {
        final Map<String, String> invalidNames = this.getInvalidNames();
        final String s = invalidNames.get(str);
        if (s == "<invalid>") {
            throw new CompileError("no such class: " + str);
        }
        if (s != null) {
            try {
                return this.classPool.get(s);
            }
            catch (final NotFoundException ex) {}
        }
        CtClass ctClass;
        try {
            ctClass = this.lookupClass0(str, b);
        }
        catch (final NotFoundException ex2) {
            ctClass = this.searchImports(str);
        }
        invalidNames.put(str, ctClass.getName());
        return ctClass;
    }
    
    public static int getInvalidMapSize() {
        return MemberResolver.invalidNamesMap.size();
    }
    
    private Map<String, String> getInvalidNames() {
        Map<?, ?> invalidNames = this.invalidNames;
        if (invalidNames == null) {
            synchronized (MemberResolver.class) {
                final Reference reference = MemberResolver.invalidNamesMap.get(this.classPool);
                if (reference != null) {
                    invalidNames = (Map<?, ?>)reference.get();
                }
                if (invalidNames == null) {
                    invalidNames = new Hashtable<Object, Object>();
                    MemberResolver.invalidNamesMap.put(this.classPool, new WeakReference<Map<String, String>>((Map<String, String>)invalidNames));
                }
            }
            this.invalidNames = (Map<String, String>)invalidNames;
        }
        return (Map<String, String>)invalidNames;
    }
    
    private CtClass searchImports(final String str) {
        if (str.indexOf(46) < 0) {
            final Iterator<String> importedPackages = this.classPool.getImportedPackages();
            while (importedPackages.hasNext()) {
                final String s = importedPackages.next();
                final String string = s.replaceAll("\\.$", "") + "." + str;
                try {
                    return this.classPool.get(string);
                }
                catch (final NotFoundException ex) {
                    try {
                        if (s.endsWith("." + str)) {
                            return this.classPool.get(s);
                        }
                        continue;
                    }
                    catch (final NotFoundException ex2) {}
                    continue;
                }
                break;
            }
        }
        this.getInvalidNames().put(str, "<invalid>");
        throw new CompileError("no such class: " + str);
    }
    
    private CtClass lookupClass0(String string, final boolean b) {
        CtClass value = null;
        do {
            try {
                value = this.classPool.get(string);
            }
            catch (final NotFoundException ex) {
                final int lastIndex = string.lastIndexOf(46);
                if (b || lastIndex < 0) {
                    throw ex;
                }
                final StringBuilder sb = new StringBuilder(string);
                sb.setCharAt();
                string = sb.toString();
            }
        } while (value == null);
        return value;
    }
    
    public String resolveClassName(final ASTList list) {
        if (list == null) {
            return null;
        }
        return javaToJvmName(this.lookupClassByName(list).getName());
    }
    
    public String resolveJvmClassName(final String s) {
        if (s == null) {
            return null;
        }
        return javaToJvmName(this.lookupClassByJvmName(s).getName());
    }
    
    public static CtClass getSuperclass(final CtClass ctClass) {
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                return superclass;
            }
        }
        catch (final NotFoundException ex) {}
        throw new CompileError("cannot find the super class of " + ctClass.getName());
    }
    
    public static CtClass getSuperInterface(final CtClass ctClass, final String s) {
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i].getName().equals(s)) {
                    return interfaces[i];
                }
            }
        }
        catch (final NotFoundException ex) {}
        throw new CompileError("cannot find the super interface " + s + " of " + ctClass.getName());
    }
    
    public static String javaToJvmName(final String s) {
        return s.replace('.', '/');
    }
    
    public static String jvmToJavaName(final String s) {
        return s.replace('/', '.');
    }
    
    public static int descToType(final char c) {
        switch (c) {
            case 'Z': {
                return 301;
            }
            case 'C': {
                return 306;
            }
            case 'B': {
                return 303;
            }
            case 'S': {
                return 334;
            }
            case 'I': {
                return 324;
            }
            case 'J': {
                return 326;
            }
            case 'F': {
                return 317;
            }
            case 'D': {
                return 312;
            }
            case 'V': {
                return 344;
            }
            case 'L':
            case '[': {
                return 307;
            }
            default: {
                fatal();
                return 344;
            }
        }
    }
    
    public static int getModifiers(ASTList tail) {
        int n = 0;
        while (tail != null) {
            final Keyword keyword = (Keyword)tail.head();
            tail = tail.tail();
            switch (keyword.get()) {
                case 335: {
                    n |= 0x8;
                    continue;
                }
                case 315: {
                    n |= 0x10;
                    continue;
                }
                case 338: {
                    n |= 0x20;
                    continue;
                }
                case 300: {
                    n |= 0x400;
                    continue;
                }
                case 332: {
                    n |= 0x1;
                    continue;
                }
                case 331: {
                    n |= 0x4;
                    continue;
                }
                case 330: {
                    n |= 0x2;
                    continue;
                }
                case 345: {
                    n |= 0x40;
                    continue;
                }
                case 342: {
                    n |= 0x80;
                    continue;
                }
                case 347: {
                    n |= 0x800;
                    continue;
                }
            }
        }
        return n;
    }
    
    static {
        MemberResolver.invalidNamesMap = new WeakHashMap<ClassPool, Reference<Map<String, String>>>();
    }
    
    public static class Method
    {
        public CtClass declaring;
        public MethodInfo info;
        public int notmatch;
        
        public Method(final CtClass declaring, final MethodInfo info, final int notmatch) {
            this.declaring = declaring;
            this.info = info;
            this.notmatch = notmatch;
        }
        
        public boolean isStatic() {
            return (this.info.getAccessFlags() & 0x8) != 0x0;
        }
    }
}
