// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.Type;
import java.lang.reflect.Constructor;

public class ConstructorDeclaration extends Declaration
{
    public final ModifierDeclaration modifiers;
    public final TypeDeclaration type;
    public final ParameterListDeclaration parameters;
    public Constructor<?> constructor;
    
    public ConstructorDeclaration(final ClassResolver classResolver, final Constructor<?> constructor) {
        super(classResolver);
        this.constructor = constructor;
        this.modifiers = new ModifierDeclaration(classResolver, constructor.getModifiers());
        this.type = TypeDeclaration.fromType(classResolver, constructor.getDeclaringClass());
        this.parameters = new ParameterListDeclaration(classResolver, constructor.getGenericParameterTypes());
    }
    
    @Deprecated
    public ConstructorDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public ConstructorDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver, stringBuffer);
        this.constructor = null;
        this.modifiers = this.nextModifier();
        this.type = this.nextType();
        this.parameters = this.nextParameterList();
    }
    
    public final String getName() {
        String string = "constr";
        final ParameterDeclaration[] parameters = this.parameters.parameters;
        for (int length = parameters.length, i = 0; i < length; ++i) {
            string = string + "_" + parameters[i].name.real();
        }
        return string;
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        if (declaration instanceof ConstructorDeclaration) {
            final ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration)declaration;
            return this.type.match(constructorDeclaration.type) && this.parameters.match(constructorDeclaration.parameters);
        }
        return false;
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        final String string = this.modifiers.toString(b);
        if (string.length() > 0) {
            return string + " " + this.type.toString(b) + this.parameters.toString(b) + ";";
        }
        return this.type.toString() + this.parameters.toString(b) + ";";
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof ConstructorDeclaration)) {
            return 0.0;
        }
        final ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration)declaration;
        return 0.1 * this.modifiers.similarity(constructorDeclaration.modifiers) + 0.9 * this.parameters.similarity(constructorDeclaration.parameters);
    }
    
    @Override
    public boolean isResolved() {
        return this.type.isResolved() && this.parameters.isResolved();
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String str) {
        sb.append(str).append("Constructor {\n");
        sb.append(str).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(str).append("  postfix=").append(this.getPostfix()).append('\n');
        this.modifiers.debugString(sb, str + "  ");
        this.type.debugString(sb, str + "  ");
        this.parameters.debugString(sb, str + "  ");
        sb.append(str).append("}\n");
    }
    
    @Override
    public ConstructorDeclaration discover() {
        if (!this.isValid() || !this.isResolved()) {
            return null;
        }
        try {
            final Constructor<?> declaredConstructor = this.getResolver().getDeclaredClass().getDeclaredConstructor(this.parameters.toParamArray());
            if (declaredConstructor != null) {
                this.constructor = declaredConstructor;
                return this;
            }
        }
        catch (final NoSuchMethodException | SecurityException ex) {}
        return null;
    }
}
