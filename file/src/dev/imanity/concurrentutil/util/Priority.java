// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public enum Priority
{
    COMPLETING(-1), 
    BLOCKING, 
    HIGHEST, 
    HIGHER, 
    HIGH, 
    NORMAL, 
    LOW, 
    LOWER, 
    LOWEST, 
    IDLE;
    
    static final Priority[] PRIORITIES;
    public static final int TOTAL_PRIORITIES;
    public static final int TOTAL_SCHEDULABLE_PRIORITIES;
    private static int priorityCounter;
    public final int priority;
    
    public static boolean isValidPriority(final Priority priority) {
        return priority != null && priority != Priority.COMPLETING;
    }
    
    public static Priority max(final Priority priority, final Priority priority2) {
        return priority.isHigherOrEqualPriority(priority2) ? priority : priority2;
    }
    
    public static Priority min(final Priority priority, final Priority priority2) {
        return priority.isLowerOrEqualPriority(priority2) ? priority : priority2;
    }
    
    public boolean isHigherOrEqualPriority(final Priority priority) {
        return this.priority <= priority.priority;
    }
    
    public boolean isHigherPriority(final Priority priority) {
        return this.priority < priority.priority;
    }
    
    public boolean isLowerOrEqualPriority(final Priority priority) {
        return this.priority >= priority.priority;
    }
    
    public boolean isLowerPriority(final Priority priority) {
        return this.priority > priority.priority;
    }
    
    public boolean isHigherOrEqualPriority(final int n) {
        return this.priority <= n;
    }
    
    public boolean isHigherPriority(final int n) {
        return this.priority < n;
    }
    
    public boolean isLowerOrEqualPriority(final int n) {
        return this.priority >= n;
    }
    
    public boolean isLowerPriority(final int n) {
        return this.priority > n;
    }
    
    public static boolean isHigherOrEqualPriority(final int n, final int n2) {
        return n <= n2;
    }
    
    public static boolean isHigherPriority(final int n, final int n2) {
        return n < n2;
    }
    
    public static boolean isLowerOrEqualPriority(final int n, final int n2) {
        return n >= n2;
    }
    
    public static boolean isLowerPriority(final int n, final int n2) {
        return n > n2;
    }
    
    public static Priority getPriority(final int n) {
        return Priority.PRIORITIES[n + 1];
    }
    
    private static int nextCounter() {
        return Priority.priorityCounter++;
    }
    
    private Priority() {
        this(nextCounter());
    }
    
    private Priority(final int priority) {
        this.priority = priority;
    }
    
    static {
        PRIORITIES = values();
        TOTAL_PRIORITIES = Priority.PRIORITIES.length;
        TOTAL_SCHEDULABLE_PRIORITIES = Priority.TOTAL_PRIORITIES - 1;
    }
}
