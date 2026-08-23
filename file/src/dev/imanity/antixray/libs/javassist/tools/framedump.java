// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools;

import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.javassist.bytecode.analysis.FramePrinter;
import dev.imanity.antixray.libs.javassist.ClassPool;

public class framedump
{
    private framedump() {
    }
    
    public static void main(final String[] array) {
        if (array.length != 1) {
            System.err.println("Usage: java javassist.tools.framedump <fully-qualified class name>");
            return;
        }
        final CtClass value = ClassPool.getDefault().get(array[0]);
        System.out.println("Frame Dump of " + value.getName() + ":");
        FramePrinter.print(value, System.out);
    }
}
