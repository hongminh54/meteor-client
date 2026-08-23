// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public abstract class DuplexConverter<A, B> extends Converter<A, B>
{
    protected DuplexConverter<B, A> reverse;
    
    public DuplexConverter(final Class<?> clazz, final Class<?> clazz2) {
        super(clazz, clazz2);
        this.reverse = new ReverseDuplexConverter();
    }
    
    public DuplexConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
        this.reverse = new ReverseDuplexConverter();
    }
    
    protected DuplexConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final DuplexConverter<B, A> reverse) {
        super(typeDeclaration, typeDeclaration2);
        this.reverse = reverse;
    }
    
    @Override
    public abstract B convertInput(final A p0);
    
    public abstract A convertOutput(final B p0);
    
    public final A convertReverse(final Object o) {
        A a = null;
        if (o != null) {
            Class<?> clazz = this.output.type;
            if (this.output.isPrimitive) {
                clazz = BoxedType.getBoxedType(clazz);
            }
            if (clazz.isAssignableFrom(o.getClass())) {
                a = this.convertOutput(o);
            }
        }
        else if (this.acceptsNullOutput()) {
            a = this.convertOutput(null);
        }
        if (a == null && this.input.isPrimitive) {
            a = BoxedType.getDefaultValue(this.input.type);
        }
        return a;
    }
    
    public final A convertReverse(final Object o, final A a) {
        A a2 = null;
        if (o != null) {
            Class<?> clazz = this.output.type;
            if (this.output.isPrimitive) {
                clazz = BoxedType.getBoxedType(clazz);
            }
            if (clazz.isAssignableFrom(o.getClass())) {
                a2 = this.convertOutput(o);
            }
        }
        else if (this.acceptsNullOutput()) {
            a2 = this.convertOutput(null);
        }
        if (a2 == null) {
            a2 = a;
        }
        if (a2 == null && this.input.isPrimitive) {
            a2 = BoxedType.getDefaultValue(this.input.type);
        }
        return a2;
    }
    
    public boolean acceptsNullOutput() {
        return false;
    }
    
    public Converter<A, B> getInputConverter() {
        return this;
    }
    
    public Converter<B, A> getOutputConverter() {
        return (Converter<B, A>)this.reverse;
    }
    
    public final DuplexConverter<B, A> reverse() {
        return this.reverse;
    }
    
    public static <A, B> DuplexConverter<A, B> pair(final Converter<?, B> converter, final Converter<?, A> converter2) {
        if (converter == null || converter2 == null) {
            return null;
        }
        if (!converter.output.isInstanceOf(converter2.input)) {
            throw new RuntimeException("Converter output of " + converter.toString() + " can not be assigned to the input of " + converter2.toString());
        }
        if (!converter2.output.isInstanceOf(converter.input)) {
            throw new RuntimeException("Reverse converter output of " + converter.toString() + " can not be assigned to the input of " + converter2.toString());
        }
        if (isNullConverter(converter)) {
            if (isNullConverter(converter2)) {
                return createNull(converter.output);
            }
            if (converter2 instanceof CastingConverter) {
                return new DuplexCastingAdapter<B, A>(converter2.output, converter.output).reverse();
            }
            return new DuplexHalfAdapter<B, A>(converter2, converter.output).reverse();
        }
        else if (isNullConverter(converter2)) {
            if (converter instanceof CastingConverter) {
                return new DuplexCastingAdapter<A, B>(converter.output, converter2.output);
            }
            return new DuplexHalfAdapter<A, B>(converter, converter2.output);
        }
        else {
            if (converter instanceof DuplexConverter) {
                final DuplexConverter duplexConverter = (DuplexConverter)converter;
                if (duplexConverter.reverse() == converter2) {
                    return duplexConverter;
                }
            }
            if (converter instanceof AnnotatedConverter && converter2 instanceof AnnotatedConverter) {
                return new DuplexAnnotatedConverter<A, B>((AnnotatedConverter)converter, (AnnotatedConverter)converter2);
            }
            return new DuplexAdapter<A, B>(converter, converter2);
        }
    }
    
    public static <A, B> DuplexConverter<A, B> createNull(final TypeDeclaration typeDeclaration) {
        return (DuplexConverter<A, B>)new DuplexNullConverter(typeDeclaration);
    }
    
    private static boolean isNullConverter(final Converter<?, ?> converter) {
        return converter instanceof NullConverter || converter instanceof DuplexNullConverter;
    }
    
    private final class ReverseDuplexConverter extends DuplexConverter<B, A>
    {
        public ReverseDuplexConverter() {
            super(DuplexConverter.this.output, DuplexConverter.this.input, DuplexConverter.this);
        }
        
        @Override
        public A convertInput(final B b) {
            return DuplexConverter.this.convertOutput(b);
        }
        
        @Override
        public B convertOutput(final A a) {
            return DuplexConverter.this.convertInput(a);
        }
        
        @Override
        public boolean acceptsNullInput() {
            return DuplexConverter.this.acceptsNullOutput();
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return DuplexConverter.this.acceptsNullInput();
        }
        
        @Override
        public Converter<B, A> getInputConverter() {
            return DuplexConverter.this.getOutputConverter();
        }
        
        @Override
        public Converter<A, B> getOutputConverter() {
            return DuplexConverter.this.getInputConverter();
        }
    }
    
    private static final class DuplexAdapter<A, B> extends DuplexConverter<A, B>
    {
        private final Converter<A, B> input_converter;
        private final Converter<B, A> output_converter;
        
        public DuplexAdapter(final Converter<?, B> converter, final Converter<?, A> converter2) {
            this(converter, converter2, null);
            this.reverse = (DuplexConverter<B, A>)new DuplexAdapter<Object, Object>((Converter<?, A>)converter2, (Converter<?, B>)converter, (DuplexAdapter<A, B>)this);
        }
        
        private DuplexAdapter(final Converter<?, B> input_converter, final Converter<?, A> output_converter, final DuplexAdapter<B, A> duplexAdapter) {
            super(output_converter.output, input_converter.output, (DuplexConverter<Object, Object>)duplexAdapter);
            this.input_converter = (Converter<A, B>)input_converter;
            this.output_converter = (Converter<B, A>)output_converter;
        }
        
        @Override
        public B convertInput(final A a) {
            return this.input_converter.convertInput(a);
        }
        
        @Override
        public A convertOutput(final B b) {
            return this.output_converter.convertInput(b);
        }
        
        @Override
        public boolean acceptsNullInput() {
            return this.input_converter.acceptsNullInput();
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return this.output_converter.acceptsNullInput();
        }
        
        @Override
        public Converter<A, B> getInputConverter() {
            return this.input_converter;
        }
        
        @Override
        public Converter<B, A> getOutputConverter() {
            return this.output_converter;
        }
        
        @Override
        public String toString() {
            return "DuplexAdapter{" + this.output_converter.output.toString() + " <> " + this.input_converter.output.toString() + "}";
        }
    }
    
    private static final class DuplexHalfAdapter<A, B> extends DuplexConverter<A, B>
    {
        private final Converter<A, B> converter;
        private final TypeDeclaration reverseOutput;
        
        public DuplexHalfAdapter(final Converter<?, B> converter, final TypeDeclaration reverseOutput) {
            super(reverseOutput, converter.output);
            this.converter = (Converter<A, B>)converter;
            this.reverseOutput = reverseOutput;
        }
        
        @Override
        public B convertInput(final A a) {
            return this.converter.convertInput(a);
        }
        
        @Override
        public A convertOutput(final B b) {
            return (A)b;
        }
        
        @Override
        public boolean acceptsNullInput() {
            return this.converter.acceptsNullInput();
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return true;
        }
        
        @Override
        public Converter<A, B> getInputConverter() {
            return this.converter;
        }
        
        @Override
        public Converter<B, A> getOutputConverter() {
            return (Converter<B, A>)new NullConverter(this.converter.output, this.reverseOutput);
        }
        
        @Override
        public String toString() {
            return "DuplexHalfAdapter{" + this.reverseOutput.toString() + " <> " + this.converter.output.toString() + "}";
        }
    }
    
    private static final class DuplexNullConverter<A> extends DuplexConverter<A, A>
    {
        public DuplexNullConverter(final TypeDeclaration typeDeclaration) {
            super(typeDeclaration, typeDeclaration, null);
            this.reverse = (DuplexConverter<B, A>)this;
        }
        
        @Override
        public A convertInput(final A a) {
            return a;
        }
        
        @Override
        public A convertOutput(final A a) {
            return a;
        }
        
        @Override
        public boolean acceptsNullInput() {
            return true;
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return true;
        }
        
        @Override
        public String toString() {
            return "DuplexNullConverter{" + this.input + "}";
        }
    }
    
    private static final class DuplexCastingAdapter<A, B> extends DuplexConverter<A, B>
    {
        public DuplexCastingAdapter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            super(typeDeclaration, typeDeclaration2);
        }
        
        @Override
        public B convertInput(final A a) {
            if (this.output.isAssignableFrom(a)) {
                return (B)a;
            }
            return null;
        }
        
        @Override
        public A convertOutput(final B b) {
            return (A)b;
        }
        
        @Override
        public boolean acceptsNullInput() {
            return false;
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return true;
        }
        
        @Override
        public String toString() {
            return "DuplexCastingAdapter{" + this.input + " <> " + this.output.toString() + "}";
        }
    }
    
    private static final class DuplexAnnotatedConverter<A, B> extends DuplexConverter<A, B>
    {
        private final Invoker<Object> converterInvoker;
        private final Invoker<Object> reverseInvoker;
        private final boolean converterAcceptNull;
        private final boolean reverseAcceptNull;
        
        public DuplexAnnotatedConverter(final AnnotatedConverter annotatedConverter, final AnnotatedConverter annotatedConverter2) {
            this(annotatedConverter, annotatedConverter2, null);
            this.reverse = (DuplexConverter<B, A>)new DuplexAnnotatedConverter<Object, Object>(annotatedConverter2, annotatedConverter, (DuplexAnnotatedConverter<A, B>)this);
        }
        
        public DuplexAnnotatedConverter(final AnnotatedConverter annotatedConverter, final AnnotatedConverter annotatedConverter2, final DuplexAnnotatedConverter<B, A> duplexAnnotatedConverter) {
            super(annotatedConverter2.output, annotatedConverter.output, (DuplexConverter<Object, Object>)duplexAnnotatedConverter);
            this.converterInvoker = InitInvoker.proxy(this, "converterInvoker", annotatedConverter.invoker);
            this.reverseInvoker = InitInvoker.proxy(this, "reverseInvoker", annotatedConverter2.invoker);
            this.converterAcceptNull = annotatedConverter.acceptsNullInput();
            this.reverseAcceptNull = annotatedConverter2.acceptsNullInput();
        }
        
        @Override
        public B convertInput(final A a) {
            return (B)this.converterInvoker.invoke(null, a);
        }
        
        @Override
        public A convertOutput(final B b) {
            return (A)this.reverseInvoker.invoke(null, b);
        }
        
        @Override
        public boolean acceptsNullInput() {
            return this.converterAcceptNull;
        }
        
        @Override
        public boolean acceptsNullOutput() {
            return this.reverseAcceptNull;
        }
    }
}
