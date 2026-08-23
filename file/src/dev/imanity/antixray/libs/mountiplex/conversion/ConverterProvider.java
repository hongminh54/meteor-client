// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion;

import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public interface ConverterProvider
{
    void getConverters(final TypeDeclaration p0, final List<Converter<?, ?>> p1);
}
