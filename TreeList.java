/*
Yuval Cohen yuvalc2 213813918
Maya Farber Brodsky mayaf2 325482842
 */

/**
 * Tree list
 * <p>
 * An implementation of a Tree list with  key and info
 */

public class TreeList {
    public AVLTree tree;

    public TreeList() {
        this.tree = new AVLTree();
    }

    /**
     * public Item retrieve(int i)
     * <p>
     * returns the item in the ith position if it exists in the list.
     * otherwise, returns null
     * <p>
     * Complexity: O(logn)
     */
    public Item retrieve(int i) {
        // Check that i is legal
        if (i < 0 || i >= tree.size()) {
            return null;
        }

        // Return item with rank i, offset by 1 because i starts from 0 and rank starts from 1
        AVLTree.IAVLNode node = this.tree.TreeSelect(i + 1);
        return new Item(node.getKey(), node.getValue());
    }

    /**
     * public int insert(int i, int k, String s)
     * <p>
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n otherwise return 0.
     * <p>
     * Complexity: O(logn)
     */
    public int insert(int i, int k, String s) {
        // Check that i is legal
        if (i < 0 || i > tree.size()) {
            return -1;
        }

        AVLTree.IAVLNode newNode = tree.new AVLNode(k, s);

        // Edge case: first item inserted, set it as the new root
        if (i == 0 && tree.getRoot() == null) {
            tree.insert(k, s);
            return 0;
        }

        AVLTree.IAVLNode parentOfInsertedNode = null;

        if (i == tree.size()) {  // Edge case: inserted in last position, set it as the new max
            AVLTree.IAVLNode max = tree.getRoot();
            while (max.getRight() != null) {
                max = max.getRight();
            }
            max.setRight(newNode);
            newNode.setParent(max);
            parentOfInsertedNode = max;
        } else {  // Otherwise, insert as normal
            AVLTree.IAVLNode node = tree.TreeSelect(i + 1);
            if (node.getLeft() == null) {  // No left child, safe to insert
                node.setLeft(newNode);
                newNode.setParent(node);
                parentOfInsertedNode = node;
            } else {  // Otherwise, need to find predecessor, which doesn't have a right child
                AVLTree.IAVLNode x = tree.predecessor(node);
                x.setRight(newNode);
                newNode.setParent(x);
                parentOfInsertedNode = x;
            }
        }

        // Perform necessary rotations to fix the tree, and update heights and sizes
        this.tree.fixAVLCriminals(parentOfInsertedNode);

        return 0;
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     * <p>
     * Complexity: O(logn)
     */
    public int delete(int i) {
        // Check that i is legal
        if (i < 0 || i > tree.size() - 1) {
            return -1;
        }

        // Delete item with rank i, offset by 1 because i starts from 0 and rank starts from 1
        AVLTree.IAVLNode node = this.tree.TreeSelect(i + 1);
        AVLTree.IAVLNode parentOfDeletedNode = this.tree.deleteNodeBST(node);  // Physically delete
        this.tree.fixAVLCriminals(parentOfDeletedNode);  // Perform fixes and rotations

        return 0;
    }

}