// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import java.util.List;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public final class ChainConverter<I, O> extends Converter<I, O>
{
    private final Converter<Object, Object>[] converters;
    private final boolean acceptsNull;
    
    public ChainConverter(final List<Converter<?, ?>> list) {
        super(list.get(0).input, list.get(list.size() - 1).output);
        this.converters = list.toArray(new Converter[list.size()]);
        this.acceptsNull = this.converters[0].acceptsNullInput();
    }
    
    @Override
    public O convertInput(final I n) {
        Object convertInput = n;
        final Converter<Object, Object>[] converters = this.converters;
        for (int length = converters.length, i = 0; i < length; ++i) {
            convertInput = converters[i].convertInput(convertInput);
            if (convertInput == null) {
                break;
            }
        }
        return (O)convertInput;
    }
    
    @Override
    public boolean acceptsNullInput() {
        return this.acceptsNull;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" {\n");
        final Converter<Object, Object>[] converters = this.converters;
        for (int length = converters.length, i = 0; i < length; ++i) {
            sb.append("  -> ").append(converters[i].toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int getCost() {
        int n = 0;
        final Converter<Object, Object>[] converters = this.converters;
        for (int length = converters.length, i = 0; i < length; ++i) {
            n += converters[i].getCost();
        }
        return n;
    }
}
