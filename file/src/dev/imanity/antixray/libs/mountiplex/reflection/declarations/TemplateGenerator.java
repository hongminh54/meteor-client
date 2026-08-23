// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.Locale;
import java.util.Iterator;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.io.File;

public class TemplateGenerator
{
    private ClassDeclaration rootClassDec;
    private File rootDir;
    private String path;
    private StringBuilder builder;
    private Map<String, String> imports;
    private Set<String> codeImports;
    private int indent;
    private Map<TypeDeclaration, TemplateGenerator> generatorPool;
    
    public TemplateGenerator() {
        this.rootClassDec = null;
        this.rootDir = null;
        this.path = "";
        this.builder = new StringBuilder();
        this.imports = new TreeMap<String, String>();
        this.codeImports = new TreeSet<String>();
        this.indent = 0;
        this.generatorPool = null;
    }
    
    public void setClass(final ClassDeclaration rootClassDec) {
        this.rootClassDec = rootClassDec;
    }
    
    public ClassDeclaration getClassType() {
        return this.rootClassDec;
    }
    
    public void setPool(final Map<TypeDeclaration, TemplateGenerator> generatorPool) {
        this.generatorPool = generatorPool;
    }
    
    public void setRootDirectory(final File rootDir) {
        this.rootDir = rootDir;
    }
    
    public void setPath(final String s) {
        this.path = s.replace('/', '.');
    }
    
