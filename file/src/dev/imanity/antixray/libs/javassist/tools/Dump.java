// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.tools;

import dev.imanity.antixray.libs.javassist.bytecode.ClassFilePrinter;
import java.io.OutputStream;
import java.io.PrintWriter;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Dump
{
    private Dump() {
    }
    
    public static void main(final String[] array) {
        if (array.length != 1) {
            System.err.println("Usage: java Dump <class file name>");
            return;
        }
        final ClassFile classFile = new ClassFile(new DataInputStream(new FileInputStream(array[0])));
        final PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println("*** constant pool ***");
        classFile.getConstPool().print(printWriter);
        printWriter.println();
        printWriter.println("*** members ***");
        ClassFilePrinter.print(classFile, printWriter);
    }
}
