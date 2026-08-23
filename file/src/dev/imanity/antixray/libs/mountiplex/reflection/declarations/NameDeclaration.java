// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.ParserStringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;

public class NameDeclaration extends Declaration
{
    private final String _name;
    private final String _alias;
    
    public NameDeclaration(final ClassResolver classResolver, final String name, final String alias) {
        super(classResolver);
        this._name = name;
        this._alias = alias;
    }
    
    @Deprecated
    public NameDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    @Deprecated
    public NameDeclaration(final ClassResolver classResolver, final String s, final int n) {
        this(classResolver, StringBuffer.of(s), n);
    }
    
    public NameDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        this(classResolver, stringBuffer, -1);
    }
    
    public NameDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer, final int i) {
        super(classResolver, stringBuffer);
        if (stringBuffer == null) {
            this._name = "";
            this._alias = null;
            this.setInvalid();
            return;
        }
        int n = -1;
        StringBuffer stringBuffer2 = null;
        StringBuffer substring = null;
        for (int j = 0; j < stringBuffer.length(); ++j) {
            final char char1 = stringBuffer.charAt(j);
            if (n != -1 || char1 != ' ') {
                boolean b;
                if (char1 == '<') {
                    b = (stringBuffer.substringEquals(j, j + 6, "<init>") || stringBuffer.substringEquals(j, j + 16, "<record_changer>"));
                }
                else if (char1 == '>') {
                    b = (stringBuffer.substringEquals(j - 5, j + 1, "<init>") || stringBuffer.substringEquals(j - 15, j + 1, "<record_changer>"));
                }
                else {
                    b = !MountiplexUtil.containsChar(char1, ParserStringBuffer.INVALID_NAME_CHARACTERS);
                }
                if (n == -1) {
                    if (!b) {
                        break;
                    }
                    n = j;
                }
                if (!b && stringBuffer2 == null) {
                    stringBuffer2 = stringBuffer.substring(n, j);
                }
                if (stringBuffer2 != null && char1 != ' ') {
                    this.setPostfix(stringBuffer.substring(j));
                    break;
                }
            }
        }
        if (n == -1) {
            if (i != -1) {
                this._name = "arg" + i;
                this._alias = null;
            }
            else {
                this._name = "";
                this._alias = null;
                this.setInvalid();
            }
            return;
        }
        if (stringBuffer2 == null) {
            stringBuffer2 = stringBuffer.substring(n);
            this.setPostfix(StringBuffer.EMPTY);
        }
        final int index = stringBuffer2.indexOf(':');
        if (index != -1) {
            substring = stringBuffer2.substring(0, index);
            stringBuffer2 = stringBuffer2.substring(index + 1);
        }
        this._name = ((stringBuffer2 == null) ? null : stringBuffer2.toString());
        this._alias = ((substring == null) ? null : substring.toString());
    }
    
    public final String value() {
        return this._name;
    }
    
    public final String alias() {
        return this._alias;
    }
    
    public final String real() {
        return (this._alias != null) ? this._alias : this._name;
    }
    
    public final String firstReal() {
        if (this._alias == null) {
            return this._name;
        }
        final int index = this._alias.indexOf(58);
        if (index != -1) {
            return this._alias.substring(0, index);
        }
        return this._alias;
    }
    
    public final boolean hasAlias() {
        return this._alias != null;
    }
    
    public final boolean isObfuscated() {
        return this._name.length() <= 2;
    }
    
    public final boolean isAliasOnly() {
        if (!this.hasAlias()) {
            return false;
        }
        for (int i = 0; i < this._name.length(); ++i) {
            if (this._name.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof NameDeclaration)) {
            return 0.0;
        }
        final NameDeclaration nameDeclaration = (NameDeclaration)declaration;
        if (nameDeclaration._name.equals(this._name)) {
            return 1.0;
        }
        if (nameDeclaration.isObfuscated() && this.isObfuscated()) {
            return 0.9;
        }
        if (nameDeclaration.isObfuscated() || this.isObfuscated()) {
            return 0.1;
        }
        return MountiplexUtil.similarity(nameDeclaration._name, this._name);
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        if (!(declaration instanceof NameDeclaration)) {
            return false;
        }
        final NameDeclaration nameDeclaration = (NameDeclaration)declaration;
        if (this.isAliasOnly()) {
            return nameDeclaration.matchAlias(this._alias);
        }
        if (nameDeclaration.isAliasOnly()) {
            return this.matchAlias(nameDeclaration._alias);
        }
        return nameDeclaration._name.equals(this._name);
    }
    
    private boolean matchAlias(final String anObject) {
        String s = this.real();
        final int index = s.indexOf(58);
        if (index != -1) {
            s = s.substring(0, index);
        }
        return s.equals(anObject);
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        if (this._alias == null || b) {
            return this._name;
        }
        return this._alias + ":" + this._name;
    }
    
    @Override
    public boolean isResolved() {
        return true;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
        sb.append(s).append("Name {\n");
        sb.append(s).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(s).append("  postfix=").append(this.getPostfix()).append('\n');
        sb.append(s).append("  name=").append(this._name).append('\n');
        sb.append(s).append("  alias=").append(this._alias).append('\n');
        sb.append(s).append("}\n");
    }
    
    public NameDeclaration rename(final NameDeclaration nameDeclaration) {
        if (!nameDeclaration.hasAlias()) {
            return this.rename(nameDeclaration.value());
        }
        final ArrayList elements = new ArrayList((Collection<? extends E>)Arrays.asList(nameDeclaration.alias().split(":")));
        if (((String)elements.get(0)).equals(this.value())) {
            elements.remove(0);
        }
        if (elements.isEmpty()) {
            return this.rename(nameDeclaration.value());
        }
        if (nameDeclaration.value().equals(this.value())) {
            return this;
        }
        elements.add(0, this.value());
        return new NameDeclaration(this.getResolver(), nameDeclaration.value(), String.join(":", elements));
    }
    
    public NameDeclaration rename(final String s) {
        if (s.equals(this.value())) {
            return this;
        }
        if (this.hasAlias()) {
            return new NameDeclaration(this.getResolver(), s, this.alias() + ":" + this.value());
        }
        return new NameDeclaration(this.getResolver(), s, this.value());
    }
}
