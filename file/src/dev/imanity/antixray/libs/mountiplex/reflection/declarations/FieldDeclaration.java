// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastConvertedField;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastField;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.MethodBodyBuilder;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.GenericSignatureFormatError;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Type;
import java.lang.reflect.Field;

public class FieldDeclaration extends Declaration
{
    public final ModifierDeclaration modifiers;
    public final NameDeclaration name;
    public final TypeDeclaration type;
    public boolean isEnum;
    public Field field;
    
    public FieldDeclaration(final ClassResolver classResolver, final Enum<?> enum1) {
        super(classResolver);
        this.isEnum = true;
        this.field = null;
        this.modifiers = new ModifierDeclaration(classResolver, 25);
        this.name = new NameDeclaration(classResolver, enum1.name(), null);
        this.type = TypeDeclaration.fromType(classResolver, enum1.getClass());
    }
    
    public FieldDeclaration(final ClassResolver classResolver, final Field field) {
        super(classResolver);
        final String name = MPLType.getName(field);
        String resolveFieldAlias = Resolver.resolveFieldAlias(field, name);
        if (name.equals(resolveFieldAlias)) {
            resolveFieldAlias = null;
        }
        TypeDeclaration type;
        try {
            type = TypeDeclaration.fromType(classResolver, field.getGenericType());
        }
        catch (final GenericSignatureFormatError | TypeNotPresentException | MalformedParameterizedTypeException ex) {
            type = TypeDeclaration.fromType(classResolver, field.getType());
        }
        this.isEnum = field.isEnumConstant();
        this.field = field;
        this.modifiers = new ModifierDeclaration(classResolver, field.getModifiers());
        this.type = type;
        this.name = new NameDeclaration(classResolver, name, resolveFieldAlias);
    }
    
