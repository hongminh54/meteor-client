// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.context.DeclarationParserContext;
import java.util.Arrays;
import java.util.Comparator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.util.Iterator;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.Collections;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.parsers.ParserStringBuffer;

public abstract class Declaration
{
    private final ParserStringBuffer _postfix;
    private String _longDeclare;
    protected final StringBuffer _initialDeclaration;
    private final ClassResolver _resolver;
    private List<String> _errors;
    private List<String> _warnings;
    private boolean _loggedErrorsAndWarnings;
    
    public Declaration(final ClassResolver resolver) {
        this._postfix = new ParserStringBuffer();
        this._longDeclare = null;
        this._errors = Collections.emptyList();
        this._warnings = Collections.emptyList();
        this._loggedErrorsAndWarnings = true;
        this._resolver = resolver;
        this._initialDeclaration = StringBuffer.EMPTY;
        this._postfix.set(StringBuffer.EMPTY);
    }
    
    public Declaration(final ClassResolver classResolver, final String s) {
        this(classResolver, StringBuffer.of(s));
    }
    
    public Declaration(final ClassResolver resolver, final StringBuffer initialDeclaration) {
        this._postfix = new ParserStringBuffer();
        this._longDeclare = null;
        this._errors = Collections.emptyList();
        this._warnings = Collections.emptyList();
        this._loggedErrorsAndWarnings = true;
        this._resolver = resolver;
        this._initialDeclaration = initialDeclaration;
        this._postfix.set(initialDeclaration);
    }
    
    public final ClassResolver getResolver() {
        return this._resolver;
    }
    
    protected final ModifierDeclaration nextModifier() {
        return this.updatePostfix(new ModifierDeclaration(this._resolver, this._postfix.get()));
    }
    
    protected final NameDeclaration nextName() {
        return this.updatePostfix(new NameDeclaration(this._resolver, this._postfix.get()));
    }
    
    protected final NameDeclaration nextName(final int n) {
        return this.updatePostfix(new NameDeclaration(this._resolver, this._postfix.get(), n));
    }
    
    protected final TypeDeclaration nextType() {
        return this.updatePostfix(TypeDeclaration.parse(this._resolver, this._postfix.get()));
    }
    
    protected final ParameterDeclaration nextParameter(final int n) {
        return this.updatePostfix(new ParameterDeclaration(this._resolver, this._postfix.get(), n));
    }
    
    protected final ParameterListDeclaration nextParameterList() {
        return this.updatePostfix(new ParameterListDeclaration(this._resolver, this._postfix.get()));
    }
    
    protected final ClassDeclaration nextClass() {
        return this.updatePostfix(new ClassDeclaration(this._resolver.clone(), this._postfix.get()));
    }
    
    protected final <T extends Declaration> T updatePostfix(final T t) {
        this._postfix.set(t.getPostfix());
        return t;
    }
    
    public final StringBuffer getPostfix() {
        return this._postfix.get();
    }
    
    protected final ParserStringBuffer getParserPostfix() {
        return this._postfix;
    }
    
    protected final void setPostfix(final StringBuffer stringBuffer) {
        this._postfix.set(stringBuffer);
    }
    
    public final boolean isValid() {
        return !this._postfix.isNull();
    }
    
    protected final void setInvalid() {
        this._postfix.set(null);
    }
    
    public abstract boolean isResolved();
    
    public final List<String> getTemplateWarnings() {
        return this._warnings;
    }
    
    public final List<String> getTemplateErrors() {
        return this._errors;
    }
    
    public final void checkTemplateErrors() {
        if (!this._loggedErrorsAndWarnings) {
            this._loggedErrorsAndWarnings = true;
            if (!this._warnings.isEmpty()) {
                if (this._warnings.size() > 1) {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Warnings in template for declaring class: " + this._resolver.getDeclaredClassName());
                    MountiplexUtil.LOGGER.log(Level.WARNING, "Multiple template warnings for " + this.getTemplateLogIdentity() + ":");
                    final Iterator<String> iterator = this._warnings.iterator();
                    while (iterator.hasNext()) {
                        MountiplexUtil.LOGGER.log(Level.WARNING, "  - " + iterator.next());
                    }
                }
                else {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Warning in template for declaring class: " + this._resolver.getDeclaredClassName());
                    MountiplexUtil.LOGGER.log(Level.WARNING, "Template warning for " + this.getTemplateLogIdentity() + ": " + this._warnings.get(0));
                }
            }
            if (!this._errors.isEmpty()) {
                if (this._errors.size() > 1) {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Errors in template for declaring class: " + this._resolver.getDeclaredClassName());
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Multiple template errors for " + this.getTemplateLogIdentity() + ":");
                    final Iterator<String> iterator2 = this._errors.iterator();
                    while (iterator2.hasNext()) {
                        MountiplexUtil.LOGGER.log(Level.SEVERE, "  - " + iterator2.next());
                    }
                }
                else {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Error in template for declaring class: " + this._resolver.getDeclaredClassName());
                }
            }
        }
        if (this._errors.isEmpty()) {
            return;
        }
        if (this._errors.size() > 1) {
            throw new TemplateError("Multiple template errors for " + this.getTemplateLogIdentity());
        }
        throw new TemplateError("Template error for " + this.getTemplateLogIdentity() + ": " + this._errors.get(0));
    }
    
