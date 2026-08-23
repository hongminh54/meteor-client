// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.compiler.SymbolTable;
import dev.imanity.antixray.libs.javassist.compiler.ast.StringL;
import dev.imanity.antixray.libs.javassist.compiler.ast.DoubleConst;
import dev.imanity.antixray.libs.javassist.compiler.ast.IntConst;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.SignatureAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.AnnotationsAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.AccessFlag;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.util.Iterator;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.bytecode.AttributeInfo;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.FieldInfo;

public class CtField extends CtMember
{
    static final String javaLangString = "java.lang.String";
    protected FieldInfo fieldInfo;
    
    public CtField(final CtClass ctClass, final String s, final CtClass ctClass2) {
        this(Descriptor.of(ctClass), s, ctClass2);
    }
    
    public CtField(final CtField ctField, final CtClass ctClass) {
        this(ctField.fieldInfo.getDescriptor(), ctField.fieldInfo.getName(), ctClass);
        final FieldInfo fieldInfo = this.fieldInfo;
        fieldInfo.setAccessFlags(ctField.fieldInfo.getAccessFlags());
        final ConstPool constPool = fieldInfo.getConstPool();
        final Iterator<AttributeInfo> iterator = ctField.fieldInfo.getAttributes().iterator();
        while (iterator.hasNext()) {
            fieldInfo.addAttribute(iterator.next().copy(constPool, null));
        }
    }
    
    private CtField(final String s, final String s2, final CtClass ctClass) {
        super(ctClass);
        final ClassFile classFile2 = ctClass.getClassFile2();
        if (classFile2 == null) {
            throw new CannotCompileException("bad declaring class: " + ctClass.getName());
        }
        this.fieldInfo = new FieldInfo(classFile2.getConstPool(), s2, s);
    }
    
    CtField(final FieldInfo fieldInfo, final CtClass ctClass) {
        super(ctClass);
        this.fieldInfo = fieldInfo;
    }
    
    @Override
    public String toString() {
        return this.getDeclaringClass().getName() + "." + this.getName() + ":" + this.fieldInfo.getDescriptor();
    }
    
    @Override
    protected void extendToString(final StringBuilder sb) {
        sb.append(' ');
        sb.append(this.getName());
        sb.append(' ');
        sb.append(this.fieldInfo.getDescriptor());
    }
    
    protected ASTree getInitAST() {
        return null;
    }
    
    Initializer getInit() {
        final ASTree initAST = this.getInitAST();
        if (initAST == null) {
            return null;
        }
        return Initializer.byExpr(initAST);
    }
    
