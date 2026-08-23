// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;

public class MethodMatchResolver
{
    public static void match(final Class<?> clazz, final ClassResolver classResolver, final MethodDeclaration[] array) {
        MethodDeclaration[] array2;
        try {
            array2 = Stream.concat((Stream<?>)ReflectionUtil.getDeclaredMethods(clazz), (Stream<?>)ReflectionUtil.getMethods(clazz).filter(method -> !Modifier.isStatic(method.getModifiers()))).filter((Predicate<? super Object>)ReflectionUtil.createDuplicateMethodFilter()).map(method2 -> new MethodDeclaration(classResolver2, method2)).toArray(MethodDeclaration[]::new);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to identify methods of class " + MPLType.getName(clazz), thrown);
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final MethodDeclaration methodDeclaration = array[i];
            if (methodDeclaration.body == null) {
                final MethodDeclaration resolveName = methodDeclaration.resolveName();
                boolean b = false;
                for (final MethodDeclaration methodDeclaration2 : array2) {
                    if (methodDeclaration2.match(resolveName)) {
                        if (!methodDeclaration2.modifiers.match(methodDeclaration.modifiers) && methodDeclaration2.modifiers.getProtectionLevel() > methodDeclaration.modifiers.getProtectionLevel()) {
                            MountiplexUtil.LOGGER.log(Level.WARNING, "Method modifiers of " + classResolver.getDeclaredClassName() + " " + methodDeclaration.toString() + " do not match (" + methodDeclaration2.modifiers + " expected)");
                        }
                        methodDeclaration.method = methodDeclaration2.method;
                        methodDeclaration.constructor = methodDeclaration2.constructor;
                        b = true;
                        break;
                    }
                }
                if (!b && !methodDeclaration.modifiers.isOptional()) {
                    FieldLCSResolver.logAlternatives("method", array2, resolveName, false);
                }
            }
        }
    }
}
