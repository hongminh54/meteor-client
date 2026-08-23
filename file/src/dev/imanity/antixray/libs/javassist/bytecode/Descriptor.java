// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.CtPrimitiveType;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.CtClass;

public class Descriptor
{
    public static String toJvmName(final String s) {
        return s.replace('.', '/');
    }
    
    public static String toJavaName(final String s) {
        return s.replace('/', '.');
    }
    
    public static String toJvmName(final CtClass ctClass) {
        if (ctClass.isArray()) {
            return of(ctClass);
        }
        return toJvmName(ctClass.getName());
    }
    
    public static String toClassName(final String s) {
        int n = 0;
        int beginIndex;
        char c;
        for (beginIndex = 0, c = s.charAt(0); c == '['; c = s.charAt(++beginIndex)) {
            ++n;
        }
        String replace;
        if (c == 'L') {
            final int index = s.indexOf(59, beginIndex++);
            replace = s.substring(beginIndex, index).replace('/', '.');
            beginIndex = index;
        }
        else if (c == 'V') {
            replace = "void";
        }
        else if (c == 'I') {
            replace = "int";
        }
        else if (c == 'B') {
            replace = "byte";
        }
        else if (c == 'J') {
            replace = "long";
        }
        else if (c == 'D') {
            replace = "double";
        }
        else if (c == 'F') {
            replace = "float";
        }
        else if (c == 'C') {
            replace = "char";
        }
        else if (c == 'S') {
            replace = "short";
        }
        else {
            if (c != 'Z') {
                throw new RuntimeException("bad descriptor: " + s);
            }
            replace = "boolean";
        }
        if (beginIndex + 1 != s.length()) {
            throw new RuntimeException("multiple descriptors?: " + s);
        }
        if (n == 0) {
            return replace;
        }
        final StringBuilder sb = new StringBuilder(replace);
        do {
            sb.append("[]");
        } while (--n > 0);
        return sb.toString();
    }
    
    public static String of(final String s) {
        if (s.equals("void")) {
            return "V";
        }
        if (s.equals("int")) {
            return "I";
        }
        if (s.equals("byte")) {
            return "B";
        }
        if (s.equals("long")) {
            return "J";
        }
        if (s.equals("double")) {
            return "D";
        }
        if (s.equals("float")) {
            return "F";
        }
        if (s.equals("char")) {
            return "C";
        }
        if (s.equals("short")) {
            return "S";
        }
        if (s.equals("boolean")) {
            return "Z";
        }
        return "L" + toJvmName(s) + ";";
    }
    
    public static String rename(final String s, final String s2, final String str) {
        if (s.indexOf(s2) < 0) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        int n = 0;
        int fromIndex = 0;
        while (true) {
            final int index = s.indexOf(76, fromIndex);
            if (index < 0) {
                break;
            }
            if (s.startsWith(s2, index + 1) && s.charAt(index + s2.length() + 1) == ';') {
                sb.append(s.substring(n, index));
                sb.append('L');
                sb.append(str);
                sb.append(';');
                fromIndex = (n = index + s2.length() + 2);
            }
            else {
                fromIndex = s.indexOf(59, index) + 1;
                if (fromIndex < 1) {
                    break;
                }
                continue;
            }
        }
        if (n == 0) {
            return s;
        }
        final int length = s.length();
        if (n < length) {
            sb.append(s.substring(n, length));
        }
        return sb.toString();
    }
    
    public static String rename(final String s, final Map<String, String> map) {
        if (map == null) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        int n = 0;
        int fromIndex = 0;
        while (true) {
            final int index = s.indexOf(76, fromIndex);
            if (index < 0) {
                break;
            }
            final int index2 = s.indexOf(59, index);
            if (index2 < 0) {
                break;
            }
            fromIndex = index2 + 1;
            final String str = map.get(s.substring(index + 1, index2));
            if (str == null) {
                continue;
            }
            sb.append(s.substring(n, index));
            sb.append('L');
            sb.append(str);
            sb.append(';');
            n = fromIndex;
        }
        if (n == 0) {
            return s;
        }
        final int length = s.length();
        if (n < length) {
            sb.append(s.substring(n, length));
        }
        return sb.toString();
    }
    
    public static String of(final CtClass ctClass) {
        final StringBuilder sb = new StringBuilder();
        toDescriptor(sb, ctClass);
        return sb.toString();
    }
    
    private static void toDescriptor(final StringBuilder sb, final CtClass ctClass) {
        if (ctClass.isArray()) {
            sb.append('[');
            try {
                toDescriptor(sb, ctClass.getComponentType());
            }
            catch (final NotFoundException ex) {
                sb.append('L');
                final String name = ctClass.getName();
                sb.append(toJvmName(name.substring(0, name.length() - 2)));
                sb.append(';');
            }
        }
        else if (ctClass.isPrimitive()) {
            sb.append(((CtPrimitiveType)ctClass).getDescriptor());
        }
        else {
            sb.append('L');
            sb.append(ctClass.getName().replace('.', '/'));
            sb.append(';');
        }
    }
    