    public static CtField make(final String s, final CtClass ctClass) {
        final Javac javac = new Javac(ctClass);
        try {
            final CtMember compile = javac.compile(s);
            if (compile instanceof CtField) {
                return (CtField)compile;
            }
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        throw new CannotCompileException("not a field");
    }
    
    public FieldInfo getFieldInfo() {
        this.declaringClass.checkModify();
        return this.fieldInfo;
    }
    
    public FieldInfo getFieldInfo2() {
        return this.fieldInfo;
    }
    
    @Override
    public CtClass getDeclaringClass() {
        return super.getDeclaringClass();
    }
    
    @Override
    public String getName() {
        return this.fieldInfo.getName();
    }
    
    public void setName(final String name) {
        this.declaringClass.checkModify();
        this.fieldInfo.setName(name);
    }
    
    @Override
    public int getModifiers() {
        return AccessFlag.toModifier(this.fieldInfo.getAccessFlags());
    }
    
    @Override
    public void setModifiers(final int n) {
        this.declaringClass.checkModify();
        this.fieldInfo.setAccessFlags(AccessFlag.of(n));
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.hasAnnotationType(s, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public Object getAnnotation(final Class<?> clazz) {
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.getAnnotationType(clazz, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public Object[] getAnnotations() {
        return this.getAnnotations(false);
    }
    
    @Override
    public Object[] getAvailableAnnotations() {
        try {
            return this.getAnnotations(true);
        }
        catch (final ClassNotFoundException cause) {
            throw new RuntimeException("Unexpected exception", cause);
        }
    }
    
    private Object[] getAnnotations(final boolean b) {
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.toAnnotationType(b, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public String getSignature() {
        return this.fieldInfo.getDescriptor();
    }
    
    @Override
    public String getGenericSignature() {
        final SignatureAttribute signatureAttribute = (SignatureAttribute)this.fieldInfo.getAttribute("Signature");
        return (signatureAttribute == null) ? null : signatureAttribute.getSignature();
    }
    
    @Override
    public void setGenericSignature(final String s) {
        this.declaringClass.checkModify();
        this.fieldInfo.addAttribute(new SignatureAttribute(this.fieldInfo.getConstPool(), s));
    }
    
    public CtClass getType() {
        return Descriptor.toCtClass(this.fieldInfo.getDescriptor(), this.declaringClass.getClassPool());
    }
    
    public void setType(final CtClass ctClass) {
        this.declaringClass.checkModify();
        this.fieldInfo.setDescriptor(Descriptor.of(ctClass));
    }
    
    public Object getConstantValue() {
        final int constantValue = this.fieldInfo.getConstantValue();
        if (constantValue == 0) {
            return null;
        }
        final ConstPool constPool = this.fieldInfo.getConstPool();
        switch (constPool.getTag(constantValue)) {
            case 5: {
                return constPool.getLongInfo(constantValue);
            }
            case 4: {
                return constPool.getFloatInfo(constantValue);
            }
            case 6: {
                return constPool.getDoubleInfo(constantValue);
            }
            case 3: {
                final int integerInfo = constPool.getIntegerInfo(constantValue);
                if ("Z".equals(this.fieldInfo.getDescriptor())) {
                    return integerInfo != 0;
                }
                return integerInfo;
            }
            case 8: {
                return constPool.getStringInfo(constantValue);
            }
            default: {
                throw new RuntimeException("bad tag: " + constPool.getTag(constantValue) + " at " + constantValue);
            }
        }
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        final AttributeInfo attribute = this.fieldInfo.getAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
        this.declaringClass.checkModify();
        this.fieldInfo.addAttribute(new AttributeInfo(this.fieldInfo.getConstPool(), s, array));
    }
    
    public abstract static class Initializer
    {
        public static Initializer constant(final int n) {
            return new IntInitializer(n);
        }
        
        public static Initializer constant(final boolean b) {
            return new IntInitializer(b ? 1 : 0);
        }
        
        public static Initializer constant(final long n) {
            return new LongInitializer(n);
        }
        
        public static Initializer constant(final float n) {
            return new FloatInitializer(n);
        }
        
        public static Initializer constant(final double n) {
            return new DoubleInitializer(n);
        }
        
        public static Initializer constant(final String s) {
            return new StringInitializer(s);
        }
        
        public static Initializer byParameter(final int nthParam) {
            final ParamInitializer paramInitializer = new ParamInitializer();
            paramInitializer.nthParam = nthParam;
            return paramInitializer;
        }
        
        public static Initializer byNew(final CtClass objectType) {
            final NewInitializer newInitializer = new NewInitializer();
            newInitializer.objectType = objectType;
            newInitializer.stringParams = null;
            newInitializer.withConstructorParams = false;
            return newInitializer;
        }
        
        public static Initializer byNew(final CtClass objectType, final String[] stringParams) {
            final NewInitializer newInitializer = new NewInitializer();
            newInitializer.objectType = objectType;
            newInitializer.stringParams = stringParams;
            newInitializer.withConstructorParams = false;
            return newInitializer;
        }
        
        public static Initializer byNewWithParams(final CtClass objectType) {
            final NewInitializer newInitializer = new NewInitializer();
            newInitializer.objectType = objectType;
            newInitializer.stringParams = null;
            newInitializer.withConstructorParams = true;
            return newInitializer;
        }
        
        public static Initializer byNewWithParams(final CtClass objectType, final String[] stringParams) {
            final NewInitializer newInitializer = new NewInitializer();
            newInitializer.objectType = objectType;
            newInitializer.stringParams = stringParams;
            newInitializer.withConstructorParams = true;
            return newInitializer;
        }
        
        public static Initializer byCall(final CtClass objectType, final String methodName) {
            final MethodInitializer methodInitializer = new MethodInitializer();
            methodInitializer.objectType = objectType;
            methodInitializer.methodName = methodName;
            methodInitializer.stringParams = null;
            methodInitializer.withConstructorParams = false;
            return methodInitializer;
        }
        
        public static Initializer byCall(final CtClass objectType, final String methodName, final String[] stringParams) {
            final MethodInitializer methodInitializer = new MethodInitializer();
            methodInitializer.objectType = objectType;
            methodInitializer.methodName = methodName;
            methodInitializer.stringParams = stringParams;
            methodInitializer.withConstructorParams = false;
            return methodInitializer;
        }
        
        public static Initializer byCallWithParams(final CtClass objectType, final String methodName) {
            final MethodInitializer methodInitializer = new MethodInitializer();
            methodInitializer.objectType = objectType;
            methodInitializer.methodName = methodName;
            methodInitializer.stringParams = null;
            methodInitializer.withConstructorParams = true;
            return methodInitializer;
        }
        
        public static Initializer byCallWithParams(final CtClass objectType, final String methodName, final String[] stringParams) {
            final MethodInitializer methodInitializer = new MethodInitializer();
            methodInitializer.objectType = objectType;
            methodInitializer.methodName = methodName;
            methodInitializer.stringParams = stringParams;
            methodInitializer.withConstructorParams = true;
            return methodInitializer;
        }
        
        public static Initializer byNewArray(final CtClass ctClass, final int n) {
            return new ArrayInitializer(ctClass.getComponentType(), n);
        }
        
        public static Initializer byNewArray(final CtClass ctClass, final int[] array) {
            return new MultiArrayInitializer(ctClass, array);
        }
        
        public static Initializer byExpr(final String s) {
            return new CodeInitializer(s);
        }
        
        static Initializer byExpr(final ASTree asTree) {
            return new PtreeInitializer(asTree);
        }
        
        void check(final String s) {
        }
        
        abstract int compile(final CtClass p0, final String p1, final Bytecode p2, final CtClass[] p3, final Javac p4);
        
        abstract int compileIfStatic(final CtClass p0, final String p1, final Bytecode p2, final Javac p3);
        
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            return 0;
        }
    }
    
    abstract static class CodeInitializer0 extends Initializer
    {
        abstract void compileExpr(final Javac p0);
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            try {
                bytecode.addAload(0);
                this.compileExpr(javac);
                bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
                return bytecode.getMaxStack();
            }
            catch (final CompileError compileError) {
                throw new CannotCompileException(compileError);
            }
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            try {
                this.compileExpr(javac);
                bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
                return bytecode.getMaxStack();
            }
            catch (final CompileError compileError) {
                throw new CannotCompileException(compileError);
            }
        }
        
        int getConstantValue2(final ConstPool constPool, final CtClass ctClass, final ASTree asTree) {
            if (ctClass.isPrimitive()) {
                if (asTree instanceof IntConst) {
                    final long value = ((IntConst)asTree).get();
                    if (ctClass == CtClass.doubleType) {
                        return constPool.addDoubleInfo((double)value);
                    }
                    if (ctClass == CtClass.floatType) {
                        return constPool.addFloatInfo((float)value);
                    }
                    if (ctClass == CtClass.longType) {
                        return constPool.addLongInfo(value);
                    }
                    if (ctClass != CtClass.voidType) {
                        return constPool.addIntegerInfo((int)value);
                    }
                }
                else if (asTree instanceof DoubleConst) {
                    final double value2 = ((DoubleConst)asTree).get();
                    if (ctClass == CtClass.floatType) {
                        return constPool.addFloatInfo((float)value2);
                    }
                    if (ctClass == CtClass.doubleType) {
                        return constPool.addDoubleInfo(value2);
                    }
                }
            }
            else if (asTree instanceof StringL && ctClass.getName().equals("java.lang.String")) {
                return constPool.addStringInfo(((StringL)asTree).get());
            }
            return 0;
        }
    }
    
    static class CodeInitializer extends CodeInitializer0
    {
        private String expression;
        
        CodeInitializer(final String expression) {
            this.expression = expression;
        }
        
        @Override
        void compileExpr(final Javac javac) {
            javac.compileExpr(this.expression);
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            try {
                return this.getConstantValue2(constPool, ctClass, Javac.parseExpr(this.expression, new SymbolTable()));
            }
            catch (final CompileError compileError) {
                return 0;
            }
        }
    }
    
    static class PtreeInitializer extends CodeInitializer0
    {
        private ASTree expression;
        
        PtreeInitializer(final ASTree expression) {
            this.expression = expression;
        }
        
        @Override
        void compileExpr(final Javac javac) {
            javac.compileExpr(this.expression);
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            return this.getConstantValue2(constPool, ctClass, this.expression);
        }
    }
    
    static class ParamInitializer extends Initializer
    {
        int nthParam;
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            if (array != null && this.nthParam < array.length) {
                bytecode.addAload(0);
                final int n = bytecode.addLoad(nthParamToLocal(this.nthParam, array, false), ctClass) + 1;
                bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
                return n;
            }
            return 0;
        }
        
        static int nthParamToLocal(final int n, final CtClass[] array, final boolean b) {
            final CtClass longType = CtClass.longType;
            final CtClass doubleType = CtClass.doubleType;
            int n2;
            if (b) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            for (final CtClass ctClass : array) {
                if (ctClass == longType || ctClass == doubleType) {
                    n2 += 2;
                }
                else {
                    ++n2;
                }
            }
            return n2;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            return 0;
        }
    }
    
    static class NewInitializer extends Initializer
    {
        CtClass objectType;
        String[] stringParams;
        boolean withConstructorParams;
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addNew(this.objectType);
            bytecode.add(89);
            bytecode.addAload(0);
            int n;
            if (this.stringParams == null) {
                n = 4;
            }
            else {
                n = this.compileStringParameter(bytecode) + 4;
            }
            if (this.withConstructorParams) {
                n += CtNewWrappedMethod.compileParameterList(bytecode, array, 1);
            }
            bytecode.addInvokespecial(this.objectType, "<init>", this.getDescriptor());
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return n;
        }
        
        private String getDescriptor() {
            if (this.stringParams == null) {
                if (this.withConstructorParams) {
                    return "(Ljava/lang/Object;[Ljava/lang/Object;)V";
                }
                return "(Ljava/lang/Object;)V";
            }
            else {
                if (this.withConstructorParams) {
                    return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)V";
                }
                return "(Ljava/lang/Object;[Ljava/lang/String;)V";
            }
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addNew(this.objectType);
            bytecode.add(89);
            int n = 2;
            String s2;
            if (this.stringParams == null) {
                s2 = "()V";
            }
            else {
                s2 = "([Ljava/lang/String;)V";
                n += this.compileStringParameter(bytecode);
            }
            bytecode.addInvokespecial(this.objectType, "<init>", s2);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return n;
        }
        
        protected final int compileStringParameter(final Bytecode bytecode) {
            final int length = this.stringParams.length;
            bytecode.addIconst(length);
            bytecode.addAnewarray("java.lang.String");
            for (int i = 0; i < length; ++i) {
                bytecode.add(89);
                bytecode.addIconst(i);
                bytecode.addLdc(this.stringParams[i]);
                bytecode.add(83);
            }
            return 4;
        }
    }
    
    static class MethodInitializer extends NewInitializer
    {
        String methodName;
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addAload(0);
            int n;
            if (this.stringParams == null) {
                n = 2;
            }
            else {
                n = this.compileStringParameter(bytecode) + 2;
            }
            if (this.withConstructorParams) {
                n += CtNewWrappedMethod.compileParameterList(bytecode, array, 1);
            }
            final String of = Descriptor.of(ctClass);
            bytecode.addInvokestatic(this.objectType, this.methodName, this.getDescriptor() + of);
            bytecode.addPutfield(Bytecode.THIS, s, of);
            return n;
        }
        
        private String getDescriptor() {
            if (this.stringParams == null) {
                if (this.withConstructorParams) {
                    return "(Ljava/lang/Object;[Ljava/lang/Object;)";
                }
                return "(Ljava/lang/Object;)";
            }
            else {
                if (this.withConstructorParams) {
                    return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)";
                }
                return "(Ljava/lang/Object;[Ljava/lang/String;)";
            }
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            int n = 1;
            String str;
            if (this.stringParams == null) {
                str = "()";
            }
            else {
                str = "([Ljava/lang/String;)";
                n += this.compileStringParameter(bytecode);
            }
            final String of = Descriptor.of(ctClass);
            bytecode.addInvokestatic(this.objectType, this.methodName, str + of);
            bytecode.addPutstatic(Bytecode.THIS, s, of);
            return n;
        }
    }
    