    protected String getTemplateLogIdentity() {
        return this.toString(false);
    }
    
    public abstract boolean match(final Declaration p0);
    
    public abstract String toString(final boolean p0);
    
    @Override
    public final String toString() {
        return this.toString(false);
    }
    
    public String debugString() {
        final StringBuilder sb = new StringBuilder();
        this.debugString(sb, "");
        return sb.toString();
    }
    
    protected abstract void debugString(final StringBuilder p0, final String p1);
    
    public abstract double similarity(final Declaration p0);
    
    public Declaration discover() {
        if (!this.isValid() || !this.isResolved()) {
            return null;
        }
        return this;
    }
    
    public void discoverAlternatives() {
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Declaration) {
            final Declaration declaration = (Declaration)o;
            if (declaration._longDeclare == null) {
                declaration._longDeclare = declaration.toString(true);
            }
            if (this._longDeclare == null) {
                this._longDeclare = this.toString(true);
            }
            return declaration._longDeclare.equals(this._longDeclare);
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        if (this._longDeclare == null) {
            this._longDeclare = this.toString(true);
        }
        return this._longDeclare.hashCode();
    }
    
    public void modifyBodyRequirement(final Requirement requirement, final StringBuilder sb, final String s, final String s2, final int n, final int n2) {
        throw new UnsupportedOperationException("Declaration " + this.toString() + " can not be added as requirement");
    }
    
    public void addAsRequirement(final ExtendedClassWriter<?> extendedClassWriter, final Requirement requirement, final String s) {
        throw new UnsupportedOperationException("Declaration " + this.toString() + " can not be added as requirement");
    }
    
    public static <T extends Declaration> void sortSimilarity(final T t, final List<T> list) {
        Collections.sort(list, (Comparator<? super T>)createSimilarityComparator(t));
    }
    
    public static <T extends Declaration> void sortSimilarity(final T t, final T[] a) {
        Arrays.sort(a, (Comparator<? super T>)createSimilarityComparator(t));
    }
    
    private static <T extends Declaration> Comparator<T> createSimilarityComparator(final T t) {
        return (declaration2, declaration3) -> {
            declaration.similarity(declaration2);
            declaration.similarity(declaration3);
            final double n;
            final double n2;
            if (n == n2) {
                return 0;
            }
            else if (n > n2) {
                return -1;
            }
            else {
                return 1;
            }
        };
    }
    
    public static Declaration parseDeclaration(final ClassResolver classResolver, final String s) {
        return parseDeclaration(classResolver, StringBuffer.of(s));
    }
    
    public static Declaration parseDeclaration(final ClassResolver classResolver, final StringBuffer stringBuffer) {
        final MethodDeclaration methodDeclaration = new MethodDeclaration(classResolver, stringBuffer);
        if (methodDeclaration.isValid()) {
            return methodDeclaration;
        }
        final ConstructorDeclaration constructorDeclaration = new ConstructorDeclaration(classResolver, stringBuffer);
        if (constructorDeclaration.isValid()) {
            return constructorDeclaration;
        }
        final FieldDeclaration fieldDeclaration = new FieldDeclaration(classResolver, stringBuffer);
        if (fieldDeclaration.isValid()) {
            return fieldDeclaration;
        }
        return null;
    }
    
    protected class BaseDeclarationParserContext implements DeclarationParserContext
    {
        @Override
        public ClassResolver getResolver() {
            return Declaration.this._resolver;
        }
        
        @Override
        public ParserStringBuffer getBuffer() {
            return Declaration.this._postfix;
        }
        
        @Override
        public void addWarning(final String s) {
            if (Declaration.this._warnings.isEmpty()) {
                Declaration.this._warnings = (List<String>)new ArrayList();
            }
            Declaration.this._warnings.add(s);
            Declaration.this._loggedErrorsAndWarnings = false;
        }
        
        @Override
        public void addError(final String s) {
            if (Declaration.this._errors.isEmpty()) {
                Declaration.this._errors = (List<String>)new ArrayList();
            }
            Declaration.this._errors.add(s);
            Declaration.this._loggedErrorsAndWarnings = false;
        }
    }
}
