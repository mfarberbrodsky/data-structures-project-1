/**
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info
 */

public class AVLTree {
	private IAVLNode root;

	public AVLTree() {
		this.root = null;
	}

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 */
	public boolean empty() {
		return this.root == null;
	}

	/**
	 * public String search(int k)
	 *
	 * returns the info of an item with key k if it exists in the tree otherwise,
	 * returns null
	 */
	public String search(int k) {
		IAVLNode node = this.root;
		while (node != null) {
			if (node.getKey() == k) {
				return node.getValue();
			} else if (node.getKey() < k) {
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
		
		
		x.setHeight(1 + java.lang.Math.max(x.getLeft().getHeight(), x.getRight().getHeight()));
		y.setHeight(1 + java.lang.Math.max(y.getLeft().getHeight(), y.getRight().getHeight()));
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
		
		
		x.setHeight(1 + java.lang.Math.max(x.getLeft().getHeight(), x.getRight().getHeight()));
		y.setHeight(1 + java.lang.Math.max(y.getLeft().getHeight(), y.getRight().getHeight()));
	}

	/**
	 * public int insert(int k, String i)
	 *
	 * inserts an item with key k and info i to the AVL tree. the tree must remain
	 * valid (keep its invariants). returns the number of rebalancing operations, or
	 * 0 if no rebalancing operations were necessary. returns -1 if an item with key
	 * k already exists in the tree.
	 */
	public int insert(int k, String i) {
		
		IAVLNode newNode = new AVLNode(k, i);
		IAVLNode xNode = this.root;
		IAVLNode yNode = null;
		
		while (xNode != null) {
			yNode = xNode;
			if (xNode.getKey() == k) {
				return -1;
			}
			if (xNode.getKey() < k) {
				xNode = xNode.getRight();
			}
			else {
				xNode = xNode.getLeft();
			}
		}
		
		newNode.setParent(yNode);
		if (yNode.getKey() < k) {
			yNode.setRight(newNode);
		}
		else {                        // k < yNode.geyKey()
			yNode.setLeft(newNode);
		}
		
		int BF = 0;
		int childBF = 0;
		int oldHeight = 0 ;
		int cnt = 0; // number of rotations while the insertion
		
		while (yNode != null) {
			
			BF = yNode.getLeft().getHeight() - yNode.getRight().getHeight();
			oldHeight = yNode.getHeight();
			
			yNode.setHeight(1 + java.lang.Math.max(yNode.getLeft().getHeight(), yNode.getRight().getHeight()));
			
			if ((java.lang.Math.abs(BF) < 2) && (oldHeight == yNode.getHeight())) {
				return cnt;
			}
			
			else if ((java.lang.Math.abs(BF) < 2) && (oldHeight != yNode.getHeight())) {
				yNode = yNode.getParent();
			}
			
			else {                    // abs(BF) = 2
				if (BF == 2) {
					childBF = yNode.getLeft().getLeft().getHeight() - yNode.getLeft().getRight().getHeight();
					if (childBF == 1) {
						rightRotate(yNode);
						cnt ++;
					}
					else {   // childBF == -1
						leftRotate(yNode.getLeft());
						rightRotate(yNode);
						cnt += 2;
					}
				}
				
				else {                // BF = -2
					childBF = yNode.getRight().getLeft().getHeight() - yNode.getRight().getRight().getHeight();
					if (childBF == 1) {
						rightRotate(yNode.getRight());
						leftRotate(yNode);
						cnt += 2;
					}
					else {   // childBF == -1
						leftRotate(yNode);
						cnt ++;
					}
				}
				 
				return cnt;
			}
		}
			
	}

	/**
	 * public int delete(int k)
	 *
	 * deletes an item with key k from the binary tree, if it is there; the tree
	 * must remain valid (keep its invariants). returns the number of rebalancing
	 * operations, or 0 if no rebalancing operations were needed. returns -1 if an
	 * item with key k was not found in the tree.
	 */
	public int delete(int k) {
		return 42; // to be replaced by student code
	}

	/**
	 * public String min()
	 *
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
	 *
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
	 *
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
				stackPointer ++;
				stack[stackPointer] = node;
				node = node.getLeft();
			}

			node = stack[stackPointer];
			stack[stackPointer] = null;
			stackPointer --;
			
			arr[arrPointer] = node.getKey();
			arrPointer ++;
			
			node = node.getRight();
			
		}

		return arr;
	}

	/**
	 * public String[] infoToArray()
	 *
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
				stackPointer ++;
				stack[stackPointer] = node;
				node = node.getLeft();
			}

			node = stack[stackPointer];
			stack[stackPointer] = null;
			stackPointer --;
			
			arr[arrPointer] = node.getValue();
			arrPointer ++;
			
			node = node.getRight();
			
		}

		return arr;
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none postcondition: none
	 */
	public int size() {
		return 42; // to be replaced by student code
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty
	 *
	 * precondition: none postcondition: none
	 */
	public IAVLNode getRoot() {
		return this.root;
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
	 *
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

		public AVLNode(int key, String value) {
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

	}

}