    static class IntInitializer extends Initializer
    {
        int value;
        
        IntInitializer(final int value) {
            this.value = value;
        }
        
        @Override
        void check(final String s) {
            final char char1 = s.charAt(0);
            if (char1 != 'I' && char1 != 'S' && char1 != 'B' && char1 != 'C' && char1 != 'Z') {
                throw new CannotCompileException("type mismatch");
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addIconst(this.value);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addIconst(this.value);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 1;
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            return constPool.addIntegerInfo(this.value);
        }
    }
    
    static class LongInitializer extends Initializer
    {
        long value;
        
        LongInitializer(final long value) {
            this.value = value;
        }
        
        @Override
        void check(final String s) {
            if (!s.equals("J")) {
                throw new CannotCompileException("type mismatch");
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addLdc2w(this.value);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 3;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addLdc2w(this.value);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            if (ctClass == CtClass.longType) {
                return constPool.addLongInfo(this.value);
            }
            return 0;
        }
    }
    
    static class FloatInitializer extends Initializer
    {
        float value;
        
        FloatInitializer(final float value) {
            this.value = value;
        }
        
        @Override
        void check(final String s) {
            if (!s.equals("F")) {
                throw new CannotCompileException("type mismatch");
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addFconst(this.value);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 3;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addFconst(this.value);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            if (ctClass == CtClass.floatType) {
                return constPool.addFloatInfo(this.value);
            }
            return 0;
        }
    }
    
