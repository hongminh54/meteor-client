// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.stackmap;

import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;

public class TypedBlock extends BasicBlock
{
    public int stackTop;
    public int numLocals;
    public TypeData[] localsTypes;
    public TypeData[] stackTypes;
    
    public static TypedBlock[] makeBlocks(final MethodInfo methodInfo, final CodeAttribute codeAttribute, final boolean b) {
        final TypedBlock[] array = (TypedBlock[])new Maker().make(methodInfo);
        if (b && array.length < 2 && (array.length == 0 || array[0].incoming == 0)) {
            return null;
        }
        array[0].initFirstBlock(codeAttribute.getMaxStack(), codeAttribute.getMaxLocals(), methodInfo.getConstPool().getClassName(), methodInfo.getDescriptor(), (methodInfo.getAccessFlags() & 0x8) != 0x0, methodInfo.isConstructor());
        return array;
    }
    
    protected TypedBlock(final int n) {
        super(n);
        this.localsTypes = null;
    }
    
    @Override
    protected void toString2(final StringBuilder sb) {
        super.toString2(sb);
        sb.append(",\n stack={");
        this.printTypes(sb, this.stackTop, this.stackTypes);
        sb.append("}, locals={");
        this.printTypes(sb, this.numLocals, this.localsTypes);
        sb.append('}');
    }
    
    private void printTypes(final StringBuilder sb, final int n, final TypeData[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            final TypeData typeData = array[i];
            sb.append((typeData == null) ? "<>" : typeData.toString());
        }
    }
    
    public boolean alreadySet() {
        return this.localsTypes != null;
    }
    
    public void setStackMap(final int stackTop, final TypeData[] stackTypes, final int numLocals, final TypeData[] localsTypes) {
        this.stackTop = stackTop;
        this.stackTypes = stackTypes;
        this.numLocals = numLocals;
        this.localsTypes = localsTypes;
    }
    
    public void resetNumLocals() {
        if (this.localsTypes != null) {
            int length;
            for (length = this.localsTypes.length; length > 0 && this.localsTypes[length - 1].isBasicType() == TypeTag.TOP && (length <= 1 || !this.localsTypes[length - 2].is2WordType()); --length) {}
            this.numLocals = length;
        }
    }
    
    void initFirstBlock(final int n, final int n2, final String s, final String s2, final boolean b, final boolean b2) {
        if (s2.charAt(0) != '(') {
            throw new BadBytecode("no method descriptor: " + s2);
        }
        this.stackTop = 0;
        this.stackTypes = TypeData.make(n);
        final TypeData[] make = TypeData.make(n2);
        if (b2) {
            make[0] = new TypeData.UninitThis(s);
        }
        else if (!b) {
            make[0] = new TypeData.ClassName(s);
        }
        int numLocals = b ? -1 : 0;
        int descToTag = 1;
        try {
            while ((descToTag = descToTag(s2, descToTag, ++numLocals, make)) > 0) {
                if (make[numLocals].is2WordType()) {
                    make[++numLocals] = TypeTag.TOP;
                }
            }
        }
        catch (final StringIndexOutOfBoundsException ex) {
            throw new BadBytecode("bad method descriptor: " + s2);
        }
        this.numLocals = numLocals;
        this.localsTypes = make;
    }
    
    private static int descToTag(final String str, int index, final int n, final TypeData[] array) {
        final int n2 = index;
        int n3 = 0;
        char c = str.charAt(index);
        if (c == ')') {
            return 0;
        }
        while (c == '[') {
            ++n3;
            c = str.charAt(++index);
        }
        if (c == 'L') {
            int index2 = str.indexOf(59, ++index);
            if (n3 > 0) {
                array[n] = new TypeData.ClassName(str.substring(n2, ++index2));
            }
            else {
                array[n] = new TypeData.ClassName(str.substring(n2 + 1, ++index2 - 1).replace('/', '.'));
            }
            return index2;
        }
        if (n3 > 0) {
            array[n] = new TypeData.ClassName(str.substring(n2, ++index));
            return index;
        }
        final TypeData primitiveTag = toPrimitiveTag(c);
        if (primitiveTag == null) {
            throw new BadBytecode("bad method descriptor: " + str);
        }
        array[n] = primitiveTag;
        return index + 1;
    }
    
    private static TypeData toPrimitiveTag(final char c) {
        switch (c) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z': {
                return TypeTag.INTEGER;
            }
            case 'J': {
                return TypeTag.LONG;
            }
            case 'F': {
                return TypeTag.FLOAT;
            }
            case 'D': {
                return TypeTag.DOUBLE;
            }
            default: {
                return null;
            }
        }
    }
    
    public static String getRetType(final String s) {
        final int index = s.indexOf(41);
        if (index < 0) {
            return "java.lang.Object";
        }
        final char char1 = s.charAt(index + 1);
        if (char1 == '[') {
            return s.substring(index + 1);
        }
        if (char1 == 'L') {
            return s.substring(index + 2, s.length() - 1).replace('/', '.');
        }
        return "java.lang.Object";
    }
    
    public static class Maker extends BasicBlock.Maker
    {
        @Override
        protected BasicBlock makeBlock(final int n) {
            return new TypedBlock(n);
        }
        
        @Override
        protected BasicBlock[] makeArray(final int n) {
            return new TypedBlock[n];
        }
    }
}
