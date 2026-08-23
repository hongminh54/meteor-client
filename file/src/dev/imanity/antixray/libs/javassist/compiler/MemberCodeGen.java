// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.compiler;

import dev.imanity.antixray.libs.javassist.compiler.ast.MethodDecl;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.FieldInfo;
import dev.imanity.antixray.libs.javassist.CtField;
import dev.imanity.antixray.libs.javassist.Modifier;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.AccessFlag;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.javassist.compiler.ast.Symbol;
import dev.imanity.antixray.libs.javassist.compiler.ast.Expr;
import dev.imanity.antixray.libs.javassist.compiler.ast.Keyword;
import dev.imanity.antixray.libs.javassist.compiler.ast.Member;
import dev.imanity.antixray.libs.javassist.compiler.ast.CallExpr;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTree;
import dev.imanity.antixray.libs.javassist.compiler.ast.ArrayInit;
import dev.imanity.antixray.libs.javassist.compiler.ast.NewExpr;
import java.util.Iterator;
import java.util.List;
import dev.imanity.antixray.libs.javassist.compiler.ast.Declarator;
import dev.imanity.antixray.libs.javassist.compiler.ast.Pair;
import dev.imanity.antixray.libs.javassist.compiler.ast.Visitor;
import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.compiler.ast.ASTList;
import dev.imanity.antixray.libs.javassist.compiler.ast.Stmnt;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.javassist.bytecode.Bytecode;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;

public class MemberCodeGen extends CodeGen
{
    protected MemberResolver resolver;
    protected CtClass thisClass;
    protected MethodInfo thisMethod;
    protected boolean resultStatic;
    
    public MemberCodeGen(final Bytecode bytecode, final CtClass thisClass, final ClassPool classPool) {
        super(bytecode);
        this.resolver = new MemberResolver(classPool);
        this.thisClass = thisClass;
        this.thisMethod = null;
    }
    
    public int getMajorVersion() {
        final ClassFile classFile2 = this.thisClass.getClassFile2();
        if (classFile2 == null) {
            return ClassFile.MAJOR_VERSION;
        }
        return classFile2.getMajorVersion();
    }
    
    public void setThisMethod(final CtMethod ctMethod) {
        this.thisMethod = ctMethod.getMethodInfo2();
        if (this.typeChecker != null) {
            this.typeChecker.setThisMethod(this.thisMethod);
        }
    }
    
    public CtClass getThisClass() {
        return this.thisClass;
    }
    
    @Override
    protected String getThisName() {
        return MemberResolver.javaToJvmName(this.thisClass.getName());
    }
    
