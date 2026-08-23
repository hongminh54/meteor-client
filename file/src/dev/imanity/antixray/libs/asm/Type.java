// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public final class Type
{
    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    private static final int INTERNAL = 12;
    private static final String PRIMITIVE_DESCRIPTORS = "VZCBSIFJD";
    public static final Type VOID_TYPE;
    public static final Type BOOLEAN_TYPE;
    public static final Type CHAR_TYPE;
    public static final Type BYTE_TYPE;
    public static final Type SHORT_TYPE;
    public static final Type INT_TYPE;
    public static final Type FLOAT_TYPE;
    public static final Type LONG_TYPE;
    public static final Type DOUBLE_TYPE;
    private final int sort;
    private final String valueBuffer;
    private final int valueBegin;
    private final int valueEnd;
    
    private Type(final int sort, final String valueBuffer, final int valueBegin, final int valueEnd) {
        this.sort = sort;
        this.valueBuffer = valueBuffer;
        this.valueBegin = valueBegin;
        this.valueEnd = valueEnd;
    }
    
    public static Type getType(final String s) {
        return getTypeInternal(s, 0, s.length());
    }
    
    public static Type getType(final Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            return getType(getDescriptor(clazz));
        }
        if (clazz == Integer.TYPE) {
            return Type.INT_TYPE;
        }
        if (clazz == Void.TYPE) {
            return Type.VOID_TYPE;
        }
        if (clazz == Boolean.TYPE) {
            return Type.BOOLEAN_TYPE;
        }
        if (clazz == Byte.TYPE) {
            return Type.BYTE_TYPE;
        }
        if (clazz == Character.TYPE) {
            return Type.CHAR_TYPE;
        }
        if (clazz == Short.TYPE) {
            return Type.SHORT_TYPE;
        }
        if (clazz == Double.TYPE) {
            return Type.DOUBLE_TYPE;
        }
        if (clazz == Float.TYPE) {
            return Type.FLOAT_TYPE;
        }
        if (clazz == Long.TYPE) {
            return Type.LONG_TYPE;
        }
        throw new AssertionError();
    }
    
    public static Type getType(final Constructor<?> constructor) {
        return getType(getConstructorDescriptor(constructor));
    }
    
    public static Type getType(final Method method) {
        return getType(getMethodDescriptor(method));
    }
    
    public Type getElementType() {
        return getTypeInternal(this.valueBuffer, this.valueBegin + this.getDimensions(), this.valueEnd);
    }
    
    public static Type getObjectType(final String s) {
        return new Type((s.charAt(0) == '[') ? 9 : 12, s, 0, s.length());
    }
    
    public static Type getMethodType(final String s) {
        return new Type(11, s, 0, s.length());
    }
    
    public static Type getMethodType(final Type type, final Type... array) {
        return getType(getMethodDescriptor(type, array));
    }
    
    public Type[] getArgumentTypes() {
        return getArgumentTypes(this.getDescriptor());
    }
    
    public static Type[] getArgumentTypes(final String s) {
        final Type[] array = new Type[getArgumentCount(s)];
        int max = 1;
        int n = 0;
        while (s.charAt(max) != ')') {
            final int n2 = max;
            while (s.charAt(max) == '[') {
                ++max;
            }
            if (s.charAt(max++) == 'L') {
                max = Math.max(max, s.indexOf(59, max) + 1);
            }
            array[n++] = getTypeInternal(s, n2, max);
        }
        return array;
    }
    
    public static Type[] getArgumentTypes(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Type[] array = new Type[parameterTypes.length];
        for (int i = parameterTypes.length - 1; i >= 0; --i) {
            array[i] = getType(parameterTypes[i]);
        }
        return array;
    }
    
    public Type getReturnType() {
        return getReturnType(this.getDescriptor());
    }
    
    public static Type getReturnType(final String s) {
        return getTypeInternal(s, getReturnTypeOffset(s), s.length());
    }
    
    public static Type getReturnType(final Method method) {
        return getType(method.getReturnType());
    }
    
    static int getReturnTypeOffset(final String s) {
        int max;
        for (max = 1; s.charAt(max) != ')'; max = Math.max(max, s.indexOf(59, max) + 1)) {
            while (s.charAt(max) == '[') {
                ++max;
            }
            if (s.charAt(max++) == 'L') {}
        }
        return max + 1;
    }
    
    private static Type getTypeInternal(final String s, final int index, final int n) {
        switch (s.charAt(index)) {
            case 'V': {
                return Type.VOID_TYPE;
            }
            case 'Z': {
                return Type.BOOLEAN_TYPE;
            }
            case 'C': {
                return Type.CHAR_TYPE;
            }
            case 'B': {
                return Type.BYTE_TYPE;
            }
            case 'S': {
                return Type.SHORT_TYPE;
            }
            case 'I': {
                return Type.INT_TYPE;
            }
            case 'F': {
                return Type.FLOAT_TYPE;
            }
            case 'J': {
                return Type.LONG_TYPE;
            }
            case 'D': {
                return Type.DOUBLE_TYPE;
            }
            case '[': {
                return new Type(9, s, index, n);
            }
            case 'L': {
                return new Type(10, s, index + 1, n - 1);
            }
            case '(': {
                return new Type(11, s, index, n);
            }
            default: {
                throw new IllegalArgumentException(stringConcat$0(s));
            }
        }
    }
    
    private static /* synthetic */ String stringConcat$0(final String str) {
        return "Invalid descriptor: " + str;
    }
    
    public String getClassName() {
        switch (this.sort) {
            case 0: {
                return "void";
            }
            case 1: {
                return "boolean";
            }
            case 2: {
                return "char";
            }
            case 3: {
                return "byte";
            }
            case 4: {
                return "short";
            }
            case 5: {
                return "int";
            }
            case 6: {
                return "float";
            }
            case 7: {
                return "long";
            }
            case 8: {
                return "double";
            }
            case 9: {
                final StringBuilder sb = new StringBuilder(this.getElementType().getClassName());
                for (int i = this.getDimensions(); i > 0; --i) {
                    sb.append("[]");
                }
                return sb.toString();
            }
            case 10:
            case 12: {
                return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
            }
            default: {
                throw new AssertionError();
            }
        }
    }
    
    public String getInternalName() {
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }
    
    public static String getInternalName(final Class<?> clazz) {
        return clazz.getName().replace('.', '/');
    }
    
    public String getDescriptor() {
        if (this.sort == 10) {
            return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
        }
        if (this.sort == 12) {
            return stringConcat$1(this.valueBuffer.substring(this.valueBegin, this.valueEnd));
        }
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }
    
    private static /* synthetic */ String stringConcat$1(final String str) {
        return "L" + str + ";";
    }
    
    public static String getDescriptor(final Class<?> clazz) {
        final StringBuilder sb = new StringBuilder();
        appendDescriptor(clazz, sb);
        return sb.toString();
    }
    
    public static String getConstructorDescriptor(final Constructor<?> constructor) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        final Class[] parameterTypes = constructor.getParameterTypes();
        for (int length = parameterTypes.length, i = 0; i < length; ++i) {
            appendDescriptor(parameterTypes[i], sb);
        }
        return sb.append(")V").toString();
    }
    
    public static String getMethodDescriptor(final Type type, final Type... array) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].appendDescriptor(sb);
        }
        sb.append(')');
        type.appendDescriptor(sb);
        return sb.toString();
    }
    
    public static String getMethodDescriptor(final Method method) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        final Class<?>[] parameterTypes = method.getParameterTypes();
        for (int length = parameterTypes.length, i = 0; i < length; ++i) {
            appendDescriptor(parameterTypes[i], sb);
        }
        sb.append(')');
        appendDescriptor(method.getReturnType(), sb);
        return sb.toString();
    }
    
    private void appendDescriptor(final StringBuilder sb) {
        if (this.sort == 10) {
            sb.append(this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
        }
        else if (this.sort == 12) {
            sb.append('L').append(this.valueBuffer, this.valueBegin, this.valueEnd).append(';');
        }
        else {
            sb.append(this.valueBuffer, this.valueBegin, this.valueEnd);
        }
    }
    
    private static void appendDescriptor(final Class<?> clazz, final StringBuilder sb) {
        Class<Integer> componentType;
        for (componentType = (Class<Integer>)clazz; componentType.isArray(); componentType = componentType.getComponentType()) {
            sb.append('[');
        }
        if (componentType.isPrimitive()) {
            char c;
            if (componentType == Integer.TYPE) {
                c = 'I';
            }
            else if (componentType == Void.TYPE) {
                c = 'V';
            }
            else if (componentType == Boolean.TYPE) {
                c = 'Z';
            }
            else if (componentType == Byte.TYPE) {
                c = 'B';
            }
            else if (componentType == Character.TYPE) {
                c = 'C';
            }
            else if (componentType == Short.TYPE) {
                c = 'S';
            }
            else if (componentType == Double.TYPE) {
                c = 'D';
            }
            else if (componentType == Float.TYPE) {
                c = 'F';
            }
            else {
                if (componentType != Long.TYPE) {
                    throw new AssertionError();
                }
                c = 'J';
            }
            sb.append(c);
        }
        else {
            sb.append('L').append(getInternalName(componentType)).append(';');
        }
    }
    
    public int getSort() {
        return (this.sort == 12) ? 10 : this.sort;
    }
    
    public int getDimensions() {
        int n;
        for (n = 1; this.valueBuffer.charAt(this.valueBegin + n) == '['; ++n) {}
        return n;
    }
    
    public int getSize() {
        switch (this.sort) {
            case 0: {
                return 0;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 12: {
                return 1;
            }
            case 7:
            case 8: {
                return 2;
            }
            default: {
                throw new AssertionError();
            }
        }
    }
    
    public int getArgumentCount() {
        return getArgumentCount(this.getDescriptor());
    }
    
    public static int getArgumentCount(final String s) {
        int n = 0;
        int max = 1;
        while (s.charAt(max) != ')') {
            while (s.charAt(max) == '[') {
                ++max;
            }
            if (s.charAt(max++) == 'L') {
                max = Math.max(max, s.indexOf(59, max) + 1);
            }
            ++n;
        }
        return n;
    }
    
    public int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(this.getDescriptor());
    }
    
    public static int getArgumentsAndReturnSizes(final String s) {
        int n = 1;
        int max = 1;
        for (char c = s.charAt(max); c != ')'; c = s.charAt(max)) {
            if (c == 'J' || c == 'D') {
                ++max;
                n += 2;
            }
            else {
                while (s.charAt(max) == '[') {
                    ++max;
                }
                if (s.charAt(max++) == 'L') {
                    max = Math.max(max, s.indexOf(59, max) + 1);
                }
                ++n;
            }
        }
        final char char1 = s.charAt(max + 1);
        if (char1 == 'V') {
            return n << 2;
        }
        return n << 2 | ((char1 == 'J' || char1 == 'D') ? 2 : 1);
    }
    
    public int getOpcode(final int n) {
        if (n == 46 || n == 79) {
            switch (this.sort) {
                case 1:
                case 3: {
                    return n + 5;
                }
                case 2: {
                    return n + 6;
                }
                case 4: {
                    return n + 7;
                }
                case 5: {
                    return n;
                }
                case 6: {
                    return n + 2;
                }
                case 7: {
                    return n + 1;
                }
                case 8: {
                    return n + 3;
                }
                case 9:
                case 10:
                case 12: {
                    return n + 4;
                }
                case 0:
                case 11: {
                    throw new UnsupportedOperationException();
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        else {
            switch (this.sort) {
                case 0: {
                    if (n != 172) {
                        throw new UnsupportedOperationException();
                    }
                    return 177;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    return n;
                }
                case 6: {
                    return n + 2;
                }
                case 7: {
                    return n + 1;
                }
                case 8: {
                    return n + 3;
                }
                case 9:
                case 10:
                case 12: {
                    if (n != 21 && n != 54 && n != 172) {
                        throw new UnsupportedOperationException();
                    }
                    return n + 4;
                }
                case 11: {
                    throw new UnsupportedOperationException();
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Type)) {
            return false;
        }
        final Type type = (Type)o;
        if (((this.sort == 12) ? 10 : this.sort) != ((type.sort == 12) ? 10 : type.sort)) {
            return false;
        }
        final int valueBegin = this.valueBegin;
        final int valueEnd = this.valueEnd;
        final int valueBegin2 = type.valueBegin;
        if (valueEnd - valueBegin != type.valueEnd - valueBegin2) {
            return false;
        }
        for (int i = valueBegin, index = valueBegin2; i < valueEnd; ++i, ++index) {
            if (this.valueBuffer.charAt(i) != type.valueBuffer.charAt(index)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int n = 13 * ((this.sort == 12) ? 10 : this.sort);
        if (this.sort >= 9) {
            for (int i = this.valueBegin; i < this.valueEnd; ++i) {
                n = 17 * (n + this.valueBuffer.charAt(i));
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        return this.getDescriptor();
    }
    
    static {
        VOID_TYPE = new Type(0, "VZCBSIFJD", 0, 1);
        BOOLEAN_TYPE = new Type(1, "VZCBSIFJD", 1, 2);
        CHAR_TYPE = new Type(2, "VZCBSIFJD", 2, 3);
        BYTE_TYPE = new Type(3, "VZCBSIFJD", 3, 4);
        SHORT_TYPE = new Type(4, "VZCBSIFJD", 4, 5);
        INT_TYPE = new Type(5, "VZCBSIFJD", 5, 6);
        FLOAT_TYPE = new Type(6, "VZCBSIFJD", 6, 7);
        LONG_TYPE = new Type(7, "VZCBSIFJD", 7, 8);
        DOUBLE_TYPE = new Type(8, "VZCBSIFJD", 8, 9);
    }
}
