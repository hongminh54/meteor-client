// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class Subroutine
{
    private List<Integer> callers;
    private Set<Integer> access;
    private int start;
    
    public Subroutine(final int start, final int i) {
        this.callers = new ArrayList<Integer>();
        this.access = new HashSet<Integer>();
        this.start = start;
        this.callers.add(i);
    }
    
    public void addCaller(final int i) {
        this.callers.add(i);
    }
    
    public int start() {
        return this.start;
    }
    
    public void access(final int i) {
        this.access.add(i);
    }
    
    public boolean isAccessed(final int i) {
        return this.access.contains(i);
    }
    
    public Collection<Integer> accessed() {
        return this.access;
    }
    
    public Collection<Integer> callers() {
        return this.callers;
    }
    
    @Override
    public String toString() {
        return "start = " + this.start + " callers = " + this.callers.toString();
    }
}
