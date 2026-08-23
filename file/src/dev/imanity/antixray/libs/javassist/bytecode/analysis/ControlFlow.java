// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.bytecode.analysis;

import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.bytecode.stackmap.BasicBlock;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.javassist.bytecode.MethodInfo;
import dev.imanity.antixray.libs.javassist.CtClass;

public class ControlFlow
{
    private CtClass clazz;
    private MethodInfo methodInfo;
    private Block[] basicBlocks;
    private Frame[] frames;
    
    public ControlFlow(final CtMethod ctMethod) {
        this(ctMethod.getDeclaringClass(), ctMethod.getMethodInfo2());
    }
    
    public ControlFlow(final CtClass clazz, final MethodInfo methodInfo) {
        this.clazz = clazz;
        this.methodInfo = methodInfo;
        this.frames = null;
        this.basicBlocks = (Block[])new BasicBlock.Maker() {
            @Override
            protected BasicBlock makeBlock(final int n) {
                return new Block(n, ControlFlow.this.methodInfo);
            }
            
            @Override
            protected BasicBlock[] makeArray(final int n) {
                return new Block[n];
            }
        }.make(methodInfo);
        if (this.basicBlocks == null) {
            this.basicBlocks = new Block[0];
        }
        final int length = this.basicBlocks.length;
        final int[] array = new int[length];
        for (int i = 0; i < length; ++i) {
            final Block block = this.basicBlocks[i];
            block.index = i;
            block.entrances = new Block[block.incomings()];
            array[i] = 0;
        }
        for (int j = 0; j < length; ++j) {
            final Block block2 = this.basicBlocks[j];
            for (int k = 0; k < block2.exits(); ++k) {
                final Block exit = block2.exit(k);
                exit.entrances[array[exit.index]++] = block2;
            }
            final Catcher[] catchers = block2.catchers();
            for (int l = 0; l < catchers.length; ++l) {
                final Block access$100 = catchers[l].node;
                access$100.entrances[array[access$100.index]++] = block2;
            }
        }
    }
    
    public Block[] basicBlocks() {
        return this.basicBlocks;
    }
    
    public Frame frameAt(final int n) {
        if (this.frames == null) {
            this.frames = new Analyzer().analyze(this.clazz, this.methodInfo);
        }
        return this.frames[n];
    }
    
