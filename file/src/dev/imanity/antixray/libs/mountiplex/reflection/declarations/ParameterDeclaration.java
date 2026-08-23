// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.Type;

public class ParameterDeclaration extends Declaration
{
    public final TypeDeclaration type;
    public final NameDeclaration name;
    
    public ParameterDeclaration(final ClassResolver classResolver, final TypeDeclaration type, final NameDeclaration name) {
        super(classResolver);
        this.type = type;
        this.name = name;
    }
    
    public ParameterDeclaration(final ClassResolver classResolver, final Type type, final String s) {
        super(classResolver);
        this.type = TypeDeclaration.fromType(classResolver, type);
        this.name = new NameDeclaration(classResolver, s, null);
    }
    
    @Deprecated
    public ParameterDeclaration(final ClassResolver classResolver, final String s, final int n) {
        this(classResolver, StringBuffer.of(s), n);
    }
    
    public ParameterDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer, final int n) {
        super(classResolver, stringBuffer);
        this.type = this.nextType();
        this.name = this.nextName(n);
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof ParameterDeclaration)) {
            return 0.0;
        }
        return this.type.similarity(((ParameterDeclaration)declaration).type);
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        return declaration instanceof ParameterDeclaration && this.type.match(((ParameterDeclaration)declaration).type);
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        return this.type.toString(b) + " " + this.name.toString(b);
    }
    
    @Override
    public boolean isResolved() {
        return this.type.isResolved() && this.name.isResolved();
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
        sb.append(s).append("Parameter {\n");
        sb.append(s).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(s).append("  postfix=").append(this.getPostfix()).append('\n');
        this.name.debugString(sb, s + "  ");
        this.type.debugString(sb, s + "  ");
        sb.append(s).append("}\n");
    }
}
