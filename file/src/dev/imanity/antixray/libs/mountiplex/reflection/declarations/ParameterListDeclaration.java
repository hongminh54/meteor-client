// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.function.Function;
import java.util.LinkedList;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.Type;

public class ParameterListDeclaration extends Declaration
{
    public final ParameterDeclaration[] parameters;
    
    public ParameterListDeclaration(final ClassResolver classResolver, final Type[] array) {
        super(classResolver);
        this.parameters = new ParameterDeclaration[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.parameters[i] = new ParameterDeclaration(classResolver, array[i], "arg" + i);
        }
    }
    
    public ParameterListDeclaration(final ClassResolver classResolver, final Declaration declaration) {
        this(classResolver, declaration.getPostfix());
    }
    
    @Deprecated
    public ParameterListDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public ParameterListDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver);
        if (stringBuffer == null) {
            this.parameters = new ParameterDeclaration[0];
            this.setInvalid();
            return;
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < stringBuffer.length(); ++i) {
            final char char1 = stringBuffer.charAt(i);
            if (n == 0 && char1 == '(') {
                this.setPostfix(stringBuffer.substring(i));
                n = 1;
            }
            else if (char1 == ')') {
                if (n2 != 0) {
                    n = 0;
                    break;
                }
                n2 = 1;
            }
            else if (char1 != ' ') {
                if (n2 != 0) {
                    this.setPostfix(stringBuffer.substring(i));
                    break;
                }
                break;
            }
        }
        if (n == 0) {
            this.parameters = new ParameterDeclaration[0];
            this.setInvalid();
            return;
        }
        if (n2 != 0) {
            this.parameters = new ParameterDeclaration[0];
            return;
        }
        final LinkedList<ParameterDeclaration> list = new LinkedList<ParameterDeclaration>();
        StringBuffer stringBuffer2 = this.getPostfix();
        do {
            this.setPostfix(stringBuffer2.substring(1));
            list.add(this.nextParameter(list.size()));
            stringBuffer2 = this.getPostfix();
        } while (stringBuffer2 != null && stringBuffer2.length() > 0 && stringBuffer2.charAt(0) == ',');
        if (stringBuffer2 != null && stringBuffer2.charAt(0) == ')') {
            this.setPostfix(stringBuffer2.substring(1));
        }
        this.parameters = list.toArray(new ParameterDeclaration[list.size()]);
    }
    
    private ParameterListDeclaration(final ParameterListDeclaration parameterListDeclaration, final Function<ParameterDeclaration, String> function) {
        super(parameterListDeclaration.getResolver());
        this.parameters = new ParameterDeclaration[parameterListDeclaration.parameters.length];
        for (int i = 0; i < this.parameters.length; ++i) {
            final ParameterDeclaration parameterDeclaration = parameterListDeclaration.parameters[i];
            this.parameters[i] = new ParameterDeclaration(this.getResolver(), parameterDeclaration.type, parameterDeclaration.name.rename(function.apply(parameterDeclaration)));
        }
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof ParameterListDeclaration)) {
            return 0.0;
        }
        final ParameterListDeclaration parameterListDeclaration = (ParameterListDeclaration)declaration;
        if (parameterListDeclaration.parameters.length == 0 && this.parameters.length == 0) {
            return 1.0;
        }
        if (parameterListDeclaration.parameters.length == this.parameters.length) {
            double n = 0.0;
            for (int i = 0; i < this.parameters.length; ++i) {
                n += this.parameters[i].similarity(parameterListDeclaration.parameters[i]);
            }
            return n / this.parameters.length;
        }
        final int min = Math.min(parameterListDeclaration.parameters.length, this.parameters.length);
        double n2 = 0.0;
        for (int j = 0; j < min; ++j) {
            n2 += this.parameters[j].similarity(parameterListDeclaration.parameters[j]);
        }
        return n2 / Math.max(parameterListDeclaration.parameters.length, this.parameters.length);
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        if (declaration instanceof ParameterListDeclaration) {
            final ParameterListDeclaration parameterListDeclaration = (ParameterListDeclaration)declaration;
            if (this.parameters.length == parameterListDeclaration.parameters.length) {
                for (int i = 0; i < this.parameters.length; ++i) {
                    if (!this.parameters[i].match(parameterListDeclaration.parameters[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        String str = "(";
        for (int i = 0; i < this.parameters.length; ++i) {
            if (i > 0) {
                str += ", ";
            }
            str += this.parameters[i].toString(b);
        }
        return str + ")";
    }
    
    @Override
    public boolean isResolved() {
        for (int i = 0; i < this.parameters.length; ++i) {
            if (!this.parameters[i].isResolved()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String str) {
        sb.append(str).append("ParameterList {\n");
        sb.append(str).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(str).append("  postfix=").append(this.getPostfix()).append('\n');
        final ParameterDeclaration[] parameters = this.parameters;
        for (int length = parameters.length, i = 0; i < length; ++i) {
            parameters[i].debugString(sb, str + "  ");
        }
        sb.append(str).append("}\n");
    }
    
    public Class<?>[] toParamArray() {
        final Class[] array = new Class[this.parameters.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.parameters[i].type.type;
        }
        return array;
    }
    
    public ParameterListDeclaration renameParameters(final Function<ParameterDeclaration, String> function) {
        return new ParameterListDeclaration(this, function);
    }
}