    @Override
    protected String getSuperName() {
        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(this.thisClass).getName());
    }
    
    @Override
    protected void insertDefaultSuperCall() {
        this.bytecode.addAload(0);
        this.bytecode.addInvokespecial(MemberResolver.getSuperclass(this.thisClass), "<init>", "()V");
    }
    
    @Override
    protected void atTryStmnt(final Stmnt stmnt) {
        final Bytecode bytecode = this.bytecode;
        final Stmnt stmnt2 = (Stmnt)stmnt.getLeft();
        if (stmnt2 == null) {
            return;
        }
        ASTList tail = (ASTList)stmnt.getRight().getLeft();
        final Stmnt stmnt3 = (Stmnt)stmnt.getRight().getRight().getLeft();
        final ArrayList list = new ArrayList();
        JsrHook jsrHook = null;
        if (stmnt3 != null) {
            jsrHook = new JsrHook(this);
        }
        final int currentPc = bytecode.currentPc();
        stmnt2.accept(this);
        final int currentPc2 = bytecode.currentPc();
        if (currentPc == currentPc2) {
            throw new CompileError("empty try block");
        }
        int n = this.hasReturned ? 0 : 1;
        if (n != 0) {
            bytecode.addOpcode(167);
            list.add(bytecode.currentPc());
            bytecode.addIndex(0);
        }
        final int maxLocals = this.getMaxLocals();
        this.incMaxLocals(1);
        while (tail != null) {
            final Pair pair = (Pair)tail.head();
            tail = tail.tail();
            final Declarator declarator = (Declarator)pair.getLeft();
            final Stmnt stmnt4 = (Stmnt)pair.getRight();
            declarator.setLocalVar(maxLocals);
            final CtClass lookupClassByJvmName = this.resolver.lookupClassByJvmName(declarator.getClassName());
            declarator.setClassName(MemberResolver.javaToJvmName(lookupClassByJvmName.getName()));
            bytecode.addExceptionHandler(currentPc, currentPc2, bytecode.currentPc(), lookupClassByJvmName);
            bytecode.growStack(1);
            bytecode.addAstore(maxLocals);
            this.hasReturned = false;
            if (stmnt4 != null) {
                stmnt4.accept(this);
            }
            if (!this.hasReturned) {
                bytecode.addOpcode(167);
                list.add(bytecode.currentPc());
                bytecode.addIndex(0);
                n = 1;
            }
        }
        if (stmnt3 != null) {
            jsrHook.remove(this);
            final int currentPc3 = bytecode.currentPc();
            bytecode.addExceptionHandler(currentPc, currentPc3, currentPc3, 0);
            bytecode.growStack(1);
            bytecode.addAstore(maxLocals);
            this.hasReturned = false;
            stmnt3.accept(this);
            if (!this.hasReturned) {
                bytecode.addAload(maxLocals);
                bytecode.addOpcode(191);
            }
            this.addFinally(jsrHook.jsrList, stmnt3);
        }
        this.patchGoto(list, bytecode.currentPc());
        this.hasReturned = (n == 0);
        if (stmnt3 != null && n != 0) {
            stmnt3.accept(this);
        }
    }
    
    private void addFinally(final List<int[]> list, final Stmnt stmnt) {
        final Bytecode bytecode = this.bytecode;
        for (final int[] array : list) {
            final int n = array[0];
            bytecode.write16bit(n, bytecode.currentPc() - n + 1);
            final JsrHook2 jsrHook2 = new JsrHook2(this, array);
            stmnt.accept(this);
            jsrHook2.remove(this);
            if (!this.hasReturned) {
                bytecode.addOpcode(167);
                bytecode.addIndex(n + 3 - bytecode.currentPc());
            }
        }
    }
    
    @Override
    public void atNewExpr(final NewExpr newExpr) {
        if (newExpr.isArray()) {
            this.atNewArrayExpr(newExpr);
        }
        else {
            final CtClass lookupClassByName = this.resolver.lookupClassByName(newExpr.getClassName());
            final String name = lookupClassByName.getName();
            final ASTList arguments = newExpr.getArguments();
            this.bytecode.addNew(name);
            this.bytecode.addOpcode(89);
            this.atMethodCallCore(lookupClassByName, "<init>", arguments, false, true, -1, null);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = MemberResolver.javaToJvmName(name);
        }
    }
    
    public void atNewArrayExpr(final NewExpr newExpr) {
        final int arrayType = newExpr.getArrayType();
        final ASTList arraySize = newExpr.getArraySize();
        final ASTList className = newExpr.getClassName();
        final ArrayInit initializer = newExpr.getInitializer();
        if (arraySize.length() <= 1) {
            this.atNewArrayExpr2(arrayType, arraySize.head(), Declarator.astToClassName(className, '/'), initializer);
            return;
        }
        if (initializer != null) {
            throw new CompileError("sorry, multi-dimensional array initializer for new is not supported");
        }
        this.atMultiNewArray(arrayType, className, arraySize);
    }
    
    private void atNewArrayExpr2(final int exprType, final ASTree asTree, final String s, final ArrayInit arrayInit) {
        if (arrayInit == null) {
            if (asTree == null) {
                throw new CompileError("no array size");
            }
            asTree.accept(this);
        }
        else {
            if (asTree != null) {
                throw new CompileError("unnecessary array size specified for new");
            }
            this.bytecode.addIconst(arrayInit.size());
        }
        String resolveClassName;
        if (exprType == 307) {
            resolveClassName = this.resolveClassName(s);
            this.bytecode.addAnewarray(MemberResolver.jvmToJavaName(resolveClassName));
        }
        else {
            resolveClassName = null;
            int n = 0;
            switch (exprType) {
                case 301: {
                    n = 4;
                    break;
                }
                case 306: {
                    n = 5;
                    break;
                }
                case 317: {
                    n = 6;
                    break;
                }
                case 312: {
                    n = 7;
                    break;
                }
                case 303: {
                    n = 8;
                    break;
                }
                case 334: {
                    n = 9;
                    break;
                }
                case 324: {
                    n = 10;
                    break;
                }
                case 326: {
                    n = 11;
                    break;
                }
                default: {
                    badNewExpr();
                    break;
                }
            }
            this.bytecode.addOpcode(188);
            this.bytecode.add(n);
        }
        if (arrayInit != null) {
            final int size = arrayInit.size();
            ASTList tail = arrayInit;
            for (int i = 0; i < size; ++i) {
                this.bytecode.addOpcode(89);
                this.bytecode.addIconst(i);
                tail.head().accept(this);
                if (!CodeGen.isRefType(exprType)) {
                    this.atNumCastExpr(this.exprType, exprType);
                }
                this.bytecode.addOpcode(CodeGen.getArrayWriteOp(exprType, 0));
                tail = tail.tail();
            }
        }
        this.exprType = exprType;
        this.arrayDim = 1;
        this.className = resolveClassName;
    }
    
    private static void badNewExpr() {
        throw new CompileError("bad new expression");
    }
    
    @Override
    protected void atArrayVariableAssign(final ArrayInit arrayInit, final int n, final int n2, final String s) {
        this.atNewArrayExpr2(n, null, s, arrayInit);
    }
    
    @Override
    public void atArrayInit(final ArrayInit arrayInit) {
        throw new CompileError("array initializer is not supported");
    }
    
    protected void atMultiNewArray(final int exprType, final ASTList list, ASTList tail) {
        final int length = tail.length();
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head == null) {
                break;
            }
            ++n;
            head.accept(this);
            if (this.exprType != 324) {
                throw new CompileError("bad type for array size");
            }
            tail = tail.tail();
        }
        this.exprType = exprType;
        this.arrayDim = length;
        String s;
        if (exprType == 307) {
            this.className = this.resolveClassName(list);
            s = CodeGen.toJvmArrayName(this.className, length);
        }
        else {
            s = CodeGen.toJvmTypeName(exprType, length);
        }
        this.bytecode.addMultiNewarray(s, n);
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) {
        String s = null;
        CtClass ctClass = null;
        final ASTree oprand1 = callExpr.oprand1();
        final ASTList list = (ASTList)callExpr.oprand2();
        boolean b = false;
        boolean b2 = false;
        int n = -1;
        final MemberResolver.Method method = callExpr.getMethod();
        if (oprand1 instanceof Member) {
            s = ((Member)oprand1).get();
            ctClass = this.thisClass;
            if (this.inStaticMethod || (method != null && method.isStatic())) {
                b = true;
            }
            else {
                n = this.bytecode.currentPc();
                this.bytecode.addAload(0);
            }
        }
        else if (oprand1 instanceof Keyword) {
            b2 = true;
            s = "<init>";
            ctClass = this.thisClass;
            if (this.inStaticMethod) {
                throw new CompileError("a constructor cannot be static");
            }
            this.bytecode.addAload(0);
            if (((Keyword)oprand1).get() == 336) {
                ctClass = MemberResolver.getSuperclass(ctClass);
            }
        }
        else if (oprand1 instanceof Expr) {
            final Expr expr = (Expr)oprand1;
            s = ((Symbol)expr.oprand2()).get();
            final int operator = expr.getOperator();
            if (operator == 35) {
                ctClass = this.resolver.lookupClass(((Symbol)expr.oprand1()).get(), false);
                b = true;
            }
            else if (operator == 46) {
                final ASTree oprand2 = expr.oprand1();
                final String dotSuper = TypeChecker.isDotSuper(oprand2);
                if (dotSuper != null) {
                    b2 = true;
                    ctClass = MemberResolver.getSuperInterface(this.thisClass, dotSuper);
                    if (this.inStaticMethod || (method != null && method.isStatic())) {
                        b = true;
                    }
                    else {
                        n = this.bytecode.currentPc();
                        this.bytecode.addAload(0);
                    }
                }
                else {
                    if (oprand2 instanceof Keyword && ((Keyword)oprand2).get() == 336) {
                        b2 = true;
                    }
                    try {
                        oprand2.accept(this);
                    }
                    catch (final NoFieldException ex) {
                        if (ex.getExpr() != oprand2) {
                            throw ex;
                        }
                        this.exprType = 307;
                        this.arrayDim = 0;
                        this.className = ex.getField();
                        b = true;
                    }
                    if (this.arrayDim > 0) {
                        ctClass = this.resolver.lookupClass("java.lang.Object", true);
                    }
                    else if (this.exprType == 307) {
                        ctClass = this.resolver.lookupClassByJvmName(this.className);
                    }
                    else {
                        badMethod();
                    }
                }
            }
            else {
                badMethod();
            }
        }
        else {
            fatal();
        }
        this.atMethodCallCore(ctClass, s, list, b, b2, n, method);
    }
    
    private static void badMethod() {
        throw new CompileError("bad method");
    }
    
    public void atMethodCallCore(final CtClass ctClass, final String str, final ASTList list, boolean b, final boolean b2, final int n, MemberResolver.Method lookupMethod) {
        final int methodArgsLength = this.getMethodArgsLength(list);
        final int[] array = new int[methodArgsLength];
        final int[] array2 = new int[methodArgsLength];
        final String[] array3 = new String[methodArgsLength];
        if (!b && lookupMethod != null && lookupMethod.isStatic()) {
            this.bytecode.addOpcode(87);
            b = true;
        }
        this.bytecode.getStackDepth();
        this.atMethodArgs(list, array, array2, array3);
        if (lookupMethod == null) {
            lookupMethod = this.resolver.lookupMethod(ctClass, this.thisClass, this.thisMethod, str, array, array2, array3);
        }
        if (lookupMethod == null) {
            String string;
            if (str.equals("<init>")) {
                string = "constructor not found";
            }
            else {
                string = "Method " + str + " not found in " + ctClass.getName();
            }
            throw new CompileError(string);
        }
        this.atMethodCallCore2(ctClass, str, b, b2, n, lookupMethod);
    }
    
    private boolean isFromSameDeclaringClass(CtClass declaringClass, final CtClass ctClass) {
        try {
            while (declaringClass != null) {
                if (this.isEnclosing(declaringClass, ctClass)) {
                    return true;
                }
                declaringClass = declaringClass.getDeclaringClass();
            }
        }
        catch (final NotFoundException ex) {}
        return false;
    }
    
    private void atMethodCallCore2(final CtClass ctClass, String accessiblePrivate, boolean b, boolean b2, final int n, final MemberResolver.Method method) {
        CtClass declaring = method.declaring;
        final MethodInfo info = method.info;
        String s = info.getDescriptor();
        int accessFlags = info.getAccessFlags();
        if (accessiblePrivate.equals("<init>")) {
            b2 = true;
            if (declaring != ctClass) {
                throw new CompileError("no such constructor: " + ctClass.getName());
            }
            if (declaring != this.thisClass && AccessFlag.isPrivate(accessFlags) && (declaring.getClassFile().getMajorVersion() < 52 || !this.isFromSameDeclaringClass(declaring, this.thisClass))) {
                s = this.getAccessibleConstructor(s, declaring, info);
                this.bytecode.addOpcode(1);
            }
        }
        else if (AccessFlag.isPrivate(accessFlags)) {
            if (declaring == this.thisClass) {
                b2 = true;
            }
            else {
                b2 = false;
                b = true;
                final String s2 = s;
                if ((accessFlags & 0x8) == 0x0) {
                    s = Descriptor.insertParameter(declaring.getName(), s2);
                }
                accessFlags = (AccessFlag.setPackage(accessFlags) | 0x8);
                accessiblePrivate = this.getAccessiblePrivate(accessiblePrivate, s2, s, info, declaring);
            }
        }
        boolean b3 = false;
        if ((accessFlags & 0x8) != 0x0) {
            if (!b) {
                b = true;
                if (n >= 0) {
                    this.bytecode.write(n, 0);
                }
                else {
                    b3 = true;
                }
            }
            this.bytecode.addInvokestatic(declaring, accessiblePrivate, s);
        }
        else if (b2) {
            this.bytecode.addInvokespecial(ctClass, accessiblePrivate, s);
        }
        else {
            if (!Modifier.isPublic(declaring.getModifiers()) || declaring.isInterface() != ctClass.isInterface()) {
                declaring = ctClass;
            }
            if (declaring.isInterface()) {
                this.bytecode.addInvokeinterface(declaring, accessiblePrivate, s, Descriptor.paramSize(s) + 1);
            }
            else {
                if (b) {
                    throw new CompileError(accessiblePrivate + " is not static");
                }
                this.bytecode.addInvokevirtual(declaring, accessiblePrivate, s);
            }
        }
        this.setReturnType(s, b, b3);
    }
    
    protected String getAccessiblePrivate(final String str, final String s, final String s2, final MethodInfo methodInfo, final CtClass ctClass) {
        if (this.isEnclosing(ctClass, this.thisClass)) {
            final AccessorMaker accessorMaker = ctClass.getAccessorMaker();
            if (accessorMaker != null) {
                return accessorMaker.getMethodAccessor(str, s, s2, methodInfo);
            }
        }
        throw new CompileError("Method " + str + " is private");
    }
    
    protected String getAccessibleConstructor(final String s, final CtClass ctClass, final MethodInfo methodInfo) {
        if (this.isEnclosing(ctClass, this.thisClass)) {
            final AccessorMaker accessorMaker = ctClass.getAccessorMaker();
            if (accessorMaker != null) {
                return accessorMaker.getConstructor(ctClass, s, methodInfo);
            }
        }
        throw new CompileError("the called constructor is private in " + ctClass.getName());
    }
    
    private boolean isEnclosing(final CtClass ctClass, CtClass declaringClass) {
        try {
            while (declaringClass != null) {
                declaringClass = declaringClass.getDeclaringClass();
                if (declaringClass == ctClass) {
                    return true;
                }
            }
        }
        catch (final NotFoundException ex) {}
        return false;
    }
    
    public int getMethodArgsLength(final ASTList list) {
        return ASTList.length(list);
    }
    
    public void atMethodArgs(ASTList tail, final int[] array, final int[] array2, final String[] array3) {
        int n = 0;
        while (tail != null) {
            tail.head().accept(this);
            array[n] = this.exprType;
            array2[n] = this.arrayDim;
            array3[n] = this.className;
            ++n;
            tail = tail.tail();
        }
    }
    
    void setReturnType(final String s, final boolean b, final boolean b2) {
        int index = s.indexOf(41);
        if (index < 0) {
            badMethod();
        }
        char c = s.charAt(++index);
        int arrayDim = 0;
        while (c == '[') {
            ++arrayDim;
            c = s.charAt(++index);
        }
        this.arrayDim = arrayDim;
        if (c == 'L') {
            final int index2 = s.indexOf(59, index + 1);
            if (index2 < 0) {
                badMethod();
            }
            this.exprType = 307;
            this.className = s.substring(index + 1, index2);
        }
        else {
            this.exprType = MemberResolver.descToType(c);
            this.className = null;
        }
        final int exprType = this.exprType;
        if (b && b2) {
            if (CodeGen.is2word(exprType, arrayDim)) {
                this.bytecode.addOpcode(93);
                this.bytecode.addOpcode(88);
                this.bytecode.addOpcode(87);
            }
            else if (exprType == 344) {
                this.bytecode.addOpcode(87);
            }
            else {
                this.bytecode.addOpcode(95);
                this.bytecode.addOpcode(87);
            }
        }
    }
    
    @Override
    protected void atFieldAssign(final Expr expr, final int n, final ASTree asTree, final ASTree asTree2, final boolean b) {
        final CtField fieldAccess = this.fieldAccess(asTree, false);
        final boolean resultStatic = this.resultStatic;
        if (n != 61 && !resultStatic) {
            this.bytecode.addOpcode(89);
        }
        int n2;
        if (n == 61) {
            final FieldInfo fieldInfo2 = fieldAccess.getFieldInfo2();
            this.setFieldType(fieldInfo2);
            if (this.isAccessibleField(fieldAccess, fieldInfo2) == null) {
                n2 = this.addFieldrefInfo(fieldAccess, fieldInfo2);
            }
            else {
                n2 = 0;
            }
        }
        else {
            n2 = this.atFieldRead(fieldAccess, resultStatic);
        }
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        this.atAssignCore(expr, n, asTree2, exprType, arrayDim, className);
        final boolean is2word = CodeGen.is2word(exprType, arrayDim);
        if (b) {
            int n3;
            if (resultStatic) {
                n3 = (is2word ? 92 : 89);
            }
            else {
                n3 = (is2word ? 93 : 90);
            }
            this.bytecode.addOpcode(n3);
        }
        this.atFieldAssignCore(fieldAccess, resultStatic, n2, is2word);
        this.exprType = exprType;
        this.arrayDim = arrayDim;
        this.className = className;
    }
    
    private void atFieldAssignCore(final CtField ctField, final boolean b, final int n, final boolean b2) {
        if (n != 0) {
            if (b) {
                this.bytecode.add(179);
                this.bytecode.growStack(b2 ? -2 : -1);
            }
            else {
                this.bytecode.add(181);
                this.bytecode.growStack(b2 ? -3 : -2);
            }
            this.bytecode.addIndex(n);
        }
        else {
            final CtClass declaringClass = ctField.getDeclaringClass();
            final MethodInfo fieldSetter = declaringClass.getAccessorMaker().getFieldSetter(ctField.getFieldInfo2(), b);
            this.bytecode.addInvokestatic(declaringClass, fieldSetter.getName(), fieldSetter.getDescriptor());
        }
    }
    
    @Override
    public void atMember(final Member member) {
        this.atFieldRead(member);
    }
    
    @Override
    protected void atFieldRead(final ASTree asTree) {
        final CtField fieldAccess = this.fieldAccess(asTree, true);
        if (fieldAccess == null) {
            this.atArrayLength(asTree);
            return;
        }
        final boolean resultStatic = this.resultStatic;
        final ASTree constantFieldValue = TypeChecker.getConstantFieldValue(fieldAccess);
        if (constantFieldValue == null) {
            this.atFieldRead(fieldAccess, resultStatic);
        }
        else {
            constantFieldValue.accept(this);
            this.setFieldType(fieldAccess.getFieldInfo2());
        }
    }
    
    private void atArrayLength(final ASTree asTree) {
        if (this.arrayDim == 0) {
            throw new CompileError(".length applied to a non array");
        }
        this.bytecode.addOpcode(190);
        this.exprType = 324;
        this.arrayDim = 0;
    }
    
    private int atFieldRead(final CtField ctField, final boolean b) {
        final FieldInfo fieldInfo2 = ctField.getFieldInfo2();
        final int setFieldType = this.setFieldType(fieldInfo2) ? 1 : 0;
        final AccessorMaker accessibleField = this.isAccessibleField(ctField, fieldInfo2);
        if (accessibleField != null) {
            final MethodInfo fieldGetter = accessibleField.getFieldGetter(fieldInfo2, b);
            this.bytecode.addInvokestatic(ctField.getDeclaringClass(), fieldGetter.getName(), fieldGetter.getDescriptor());
            return 0;
        }
        final int addFieldrefInfo = this.addFieldrefInfo(ctField, fieldInfo2);
        if (b) {
            this.bytecode.add(178);
            this.bytecode.growStack((setFieldType != 0) ? 2 : 1);
        }
        else {
            this.bytecode.add(180);
            this.bytecode.growStack(setFieldType);
        }
        this.bytecode.addIndex(addFieldrefInfo);
        return addFieldrefInfo;
    }
    
    private AccessorMaker isAccessibleField(final CtField ctField, final FieldInfo fieldInfo) {
        if (AccessFlag.isPrivate(fieldInfo.getAccessFlags()) && ctField.getDeclaringClass() != this.thisClass) {
            final CtClass declaringClass = ctField.getDeclaringClass();
            if (this.isEnclosing(declaringClass, this.thisClass)) {
                final AccessorMaker accessorMaker = declaringClass.getAccessorMaker();
                if (accessorMaker != null) {
                    return accessorMaker;
                }
            }
            throw new CompileError("Field " + ctField.getName() + " in " + declaringClass.getName() + " is private.");
        }
        return null;
    }
    
    private boolean setFieldType(final FieldInfo fieldInfo) {
        final String descriptor = fieldInfo.getDescriptor();
        int index = 0;
        int arrayDim = 0;
        char c;
        for (c = descriptor.charAt(index); c == '['; c = descriptor.charAt(++index)) {
            ++arrayDim;
        }
        this.arrayDim = arrayDim;
        this.exprType = MemberResolver.descToType(c);
        if (c == 'L') {
            this.className = descriptor.substring(index + 1, descriptor.indexOf(59, index + 1));
        }
        else {
            this.className = null;
        }
        return arrayDim == 0 && (c == 'J' || c == 'D');
    }
    
    private int addFieldrefInfo(final CtField ctField, final FieldInfo fieldInfo) {
        final ConstPool constPool = this.bytecode.getConstPool();
        return constPool.addFieldrefInfo(constPool.addClassInfo(ctField.getDeclaringClass().getName()), fieldInfo.getName(), fieldInfo.getDescriptor());
    }
    
    @Override
    protected void atClassObject2(final String s) {
        if (this.getMajorVersion() < 49) {
            super.atClassObject2(s);
        }
        else {
            this.bytecode.addLdc(this.bytecode.getConstPool().addClassInfo(s));
        }
    }
    
    @Override
    protected void atFieldPlusPlus(final int n, final boolean b, final ASTree asTree, final Expr expr, final boolean b2) {
        final CtField fieldAccess = this.fieldAccess(asTree, false);
        final boolean resultStatic = this.resultStatic;
        if (!resultStatic) {
            this.bytecode.addOpcode(89);
        }
        final int atFieldRead = this.atFieldRead(fieldAccess, resultStatic);
        final boolean is2word = CodeGen.is2word(this.exprType, this.arrayDim);
        int n2;
        if (resultStatic) {
            n2 = (is2word ? 92 : 89);
        }
        else {
            n2 = (is2word ? 93 : 90);
        }
        this.atPlusPlusCore(n2, b2, n, b, expr);
        this.atFieldAssignCore(fieldAccess, resultStatic, atFieldRead, is2word);
    }
    
    protected CtField fieldAccess(final ASTree asTree, final boolean b) {
        if (asTree instanceof Member) {
            final String value = ((Member)asTree).get();
            CtField field;
            try {
                field = this.thisClass.getField(value);
            }
            catch (final NotFoundException ex) {
                throw new NoFieldException(value, asTree);
            }
            final boolean static1 = Modifier.isStatic(field.getModifiers());
            if (!static1) {
                if (this.inStaticMethod) {
                    throw new CompileError("not available in a static method: " + value);
                }
                this.bytecode.addAload(0);
            }
            this.resultStatic = static1;
            return field;
        }
        if (asTree instanceof Expr) {
            final Expr expr = (Expr)asTree;
            final int operator = expr.getOperator();
            if (operator == 35) {
                final CtField lookupField = this.resolver.lookupField(((Symbol)expr.oprand1()).get(), (Symbol)expr.oprand2());
                this.resultStatic = true;
                return lookupField;
            }
            if (operator == 46) {
                CtField lookupFieldByJvmName = null;
                try {
                    expr.oprand1().accept(this);
                    if (this.exprType == 307 && this.arrayDim == 0) {
                        lookupFieldByJvmName = this.resolver.lookupFieldByJvmName(this.className, (Symbol)expr.oprand2());
                    }
                    else {
                        if (b && this.arrayDim > 0 && ((Symbol)expr.oprand2()).get().equals("length")) {
                            return null;
                        }
                        badLvalue();
                    }
                    final boolean static2 = Modifier.isStatic(lookupFieldByJvmName.getModifiers());
                    if (static2) {
                        this.bytecode.addOpcode(87);
                    }
                    this.resultStatic = static2;
                    return lookupFieldByJvmName;
                }
                catch (final NoFieldException ex2) {
                    if (ex2.getExpr() != expr.oprand1()) {
                        throw ex2;
                    }
                    final CtField lookupFieldByJvmName2 = this.resolver.lookupFieldByJvmName2(ex2.getField(), (Symbol)expr.oprand2(), asTree);
                    this.resultStatic = true;
                    return lookupFieldByJvmName2;
                }
            }
            badLvalue();
        }
        else {
            badLvalue();
        }
        this.resultStatic = false;
        return null;
    }
    
    private static void badLvalue() {
        throw new CompileError("bad l-value");
    }
    
    public CtClass[] makeParamList(final MethodDecl methodDecl) {
        ASTList list = methodDecl.getParams();
        CtClass[] array;
        if (list == null) {
            array = new CtClass[0];
        }
        else {
            int n = 0;
            array = new CtClass[list.length()];
            while (list != null) {
                array[n++] = this.resolver.lookupClass((Declarator)list.head());
                list = list.tail();
            }
        }
        return array;
    }
    
    public CtClass[] makeThrowsList(final MethodDecl methodDecl) {
        ASTList list = methodDecl.getThrows();
        if (list == null) {
            return null;
        }
        int n = 0;
        final CtClass[] array = new CtClass[list.length()];
        while (list != null) {
            array[n++] = this.resolver.lookupClassByName((ASTList)list.head());
            list = list.tail();
        }
        return array;
    }
    
    @Override
    protected String resolveClassName(final ASTList list) {
        return this.resolver.resolveClassName(list);
    }
    
    @Override
    protected String resolveClassName(final String s) {
        return this.resolver.resolveJvmClassName(s);
    }
    
    static class JsrHook extends ReturnHook
    {
        List<int[]> jsrList;
        CodeGen cgen;
        int var;
        
        JsrHook(final CodeGen cgen) {
            super(cgen);
            this.jsrList = new ArrayList<int[]>();
            this.cgen = cgen;
            this.var = -1;
        }
        
        private int getVar(final int n) {
            if (this.var < 0) {
                this.var = this.cgen.getMaxLocals();
                this.cgen.incMaxLocals(n);
            }
            return this.var;
        }
        
        private void jsrJmp(final Bytecode bytecode) {
            bytecode.addOpcode(167);
            this.jsrList.add(new int[] { bytecode.currentPc(), this.var });
            bytecode.addIndex(0);
        }
        
        @Override
        protected boolean doit(final Bytecode bytecode, final int n) {
            switch (n) {
                case 177: {
                    this.jsrJmp(bytecode);
                    break;
                }
                case 176: {
                    bytecode.addAstore(this.getVar(1));
                    this.jsrJmp(bytecode);
                    bytecode.addAload(this.var);
                    break;
                }
                case 172: {
                    bytecode.addIstore(this.getVar(1));
                    this.jsrJmp(bytecode);
                    bytecode.addIload(this.var);
                    break;
                }
                case 173: {
                    bytecode.addLstore(this.getVar(2));
                    this.jsrJmp(bytecode);
                    bytecode.addLload(this.var);
                    break;
                }
                case 175: {
                    bytecode.addDstore(this.getVar(2));
                    this.jsrJmp(bytecode);
                    bytecode.addDload(this.var);
                    break;
                }
                case 174: {
                    bytecode.addFstore(this.getVar(1));
                    this.jsrJmp(bytecode);
                    bytecode.addFload(this.var);
                    break;
                }
                default: {
                    throw new RuntimeException("fatal");
                }
            }
            return false;
        }
    }
    
    static class JsrHook2 extends ReturnHook
    {
        int var;
        int target;
        
        JsrHook2(final CodeGen codeGen, final int[] array) {
            super(codeGen);
            this.target = array[0];
            this.var = array[1];
        }
        
        @Override
        protected boolean doit(final Bytecode bytecode, final int n) {
            switch (n) {
                case 177: {
                    break;
                }
                case 176: {
                    bytecode.addAstore(this.var);
                    break;
                }
                case 172: {
                    bytecode.addIstore(this.var);
                    break;
                }
                case 173: {
                    bytecode.addLstore(this.var);
                    break;
                }
                case 175: {
                    bytecode.addDstore(this.var);
                    break;
                }
                case 174: {
                    bytecode.addFstore(this.var);
                    break;
                }
                default: {
                    throw new RuntimeException("fatal");
                }
            }
            bytecode.addOpcode(167);
            bytecode.addIndex(this.target - bytecode.currentPc() + 3);
            return true;
        }
    }
}
