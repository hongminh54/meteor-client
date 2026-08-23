// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist;

import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.bytecode.CodeAttribute;
import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.convert.TransformAfter;
import dev.imanity.antixray.libs.javassist.convert.TransformBefore;
import dev.imanity.antixray.libs.javassist.convert.TransformCallToStatic;
import dev.imanity.antixray.libs.javassist.convert.TransformCall;
import dev.imanity.antixray.libs.javassist.convert.TransformAccessArrayField;
import dev.imanity.antixray.libs.javassist.convert.TransformWriteField;
import dev.imanity.antixray.libs.javassist.convert.TransformReadField;
import dev.imanity.antixray.libs.javassist.convert.TransformFieldAccess;
import dev.imanity.antixray.libs.javassist.convert.TransformNewClass;
import dev.imanity.antixray.libs.javassist.convert.TransformNew;
import dev.imanity.antixray.libs.javassist.convert.Transformer;

public class CodeConverter
{
    protected Transformer transformers;
    
    public CodeConverter() {
        this.transformers = null;
    }
    
    public void replaceNew(final CtClass ctClass, final CtClass ctClass2, final String s) {
        this.transformers = new TransformNew(this.transformers, ctClass.getName(), ctClass2.getName(), s);
    }
    
    public void replaceNew(final CtClass ctClass, final CtClass ctClass2) {
        this.transformers = new TransformNewClass(this.transformers, ctClass.getName(), ctClass2.getName());
    }
    
    public void redirectFieldAccess(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformFieldAccess(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceFieldRead(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformReadField(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceFieldWrite(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformWriteField(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceArrayAccess(final CtClass ctClass, final ArrayAccessReplacementMethodNames arrayAccessReplacementMethodNames) {
        this.transformers = new TransformAccessArrayField(this.transformers, ctClass.getName(), arrayAccessReplacementMethodNames);
    }
    
    public void redirectMethodCall(final CtMethod ctMethod, final CtMethod ctMethod2) {
        if (!ctMethod.getMethodInfo2().getDescriptor().equals(ctMethod2.getMethodInfo2().getDescriptor())) {
            throw new CannotCompileException("signature mismatch: " + ctMethod2.getLongName());
        }
        final int modifiers = ctMethod.getModifiers();
        final int modifiers2 = ctMethod2.getModifiers();
        if (Modifier.isStatic(modifiers) != Modifier.isStatic(modifiers2) || (Modifier.isPrivate(modifiers) && !Modifier.isPrivate(modifiers2)) || ctMethod.getDeclaringClass().isInterface() != ctMethod2.getDeclaringClass().isInterface()) {
            throw new CannotCompileException("invoke-type mismatch " + ctMethod2.getLongName());
        }
        this.transformers = new TransformCall(this.transformers, ctMethod, ctMethod2);
    }
    
    public void redirectMethodCall(final String s, final CtMethod ctMethod) {
        this.transformers = new TransformCall(this.transformers, s, ctMethod);
    }
    
    public void redirectMethodCallToStatic(final CtMethod ctMethod, final CtMethod ctMethod2) {
        this.transformers = new TransformCallToStatic(this.transformers, ctMethod, ctMethod2);
    }
    
    public void insertBeforeMethod(final CtMethod ctMethod, final CtMethod ctMethod2) {
        try {
            this.transformers = new TransformBefore(this.transformers, ctMethod, ctMethod2);
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public void insertAfterMethod(final CtMethod ctMethod, final CtMethod ctMethod2) {
        try {
            this.transformers = new TransformAfter(this.transformers, ctMethod, ctMethod2);
        }
        catch (final NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    protected void doit(final CtClass ctClass, final MethodInfo methodInfo, final ConstPool constPool) {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null || this.transformers == null) {
            return;
        }
        for (Transformer transformer = this.transformers; transformer != null; transformer = transformer.getNext()) {
            transformer.initialize(constPool, ctClass, methodInfo);
        }
        final CodeIterator iterator = codeAttribute.iterator();
        while (iterator.hasNext()) {
            try {
                int n = iterator.next();
                for (Transformer transformer2 = this.transformers; transformer2 != null; transformer2 = transformer2.getNext()) {
                    n = transformer2.transform(ctClass, n, iterator, constPool);
                }
                continue;
            }
            catch (final BadBytecode badBytecode) {
                throw new CannotCompileException(badBytecode);
            }
            break;
        }
        int n2 = 0;
        int n3 = 0;
        for (Transformer transformer3 = this.transformers; transformer3 != null; transformer3 = transformer3.getNext()) {
            final int extraLocals = transformer3.extraLocals();
            if (extraLocals > n2) {
                n2 = extraLocals;
            }
            final int extraStack = transformer3.extraStack();
            if (extraStack > n3) {
                n3 = extraStack;
            }
        }
        for (Transformer transformer4 = this.transformers; transformer4 != null; transformer4 = transformer4.getNext()) {
            transformer4.clean();
        }
        if (n2 > 0) {
            codeAttribute.setMaxLocals(codeAttribute.getMaxLocals() + n2);
        }
        if (n3 > 0) {
            codeAttribute.setMaxStack(codeAttribute.getMaxStack() + n3);
        }
        try {
            methodInfo.rebuildStackMapIf6(ctClass.getClassPool(), ctClass.getClassFile2());
        }
        catch (final BadBytecode badBytecode2) {
            throw new CannotCompileException(badBytecode2.getMessage(), badBytecode2);
        }
    }
    
    public static class DefaultArrayAccessReplacementMethodNames implements ArrayAccessReplacementMethodNames
    {
        @Override
        public String byteOrBooleanRead() {
            return "arrayReadByteOrBoolean";
        }
        
        @Override
        public String byteOrBooleanWrite() {
            return "arrayWriteByteOrBoolean";
        }
        
        @Override
        public String charRead() {
            return "arrayReadChar";
        }
        
        @Override
        public String charWrite() {
            return "arrayWriteChar";
        }
        
        @Override
        public String doubleRead() {
            return "arrayReadDouble";
        }
        
        @Override
        public String doubleWrite() {
            return "arrayWriteDouble";
        }
        
        @Override
        public String floatRead() {
            return "arrayReadFloat";
        }
        
        @Override
        public String floatWrite() {
            return "arrayWriteFloat";
        }
        
        @Override
        public String intRead() {
            return "arrayReadInt";
        }
        
        @Override
        public String intWrite() {
            return "arrayWriteInt";
        }
        
        @Override
        public String longRead() {
            return "arrayReadLong";
        }
        
        @Override
        public String longWrite() {
            return "arrayWriteLong";
        }
        
        @Override
        public String objectRead() {
            return "arrayReadObject";
        }
        
        @Override
        public String objectWrite() {
            return "arrayWriteObject";
        }
        
        @Override
        public String shortRead() {
            return "arrayReadShort";
        }
        
        @Override
        public String shortWrite() {
            return "arrayWriteShort";
        }
    }
    
    public interface ArrayAccessReplacementMethodNames
    {
        String byteOrBooleanRead();
        
        String byteOrBooleanWrite();
        
        String charRead();
        
        String charWrite();
        
        String doubleRead();
        
        String doubleWrite();
        
        String floatRead();
        
        String floatWrite();
        
        String intRead();
        
        String intWrite();
        
        String longRead();
        
        String longWrite();
        
        String objectRead();
        
        String objectWrite();
        
        String shortRead();
        
        String shortWrite();
    }
}
