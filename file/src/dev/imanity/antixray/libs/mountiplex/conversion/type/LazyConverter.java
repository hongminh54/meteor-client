// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import java.util.ArrayList;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.LazyInitializedObject;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public final class LazyConverter<I, O> extends Converter<I, O> implements LazyInitializedObject
{
    public Converter<I, O> converter;
    private static final LazyConverter UNINITIALIZED;
    
    private LazyConverter(final Converter<I, O> converter) {
        super(converter.input, converter.output);
        this.converter = converter;
    }
    
    private LazyConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
        this.converter = new InitConverter(typeDeclaration, typeDeclaration2);
    }
    
    public boolean isAvailable() {
        final Converter<I, O> converter = this.converter;
        if (converter instanceof LazyInitializedObject) {
            ((LazyInitializedObject)converter).forceInitialization();
        }
        return !(this.converter instanceof FailingConverter);
    }
    
    public void whenFailing(final FailCallback failCallback) {
        final Converter<I, O> converter = this.converter;
        if (converter instanceof InitConverter) {
            ((InitConverter)converter).failConsumers.add(failCallback);
        }
        else if (converter instanceof FailingConverter) {
            failCallback.failed(converter.input, converter.output);
        }
    }
    
    @Override
    public O convertInput(final I n) {
        return this.converter.convertInput(n);
    }
    
    @Override
    public boolean acceptsNullInput() {
        return this.converter.acceptsNullInput();
    }
    
    @Override
    public void forceInitialization() {
        final Converter<I, O> converter = this.converter;
        if (converter instanceof LazyInitializedObject) {
            ((LazyInitializedObject)converter).forceInitialization();
        }
        final Converter<I, O> converter2 = this.converter;
        if (converter2 instanceof FailingConverter) {
            throw new UnsupportedOperationException(((FailingConverter)converter2).getMessage());
        }
    }
    
    public static <I, O> LazyConverter<I, O> uninitialized() {
        return LazyConverter.UNINITIALIZED;
    }
    
    public static <I, O> LazyConverter<I, O> of(final Converter<I, O> converter) {
        return new LazyConverter<I, O>(converter);
    }
    
    public static <I, O> LazyConverter<I, O> create(final Class<?> clazz, final Class<?> clazz2) {
        return new LazyConverter<I, O>(TypeDeclaration.fromClass(clazz), TypeDeclaration.fromClass(clazz2));
    }
    
    public static <I, O> LazyConverter<I, O> create(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        return new LazyConverter<I, O>(typeDeclaration, typeDeclaration2);
    }
    
    static {
        UNINITIALIZED = of(FailingConverter.uninitialized());
    }
    
    private final class InitConverter extends Converter<I, O> implements LazyInitializedObject
    {
        private final List<FailCallback> failConsumers;
        
        public InitConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            super(typeDeclaration, typeDeclaration2);
            this.failConsumers = new ArrayList<FailCallback>();
        }
        
        @Override
        public void forceInitialization() {
            this.init();
        }
        
        private Converter<I, O> init() {
            final Converter<Object, Object> find = Conversion.find(this.input, this.output);
            if (find != null) {
                LazyConverter.this.converter = (Converter<I, O>)find;
            }
            else {
                LazyConverter.this.converter = (Converter<I, O>)FailingConverter.create(this.input, this.output, "Converter not found: " + this.input.toString(true) + " -> " + this.output.toString(true));
                final Iterator<FailCallback> iterator = this.failConsumers.iterator();
                while (iterator.hasNext()) {
                    iterator.next().failed(this.input, this.output);
                }
            }
            return LazyConverter.this.converter;
        }
        
        @Override
        public O convertInput(final I n) {
            return this.init().convertInput(n);
        }
        
        @Override
        public boolean acceptsNullInput() {
            return this.init().acceptsNullInput();
        }
    }
    
    public interface FailCallback
    {
        void failed(final TypeDeclaration p0, final TypeDeclaration p1);
    }
}
