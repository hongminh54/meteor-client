// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import java.util.NoSuchElementException;

class IntQueue
{
    private Entry head;
    private Entry tail;
    
    void add(final int n) {
        final Entry entry = new Entry(n);
        if (this.tail != null) {
            this.tail.next = entry;
        }
        this.tail = entry;
        if (this.head == null) {
            this.head = entry;
        }
    }
    
    boolean isEmpty() {
        return this.head == null;
    }
    
    int take() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        final int access$200 = this.head.value;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        return access$200;
    }
    
    private static class Entry
    {
        private Entry next;
        private int value;
        
        private Entry(final int value) {
            this.value = value;
        }
    }
}