    public Node[] dominatorTree() {
        final int length = this.basicBlocks.length;
        if (length == 0) {
            return null;
        }
        final Node[] array = new Node[length];
        final boolean[] array2 = new boolean[length];
        final int[] array3 = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = new Node(this.basicBlocks[i]);
            array2[i] = false;
        }
        final Access access = new Access(array) {
            @Override
            BasicBlock[] exits(final Node node) {
                return node.block.getExit();
            }
            
            @Override
            BasicBlock[] entrances(final Node node) {
                return node.block.entrances;
            }
        };
        array[0].makeDepth1stTree(null, array2, 0, array3, access);
        do {
            for (int j = 0; j < length; ++j) {
                array2[j] = false;
            }
        } while (array[0].makeDominatorTree(array2, array3, access));
        setChildren(array);
        return array;
    }
    
    public Node[] postDominatorTree() {
        final int length = this.basicBlocks.length;
        if (length == 0) {
            return null;
        }
        final Node[] array = new Node[length];
        final boolean[] array2 = new boolean[length];
        final int[] array3 = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = new Node(this.basicBlocks[i]);
            array2[i] = false;
        }
        final Access access = new Access(array) {
            @Override
            BasicBlock[] exits(final Node node) {
                return node.block.entrances;
            }
            
            @Override
            BasicBlock[] entrances(final Node node) {
                return node.block.getExit();
            }
        };
        int depth1stTree = 0;
        for (int j = 0; j < length; ++j) {
            if (array[j].block.exits() == 0) {
                depth1stTree = array[j].makeDepth1stTree(null, array2, depth1stTree, array3, access);
            }
        }
        boolean b;
        do {
            for (int k = 0; k < length; ++k) {
                array2[k] = false;
            }
            b = false;
            for (int l = 0; l < length; ++l) {
                if (array[l].block.exits() == 0 && array[l].makeDominatorTree(array2, array3, access)) {
                    b = true;
                }
            }
        } while (b);
        setChildren(array);
        return array;
    }
    
    public static class Block extends BasicBlock
    {
        public Object clientData;
        int index;
        MethodInfo method;
        Block[] entrances;
        
        Block(final int n, final MethodInfo method) {
            super(n);
            this.clientData = null;
            this.method = method;
        }
        
        @Override
        protected void toString2(final StringBuilder sb) {
            super.toString2(sb);
            sb.append(", incoming{");
            for (int i = 0; i < this.entrances.length; ++i) {
                sb.append(this.entrances[i].position).append(", ");
            }
            sb.append('}');
        }
        
        BasicBlock[] getExit() {
            return this.exit;
        }
        
        public int index() {
            return this.index;
        }
        
        public int position() {
            return this.position;
        }
        
        public int length() {
            return this.length;
        }
        
        public int incomings() {
            return this.incoming;
        }
        
        public Block incoming(final int n) {
            return this.entrances[n];
        }
        
        public int exits() {
            return (this.exit == null) ? 0 : this.exit.length;
        }
        
        public Block exit(final int n) {
            return (Block)this.exit[n];
        }
        
        public Catcher[] catchers() {
            final ArrayList list = new ArrayList();
            for (Catch catch1 = this.toCatch; catch1 != null; catch1 = catch1.next) {
                list.add(new Catcher(catch1));
            }
            return (Catcher[])list.toArray(new Catcher[list.size()]);
        }
    }
    
    abstract static class Access
    {
        Node[] all;
        
        Access(final Node[] all) {
            this.all = all;
        }
        
        Node node(final BasicBlock basicBlock) {
            return this.all[((Block)basicBlock).index];
        }
        
        abstract BasicBlock[] exits(final Node p0);
        
        abstract BasicBlock[] entrances(final Node p0);
    }
    
    public static class Node
    {
        private Block block;
        private Node parent;
        private Node[] children;
        
        Node(final Block block) {
            this.block = block;
            this.parent = null;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Node[pos=").append(this.block().position());
            sb.append(", parent=");
            sb.append((this.parent == null) ? "*" : Integer.toString(this.parent.block().position()));
            sb.append(", children{");
            for (int i = 0; i < this.children.length; ++i) {
                sb.append(this.children[i].block().position()).append(", ");
            }
            sb.append("}]");
            return sb.toString();
        }
        
        public Block block() {
            return this.block;
        }
        
        public Node parent() {
            return this.parent;
        }
        
        public int children() {
            return this.children.length;
        }
        
        public Node child(final int n) {
            return this.children[n];
        }
        
        int makeDepth1stTree(final Node parent, final boolean[] array, int depth1stTree, final int[] array2, final Access access) {
            final int index = this.block.index;
            if (array[index]) {
                return depth1stTree;
            }
            array[index] = true;
            this.parent = parent;
            final BasicBlock[] exits = access.exits(this);
            if (exits != null) {
                for (int i = 0; i < exits.length; ++i) {
                    depth1stTree = access.node(exits[i]).makeDepth1stTree(this, array, depth1stTree, array2, access);
                }
            }
            array2[index] = depth1stTree++;
            return depth1stTree;
        }
        
        boolean makeDominatorTree(final boolean[] array, final int[] array2, final Access access) {
            final int index = this.block.index;
            if (array[index]) {
                return false;
            }
            array[index] = true;
            boolean b = false;
            final BasicBlock[] exits = access.exits(this);
            if (exits != null) {
                for (int i = 0; i < exits.length; ++i) {
                    if (access.node(exits[i]).makeDominatorTree(array, array2, access)) {
                        b = true;
                    }
                }
            }
            final BasicBlock[] entrances = access.entrances(this);
            if (entrances != null) {
                for (int j = 0; j < entrances.length; ++j) {
                    if (this.parent != null) {
                        final Node ancestor = getAncestor(this.parent, access.node(entrances[j]), array2);
                        if (ancestor != this.parent) {
                            this.parent = ancestor;
                            b = true;
                        }
                    }
                }
            }
            return b;
        }
        
        private static Node getAncestor(Node parent, Node parent2, final int[] array) {
            while (parent != parent2) {
                if (array[parent.block.index] < array[parent2.block.index]) {
                    parent = parent.parent;
                }
                else {
                    parent2 = parent2.parent;
                }
                if (parent == null || parent2 == null) {
                    return null;
                }
            }
            return parent;
        }
        
        private static void setChildren(final Node[] array) {
            final int length = array.length;
            final int[] array2 = new int[length];
            for (int i = 0; i < length; ++i) {
                array2[i] = 0;
            }
            for (int j = 0; j < length; ++j) {
                final Node parent = array[j].parent;
                if (parent != null) {
                    final int[] array3 = array2;
                    final int index = parent.block.index;
                    ++array3[index];
                }
            }
            for (int k = 0; k < length; ++k) {
                array[k].children = new Node[array2[k]];
            }
            for (int l = 0; l < length; ++l) {
                array2[l] = 0;
            }
            for (final Node node : array) {
                final Node parent2 = node.parent;
                if (parent2 != null) {
                    parent2.children[array2[parent2.block.index]++] = node;
                }
            }
        }
    }
    
    public static class Catcher
    {
        private Block node;
        private int typeIndex;
        
        Catcher(final BasicBlock.Catch catch1) {
            this.node = (Block)catch1.body;
            this.typeIndex = catch1.typeIndex;
        }
        
        public Block block() {
            return this.node;
        }
        
        public String type() {
            if (this.typeIndex == 0) {
                return "java.lang.Throwable";
            }
            return this.node.method.getConstPool().getClassInfo(this.typeIndex);
        }
    }
}
