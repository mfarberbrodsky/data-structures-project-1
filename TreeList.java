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
     */
    public Item retrieve(int i) {
        if (i < 0 || i >= tree.size()) {
            return null;
        }
        AVLTree.IAVLNode node = this.tree.TreeSelect(i + 1);
        return new Item(node.getKey(), node.getValue());
    }

    /**
     * public int insert(int i, int k, String s)
     * <p>
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n otherwise return 0.
     */
    public int insert(int i, int k, String s) {
        if (i < 0 || i > tree.size()) {
            return -1;
        }

        AVLTree.IAVLNode newNode = tree.new AVLNode(k, s);

        if (i == 0 && tree.getRoot() == null) {
            tree.insert(k, s);
            return 0;
        }

        AVLTree.IAVLNode parentOfInsertedNode = null;
        if (i == tree.size()) {
            AVLTree.IAVLNode max = tree.getRoot();
            while (max.getRight() != null) {
                max = max.getRight();
            }
            max.setRight(newNode);
            newNode.setParent(max);
            parentOfInsertedNode = max;
        } else {
            AVLTree.IAVLNode node = tree.TreeSelect(i + 1);
            if (node.getLeft() == null) {
                node.setLeft(newNode);
                newNode.setParent(node);
                parentOfInsertedNode = node;
            } else {
                AVLTree.IAVLNode x = tree.predecessor(node);
                x.setRight(newNode);
                newNode.setParent(x);
                parentOfInsertedNode = x;
            }
        }

        this.tree.updateHeightsAndSizes(parentOfInsertedNode);
        this.tree.fixAVLCriminals(parentOfInsertedNode);

        return 0;
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
        if (i < 0 || i > tree.size() - 1) {
            return -1;
        }
        AVLTree.IAVLNode node = this.tree.TreeSelect(i);
        AVLTree.IAVLNode parentOfDeletedNode = this.tree.deleteNodeBST(node);
        this.tree.fixAVLCriminals(parentOfDeletedNode);
        return 0;
    }

}