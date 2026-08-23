// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.ClassDeclarationParserContext;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.DeclarationParserGroups;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.ParserStringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.util.stream.Stream;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import java.util.Objects;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class ClassDeclaration extends Declaration
{
    public final ModifierDeclaration modifiers;
    public final TypeDeclaration base;
    public final TypeDeclaration type;
    public final ClassDeclaration[] subclasses;
    public final ConstructorDeclaration[] constructors;
    public final MethodDeclaration[] methods;
    public final FieldDeclaration[] fields;
    public final String code;
    public final List<String> codeImports;
    public final boolean is_interface;
    
    public ClassDeclaration(final ClassResolver classResolver, final Class<?> declaredClass) {
        super(classResolver.clone());
        final Class<? super Object> superclass = declaredClass.getSuperclass();
        this.is_interface = declaredClass.isInterface();
        this.base = ((superclass == null) ? null : TypeDeclaration.fromClass(superclass));
        this.type = TypeDeclaration.fromClass(declaredClass);
        this.modifiers = new ModifierDeclaration(this.getResolver(), declaredClass.getModifiers());
        this.code = "";
        this.codeImports = Collections.emptyList();
        this.getResolver().setDeclaredClass(declaredClass);
        final LinkedList list = new LinkedList();
        final LinkedList list2 = new LinkedList();
        final LinkedList list3 = new LinkedList();
        final LinkedList list4 = new LinkedList();
        if (declaredClass.isEnum()) {
            final Object[] enumConstants = declaredClass.getEnumConstants();
            for (int length = enumConstants.length, i = 0; i < length; ++i) {
                list3.add(new FieldDeclaration(this.getResolver(), (Enum<?>)enumConstants[i]));
            }
        }
        final Constructor<?>[] declaredConstructors = declaredClass.getDeclaredConstructors();
        for (int length2 = declaredConstructors.length, j = 0; j < length2; ++j) {
            list.add(new ConstructorDeclaration(this.getResolver(), declaredConstructors[j]));
        }
        final Field[] declaredFields = declaredClass.getDeclaredFields();
        for (int length3 = declaredFields.length, k = 0; k < length3; ++k) {
            list3.add(new FieldDeclaration(this.getResolver(), declaredFields[k]));
        }
        final Stream<Object> map = ReflectionUtil.getDeclaredMethods(declaredClass).filter(ReflectionUtil.createDuplicateMethodFilter()).map(method -> new MethodDeclaration(this.getResolver(), method));
        final LinkedList obj = list2;
        Objects.requireNonNull(obj);
        map.forEachOrdered(obj::add);
        final Class<?>[] declaredClasses = declaredClass.getDeclaredClasses();
        for (int length4 = declaredClasses.length, l = 0; l < length4; ++l) {
            list4.add(new ClassDeclaration(this.getResolver(), declaredClasses[l]));
        }
        this.constructors = list.toArray(new ConstructorDeclaration[list.size()]);
        this.methods = list2.toArray(new MethodDeclaration[list2.size()]);
        this.fields = list3.toArray(new FieldDeclaration[list3.size()]);
        this.subclasses = list4.toArray(new ClassDeclaration[list4.size()]);
    }
    
    public ClassDeclaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public ClassDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        super(classResolver.clone(), stringBuffer);
        this.modifiers = this.nextModifier();
        if (!this.isValid()) {
            this.code = "";
            this.codeImports = Collections.emptyList();
            this.base = null;
            this.type = null;
            this.subclasses = new ClassDeclaration[0];
            this.constructors = new ConstructorDeclaration[0];
            this.methods = new MethodDeclaration[0];
            this.fields = new FieldDeclaration[0];
            this.is_interface = false;
            return;
        }
        final StringBuffer postfix = this.getPostfix();
        this.is_interface = postfix.startsWith("interface ");
        if (!this.is_interface && !postfix.startsWith("class ")) {
            this.base = null;
            this.type = null;
            this.code = "";
            this.codeImports = Collections.emptyList();
            this.subclasses = new ClassDeclaration[0];
            this.constructors = new ConstructorDeclaration[0];
            this.methods = new MethodDeclaration[0];
            this.fields = new FieldDeclaration[0];
            this.setInvalid();
            return;
        }
        this.setPostfix(postfix.substring(this.is_interface ? 10 : 6));
        this.type = this.nextType();
        if (!this.isValid()) {
            this.base = null;
            this.code = "";
            this.codeImports = Collections.emptyList();
            this.subclasses = new ClassDeclaration[0];
            this.constructors = new ConstructorDeclaration[0];
            this.methods = new MethodDeclaration[0];
            this.fields = new FieldDeclaration[0];
            return;
        }
        this.getResolver().setDeclaredClass(this.type.type, this.type.typePath);
        final StringBuffer postfix2 = this.getPostfix();
        if (postfix2.startsWith("extends ")) {
            this.setPostfix(postfix2.substring(8));
            this.base = this.nextType();
        }
        else {
            this.base = null;
        }
        final StringBuffer postfix3 = this.getPostfix();
        boolean b = false;
        int n = -1;
        for (int i = 0; i < postfix3.length(); ++i) {
            final char char1 = postfix3.charAt(i);
            if (char1 == '{') {
                b = true;
            }
            else if (b && !MountiplexUtil.containsChar(char1, ParserStringBuffer.WHITESPACE_CHARACTERS)) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            this.code = "";
            this.codeImports = Collections.emptyList();
            this.subclasses = new ClassDeclaration[0];
            this.constructors = new ConstructorDeclaration[0];
            this.methods = new MethodDeclaration[0];
            this.fields = new FieldDeclaration[0];
            this.setInvalid();
            return;
        }
        this.getParserPostfix().trimWhitespace(n);
        final ParserContext parserContext = new ParserContext();
        final LinkedList<ClassDeclaration> list = new LinkedList<ClassDeclaration>();
        final LinkedList<ConstructorDeclaration> list2 = new LinkedList<ConstructorDeclaration>();
        final LinkedList<MethodDeclaration> list3 = new LinkedList<MethodDeclaration>();
        final LinkedList<FieldDeclaration> list4 = new LinkedList<FieldDeclaration>();
        StringBuffer postfix4;
        while ((postfix4 = this.getPostfix()) != null && postfix4.length() > 0) {
            if (parserContext.runParsers(DeclarationParserGroups.CLASS)) {
                continue;
            }
            if (postfix4.charAt(0) == '}') {
                this.getParserPostfix().trimWhitespace(1);
                break;
            }
            final ClassDeclaration e = new ClassDeclaration(this.getResolver(), postfix4);
            if (e.isValid()) {
                list.add(e);
                this.setPostfix(e.getPostfix());
                this.getParserPostfix().trimWhitespace(0);
            }
            else {
                final Declaration detectMemberDeclaration = this.getParserPostfix().detectMemberDeclaration(this.getResolver());
                if (detectMemberDeclaration instanceof MethodDeclaration) {
                    list3.add((MethodDeclaration)detectMemberDeclaration);
                }
                else if (detectMemberDeclaration instanceof ConstructorDeclaration) {
                    list2.add((ConstructorDeclaration)detectMemberDeclaration);
                }
                else {
                    if (!(detectMemberDeclaration instanceof FieldDeclaration)) {
                        break;
                    }
                    list4.add((FieldDeclaration)detectMemberDeclaration);
                }
            }
        }
        this.code = parserContext.codeStr.toString();
        this.codeImports = (List<String>)(parserContext.codeImports.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList((List<?>)parserContext.codeImports));
        this.subclasses = list.toArray(new ClassDeclaration[list.size()]);
        this.constructors = list2.toArray(new ConstructorDeclaration[list2.size()]);
        this.methods = list3.toArray(new MethodDeclaration[list3.size()]);
        this.fields = list4.toArray(new FieldDeclaration[list4.size()]);
        if (this.type.isResolved()) {
            this.resolveFields();
            this.resolveMethods();
            this.resolveConstructors();
        }
    }
    
    private void resolveFields() {
        Field[] declaredFields;
        try {
            declaredFields = this.type.type.getDeclaredFields();
        }
        catch (final Throwable thrown) {
            if (this.getResolver().getLogErrors()) {
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to get declared fields of " + this.type.typePath, thrown);
            }
            return;
        }
        final FieldDeclaration[] array = new FieldDeclaration[declaredFields.length];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = new FieldDeclaration(this.getResolver(), declaredFields[i]);
            }
            catch (final Throwable thrown2) {
                if (this.getResolver().getLogErrors()) {
                    MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to read field " + declaredFields[i], thrown2);
                }
            }
        }
        final FieldDeclaration[] array2 = new FieldDeclaration[this.fields.length];
        for (int j = 0; j < array2.length; ++j) {
            array2[j] = this.fields[j].resolveName();
        }
        FieldLCSResolver.resolve(array2, array);
        for (int k = 0; k < array2.length; ++k) {
            this.fields[k].copyFieldFrom(array2[k]);
        }
    }
    
    private void resolveMethods() {
        MethodMatchResolver.match(this.type.type, this.getResolver(), this.methods);
    }
    
    private void resolveConstructors() {
        final Constructor<?>[] declaredConstructors = this.type.type.getDeclaredConstructors();
        final ConstructorDeclaration[] array = new ConstructorDeclaration[declaredConstructors.length];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = new ConstructorDeclaration(this.getResolver(), declaredConstructors[i]);
            }
            catch (final Throwable thrown) {
                if (this.getResolver().getLogErrors()) {
                    MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to read constructor " + declaredConstructors[i], thrown);
                }
            }
        }
        for (int j = 0; j < this.constructors.length; ++j) {
            final ConstructorDeclaration constructorDeclaration = this.constructors[j];
            boolean b = false;
            for (int k = 0; k < array.length; ++k) {
                if (array[k].match(constructorDeclaration)) {
                    constructorDeclaration.constructor = array[k].constructor;
                    b = true;
                    break;
                }
            }
            if (!b && !constructorDeclaration.modifiers.isOptional()) {
                FieldLCSResolver.logAlternatives("constructor", array, constructorDeclaration, false);
            }
        }
    }
    
    public MethodDeclaration findMethod(final MethodDeclaration methodDeclaration) {
        for (final MethodDeclaration methodDeclaration2 : this.methods) {
            if (methodDeclaration2.match(methodDeclaration)) {
                return methodDeclaration2;
            }
        }
        final Remapping.MethodRemapping find = this.getResolver().getRemappings().find(methodDeclaration);
        if (find != null) {
            return find.declaration;
        }
        return null;
    }
    
    public String resolveMethodAlias(final Method obj) {
        if (!Modifier.isPrivate(obj.getModifiers())) {
            final Class<?>[] parameterTypes = obj.getParameterTypes();
            for (final MethodDeclaration methodDeclaration : this.methods) {
                if (methodDeclaration.method != null) {
                    if (!Modifier.isPrivate(methodDeclaration.method.getModifiers())) {
                        if (MPLType.getName(methodDeclaration.method).equals(MPLType.getName(obj))) {
                            boolean b = true;
                            final Class<?>[] parameterTypes2 = methodDeclaration.method.getParameterTypes();
                            if (parameterTypes2.length == parameterTypes.length) {
                                for (int j = 0; j < parameterTypes.length; ++j) {
                                    if (!parameterTypes[j].equals(parameterTypes2[j])) {
                                        b = false;
                                        break;
                                    }
                                }
                                if (b) {
                                    return methodDeclaration.name.real();
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
        if (!this.type.type.equals(obj.getDeclaringClass())) {
            return null;
        }
        for (final MethodDeclaration methodDeclaration2 : this.methods) {
            if (methodDeclaration2.method != null && methodDeclaration2.method.equals(obj)) {
                return methodDeclaration2.name.real();
            }
        }
        return null;
    }
    
    @Override
    public boolean isResolved() {
        return this.type.isResolved();
    }
    
    @Override
    public double similarity(final Declaration declaration) {
        return 0.0;
    }
    
    @Override
    public boolean match(final Declaration declaration) {
        return declaration instanceof ClassDeclaration && ((ClassDeclaration)declaration).type.match(this.type);
    }
    
    @Override
    public String toString(final boolean b) {
        String s = this.modifiers.toString(b);
        if (s.length() > 0) {
            s += " ";
        }
        final String string = s + (this.is_interface ? "interface " : "class ");
        String s2;
        if (this.type == null) {
            s2 = string + "<nulltype>";
        }
        else {
            s2 = string + this.type.toString(b);
        }
        if (this.base != null) {
            s2 = s2 + " extends " + this.base.toString(b);
        }
        String str = s2 + " {\n";
        final FieldDeclaration[] fields = this.fields;
        for (int length = fields.length, i = 0; i < length; ++i) {
            str = str + "    " + fields[i].toString(b) + "\n";
        }
        final ConstructorDeclaration[] constructors = this.constructors;
        for (int length2 = constructors.length, j = 0; j < length2; ++j) {
            str = str + "    " + constructors[j].toString(b) + "\n";
        }
        final MethodDeclaration[] methods = this.methods;
        for (int length3 = methods.length, k = 0; k < length3; ++k) {
            str = str + "    " + methods[k].toString(b) + "\n";
        }
        if (this.subclasses.length > 0) {
            str += "\n";
            String string2 = "";
            final ClassDeclaration[] subclasses = this.subclasses;
            for (int length4 = subclasses.length, l = 0; l < length4; ++l) {
                string2 = string2 + subclasses[l].toString(b) + "\n";
            }
            for (final String str2 : string2.split("\\r?\\n", -1)) {
                if (str2.length() > 0) {
                    str = str + "    " + str2 + "\n";
                }
                else {
                    str += "\n";
                }
            }
        }
        return str + "}";
    }
    
    public String getTemplateLogIdentity() {
        return "class " + this.type.typeName;
    }
    
    @Override
    protected void debugString(final StringBuilder sb, final String s) {
    }
    
    private class ParserContext extends BaseDeclarationParserContext implements ClassDeclarationParserContext
    {
        public StringBuilder codeStr;
        public List<String> codeImports;
        
        public ParserContext() {
            this.codeStr = new StringBuilder();
            this.codeImports = new ArrayList<String>();
        }
        
        @Override
        public void appendCode(final String s) {
            this.codeStr.append(SourceDeclaration.trimIndentation(s));
        }
        
        @Override
        public void addCodeImport(final String s) {
            this.codeImports.add(s);
        }
    }
}
