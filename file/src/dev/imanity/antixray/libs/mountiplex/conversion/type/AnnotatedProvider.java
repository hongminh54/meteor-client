// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;

public class AnnotatedProvider implements ConverterProvider
{
    public final Method method;
    
    public AnnotatedProvider(final Method method) {
        this.method = method;
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Converter provider method is not static");
        }
        final Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 2 || !parameterTypes[0].equals(TypeDeclaration.class) || !parameterTypes[1].equals(List.class)) {
            throw new IllegalArgumentException("Converter provider method does not have the expected method signature");
        }
    }
    
    @Override
    public void getConverters(final TypeDeclaration obj, final List<Converter<?, ?>> list) {
        try {
            this.method.invoke(null, obj, list);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to get suitable converters for " + obj, thrown);
        }
    }
}
