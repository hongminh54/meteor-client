// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.asm.signature;

public class SignatureReader
{
    private final String signatureValue;
    
    public SignatureReader(final String signatureValue) {
        this.signatureValue = signatureValue;
    }
    
    public void accept(final SignatureVisitor signatureVisitor) {
        final String signatureValue = this.signatureValue;
        final int length = signatureValue.length();
        int n;
        if (signatureValue.charAt(0) == '<') {
            n = 2;
            char char1;
            do {
                final int index = signatureValue.indexOf(58, n);
                signatureVisitor.visitFormalTypeParameter(signatureValue.substring(n - 1, index));
                n = index + 1;
                final char char2 = signatureValue.charAt(n);
                if (char2 == 'L' || char2 == '[' || char2 == 'T') {
                    n = parseType(signatureValue, n, signatureVisitor.visitClassBound());
                }
                while ((char1 = signatureValue.charAt(n++)) == ':') {
                    n = parseType(signatureValue, n, signatureVisitor.visitInterfaceBound());
                }
            } while (char1 != '>');
        }
        else {
            n = 0;
        }
        if (signatureValue.charAt(n) == '(') {
            ++n;
            while (signatureValue.charAt(n) != ')') {
                n = parseType(signatureValue, n, signatureVisitor.visitParameterType());
            }
            for (int i = parseType(signatureValue, n + 1, signatureVisitor.visitReturnType()); i < length; i = parseType(signatureValue, i + 1, signatureVisitor.visitExceptionType())) {}
        }
        else {
            for (int j = parseType(signatureValue, n, signatureVisitor.visitSuperclass()); j < length; j = parseType(signatureValue, j, signatureVisitor.visitInterface())) {}
        }
    }
    
    public void acceptType(final SignatureVisitor signatureVisitor) {
        parseType(this.signatureValue, 0, signatureVisitor);
    }
    
    private static int parseType(final String s, final int n, final SignatureVisitor signatureVisitor) {
        int index = n;
        final char char1 = s.charAt(index++);
        switch (char1) {
            case 66:
            case 67:
            case 68:
            case 70:
            case 73:
            case 74:
            case 83:
            case 86:
            case 90: {
                signatureVisitor.visitBaseType(char1);
                return index;
            }
            case 91: {
                return parseType(s, index, signatureVisitor.visitArrayType());
            }
            case 84: {
                final int index2 = s.indexOf(59, index);
                signatureVisitor.visitTypeVariable(s.substring(index, index2));
                return index2 + 1;
            }
            case 76: {
                int n2 = index;
                int n3 = 0;
                int n4 = 0;
                while (true) {
                    final char char2 = s.charAt(index++);
                    if (char2 == '.' || char2 == ';') {
                        if (n3 == 0) {
                            final String substring = s.substring(n2, index - 1);
                            if (n4 != 0) {
                                signatureVisitor.visitInnerClassType(substring);
                            }
                            else {
                                signatureVisitor.visitClassType(substring);
                            }
                        }
                        if (char2 == ';') {
                            break;
                        }
                        n2 = index;
                        n3 = 0;
                        n4 = 1;
                    }
                    else {
                        if (char2 != '<') {
                            continue;
                        }
                        final String substring2 = s.substring(n2, index - 1);
                        if (n4 != 0) {
                            signatureVisitor.visitInnerClassType(substring2);
                        }
                        else {
                            signatureVisitor.visitClassType(substring2);
                        }
                        n3 = 1;
                        char char3;
                        while ((char3 = s.charAt(index)) != '>') {
                            switch (char3) {
                                case 42: {
                                    ++index;
                                    signatureVisitor.visitTypeArgument();
                                    continue;
                                }
                                case 43:
                                case 45: {
                                    index = parseType(s, index + 1, signatureVisitor.visitTypeArgument(char3));
                                    continue;
                                }
                                default: {
                                    index = parseType(s, index, signatureVisitor.visitTypeArgument('='));
                                    continue;
                                }
                            }
                        }
                    }
                }
                signatureVisitor.visitEnd();
                return index;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}
