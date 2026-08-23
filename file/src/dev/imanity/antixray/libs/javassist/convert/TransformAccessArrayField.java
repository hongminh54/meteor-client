// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.convert;

import dev.imanity.antixray.libs.javassist.bytecode.BadBytecode;
import dev.imanity.antixray.libs.javassist.bytecode.Descriptor;
import dev.imanity.antixray.libs.javassist.bytecode.analysis.Analyzer;
import dev.imanity.antixray.libs.javassist.bytecode.CodeIterator;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.ConstPool;
import dev.imanity.antixray.libs.javassist.bytecode.analysis.Frame;
import dev.imanity.antixray.libs.javassist.CodeConverter;

public final class TransformAccessArrayField extends Transformer
{
    private final String methodClassname;
    private final CodeConverter.ArrayAccessReplacementMethodNames names;
    private Frame[] frames;
    private int offset;
    
    public TransformAccessArrayField(final Transformer transformer, final String methodClassname, final CodeConverter.ArrayAccessReplacementMethodNames names) {
        super(transformer);
        this.methodClassname = methodClassname;
        this.names = names;
    }
    
    @Override
    public void initialize(final ConstPool constPool, final CtClass ctClass, final MethodInfo methodInfo) {
        final CodeIterator iterator = methodInfo.getCodeAttribute().iterator();
        while (iterator.hasNext()) {
            try {
                final int next = iterator.next();
                final int byte1 = iterator.byteAt(next);
                if (byte1 == 50) {
                    this.initFrames(ctClass, methodInfo);
                }
                if (byte1 == 50 || byte1 == 51 || byte1 == 52 || byte1 == 49 || byte1 == 48 || byte1 == 46 || byte1 == 47 || byte1 == 53) {
                    this.replace(constPool, iterator, next, byte1, this.getLoadReplacementSignature(byte1));
                }
                else {
                    if (byte1 != 83 && byte1 != 84 && byte1 != 85 && byte1 != 82 && byte1 != 81 && byte1 != 79 && byte1 != 80 && byte1 != 86) {
                        continue;
                    }
                    this.replace(constPool, iterator, next, byte1, this.getStoreReplacementSignature(byte1));
                }
                continue;
            }
            catch (final Exception ex) {
                throw new CannotCompileException(ex);
            }
            break;
        }
    }
    
    @Override
    public void clean() {
        this.frames = null;
        this.offset = -1;
    }
    
    @Override
    public int transform(final CtClass ctClass, final int n, final CodeIterator codeIterator, final ConstPool constPool) {
        return n;
    }
    
    private Frame getFrame(final int n) {
        return this.frames[n - this.offset];
    }
    
    private void initFrames(final CtClass ctClass, final MethodInfo methodInfo) {
        if (this.frames == null) {
            this.frames = new Analyzer().analyze(ctClass, methodInfo);
            this.offset = 0;
        }
    }
    
    private int updatePos(final int n, final int n2) {
        if (this.offset > -1) {
            this.offset += n2;
        }
        return n + n2;
    }
    
    private String getTopType(final int n) {
        final Frame frame = this.getFrame(n);
        if (frame == null) {
            return null;
        }
        final CtClass ctClass = frame.peek().getCtClass();
        return (ctClass != null) ? Descriptor.toJvmName(ctClass) : null;
    }
    
    private int replace(final ConstPool constPool, final CodeIterator codeIterator, int n, final int n2, final String s) {
        String topType = null;
        final String methodName = this.getMethodName(n2);
        if (methodName != null) {
            if (n2 == 50) {
                topType = this.getTopType(codeIterator.lookAhead());
                if (topType == null) {
                    return n;
                }
                if ("java/lang/Object".equals(topType)) {
                    topType = null;
                }
            }
            codeIterator.writeByte(0, n);
            final CodeIterator.Gap insertGap = codeIterator.insertGapAt(n, (topType != null) ? 5 : 2, false);
            n = insertGap.position;
            final int addMethodrefInfo = constPool.addMethodrefInfo(constPool.addClassInfo(this.methodClassname), methodName, s);
            codeIterator.writeByte(184, n);
            codeIterator.write16bit(addMethodrefInfo, n + 1);
            if (topType != null) {
                final int addClassInfo = constPool.addClassInfo(topType);
                codeIterator.writeByte(192, n + 3);
                codeIterator.write16bit(addClassInfo, n + 4);
            }
            n = this.updatePos(n, insertGap.length);
        }
        return n;
    }
    
    private String getMethodName(final int n) {
        Object anObject = null;
        switch (n) {
            case 50: {
                anObject = this.names.objectRead();
                break;
            }
            case 51: {
                anObject = this.names.byteOrBooleanRead();
                break;
            }
            case 52: {
                anObject = this.names.charRead();
                break;
            }
            case 49: {
                anObject = this.names.doubleRead();
                break;
            }
            case 48: {
                anObject = this.names.floatRead();
                break;
            }
            case 46: {
                anObject = this.names.intRead();
                break;
            }
            case 53: {
                anObject = this.names.shortRead();
                break;
            }
            case 47: {
                anObject = this.names.longRead();
                break;
            }
            case 83: {
                anObject = this.names.objectWrite();
                break;
            }
            case 84: {
                anObject = this.names.byteOrBooleanWrite();
                break;
            }
            case 85: {
                anObject = this.names.charWrite();
                break;
            }
            case 82: {
                anObject = this.names.doubleWrite();
                break;
            }
            case 81: {
                anObject = this.names.floatWrite();
                break;
            }
            case 79: {
                anObject = this.names.intWrite();
                break;
            }
            case 86: {
                anObject = this.names.shortWrite();
                break;
            }
            case 80: {
                anObject = this.names.longWrite();
                break;
            }
        }
        if ("".equals(anObject)) {
            anObject = null;
        }
        return (String)anObject;
    }
    
    private String getLoadReplacementSignature(final int n) {
        switch (n) {
            case 50: {
                return "(Ljava/lang/Object;I)Ljava/lang/Object;";
            }
            case 51: {
                return "(Ljava/lang/Object;I)B";
            }
            case 52: {
                return "(Ljava/lang/Object;I)C";
            }
            case 49: {
                return "(Ljava/lang/Object;I)D";
            }
            case 48: {
                return "(Ljava/lang/Object;I)F";
            }
            case 46: {
                return "(Ljava/lang/Object;I)I";
            }
            case 53: {
                return "(Ljava/lang/Object;I)S";
            }
            case 47: {
                return "(Ljava/lang/Object;I)J";
            }
            default: {
                throw new BadBytecode(n);
            }
        }
    }
    
    private String getStoreReplacementSignature(final int n) {
        switch (n) {
            case 83: {
                return "(Ljava/lang/Object;ILjava/lang/Object;)V";
            }
            case 84: {
                return "(Ljava/lang/Object;IB)V";
            }
            case 85: {
                return "(Ljava/lang/Object;IC)V";
            }
            case 82: {
                return "(Ljava/lang/Object;ID)V";
            }
            case 81: {
                return "(Ljava/lang/Object;IF)V";
            }
            case 79: {
                return "(Ljava/lang/Object;II)V";
            }
            case 86: {
                return "(Ljava/lang/Object;IS)V";
            }
            case 80: {
                return "(Ljava/lang/Object;IJ)V";
            }
            default: {
                throw new BadBytecode(n);
            }
        }
    }
}
