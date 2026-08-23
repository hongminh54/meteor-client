// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import dev.imanity.antixray.libs.mountiplex.reflection.util.InputTypeMap;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.util.signature.MethodSignature;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.signature.FieldSignature;

public abstract class Remapping
{
    public final Class<?> declaringClass;
    
    public static Lookup createLookup() {
        return new Lookup(new LookupTable());
    }
    
    public Remapping(final Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }
    
    public final Class<?> getDeclaringClass() {
        return this.declaringClass;
    }
    
    public abstract Object getKey();
    
    public static class Lookup implements Cloneable
    {
        private LookupTable table;
        
        private Lookup(final LookupTable table) {
            this.table = table;
        }
        
        private void makeMutable() {
            if (this.table.isReadOnly()) {
                this.table = this.table.copy(false);
            }
        }
        
        public void assign(final Lookup lookup) {
            this.table = lookup.table;
        }
        
        public void addRemapping(final Remapping remapping) {
            this.makeMutable();
            this.table.addRemapping(remapping);
        }
        
        public FieldRemapping find(final FieldDeclaration fieldDeclaration) {
            return this.find(fieldDeclaration.getResolver().getDeclaredClass(), new FieldSignature(fieldDeclaration.name.value()));
        }
        
        public FieldRemapping find(final Class<?> clazz, final FieldSignature fieldSignature) {
            if (clazz == null) {
                return null;
            }
            final Iterator<ClassRemapping> iterator = this.table.getAll(clazz).iterator();
            while (iterator.hasNext()) {
                final FieldRemapping find = iterator.next().find(fieldSignature);
                if (find != null) {
                    return find;
                }
            }
            return null;
        }
        
        public MethodRemapping find(final MethodDeclaration methodDeclaration) {
            return this.find(methodDeclaration.getDeclaringClass(), new MethodSignature(methodDeclaration.name.value(), methodDeclaration.parameters.toParamArray()));
        }
        
        public MethodRemapping find(final Class<?> clazz, final MethodSignature methodSignature) {
            if (clazz == null) {
                return null;
            }
            final Iterator<ClassRemapping> iterator = this.table.getAll(clazz).iterator();
            while (iterator.hasNext()) {
                final MethodRemapping find = iterator.next().find(methodSignature);
                if (find != null) {
                    return find;
                }
            }
            return null;
        }
        
        public Collection<Remapping> getAllStoredRemappings() {
            return this.table.getAllStoredRemappings();
        }
        
        public Lookup clone() {
            return new Lookup(this.table.copy(true));
        }
    }
    
    private static final class LookupTable
    {
        private final Object lock;
        private final boolean readOnly;
        private final InputTypeMap<ClassRemapping> remappingsByDeclaringClass;
        
        public LookupTable() {
            this.lock = new Object();
            this.readOnly = true;
            this.remappingsByDeclaringClass = new InputTypeMap<ClassRemapping>();
        }
        
        private LookupTable(final LookupTable lookupTable, final boolean readOnly) {
            this.readOnly = readOnly;
            if (lookupTable.readOnly && readOnly) {
                this.lock = lookupTable.lock;
                this.remappingsByDeclaringClass = lookupTable.remappingsByDeclaringClass;
            }
            else {
                this.lock = new Object();
                synchronized (lookupTable.lock) {
                    this.remappingsByDeclaringClass = lookupTable.remappingsByDeclaringClass.clone();
                }
            }
        }
        
        public boolean isReadOnly() {
            return this.readOnly;
        }
        
        public Collection<ClassRemapping> getAll(final Class<?> clazz) {
            synchronized (this.lock) {
                return this.remappingsByDeclaringClass.getAll(clazz);
            }
        }
        
        public Collection<Remapping> getAllStoredRemappings() {
            synchronized (this.lock) {
                return this.remappingsByDeclaringClass.values().stream().flatMap(classRemapping -> classRemapping.getAll().stream()).collect((Collector<? super Object, ?, Collection<Remapping>>)Collectors.toList());
            }
        }
        
        public void addRemapping(final Remapping remapping) {
            synchronized (this.lock) {
                ClassRemapping classRemapping = this.remappingsByDeclaringClass.get(remapping.getDeclaringClass());
                if (classRemapping == null) {
                    classRemapping = new ClassRemapping(remapping.getDeclaringClass());
                }
                final ClassRemapping withRemapping = classRemapping.withRemapping(remapping);
                this.remappingsByDeclaringClass.put(withRemapping.getDeclaringClass(), withRemapping);
            }
        }
        
        public LookupTable copy(final boolean b) {
            return new LookupTable(this, b);
        }
    }
    
    public static class ClassRemapping extends Remapping
    {
        private final Map<Object, Remapping> remappings;
        
        public ClassRemapping(final Class<?> clazz) {
            super(clazz);
            this.remappings = Collections.emptyMap();
        }
        
        private ClassRemapping(final ClassRemapping classRemapping, final Remapping value) {
            super(classRemapping.getDeclaringClass());
            if (classRemapping.remappings.isEmpty()) {
                this.remappings = Collections.singletonMap(value.getKey(), value);
            }
            else {
                (this.remappings = new HashMap<Object, Remapping>(classRemapping.remappings)).put(value.getKey(), value);
            }
        }
        
        public ClassRemapping withRemapping(final Remapping remapping) {
            return new ClassRemapping(this, remapping);
        }
        
        @Override
        public Object getKey() {
            return this.getDeclaringClass();
        }
        
        public FieldRemapping find(final FieldSignature fieldSignature) {
            return this.remappings.get(fieldSignature);
        }
        
        public MethodRemapping find(final MethodSignature methodSignature) {
            return this.remappings.get(methodSignature);
        }
        
        public Collection<Remapping> getAll() {
            return this.remappings.values();
        }
    }
    
    public static class FieldRemapping extends Remapping
    {
        public final FieldSignature signature;
        public final Field field;
        public final FieldDeclaration declaration;
        
        public FieldRemapping(final FieldDeclaration declaration) {
            super(declaration.getResolver().getDeclaredClass());
            this.signature = new FieldSignature(declaration.name.firstReal());
            this.field = declaration.field;
            this.declaration = declaration;
            if (this.field == null) {
                throw new IllegalStateException("Field for remapping is not resolved");
            }
        }
        
        @Override
        public Object getKey() {
            return this.signature;
        }
        
        @Override
        public String toString() {
            return "FieldRemapping { " + this.declaration.toString() + " }";
        }
    }
    
    public static class MethodRemapping extends Remapping
    {
        public final MethodSignature signature;
        public final Method method;
        public final MethodDeclaration declaration;
        
        public MethodRemapping(final MethodDeclaration declaration) {
            super(declaration.getResolver().getDeclaredClass());
            this.signature = new MethodSignature(declaration.name.firstReal(), declaration.parameters.toParamArray());
            this.method = declaration.method;
            this.declaration = declaration;
            if (this.method == null) {
                throw new IllegalStateException("Method for remapping is not resolved");
            }
        }
        
        @Override
        public Object getKey() {
            return this.signature;
        }
        
        @Override
        public String toString() {
            return "MethodRemapping { " + this.declaration.toString() + " }";
        }
    }
}
