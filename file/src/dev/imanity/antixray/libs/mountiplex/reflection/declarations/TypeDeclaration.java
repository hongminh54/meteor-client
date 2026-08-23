// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.LinkedList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.ParserStringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.TypeVariable;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.lang.reflect.Type;

public class TypeDeclaration extends Declaration
{
    public static final TypeDeclaration INVALID;
    public static final TypeDeclaration OBJECT;
    public static final TypeDeclaration ENUM;
    public static final TypeDeclaration ANY;
    public final boolean isWildcard;
    public final boolean isPrimitive;
    private final TypeDeclaration boxed;
    public final String variableName;
    public final String typeName;
    public final String typePath;
    public final Class<?> type;
    public final TypeDeclaration[] genericTypes;
    public final TypeDeclaration cast;
    private TypeDeclaration[] superTypes;
    
    public TypeDeclaration(final ClassResolver classResolver, Type genericComponentType) {
        super(classResolver);
        this.superTypes = null;
        this.cast = null;
        if (genericComponentType == null) {
            this.isWildcard = false;
            this.variableName = null;
            this.type = null;
            this.typeName = "NULL";
            this.typePath = "NULL";
            this.isPrimitive = false;
            this.boxed = this;
            this.genericTypes = new TypeDeclaration[0];
            this.setInvalid();
            return;
        }
        this.isWildcard = (genericComponentType instanceof WildcardType);
        if (this.isWildcard) {
            genericComponentType = ((WildcardType)genericComponentType).getUpperBounds()[0];
        }
        int n;
        for (n = 0; genericComponentType instanceof GenericArrayType; genericComponentType = ((GenericArrayType)genericComponentType).getGenericComponentType(), ++n) {}
        if (genericComponentType instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)genericComponentType;
            final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            this.type = MountiplexUtil.getArrayType((Class<?>)parameterizedType.getRawType(), n);
            this.typePath = classResolver.resolvePath(this.type);
            this.typeName = classResolver.resolveName(this.type);
            this.isPrimitive = false;
            this.boxed = this;
            this.genericTypes = new TypeDeclaration[actualTypeArguments.length];
            this.variableName = null;
            for (int i = 0; i < actualTypeArguments.length; ++i) {
                this.genericTypes[i] = new TypeDeclaration(classResolver, actualTypeArguments[i]);
            }
        }
        else if (genericComponentType instanceof Class) {
            this.type = MountiplexUtil.getArrayType((Class<?>)genericComponentType, n);
            this.typePath = classResolver.resolvePath(this.type);
            this.typeName = classResolver.resolveName(this.type);
            this.isPrimitive = this.type.isPrimitive();
            this.boxed = (this.isPrimitive ? fromClass(BoxedType.getBoxedType(this.type)) : this);
            this.genericTypes = new TypeDeclaration[0];
            this.variableName = null;
        }
        else {
            if (genericComponentType instanceof TypeVariable) {
                final TypeVariable typeVariable = (TypeVariable)genericComponentType;
                Type rawType = typeVariable.getBounds()[0];
                while (true) {
                    while (!(rawType instanceof Class)) {
                        if (rawType instanceof ParameterizedType) {
                            rawType = ((ParameterizedType)rawType).getRawType();
                        }
                        else {
                            if (!(rawType instanceof TypeVariable)) {
                                final Class<Object> clazz = Object.class;
                                this.type = MountiplexUtil.getArrayType(clazz, n);
                                this.typePath = classResolver.resolvePath(this.type);
                                this.typeName = classResolver.resolveName(this.type);
                                this.isPrimitive = false;
                                this.boxed = this;
                                this.genericTypes = new TypeDeclaration[0];
                                this.variableName = typeVariable.getName();
                                return;
                            }
                            rawType = ((TypeVariable)rawType).getBounds()[0];
                        }
                    }
                    final Class<Object> clazz = (Class<Object>)rawType;
                    continue;
                }
            }
            MountiplexUtil.LOGGER.warning("Unsupported type in TypeDeclaration: " + genericComponentType.getClass());
            this.type = null;
            this.typePath = "";
            this.typeName = "";
            this.isPrimitive = false;
            this.boxed = this;
            this.genericTypes = new TypeDeclaration[0];
            this.variableName = null;
            this.setInvalid();
        }
    }
    
    private TypeDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver, stringBuffer);
        this.superTypes = null;
        if (stringBuffer == null) {
            this.typeName = "";
            this.typePath = "";
            this.type = null;
            this.isPrimitive = false;
            this.boxed = this;
            this.genericTypes = new TypeDeclaration[0];
            this.isWildcard = false;
            this.variableName = null;
            this.cast = null;
            this.setInvalid();
            return;
        }
        String s = null;
        String s2 = null;
        StringBuffer postfix = StringBuffer.EMPTY;
        int n = -1;
        boolean isWildcard = false;
        int n2 = 0;
        TypeDeclaration typeDeclaration = null;
        for (int i = 0; i < stringBuffer.length(); ++i) {
            final char char1 = stringBuffer.charAt(i);
            if (n == -1) {
                if (char1 == ' ') {
                    continue;
                }
                if (char1 == '?') {
                    isWildcard = true;
                    continue;
                }
                if (char1 == ')' && typeDeclaration != null) {
                    continue;
                }
            }
            final boolean b = !MountiplexUtil.containsChar(char1, ParserStringBuffer.INVALID_NAME_CHARACTERS);
            if (n == -1) {
                if (char1 == '(') {
                    final int index = stringBuffer.indexOf(')', i + 1);
                    if (index == -1) {
                        break;
                    }
                    typeDeclaration = new TypeDeclaration(classResolver, stringBuffer.substring(i + 1, index));
                    if (!typeDeclaration.isValid()) {
                        break;
                    }
                    i = index;
                    continue;
                }
                else {
                    if (!b) {
                        postfix = stringBuffer.substring(i);
                        break;
                    }
                    n = i;
                }
            }
            if (!b && s == null) {
                s = stringBuffer.substringToString(n, i);
                if (isWildcard && n2 == 0) {
                    n = -1;
                    if (!s.equals("extends")) {
                        break;
                    }
                    n2 = 1;
                    s = null;
                }
            }
            if (s != null && char1 != ' ') {
                if (!stringBuffer.substring(i).startsWith("extends ")) {
                    postfix = stringBuffer.substring(i);
                    break;
                }
                if (s.length() > 1) {
                    postfix = stringBuffer.substring(i);
                    break;
                }
                s2 = s;
                n2 = 1;
                s = null;
                n = -1;
                i += 7;
            }
        }
        this.isWildcard = isWildcard;
        if (n == -1) {
            if (this.isWildcard) {
                this.setPostfix(postfix);
                this.typeName = "";
                this.typePath = "java.lang.Object";
                this.type = Object.class;
                this.isPrimitive = false;
                this.boxed = this;
                this.variableName = s2;
                this.genericTypes = new TypeDeclaration[0];
                this.cast = typeDeclaration;
            }
            else {
                this.setInvalid();
                this.typeName = "";
                this.typePath = "";
                this.type = null;
                this.isPrimitive = false;
                this.boxed = this;
                this.variableName = s2;
                this.genericTypes = new TypeDeclaration[0];
                this.cast = typeDeclaration;
            }
            return;
        }
        if (s == null) {
            s = stringBuffer.substringToString(n);
            postfix = StringBuffer.EMPTY;
        }
        if (s != null && s.equals("enum")) {
            this.setInvalid();
            this.typeName = "";
            this.typePath = "";
            this.type = null;
            this.variableName = s2;
            this.isPrimitive = false;
            this.boxed = this;
            this.genericTypes = new TypeDeclaration[0];
            this.cast = typeDeclaration;
            return;
        }
        Label_0753: {
            if (s != null && s.length() == 1 && s2 == null) {
                s2 = s;
                s = "Object";
                if (classResolver.getDeclaredClass() != null) {
                    for (final TypeVariable<Class<?>> typeVariable : classResolver.getDeclaredClass().getTypeParameters()) {
                        if (s2.equals(typeVariable.getName())) {
                            final Type[] bounds = typeVariable.getBounds();
                            for (int length2 = bounds.length, k = 0; k < length2; ++k) {
                                final TypeDeclaration fromType = fromType(classResolver, bounds[k]);
                                if (fromType.isValid() && fromType.isResolved()) {
                                    s = fromType.typePath;
                                    break Label_0753;
                                }
                            }
                        }
                    }
                }
            }
        }
        this.variableName = s2;
        if (postfix.length() > 0 && postfix.charAt(0) == '<') {
            final LinkedList<TypeDeclaration> list = new LinkedList<TypeDeclaration>();
            do {
                final TypeDeclaration e = new TypeDeclaration(classResolver, postfix.substring(1));
                if (!e.isValid()) {
                    this.setInvalid();
                    this.typeName = "";
                    this.typePath = "";
                    this.type = null;
                    this.isPrimitive = false;
                    this.boxed = this;
                    this.genericTypes = new TypeDeclaration[0];
                    this.cast = null;
                    return;
                }
                list.add(e);
                postfix = e.getPostfix();
            } while (postfix.length() > 0 && postfix.charAt(0) == ',');
            int l = 0;
            while (l < postfix.length()) {
                final char char2 = postfix.charAt(l);
                if (char2 != ' ') {
                    if (char2 == '>') {
                        postfix = postfix.substring(l + 1);
                        break;
                    }
                    postfix = postfix.substring(l);
                    break;
                }
                else {
                    ++l;
                }
            }
            this.genericTypes = list.toArray(new TypeDeclaration[list.size()]);
        }
        else {
            this.genericTypes = new TypeDeclaration[0];
        }
        int n3 = -1;
        for (int n4 = 0; n4 < postfix.length(); ++n4) {
            final char char3 = postfix.charAt(n4);
            if (char3 == '[' || char3 == ']') {
                s += char3;
            }
            else if (char3 != ' ') {
                n3 = n4;
                break;
            }
        }
        if (n3 == -1) {
            this.setPostfix(StringBuffer.EMPTY);
        }
        else {
            this.setPostfix(postfix.substring(n3));
        }
        final ClassResolver.ResolveResult resolve = classResolver.resolve(s);
        this.cast = typeDeclaration;
        this.type = resolve.classType;
        this.typePath = resolve.classPath;
        this.typeName = s;
        this.isPrimitive = (this.type != null && this.type.isPrimitive());
        this.boxed = (this.isPrimitive ? fromClass(BoxedType.getBoxedType(this.type)) : this);
    }
    
    private TypeDeclaration(final TypeDeclaration typeDeclaration, final TypeDeclaration[] genericTypes) {
        super(typeDeclaration.getResolver());
        this.superTypes = null;
        this.cast = typeDeclaration.cast;
        this.isWildcard = typeDeclaration.isWildcard;
        this.typeName = typeDeclaration.typeName;
        this.typePath = typeDeclaration.typePath;
        this.type = typeDeclaration.type;
        this.isPrimitive = typeDeclaration.isPrimitive;
        this.boxed = (typeDeclaration.isPrimitive ? typeDeclaration.boxed : this);
        this.variableName = typeDeclaration.variableName;
        this.genericTypes = genericTypes;
    }
    
    public TypeDeclaration getSuperType() {
        return this.resolveSuperType(Resolver.getMeta(this.type).superType);
    }
    
    public TypeDeclaration[] getSuperTypes() {
        if (this.superTypes == null) {
            final ArrayList list = new ArrayList();
            final TypeDeclaration superType = this.getSuperType();
            if (superType != null) {
                list.add(superType);
                list.addAll(Arrays.asList(superType.getSuperTypes()));
            }
            this.addInterfaces(list);
            this.superTypes = list.toArray(new TypeDeclaration[list.size()]);
        }
        return this.superTypes;
    }
    
    private void addInterfaces(final ArrayList<TypeDeclaration> list) {
        if (this.type != null) {
            final TypeDeclaration[] interfaces = Resolver.getMeta(this.type).interfaces;
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final TypeDeclaration resolveSuperType = this.resolveSuperType(interfaces[i]);
                if (!list.contains(resolveSuperType)) {
                    list.add(resolveSuperType);
                    resolveSuperType.addInterfaces(list);
                }
            }
        }
    }
    
    public boolean isArray() {
        return this.type != null && this.type.isArray();
    }
    
    public TypeDeclaration exposed() {
        return (this.cast == null) ? this : this.cast;
    }
    
    public TypeDeclaration getComponentType() {
        if (this.type != null && this.type.isArray()) {
            return new TypeDeclaration(this.getResolver(), this.type.getComponentType()).setGenericTypes(this.genericTypes);
        }
        return null;
    }
    
    public TypeDeclaration getBoxedType() {
        return this.boxed;
    }
    
    public boolean hasTypeVariables() {
        if (this.variableName != null) {
            return true;
        }
        if (this.genericTypes.length == 0) {
            return false;
        }
        for (int i = 0; i < this.genericTypes.length; ++i) {
            if (this.genericTypes[i].hasTypeVariables()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canDownCast() {
        return this.cast == null || this.cast.isAssignableFrom(this);
    }
    
    public boolean canUpCast() {
        return this.cast == null || this.isAssignableFrom(this.cast);
    }
    
    public boolean isAssignableFrom(final Object o) {
        return o != null && this.type.isAssignableFrom(o.getClass());
    }
    
    public boolean isAssignableFrom(final TypeDeclaration typeDeclaration) {
        return typeDeclaration != null && typeDeclaration.isInstanceOf(this);
    }
    
    public boolean isInstanceOf(final Class<?> clazz) {
        return clazz != null && this.type != null && clazz.isAssignableFrom(this.type);
    }
    
    public boolean isInstanceOf(final TypeDeclaration typeDeclaration) {
        if (typeDeclaration == null || typeDeclaration.type == null || this.type == null || !typeDeclaration.type.isAssignableFrom(this.type)) {
            return false;
        }
        if (typeDeclaration.genericTypes.length == 0) {
            return true;
        }
        final TypeDeclaration typeDeclaration2 = this.type.equals(typeDeclaration.type) ? this : this.castAsSuperType(typeDeclaration.type);
        if (typeDeclaration2 == null || typeDeclaration.genericTypes.length != typeDeclaration2.genericTypes.length) {
            return false;
        }
        for (int i = 0; i < typeDeclaration2.genericTypes.length; ++i) {
            final TypeDeclaration typeDeclaration3 = typeDeclaration2.genericTypes[i];
            final TypeDeclaration typeDeclaration4 = typeDeclaration.genericTypes[i];
            if (typeDeclaration4.type == null) {
                return false;
            }
            if (typeDeclaration4.isWildcard) {
                if (!typeDeclaration3.isInstanceOf(typeDeclaration4)) {
                    return false;
                }
            }
            else if (typeDeclaration4.variableName == null || !typeDeclaration3.isInstanceOf(typeDeclaration4)) {
                if (!typeDeclaration4.equals(typeDeclaration3)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public TypeDeclaration castAsType(final Class<?> clazz) {
        if (clazz.equals(this.type)) {
            return this;
        }
        if (clazz.isAssignableFrom(this.type)) {
            return this.castAsSuperType(clazz);
        }
        return null;
    }
    
    private TypeDeclaration castAsSuperType(final Class<?> obj) {
        for (final TypeDeclaration typeDeclaration : this.getSuperTypes()) {
            if (typeDeclaration.type.equals(obj)) {
                return typeDeclaration;
            }
        }
        return null;
    }
    
    public TypeDeclaration setGenericTypes(final TypeDeclaration... array) {
        return new TypeDeclaration(this, array);
    }
    
    public TypeDeclaration getGenericType(final int n) {
        return (n >= 0 && n < this.genericTypes.length) ? this.genericTypes[n] : TypeDeclaration.OBJECT;
    }
    
    private final TypeDeclaration resolveSuperType(final TypeDeclaration typeDeclaration) {
        if (typeDeclaration == null) {
            return null;
        }
        if (typeDeclaration.genericTypes.length > 0 && this.genericTypes.length > 0) {
            final TypeDeclaration[] genericTypes = typeDeclaration.genericTypes.clone();
            final TypeVariable<Class<?>>[] typeParameters = this.type.getTypeParameters();
            if (typeParameters.length == this.genericTypes.length) {
                final boolean b = typeParameters.length == typeDeclaration.genericTypes.length;
                for (int i = 0; i < typeDeclaration.genericTypes.length; ++i) {
                    final String variableName = typeDeclaration.genericTypes[i].variableName;
                    if (variableName != null) {
                        if (b && typeParameters[i].getName().equals(variableName)) {
                            genericTypes[i] = this.genericTypes[i];
                        }
                        else {
                            for (int j = 0; j < typeParameters.length; ++j) {
                                if (typeParameters[j].getName().equals(variableName)) {
                                    genericTypes[i] = this.genericTypes[j];
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return typeDeclaration.setGenericTypes(genericTypes);
        }
        return typeDeclaration;
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof TypeDeclaration)) {
            return 0.0;
        }
        final TypeDeclaration typeDeclaration = (TypeDeclaration)declaration;
        if (!typeDeclaration.isValid() || !this.isValid()) {
            return 0.0;
        }
        double similarity;
        if (!typeDeclaration.isResolved() || !this.isResolved()) {
            similarity = MountiplexUtil.similarity(this.typePath, typeDeclaration.typePath);
        }
        else if (this.type.equals(typeDeclaration.type)) {
            similarity = 1.0;
        }
        else {
            final Class<?> tryBoxType = BoxedType.tryBoxType(this.type);
            final Class<?> tryBoxType2 = BoxedType.tryBoxType(typeDeclaration.type);
            if (tryBoxType.isAssignableFrom(tryBoxType2)) {
                int n = 1;
                Class<?> superclass = tryBoxType2;
                while ((superclass = superclass.getSuperclass()) != null && !superclass.equals(tryBoxType)) {
                    ++n;
                }
                similarity = 1.0 / n;
            }
            else if (tryBoxType2.isAssignableFrom(tryBoxType)) {
                int n2 = 1;
                Class<?> superclass2 = tryBoxType;
                while ((superclass2 = superclass2.getSuperclass()) != null && !superclass2.equals(tryBoxType2)) {
                    ++n2;
                }
                similarity = 1.0 / n2;
            }
            else {
                final Collection collection = ReflectionUtil.getAllClassesAndInterfaces(tryBoxType).filter(clazz -> clazz != Object.class).collect((Collector<? super Class<?>, ?, Collection>)Collectors.toCollection((Supplier<R>)ArrayList::new));
                final Collection collection2 = ReflectionUtil.getAllClassesAndInterfaces(tryBoxType2).filter(clazz2 -> clazz2 != Object.class).collect((Collector<? super Class<?>, ?, Collection>)Collectors.toCollection((Supplier<R>)ArrayList::new));
                final Stream stream = collection.stream();
                final Collection obj = collection2;
                Objects.requireNonNull(obj);
                final long count = stream.filter(obj::contains).count();
                if (count == 0L) {
                    similarity = 0.0;
                }
                else {
                    similarity = count / (double)Stream.concat((Stream<?>)collection.stream(), (Stream<?>)collection2.stream()).distinct().count();
                }
            }
        }
        double n4;
        if (this.genericTypes.length > 0 && typeDeclaration.genericTypes.length > 0) {
            if (this.genericTypes.length == typeDeclaration.genericTypes.length) {
                double n3 = 0.0;
                for (int i = 0; i < this.genericTypes.length; ++i) {
                    n3 += this.genericTypes[i].similarity(typeDeclaration.genericTypes[i]);
                }
                n4 = n3 / this.genericTypes.length;
            }
            else {
                n4 = 0.0;
            }
        }
        else if (this.genericTypes.length == 0 && typeDeclaration.genericTypes.length == 0) {
            n4 = 1.0;
        }
        else {
            n4 = 0.5;
        }
        return 0.75 * similarity + 0.25 * n4;
    }
    
    @Override
    public final boolean match(final Declaration declaration) {
        if (!(declaration instanceof TypeDeclaration)) {
            return false;
        }
        final TypeDeclaration typeDeclaration = (TypeDeclaration)declaration;
        if (this.type == null || typeDeclaration.type == null) {
            return false;
        }
        if (this.isWildcard != typeDeclaration.isWildcard) {
            return false;
        }
        if (!this.type.equals(typeDeclaration.type)) {
            return false;
        }
        if (this.genericTypes.length != 0 && typeDeclaration.genericTypes.length != 0) {
            if (this.genericTypes.length != typeDeclaration.genericTypes.length) {
                return false;
            }
            for (int i = 0; i < this.genericTypes.length; ++i) {
                if (!this.genericTypes[i].match(typeDeclaration.genericTypes[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public final String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        String s = b ? ((this.type == null) ? this.typePath : MPLType.getName(this.type)) : this.typeName;
        final int index = s.indexOf(91);
        String substring = "";
        if (index != -1) {
            substring = s.substring(index);
            s = s.substring(0, index);
        }
        if (this.type == null) {
            s = "??" + s + "??";
        }
        String string = "";
        if (this.cast != null && !b) {
            string = string + "(" + this.cast.toString(b) + ") ";
        }
        String s2;
        if (this.isWildcard) {
            if (s.length() == 0 || this.type == Object.class) {
                s2 = string + "?";
            }
            else {
                s2 = string + "? extends " + s;
            }
        }
        else if (this.variableName != null) {
            if (s.length() == 0 || this.type == Object.class) {
                s2 = string + this.variableName;
            }
            else {
                s2 = string + this.variableName + " extends " + s;
            }
        }
        else {
            s2 = string + s;
        }
        if (this.genericTypes.length > 0) {
            String str = s2 + "<";
            int n = 1;
            for (final TypeDeclaration typeDeclaration : this.genericTypes) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    str += ", ";
                }
                str += typeDeclaration.toString(b);
            }
            s2 = str + ">";
        }
        return s2 + substring;
    }
    
    @Override
    public boolean isResolved() {
        if (this.type == null) {
            return false;
        }
        if (this.cast != null && !this.cast.isResolved()) {
            return false;
        }
        for (int i = 0; i < this.genericTypes.length; ++i) {
            if (!this.genericTypes[i].isResolved()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isBuiltin() {
        return isBuiltin(this.type);
    }
    
    private static boolean isBuiltin(final Class<?> clazz) {
        if (clazz != null) {
            if (clazz.isPrimitive()) {
                return true;
            }
            if (clazz.isArray()) {
                return isBuiltin(clazz.getComponentType());
            }
            if (clazz.getName().startsWith("java.lang.")) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String str) {
        sb.append(str).append("Type {\n");
        sb.append(str).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(str).append("  postfix=").append(this.getPostfix()).append('\n');
        sb.append(str).append("  typeName=").append(this.typeName).append('\n');
        sb.append(str).append("  typePath=").append(this.typePath).append('\n');
        sb.append(str).append("  type=").append(this.type).append('\n');
        sb.append(str).append("  isWildcard=").append(this.isWildcard).append('\n');
        final TypeDeclaration[] genericTypes = this.genericTypes;
        for (int length = genericTypes.length, i = 0; i < length; ++i) {
            genericTypes[i].debugString(sb, str + "  ");
        }
        sb.append(str).append("}\n");
    }
    
    public static TypeDeclaration fromClass(final Class<?> clazz) {
        return Resolver.getMeta(clazz).typeDec;
    }
    
    public static TypeDeclaration createGeneric(final Class<?> clazz, final Class<?>... array) {
        final TypeDeclaration[] array2 = new TypeDeclaration[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = fromClass(array[i]);
        }
        return createGeneric(clazz, array2);
    }
    
    public static TypeDeclaration createGeneric(final Class<?> clazz, final TypeDeclaration... genericTypes) {
        return fromClass(clazz).setGenericTypes(genericTypes);
    }
    
    public static TypeDeclaration createArray(final Class<?> clazz) {
        return fromClass(MountiplexUtil.getArrayType(clazz));
    }
    
    public static TypeDeclaration createArray(final TypeDeclaration typeDeclaration) {
        return createArray(typeDeclaration.type).setGenericTypes(typeDeclaration.genericTypes);
    }
    
    public static TypeDeclaration fromType(final ClassResolver classResolver, final Type type) {
        return new TypeDeclaration(classResolver, type);
    }
    
    public static TypeDeclaration fromType(final Type type) {
        if (type instanceof Class) {
            return fromClass((Class<?>)type);
        }
        return new TypeDeclaration(ClassResolver.DEFAULT, type);
    }
    
    public static TypeDeclaration parse(final StringBuffer stringBuffer) {
        return new TypeDeclaration(ClassResolver.DEFAULT, stringBuffer);
    }
    
    public static TypeDeclaration parse(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        return new TypeDeclaration(classResolver, stringBuffer);
    }
    
    public static TypeDeclaration parse(final String s) {
        return new TypeDeclaration(ClassResolver.DEFAULT, StringBuffer.of(s));
    }
    
    public static TypeDeclaration parse(final ClassResolver classResolver, final String s) {
        return new TypeDeclaration(classResolver, StringBuffer.of(s));
    }
    
    static {
        INVALID = new TypeDeclaration(ClassResolver.DEFAULT, (Type)null);
        OBJECT = new TypeDeclaration(ClassResolver.DEFAULT, Object.class);
        ENUM = new TypeDeclaration(ClassResolver.DEFAULT, Enum.class);
        ANY = parse("?");
    }
}
