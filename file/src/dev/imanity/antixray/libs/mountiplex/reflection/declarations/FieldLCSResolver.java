// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class FieldLCSResolver
{
    public static List<Pair> lcs(final FieldDeclaration[] a, final FieldDeclaration[] a2) {
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        final Sequence sequence = new Sequence(Arrays.asList(a), Arrays.asList(a2));
        Sequence sequence2 = new Sequence();
        for (final FieldDeclaration e : sequence.a) {
            if (e.name.isObfuscated()) {
                sequence2.a.add(e);
            }
            else {
                FieldDeclaration fieldDeclaration = null;
                while (!sequence.b.isEmpty()) {
                    final FieldDeclaration e2 = sequence.b.remove(0);
                    if (e.match(e2)) {
                        fieldDeclaration = e2;
                        break;
                    }
                    sequence2.b.add(e2);
                }
                list.add(new Pair(e, fieldDeclaration));
                if (fieldDeclaration == null) {
                    sequence.b.addAll(sequence2.b);
                    sequence2.b.clear();
                }
                else {
                    int i = 0;
                    while (i < sequence2.b.size()) {
                        final FieldDeclaration fieldDeclaration2 = sequence2.b.get(i);
                        if (fieldDeclaration2.name.isObfuscated()) {
                            ++i;
                        }
                        else {
                            list.add(new Pair(null, fieldDeclaration2));
                            sequence2.b.remove(i);
                        }
                    }
                    if (sequence2.a.size() > 0 && sequence2.b.size() == 0) {
                        final Iterator<FieldDeclaration> iterator2 = sequence2.a.iterator();
                        while (iterator2.hasNext()) {
                            list.add(new Pair(iterator2.next(), null));
                        }
                    }
                    else if (sequence2.a.size() == 0 && sequence2.b.size() > 0) {
                        final Iterator<FieldDeclaration> iterator3 = sequence2.b.iterator();
                        while (iterator3.hasNext()) {
                            list.add(new Pair(null, iterator3.next()));
                        }
                    }
                    else if (sequence2.a.size() > 0 && sequence2.b.size() > 0) {
                        list2.add(sequence2);
                    }
                    sequence2 = new Sequence();
                }
            }
        }
        sequence2.b.addAll(sequence.b);
        if (sequence2.a.size() > 0 && sequence2.b.size() == 0) {
            final Iterator<FieldDeclaration> iterator4 = sequence2.a.iterator();
            while (iterator4.hasNext()) {
                list.add(new Pair(iterator4.next(), null));
            }
        }
        else if (sequence2.a.size() == 0 && sequence2.b.size() > 0) {
            final Iterator<FieldDeclaration> iterator5 = sequence2.b.iterator();
            while (iterator5.hasNext()) {
                list.add(new Pair(null, iterator5.next()));
            }
        }
        else if (sequence2.a.size() > 0 && sequence2.b.size() > 0) {
            list2.add(sequence2);
        }
        for (final Sequence sequence3 : list2) {
            final FieldDeclaration[] array = sequence3.a.toArray(new FieldDeclaration[0]);
            final FieldDeclaration[] array2 = sequence3.b.toArray(new FieldDeclaration[0]);
            if (sequence3.a.size() > 0 && sequence3.b.size() > 0) {
                final Iterator<FieldDeclaration> iterator7 = sequence3.a.iterator();
                while (iterator7.hasNext()) {
                    final FieldDeclaration fieldDeclaration3 = iterator7.next();
                    final Iterator<FieldDeclaration> iterator8 = sequence3.b.iterator();
                    while (iterator8.hasNext()) {
                        final FieldDeclaration fieldDeclaration4 = iterator8.next();
                        if (fieldDeclaration3.name.value().equals(fieldDeclaration4.name.value())) {
                            if (fieldDeclaration3.match(fieldDeclaration4)) {
                                list.add(new Pair(fieldDeclaration3, fieldDeclaration4, array, array2));
                            }
                            else {
                                list.add(new Pair(fieldDeclaration3, null, array, array2));
                                list.add(new Pair(null, fieldDeclaration4, array, array2));
                            }
                            iterator7.remove();
                            iterator8.remove();
                            break;
                        }
                    }
                }
            }
            final Iterator<FieldDeclaration> iterator9 = sequence3.a.iterator();
            while (iterator9.hasNext()) {
                list.add(new Pair(iterator9.next(), null, array, array2));
            }
            final Iterator<FieldDeclaration> iterator10 = sequence3.b.iterator();
            while (iterator10.hasNext()) {
                list.add(new Pair(null, iterator10.next(), array, array2));
            }
        }
        return list;
    }
    
    public static <T extends Declaration> void logAlternatives(final String s, final T[] a, final T t, final boolean b) {
        if (!t.getResolver().getLogErrors()) {
            return;
        }
        if (b) {
            MountiplexUtil.LOGGER.warning("Requirement was not found in " + t.getResolver().getDeclaredClassName() + ":");
        }
        else {
            MountiplexUtil.LOGGER.warning("A class member of " + t.getResolver().getDeclaredClassName() + " was not found!");
        }
        if (a.length == 0) {
            MountiplexUtil.LOGGER.warning("Failed to find " + s + " " + t + " (No alternatives)");
        }
        else {
            final ArrayList list = new ArrayList((Collection<? extends E>)Arrays.asList(a));
            Declaration.sortSimilarity(t, list);
            MountiplexUtil.LOGGER.warning("Failed to find " + s + " " + t + " - Alternatives:");
            int n = 8;
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                MountiplexUtil.LOGGER.warning("  - " + iterator.next());
                if (--n == 0) {
                    break;
                }
            }
        }
    }
    
    public static void resolve(final FieldDeclaration[] array, final FieldDeclaration[] array2) {
        final List<Pair> lcs = lcs(array, array2);
        final Iterator<Pair> iterator = lcs.iterator();
        while (iterator.hasNext()) {
            final Pair pair = iterator.next();
            if (pair.a != null && pair.b != null) {
                pair.a.copyFieldFrom(pair.b);
                iterator.remove();
            }
        }
        final Iterator<Pair> iterator2 = lcs.iterator();
        while (iterator2.hasNext()) {
            final Pair pair2 = iterator2.next();
            if (pair2.a == null) {
                continue;
            }
            for (final FieldDeclaration fieldDeclaration : array2) {
                if (pair2.a.match(fieldDeclaration)) {
                    pair2.a.copyFieldFrom(fieldDeclaration);
                    iterator2.remove();
                    break;
                }
            }
        }
        for (final Pair pair3 : lcs) {
            if (pair3.b == null && pair3.a != null && !pair3.a.modifiers.isOptional()) {
                if (pair3.bb.length > 0) {
                    logAlternatives("field", pair3.bb, pair3.a, false);
                }
                else {
                    logAlternatives("field", array2, pair3.a, false);
                }
            }
        }
    }
    
    public static class Pair
    {
        public final FieldDeclaration a;
        public final FieldDeclaration b;
        public final FieldDeclaration[] aa;
        public final FieldDeclaration[] bb;
        
        public Pair(final FieldDeclaration a, final FieldDeclaration b) {
            this.a = a;
            this.b = b;
            final FieldDeclaration[] array = new FieldDeclaration[0];
            this.bb = array;
            this.aa = array;
        }
        
        public Pair(final FieldDeclaration a, final FieldDeclaration b, final FieldDeclaration[] aa, final FieldDeclaration[] bb) {
            this.a = a;
            this.b = b;
            this.aa = aa;
            this.bb = bb;
        }
    }
    
    public static class Sequence
    {
        public final ArrayList<FieldDeclaration> a;
        public final ArrayList<FieldDeclaration> b;
        
        public Sequence() {
            this.a = new ArrayList<FieldDeclaration>();
            this.b = new ArrayList<FieldDeclaration>();
        }
        
        public Sequence(final List<FieldDeclaration> c, final List<FieldDeclaration> c2) {
            this.a = new ArrayList<FieldDeclaration>();
            this.b = new ArrayList<FieldDeclaration>();
            this.a.addAll(c);
            this.b.addAll(c2);
        }
    }
}
