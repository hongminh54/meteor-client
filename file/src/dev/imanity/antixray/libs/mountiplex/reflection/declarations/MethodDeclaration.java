// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.javassist.CtClass;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist.MPLCtNewMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.MethodBodyBuilder;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.DeclarationParserGroups;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.lang.reflect.Type;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.GenericSignatureFormatError;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Executable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodDeclaration extends Declaration
{
    public Method method;
    public Constructor<Object> constructor;
    public final boolean isRecordFieldChanger;
    public final ModifierDeclaration modifiers;
    public final TypeDeclaration returnType;
    public final NameDeclaration name;
    public final ParameterListDeclaration parameters;
    public final String body;
    public final Requirement[] bodyRequirements;
    
    public MethodDeclaration(final ClassResolver classResolver, final Constructor<?> constructor) {
        super(classResolver);
        try {
            this.method = null;
            this.constructor = (Constructor<Object>)constructor;
            this.isRecordFieldChanger = false;
            this.modifiers = new ModifierDeclaration(classResolver, constructor.getModifiers());
            this.returnType = TypeDeclaration.fromClass(constructor.getDeclaringClass());
            this.name = new NameDeclaration(classResolver, "<init>", null);
            this.parameters = new ParameterListDeclaration(classResolver, constructor.getGenericParameterTypes());
            this.body = null;
            this.bodyRequirements = new Requirement[0];
        }
        catch (final Throwable cause) {
            throw new IllegalStateException("Failed to read details of " + toDebugString(constructor), cause);
        }
    }
    
    public MethodDeclaration(final ClassResolver classResolver, final Method method) {
        super(classResolver);
        final String name = MPLType.getName(method);
        String resolveMethodAlias = Resolver.resolveMethodAlias(method, name);
        if (name.equals(resolveMethodAlias)) {
            resolveMethodAlias = null;
        }
        try {
            this.method = method;
            this.constructor = null;
            this.isRecordFieldChanger = false;
            this.modifiers = new ModifierDeclaration(classResolver, method.getModifiers() & 0xFFFFFFBF);
            this.name = new NameDeclaration(classResolver, name, resolveMethodAlias);
            Type type;
            Type[] array;
            try {
                type = method.getGenericReturnType();
                array = method.getGenericParameterTypes();
            }
            catch (final GenericSignatureFormatError | TypeNotPresentException | MalformedParameterizedTypeException ex) {
                type = method.getReturnType();
                array = method.getParameterTypes();
                MountiplexUtil.LOGGER.warning("Generic type information about method " + toDebugString(method) + " is corrupt: " + ((Throwable)ex).getMessage() + " - using a non-generic fallback representation");
            }
            this.returnType = TypeDeclaration.fromType(classResolver, type);
            this.parameters = new ParameterListDeclaration(classResolver, array);
            this.body = null;
            this.bodyRequirements = new Requirement[0];
        }
        catch (final Throwable cause) {
            throw new IllegalStateException("Failed to read details of " + toDebugString(method), cause);
        }
    }
    
    public MethodDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public MethodDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver, stringBuffer);
        this.method = null;
        this.constructor = null;
        this.modifiers = this.nextModifier();
        final BaseDeclarationParserContext baseDeclarationParserContext = new BaseDeclarationParserContext();
        this.getParserPostfix().trimGenericTypes();
        this.returnType = this.nextType();
        this.name = this.nextName();
        this.parameters = this.nextParameterList();
        this.isRecordFieldChanger = this.name.value().equals("<record_changer>");
        this.getParserPostfix().trimWhitespace(0);
        StringBuffer postfix = this.getPostfix();
        if (postfix != null && postfix.startsWith("{")) {
            final StringBuilder sb = new StringBuilder();
            int n = 0;
            boolean b = false;
            while (true) {
                boolean b2 = false;
                int i;
                for (i = 0; i < postfix.length(); ++i) {
                    final char char1 = postfix.charAt(i);
                    sb.append(char1);
                    if (char1 == '\n') {
                        ++i;
                        while (i < postfix.length() && postfix.charAt(i) == ' ') {
                            sb.append(' ');
                            ++i;
                        }
                        break;
                    }
                    if (char1 == '\"') {
                        b2 = !b2;
                    }
                    else if (!b2) {
                        if (char1 == '{') {
                            ++n;
                        }
                        else if (char1 == '}' && --n == 0) {
                            b = true;
                            ++i;
                            break;
                        }
                    }
                }
                postfix = postfix.substring(i);
                this.setPostfix(postfix);
                if (b || this.getPostfix().length() == 0) {
                    break;
                }
                while (baseDeclarationParserContext.runParsers(DeclarationParserGroups.BASE)) {
                    postfix = this.getPostfix();
                }
            }
            final int lastIndex = sb.lastIndexOf("\n");
            if (lastIndex != -1) {
                for (int n2 = lastIndex + 1; n2 < sb.length() && sb.charAt() == ' '; n2 += 2) {
                    sb.insert(0, ' ');
                }
            }
            if (!this.getResolver().isGenerating()) {
                this.bodyRequirements = this.processRequirements(sb);
            }
            else {
                this.bodyRequirements = new Requirement[0];
            }
            this.body = SourceDeclaration.trimIndentation(sb.toString());
        }
        else {
            this.body = null;
            this.bodyRequirements = new Requirement[0];
            if (postfix != null && postfix.startsWith(";")) {
                this.setPostfix(postfix.substring(1));
            }
        }
        this.getParserPostfix().trimWhitespace(0);
        if (this.getPostfix() != null) {
            this.setPostfix(this.getPostfix().prepend("\n"));
        }
    }
    
    private MethodDeclaration(final MethodDeclaration methodDeclaration, final NameDeclaration name) {
        super(methodDeclaration.getResolver());
        this.method = methodDeclaration.method;
        this.constructor = methodDeclaration.constructor;
        this.isRecordFieldChanger = methodDeclaration.isRecordFieldChanger;
        this.modifiers = methodDeclaration.modifiers;
        this.returnType = methodDeclaration.returnType;
        this.name = name;
        this.parameters = methodDeclaration.parameters;
        this.body = methodDeclaration.body;
        this.bodyRequirements = methodDeclaration.bodyRequirements;
    }
    
    private MethodDeclaration(final MethodDeclaration methodDeclaration, final ParameterListDeclaration parameters) {
        super(methodDeclaration.getResolver());
        this.method = methodDeclaration.method;
        this.constructor = methodDeclaration.constructor;
        this.isRecordFieldChanger = methodDeclaration.isRecordFieldChanger;
        this.modifiers = methodDeclaration.modifiers;
        this.returnType = methodDeclaration.returnType;
        this.name = methodDeclaration.name;
        this.parameters = parameters;
        this.body = methodDeclaration.body;
        this.bodyRequirements = methodDeclaration.bodyRequirements;
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        if (!(declaration instanceof MethodDeclaration)) {
            return 0.0;
        }
        final MethodDeclaration methodDeclaration = (MethodDeclaration)declaration;
        return 0.1 * this.modifiers.similarity(methodDeclaration.modifiers) + 0.3 * this.name.similarity(methodDeclaration.name) + 0.3 * this.returnType.similarity(methodDeclaration.returnType) + 0.3 * this.parameters.similarity(methodDeclaration.parameters);
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        if (declaration instanceof MethodDeclaration) {
            final MethodDeclaration methodDeclaration = (MethodDeclaration)declaration;
            return this.name.match(methodDeclaration.name) && this.returnType.match(methodDeclaration.returnType) && this.parameters.match(methodDeclaration.parameters) && this.modifiers.isStatic() == methodDeclaration.modifiers.isStatic();
        }
        return false;
    }
    
    public boolean matchSignature(final Declaration declaration) {
        if (declaration instanceof MethodDeclaration) {
            final MethodDeclaration methodDeclaration = (MethodDeclaration)declaration;
            return this.modifiers.match(methodDeclaration.modifiers) && this.returnType.match(methodDeclaration.returnType) && this.parameters.match(methodDeclaration.parameters);
        }
        return false;
    }
    
    public Class<?> getDeclaringClass() {
        if (this.body == null && this.method != null) {
            return this.method.getDeclaringClass();
        }
        if (this.body == null && this.constructor != null) {
            return this.constructor.getDeclaringClass();
        }
        return this.getResolver().getDeclaredClass();
    }
    
    public String getASMInvokeDescriptor() {
        Class[] array;
        if (this.modifiers.isStatic()) {
            array = new Class[this.parameters.parameters.length];
            for (int i = 0; i < this.parameters.parameters.length; ++i) {
                array[i] = getAccessibleType(this.parameters.parameters[i].type);
            }
        }
        else {
            array = new Class[this.parameters.parameters.length + 1];
            array[0] = getAccessibleType(this.getDeclaringClass());
            for (int j = 0; j < this.parameters.parameters.length; ++j) {
                array[j + 1] = getAccessibleType(this.parameters.parameters[j].type);
            }
        }
        return MPLType.getMethodDescriptor(this.returnType.type, array);
    }
    
    private static Class<?> getAccessibleType(final TypeDeclaration obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Input type is null");
        }
        if (!obj.isResolved()) {
            throw new IllegalArgumentException("Input type " + obj + " was not resolved");
        }
        return getAccessibleType(obj.type);
    }
    
    private static Class<?> getAccessibleType(final Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Input type is null");
        }
        return Resolver.getMeta(clazz).isPublic ? clazz : Object.class;
    }
    
    @Override
    public String toString(final boolean b) {
        if (!this.isValid()) {
            return "??[" + (Object)this._initialDeclaration + "]??";
        }
        final String string = this.modifiers.toString(b);
        final String string2 = this.returnType.toString(b);
        final String string3 = this.name.toString(b);
        final String string4 = this.parameters.toString(b);
        if (string.length() > 0) {
            return string + " " + string2 + " " + string3 + string4 + ";";
        }
        return string2 + " " + string3 + string4 + ";";
    }
    
    @Override
    public boolean isResolved() {
        return this.modifiers.isResolved() && this.returnType.isResolved() && this.name.isResolved() && this.parameters.isResolved();
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
        sb.append(s).append("Method {\n");
        sb.append(s).append("  declaration=").append(this._initialDeclaration).append('\n');
        sb.append(s).append("  postfix=").append(this.getPostfix()).append('\n');
        this.modifiers.debugString(sb, s + "  ");
        this.returnType.debugString(sb, s + "  ");
        this.name.debugString(sb, s + "  ");
        this.parameters.debugString(sb, s + "  ");
        sb.append(s).append("}\n");
    }
    
    @Override
    public void modifyBodyRequirement(final Requirement requirement, final StringBuilder sb, final String str, final String str2, final int n, final int end) {
        final Class<?> declaringClass = this.getDeclaringClass();
        int n2 = 0;
        if (declaringClass != null && Resolver.isPublic(declaringClass)) {
            if (this.method != null) {
                n2 = (Modifier.isPublic(this.method.getModifiers()) ? 1 : 0);
            }
            else if (this.constructor != null) {
                n2 = (Modifier.isPublic(this.constructor.getModifiers()) ? 1 : 0);
            }
        }
        if (n2 != 0) {
            if (this.returnType.cast != null) {
                n2 = 0;
            }
            else {
                final ParameterDeclaration[] parameters = this.parameters.parameters;
                for (int length = parameters.length, i = 0; i < length; ++i) {
                    if (parameters[i].type.cast != null) {
                        n2 = 0;
                        break;
                    }
                }
            }
        }
        if (n2 != 0) {
            final MethodBodyBuilder methodBodyBuilder = new MethodBodyBuilder();
            if (this.method != null) {
                if (this.modifiers.isStatic()) {
                    methodBodyBuilder.append(MPLType.getName(this.method.getDeclaringClass()));
                }
                else {
                    methodBodyBuilder.append(str);
                }
                methodBodyBuilder.append('.');
                methodBodyBuilder.append("MPL_NOREMAP$");
                methodBodyBuilder.append(MPLType.getName(this.method));
            }
            else if (this.constructor != null) {
                methodBodyBuilder.append("new ");
                methodBodyBuilder.append("MPL_NOREMAP$");
                methodBodyBuilder.append(MPLType.getName(this.constructor.getDeclaringClass()));
            }
            sb.replace(n, end, methodBodyBuilder.toString());
            return;
        }
        int j = end;
        while (j < sb.length()) {
            final char char1 = sb.charAt();
            if (char1 == ' ') {
                ++j;
            }
            else {
                if (char1 == '(') {
                    break;
                }
                j = -1;
                break;
            }
        }
        if (j == -1) {
            if (this.getResolver().getLogErrors()) {
                MountiplexUtil.LOGGER.warning("Requirement refers to method but is used as field");
                MountiplexUtil.LOGGER.warning("Method body: " + str + "#" + str2);
            }
        }
        else {
            final MethodBodyBuilder methodBodyBuilder2 = new MethodBodyBuilder();
            methodBodyBuilder2.append("this.").append(str2);
            methodBodyBuilder2.append('(');
            methodBodyBuilder2.append(str);
            int k = j + 1;
            while (k < sb.length()) {
                final char char2 = sb.charAt();
                if (char2 == ' ') {
                    ++k;
                }
                else {
                    if (char2 == ')') {
                        break;
                    }
                    methodBodyBuilder2.append(", ");
                    break;
                }
            }
            sb.replace(n, j + 1, methodBodyBuilder2.toString());
        }
        requirement.setProperty("generateMethod");
    }
    
    @Override
    public void addAsRequirement(final ExtendedClassWriter<?> extendedClassWriter, final Requirement requirement, final String str) {
        if (!requirement.hasProperty("generateMethod")) {
            return;
        }
        final MethodBodyBuilder methodBodyBuilder = new MethodBodyBuilder();
        methodBodyBuilder.append("private final ");
        methodBodyBuilder.append(ReflectionUtil.getAccessibleTypeName(this.returnType.exposed().type));
        methodBodyBuilder.append(' ').append(str).append('(');
        methodBodyBuilder.append("Object instance");
        for (int i = 0; i < this.parameters.parameters.length; ++i) {
            final ParameterDeclaration parameterDeclaration = this.parameters.parameters[i];
            methodBodyBuilder.append(", ");
            methodBodyBuilder.appendAccessibleTypeName(parameterDeclaration.type.exposed().type);
            methodBodyBuilder.append(' ').append(parameterDeclaration.name.real());
            if ((parameterDeclaration.type.cast != null && parameterDeclaration.type.cast.type != Object.class) || parameterDeclaration.type.isPrimitive) {
                methodBodyBuilder.append("_conv_input");
            }
        }
        methodBodyBuilder.append(") {\n");
        final String string = str + "_method";
        final FastMethod fastMethod = new FastMethod();
        fastMethod.init(this);
        extendedClassWriter.visitSingletonField(string, FastMethod.class, fastMethod);
        final boolean b = this.parameters.parameters.length > 5;
        if (b) {
            methodBodyBuilder.append("  Object[] ").append(str).append("_input_args");
            methodBodyBuilder.append(" = new Object[").append(this.parameters.parameters.length).append("];\n");
        }
        for (int j = 0; j < this.parameters.parameters.length; ++j) {
            final ParameterDeclaration parameterDeclaration2 = this.parameters.parameters[j];
            final boolean b2 = parameterDeclaration2.type.cast != null && parameterDeclaration2.type.cast.type != Object.class;
            if (b) {
                methodBodyBuilder.append("  ").appendFieldName(str, "_input_args").append('[').append(j).append("] = ");
            }
            else if (b2 || parameterDeclaration2.type.isPrimitive) {
                methodBodyBuilder.append("  Object ").append(parameterDeclaration2.name.real()).append(" = ");
            }
            if (parameterDeclaration2.type.cast == null && parameterDeclaration2.type.isPrimitive) {
                methodBodyBuilder.appendBoxPrimitive(parameterDeclaration2.type.type, parameterDeclaration2.name.real(), "_conv_input").appendEnd();
            }
            else if (!b2) {
                if (b) {
                    methodBodyBuilder.append(parameterDeclaration2.name.real()).appendEnd();
                }
            }
            else {
                final String string2 = str + "_conv_" + parameterDeclaration2.name.real();
                final Converter<Object, Object> find = Conversion.find(parameterDeclaration2.type.cast, parameterDeclaration2.type);
                if (find == null) {
                    throw new RuntimeException("Failed to find converter for parameter " + parameterDeclaration2.name.real() + " of method " + str + " (" + parameterDeclaration2.type.cast.toString(true) + " -> " + parameterDeclaration2.type.toString(true) + ")");
                }
                extendedClassWriter.visitSingletonField(string2, Converter.class, find);
                methodBodyBuilder.append("this.").append(string2);
                methodBodyBuilder.append(".convertInput(");
                if (parameterDeclaration2.type.cast.isPrimitive) {
                    methodBodyBuilder.appendBoxPrimitive(parameterDeclaration2.type.cast.type, parameterDeclaration2.name.real(), "_conv_input");
                }
                else {
                    methodBodyBuilder.appendFieldName(parameterDeclaration2.name.real(), "_conv_input");
                }
                methodBodyBuilder.append(')').appendEnd();
            }
        }
        if (!this.returnType.type.equals(Void.TYPE)) {
            methodBodyBuilder.append("  ");
            if (this.returnType.cast != null && this.returnType.cast.type == Object.class) {
                methodBodyBuilder.append("Object ");
                methodBodyBuilder.appendFieldName(str, "_return").append(" = ");
            }
            else if (this.returnType.cast != null) {
                methodBodyBuilder.append("Object ");
                methodBodyBuilder.appendFieldName(str, "_return_conv_input").append(" = ");
            }
            else if (this.returnType.isPrimitive) {
                final Class<?> boxedType = BoxedType.getBoxedType(this.returnType.type);
                methodBodyBuilder.appendTypeName(boxedType).append(' ');
                methodBodyBuilder.appendFieldName(str, "_return").append(" = ");
                methodBodyBuilder.appendTypeCast(boxedType);
            }
            else {
                methodBodyBuilder.appendAccessibleTypeName(this.returnType.type).append(' ');
                methodBodyBuilder.appendFieldName(str, "_return").append(" = ");
                methodBodyBuilder.appendAccessibleTypeCast(this.returnType.type);
            }
        }
        methodBodyBuilder.append("this.").append(string);
        if (b) {
            methodBodyBuilder.append(".invokeVA(instance, ").appendFieldName(str, "_input_args").append(')').appendEnd();
        }
        else {
            methodBodyBuilder.append(".invoke(instance");
            for (int k = 0; k < this.parameters.parameters.length; ++k) {
                methodBodyBuilder.append(", ").append(this.parameters.parameters[k].name.real());
            }
            methodBodyBuilder.append(')').appendEnd();
        }
        if (this.returnType.cast != null && this.returnType.cast.type != Object.class) {
            final String string3 = str + "_conv_return";
            final Converter<Object, Object> find2 = Conversion.find(this.returnType, this.returnType.cast);
            if (find2 == null) {
                throw new RuntimeException("Failed to find converter for return value  of method " + str + " (" + this.returnType.toString(true) + " -> " + this.returnType.cast.toString(true) + ")");
            }
            extendedClassWriter.visitSingletonField(string3, Converter.class, find2);
            Class<?> clazz;
            if (this.returnType.cast.isPrimitive) {
                clazz = BoxedType.getBoxedType(this.returnType.cast.type);
            }
            else {
                clazz = this.returnType.cast.type;
            }
            methodBodyBuilder.append("  ").appendAccessibleTypeName(clazz).append(' ').appendFieldName(str, "_return").append(" = ").appendAccessibleTypeCast(clazz).append("this.").append(string3).append(".convertInput(").appendFieldName(str, "_return_conv_input").append(')').appendEnd();
        }
        if (!this.returnType.type.equals(Void.TYPE)) {
            methodBodyBuilder.append("  return ");
            methodBodyBuilder.appendFieldName(str, "_return");
            if (this.returnType.exposed().isPrimitive) {
                methodBodyBuilder.appendUnboxPrimitive(this.returnType.exposed().type);
            }
            methodBodyBuilder.appendEnd();
        }
        methodBodyBuilder.append("}");
        extendedClassWriter.addJavassist(ctClass2 -> {
            try {
                ctClass2.addMethod(MPLCtNewMethod.make(methodBodyBuilder2.toString(), ctClass2));
            }
            catch (final CannotCompileException ex) {
                MountiplexUtil.LOGGER.severe("Failed to compile method: " + methodBodyBuilder2.toString());
                throw ex;
            }
        });
    }
    
    @Override
    public MethodDeclaration discover() {
        if (!this.isValid() || !this.isResolved()) {
            return null;
        }
        if (this.body != null) {
            return this;
        }
        if (this.getResolver().getDeclaredClass() == null) {
            return null;
        }
        final TypeDeclaration parse = TypeDeclaration.parse(this.getResolver().getDeclaredClassName());
        if (this.name.isAliasOnly()) {
            final ClassDeclaration resolveClassDeclaration = Resolver.resolveClassDeclaration(this.getResolver().getDeclaredClassName(), this.getResolver().getDeclaredClass());
            if (resolveClassDeclaration != null) {
                final MethodDeclaration method = resolveClassDeclaration.findMethod(this);
                if (method != null) {
                    return method;
                }
            }
            if (parse != null) {
                for (final TypeDeclaration typeDeclaration : parse.getSuperTypes()) {
                    final ClassDeclaration resolveClassDeclaration2 = Resolver.resolveClassDeclaration(typeDeclaration.typePath, typeDeclaration.type);
                    if (resolveClassDeclaration2 != null) {
                        final MethodDeclaration method2 = resolveClassDeclaration2.findMethod(this);
                        if (method2 != null) {
                            return method2;
                        }
                    }
                }
            }
        }
        if (this.isRecordFieldChanger) {
            return new MethodDeclaration(this, this.parameters.renameParameters(parameterDeclaration2 -> Resolver.resolveMethodName(this.getResolver().getDeclaredClass(), parameterDeclaration2.name.value(), new Class[0])));
        }
        final MethodDeclaration resolveName = this.resolveName();
        if (resolveName.name.value().equals("<init>")) {
            try {
                this.constructor = (Constructor<Object>)this.getResolver().getDeclaredClass().getDeclaredConstructor(resolveName.parameters.toParamArray());
                return this;
            }
            catch (final NoSuchMethodException | SecurityException ex) {
                return null;
            }
        }
        if (resolveName.method != null) {
            return resolveName;
        }
        try {
            final Method declaredMethod = MPLType.getDeclaredMethod(this.getResolver().getDeclaredClass(), resolveName.name.value(), resolveName.parameters.toParamArray());
            if (new MethodDeclaration(this.getResolver(), declaredMethod).match(resolveName) && this.checkPublic(declaredMethod)) {
                resolveName.method = declaredMethod;
                return resolveName;
            }
        }
        catch (final NoSuchMethodException | SecurityException ex2) {}
        final MethodDeclaration method3 = new ClassDeclaration(ClassResolver.DEFAULT, this.getResolver().getDeclaredClass()).findMethod(resolveName);
        if (method3 != null && this.checkPublic(method3.method)) {
            this.method = method3.method;
            return this;
        }
        final TypeDeclaration[] superTypes2 = parse.getSuperTypes();
        for (int length2 = superTypes2.length, j = 0; j < length2; ++j) {
            final MethodDeclaration method4 = new ClassDeclaration(ClassResolver.DEFAULT, superTypes2[j].type).findMethod(resolveName);
            if (method4 != null && this.checkPublic(method4.method)) {
                this.method = method4.method;
                return this;
            }
        }
        return null;
    }
    
    @Override
    public void discoverAlternatives() {
        final Class<?> declaredClass = this.getResolver().getDeclaredClass();
        if (declaredClass == null) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration could not be found inside: ??" + this.getResolver().getDeclaredClassName() + "??");
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration: " + this.toString());
            return;
        }
        MethodDeclaration[] array;
        if (this.modifiers.isStatic()) {
            array = ReflectionUtil.getAllMethods(declaredClass).filter(method -> Modifier.isStatic(method.getModifiers())).map(method2 -> new MethodDeclaration(this.getResolver(), method2)).toArray(MethodDeclaration[]::new);
        }
        else {
            array = ReflectionUtil.getAllMethods(declaredClass).filter(method3 -> !Modifier.isStatic(method3.getModifiers())).filter(ReflectionUtil.createDuplicateMethodFilter()).map(method4 -> new MethodDeclaration(this.getResolver(), method4)).toArray(MethodDeclaration[]::new);
        }
        Declaration.sortSimilarity(this, array);
        FieldLCSResolver.logAlternatives("method", array, this, true);
    }
    
    public String getTemplateLogIdentity() {
        final StringBuilder sb = new StringBuilder();
        if (this.modifiers.isStatic()) {
            sb.append("static ");
        }
        sb.append("method ");
        sb.append(this.returnType.exposed().typeName);
        sb.append(' ');
        sb.append(this.name.toString());
        sb.append('(');
        int n = 1;
        for (final ParameterDeclaration parameterDeclaration : this.parameters.parameters) {
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append(", ");
            }
            sb.append(parameterDeclaration.name.toString());
        }
        sb.append(')');
        return sb.toString();
    }
    
    private boolean checkPublic(final Method method) {
        return !this.modifiers.isPublic() || Modifier.isPublic(method.getModifiers());
    }
    
    private Requirement[] processRequirements(final StringBuilder sb) {
        final ArrayList list = new ArrayList();
        for (int i = 1; i < sb.length(); ++i) {
            if (sb.charAt() == '#') {
                if (i < 1 || sb.charAt() != '#') {
                    if (i + 1 >= sb.length() || sb.charAt() != '#') {
                        int n = -1;
                        boolean b = false;
                        boolean b2 = false;
                        for (int j = i + 1; j < sb.length(); ++j) {
                            final char char1 = sb.charAt();
                            if (char1 == '(') {
                                n = j;
                                b = true;
                                break;
                            }
                            if (!Character.isLetterOrDigit(char1) && char1 != '_' && char1 != '$') {
                                n = j;
                                b2 = true;
                                break;
                            }
                        }
                        final String substring = sb.substring();
                        Requirement e = null;
                        for (final Requirement requirement : list) {
                            if (requirement.name.equals(substring)) {
                                e = requirement;
                                break;
                            }
                        }
                        if (e == null) {
                            Declaration declaration = null;
                            for (final Requirement requirement2 : this.getResolver().getRequirements()) {
                                if (!requirement2.name.equals(substring)) {
                                    continue;
                                }
                                if (b && requirement2.declaration instanceof MethodDeclaration) {
                                    declaration = requirement2.declaration;
                                    break;
                                }
                                if (b2 && requirement2.declaration instanceof FieldDeclaration) {
                                    declaration = requirement2.declaration;
                                    break;
                                }
                            }
                            if (declaration == null) {
                                continue;
                            }
                            final String declaredClassName = declaration.getResolver().getDeclaredClassName();
                            if (declaration.getResolver().getDeclaredClass() == null) {
                                if (this.getResolver().getLogErrors()) {
                                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Requirement declaration declaring Class not found: ??" + declaredClassName + "??");
                                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration: " + declaration.toString());
                                }
                                continue;
                            }
                            else if (!declaration.isResolved()) {
                                if (this.getResolver().getLogErrors()) {
                                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Requirement declaration could not be resolved for: " + declaredClassName);
                                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Declaration: " + declaration.toString());
                                }
                                continue;
                            }
                            else {
                                final Declaration discover = declaration.discover();
                                if (discover == null) {
                                    if (this.getResolver().getLogErrors()) {
                                        declaration.discoverAlternatives();
                                    }
                                    continue;
                                }
                                else {
                                    e = new Requirement(substring, discover);
                                    list.add(e);
                                }
                            }
                        }
                        int n2 = 0;
                        if (e.declaration instanceof MethodDeclaration) {
                            final MethodDeclaration methodDeclaration = (MethodDeclaration)e.declaration;
                            n2 = ((methodDeclaration.constructor != null || methodDeclaration.modifiers.isStatic()) ? 1 : 0);
                        }
                        if (e.declaration instanceof FieldDeclaration && ((FieldDeclaration)e.declaration).modifiers.isStatic()) {
                            n2 = 1;
                        }
                        int n3 = i - 1;
                        int k;
                        if (n2 != 0 && !Character.isLetterOrDigit(sb.charAt())) {
                            k = ++n3;
                        }
                        else {
                            while (n3 > 0 && sb.charAt() == ' ') {
                                --n3;
                            }
                            k = n3;
                            ++n3;
                            int n4 = 0;
                            while (k >= 0) {
                                final char char2 = sb.charAt();
                                if (char2 == ')') {
                                    ++n4;
                                }
                                else if (char2 == '(') {
                                    if (--n4 < 0) {
                                        break;
                                    }
                                }
                                else if (!Character.isLetterOrDigit(char2) && char2 != '.' && char2 != '$' && char2 != '_' && n4 == 0) {
                                    break;
                                }
                                --k;
                            }
                            ++k;
                        }
                        e.declaration.modifyBodyRequirement(e, sb, (n2 != 0) ? "null" : sb.substring(), substring, k, n);
                    }
                }
            }
        }
        return list.toArray(new Requirement[list.size()]);
    }
    
    public MethodDeclaration resolveName() {
        if (!this.isResolved() || this.getResolver().getDeclaredClass() == null || this.body != null || this.name.value().equals("<init>") || this.isRecordFieldChanger) {
            return this;
        }
        final Remapping.MethodRemapping find = this.getResolver().getRemappings().find(this);
        if (find != null) {
            final MethodDeclaration methodDeclaration = new MethodDeclaration(this, this.name.rename(find.declaration.name));
            methodDeclaration.method = find.method;
            return methodDeclaration;
        }
        final String resolveMethodName = Resolver.resolveMethodName(this.getResolver().getDeclaredClass(), this.name.value(), this.parameters.toParamArray());
        if (resolveMethodName != null && !resolveMethodName.equals(this.name.value())) {
            return new MethodDeclaration(this, this.name.rename(resolveMethodName));
        }
        return this;
    }
    
    public MethodDeclaration setAlias(final String s) {
        if (s.equals(this.name.alias())) {
            return this;
        }
        return new MethodDeclaration(this, new NameDeclaration(this.getResolver(), this.name.value(), s));
    }
    
    protected String getAccessedName() {
        return (this.method != null) ? MPLType.getName(this.method) : this.name.value();
    }
    
    private static String toDebugString(final Executable executable) {
        if (executable == null) {
            return "<null>";
        }
        try {
            return executable.toGenericString();
        }
        catch (final Throwable t) {
            try {
                return executable.toString();
            }
            catch (final Throwable t2) {
                final StringBuilder sb = new StringBuilder();
                if (executable instanceof Method) {
                    try {
                        sb.append(((Method)executable).getReturnType());
                        sb.append(' ');
                    }
                    catch (final Throwable t3) {}
                }
                try {
                    sb.append(executable.getDeclaringClass());
                    sb.append('.');
                }
                catch (final Throwable t4) {}
                try {
                    sb.append(executable.getName());
                }
                catch (final Throwable t5) {}
                sb.append('(');
                try {
                    final Class<?>[] parameterTypes = executable.getParameterTypes();
                    for (int i = 0; i < parameterTypes.length; ++i) {
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append(parameterTypes[i]);
                    }
                }
                catch (final Throwable t6) {
                    sb.append("???");
                }
                sb.append(')');
                return sb.toString();
            }
        }
    }
}
