// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.util.HashMap;

public class ModifierDeclaration extends Declaration
{
    private static final HashMap<StringBuffer, Integer> _tokens;
    private static final int _token_mask = 207;
    private final int _modifiers;
    private final String _modifiersStr;
    private final boolean _final;
    private final boolean _unknown;
    private final boolean _optional;
    private final boolean _readonly;
    private final boolean _rawtype;
    
    public ModifierDeclaration(final ClassResolver classResolver, final int n) {
        super(classResolver);
        this._modifiers = n;
        this._modifiersStr = Modifier.toString(n);
        this._final = Modifier.isFinal(this._modifiers);
        this._unknown = false;
        this._optional = false;
        this._rawtype = false;
        this._readonly = false;
    }
    
    @Deprecated
    public ModifierDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public ModifierDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver, stringBuffer);
        if (stringBuffer == null) {
            this._modifiers = 0;
            this._modifiersStr = "";
            this._final = false;
            this._unknown = false;
            this._optional = false;
            this._rawtype = false;
            this._readonly = false;
            this.setInvalid();
            return;
        }
        boolean unknown = false;
        boolean optional = false;
        boolean readonly = false;
        boolean rawtype = false;
        int n = 0;
        String modifiersStr = "";
        StringBuffer substring = stringBuffer;
        int n2;
        while (true) {
            for (n2 = 0; n2 < substring.length() && substring.charAt(n2) == ' '; ++n2) {}
            final int index = substring.indexOf(' ', n2);
            if (index == -1) {
                break;
            }
            final StringBuffer substring2 = substring.substring(n2, index);
            final Integer n3 = ModifierDeclaration._tokens.get(substring2);
            boolean b = false;
            if (n3 != null) {
                n |= n3;
                b = true;
            }
            else if (substring2.equals("unknown")) {
                unknown = true;
                b = true;
            }
            else if (substring2.equals("optional")) {
                optional = true;
                b = true;
            }
            else if (substring2.equals("readonly")) {
                readonly = true;
                b = true;
            }
            else if (substring2.equals("rawtype")) {
                rawtype = true;
                b = true;
            }
            if (!b) {
                break;
            }
            if (n3 != null) {
                if (modifiersStr.length() > 0) {
                    modifiersStr += " ";
                }
                modifiersStr += (Object)substring2;
            }
            substring = substring.substring(index + 1);
        }
        final StringBuffer substring3 = substring.substring(n2);
        this._final = Modifier.isFinal(n);
        this._modifiers = n;
        this._modifiersStr = modifiersStr;
        this._unknown = unknown;
        this._optional = optional;
        this._readonly = readonly;
        this._rawtype = rawtype;
        this.setPostfix(substring3);
    }
    
    public final boolean isUnknown() {
        return this._unknown;
    }
    
    public final boolean isReadonly() {
        return this._readonly;
    }
    
    public final boolean isOptional() {
        return this._optional;
    }
    
    public final boolean isRawtype() {
        return this._rawtype;
    }
    
    public final boolean isFinal() {
        return this._final;
    }
    
    public final boolean isStatic() {
        return Modifier.isStatic(this._modifiers);
    }
    
    public final boolean isPublic() {
        return Modifier.isPublic(this._modifiers);
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof ModifierDeclaration)) {
            return 0.0;
        }
        final ModifierDeclaration modifierDeclaration = (ModifierDeclaration)declaration;
        final int n = (modifierDeclaration._modifiers | this._modifiers) & 0xCF;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < 31; ++i) {
            final int n4 = 1 << i;
            if ((n & n4) != 0x0) {
                ++n2;
                if ((modifierDeclaration._modifiers & n4) == (this._modifiers & n4)) {
                    ++n3;
                }
            }
        }
        if (n2 == 0) {
            return 1.0;
        }
        return n3 / (double)n2;
    }
    
    @Override
    public final boolean match(final Declaration declaration) {
        return declaration instanceof ModifierDeclaration && (this._modifiers & 0xCF) == (((ModifierDeclaration)declaration)._modifiers & 0xCF);
    }
    
    public int getProtectionLevel() {
        if (Modifier.isPublic(this._modifiers)) {
            return 0;
        }
        if (Modifier.isProtected(this._modifiers)) {
            return 1;
        }
        if (!Modifier.isPrivate(this._modifiers)) {
            return 2;
        }
        return 3;
    }
    
    @Override
    public final String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        return this._modifiersStr;
    }
    
    @Override
    public boolean isResolved() {
        return true;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
        sb.append(s).append("Modifier {\n");
        sb.append(s).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(s).append("  postfix=").append(this.getPostfix()).append('\n');
        sb.append(s).append("  modifiersStr=").append(this._modifiersStr).append('\n');
        sb.append(s).append("  modifiers=").append(this._modifiers).append('\n');
        sb.append(s).append("}\n");
    }
    
    static {
        _tokens = new HashMap<StringBuffer, Integer>();
        for (final int n : new int[] { 1024, 16, 256, 2, 4, 1, 8, 2048, 32, 128, 64 }) {
            ModifierDeclaration._tokens.put(StringBuffer.of(Modifier.toString(n)), n);
        }
    }
}