    public FieldDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public FieldDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver, stringBuffer);
        this.getParserPostfix().trimWhitespace(0);
        if (this.getPostfix().startsWith("enum ")) {
            this.getParserPostfix().trimWhitespace(5);
            this.setPostfix(this.getPostfix().prepend("public static final "));
            this.isEnum = true;
            this.modifiers = this.nextModifier();
            this.type = this.nextType();
            this.name = this.nextName();
        }
        else {
            this.isEnum = false;
            this.field = null;
            this.modifiers = this.nextModifier();
            this.type = this.nextType();
            this.name = this.nextName();
        }
    }
    
    private FieldDeclaration(final FieldDeclaration fieldDeclaration, final NameDeclaration name) {
        super(fieldDeclaration.getResolver());
        this.modifiers = fieldDeclaration.modifiers;
        this.name = name;
        this.type = fieldDeclaration.type;
        this.isEnum = fieldDeclaration.isEnum;
        this.field = fieldDeclaration.field;
    }
    
    public final void copyFieldFrom(final FieldDeclaration fieldDeclaration) {
        if (this != fieldDeclaration) {
            this.field = fieldDeclaration.field;
            this.isEnum = fieldDeclaration.isEnum;
        }
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof FieldDeclaration)) {
            return 0.0;
        }
        final FieldDeclaration fieldDeclaration = (FieldDeclaration)declaration;
        return 0.1 * this.modifiers.similarity(fieldDeclaration.modifiers) + 0.3 * this.name.similarity(fieldDeclaration.name) + 0.6 * this.type.similarity(fieldDeclaration.type);
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        if (declaration instanceof FieldDeclaration) {
            final FieldDeclaration fieldDeclaration = (FieldDeclaration)declaration;
            return (this.isEnum == fieldDeclaration.isEnum || this.modifiers.match(fieldDeclaration.modifiers)) && this.name.match(fieldDeclaration.name) && this.type.match(fieldDeclaration.type);
        }
        return false;
    }
    
    public boolean matchSignature(final Declaration declaration) {
        if (declaration instanceof FieldDeclaration) {
            final FieldDeclaration fieldDeclaration = (FieldDeclaration)declaration;
            return (this.isEnum == fieldDeclaration.isEnum || this.modifiers.match(fieldDeclaration.modifiers)) && this.type.match(fieldDeclaration.type);
        }
        return false;
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        if (this.isEnum) {
            return "enum " + this.type.toString(b) + " " + this.name.toString(b);
        }
        final String string = this.modifiers.toString(b);
        final String string2 = this.type.toString(b);
        final String string3 = this.name.toString(b);
        if (string.length() > 0) {
            return string + " " + string2 + " " + string3 + ";";
        }
        return string2 + " " + string3 + ";";
    }
    
    @Override
    public boolean isResolved() {
        return this.modifiers.isResolved() && this.type.isResolved() && this.name.isResolved();
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String str) {
        sb.append(str).append("Field {\n");
        sb.append(str).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(str).append("  postfix=").append(this.getPostfix()).append('\n');
        this.modifiers.debugString(sb, str + "  ");
        this.type.debugString(sb, str + "  ");
        this.name.debugString(sb, str + "  ");
        sb.append(str).append("}\n");
    }
    
    @Override
    public void modifyBodyRequirement(final Requirement requirement, final StringBuilder sb, final String s, final String s2, final int start, final int n) {
        TypeDeclaration typeDeclaration = this.type;
        if (typeDeclaration.cast != null) {
            typeDeclaration = typeDeclaration.cast;
        }
        int n2 = -1;
        int i = n;
        while (i < sb.length()) {
            final char char1 = sb.charAt();
            if (char1 == ' ') {
                ++i;
            }
            else {
                if (char1 == '=' && (i + 1 >= sb.length() || sb.charAt() != '=')) {
                    for (n2 = i + 1; n2 < sb.length() && sb.charAt() == ' '; ++n2) {}
                    break;
                }
                break;
            }
        }
        final Class<?> clazz = (this.field == null) ? null : this.field.getDeclaringClass();
        int modifiers = 0;
        if (clazz != null && this.type.cast == null && Resolver.isPublic(clazz)) {
            modifiers = this.field.getModifiers();
        }
        if (n2 != -1) {
            if (Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers)) {
                final MethodBodyBuilder methodBodyBuilder = new MethodBodyBuilder();
                if (Modifier.isStatic(modifiers)) {
                    methodBodyBuilder.appendAccessibleTypeName(clazz);
                }
                else {
                    methodBodyBuilder.append(s);
                }
                methodBodyBuilder.append('.');
                methodBodyBuilder.append("MPL_NOREMAP$");
                methodBodyBuilder.append(MPLType.getName(this.field));
                sb.replace(start, n, methodBodyBuilder.toString());
                return;
            }
            int j = n2;
            int n3 = 0;
            while (j < sb.length()) {
                final char char2 = sb.charAt();
                if (char2 == ';') {
                    break;
                }
                if (char2 == '(') {
                    ++n3;
                }
                else if (char2 == ')' && --n3 < 0) {
                    break;
                }
                ++j;
            }
            final String substring = sb.substring();
            final MethodBodyBuilder methodBodyBuilder2 = new MethodBodyBuilder();
            methodBodyBuilder2.append("this.").append(s2).append(".set");
            if (typeDeclaration.isPrimitive) {
                methodBodyBuilder2.append(BoxedType.getBoxedType(typeDeclaration.type).getSimpleName());
            }
            methodBodyBuilder2.append('(');
            methodBodyBuilder2.append(s);
            methodBodyBuilder2.append(", ");
            methodBodyBuilder2.append(substring);
            methodBodyBuilder2.append(')');
            requirement.setProperty("generateFastField");
            sb.replace(start, j, methodBodyBuilder2.toString());
        }
        else {
            final MethodBodyBuilder methodBodyBuilder3 = new MethodBodyBuilder();
            if (Modifier.isPublic(modifiers)) {
                if (Modifier.isStatic(modifiers)) {
                    methodBodyBuilder3.append(ReflectionUtil.getAccessibleTypeName(clazz));
                }
                else {
                    methodBodyBuilder3.append(s);
                }
                methodBodyBuilder3.append('.');
                methodBodyBuilder3.append("MPL_NOREMAP$");
                methodBodyBuilder3.append(MPLType.getName(this.field));
            }
            else {
                if (typeDeclaration.isPrimitive) {
                    methodBodyBuilder3.append("this.").append(s2).append(".get");
                    methodBodyBuilder3.append(BoxedType.getBoxedType(typeDeclaration.type).getSimpleName());
                }
                else {
                    methodBodyBuilder3.appendAccessibleTypeCast(typeDeclaration.type);
                    methodBodyBuilder3.append("this.").append(s2).append(".get");
                }
                methodBodyBuilder3.append('(');
                methodBodyBuilder3.append(s);
                methodBodyBuilder3.append(')');
                requirement.setProperty("generateFastField");
            }
            sb.replace(start, n, methodBodyBuilder3.toString());
        }
    }
    
    @Override
    public void addAsRequirement(final ExtendedClassWriter<?> extendedClassWriter, final Requirement requirement, final String s) {
        if (!requirement.hasProperty("generateFastField")) {
            return;
        }
        if (this.type.cast != null) {
            final DuplexConverter<Object, Object> duplex = Conversion.findDuplex(this.type, this.type.cast);
            if (duplex == null) {
                throw new RuntimeException("Failed to find converter from " + this.type.toString(true) + " <> " + this.type.cast.toString(true));
            }
            final FastField fastField = new FastField();
            fastField.init(this.field);
            extendedClassWriter.visitSingletonField(s, FastConvertedField.class, new FastConvertedField(fastField, duplex));
        }
        else {
            final FastField fastField2 = new FastField();
            fastField2.init(this.field);
            extendedClassWriter.visitSingletonField(s, FastField.class, fastField2);
        }
    }
    
    @Override
    public FieldDeclaration discover() {
        if (!this.isValid() || !this.isResolved()) {
            return null;
        }
        try {
            final FieldDeclaration resolveName = this.resolveName();
            if (resolveName.field == null) {
                final FieldDeclaration fieldDeclaration = new FieldDeclaration(this.getResolver(), MPLType.getDeclaredField(this.getResolver().getDeclaredClass(), resolveName.name.value()));
                if (!resolveName.match(fieldDeclaration)) {
                    return null;
                }
                if (this.modifiers.isPublic() && !Modifier.isPublic(fieldDeclaration.field.getModifiers())) {
                    return null;
                }
                resolveName.copyFieldFrom(fieldDeclaration);
            }
            else if (this.modifiers.isPublic() && !Modifier.isPublic(resolveName.field.getModifiers())) {
                return null;
            }
            this.copyFieldFrom(resolveName);
            return resolveName;
        }
        catch (final NoSuchFieldException ex) {}
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to discover field", thrown);
        }
        return null;
    }
    
    @Override
    public void discoverAlternatives() {
        final Class<?> declaredClass = this.getResolver().getDeclaredClass();
        if (declaredClass == null) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration could not be found inside: ??" + this.getResolver().getDeclaredClassName() + "??");
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration: " + this.toString());
            return;
        }
        FieldDeclaration[] array;
        if (this.modifiers.isStatic()) {
            array = ReflectionUtil.getAllStaticFields(declaredClass).map(field -> new FieldDeclaration(this.getResolver(), field)).toArray(FieldDeclaration[]::new);
        }
        else {
            array = ReflectionUtil.getAllNonStaticFields(declaredClass).map(field2 -> new FieldDeclaration(this.getResolver(), field2)).toArray(FieldDeclaration[]::new);
        }
        Declaration.sortSimilarity(this, array);
        FieldLCSResolver.logAlternatives("field", array, this.resolveName(), true);
    }
    
    public FieldDeclaration resolveName() {
        if (!this.isResolved()) {
            return this;
        }
        final Remapping.FieldRemapping find = this.getResolver().getRemappings().find(this);
        if (find != null) {
            final FieldDeclaration fieldDeclaration = new FieldDeclaration(this, this.name.rename(find.declaration.name));
            fieldDeclaration.field = find.field;
            fieldDeclaration.isEnum = find.field.isEnumConstant();
            return fieldDeclaration;
        }
        final String resolveFieldName = Resolver.resolveFieldName(this.getResolver().getDeclaredClass(), this.name.value());
        if (resolveFieldName != null && !resolveFieldName.equals(this.name.value())) {
            return new FieldDeclaration(this, this.name.rename(resolveFieldName));
        }
        return this;
    }
    
    protected String getAccessedName() {
        return (this.field != null) ? MPLType.getName(this.field) : this.name.value();
    }
}
