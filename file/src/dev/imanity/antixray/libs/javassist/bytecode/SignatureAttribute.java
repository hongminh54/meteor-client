// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode;

import dev.imanity.antixray.libs.javassist.CtClass;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.DataInputStream;

public class SignatureAttribute extends AttributeInfo
{
    public static final String tag = "Signature";
    
    SignatureAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) {
        super(constPool, n, dataInputStream);
    }
    
    public SignatureAttribute(final ConstPool constPool, final String s) {
        super(constPool, "Signature");
        final int addUtf8Info = constPool.addUtf8Info(s);
        this.set(new byte[] { (byte)(addUtf8Info >>> 8), (byte)addUtf8Info });
    }
    
    public String getSignature() {
        return this.getConstPool().getUtf8Info(ByteArray.readU16bit(this.get(), 0));
    }
    
    public void setSignature(final String s) {
        ByteArray.write16bit(this.getConstPool().addUtf8Info(s), this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map<String, String> map) {
        return new SignatureAttribute(constPool, this.getSignature());
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        this.setSignature(renameClass(this.getSignature(), s, s2));
    }
    
    @Override
    void renameClass(final Map<String, String> map) {
        this.setSignature(renameClass(this.getSignature(), map));
    }
    
    static String renameClass(final String s, final String s2, final String s3) {
        final HashMap hashMap = new HashMap();
        hashMap.put(s2, s3);
        return renameClass(s, hashMap);
    }
    
    static String renameClass(final String s, final Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        int beginIndex = 0;
        int fromIndex = 0;
        while (true) {
            final int index = s.indexOf(76, fromIndex);
            if (index < 0) {
                break;
            }
            final ArrayList<StringBuilder> list = new ArrayList<StringBuilder>();
            final ArrayList<StringBuilder> list2 = new ArrayList<StringBuilder>();
            fromIndex = parseClassName(list, list2, s, index) + 1;
            if (fromIndex < 0) {
                break;
            }
            final String join = String.join("$", (CharSequence[])list.toArray(new StringBuilder[0]));
            final String s2 = map.get(join);
            if (s2 != null) {
                if (!makeNewClassName(s, map, join, s2, sb, beginIndex, index, list, list2)) {
                    continue;
                }
                beginIndex = fromIndex;
            }
            else {
                if (!replaceTypeArguments(s, map, sb, beginIndex, index, list, list2)) {
                    continue;
                }
                beginIndex = fromIndex;
            }
        }
        if (beginIndex == 0) {
            return s;
        }
        final int length = s.length();
        if (beginIndex < length) {
            sb.append(s.substring(beginIndex, length));
        }
        return sb.toString();
    }
    
    private static int parseClassName(final ArrayList<StringBuilder> list, final ArrayList<StringBuilder> list2, final String s, final int n) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int n2 = n;
        try {
            char char1;
            while ((char1 = s.charAt(++n2)) != ';') {
                if (char1 == '<') {
                    sb2.append(char1);
                    int i = 1;
                    while (i > 0) {
                        final char char2 = s.charAt(++n2);
                        sb2.append(char2);
                        if (char2 == '<') {
                            ++i;
                        }
                        else {
                            if (char2 != '>') {
                                continue;
                            }
                            --i;
                        }
                    }
                }
                else if (char1 == '.') {
                    list.add(sb);
                    list2.add(sb2);
                    sb = new StringBuilder();
                    sb2 = new StringBuilder();
                }
                else {
                    sb.append(char1);
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            return -2;
        }
        list.add(sb);
        list2.add(sb2);
        return n2;
    }
    
    private static boolean makeNewClassName(final String s, final Map<String, String> map, final String s2, final String s3, final StringBuilder sb, final int beginIndex, final int endIndex, final ArrayList<StringBuilder> list, final ArrayList<StringBuilder> list2) {
        final String[] split = s2.split("\\$");
        final String[] split2 = s3.split("\\$");
        if (split.length == split2.length) {
            final String[] array = new String[list.size()];
            int from = 0;
            for (int i = 0; i < list.size(); ++i) {
                final int n = (int)list.get(i).chars().filter(n2 -> n2 == 36).count() + 1;
                final String join = String.join("$", (CharSequence[])Arrays.copyOfRange(split2, from, from + n));
                from += n;
                array[i] = join;
            }
            sb.append(s.substring(beginIndex, endIndex));
            sb.append('L');
            for (int j = 0; j < array.length; ++j) {
                if (j > 0) {
                    sb.append('.');
                }
                sb.append(array[j]);
                final StringBuilder sb2 = list2.get(j);
                String str;
                if (sb2.length() > 0) {
                    str = "<" + renameClass(sb2.substring(), map) + ">";
                }
                else {
                    str = sb2.toString();
                }
                sb.append(str);
            }
            sb.append(';');
            return true;
        }
        return false;
    }
    
    private static boolean replaceTypeArguments(final String s, final Map<String, String> map, final StringBuilder sb, final int beginIndex, final int endIndex, final ArrayList<StringBuilder> list, final ArrayList<StringBuilder> list2) {
        final ArrayList list3 = new ArrayList();
        boolean b = false;
        for (int i = 0; i < list2.size(); ++i) {
            final StringBuilder sb2 = list2.get(i);
            String s2;
            if (sb2.length() > 0) {
                s2 = "<" + renameClass(sb2.substring(), map) + ">";
                b = (b || !sb2.toString().equals(s2));
            }
            else {
                s2 = sb2.toString();
            }
            list3.add(s2);
        }
        if (b) {
            sb.append(s.substring(beginIndex, endIndex));
            sb.append('L');
            for (int j = 0; j < list2.size(); ++j) {
                if (j > 0) {
                    sb.append('.');
                }
                sb.append((CharSequence)list.get(j));
                sb.append((String)list3.get(j));
            }
            sb.append(';');
            return true;
        }
        return false;
    }
    
    private static boolean isNamePart(final int n) {
        return n != 59 && n != 60;
    }
    
    public static ClassSignature toClassSignature(final String s) {
        try {
            return parseSig(s);
        }
        catch (final IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static MethodSignature toMethodSignature(final String s) {
        try {
            return parseMethodSig(s);
        }
        catch (final IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static ObjectType toFieldSignature(final String s) {
        try {
            return parseObjectType(s, new Cursor(), false);
        }
        catch (final IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static Type toTypeSignature(final String s) {
        try {
            return parseType(s, new Cursor());
        }
        catch (final IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    private static ClassSignature parseSig(final String s) {
        final Cursor cursor = new Cursor();
        final TypeParameter[] typeParams = parseTypeParams(s, cursor);
        final ClassType classType = parseClassType(s, cursor);
        final int length = s.length();
        final ArrayList list = new ArrayList();
        while (cursor.position < length && s.charAt(cursor.position) == 'L') {
            list.add(parseClassType(s, cursor));
        }
        return new ClassSignature(typeParams, classType, (ClassType[])list.toArray(new ClassType[list.size()]));
    }
    
    private static MethodSignature parseMethodSig(final String s) {
        final Cursor cursor = new Cursor();
        final TypeParameter[] typeParams = parseTypeParams(s, cursor);
        if (s.charAt(cursor.position++) != '(') {
            throw error(s);
        }
        final ArrayList list = new ArrayList();
        while (s.charAt(cursor.position) != ')') {
            list.add(parseType(s, cursor));
        }
        final Cursor cursor2 = cursor;
        ++cursor2.position;
        final Type type = parseType(s, cursor);
        final int length = s.length();
        final ArrayList list2 = new ArrayList();
        while (cursor.position < length && s.charAt(cursor.position) == '^') {
            final Cursor cursor3 = cursor;
            ++cursor3.position;
            final ObjectType objectType = parseObjectType(s, cursor, false);
            if (objectType instanceof ArrayType) {
                throw error(s);
            }
            list2.add(objectType);
        }
        return new MethodSignature(typeParams, (Type[])list.toArray(new Type[list.size()]), type, (ObjectType[])list2.toArray(new ObjectType[list2.size()]));
    }
    
    private static TypeParameter[] parseTypeParams(final String s, final Cursor cursor) {
        final ArrayList list = new ArrayList();
        if (s.charAt(cursor.position) == '<') {
            ++cursor.position;
            while (s.charAt(cursor.position) != '>') {
                final int position = cursor.position;
                final int index = cursor.indexOf(s, 58);
                final ObjectType objectType = parseObjectType(s, cursor, true);
                final ArrayList list2 = new ArrayList();
                while (s.charAt(cursor.position) == ':') {
                    ++cursor.position;
                    list2.add(parseObjectType(s, cursor, false));
                }
                list.add(new TypeParameter(s, position, index, objectType, (ObjectType[])list2.toArray(new ObjectType[list2.size()])));
            }
            ++cursor.position;
        }
        return (TypeParameter[])list.toArray(new TypeParameter[list.size()]);
    }
    
    private static ObjectType parseObjectType(final String s, final Cursor cursor, final boolean b) {
        final int position = cursor.position;
        switch (s.charAt(position)) {
            case 'L': {
                return parseClassType2(s, cursor, null);
            }
            case 'T': {
                return new TypeVariable(s, position + 1, cursor.indexOf(s, 59));
            }
            case '[': {
                return parseArray(s, cursor);
            }
            default: {
                if (b) {
                    return null;
                }
                throw error(s);
            }
        }
    }
    
    private static ClassType parseClassType(final String s, final Cursor cursor) {
        if (s.charAt(cursor.position) == 'L') {
            return parseClassType2(s, cursor, null);
        }
        throw error(s);
    }
    
    private static ClassType parseClassType2(final String s, final Cursor cursor, final ClassType classType) {
        final int n = ++cursor.position;
        char c;
        do {
            c = s.charAt(cursor.position++);
        } while (c != '$' && c != '<' && c != ';');
        final int n2 = cursor.position - 1;
        TypeArgument[] typeArgs;
        if (c == '<') {
            typeArgs = parseTypeArgs(s, cursor);
            c = s.charAt(cursor.position++);
        }
        else {
            typeArgs = null;
        }
        final ClassType make = ClassType.make(s, n, n2, typeArgs, classType);
        if (c == '$' || c == '.') {
            --cursor.position;
            return parseClassType2(s, cursor, make);
        }
        return make;
    }
    
    private static TypeArgument[] parseTypeArgs(final String s, final Cursor cursor) {
        final ArrayList list = new ArrayList();
        char char1;
        while ((char1 = s.charAt(cursor.position++)) != '>') {
            TypeArgument typeArgument;
            if (char1 == '*') {
                typeArgument = new TypeArgument(null, '*');
            }
            else {
                if (char1 != '+' && char1 != '-') {
                    char1 = ' ';
                    --cursor.position;
                }
                typeArgument = new TypeArgument(parseObjectType(s, cursor, false), char1);
            }
            list.add(typeArgument);
        }
        return (TypeArgument[])list.toArray(new TypeArgument[list.size()]);
    }
    
    private static ObjectType parseArray(final String s, final Cursor cursor) {
        int n = 1;
        while (s.charAt(++cursor.position) == '[') {
            ++n;
        }
        return new ArrayType(n, parseType(s, cursor));
    }
    
    private static Type parseType(final String s, final Cursor cursor) {
        Type objectType = parseObjectType(s, cursor, true);
        if (objectType == null) {
            objectType = new BaseType(s.charAt(cursor.position++));
        }
        return objectType;
    }
    
    private static BadBytecode error(final String str) {
        return new BadBytecode("bad signature: " + str);
    }
    
    private static class Cursor
    {
        int position;
        
        private Cursor() {
            this.position = 0;
        }
        
        int indexOf(final String s, final int ch) {
            final int index = s.indexOf(ch, this.position);
            if (index < 0) {
                throw error(s);
            }
            this.position = index + 1;
            return index;
        }
    }
    
    public static class ClassSignature
    {
        TypeParameter[] params;
        ClassType superClass;
        ClassType[] interfaces;
        
        public ClassSignature(final TypeParameter[] array, final ClassType classType, final ClassType[] array2) {
            this.params = ((array == null) ? new TypeParameter[0] : array);
            this.superClass = ((classType == null) ? ClassType.OBJECT : classType);
            this.interfaces = ((array2 == null) ? new ClassType[0] : array2);
        }
        
        public ClassSignature(final TypeParameter[] array) {
            this(array, null, null);
        }
        
        public TypeParameter[] getParameters() {
            return this.params;
        }
        
        public ClassType getSuperClass() {
            return this.superClass;
        }
        
        public ClassType[] getInterfaces() {
            return this.interfaces;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            TypeParameter.toString(sb, this.params);
            sb.append(" extends ").append(this.superClass);
            if (this.interfaces.length > 0) {
                sb.append(" implements ");
                Type.toString(sb, this.interfaces);
            }
            return sb.toString();
        }
        
        public String encode() {
            final StringBuilder sb = new StringBuilder();
            if (this.params.length > 0) {
                sb.append('<');
                for (int i = 0; i < this.params.length; ++i) {
                    this.params[i].encode(sb);
                }
                sb.append('>');
            }
            this.superClass.encode(sb);
            for (int j = 0; j < this.interfaces.length; ++j) {
                this.interfaces[j].encode(sb);
            }
            return sb.toString();
        }
    }
    
    public static class MethodSignature
    {
        TypeParameter[] typeParams;
        Type[] params;
        Type retType;
        ObjectType[] exceptions;
        
        public MethodSignature(final TypeParameter[] array, final Type[] array2, final Type type, final ObjectType[] array3) {
            this.typeParams = ((array == null) ? new TypeParameter[0] : array);
            this.params = ((array2 == null) ? new Type[0] : array2);
            this.retType = ((type == null) ? new BaseType("void") : type);
            this.exceptions = ((array3 == null) ? new ObjectType[0] : array3);
        }
        
        public TypeParameter[] getTypeParameters() {
            return this.typeParams;
        }
        
        public Type[] getParameterTypes() {
            return this.params;
        }
        
        public Type getReturnType() {
            return this.retType;
        }
        
        public ObjectType[] getExceptionTypes() {
            return this.exceptions;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            TypeParameter.toString(sb, this.typeParams);
            sb.append(" (");
            Type.toString(sb, this.params);
            sb.append(") ");
            sb.append(this.retType);
            if (this.exceptions.length > 0) {
                sb.append(" throws ");
                Type.toString(sb, this.exceptions);
            }
            return sb.toString();
        }
        
        public String encode() {
            final StringBuilder sb = new StringBuilder();
            if (this.typeParams.length > 0) {
                sb.append('<');
                for (int i = 0; i < this.typeParams.length; ++i) {
                    this.typeParams[i].encode(sb);
                }
                sb.append('>');
            }
            sb.append('(');
            for (int j = 0; j < this.params.length; ++j) {
                this.params[j].encode(sb);
            }
            sb.append(')');
            this.retType.encode(sb);
            if (this.exceptions.length > 0) {
                for (int k = 0; k < this.exceptions.length; ++k) {
                    sb.append('^');
                    this.exceptions[k].encode(sb);
                }
            }
            return sb.toString();
        }
    }
    
    public static class TypeParameter
    {
        String name;
        ObjectType superClass;
        ObjectType[] superInterfaces;
        
        TypeParameter(final String s, final int beginIndex, final int endIndex, final ObjectType superClass, final ObjectType[] superInterfaces) {
            this.name = s.substring(beginIndex, endIndex);
            this.superClass = superClass;
            this.superInterfaces = superInterfaces;
        }
        
        public TypeParameter(final String name, final ObjectType superClass, final ObjectType[] superInterfaces) {
            this.name = name;
            this.superClass = superClass;
            if (superInterfaces == null) {
                this.superInterfaces = new ObjectType[0];
            }
            else {
                this.superInterfaces = superInterfaces;
            }
        }
        
        public TypeParameter(final String s) {
            this(s, null, null);
        }
        
        public String getName() {
            return this.name;
        }
        
        public ObjectType getClassBound() {
            return this.superClass;
        }
        
        public ObjectType[] getInterfaceBound() {
            return this.superInterfaces;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(this.getName());
            if (this.superClass != null) {
                sb.append(" extends ").append(this.superClass.toString());
            }
            final int length = this.superInterfaces.length;
            if (length > 0) {
                for (int i = 0; i < length; ++i) {
                    if (i > 0 || this.superClass != null) {
                        sb.append(" & ");
                    }
                    else {
                        sb.append(" extends ");
                    }
                    sb.append(this.superInterfaces[i].toString());
                }
            }
            return sb.toString();
        }
        
        static void toString(final StringBuilder sb, final TypeParameter[] array) {
            sb.append('<');
            for (int i = 0; i < array.length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(array[i]);
            }
            sb.append('>');
        }
        
        void encode(final StringBuilder sb) {
            sb.append(this.name);
            if (this.superClass == null) {
                sb.append(":Ljava/lang/Object;");
            }
            else {
                sb.append(':');
                this.superClass.encode(sb);
            }
            for (int i = 0; i < this.superInterfaces.length; ++i) {
                sb.append(':');
                this.superInterfaces[i].encode(sb);
            }
        }
    }
    
    public static class TypeArgument
    {
        ObjectType arg;
        char wildcard;
        
        TypeArgument(final ObjectType arg, final char wildcard) {
            this.arg = arg;
            this.wildcard = wildcard;
        }
        
        public TypeArgument(final ObjectType objectType) {
            this(objectType, ' ');
        }
        
        public TypeArgument() {
            this(null, '*');
        }
        
        public static TypeArgument subclassOf(final ObjectType objectType) {
            return new TypeArgument(objectType, '+');
        }
        
        public static TypeArgument superOf(final ObjectType objectType) {
            return new TypeArgument(objectType, '-');
        }
        
        public char getKind() {
            return this.wildcard;
        }
        
        public boolean isWildcard() {
            return this.wildcard != ' ';
        }
        
        public ObjectType getType() {
            return this.arg;
        }
        
        @Override
        public String toString() {
            if (this.wildcard == '*') {
                return "?";
            }
            final String string = this.arg.toString();
            if (this.wildcard == ' ') {
                return string;
            }
            if (this.wildcard == '+') {
                return "? extends " + string;
            }
            return "? super " + string;
        }
        
        static void encode(final StringBuilder sb, final TypeArgument[] array) {
            sb.append('<');
            for (int i = 0; i < array.length; ++i) {
                final TypeArgument typeArgument = array[i];
                if (typeArgument.isWildcard()) {
                    sb.append(typeArgument.wildcard);
                }
                if (typeArgument.getType() != null) {
                    typeArgument.getType().encode(sb);
                }
            }
            sb.append('>');
        }
    }
    
    public abstract static class Type
    {
        abstract void encode(final StringBuilder p0);
        
        static void toString(final StringBuilder sb, final Type[] array) {
            for (int i = 0; i < array.length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(array[i]);
            }
        }
        
        public String jvmTypeName() {
            return this.toString();
        }
    }
    
    public static class BaseType extends Type
    {
        char descriptor;
        
        BaseType(final char descriptor) {
            this.descriptor = descriptor;
        }
        
        public BaseType(final String s) {
            this(Descriptor.of(s).charAt(0));
        }
        
        public char getDescriptor() {
            return this.descriptor;
        }
        
        public CtClass getCtlass() {
            return Descriptor.toPrimitiveClass(this.descriptor);
        }
        
        @Override
        public String toString() {
            return Descriptor.toClassName(Character.toString(this.descriptor));
        }
        
        @Override
        void encode(final StringBuilder sb) {
            sb.append(this.descriptor);
        }
    }
    
    public abstract static class ObjectType extends Type
    {
        public String encode() {
            final StringBuilder sb = new StringBuilder();
            this.encode(sb);
            return sb.toString();
        }
    }
    
    public static class ClassType extends ObjectType
    {
        String name;
        TypeArgument[] arguments;
        public static ClassType OBJECT;
        
        static ClassType make(final String s, final int n, final int n2, final TypeArgument[] array, final ClassType classType) {
            if (classType == null) {
                return new ClassType(s, n, n2, array);
            }
            return new NestedClassType(s, n, n2, array, classType);
        }
        
        ClassType(final String s, final int beginIndex, final int endIndex, final TypeArgument[] arguments) {
            this.name = s.substring(beginIndex, endIndex).replace('/', '.');
            this.arguments = arguments;
        }
        
        public ClassType(final String name, final TypeArgument[] arguments) {
            this.name = name;
            this.arguments = arguments;
        }
        
        public ClassType(final String s) {
            this(s, null);
        }
        
        public String getName() {
            return this.name;
        }
        
        public TypeArgument[] getTypeArguments() {
            return this.arguments;
        }
        
        public ClassType getDeclaringClass() {
            return null;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            final ClassType declaringClass = this.getDeclaringClass();
            if (declaringClass != null) {
                sb.append(declaringClass.toString()).append('.');
            }
            return this.toString2(sb);
        }
        
        private String toString2(final StringBuilder sb) {
            sb.append(this.name);
            if (this.arguments != null) {
                sb.append('<');
                for (int length = this.arguments.length, i = 0; i < length; ++i) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.arguments[i].toString());
                }
                sb.append('>');
            }
            return sb.toString();
        }
        
        @Override
        public String jvmTypeName() {
            final StringBuilder sb = new StringBuilder();
            final ClassType declaringClass = this.getDeclaringClass();
            if (declaringClass != null) {
                sb.append(declaringClass.jvmTypeName()).append('$');
            }
            return this.toString2(sb);
        }
        
        @Override
        void encode(final StringBuilder sb) {
            sb.append('L');
            this.encode2(sb);
            sb.append(';');
        }
        
        void encode2(final StringBuilder sb) {
            final ClassType declaringClass = this.getDeclaringClass();
            if (declaringClass != null) {
                declaringClass.encode2(sb);
                sb.append('$');
            }
            sb.append(this.name.replace('.', '/'));
            if (this.arguments != null) {
                TypeArgument.encode(sb, this.arguments);
            }
        }
        
        static {
            ClassType.OBJECT = new ClassType("java.lang.Object", null);
        }
    }
    
    public static class NestedClassType extends ClassType
    {
        ClassType parent;
        
        NestedClassType(final String s, final int n, final int n2, final TypeArgument[] array, final ClassType parent) {
            super(s, n, n2, array);
            this.parent = parent;
        }
        
        public NestedClassType(final ClassType parent, final String s, final TypeArgument[] array) {
            super(s, array);
            this.parent = parent;
        }
        
        @Override
        public ClassType getDeclaringClass() {
            return this.parent;
        }
    }
    
    public static class ArrayType extends ObjectType
    {
        int dim;
        Type componentType;
        
        public ArrayType(final int dim, final Type componentType) {
            this.dim = dim;
            this.componentType = componentType;
        }
        
        public int getDimension() {
            return this.dim;
        }
        
        public Type getComponentType() {
            return this.componentType;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(this.componentType.toString());
            for (int i = 0; i < this.dim; ++i) {
                sb.append("[]");
            }
            return sb.toString();
        }
        
        @Override
        void encode(final StringBuilder sb) {
            for (int i = 0; i < this.dim; ++i) {
                sb.append('[');
            }
            this.componentType.encode(sb);
        }
    }
    
    public static class TypeVariable extends ObjectType
    {
        String name;
        
        TypeVariable(final String s, final int beginIndex, final int endIndex) {
            this.name = s.substring(beginIndex, endIndex);
        }
        
        public TypeVariable(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
        
        @Override
        void encode(final StringBuilder sb) {
            sb.append('T').append(this.name).append(';');
        }
    }
}