    public static String ofConstructor(final CtClass[] array) {
        return ofMethod(CtClass.voidType, array);
    }
    
    public static String ofMethod(final CtClass ctClass, final CtClass[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                toDescriptor(sb, array[i]);
            }
        }
        sb.append(')');
        if (ctClass != null) {
            toDescriptor(sb, ctClass);
        }
        return sb.toString();
    }
    
    public static String ofParameters(final CtClass[] array) {
        return ofMethod(null, array);
    }
    
    public static String appendParameter(final String s, final String s2) {
        final int index = s2.indexOf(41);
        if (index < 0) {
            return s2;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s2.substring(0, index));
        sb.append('L');
        sb.append(s.replace('.', '/'));
        sb.append(';');
        sb.append(s2.substring(index));
        return sb.toString();
    }
    
    public static String insertParameter(final String s, final String s2) {
        if (s2.charAt(0) != '(') {
            return s2;
        }
        return "(L" + s.replace('.', '/') + ';' + s2.substring(1);
    }
    
    public static String appendParameter(final CtClass ctClass, final String s) {
        final int index = s.indexOf(41);
        if (index < 0) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, index));
        toDescriptor(sb, ctClass);
        sb.append(s.substring(index));
        return sb.toString();
    }
    
    public static String insertParameter(final CtClass ctClass, final String s) {
        if (s.charAt(0) != '(') {
            return s;
        }
        return "(" + of(ctClass) + s.substring(1);
    }
    
    public static String changeReturnType(final String s, final String s2) {
        final int index = s2.indexOf(41);
        if (index < 0) {
            return s2;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s2.substring(0, index + 1));
        sb.append('L');
        sb.append(s.replace('.', '/'));
        sb.append(';');
        return sb.toString();
    }
    
    public static CtClass[] getParameterTypes(final String s, final ClassPool classPool) {
        if (s.charAt(0) != '(') {
            return null;
        }
        final CtClass[] array = new CtClass[numOfParameters(s)];
        int n = 0;
        int i = 1;
        do {
            i = toCtClass(classPool, s, i, array, n++);
        } while (i > 0);
        return array;
    }
    
    public static boolean eqParamTypes(final String s, final String s2) {
        if (s.charAt(0) != '(') {
            return false;
        }
        int n = 0;
        while (true) {
            final char char1 = s.charAt(n);
            if (char1 != s2.charAt(n)) {
                return false;
            }
            if (char1 == ')') {
                return true;
            }
            ++n;
        }
    }
    
    public static String getParamDescriptor(final String s) {
        return s.substring(0, s.indexOf(41) + 1);
    }
    
    public static CtClass getReturnType(final String s, final ClassPool classPool) {
        final int index = s.indexOf(41);
        if (index < 0) {
            return null;
        }
        final CtClass[] array = { null };
        toCtClass(classPool, s, index + 1, array, 0);
        return array[0];
    }
    
    public static int numOfParameters(final String s) {
        int n = 0;
        int n2 = 1;
        while (true) {
            char c = s.charAt(n2);
            if (c == ')') {
                return n;
            }
            while (c == '[') {
                c = s.charAt(++n2);
            }
            if (c == 'L') {
                n2 = s.indexOf(59, n2) + 1;
                if (n2 <= 0) {
                    throw new IndexOutOfBoundsException("bad descriptor");
                }
            }
            else {
                ++n2;
            }
            ++n;
        }
    }
    
    public static CtClass toCtClass(final String s, final ClassPool classPool) {
        final CtClass[] array = { null };
        if (toCtClass(classPool, s, 0, array, 0) >= 0) {
            return array[0];
        }
        return classPool.get(s.replace('/', '.'));
    }
    
    private static int toCtClass(final ClassPool classPool, final String s, int n, final CtClass[] array, final int n2) {
        int n3 = 0;
        char c;
        for (c = s.charAt(n); c == '['; c = s.charAt(++n)) {
            ++n3;
        }
        int index;
        String str;
        if (c == 'L') {
            index = s.indexOf(59, ++n);
            str = s.substring(n, index++).replace('/', '.');
        }
        else {
            final CtClass primitiveClass = toPrimitiveClass(c);
            if (primitiveClass == null) {
                return -1;
            }
            index = n + 1;
            if (n3 == 0) {
                array[n2] = primitiveClass;
                return index;
            }
            str = primitiveClass.getName();
        }
        if (n3 > 0) {
            final StringBuilder sb = new StringBuilder(str);
            while (n3-- > 0) {
                sb.append("[]");
            }
            str = sb.toString();
        }
        array[n2] = classPool.get(str);
        return index;
    }
    
    static CtClass toPrimitiveClass(final char c) {
        CtClass ctClass = null;
        switch (c) {
            case 'Z': {
                ctClass = CtClass.booleanType;
                break;
            }
            case 'C': {
                ctClass = CtClass.charType;
                break;
            }
            case 'B': {
                ctClass = CtClass.byteType;
                break;
            }
            case 'S': {
                ctClass = CtClass.shortType;
                break;
            }
            case 'I': {
                ctClass = CtClass.intType;
                break;
            }
            case 'J': {
                ctClass = CtClass.longType;
                break;
            }
            case 'F': {
                ctClass = CtClass.floatType;
                break;
            }
            case 'D': {
                ctClass = CtClass.doubleType;
                break;
            }
            case 'V': {
                ctClass = CtClass.voidType;
                break;
            }
        }
        return ctClass;
    }
    
    public static int arrayDimension(final String s) {
        int index;
        for (index = 0; s.charAt(index) == '['; ++index) {}
        return index;
    }
    
    public static String toArrayComponent(final String s, final int beginIndex) {
        return s.substring(beginIndex);
    }
    
    public static int dataSize(final String s) {
        return dataSize(s, true);
    }
    
    public static int paramSize(final String s) {
        return -dataSize(s, false);
    }
    
    private static int dataSize(final String s, final boolean b) {
        int n = 0;
        char c = s.charAt(0);
        if (c == '(') {
            int n2 = 1;
            while (true) {
                char c2 = s.charAt(n2);
                if (c2 == ')') {
                    c = s.charAt(n2 + 1);
                    break;
                }
                boolean b2 = false;
                while (c2 == '[') {
                    b2 = true;
                    c2 = s.charAt(++n2);
                }
                if (c2 == 'L') {
                    n2 = s.indexOf(59, n2) + 1;
                    if (n2 <= 0) {
                        throw new IndexOutOfBoundsException("bad descriptor");
                    }
                }
                else {
                    ++n2;
                }
                if (!b2 && (c2 == 'J' || c2 == 'D')) {
                    n -= 2;
                }
                else {
                    --n;
                }
            }
        }
        if (b) {
            if (c == 'J' || c == 'D') {
                n += 2;
            }
            else if (c != 'V') {
                ++n;
            }
        }
        return n;
    }
    
    public static String toString(final String s) {
        return PrettyPrinter.toString(s);
    }
    
    static class PrettyPrinter
    {
        static String toString(final String s) {
            final StringBuilder sb = new StringBuilder();
            if (s.charAt(0) == '(') {
                int type = 1;
                sb.append('(');
                while (s.charAt(type) != ')') {
                    if (type > 1) {
                        sb.append(',');
                    }
                    type = readType(sb, type, s);
                }
                sb.append(')');
            }
            else {
                readType(sb, 0, s);
            }
            return sb.toString();
        }
        
        static int readType(final StringBuilder sb, int index, final String s) {
            char c = s.charAt(index);
            int n = 0;
            while (c == '[') {
                ++n;
                c = s.charAt(++index);
            }
            if (c == 'L') {
                while (true) {
                    char char1 = s.charAt(++index);
                    if (char1 == ';') {
                        break;
                    }
                    if (char1 == '/') {
                        char1 = '.';
                    }
                    sb.append(char1);
                }
            }
            else {
                sb.append(Descriptor.toPrimitiveClass(c).getName());
            }
            while (n-- > 0) {
                sb.append("[]");
            }
            return index + 1;
        }
    }
    
    public static class Iterator
    {
        private String desc;
        private int index;
        private int curPos;
        private boolean param;
        
        public Iterator(final String desc) {
            this.desc = desc;
            final int n = 0;
            this.curPos = n;
            this.index = n;
            this.param = false;
        }
        
        public boolean hasNext() {
            return this.index < this.desc.length();
        }
        
        public boolean isParameter() {
            return this.param;
        }
        
        public char currentChar() {
            return this.desc.charAt(this.curPos);
        }
        
        public boolean is2byte() {
            final char currentChar = this.currentChar();
            return currentChar == 'D' || currentChar == 'J';
        }
        
        public int next() {
            int index = this.index;
            char c = this.desc.charAt(index);
            if (c == '(') {
                ++this.index;
                c = this.desc.charAt(++index);
                this.param = true;
            }
            if (c == ')') {
                ++this.index;
                c = this.desc.charAt(++index);
                this.param = false;
            }
            while (c == '[') {
                c = this.desc.charAt(++index);
            }
            if (c == 'L') {
                index = this.desc.indexOf(59, index) + 1;
                if (index <= 0) {
                    throw new IndexOutOfBoundsException("bad descriptor");
                }
            }
            else {
                ++index;
            }
            this.curPos = this.index;
            this.index = index;
            return this.curPos;
        }
    }
}