    static class DoubleInitializer extends Initializer
    {
        double value;
        
        DoubleInitializer(final double value) {
            this.value = value;
        }
        
        @Override
        void check(final String s) {
            if (!s.equals("D")) {
                throw new CannotCompileException("type mismatch");
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addLdc2w(this.value);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 3;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addLdc2w(this.value);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            if (ctClass == CtClass.doubleType) {
                return constPool.addDoubleInfo(this.value);
            }
            return 0;
        }
    }
    
    static class StringInitializer extends Initializer
    {
        String value;
        
        StringInitializer(final String value) {
            this.value = value;
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            bytecode.addLdc(this.value);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            bytecode.addLdc(this.value);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 1;
        }
        
        @Override
        int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
            if (ctClass.getName().equals("java.lang.String")) {
                return constPool.addStringInfo(this.value);
            }
            return 0;
        }
    }
    
    static class ArrayInitializer extends Initializer
    {
        CtClass type;
        int size;
        
        ArrayInitializer(final CtClass type, final int size) {
            this.type = type;
            this.size = size;
        }
        
        private void addNewarray(final Bytecode bytecode) {
            if (this.type.isPrimitive()) {
                bytecode.addNewarray(((CtPrimitiveType)this.type).getArrayType(), this.size);
            }
            else {
                bytecode.addAnewarray(this.type, this.size);
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            this.addNewarray(bytecode);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 2;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            this.addNewarray(bytecode);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return 1;
        }
    }
    
    static class MultiArrayInitializer extends Initializer
    {
        CtClass type;
        int[] dim;
        
        MultiArrayInitializer(final CtClass type, final int[] dim) {
            this.type = type;
            this.dim = dim;
        }
        
        @Override
        void check(final String s) {
            if (s.charAt(0) != '[') {
                throw new CannotCompileException("type mismatch");
            }
        }
        
        @Override
        int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) {
            bytecode.addAload(0);
            final int addMultiNewarray = bytecode.addMultiNewarray(ctClass, this.dim);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return addMultiNewarray + 1;
        }
        
        @Override
        int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) {
            final int addMultiNewarray = bytecode.addMultiNewarray(ctClass, this.dim);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return addMultiNewarray;
        }
    }
}
