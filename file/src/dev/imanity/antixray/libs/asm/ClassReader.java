// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader
{
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    static final int EXPAND_ASM_INSNS = 256;
    private static final int MAX_BUFFER_SIZE = 1048576;
    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
    @Deprecated
    public final byte[] b;
    public final int header;
    final byte[] classFileBuffer;
    private final int[] cpInfoOffsets;
    private final String[] constantUtf8Values;
    private final ConstantDynamic[] constantDynamicValues;
    private final int[] bootstrapMethodOffsets;
    private final int maxStringLength;
    
    public ClassReader(final byte[] array) {
        this(array, 0, array.length);
    }
    
    public ClassReader(final byte[] array, final int n, final int n2) {
        this(array, n, true);
    }
    
    ClassReader(final byte[] array, final int n, final boolean b) {
        this.classFileBuffer = array;
        this.b = array;
        if (b && this.readShort(n + 6) > 67) {
            throw new IllegalArgumentException(stringConcat$0(this.readShort(n + 6)));
        }
        final int unsignedShort = this.readUnsignedShort(n + 8);
        this.cpInfoOffsets = new int[unsignedShort];
        this.constantUtf8Values = new String[unsignedShort];
        int i = 1;
        int header = n + 10;
        int maxStringLength = 0;
        boolean b2 = false;
        boolean b3 = false;
        while (i < unsignedShort) {
            this.cpInfoOffsets[i++] = header + 1;
            int n2 = 0;
            switch (array[header]) {
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12: {
                    n2 = 5;
                    break;
                }
                case 17: {
                    n2 = 5;
                    b2 = true;
                    b3 = true;
                    break;
                }
                case 18: {
                    n2 = 5;
                    b2 = true;
                    break;
                }
                case 5:
                case 6: {
                    n2 = 9;
                    ++i;
                    break;
                }
                case 1: {
                    n2 = 3 + this.readUnsignedShort(header + 1);
                    if (n2 > maxStringLength) {
                        maxStringLength = n2;
                        break;
                    }
                    break;
                }
                case 15: {
                    n2 = 4;
                    break;
                }
                case 7:
                case 8:
                case 16:
                case 19:
                case 20: {
                    n2 = 3;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            header += n2;
        }
        this.maxStringLength = maxStringLength;
        this.header = header;
        this.constantDynamicValues = (ConstantDynamic[])(b3 ? new ConstantDynamic[unsignedShort] : null);
        this.bootstrapMethodOffsets = (int[])(b2 ? this.readBootstrapMethodsAttribute(maxStringLength) : null);
    }
    
    private static /* synthetic */ String stringConcat$0(final short i) {
        return "Unsupported class file major version " + i;
    }
    
    public ClassReader(final InputStream inputStream) {
        this(readStream(inputStream, false));
    }
    
    public ClassReader(final String s) {
        this(readStream(ClassLoader.getSystemResourceAsStream(stringConcat$1(s.replace('.', '/'))), true));
    }
    
    private static /* synthetic */ String stringConcat$1(final String str) {
        return str + ".class";
    }
    
    private static byte[] readStream(final InputStream inputStream, final boolean b) {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        final int computeBufferSize = computeBufferSize(inputStream);
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                final byte[] array = new byte[computeBufferSize];
                int n = 0;
                int read;
                while ((read = inputStream.read(array, 0, computeBufferSize)) != -1) {
                    byteArrayOutputStream.write(array, 0, read);
                    ++n;
                }
                byteArrayOutputStream.flush();
                if (n == 1) {
                    final byte[] array2 = array;
                    byteArrayOutputStream.close();
                    return array2;
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
            catch (final Throwable t) {
                try {
                    byteArrayOutputStream.close();
                }
                catch (final Throwable t2) {}
                throw t;
            }
        }
        finally {
            if (b) {
                inputStream.close();
            }
        }
    }
    
    private static int computeBufferSize(final InputStream inputStream) {
        final int available = inputStream.available();
        if (available < 256) {
            return 4096;
        }
        return Math.min(available, 1048576);
    }
    
    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }
    
    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.maxStringLength]);
    }
    
    public String getSuperName() {
        return this.readClass(this.header + 4, new char[this.maxStringLength]);
    }
    
    public String[] getInterfaces() {
        int n = this.header + 6;
        final int unsignedShort = this.readUnsignedShort(n);
        final String[] array = new String[unsignedShort];
        if (unsignedShort > 0) {
            final char[] array2 = new char[this.maxStringLength];
            for (int i = 0; i < unsignedShort; ++i) {
                n += 2;
                array[i] = this.readClass(n, array2);
            }
        }
        return array;
    }
    
    public void accept(final ClassVisitor classVisitor, final int n) {
        this.accept(classVisitor, new Attribute[0], n);
    }
    
    public void accept(final ClassVisitor classVisitor, final Attribute[] attributePrototypes, final int parsingOptions) {
        final Context context = new Context();
        context.attributePrototypes = attributePrototypes;
        context.parsingOptions = parsingOptions;
        context.charBuffer = new char[this.maxStringLength];
        final char[] charBuffer = context.charBuffer;
        int n = this.header;
        int unsignedShort = this.readUnsignedShort(n);
        final String class1 = this.readClass(n + 2, charBuffer);
        final String class2 = this.readClass(n + 4, charBuffer);
        final String[] array = new String[this.readUnsignedShort(n + 6)];
        n += 8;
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.readClass(n, charBuffer);
            n += 2;
        }
        int n2 = 0;
        int n3 = 0;
        String utf8 = null;
        String utf9 = null;
        String utf10 = null;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        String class3 = null;
        String class4 = null;
        int n10 = 0;
        int n11 = 0;
        int recordComponent = 0;
        Attribute nextAttribute = null;
        int firstAttributeOffset = this.getFirstAttributeOffset();
        for (int j = this.readUnsignedShort(firstAttributeOffset - 2); j > 0; --j) {
            final String utf11 = this.readUTF8(firstAttributeOffset, charBuffer);
            final int int1 = this.readInt(firstAttributeOffset + 2);
            firstAttributeOffset += 6;
            if ("SourceFile".equals(utf11)) {
                utf9 = this.readUTF8(firstAttributeOffset, charBuffer);
            }
            else if ("InnerClasses".equals(utf11)) {
                n2 = firstAttributeOffset;
            }
            else if ("EnclosingMethod".equals(utf11)) {
                n3 = firstAttributeOffset;
            }
            else if ("NestHost".equals(utf11)) {
                class4 = this.readClass(firstAttributeOffset, charBuffer);
            }
            else if ("NestMembers".equals(utf11)) {
                n10 = firstAttributeOffset;
            }
            else if ("PermittedSubclasses".equals(utf11)) {
                n11 = firstAttributeOffset;
            }
            else if ("Signature".equals(utf11)) {
                utf8 = this.readUTF8(firstAttributeOffset, charBuffer);
            }
            else if ("RuntimeVisibleAnnotations".equals(utf11)) {
                n4 = firstAttributeOffset;
            }
            else if ("RuntimeVisibleTypeAnnotations".equals(utf11)) {
                n6 = firstAttributeOffset;
            }
            else if ("Deprecated".equals(utf11)) {
                unsignedShort |= 0x20000;
            }
            else if ("Synthetic".equals(utf11)) {
                unsignedShort |= 0x1000;
            }
            else if ("SourceDebugExtension".equals(utf11)) {
                if (int1 > this.classFileBuffer.length - firstAttributeOffset) {
                    throw new IllegalArgumentException();
                }
                utf10 = this.readUtf(firstAttributeOffset, int1, new char[int1]);
            }
            else if ("RuntimeInvisibleAnnotations".equals(utf11)) {
                n5 = firstAttributeOffset;
            }
            else if ("RuntimeInvisibleTypeAnnotations".equals(utf11)) {
                n7 = firstAttributeOffset;
            }
            else if ("Record".equals(utf11)) {
                recordComponent = firstAttributeOffset;
                unsignedShort |= 0x10000;
            }
            else if ("Module".equals(utf11)) {
                n8 = firstAttributeOffset;
            }
            else if ("ModuleMainClass".equals(utf11)) {
                class3 = this.readClass(firstAttributeOffset, charBuffer);
            }
            else if ("ModulePackages".equals(utf11)) {
                n9 = firstAttributeOffset;
            }
            else if (!"BootstrapMethods".equals(utf11)) {
                final Attribute attribute = this.readAttribute(attributePrototypes, utf11, firstAttributeOffset, int1, charBuffer, -1, null);
                attribute.nextAttribute = nextAttribute;
                nextAttribute = attribute;
            }
            firstAttributeOffset += int1;
        }
        classVisitor.visit(this.readInt(this.cpInfoOffsets[1] - 7), unsignedShort, class1, utf8, class2, array);
        if ((parsingOptions & 0x2) == 0x0 && (utf9 != null || utf10 != null)) {
            classVisitor.visitSource(utf9, utf10);
        }
        if (n8 != 0) {
            this.readModuleAttributes(classVisitor, context, n8, n9, class3);
        }
        if (class4 != null) {
            classVisitor.visitNestHost(class4);
        }
        if (n3 != 0) {
            final String class5 = this.readClass(n3, charBuffer);
            final int unsignedShort2 = this.readUnsignedShort(n3 + 2);
            classVisitor.visitOuterClass(class5, (unsignedShort2 == 0) ? null : this.readUTF8(this.cpInfoOffsets[unsignedShort2], charBuffer), (unsignedShort2 == 0) ? null : this.readUTF8(this.cpInfoOffsets[unsignedShort2] + 2, charBuffer));
        }
        if (n4 != 0) {
            int unsignedShort3 = this.readUnsignedShort(n4);
            int elementValues = n4 + 2;
            while (unsignedShort3-- > 0) {
                final String utf12 = this.readUTF8(elementValues, charBuffer);
                elementValues += 2;
                elementValues = this.readElementValues(classVisitor.visitAnnotation(utf12, true), elementValues, true, charBuffer);
            }
        }
        if (n5 != 0) {
            int unsignedShort4 = this.readUnsignedShort(n5);
            int elementValues2 = n5 + 2;
            while (unsignedShort4-- > 0) {
                final String utf13 = this.readUTF8(elementValues2, charBuffer);
                elementValues2 += 2;
                elementValues2 = this.readElementValues(classVisitor.visitAnnotation(utf13, false), elementValues2, true, charBuffer);
            }
        }
        if (n6 != 0) {
            int unsignedShort5 = this.readUnsignedShort(n6);
            int elementValues3 = n6 + 2;
            while (unsignedShort5-- > 0) {
                int typeAnnotationTarget = this.readTypeAnnotationTarget(context, elementValues3);
                final String utf14 = this.readUTF8(typeAnnotationTarget, charBuffer);
                typeAnnotationTarget += 2;
                elementValues3 = this.readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf14, true), typeAnnotationTarget, true, charBuffer);
            }
        }
        if (n7 != 0) {
            int unsignedShort6 = this.readUnsignedShort(n7);
            int elementValues4 = n7 + 2;
            while (unsignedShort6-- > 0) {
                int typeAnnotationTarget2 = this.readTypeAnnotationTarget(context, elementValues4);
                final String utf15 = this.readUTF8(typeAnnotationTarget2, charBuffer);
                typeAnnotationTarget2 += 2;
                elementValues4 = this.readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf15, false), typeAnnotationTarget2, true, charBuffer);
            }
        }
        while (nextAttribute != null) {
            final Attribute nextAttribute2 = nextAttribute.nextAttribute;
            nextAttribute.nextAttribute = null;
            classVisitor.visitAttribute(nextAttribute);
            nextAttribute = nextAttribute2;
        }
        if (n10 != 0) {
            int unsignedShort7 = this.readUnsignedShort(n10);
            int n12 = n10 + 2;
            while (unsignedShort7-- > 0) {
                classVisitor.visitNestMember(this.readClass(n12, charBuffer));
                n12 += 2;
            }
        }
        if (n11 != 0) {
            int unsignedShort8 = this.readUnsignedShort(n11);
            int n13 = n11 + 2;
            while (unsignedShort8-- > 0) {
                classVisitor.visitPermittedSubclass(this.readClass(n13, charBuffer));
                n13 += 2;
            }
        }
        if (n2 != 0) {
            int unsignedShort9 = this.readUnsignedShort(n2);
            int n14 = n2 + 2;
            while (unsignedShort9-- > 0) {
                classVisitor.visitInnerClass(this.readClass(n14, charBuffer), this.readClass(n14 + 2, charBuffer), this.readUTF8(n14 + 4, charBuffer), this.readUnsignedShort(n14 + 6));
                n14 += 8;
            }
        }
        if (recordComponent != 0) {
            int unsignedShort10 = this.readUnsignedShort(recordComponent);
            recordComponent += 2;
            while (unsignedShort10-- > 0) {
                recordComponent = this.readRecordComponent(classVisitor, context, recordComponent);
            }
        }
        int unsignedShort11 = this.readUnsignedShort(n);
        n += 2;
        while (unsignedShort11-- > 0) {
            n = this.readField(classVisitor, context, n);
        }
        int unsignedShort12 = this.readUnsignedShort(n);
        n += 2;
        while (unsignedShort12-- > 0) {
            n = this.readMethod(classVisitor, context, n);
        }
        classVisitor.visitEnd();
    }
    
    private void readModuleAttributes(final ClassVisitor classVisitor, final Context context, final int n, final int n2, final String s) {
        final char[] charBuffer = context.charBuffer;
        int n3 = n;
        final String module = this.readModule(n3, charBuffer);
        final int unsignedShort = this.readUnsignedShort(n3 + 2);
        final String utf8 = this.readUTF8(n3 + 4, charBuffer);
        n3 += 6;
        final ModuleVisitor visitModule = classVisitor.visitModule(module, unsignedShort, utf8);
        if (visitModule == null) {
            return;
        }
        if (s != null) {
            visitModule.visitMainClass(s);
        }
        if (n2 != 0) {
            int unsignedShort2 = this.readUnsignedShort(n2);
            int n4 = n2 + 2;
            while (unsignedShort2-- > 0) {
                visitModule.visitPackage(this.readPackage(n4, charBuffer));
                n4 += 2;
            }
        }
        int unsignedShort3 = this.readUnsignedShort(n3);
        n3 += 2;
        while (unsignedShort3-- > 0) {
            final String module2 = this.readModule(n3, charBuffer);
            final int unsignedShort4 = this.readUnsignedShort(n3 + 2);
            final String utf9 = this.readUTF8(n3 + 4, charBuffer);
            n3 += 6;
            visitModule.visitRequire(module2, unsignedShort4, utf9);
        }
        int unsignedShort5 = this.readUnsignedShort(n3);
        n3 += 2;
        while (unsignedShort5-- > 0) {
            final String package1 = this.readPackage(n3, charBuffer);
            final int unsignedShort6 = this.readUnsignedShort(n3 + 2);
            final int unsignedShort7 = this.readUnsignedShort(n3 + 4);
            n3 += 6;
            String[] array = null;
            if (unsignedShort7 != 0) {
                array = new String[unsignedShort7];
                for (int i = 0; i < unsignedShort7; ++i) {
                    array[i] = this.readModule(n3, charBuffer);
                    n3 += 2;
                }
            }
            visitModule.visitExport(package1, unsignedShort6, array);
        }
        int unsignedShort8 = this.readUnsignedShort(n3);
        n3 += 2;
        while (unsignedShort8-- > 0) {
            final String package2 = this.readPackage(n3, charBuffer);
            final int unsignedShort9 = this.readUnsignedShort(n3 + 2);
            final int unsignedShort10 = this.readUnsignedShort(n3 + 4);
            n3 += 6;
            String[] array2 = null;
            if (unsignedShort10 != 0) {
                array2 = new String[unsignedShort10];
                for (int j = 0; j < unsignedShort10; ++j) {
                    array2[j] = this.readModule(n3, charBuffer);
                    n3 += 2;
                }
            }
            visitModule.visitOpen(package2, unsignedShort9, array2);
        }
        int unsignedShort11 = this.readUnsignedShort(n3);
        n3 += 2;
        while (unsignedShort11-- > 0) {
            visitModule.visitUse(this.readClass(n3, charBuffer));
            n3 += 2;
        }
        int unsignedShort12 = this.readUnsignedShort(n3);
        n3 += 2;
        while (unsignedShort12-- > 0) {
            final String class1 = this.readClass(n3, charBuffer);
            final int unsignedShort13 = this.readUnsignedShort(n3 + 2);
            n3 += 4;
            final String[] array3 = new String[unsignedShort13];
            for (int k = 0; k < unsignedShort13; ++k) {
                array3[k] = this.readClass(n3, charBuffer);
                n3 += 2;
            }
            visitModule.visitProvide(class1, array3);
        }
        visitModule.visitEnd();
    }
    
    private int readRecordComponent(final ClassVisitor classVisitor, final Context context, final int n) {
        final char[] charBuffer = context.charBuffer;
        int n2 = n;
        final String utf8 = this.readUTF8(n2, charBuffer);
        final String utf9 = this.readUTF8(n2 + 2, charBuffer);
        n2 += 4;
        String utf10 = null;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        Attribute nextAttribute = null;
        int unsignedShort = this.readUnsignedShort(n2);
        n2 += 2;
        while (unsignedShort-- > 0) {
            final String utf11 = this.readUTF8(n2, charBuffer);
            final int int1 = this.readInt(n2 + 2);
            n2 += 6;
            if ("Signature".equals(utf11)) {
                utf10 = this.readUTF8(n2, charBuffer);
            }
            else if ("RuntimeVisibleAnnotations".equals(utf11)) {
                n3 = n2;
            }
            else if ("RuntimeVisibleTypeAnnotations".equals(utf11)) {
                n5 = n2;
            }
            else if ("RuntimeInvisibleAnnotations".equals(utf11)) {
                n4 = n2;
            }
            else if ("RuntimeInvisibleTypeAnnotations".equals(utf11)) {
                n6 = n2;
            }
            else {
                final Attribute attribute = this.readAttribute(context.attributePrototypes, utf11, n2, int1, charBuffer, -1, null);
                attribute.nextAttribute = nextAttribute;
                nextAttribute = attribute;
            }
            n2 += int1;
        }
        final RecordComponentVisitor visitRecordComponent = classVisitor.visitRecordComponent(utf8, utf9, utf10);
        if (visitRecordComponent == null) {
            return n2;
        }
        if (n3 != 0) {
            int unsignedShort2 = this.readUnsignedShort(n3);
            int elementValues = n3 + 2;
            while (unsignedShort2-- > 0) {
                final String utf12 = this.readUTF8(elementValues, charBuffer);
                elementValues += 2;
                elementValues = this.readElementValues(visitRecordComponent.visitAnnotation(utf12, true), elementValues, true, charBuffer);
            }
        }
        if (n4 != 0) {
            int unsignedShort3 = this.readUnsignedShort(n4);
            int elementValues2 = n4 + 2;
            while (unsignedShort3-- > 0) {
                final String utf13 = this.readUTF8(elementValues2, charBuffer);
                elementValues2 += 2;
                elementValues2 = this.readElementValues(visitRecordComponent.visitAnnotation(utf13, false), elementValues2, true, charBuffer);
            }
        }
        if (n5 != 0) {
            int unsignedShort4 = this.readUnsignedShort(n5);
            int elementValues3 = n5 + 2;
            while (unsignedShort4-- > 0) {
                int typeAnnotationTarget = this.readTypeAnnotationTarget(context, elementValues3);
                final String utf14 = this.readUTF8(typeAnnotationTarget, charBuffer);
                typeAnnotationTarget += 2;
                elementValues3 = this.readElementValues(visitRecordComponent.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf14, true), typeAnnotationTarget, true, charBuffer);
            }
        }
        if (n6 != 0) {
            int unsignedShort5 = this.readUnsignedShort(n6);
            int elementValues4 = n6 + 2;
            while (unsignedShort5-- > 0) {
                int typeAnnotationTarget2 = this.readTypeAnnotationTarget(context, elementValues4);
                final String utf15 = this.readUTF8(typeAnnotationTarget2, charBuffer);
                typeAnnotationTarget2 += 2;
                elementValues4 = this.readElementValues(visitRecordComponent.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf15, false), typeAnnotationTarget2, true, charBuffer);
            }
        }
        while (nextAttribute != null) {
            final Attribute nextAttribute2 = nextAttribute.nextAttribute;
            nextAttribute.nextAttribute = null;
            visitRecordComponent.visitAttribute(nextAttribute);
            nextAttribute = nextAttribute2;
        }
        visitRecordComponent.visitEnd();
        return n2;
    }
    
    private int readField(final ClassVisitor classVisitor, final Context context, final int n) {
        final char[] charBuffer = context.charBuffer;
        int n2 = n;
        int unsignedShort = this.readUnsignedShort(n2);
        final String utf8 = this.readUTF8(n2 + 2, charBuffer);
        final String utf9 = this.readUTF8(n2 + 4, charBuffer);
        n2 += 6;
        Object o = null;
        String utf10 = null;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        Attribute nextAttribute = null;
        int unsignedShort2 = this.readUnsignedShort(n2);
        n2 += 2;
        while (unsignedShort2-- > 0) {
            final String utf11 = this.readUTF8(n2, charBuffer);
            final int int1 = this.readInt(n2 + 2);
            n2 += 6;
            if ("ConstantValue".equals(utf11)) {
                final int unsignedShort3 = this.readUnsignedShort(n2);
                o = ((unsignedShort3 == 0) ? null : this.readConst(unsignedShort3, charBuffer));
            }
            else if ("Signature".equals(utf11)) {
                utf10 = this.readUTF8(n2, charBuffer);
            }
            else if ("Deprecated".equals(utf11)) {
                unsignedShort |= 0x20000;
            }
            else if ("Synthetic".equals(utf11)) {
                unsignedShort |= 0x1000;
            }
            else if ("RuntimeVisibleAnnotations".equals(utf11)) {
                n3 = n2;
            }
            else if ("RuntimeVisibleTypeAnnotations".equals(utf11)) {
                n5 = n2;
            }
            else if ("RuntimeInvisibleAnnotations".equals(utf11)) {
                n4 = n2;
            }
            else if ("RuntimeInvisibleTypeAnnotations".equals(utf11)) {
                n6 = n2;
            }
            else {
                final Attribute attribute = this.readAttribute(context.attributePrototypes, utf11, n2, int1, charBuffer, -1, null);
                attribute.nextAttribute = nextAttribute;
                nextAttribute = attribute;
            }
            n2 += int1;
        }
        final FieldVisitor visitField = classVisitor.visitField(unsignedShort, utf8, utf9, utf10, o);
        if (visitField == null) {
            return n2;
        }
        if (n3 != 0) {
            int unsignedShort4 = this.readUnsignedShort(n3);
            int elementValues = n3 + 2;
            while (unsignedShort4-- > 0) {
                final String utf12 = this.readUTF8(elementValues, charBuffer);
                elementValues += 2;
                elementValues = this.readElementValues(visitField.visitAnnotation(utf12, true), elementValues, true, charBuffer);
            }
        }
        if (n4 != 0) {
            int unsignedShort5 = this.readUnsignedShort(n4);
            int elementValues2 = n4 + 2;
            while (unsignedShort5-- > 0) {
                final String utf13 = this.readUTF8(elementValues2, charBuffer);
                elementValues2 += 2;
                elementValues2 = this.readElementValues(visitField.visitAnnotation(utf13, false), elementValues2, true, charBuffer);
            }
        }
        if (n5 != 0) {
            int unsignedShort6 = this.readUnsignedShort(n5);
            int elementValues3 = n5 + 2;
            while (unsignedShort6-- > 0) {
                int typeAnnotationTarget = this.readTypeAnnotationTarget(context, elementValues3);
                final String utf14 = this.readUTF8(typeAnnotationTarget, charBuffer);
                typeAnnotationTarget += 2;
                elementValues3 = this.readElementValues(visitField.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf14, true), typeAnnotationTarget, true, charBuffer);
            }
        }
        if (n6 != 0) {
            int unsignedShort7 = this.readUnsignedShort(n6);
            int elementValues4 = n6 + 2;
            while (unsignedShort7-- > 0) {
                int typeAnnotationTarget2 = this.readTypeAnnotationTarget(context, elementValues4);
                final String utf15 = this.readUTF8(typeAnnotationTarget2, charBuffer);
                typeAnnotationTarget2 += 2;
                elementValues4 = this.readElementValues(visitField.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf15, false), typeAnnotationTarget2, true, charBuffer);
            }
        }
        while (nextAttribute != null) {
            final Attribute nextAttribute2 = nextAttribute.nextAttribute;
            nextAttribute.nextAttribute = null;
            visitField.visitAttribute(nextAttribute);
            nextAttribute = nextAttribute2;
        }
        visitField.visitEnd();
        return n2;
    }
    
    private int readMethod(final ClassVisitor classVisitor, final Context context, final int n) {
        final char[] charBuffer = context.charBuffer;
        int n2 = n;
        context.currentMethodAccessFlags = this.readUnsignedShort(n2);
        context.currentMethodName = this.readUTF8(n2 + 2, charBuffer);
        context.currentMethodDescriptor = this.readUTF8(n2 + 4, charBuffer);
        n2 += 6;
        int n3 = 0;
        int n4 = 0;
        String[] array = null;
        boolean b = false;
        int unsignedShort = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        Attribute nextAttribute = null;
        int unsignedShort2 = this.readUnsignedShort(n2);
        n2 += 2;
        while (unsignedShort2-- > 0) {
            final String utf8 = this.readUTF8(n2, charBuffer);
            final int int1 = this.readInt(n2 + 2);
            n2 += 6;
            if ("Code".equals(utf8)) {
                if ((context.parsingOptions & 0x1) == 0x0) {
                    n3 = n2;
                }
            }
            else if ("Exceptions".equals(utf8)) {
                n4 = n2;
                array = new String[this.readUnsignedShort(n4)];
                int n13 = n4 + 2;
                for (int i = 0; i < array.length; ++i) {
                    array[i] = this.readClass(n13, charBuffer);
                    n13 += 2;
                }
            }
            else if ("Signature".equals(utf8)) {
                unsignedShort = this.readUnsignedShort(n2);
            }
            else if ("Deprecated".equals(utf8)) {
                context.currentMethodAccessFlags |= 0x20000;
            }
            else if ("RuntimeVisibleAnnotations".equals(utf8)) {
                n5 = n2;
            }
            else if ("RuntimeVisibleTypeAnnotations".equals(utf8)) {
                n9 = n2;
            }
            else if ("AnnotationDefault".equals(utf8)) {
                n11 = n2;
            }
            else if ("Synthetic".equals(utf8)) {
                b = true;
                context.currentMethodAccessFlags |= 0x1000;
            }
            else if ("RuntimeInvisibleAnnotations".equals(utf8)) {
                n6 = n2;
            }
            else if ("RuntimeInvisibleTypeAnnotations".equals(utf8)) {
                n10 = n2;
            }
            else if ("RuntimeVisibleParameterAnnotations".equals(utf8)) {
                n7 = n2;
            }
            else if ("RuntimeInvisibleParameterAnnotations".equals(utf8)) {
                n8 = n2;
            }
            else if ("MethodParameters".equals(utf8)) {
                n12 = n2;
            }
            else {
                final Attribute attribute = this.readAttribute(context.attributePrototypes, utf8, n2, int1, charBuffer, -1, null);
                attribute.nextAttribute = nextAttribute;
                nextAttribute = attribute;
            }
            n2 += int1;
        }
        final MethodVisitor visitMethod = classVisitor.visitMethod(context.currentMethodAccessFlags, context.currentMethodName, context.currentMethodDescriptor, (unsignedShort == 0) ? null : this.readUtf(unsignedShort, charBuffer), array);
        if (visitMethod == null) {
            return n2;
        }
        if (visitMethod instanceof MethodWriter) {
            final MethodWriter methodWriter = (MethodWriter)visitMethod;
            if (methodWriter.canCopyMethodAttributes(this, b, (context.currentMethodAccessFlags & 0x20000) != 0x0, this.readUnsignedShort(n + 4), unsignedShort, n4)) {
                methodWriter.setMethodAttributesSource(n, n2 - n);
                return n2;
            }
        }
        if (n12 != 0 && (context.parsingOptions & 0x2) == 0x0) {
            int byte1 = this.readByte(n12);
            int n14 = n12 + 1;
            while (byte1-- > 0) {
                visitMethod.visitParameter(this.readUTF8(n14, charBuffer), this.readUnsignedShort(n14 + 2));
                n14 += 4;
            }
        }
        if (n11 != 0) {
            final AnnotationVisitor visitAnnotationDefault = visitMethod.visitAnnotationDefault();
            this.readElementValue(visitAnnotationDefault, n11, null, charBuffer);
            if (visitAnnotationDefault != null) {
                visitAnnotationDefault.visitEnd();
            }
        }
        if (n5 != 0) {
            int unsignedShort3 = this.readUnsignedShort(n5);
            int elementValues = n5 + 2;
            while (unsignedShort3-- > 0) {
                final String utf9 = this.readUTF8(elementValues, charBuffer);
                elementValues += 2;
                elementValues = this.readElementValues(visitMethod.visitAnnotation(utf9, true), elementValues, true, charBuffer);
            }
        }
        if (n6 != 0) {
            int unsignedShort4 = this.readUnsignedShort(n6);
            int elementValues2 = n6 + 2;
            while (unsignedShort4-- > 0) {
                final String utf10 = this.readUTF8(elementValues2, charBuffer);
                elementValues2 += 2;
                elementValues2 = this.readElementValues(visitMethod.visitAnnotation(utf10, false), elementValues2, true, charBuffer);
            }
        }
        if (n9 != 0) {
            int unsignedShort5 = this.readUnsignedShort(n9);
            int elementValues3 = n9 + 2;
            while (unsignedShort5-- > 0) {
                int typeAnnotationTarget = this.readTypeAnnotationTarget(context, elementValues3);
                final String utf11 = this.readUTF8(typeAnnotationTarget, charBuffer);
                typeAnnotationTarget += 2;
                elementValues3 = this.readElementValues(visitMethod.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf11, true), typeAnnotationTarget, true, charBuffer);
            }
        }
        if (n10 != 0) {
            int unsignedShort6 = this.readUnsignedShort(n10);
            int elementValues4 = n10 + 2;
            while (unsignedShort6-- > 0) {
                int typeAnnotationTarget2 = this.readTypeAnnotationTarget(context, elementValues4);
                final String utf12 = this.readUTF8(typeAnnotationTarget2, charBuffer);
                typeAnnotationTarget2 += 2;
                elementValues4 = this.readElementValues(visitMethod.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf12, false), typeAnnotationTarget2, true, charBuffer);
            }
        }
        if (n7 != 0) {
            this.readParameterAnnotations(visitMethod, context, n7, true);
        }
        if (n8 != 0) {
            this.readParameterAnnotations(visitMethod, context, n8, false);
        }
        while (nextAttribute != null) {
            final Attribute nextAttribute2 = nextAttribute.nextAttribute;
            nextAttribute.nextAttribute = null;
            visitMethod.visitAttribute(nextAttribute);
            nextAttribute = nextAttribute2;
        }
        if (n3 != 0) {
            visitMethod.visitCode();
            this.readCode(visitMethod, context, n3);
        }
        visitMethod.visitEnd();
        return n2;
    }
    
    private void readCode(final MethodVisitor methodVisitor, final Context context, final int n) {
        int i = n;
        final byte[] classFileBuffer = this.classFileBuffer;
        final char[] charBuffer = context.charBuffer;
        final int unsignedShort = this.readUnsignedShort(i);
        final int unsignedShort2 = this.readUnsignedShort(i + 2);
        final int int1 = this.readInt(i + 4);
        i += 8;
        if (int1 > this.classFileBuffer.length - i) {
            throw new IllegalArgumentException();
        }
        final int n2 = i;
        final int n3 = i + int1;
        final Label[] currentMethodLabels = new Label[int1 + 1];
        context.currentMethodLabels = currentMethodLabels;
        final Label[] array = currentMethodLabels;
        while (i < n3) {
            final int n4 = i - n2;
            switch (classFileBuffer[i] & 0xFF) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                case 190:
                case 191:
                case 194:
                case 195: {
                    ++i;
                    continue;
                }
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 167:
                case 168:
                case 198:
                case 199: {
                    this.createLabel(n4 + this.readShort(i + 1), array);
                    i += 3;
                    continue;
                }
                case 202:
                case 203:
                case 204:
                case 205:
                case 206:
                case 207:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                case 216:
                case 217:
                case 218:
                case 219: {
                    this.createLabel(n4 + this.readUnsignedShort(i + 1), array);
                    i += 3;
                    continue;
                }
                case 200:
                case 201:
                case 220: {
                    this.createLabel(n4 + this.readInt(i + 1), array);
                    i += 5;
                    continue;
                }
                case 196: {
                    switch (classFileBuffer[i + 1] & 0xFF) {
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 169: {
                            i += 4;
                            continue;
                        }
                        case 132: {
                            i += 6;
                            continue;
                        }
                        default: {
                            throw new IllegalArgumentException();
                        }
                    }
                    break;
                }
                case 170: {
                    i += 4 - (n4 & 0x3);
                    this.createLabel(n4 + this.readInt(i), array);
                    int n5 = this.readInt(i + 8) - this.readInt(i + 4) + 1;
                    i += 12;
                    while (n5-- > 0) {
                        this.createLabel(n4 + this.readInt(i), array);
                        i += 4;
                    }
                    continue;
                }
                case 171: {
                    i += 4 - (n4 & 0x3);
                    this.createLabel(n4 + this.readInt(i), array);
                    int int2 = this.readInt(i + 4);
                    i += 8;
                    while (int2-- > 0) {
                        this.createLabel(n4 + this.readInt(i + 4), array);
                        i += 8;
                    }
                    continue;
                }
                case 16:
                case 18:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 169:
                case 188: {
                    i += 2;
                    continue;
                }
                case 17:
                case 19:
                case 20:
                case 132:
                case 178:
                case 179:
                case 180:
                case 181:
                case 182:
                case 183:
                case 184:
                case 187:
                case 189:
                case 192:
                case 193: {
                    i += 3;
                    continue;
                }
                case 185:
                case 186: {
                    i += 5;
                    continue;
                }
                case 197: {
                    i += 4;
                    continue;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        int unsignedShort3 = this.readUnsignedShort(i);
        i += 2;
        while (unsignedShort3-- > 0) {
            final Label label = this.createLabel(this.readUnsignedShort(i), array);
            final Label label2 = this.createLabel(this.readUnsignedShort(i + 2), array);
            final Label label3 = this.createLabel(this.readUnsignedShort(i + 4), array);
            final String utf8 = this.readUTF8(this.cpInfoOffsets[this.readUnsignedShort(i + 6)], charBuffer);
            i += 8;
            methodVisitor.visitTryCatchBlock(label, label2, label3, utf8);
        }
        int stackMapFrame = 0;
        int n6 = 0;
        boolean b = true;
        int n7 = 0;
        int n8 = 0;
        int[] typeAnnotations = null;
        int[] typeAnnotations2 = null;
        Attribute nextAttribute = null;
        int unsignedShort4 = this.readUnsignedShort(i);
        i += 2;
        while (unsignedShort4-- > 0) {
            final String utf9 = this.readUTF8(i, charBuffer);
            final int int3 = this.readInt(i + 2);
            i += 6;
            if ("LocalVariableTable".equals(utf9)) {
                if ((context.parsingOptions & 0x2) == 0x0) {
                    n7 = i;
                    int n9 = i;
                    int unsignedShort5 = this.readUnsignedShort(n9);
                    n9 += 2;
                    while (unsignedShort5-- > 0) {
                        final int unsignedShort6 = this.readUnsignedShort(n9);
                        this.createDebugLabel(unsignedShort6, array);
                        this.createDebugLabel(unsignedShort6 + this.readUnsignedShort(n9 + 2), array);
                        n9 += 10;
                    }
                }
            }
            else if ("LocalVariableTypeTable".equals(utf9)) {
                n8 = i;
            }
            else if ("LineNumberTable".equals(utf9)) {
                if ((context.parsingOptions & 0x2) == 0x0) {
                    int n10 = i;
                    int unsignedShort7 = this.readUnsignedShort(n10);
                    n10 += 2;
                    while (unsignedShort7-- > 0) {
                        final int unsignedShort8 = this.readUnsignedShort(n10);
                        final int unsignedShort9 = this.readUnsignedShort(n10 + 2);
                        n10 += 4;
                        this.createDebugLabel(unsignedShort8, array);
                        array[unsignedShort8].addLineNumber(unsignedShort9);
                    }
                }
            }
            else if ("RuntimeVisibleTypeAnnotations".equals(utf9)) {
                typeAnnotations = this.readTypeAnnotations(methodVisitor, context, i, true);
            }
            else if ("RuntimeInvisibleTypeAnnotations".equals(utf9)) {
                typeAnnotations2 = this.readTypeAnnotations(methodVisitor, context, i, false);
            }
            else if ("StackMapTable".equals(utf9)) {
                if ((context.parsingOptions & 0x4) == 0x0) {
                    stackMapFrame = i + 2;
                    n6 = i + int3;
                }
            }
            else if ("StackMap".equals(utf9)) {
                if ((context.parsingOptions & 0x4) == 0x0) {
                    stackMapFrame = i + 2;
                    n6 = i + int3;
                    b = false;
                }
            }
            else {
                final Attribute attribute = this.readAttribute(context.attributePrototypes, utf9, i, int3, charBuffer, n, array);
                attribute.nextAttribute = nextAttribute;
                nextAttribute = attribute;
            }
            i += int3;
        }
        final boolean b2 = (context.parsingOptions & 0x8) != 0x0;
        if (stackMapFrame != 0) {
            context.currentFrameOffset = -1;
            context.currentFrameType = 0;
            context.currentFrameLocalCount = 0;
            context.currentFrameLocalCountDelta = 0;
            context.currentFrameLocalTypes = new Object[unsignedShort2];
            context.currentFrameStackCount = 0;
            context.currentFrameStackTypes = new Object[unsignedShort];
            if (b2) {
                this.computeImplicitFrame(context);
            }
            for (int j = stackMapFrame; j < n6 - 2; ++j) {
                if (classFileBuffer[j] == 8) {
                    final int unsignedShort10 = this.readUnsignedShort(j + 1);
                    if (unsignedShort10 >= 0 && unsignedShort10 < int1 && (classFileBuffer[n2 + unsignedShort10] & 0xFF) == 0xBB) {
                        this.createLabel(unsignedShort10, array);
                    }
                }
            }
        }
        if (b2 && (context.parsingOptions & 0x100) != 0x0) {
            methodVisitor.visitFrame(-1, unsignedShort2, null, 0, null);
        }
        int n11 = 0;
        int n12 = this.getTypeAnnotationBytecodeOffset(typeAnnotations, 0);
        int n13 = 0;
        int n14 = this.getTypeAnnotationBytecodeOffset(typeAnnotations2, 0);
        int n15 = 0;
        final int n16 = ((context.parsingOptions & 0x100) == 0x0) ? 33 : 0;
        int k = n2;
        while (k < n3) {
            final int n17 = k - n2;
            this.readBytecodeInstructionOffset(n17);
            final Label label4 = array[n17];
            if (label4 != null) {
                label4.accept(methodVisitor, (context.parsingOptions & 0x2) == 0x0);
            }
            while (stackMapFrame != 0 && (context.currentFrameOffset == n17 || context.currentFrameOffset == -1)) {
                if (context.currentFrameOffset != -1) {
                    if (!b || b2) {
                        methodVisitor.visitFrame(-1, context.currentFrameLocalCount, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
                    }
                    else {
                        methodVisitor.visitFrame(context.currentFrameType, context.currentFrameLocalCountDelta, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
                    }
                    n15 = 0;
                }
                if (stackMapFrame < n6) {
                    stackMapFrame = this.readStackMapFrame(stackMapFrame, b, b2, context);
                }
                else {
                    stackMapFrame = 0;
                }
            }
            if (n15 != 0) {
                if ((context.parsingOptions & 0x8) != 0x0) {
                    methodVisitor.visitFrame(256, 0, null, 0, null);
                }
                n15 = 0;
            }
            int n18 = classFileBuffer[k] & 0xFF;
            switch (n18) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                case 190:
                case 191:
                case 194:
                case 195: {
                    methodVisitor.visitInsn(n18);
                    ++k;
                    break;
                }
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45: {
                    n18 -= 26;
                    methodVisitor.visitVarInsn(21 + (n18 >> 2), n18 & 0x3);
                    ++k;
                    break;
                }
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78: {
                    n18 -= 59;
                    methodVisitor.visitVarInsn(54 + (n18 >> 2), n18 & 0x3);
                    ++k;
                    break;
                }
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 167:
                case 168:
                case 198:
                case 199: {
                    methodVisitor.visitJumpInsn(n18, array[n17 + this.readShort(k + 1)]);
                    k += 3;
                    break;
                }
                case 200:
                case 201: {
                    methodVisitor.visitJumpInsn(n18 - n16, array[n17 + this.readInt(k + 1)]);
                    k += 5;
                    break;
                }
                case 202:
                case 203:
                case 204:
                case 205:
                case 206:
                case 207:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                case 216:
                case 217:
                case 218:
                case 219: {
                    final int n19 = (n18 < 218) ? (n18 - 49) : (n18 - 20);
                    final Label label5 = array[n17 + this.readUnsignedShort(k + 1)];
                    if (n19 == 167 || n19 == 168) {
                        methodVisitor.visitJumpInsn(n19 + 33, label5);
                    }
                    else {
                        methodVisitor.visitJumpInsn((n19 < 167) ? ((n19 + 1 ^ 0x1) - 1) : (n19 ^ 0x1), this.createLabel(n17 + 3, array));
                        methodVisitor.visitJumpInsn(200, label5);
                        n15 = 1;
                    }
                    k += 3;
                    break;
                }
                case 220: {
                    methodVisitor.visitJumpInsn(200, array[n17 + this.readInt(k + 1)]);
                    n15 = 1;
                    k += 5;
                    break;
                }
                case 196: {
                    final int n20 = classFileBuffer[k + 1] & 0xFF;
                    if (n20 == 132) {
                        methodVisitor.visitIincInsn(this.readUnsignedShort(k + 2), this.readShort(k + 4));
                        k += 6;
                        break;
                    }
                    methodVisitor.visitVarInsn(n20, this.readUnsignedShort(k + 2));
                    k += 4;
                    break;
                }
                case 170: {
                    k += 4 - (n17 & 0x3);
                    final Label label6 = array[n17 + this.readInt(k)];
                    final int int4 = this.readInt(k + 4);
                    final int int5 = this.readInt(k + 8);
                    k += 12;
                    final Label[] array2 = new Label[int5 - int4 + 1];
                    for (int l = 0; l < array2.length; ++l) {
                        array2[l] = array[n17 + this.readInt(k)];
                        k += 4;
                    }
                    methodVisitor.visitTableSwitchInsn(int4, int5, label6, array2);
                    break;
                }
                case 171: {
                    k += 4 - (n17 & 0x3);
                    final Label label7 = array[n17 + this.readInt(k)];
                    final int int6 = this.readInt(k + 4);
                    k += 8;
                    final int[] array3 = new int[int6];
                    final Label[] array4 = new Label[int6];
                    for (int n21 = 0; n21 < int6; ++n21) {
                        array3[n21] = this.readInt(k);
                        array4[n21] = array[n17 + this.readInt(k + 4)];
                        k += 8;
                    }
                    methodVisitor.visitLookupSwitchInsn(label7, array3, array4);
                    break;
                }
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 169: {
                    methodVisitor.visitVarInsn(n18, classFileBuffer[k + 1] & 0xFF);
                    k += 2;
                    break;
                }
                case 16:
                case 188: {
                    methodVisitor.visitIntInsn(n18, classFileBuffer[k + 1]);
                    k += 2;
                    break;
                }
                case 17: {
                    methodVisitor.visitIntInsn(n18, this.readShort(k + 1));
                    k += 3;
                    break;
                }
                case 18: {
                    methodVisitor.visitLdcInsn(this.readConst(classFileBuffer[k + 1] & 0xFF, charBuffer));
                    k += 2;
                    break;
                }
                case 19:
                case 20: {
                    methodVisitor.visitLdcInsn(this.readConst(this.readUnsignedShort(k + 1), charBuffer));
                    k += 3;
                    break;
                }
                case 178:
                case 179:
                case 180:
                case 181:
                case 182:
                case 183:
                case 184:
                case 185: {
                    final int n22 = this.cpInfoOffsets[this.readUnsignedShort(k + 1)];
                    final int n23 = this.cpInfoOffsets[this.readUnsignedShort(n22 + 2)];
                    final String class1 = this.readClass(n22, charBuffer);
                    final String utf10 = this.readUTF8(n23, charBuffer);
                    final String utf11 = this.readUTF8(n23 + 2, charBuffer);
                    if (n18 < 182) {
                        methodVisitor.visitFieldInsn(n18, class1, utf10, utf11);
                    }
                    else {
                        methodVisitor.visitMethodInsn(n18, class1, utf10, utf11, classFileBuffer[n22 - 1] == 11);
                    }
                    if (n18 == 185) {
                        k += 5;
                        break;
                    }
                    k += 3;
                    break;
                }
                case 186: {
                    final int n24 = this.cpInfoOffsets[this.readUnsignedShort(k + 1)];
                    final int n25 = this.cpInfoOffsets[this.readUnsignedShort(n24 + 2)];
                    final String utf12 = this.readUTF8(n25, charBuffer);
                    final String utf13 = this.readUTF8(n25 + 2, charBuffer);
                    int n26 = this.bootstrapMethodOffsets[this.readUnsignedShort(n24)];
                    final Handle handle = (Handle)this.readConst(this.readUnsignedShort(n26), charBuffer);
                    final Object[] array5 = new Object[this.readUnsignedShort(n26 + 2)];
                    n26 += 4;
                    for (int n27 = 0; n27 < array5.length; ++n27) {
                        array5[n27] = this.readConst(this.readUnsignedShort(n26), charBuffer);
                        n26 += 2;
                    }
                    methodVisitor.visitInvokeDynamicInsn(utf12, utf13, handle, array5);
                    k += 5;
                    break;
                }
                case 187:
                case 189:
                case 192:
                case 193: {
                    methodVisitor.visitTypeInsn(n18, this.readClass(k + 1, charBuffer));
                    k += 3;
                    break;
                }
                case 132: {
                    methodVisitor.visitIincInsn(classFileBuffer[k + 1] & 0xFF, classFileBuffer[k + 2]);
                    k += 3;
                    break;
                }
                case 197: {
                    methodVisitor.visitMultiANewArrayInsn(this.readClass(k + 1, charBuffer), classFileBuffer[k + 3] & 0xFF);
                    k += 4;
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
            while (typeAnnotations != null && n11 < typeAnnotations.length && n12 <= n17) {
                if (n12 == n17) {
                    int typeAnnotationTarget = this.readTypeAnnotationTarget(context, typeAnnotations[n11]);
                    final String utf14 = this.readUTF8(typeAnnotationTarget, charBuffer);
                    typeAnnotationTarget += 2;
                    this.readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf14, true), typeAnnotationTarget, true, charBuffer);
                }
                n12 = this.getTypeAnnotationBytecodeOffset(typeAnnotations, ++n11);
            }
            while (typeAnnotations2 != null && n13 < typeAnnotations2.length && n14 <= n17) {
                if (n14 == n17) {
                    int typeAnnotationTarget2 = this.readTypeAnnotationTarget(context, typeAnnotations2[n13]);
                    final String utf15 = this.readUTF8(typeAnnotationTarget2, charBuffer);
                    typeAnnotationTarget2 += 2;
                    this.readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, utf15, false), typeAnnotationTarget2, true, charBuffer);
                }
                n14 = this.getTypeAnnotationBytecodeOffset(typeAnnotations2, ++n13);
            }
        }
        if (array[int1] != null) {
            methodVisitor.visitLabel(array[int1]);
        }
        if (n7 != 0 && (context.parsingOptions & 0x2) == 0x0) {
            int[] array6 = null;
            if (n8 != 0) {
                array6 = new int[this.readUnsignedShort(n8) * 3];
                for (int n28 = n8 + 2, length = array6.length; length > 0; array6[--length] = n28 + 6, array6[--length] = this.readUnsignedShort(n28 + 8), array6[--length] = this.readUnsignedShort(n28), n28 += 10) {}
            }
            int unsignedShort11 = this.readUnsignedShort(n7);
            int n29 = n7 + 2;
            while (unsignedShort11-- > 0) {
                final int unsignedShort12 = this.readUnsignedShort(n29);
                final int unsignedShort13 = this.readUnsignedShort(n29 + 2);
                final String utf16 = this.readUTF8(n29 + 4, charBuffer);
                final String utf17 = this.readUTF8(n29 + 6, charBuffer);
                final int unsignedShort14 = this.readUnsignedShort(n29 + 8);
                n29 += 10;
                String utf18 = null;
                if (array6 != null) {
                    for (int n30 = 0; n30 < array6.length; n30 += 3) {
                        if (array6[n30] == unsignedShort12 && array6[n30 + 1] == unsignedShort14) {
                            utf18 = this.readUTF8(array6[n30 + 2], charBuffer);
                            break;
                        }
                    }
                }
                methodVisitor.visitLocalVariable(utf16, utf17, utf18, array[unsignedShort12], array[unsignedShort12 + unsignedShort13], unsignedShort14);
            }
        }
        if (typeAnnotations != null) {
            for (final int n32 : typeAnnotations) {
                final int byte1 = this.readByte(n32);
                if (byte1 == 64 || byte1 == 65) {
                    int typeAnnotationTarget3 = this.readTypeAnnotationTarget(context, n32);
                    final String utf19 = this.readUTF8(typeAnnotationTarget3, charBuffer);
                    typeAnnotationTarget3 += 2;
                    this.readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, utf19, true), typeAnnotationTarget3, true, charBuffer);
                }
            }
        }
        if (typeAnnotations2 != null) {
            for (final int n34 : typeAnnotations2) {
                final int byte2 = this.readByte(n34);
                if (byte2 == 64 || byte2 == 65) {
                    int typeAnnotationTarget4 = this.readTypeAnnotationTarget(context, n34);
                    final String utf20 = this.readUTF8(typeAnnotationTarget4, charBuffer);
                    typeAnnotationTarget4 += 2;
                    this.readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, utf20, false), typeAnnotationTarget4, true, charBuffer);
                }
            }
        }
        while (nextAttribute != null) {
            final Attribute nextAttribute2 = nextAttribute.nextAttribute;
            nextAttribute.nextAttribute = null;
            methodVisitor.visitAttribute(nextAttribute);
            nextAttribute = nextAttribute2;
        }
        methodVisitor.visitMaxs(unsignedShort, unsignedShort2);
    }
    
    protected void readBytecodeInstructionOffset(final int n) {
    }
    
    protected Label readLabel(final int n, final Label[] array) {
        if (array[n] == null) {
            array[n] = new Label();
        }
        return array[n];
    }
    
    private Label createLabel(final int n, final Label[] array) {
        final Label label2;
        final Label label = label2 = this.readLabel(n, array);
        label2.flags &= 0xFFFFFFFE;
        return label;
    }
    
    private void createDebugLabel(final int n, final Label[] array) {
        if (array[n] == null) {
            final Label label = this.readLabel(n, array);
            label.flags |= 0x1;
        }
    }
    
    private int[] readTypeAnnotations(final MethodVisitor methodVisitor, final Context context, final int n, final boolean b) {
        final char[] charBuffer = context.charBuffer;
        int n2 = n;
        final int[] array = new int[this.readUnsignedShort(n2)];
        n2 += 2;
        for (int i = 0; i < array.length; ++i) {
            array[i] = n2;
            final int int1 = this.readInt(n2);
            switch (int1 >>> 24) {
                case 64:
                case 65: {
                    int unsignedShort = this.readUnsignedShort(n2 + 1);
                    n2 += 3;
                    while (unsignedShort-- > 0) {
                        final int unsignedShort2 = this.readUnsignedShort(n2);
                        final int unsignedShort3 = this.readUnsignedShort(n2 + 2);
                        n2 += 6;
                        this.createLabel(unsignedShort2, context.currentMethodLabels);
                        this.createLabel(unsignedShort2 + unsignedShort3, context.currentMethodLabels);
                    }
                    break;
                }
                case 71:
                case 72:
                case 73:
                case 74:
                case 75: {
                    n2 += 4;
                    break;
                }
                case 16:
                case 17:
                case 18:
                case 23:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70: {
                    n2 += 3;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            final int byte1 = this.readByte(n2);
            if (int1 >>> 24 == 66) {
                final TypePath typePath = (byte1 == 0) ? null : new TypePath(this.classFileBuffer, n2);
                int n3 = n2 + (1 + 2 * byte1);
                final String utf8 = this.readUTF8(n3, charBuffer);
                n3 += 2;
                n2 = this.readElementValues(methodVisitor.visitTryCatchAnnotation(int1 & 0xFFFFFF00, typePath, utf8, b), n3, true, charBuffer);
            }
            else {
                n2 = this.readElementValues(null, n2 + (3 + 2 * byte1), true, charBuffer);
            }
        }
        return array;
    }
    
    private int getTypeAnnotationBytecodeOffset(final int[] array, final int n) {
        if (array == null || n >= array.length || this.readByte(array[n]) < 67) {
            return -1;
        }
        return this.readUnsignedShort(array[n] + 1);
    }
    
    private int readTypeAnnotationTarget(final Context context, final int n) {
        int n2 = n;
        final int int1 = this.readInt(n);
        int currentTypeAnnotationTarget = 0;
        switch (int1 >>> 24) {
            case 0:
            case 1:
            case 22: {
                currentTypeAnnotationTarget = (int1 & 0xFFFF0000);
                n2 += 2;
                break;
            }
            case 19:
            case 20:
            case 21: {
                currentTypeAnnotationTarget = (int1 & 0xFF000000);
                ++n2;
                break;
            }
            case 64:
            case 65: {
                currentTypeAnnotationTarget = (int1 & 0xFF000000);
                final int unsignedShort = this.readUnsignedShort(n2 + 1);
                n2 += 3;
                context.currentLocalVariableAnnotationRangeStarts = new Label[unsignedShort];
                context.currentLocalVariableAnnotationRangeEnds = new Label[unsignedShort];
                context.currentLocalVariableAnnotationRangeIndices = new int[unsignedShort];
                for (int i = 0; i < unsignedShort; ++i) {
                    final int unsignedShort2 = this.readUnsignedShort(n2);
                    final int unsignedShort3 = this.readUnsignedShort(n2 + 2);
                    final int unsignedShort4 = this.readUnsignedShort(n2 + 4);
                    n2 += 6;
                    context.currentLocalVariableAnnotationRangeStarts[i] = this.createLabel(unsignedShort2, context.currentMethodLabels);
                    context.currentLocalVariableAnnotationRangeEnds[i] = this.createLabel(unsignedShort2 + unsignedShort3, context.currentMethodLabels);
                    context.currentLocalVariableAnnotationRangeIndices[i] = unsignedShort4;
                }
                break;
            }
            case 71:
            case 72:
            case 73:
            case 74:
            case 75: {
                currentTypeAnnotationTarget = (int1 & 0xFF0000FF);
                n2 += 4;
                break;
            }
            case 16:
            case 17:
            case 18:
            case 23:
            case 66: {
                currentTypeAnnotationTarget = (int1 & 0xFFFFFF00);
                n2 += 3;
                break;
            }
            case 67:
            case 68:
            case 69:
            case 70: {
                currentTypeAnnotationTarget = (int1 & 0xFF000000);
                n2 += 3;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        context.currentTypeAnnotationTarget = currentTypeAnnotationTarget;
        final int byte1 = this.readByte(n2);
        context.currentTypeAnnotationTargetPath = ((byte1 == 0) ? null : new TypePath(this.classFileBuffer, n2));
        return n2 + 1 + 2 * byte1;
    }
    
    private void readParameterAnnotations(final MethodVisitor methodVisitor, final Context context, final int n, final boolean b) {
        int elementValues = n;
        final int n2 = this.classFileBuffer[elementValues++] & 0xFF;
        methodVisitor.visitAnnotableParameterCount(n2, b);
        final char[] charBuffer = context.charBuffer;
        for (int i = 0; i < n2; ++i) {
            int unsignedShort = this.readUnsignedShort(elementValues);
            elementValues += 2;
            while (unsignedShort-- > 0) {
                final String utf8 = this.readUTF8(elementValues, charBuffer);
                elementValues += 2;
                elementValues = this.readElementValues(methodVisitor.visitParameterAnnotation(i, utf8, b), elementValues, true, charBuffer);
            }
        }
    }
    
    private int readElementValues(final AnnotationVisitor annotationVisitor, final int n, final boolean b, final char[] array) {
        int n2 = n;
        int unsignedShort = this.readUnsignedShort(n2);
        n2 += 2;
        if (b) {
            while (unsignedShort-- > 0) {
                n2 = this.readElementValue(annotationVisitor, n2 + 2, this.readUTF8(n2, array), array);
            }
        }
        else {
            while (unsignedShort-- > 0) {
                n2 = this.readElementValue(annotationVisitor, n2, null, array);
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return n2;
    }
    
    private int readElementValue(final AnnotationVisitor annotationVisitor, final int n, final String s, final char[] array) {
        int n2 = n;
        if (annotationVisitor != null) {
            Label_1234: {
                switch (this.classFileBuffer[n2++] & 0xFF) {
                    case 66: {
                        annotationVisitor.visit(s, (byte)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2)]));
                        n2 += 2;
                        break;
                    }
                    case 67: {
                        annotationVisitor.visit(s, (char)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2)]));
                        n2 += 2;
                        break;
                    }
                    case 68:
                    case 70:
                    case 73:
                    case 74: {
                        annotationVisitor.visit(s, this.readConst(this.readUnsignedShort(n2), array));
                        n2 += 2;
                        break;
                    }
                    case 83: {
                        annotationVisitor.visit(s, (short)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2)]));
                        n2 += 2;
                        break;
                    }
                    case 90: {
                        annotationVisitor.visit(s, (this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2)]) == 0) ? Boolean.FALSE : Boolean.TRUE);
                        n2 += 2;
                        break;
                    }
                    case 115: {
                        annotationVisitor.visit(s, this.readUTF8(n2, array));
                        n2 += 2;
                        break;
                    }
                    case 101: {
                        annotationVisitor.visitEnum(s, this.readUTF8(n2, array), this.readUTF8(n2 + 2, array));
                        n2 += 4;
                        break;
                    }
                    case 99: {
                        annotationVisitor.visit(s, Type.getType(this.readUTF8(n2, array)));
                        n2 += 2;
                        break;
                    }
                    case 64: {
                        n2 = this.readElementValues(annotationVisitor.visitAnnotation(s, this.readUTF8(n2, array)), n2 + 2, true, array);
                        break;
                    }
                    case 91: {
                        final int unsignedShort = this.readUnsignedShort(n2);
                        n2 += 2;
                        if (unsignedShort == 0) {
                            return this.readElementValues(annotationVisitor.visitArray(s), n2 - 2, false, array);
                        }
                        switch (this.classFileBuffer[n2] & 0xFF) {
                            case 66: {
                                final byte[] array2 = new byte[unsignedShort];
                                for (int i = 0; i < unsignedShort; ++i) {
                                    array2[i] = (byte)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array2);
                                break Label_1234;
                            }
                            case 90: {
                                final boolean[] array3 = new boolean[unsignedShort];
                                for (int j = 0; j < unsignedShort; ++j) {
                                    array3[j] = (this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]) != 0);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array3);
                                break Label_1234;
                            }
                            case 83: {
                                final short[] array4 = new short[unsignedShort];
                                for (int k = 0; k < unsignedShort; ++k) {
                                    array4[k] = (short)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array4);
                                break Label_1234;
                            }
                            case 67: {
                                final char[] array5 = new char[unsignedShort];
                                for (int l = 0; l < unsignedShort; ++l) {
                                    array5[l] = (char)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array5);
                                break Label_1234;
                            }
                            case 73: {
                                final int[] array6 = new int[unsignedShort];
                                for (int n3 = 0; n3 < unsignedShort; ++n3) {
                                    array6[n3] = this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array6);
                                break Label_1234;
                            }
                            case 74: {
                                final long[] array7 = new long[unsignedShort];
                                for (int n4 = 0; n4 < unsignedShort; ++n4) {
                                    array7[n4] = this.readLong(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]);
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array7);
                                break Label_1234;
                            }
                            case 70: {
                                final float[] array8 = new float[unsignedShort];
                                for (int n5 = 0; n5 < unsignedShort; ++n5) {
                                    array8[n5] = Float.intBitsToFloat(this.readInt(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]));
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array8);
                                break Label_1234;
                            }
                            case 68: {
                                final double[] array9 = new double[unsignedShort];
                                for (int n6 = 0; n6 < unsignedShort; ++n6) {
                                    array9[n6] = Double.longBitsToDouble(this.readLong(this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)]));
                                    n2 += 3;
                                }
                                annotationVisitor.visit(s, array9);
                                break Label_1234;
                            }
                            default: {
                                n2 = this.readElementValues(annotationVisitor.visitArray(s), n2 - 2, false, array);
                                break Label_1234;
                            }
                        }
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException();
                    }
                }
            }
            return n2;
        }
        switch (this.classFileBuffer[n2] & 0xFF) {
            case 101: {
                return n2 + 5;
            }
            case 64: {
                return this.readElementValues(null, n2 + 3, true, array);
            }
            case 91: {
                return this.readElementValues(null, n2 + 1, false, array);
            }
            default: {
                return n2 + 3;
            }
        }
    }
    
    private void computeImplicitFrame(final Context context) {
        final String currentMethodDescriptor = context.currentMethodDescriptor;
        final Object[] currentFrameLocalTypes = context.currentFrameLocalTypes;
        int currentFrameLocalCount = 0;
        if ((context.currentMethodAccessFlags & 0x8) == 0x0) {
            if ("<init>".equals(context.currentMethodName)) {
                currentFrameLocalTypes[currentFrameLocalCount++] = Opcodes.UNINITIALIZED_THIS;
            }
            else {
                currentFrameLocalTypes[currentFrameLocalCount++] = this.readClass(this.header + 2, context.charBuffer);
            }
        }
        int n = 1;
        while (true) {
            final int beginIndex = n;
            switch (currentMethodDescriptor.charAt(n++)) {
                case 'B':
                case 'C':
                case 'I':
                case 'S':
                case 'Z': {
                    currentFrameLocalTypes[currentFrameLocalCount++] = Opcodes.INTEGER;
                    continue;
                }
                case 'F': {
                    currentFrameLocalTypes[currentFrameLocalCount++] = Opcodes.FLOAT;
                    continue;
                }
                case 'J': {
                    currentFrameLocalTypes[currentFrameLocalCount++] = Opcodes.LONG;
                    continue;
                }
                case 'D': {
                    currentFrameLocalTypes[currentFrameLocalCount++] = Opcodes.DOUBLE;
                    continue;
                }
                case '[': {
                    while (currentMethodDescriptor.charAt(n) == '[') {
                        ++n;
                    }
                    if (currentMethodDescriptor.charAt(n) == 'L') {
                        ++n;
                        while (currentMethodDescriptor.charAt(n) != ';') {
                            ++n;
                        }
                    }
                    currentFrameLocalTypes[currentFrameLocalCount++] = currentMethodDescriptor.substring(beginIndex, ++n);
                    continue;
                }
                case 'L': {
                    while (currentMethodDescriptor.charAt(n) != ';') {
                        ++n;
                    }
                    currentFrameLocalTypes[currentFrameLocalCount++] = currentMethodDescriptor.substring(beginIndex + 1, n++);
                    continue;
                }
                default: {
                    context.currentFrameLocalCount = currentFrameLocalCount;
                }
            }
        }
    }
    
    private int readStackMapFrame(final int n, final boolean b, final boolean b2, final Context context) {
        int n2 = n;
        final char[] charBuffer = context.charBuffer;
        final Label[] currentMethodLabels = context.currentMethodLabels;
        int n3;
        if (b) {
            n3 = (this.classFileBuffer[n2++] & 0xFF);
        }
        else {
            n3 = 255;
            context.currentFrameOffset = -1;
        }
        context.currentFrameLocalCountDelta = 0;
        int unsignedShort;
        if (n3 < 64) {
            unsignedShort = n3;
            context.currentFrameType = 3;
            context.currentFrameStackCount = 0;
        }
        else if (n3 < 128) {
            unsignedShort = n3 - 64;
            n2 = this.readVerificationTypeInfo(n2, context.currentFrameStackTypes, 0, charBuffer, currentMethodLabels);
            context.currentFrameType = 4;
            context.currentFrameStackCount = 1;
        }
        else {
            if (n3 < 247) {
                throw new IllegalArgumentException();
            }
            unsignedShort = this.readUnsignedShort(n2);
            n2 += 2;
            if (n3 == 247) {
                n2 = this.readVerificationTypeInfo(n2, context.currentFrameStackTypes, 0, charBuffer, currentMethodLabels);
                context.currentFrameType = 4;
                context.currentFrameStackCount = 1;
            }
            else if (n3 >= 248 && n3 < 251) {
                context.currentFrameType = 2;
                context.currentFrameLocalCountDelta = 251 - n3;
                context.currentFrameLocalCount -= context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            }
            else if (n3 == 251) {
                context.currentFrameType = 3;
                context.currentFrameStackCount = 0;
            }
            else if (n3 < 255) {
                int n4 = b2 ? context.currentFrameLocalCount : 0;
                for (int i = n3 - 251; i > 0; --i) {
                    n2 = this.readVerificationTypeInfo(n2, context.currentFrameLocalTypes, n4++, charBuffer, currentMethodLabels);
                }
                context.currentFrameType = 1;
                context.currentFrameLocalCountDelta = n3 - 251;
                context.currentFrameLocalCount += context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            }
            else {
                final int unsignedShort2 = this.readUnsignedShort(n2);
                n2 += 2;
                context.currentFrameType = 0;
                context.currentFrameLocalCountDelta = unsignedShort2;
                context.currentFrameLocalCount = unsignedShort2;
                for (int j = 0; j < unsignedShort2; ++j) {
                    n2 = this.readVerificationTypeInfo(n2, context.currentFrameLocalTypes, j, charBuffer, currentMethodLabels);
                }
                final int unsignedShort3 = this.readUnsignedShort(n2);
                n2 += 2;
                context.currentFrameStackCount = unsignedShort3;
                for (int k = 0; k < unsignedShort3; ++k) {
                    n2 = this.readVerificationTypeInfo(n2, context.currentFrameStackTypes, k, charBuffer, currentMethodLabels);
                }
            }
        }
        this.createLabel(context.currentFrameOffset += unsignedShort + 1, currentMethodLabels);
        return n2;
    }
    
    private int readVerificationTypeInfo(final int n, final Object[] array, final int n2, final char[] array2, final Label[] array3) {
        int n3 = n;
        switch (this.classFileBuffer[n3++] & 0xFF) {
            case 0: {
                array[n2] = Opcodes.TOP;
                break;
            }
            case 1: {
                array[n2] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                array[n2] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                array[n2] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                array[n2] = Opcodes.LONG;
                break;
            }
            case 5: {
                array[n2] = Opcodes.NULL;
                break;
            }
            case 6: {
                array[n2] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                array[n2] = this.readClass(n3, array2);
                n3 += 2;
                break;
            }
            case 8: {
                array[n2] = this.createLabel(this.readUnsignedShort(n3), array3);
                n3 += 2;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        return n3;
    }
    
    final int getFirstAttributeOffset() {
        int n = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;
        int unsignedShort = this.readUnsignedShort(n);
        n += 2;
        while (unsignedShort-- > 0) {
            int unsignedShort2 = this.readUnsignedShort(n + 6);
            n += 8;
            while (unsignedShort2-- > 0) {
                n += 6 + this.readInt(n + 2);
            }
        }
        int unsignedShort3 = this.readUnsignedShort(n);
        n += 2;
        while (unsignedShort3-- > 0) {
            int unsignedShort4 = this.readUnsignedShort(n + 6);
            n += 8;
            while (unsignedShort4-- > 0) {
                n += 6 + this.readInt(n + 2);
            }
        }
        return n + 2;
    }
    
    private int[] readBootstrapMethodsAttribute(final int n) {
        final char[] array = new char[n];
        int firstAttributeOffset = this.getFirstAttributeOffset();
        for (int i = this.readUnsignedShort(firstAttributeOffset - 2); i > 0; --i) {
            final String utf8 = this.readUTF8(firstAttributeOffset, array);
            final int int1 = this.readInt(firstAttributeOffset + 2);
            firstAttributeOffset += 6;
            if ("BootstrapMethods".equals(utf8)) {
                final int[] array2 = new int[this.readUnsignedShort(firstAttributeOffset)];
                int n2 = firstAttributeOffset + 2;
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = n2;
                    n2 += 4 + this.readUnsignedShort(n2 + 2) * 2;
                }
                return array2;
            }
            firstAttributeOffset += int1;
        }
        throw new IllegalArgumentException();
    }
    
    private Attribute readAttribute(final Attribute[] array, final String anObject, final int n, final int n2, final char[] array2, final int n3, final Label[] array3) {
        for (final Attribute attribute : array) {
            if (attribute.type.equals(anObject)) {
                return attribute.read(this, n, n2, array2, n3, array3);
            }
        }
        return new Attribute(anObject).read(this, n, n2, null, -1, null);
    }
    
    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }
    
    public int getItem(final int n) {
        return this.cpInfoOffsets[n];
    }
    
    public int getMaxStringLength() {
        return this.maxStringLength;
    }
    
    public int readByte(final int n) {
        return this.classFileBuffer[n] & 0xFF;
    }
    
    public int readUnsignedShort(final int n) {
        final byte[] classFileBuffer = this.classFileBuffer;
        return (classFileBuffer[n] & 0xFF) << 8 | (classFileBuffer[n + 1] & 0xFF);
    }
    
    public short readShort(final int n) {
        final byte[] classFileBuffer = this.classFileBuffer;
        return (short)((classFileBuffer[n] & 0xFF) << 8 | (classFileBuffer[n + 1] & 0xFF));
    }
    
    public int readInt(final int n) {
        final byte[] classFileBuffer = this.classFileBuffer;
        return (classFileBuffer[n] & 0xFF) << 24 | (classFileBuffer[n + 1] & 0xFF) << 16 | (classFileBuffer[n + 2] & 0xFF) << 8 | (classFileBuffer[n + 3] & 0xFF);
    }
    
    public long readLong(final int n) {
        return (long)this.readInt(n) << 32 | ((long)this.readInt(n + 4) & 0xFFFFFFFFL);
    }
    
    public String readUTF8(final int n, final char[] array) {
        final int unsignedShort = this.readUnsignedShort(n);
        if (n == 0 || unsignedShort == 0) {
            return null;
        }
        return this.readUtf(unsignedShort, array);
    }
    
    final String readUtf(final int n, final char[] array) {
        final String s = this.constantUtf8Values[n];
        if (s != null) {
            return s;
        }
        final int n2 = this.cpInfoOffsets[n];
        return this.constantUtf8Values[n] = this.readUtf(n2 + 2, this.readUnsignedShort(n2), array);
    }
    
    private String readUtf(final int n, final int n2, final char[] value) {
        int i = n;
        final int n3 = i + n2;
        int count = 0;
        final byte[] classFileBuffer = this.classFileBuffer;
        while (i < n3) {
            final byte b = classFileBuffer[i++];
            if ((b & 0x80) == 0x0) {
                value[count++] = (char)(b & 0x7F);
            }
            else if ((b & 0xE0) == 0xC0) {
                value[count++] = (char)(((b & 0x1F) << 6) + (classFileBuffer[i++] & 0x3F));
            }
            else {
                value[count++] = (char)(((b & 0xF) << 12) + ((classFileBuffer[i++] & 0x3F) << 6) + (classFileBuffer[i++] & 0x3F));
            }
        }
        return new String(value, 0, count);
    }
    
    private String readStringish(final int n, final char[] array) {
        return this.readUTF8(this.cpInfoOffsets[this.readUnsignedShort(n)], array);
    }
    
    public String readClass(final int n, final char[] array) {
        return this.readStringish(n, array);
    }
    
    public String readModule(final int n, final char[] array) {
        return this.readStringish(n, array);
    }
    
    public String readPackage(final int n, final char[] array) {
        return this.readStringish(n, array);
    }
    
    private ConstantDynamic readConstantDynamic(final int n, final char[] array) {
        final ConstantDynamic constantDynamic = this.constantDynamicValues[n];
        if (constantDynamic != null) {
            return constantDynamic;
        }
        final int n2 = this.cpInfoOffsets[n];
        final int n3 = this.cpInfoOffsets[this.readUnsignedShort(n2 + 2)];
        final String utf8 = this.readUTF8(n3, array);
        final String utf9 = this.readUTF8(n3 + 2, array);
        int n4 = this.bootstrapMethodOffsets[this.readUnsignedShort(n2)];
        final Handle handle = (Handle)this.readConst(this.readUnsignedShort(n4), array);
        final Object[] array2 = new Object[this.readUnsignedShort(n4 + 2)];
        n4 += 4;
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = this.readConst(this.readUnsignedShort(n4), array);
            n4 += 2;
        }
        return this.constantDynamicValues[n] = new ConstantDynamic(utf8, utf9, handle, array2);
    }
    
    public Object readConst(final int n, final char[] array) {
        final int n2 = this.cpInfoOffsets[n];
        switch (this.classFileBuffer[n2 - 1]) {
            case 3: {
                return this.readInt(n2);
            }
            case 4: {
                return Float.intBitsToFloat(this.readInt(n2));
            }
            case 5: {
                return this.readLong(n2);
            }
            case 6: {
                return Double.longBitsToDouble(this.readLong(n2));
            }
            case 7: {
                return Type.getObjectType(this.readUTF8(n2, array));
            }
            case 8: {
                return this.readUTF8(n2, array);
            }
            case 16: {
                return Type.getMethodType(this.readUTF8(n2, array));
            }
            case 15: {
                final int byte1 = this.readByte(n2);
                final int n3 = this.cpInfoOffsets[this.readUnsignedShort(n2 + 1)];
                final int n4 = this.cpInfoOffsets[this.readUnsignedShort(n3 + 2)];
                return new Handle(byte1, this.readClass(n3, array), this.readUTF8(n4, array), this.readUTF8(n4 + 2, array), this.classFileBuffer[n3 - 1] == 11);
            }
            case 17: {
                return this.readConstantDynamic(n, array);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}
