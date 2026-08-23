// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.NotSerializableException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.SERIALIZATION)
public class ObjectInputStreamInstantiator<T> implements ObjectInstantiator<T>
{
    private final ObjectInputStream inputStream;
    
    public ObjectInputStreamInstantiator(final Class<T> clazz) {
        if (Serializable.class.isAssignableFrom(clazz)) {
            try {
                this.inputStream = new ObjectInputStream(new MockStream(clazz));
                return;
            }
            catch (final IOException ex) {
                throw new Error("IOException: " + ex.getMessage());
            }
            throw new ObjenesisException(new NotSerializableException(clazz + " not serializable"));
        }
        throw new ObjenesisException(new NotSerializableException(clazz + " not serializable"));
    }
    
    @Override
    public T newInstance() {
        try {
            return (T)this.inputStream.readObject();
        }
        catch (final ClassNotFoundException ex) {
            throw new Error("ClassNotFoundException: " + ex.getMessage());
        }
        catch (final Exception ex2) {
            throw new ObjenesisException(ex2);
        }
    }
    
    private static class MockStream extends InputStream
    {
        private int pointer;
        private byte[] data;
        private int sequence;
        private static final int[] NEXT;
        private final byte[][] buffers;
        private static byte[] HEADER;
        private static byte[] REPEATING_DATA;
        
        private static void initialize() {
            try {
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final DataOutputStream dataOutputStream = new DataOutputStream(out);
                dataOutputStream.writeShort(-21267);
                dataOutputStream.writeShort(5);
                MockStream.HEADER = out.toByteArray();
                final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                final DataOutputStream dataOutputStream2 = new DataOutputStream(out2);
                dataOutputStream2.writeByte(115);
                dataOutputStream2.writeByte(113);
                dataOutputStream2.writeInt(8257536);
                MockStream.REPEATING_DATA = out2.toByteArray();
            }
            catch (final IOException ex) {
                throw new Error("IOException: " + ex.getMessage());
            }
        }
        
        public MockStream(final Class<?> cl) {
            this.pointer = 0;
            this.sequence = 0;
            this.data = MockStream.HEADER;
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(out);
            try {
                dataOutputStream.writeByte(115);
                dataOutputStream.writeByte(114);
                dataOutputStream.writeUTF(cl.getName());
                dataOutputStream.writeLong(ObjectStreamClass.lookup(cl).getSerialVersionUID());
                dataOutputStream.writeByte(2);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeByte(120);
                dataOutputStream.writeByte(112);
            }
            catch (final IOException ex) {
                throw new Error("IOException: " + ex.getMessage());
            }
            this.buffers = new byte[][] { MockStream.HEADER, out.toByteArray(), MockStream.REPEATING_DATA };
        }
        
        private void advanceBuffer() {
            this.pointer = 0;
            this.sequence = MockStream.NEXT[this.sequence];
            this.data = this.buffers[this.sequence];
        }
        
        @Override
        public int read() {
            final byte b = this.data[this.pointer++];
            if (this.pointer >= this.data.length) {
                this.advanceBuffer();
            }
            return b;
        }
        
        @Override
        public int available() {
            return Integer.MAX_VALUE;
        }
        
        @Override
        public int read(final byte[] array, int n, final int n2) {
            int n3 = n2;
            for (int i = this.data.length - this.pointer; i <= n3; i = this.data.length - this.pointer) {
                System.arraycopy(this.data, this.pointer, array, n, i);
                n += i;
                n3 -= i;
                this.advanceBuffer();
            }
            if (n3 > 0) {
                System.arraycopy(this.data, this.pointer, array, n, n3);
                this.pointer += n3;
            }
            return n2;
        }
        
        static {
            NEXT = new int[] { 1, 2, 2 };
            initialize();
        }
    }
}
