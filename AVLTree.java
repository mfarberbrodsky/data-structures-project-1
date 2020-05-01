/**
 * AVLTree
 * <p>
 * An implementation of a AVL Tree with distinct integer keys and info
 */

public class AVLTree {
    private IAVLNode root;

    public AVLTree() {
        this.root = null;
    }

    public IAVLNode TreeSelect(int k) {
        return TreeSelect(this.root, k);
    }

    public IAVLNode TreeSelect(IAVLNode subtreeNode, int k) {
        int r = getSize(subtreeNode.getLeft()) + 1;
        if (k == r) {
            return subtreeNode;
        } else if (k < r) {
            return TreeSelect(subtreeNode.getLeft(), k);
        } else {
            return TreeSelect(subtreeNode.getRight(), k - r);
        }
    }

    private IAVLNode successor(IAVLNode node) {
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        } else {
            while (node.getParent().getLeft() != node) {
                node = node.getParent();
            }
            return node.getParent();
        }
    }

    public IAVLNode predecessor(IAVLNode node) {
        if (node.getLeft() != null) {
            node = node.getLeft();
            while (node.getRight() != null) {
                node = node.getRight();
            }
            return node;
        } else {
            while (node.getParent().getRight() != node) {
                node = node.getParent();
            }
            return node.getParent();
        }
    }

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return this.root == null;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree otherwise,
     * returns null
     */
    public String search(int k) {
        IAVLNode node = this.root;
        while (node != null) {
            if (k == node.getKey()) {
                return node.getValue();
            } else if (k < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    public void leftRotate(IAVLNode x) {
        IAVLNode y = x.getRight();

        x.setRight(y.getLeft());

        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        // Update child of x's parent
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);

        // Maintain height and size
        ((AVLNode) y).setSize(getSize(x));
        ((AVLNode) x).setSize(getSize(x.getLeft()) + getSize(x.getRight()) + 1);

        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));
    }

    public void rightRotate(IAVLNode x) {
        IAVLNode y = x.getLeft();

        x.setLeft(y.getRight());

        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }

        y.setParent(x.getParent());

        // Update child of x's parent
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setRight(x);
        x.setParent(y);

        // Maintain height and size
        ((AVLNode) y).setSize(getSize(x));
        ((AVLNode) x).setSize(getSize(x.getLeft()) + getSize(x.getRight()) + 1);

        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the AVL tree. the tree must remain
     * valid (keep its invariants). returns the number of rebalancing operations, or
     * 0 if no rebalancing operations were necessary. returns -1 if an item with key
     * k already exists in the tree.
     */
    public int insert(int k, String i) {
        IAVLNode newNode = new AVLNode(k, i);

        if (this.root == null) {
            this.root = newNode;
            return 0;
        }

        IAVLNode xNode = this.root;
        IAVLNode yNode = null;

        while (xNode != null) {
            yNode = xNode;
            if (xNode.getKey() == k) {
                return -1;
            }
            if (xNode.getKey() < k) {
                xNode = xNode.getRight();
            } else {
                xNode = xNode.getLeft();
            }
        }

        newNode.setParent(yNode);
        if (yNode.getKey() < k) {
            yNode.setRight(newNode);
        } else {                        // k < yNode.geyKey()
            yNode.setLeft(newNode);
        }

        int BF = 0;
        int childBF = 0;
        int oldHeight = 0;
        int cnt = 0; // number of rotations while the insertion

        while (yNode != null) {

            BF = getHeight(yNode.getLeft()) - getHeight(yNode.getRight());
            oldHeight = getHeight(yNode);

            yNode.setHeight(1 + Math.max(getHeight(yNode.getLeft()), getHeight(yNode.getRight())));
            ((AVLNode) yNode).setSize(1 + getSize(yNode.getLeft()) + getSize(yNode.getRight()));

            if ((Math.abs(BF) < 2) && (oldHeight == getHeight(yNode))) {
                break;
            } else if ((Math.abs(BF) < 2) && (oldHeight != getHeight(yNode))) {
                yNode = yNode.getParent();
            } else {                    // abs(BF) = 2
                if (BF == 2) {
                    childBF = getHeight(yNode.getLeft().getLeft()) - getHeight(yNode.getLeft().getRight());
                    if (childBF == 1) {
                        rightRotate(yNode);
                        cnt++;
                    } else {   // childBF == -1
                        leftRotate(yNode.getLeft());
                        rightRotate(yNode);
                        cnt += 2;
                    }
                } else {                // BF = -2
                    childBF = getHeight(yNode.getRight().getLeft()) - getHeight(yNode.getRight().getRight());
                    if (childBF == 1) {
                        rightRotate(yNode.getRight());
                        leftRotate(yNode);
                        cnt += 2;
                    } else {   // childBF == -1
                        leftRotate(yNode);
                        cnt++;
                    }
                }

                break;
            }
        }

        while (yNode != null) {
            ((AVLNode) yNode).setSize(1 + getSize(yNode.getLeft()) + getSize(yNode.getRight()));
            yNode = yNode.getParent();
        }

        return cnt;
    }

    public IAVLNode deleteNodeBST(IAVLNode node) {
        IAVLNode parentOfDeletedNode = null;

        // If node is a leaf, simply delete it
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() != null) {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(null);
                } else {
                    node.getParent().setRight(null);
                }
            } else {
                this.root = null;
            }
            parentOfDeletedNode = node.getParent();
        }
        // if node has only a right child, bypass it
        else if (node.getLeft() == null) {
            if (node.getParent() != null) {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(node.getRight());
                } else {
                    node.getParent().setRight(node.getRight());
                }
            } else {
                this.root = node.getRight();
            }
            node.getRight().setParent(node.getParent());

            parentOfDeletedNode = node.getParent();
        }
        // if node has only a left child, bypass it
        else if (node.getRight() == null) {
            if (node.getParent() != null) {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(node.getLeft());
                } else {
                    node.getParent().setRight(node.getLeft());
                }
            } else {
                this.root = node.getLeft();
            }
            node.getLeft().setParent(node.getParent());

            parentOfDeletedNode = node.getParent();
        }
        // if node has two children
        else {
            IAVLNode nodeSuccessor = this.successor(node);
            // remove successor from the tree
            parentOfDeletedNode = this.deleteNodeBST(nodeSuccessor);

            // replace node with successor
            ((AVLNode) node).setItem(nodeSuccessor.getKey(), nodeSuccessor.getValue());
        }

        return parentOfDeletedNode;
    }

    // Perform rotations from node upwards, return number of rotations performed
    // Also updates heights and sizes of all ancestors
    public int fixAVLCriminals(IAVLNode node) {
        int rotations = 0;
        while (node != null) {
            node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
            ((AVLNode) node).setSize(1 + getSize(node.getLeft()) + getSize(node.getRight()));

            int balanceFactor = getHeight(node.getLeft()) - getHeight(node.getRight());
            if (balanceFactor == -2) {
                int rightBalanceFactor = getHeight(node.getRight().getLeft()) - getHeight(node.getRight().getRight());
                if (rightBalanceFactor == -1 || rightBalanceFactor == 0) {
                    this.leftRotate(node);
                    rotations += 1;
                } else if (rightBalanceFactor == 1) {
                    this.rightRotate(node.getRight());
                    this.leftRotate(node);
                    rotations += 2;
                }
            } else if (balanceFactor == 2) {
                int leftBalanceFactor = getHeight(node.getLeft().getLeft()) - getHeight(node.getLeft().getRight());
                if (leftBalanceFactor == -1) {
                    this.leftRotate(node.getLeft());
                    this.rightRotate(node);
                    rotations += 2;
                } else if (leftBalanceFactor == 0 || leftBalanceFactor == 1) {
                    this.rightRotate(node);
                    rotations += 1;
                }
            }
            node = node.getParent();
        }
        return rotations;
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there; the tree
     * must remain valid (keep its invariants). returns the number of rebalancing
     * operations, or 0 if no rebalancing operations were needed. returns -1 if an
     * item with key k was not found in the tree.
     */
    public int delete(int k) {
        IAVLNode node = this.root;

        while (node != null) {
            if (k == node.getKey()) {
                IAVLNode parentOfDeletedNode = deleteNodeBST(node);
                return fixAVLCriminals(parentOfDeletedNode);
            } else if (k < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return -1;
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree, or null if
     * the tree is empty
     */
    public String min() {
        if (this.empty()) {
            return null;
        }

        IAVLNode node = this.root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree, or null if the
     * tree is empty
     */
    public String max() {
        if (this.empty()) {
            return null;
        }

        IAVLNode node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree, or an empty array
     * if the tree is empty.
     */
    public int[] keysToArray() {

        if (this.empty()) {
            return new int[0];
        }

        IAVLNode[] stack = new IAVLNode[this.size()];
        int[] arr = new int[this.size()];

        int stackPointer = 0;
        int arrPointer = 0;

        IAVLNode node = this.root;

        stack[stackPointer] = node;
        node = node.getLeft();


        while ((stackPointer >= 0) || (node != null)) {

            while (node != null) {
                stackPointer++;
                stack[stackPointer] = node;
                node = node.getLeft();
            }

            node = stack[stackPointer];
            stack[stackPointer] = null;
            stackPointer--;

            arr[arrPointer] = node.getKey();
            arrPointer++;

            node = node.getRight();

        }

        return arr;
    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree, sorted by their
     * respective keys, or an empty array if the tree is empty.
     */
    public String[] infoToArray() {

        if (this.empty()) {
            return new String[0];
        }

        IAVLNode[] stack = new IAVLNode[this.size()];
        String[] arr = new String[this.size()];

        int stackPointer = 0;
        int arrPointer = 0;

        IAVLNode node = this.root;

        stack[stackPointer] = node;
        node = node.getLeft();


        while ((stackPointer >= 0) || (node != null)) {

            while (node != null) {
                stackPointer++;
                stack[stackPointer] = node;
                node = node.getLeft();
            }

            node = stack[stackPointer];
            stack[stackPointer] = null;
            stackPointer--;

            arr[arrPointer] = node.getValue();
            arrPointer++;

            node = node.getRight();

        }

        return arr;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     * <p>
     * precondition: none postcondition: none
     */
    public int size() {
        return getSize(this.root);
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     * <p>
     * precondition: none postcondition: none
     */
    public IAVLNode getRoot() {
        return this.root;
    }


    public int getHeight(IAVLNode node) {
        if (node == null) {
            return -1;
        }

        return node.getHeight();
    }

    public int getSize(IAVLNode node) {
        if (node == null) {
            return 0;
        }

        return ((AVLNode) node).getSize();
    }

    /**
     * public interface IAVLNode ! Do not delete or modify this - otherwise all
     * tests will fail !
     */
    public interface IAVLNode {
        public int getKey(); // returns node's key

        public String getValue(); // returns node's value [info]

        public void setLeft(IAVLNode node); // sets left child

        public IAVLNode getLeft(); // returns left child (if there is no left child return null)

        public void setRight(IAVLNode node); // sets right child

        public IAVLNode getRight(); // returns right child (if there is no right child return null)

        public void setParent(IAVLNode node); // sets parent

        public IAVLNode getParent(); // returns the parent (if there is no parent return null)

        public void setHeight(int height); // sets the height of the node

        public int getHeight(); // Returns the height of the node
    }

    /**
     * public class AVLNode
     * <p>
     * If you wish to implement classes other than AVLTree (for example AVLNode), do
     * it in this file, not in another file. This class can and must be modified.
     * (It must implement IAVLNode)
     */
    public class AVLNode implements IAVLNode {
        private Item item;
        private IAVLNode left;
        private IAVLNode right;
        private IAVLNode parent;
        private int height;
        private int size;


        public AVLNode(int key, String value) {
            this.item = new Item(key, value);
            this.height = 0;
            this.size = 1;
        }

        public void setItem(int key, String value) {
            this.item = new Item(key, value);
        }

        public int getKey() {
            return this.item.getKey();
        }

        public String getValue() {
            return this.item.getInfo();
        }

        public void setLeft(IAVLNode node) {
            this.left = node;
        }

        public IAVLNode getLeft() {
            return this.left;
        }

        public void setRight(IAVLNode node) {
            this.right = node;
        }

        public IAVLNode getRight() {
            return this.right;
        }

        public void setParent(IAVLNode node) {
            this.parent = node;
        }

        public IAVLNode getParent() {
            return this.parent;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }

    }

}
