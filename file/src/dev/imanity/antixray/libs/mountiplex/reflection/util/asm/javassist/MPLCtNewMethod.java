// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist;

import dev.imanity.antixray.libs.javassist.CtMember;
import dev.imanity.antixray.libs.javassist.compiler.Javac;
import dev.imanity.antixray.libs.javassist.compiler.CompileError;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.CtClass;

public class MPLCtNewMethod
{
    public static CtMethod make(final String s, final CtClass ctClass) {
        final Javac create = MPLJavac.create(ctClass);
        try {
            final CtMember compile = create.compile(s);
            if (compile instanceof CtMethod) {
                return (CtMethod)compile;
            }
        }
        catch (final CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        throw new CannotCompileException("not a method");
    }
}