    public void generate() {
        this.builder = new StringBuilder();
        (this.imports = new TreeMap<String, String>()).put("Template", Template.class.getName());
        this.indent = 0;
        final String path = this.path;
        this.addClass(this.rootClassDec);
        final String string = this.builder.toString();
        this.builder.setLength();
        this.addLine("package " + path);
        this.addLine();
        final String string2 = this.path + "." + this.handleName(this.rootClassDec);
        Collection<String> values;
        if (this.codeImports.isEmpty()) {
            values = this.imports.values();
        }
        else {
            values = new TreeSet<String>(this.codeImports);
            values.addAll(this.imports.values());
        }
        for (final String str : values) {
            if (str.startsWith(string2)) {
                continue;
            }
            if (str.startsWith(this.path) && !str.substring(this.path.length() + 1).contains(".")) {
                continue;
            }
            this.addLine("import " + str);
        }
        this.builder.append(string);
        final String string3 = this.builder.toString();
        try {
            final File parent = new File(this.rootDir, path.replace('.', File.separatorChar));
            parent.mkdirs();
            final File file = new File(parent, this.handleName(this.rootClassDec) + ".java");
            int n = 1;
            if (file.exists()) {
                n = (new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8).equals(string3) ? 0 : 1);
            }
            if (n != 0) {
                final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                try {
                    bufferedWriter.write(string3);
                }
                finally {
                    bufferedWriter.close();
                }
            }
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to generate template class " + string2, thrown);
        }
    }
    
    private String findClassName(final ClassDeclaration classDeclaration, final String str, final TypeDeclaration typeDeclaration) {
        final String string = str + "." + this.handleName(classDeclaration);
        if (classDeclaration.type.equals(typeDeclaration)) {
            return string;
        }
        final ClassDeclaration[] subclasses = classDeclaration.subclasses;
        for (int length = subclasses.length, i = 0; i < length; ++i) {
            final String className = this.findClassName(subclasses[i], string, typeDeclaration);
            if (className != null) {
                return className;
            }
        }
        return null;
    }
    
    private void addClass(final ClassDeclaration classDeclaration) {
        if (classDeclaration.type.typePath.equals("")) {
            return;
        }
        String resolveImport = "Template.Handle";
        final TypeDeclaration base = classDeclaration.base;
        if (base != null && this.generatorPool != null) {
            final TemplateGenerator templateGenerator = this.generatorPool.get(base);
            if (templateGenerator != null) {
                final String className = templateGenerator.findClassName(templateGenerator.rootClassDec, templateGenerator.path, classDeclaration.base);
                if (className != null) {
                    resolveImport = this.resolveImport(className);
                }
                else {
                    MountiplexUtil.LOGGER.severe("Failed to find super type template class: " + base.typePath);
                    MountiplexUtil.LOGGER.severe("With super template generator: " + templateGenerator.path);
                    MountiplexUtil.LOGGER.severe("At template " + classDeclaration.type.typePath);
                }
            }
            else {
                MountiplexUtil.LOGGER.severe("Super type has no template: " + base.typePath);
                MountiplexUtil.LOGGER.severe("At template " + classDeclaration.type.typePath);
            }
        }
        String str = "";
        if (classDeclaration != this.rootClassDec) {
            str = "static ";
        }
        this.addLine();
        this.addComment("Instance wrapper handle for type <b>" + classDeclaration.type.typePath + "</b>.\nTo access members without creating a handle type, use the static {@link #T} member.\nNew handles can be created from raw instances using {@link #createHandle(Object)}.");
        this.populateModifiers(classDeclaration.modifiers);
        this.addLine("@Template.InstanceType(\"" + classDeclaration.type.typePath + "\")");
        this.addLine("public abstract " + str + "class " + this.handleName(classDeclaration) + " extends " + resolveImport + " {");
        this.addComment("@see " + this.className(classDeclaration));
        this.addLine("public static final " + this.className(classDeclaration) + " T = Template.Class.create(" + this.className(classDeclaration) + ".class, " + classDeclaration.getResolver().getClassDeclarationResolverName() + ")");
        for (final FieldDeclaration fieldDeclaration : classDeclaration.fields) {
            if (fieldDeclaration.isEnum) {
                if (!fieldDeclaration.modifiers.isOptional()) {
                    final String fieldTypeStr = this.getFieldTypeStr(fieldDeclaration);
                    final String real = fieldDeclaration.name.real();
                    this.populateModifiers(fieldDeclaration.modifiers);
                    this.addLine("public static final " + fieldTypeStr + " " + real + " = T." + real + ".getSafe()");
                }
            }
        }
        for (final FieldDeclaration fieldDeclaration2 : classDeclaration.fields) {
            if (!fieldDeclaration2.modifiers.isUnknown() && fieldDeclaration2.modifiers.isStatic() && fieldDeclaration2.modifiers.isFinal() && !fieldDeclaration2.isEnum) {
                if (!fieldDeclaration2.modifiers.isOptional()) {
                    final String primFieldType = this.getPrimFieldType(fieldDeclaration2);
                    final String fieldTypeStr2 = this.getFieldTypeStr(fieldDeclaration2);
                    final String real2 = fieldDeclaration2.name.real();
                    this.populateModifiers(fieldDeclaration2.modifiers);
                    this.addLine("public static final " + fieldTypeStr2 + " " + real2 + " = T." + real2 + ".get" + primFieldType + "Safe()");
                }
            }
        }
        this.addLine("/* ============================================================================== */");
        this.addLine();
        this.addLine("public static " + this.handleName(classDeclaration) + " createHandle(Object handleInstance) {");
        this.addLine("return T.createHandle(handleInstance)");
        this.addLine("}");
        for (final ConstructorDeclaration constructorDeclaration : classDeclaration.constructors) {
            if (!constructorDeclaration.modifiers.isOptional()) {
                this.populateModifiers(constructorDeclaration.modifiers);
                this.addLine("public static final " + this.getExposedTypeStr(constructorDeclaration.type) + " createNew" + this.getParamsBody(constructorDeclaration.parameters) + " {");
                if (constructorDeclaration.parameters.parameters.length <= 5) {
                    this.addLine("return T." + constructorDeclaration.getName() + ".newInstance(" + this.getArgsBody(constructorDeclaration.parameters) + ")");
                }
                else {
                    this.addLine("return T." + constructorDeclaration.getName() + ".newInstanceVA(" + this.getArgsBody(constructorDeclaration.parameters) + ")");
                }
                this.addLine("}");
            }
        }
        this.addLine("/* ============================================================================== */");
        this.addLine();
        for (final FieldDeclaration fieldDeclaration3 : classDeclaration.fields) {
            if (!fieldDeclaration3.modifiers.isUnknown() && fieldDeclaration3.modifiers.isStatic() && !fieldDeclaration3.modifiers.isFinal() && !fieldDeclaration3.isEnum) {
                if (!fieldDeclaration3.modifiers.isOptional()) {
                    final String primFieldType2 = this.getPrimFieldType(fieldDeclaration3);
                    final String fieldTypeStr3 = this.getFieldTypeStr(fieldDeclaration3);
                    final String real3 = fieldDeclaration3.name.real();
                    this.populateModifiers(fieldDeclaration3.modifiers);
                    this.addLine("public static " + fieldTypeStr3 + " " + real3 + "() {");
                    this.addLine("return T." + fieldDeclaration3.name.real() + ".get" + primFieldType2 + "()");
                    this.addLine("}");
                    if (!fieldDeclaration3.modifiers.isReadonly()) {
                        this.populateModifiers(fieldDeclaration3.modifiers);
                        this.addLine("public static void " + real3 + "_set(" + fieldTypeStr3 + " value) {");
                        this.addLine("T." + fieldDeclaration3.name.real() + ".set" + primFieldType2 + "(value)");
                        this.addLine("}");
                    }
                }
            }
        }
        for (final MethodDeclaration methodDeclaration : classDeclaration.methods) {
            if (!methodDeclaration.modifiers.isUnknown() && methodDeclaration.modifiers.isStatic()) {
                if (!methodDeclaration.modifiers.isOptional()) {
                    if (!TemplateHandleBuilder.isCreateHandleMethod(methodDeclaration)) {
                        this.addStaticMethodBody(methodDeclaration);
                    }
                }
            }
        }
        for (final MethodDeclaration methodDeclaration2 : classDeclaration.methods) {
            if (!methodDeclaration2.modifiers.isUnknown() && !methodDeclaration2.modifiers.isStatic()) {
                if (!methodDeclaration2.modifiers.isOptional()) {
                    this.addAbstractMethodBody(methodDeclaration2);
                }
            }
        }
        this.codeImports.addAll(classDeclaration.codeImports);
        if (classDeclaration.code.length() > 0) {
            for (final String str2 : classDeclaration.code.split("\n")) {
                if (str2.trim().length() > 0) {
                    for (int n4 = 0; n4 < this.indent; ++n4) {
                        this.builder.append("    ");
                    }
                    this.builder.append(str2);
                }
                this.builder.append('\n');
            }
        }
        for (final FieldDeclaration fieldDeclaration4 : classDeclaration.fields) {
            if (!fieldDeclaration4.modifiers.isUnknown() && !fieldDeclaration4.modifiers.isStatic() && !fieldDeclaration4.isEnum) {
                if (!fieldDeclaration4.modifiers.isOptional()) {
                    final String fieldTypeStr4 = this.getFieldTypeStr(fieldDeclaration4);
                    this.populateModifiers(fieldDeclaration4.modifiers);
                    this.addLine("public abstract " + fieldTypeStr4 + " " + getGetterName(fieldDeclaration4) + "()");
                    if (!fieldDeclaration4.modifiers.isReadonly()) {
                        this.populateModifiers(fieldDeclaration4.modifiers);
                        this.addLine("public abstract void " + getSetterName(fieldDeclaration4) + "(" + fieldTypeStr4 + " value)");
                    }
                }
            }
        }
        this.addComment("Stores class members for <b>" + classDeclaration.type.typePath + "</b>.\nMethods, fields, and constructors can be used without using Handle Objects.");
        this.addLine("public static final class " + this.className(classDeclaration) + " extends Template.Class<" + this.handleName(classDeclaration) + "> {");
        boolean b = false;
        for (final FieldDeclaration fieldDeclaration5 : classDeclaration.fields) {
            if (!fieldDeclaration5.modifiers.isUnknown()) {
                if (fieldDeclaration5.isEnum) {
                    final String s = "Template.EnumConstant";
                    String s2;
                    if (this.hasConversion(fieldDeclaration5)) {
                        s2 = s + ".Converted" + "<" + this.getTypeStr(fieldDeclaration5.type.cast, true) + ">";
                    }
                    else {
                        s2 = s + "<" + this.getFieldTypeStr(fieldDeclaration5) + ">";
                    }
                    this.populateModifiers(fieldDeclaration5.modifiers);
                    this.addLine("public final " + s2 + " " + fieldDeclaration5.name.real() + " = new " + s2 + "()");
                    b = true;
                }
            }
        }
        if (b) {
            this.addLine();
        }
        boolean b2 = false;
        for (final ConstructorDeclaration constructorDeclaration2 : classDeclaration.constructors) {
            String string = "Template.Constructor";
            if (this.hasConversion(constructorDeclaration2)) {
                string += ".Converted";
            }
            final String string2 = string + "<" + this.getExposedTypeStr(constructorDeclaration2.type) + ">";
            this.populateModifiers(constructorDeclaration2.modifiers);
            this.addLine("public final " + string2 + " " + constructorDeclaration2.getName() + " = new " + string2 + "()");
            b2 = true;
        }
        if (b2) {
            this.addLine();
        }
        boolean b3 = false;
        for (final FieldDeclaration fieldDeclaration6 : classDeclaration.fields) {
            if (!fieldDeclaration6.modifiers.isUnknown() && fieldDeclaration6.modifiers.isStatic()) {
                if (!fieldDeclaration6.isEnum) {
                    final String str3 = "Template.StaticField";
                    String s3;
                    if (this.hasConversion(fieldDeclaration6)) {
                        s3 = str3 + ".Converted" + "<" + this.getTypeStr(fieldDeclaration6.type.cast, true) + ">";
                    }
                    else {
                        final String primFieldType3 = this.getPrimFieldType(fieldDeclaration6);
                        if (!primFieldType3.isEmpty()) {
                            s3 = str3 + "." + primFieldType3;
                        }
                        else {
                            s3 = str3 + "<" + this.getFieldTypeStr(fieldDeclaration6) + ">";
                        }
                    }
                    this.populateModifiers(fieldDeclaration6.modifiers);
                    this.addLine("public final " + s3 + " " + fieldDeclaration6.name.real() + " = new " + s3 + "()");
                    b3 = true;
                }
            }
        }
        if (b3) {
            this.addLine();
        }
        boolean b4 = false;
        for (final FieldDeclaration fieldDeclaration7 : classDeclaration.fields) {
            if (!fieldDeclaration7.modifiers.isUnknown() && !fieldDeclaration7.modifiers.isStatic()) {
                if (!fieldDeclaration7.isEnum) {
                    final String str4 = "Template.Field";
                    String s4;
                    if (this.hasConversion(fieldDeclaration7)) {
                        s4 = str4 + ".Converted" + "<" + this.getTypeStr(fieldDeclaration7.type.cast, true) + ">";
                    }
                    else {
                        final String primFieldType4 = this.getPrimFieldType(fieldDeclaration7);
                        if (!primFieldType4.isEmpty()) {
                            s4 = str4 + "." + primFieldType4;
                        }
                        else {
                            s4 = str4 + "<" + this.getFieldTypeStr(fieldDeclaration7) + ">";
                        }
                    }
                    this.populateModifiers(fieldDeclaration7.modifiers);
                    this.addLine("public final " + s4 + " " + fieldDeclaration7.name.real() + " = new " + s4 + "()");
                    b4 = true;
                }
            }
        }
        if (b4) {
            this.addLine();
        }
        boolean b5 = false;
        for (final MethodDeclaration methodDeclaration3 : classDeclaration.methods) {
            if (!methodDeclaration3.modifiers.isUnknown()) {
                if (methodDeclaration3.modifiers.isStatic()) {
                    final String string3 = "Template.StaticMethod" + this.getMethodAppend(methodDeclaration3);
                    this.populateModifiers(methodDeclaration3.modifiers);
                    this.addLine("public final " + string3 + " " + methodDeclaration3.name.real() + " = new " + string3 + "()");
                    b5 = true;
                }
            }
        }
        if (b5) {
            this.addLine();
        }
        boolean b6 = false;
        for (final MethodDeclaration methodDeclaration4 : classDeclaration.methods) {
            if (!methodDeclaration4.modifiers.isUnknown()) {
                if (!methodDeclaration4.modifiers.isStatic()) {
                    final String string4 = "Template.Method" + this.getMethodAppend(methodDeclaration4);
                    this.populateModifiers(methodDeclaration4.modifiers);
                    this.addLine("public final " + string4 + " " + methodDeclaration4.name.real() + " = new " + string4 + "()");
                    b6 = true;
                }
            }
        }
        if (b6) {
            this.addLine();
        }
        this.addLine("}");
        final ClassDeclaration[] subclasses = classDeclaration.subclasses;
        for (int length15 = subclasses.length, n12 = 0; n12 < length15; ++n12) {
            this.addClass(subclasses[n12]);
        }
        this.addLine("}");
    }
    
    public static String getGetterName(final FieldDeclaration fieldDeclaration) {
        final String propertyName = getPropertyName(fieldDeclaration);
        if (propertyName.length() > 2 && propertyName.startsWith("Is") && Character.isUpperCase(propertyName.charAt(2))) {
            return propertyName.substring(0, 1).toLowerCase(Locale.ENGLISH) + propertyName.substring(1);
        }
        if (Boolean.TYPE.equals(fieldDeclaration.type.exposed().type)) {
            return "is" + propertyName;
        }
        return "get" + propertyName;
    }
    
    public static String getSetterName(final FieldDeclaration fieldDeclaration) {
        return "set" + getPropertyName(fieldDeclaration);
    }
    
    private void populateModifiers(final ModifierDeclaration modifierDeclaration) {
        if (modifierDeclaration.isRawtype()) {
            this.addLine("@SuppressWarnings(\"rawtypes\")");
        }
        if (modifierDeclaration.isOptional()) {
            this.addLine("@Template.Optional");
        }
        if (modifierDeclaration.isReadonly()) {
            this.addLine("@Template.Readonly");
        }
    }
    
    private String getParamsBody(final ParameterListDeclaration parameterListDeclaration) {
        String str = "(";
        for (int i = 0; i < parameterListDeclaration.parameters.length; ++i) {
            if (i > 0) {
                str += ", ";
            }
            str = str + this.getExposedTypeStr(parameterListDeclaration.parameters[i].type) + " " + parameterListDeclaration.parameters[i].name.real();
        }
        return str + ")";
    }
    
    private String getArgsBody(final ParameterListDeclaration parameterListDeclaration) {
        String s = "";
        for (int i = 0; i < parameterListDeclaration.parameters.length; ++i) {
            if (i > 0) {
                s += ", ";
            }
            s += parameterListDeclaration.parameters[i].name.real();
        }
        return s;
    }
    
    private void addAbstractMethodBody(final MethodDeclaration methodDeclaration) {
        this.addLine("public abstract " + this.getExposedTypeStr(methodDeclaration.returnType) + " " + methodDeclaration.name.real() + this.getParamsBody(methodDeclaration.parameters));
    }
    
    private void addStaticMethodBody(final MethodDeclaration methodDeclaration) {
        this.addLine("public static " + this.getExposedTypeStr(methodDeclaration.returnType) + " " + methodDeclaration.name.real() + this.getParamsBody(methodDeclaration.parameters) + " {");
        String string = "";
        if (!Void.TYPE.equals(methodDeclaration.returnType.exposed().type)) {
            string += "return ";
        }
        String str;
        if (this.hasConversion(methodDeclaration)) {
            if (methodDeclaration.parameters.parameters.length <= 5) {
                str = string + "T." + methodDeclaration.name.real() + ".invoke(";
            }
            else {
                str = string + "T." + methodDeclaration.name.real() + ".invokeVA(";
            }
        }
        else if (methodDeclaration.parameters.parameters.length == 0) {
            str = string + "T." + methodDeclaration.name.real() + ".invoker.invoke(null";
        }
        else if (methodDeclaration.parameters.parameters.length <= 5) {
            str = string + "T." + methodDeclaration.name.real() + ".invoker.invoke(null,";
        }
        else {
            str = string + "T." + methodDeclaration.name.real() + ".invoker.invokeVA(null,";
        }
        final String string2 = str + this.getArgsBody(methodDeclaration.parameters) + ")";
        this.populateModifiers(methodDeclaration.modifiers);
        this.addLine(string2);
        this.addLine("}");
    }
    
    private boolean hasConversion(final FieldDeclaration fieldDeclaration) {
        return fieldDeclaration.type.cast != null && (!fieldDeclaration.modifiers.isReadonly() || fieldDeclaration.type.type != Object.class);
    }
    
    private boolean hasConversion(final ConstructorDeclaration constructorDeclaration) {
        if (constructorDeclaration.type.cast != null && constructorDeclaration.type.cast.type != Object.class) {
            return true;
        }
        for (int i = 0; i < constructorDeclaration.parameters.parameters.length; ++i) {
            if (constructorDeclaration.parameters.parameters[i].type.cast != null) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasConversion(final MethodDeclaration methodDeclaration) {
        if (methodDeclaration.returnType.cast != null && methodDeclaration.returnType.cast.type != Object.class) {
            return true;
        }
        for (int i = 0; i < methodDeclaration.parameters.parameters.length; ++i) {
            if (methodDeclaration.parameters.parameters[i].type.cast != null) {
                return true;
            }
        }
        return false;
    }
    
    private String getMethodAppend(final MethodDeclaration methodDeclaration) {
        String string = "";
        if (this.hasConversion(methodDeclaration)) {
            string += ".Converted";
        }
        final TypeDeclaration typeDeclaration = (methodDeclaration.returnType.cast != null) ? methodDeclaration.returnType.cast : methodDeclaration.returnType;
        String s;
        if (typeDeclaration.type != null && typeDeclaration.type.isPrimitive()) {
            s = string + "<" + BoxedType.getBoxedType(typeDeclaration.type).getSimpleName() + ">";
        }
        else {
            s = string + "<" + this.getTypeStr(typeDeclaration, true) + ">";
        }
        return s;
    }
    
    private String getExposedTypeStr(final TypeDeclaration typeDeclaration) {
        return this.getTypeStr(typeDeclaration.exposed(), false);
    }
    
    private String getFieldTypeStr(final FieldDeclaration fieldDeclaration) {
        return this.getExposedTypeStr(fieldDeclaration.type);
    }
    
    private String resolveImport(final String s) {
        String substring;
        for (substring = s; substring.endsWith("[]"); substring = substring.substring(0, substring.length() - 2)) {}
        final String substring2 = s.substring(s.lastIndexOf(46) + 1);
        final String substring3 = substring.substring(substring.indexOf(46) + 1);
        final String s2 = this.imports.get(substring3);
        String s3;
        if (s2 != null) {
            if (s2.equals(substring)) {
                s3 = substring2;
            }
            else {
                s3 = s;
            }
        }
        else {
            this.imports.put(substring3, substring);
            s3 = substring2;
        }
        return s3;
    }
    
    private String getTypeStr(final TypeDeclaration typeDeclaration, final boolean b) {
        if (typeDeclaration.isArray()) {
            return this.getTypeStr(typeDeclaration.getComponentType(), false) + "[]";
        }
        String s;
        if (b && typeDeclaration.isPrimitive) {
            s = BoxedType.getBoxedType(typeDeclaration.type).getSimpleName();
        }
        else if (typeDeclaration.isBuiltin()) {
            s = typeDeclaration.typeName;
        }
        else {
            s = this.resolveImport(typeDeclaration.typePath);
        }
        if (typeDeclaration.isWildcard) {
            if (s.length() > 0) {
                s = "? extends " + s;
            }
            else {
                s = "?";
            }
        }
        if (typeDeclaration.genericTypes.length > 0) {
            String str = s + "<";
            int n = 1;
            for (final TypeDeclaration typeDeclaration2 : typeDeclaration.genericTypes) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    str += ", ";
                }
                str += this.getTypeStr(typeDeclaration2, true);
            }
            s = str + ">";
        }
        return s;
    }
    
    private static String getPropertyName(final FieldDeclaration fieldDeclaration) {
        final String real = fieldDeclaration.name.real();
        if (real.isEmpty()) {
            return "UNKNOWN";
        }
        return real.substring(0, 1).toUpperCase(Locale.ENGLISH) + real.substring(1);
    }
    
    private String getPrimFieldType(final FieldDeclaration fieldDeclaration) {
        final Class<?> type = fieldDeclaration.type.exposed().type;
        if (type != null) {
            if (type.equals(Byte.TYPE)) {
                return "Byte";
            }
            if (type.equals(Short.TYPE)) {
                return "Short";
            }
            if (type.equals(Integer.TYPE)) {
                return "Integer";
            }
            if (type.equals(Long.TYPE)) {
                return "Long";
            }
            if (type.equals(Float.TYPE)) {
                return "Float";
            }
            if (type.equals(Double.TYPE)) {
                return "Double";
            }
            if (type.equals(Character.TYPE)) {
                return "Character";
            }
            if (type.equals(Boolean.TYPE)) {
                return "Boolean";
            }
        }
        return "";
    }
    
    private String handleName(final ClassDeclaration classDeclaration) {
        return filterTypeName(classDeclaration.type.typeName) + "Handle";
    }
    
    private String className(final ClassDeclaration classDeclaration) {
        return filterTypeName(classDeclaration.type.typeName) + "Class";
    }
    
    private static String filterTypeName(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex != -1) {
            return s.substring(lastIndex + 1);
        }
        return s;
    }
    
    private void addComment(final String s) {
        final String[] split = s.split("\\r?\\n");
        if (split.length == 1) {
            this.addRawLine("/** " + split[0] + " */");
        }
        else {
            this.addRawLine("/**");
            final String[] array = split;
            for (int length = array.length, i = 0; i < length; ++i) {
                this.addRawLine(" * " + array[i]);
            }
            this.addRawLine(" */");
        }
    }
    
    private void addLine() {
        this.builder.append('\n');
    }
    
    private void addLine(final String str) {
        if (str.endsWith("}")) {
            this.indent(-1);
        }
        for (int i = 0; i < this.indent; ++i) {
            this.builder.append("    ");
        }
        this.builder.append(str);
        if (str.endsWith("{")) {
            this.indent(1);
        }
        else if (str.endsWith("}")) {
            this.builder.append('\n');
        }
        else if (!str.startsWith("//") && !str.startsWith("/*") && !str.startsWith("@")) {
            this.builder.append(';');
        }
        this.builder.append('\n');
    }
    
    private void addRawLine(final String str) {
        for (int i = 0; i < this.indent; ++i) {
            this.builder.append("    ");
        }
        this.builder.append(str);
        this.builder.append('\n');
    }
    
    private void indent(final int n) {
        this.indent += n;
    }
}
