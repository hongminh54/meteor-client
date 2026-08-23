// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

import java.util.Arrays;

public final class ConstantDynamic
{
    private final String name;
    private final String descriptor;
    private final Handle bootstrapMethod;
    private final Object[] bootstrapMethodArguments;
    
    public ConstantDynamic(final String name, final String descriptor, final Handle bootstrapMethod, final Object... bootstrapMethodArguments) {
        this.name = name;
        this.descriptor = descriptor;
        this.bootstrapMethod = bootstrapMethod;
        this.bootstrapMethodArguments = bootstrapMethodArguments;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescriptor() {
        return this.descriptor;
    }
    
    public Handle getBootstrapMethod() {
        return this.bootstrapMethod;
    }
    
    public int getBootstrapMethodArgumentCount() {
        return this.bootstrapMethodArguments.length;
    }
    
    public Object getBootstrapMethodArgument(final int n) {
        return this.bootstrapMethodArguments[n];
    }
    
    Object[] getBootstrapMethodArgumentsUnsafe() {
        return this.bootstrapMethodArguments;
    }
    
    public int getSize() {
        final char char1 = this.descriptor.charAt(0);
        return (char1 == 'J' || char1 == 'D') ? 2 : 1;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ConstantDynamic)) {
            return false;
        }
        final ConstantDynamic constantDynamic = (ConstantDynamic)o;
        return this.name.equals(constantDynamic.name) && this.descriptor.equals(constantDynamic.descriptor) && this.bootstrapMethod.equals(constantDynamic.bootstrapMethod) && Arrays.equals(this.bootstrapMethodArguments, constantDynamic.bootstrapMethodArguments);
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() ^ Integer.rotateLeft(this.descriptor.hashCode(), 8) ^ Integer.rotateLeft(this.bootstrapMethod.hashCode(), 16) ^ Integer.rotateLeft(Arrays.hashCode(this.bootstrapMethodArguments), 24);
    }
    
    @Override
    public String toString() {
        return stringConcat$0(this.name, this.descriptor, this.bootstrapMethod, Arrays.toString(this.bootstrapMethodArguments));
    }
    
    private static /* synthetic */ String stringConcat$0(final String str, final String str2, final Handle obj, final String str3) {
        return str + " : " + str2 + " " + obj + " " + str3;
    }
}
