// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.conversion.annotations.ConverterMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;

public class AnnotatedConverter extends RawConverter
{
    public Invoker<Object> invoker;
    public final boolean isUpcast;
    private final boolean nullInput;
    private final int cost;
    
    public AnnotatedConverter(final MethodDeclaration methodDeclaration, final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        this(methodDeclaration, null, typeDeclaration, typeDeclaration2, false);
    }
    
    public AnnotatedConverter(final MethodDeclaration methodDeclaration, final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final boolean b) {
        this(methodDeclaration, null, typeDeclaration, typeDeclaration2, b);
    }
    
    private AnnotatedConverter(final MethodDeclaration methodDeclaration, final Invoker<Object> invoker, final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final boolean isUpcast) {
        super(typeDeclaration, typeDeclaration2);
        if (invoker == null) {
            this.invoker = InitInvoker.forMethod(this, "invoker", methodDeclaration);
        }
        else {
            this.invoker = InitInvoker.proxy(this, "invoker", invoker);
        }
        this.isUpcast = isUpcast;
        final ConverterMethod converterMethod = (methodDeclaration.method == null) ? null : methodDeclaration.method.getAnnotation(ConverterMethod.class);
        this.nullInput = (converterMethod != null && converterMethod.acceptsNull());
        this.cost = ((converterMethod == null) ? 1 : converterMethod.cost());
    }
    
    @Override
    public Object convertInput(final Object o) {
        final Object invoke = this.invoker.invoke(null, o);
        if (!this.isUpcast || this.output.isAssignableFrom(invoke)) {
            return invoke;
        }
        return null;
    }
    
    @Override
    public int getCost() {
        return this.cost;
    }
    
    @Override
    public boolean acceptsNullInput() {
        return this.nullInput;
    }
    
    public static TypeDeclaration parseType(final Method method, final boolean b) {
        final ClassResolver default1 = ClassResolver.DEFAULT;
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Method is not static");
        }
        if (method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("Method has no return type");
        }
        if (method.getParameterTypes().length != 1) {
            throw new IllegalArgumentException("Method does not have one parameter");
        }
        method.setAccessible(true);
        final ConverterMethod converterMethod = method.getAnnotation(ConverterMethod.class);
        final String s = (converterMethod == null) ? "" : (b ? converterMethod.input() : converterMethod.output());
        if (s.length() <= 0) {
            return TypeDeclaration.fromType(default1, b ? method.getGenericParameterTypes()[0] : method.getGenericReturnType());
        }
        final TypeDeclaration parse = TypeDeclaration.parse(default1, s);
        if (!parse.isValid()) {
            throw new IllegalArgumentException("Type is invalid: " + parse.toString());
        }
        if (parse.isResolved()) {
            return parse;
        }
        if (converterMethod.optional()) {
            return null;
        }
        throw new IllegalArgumentException("Type could not be resolved: " + parse.toString());
    }
    
    public static class GenericProvider implements ConverterProvider
    {
        public final TypeDeclaration input;
        public final TypeDeclaration output;
        public final MethodDeclaration method;
        public Invoker<Object> invoker;
        
        public GenericProvider(final MethodDeclaration method, final TypeDeclaration input, final TypeDeclaration output) {
            this.input = input;
            this.output = output;
            this.method = method;
            this.invoker = InitInvoker.forMethod(this, "invoker", method);
        }
        
        @Override
        public void getConverters(final TypeDeclaration typeDeclaration, final List<Converter<?, ?>> list) {
            boolean b = false;
            TypeDeclaration castAsType;
            if (this.output.variableName != null || this.output.isWildcard) {
                b = true;
                if (!this.output.type.isAssignableFrom(typeDeclaration.type)) {
                    return;
                }
                castAsType = typeDeclaration.castAsType(this.output.type);
            }
            else {
                if (!typeDeclaration.type.equals(this.output.type)) {
                    return;
                }
                castAsType = typeDeclaration;
            }
            TypeDeclaration[] genericTypes;
            if (castAsType.genericTypes.length == 0) {
                genericTypes = new TypeDeclaration[0];
            }
            else {
                if (!castAsType.isInstanceOf(this.output) || this.output.genericTypes.length != castAsType.genericTypes.length) {
                    return;
                }
                genericTypes = this.input.genericTypes.clone();
                for (int i = 0; i < genericTypes.length; ++i) {
                    final String variableName = genericTypes[i].variableName;
                    if (variableName != null) {
                        boolean b2 = false;
                        for (int j = 0; j < this.output.genericTypes.length; ++j) {
                            if (variableName.equals(this.output.genericTypes[i].variableName)) {
                                genericTypes[i] = castAsType.genericTypes[i];
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2) {
                            return;
                        }
                    }
                }
            }
            list.add(new AnnotatedConverter(this.method, this.invoker, this.input.setGenericTypes(genericTypes), typeDeclaration, b, null));
        }
    }
}
