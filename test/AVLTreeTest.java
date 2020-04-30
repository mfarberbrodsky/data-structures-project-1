import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void empty() {
        AVLTree tree = new AVLTree();
        assertTrue(tree.empty());
        tree.insert(5, "hello");
        assertFalse(tree.empty());
        tree.delete(5);
        assertTrue(tree.empty());
    }

    @Test
    void search() {
        AVLTree tree = new AVLTree();
        assertNull(tree.search(5));
        tree.insert(3, "hello");
        assertNull(tree.search(5));
        assertEquals("hello", tree.search(3));
    }

    @Test
    void insert() {

    }

    @Test
    void delete() {
        AVLTree tree = new AVLTree();
        tree.insert(3, "hello");
        tree.insert(2, "bye");
        tree.delete(3);
        assertEquals(2, tree.getRoot().getKey());
    }

    @Test
    void min() {
    }

    @Test
    void max() {
    }

    @Test
    void keysToArray() {
    }

    @Test
    void infoToArray() {
    }

    @Test
    void size() {
        AVLTree tree = new AVLTree();
        assertEquals(0, tree.size());
        tree.insert(5, "hello");
        assertEquals(1, tree.size());
        tree.insert(2, "bye");
        assertEquals(2, tree.size());
        tree.delete(2);
        assertNull(tree.getRoot().getLeft());
        assertEquals(1, tree.size());

        tree = new AVLTree();
        tree.insert(17, "a");
        tree.insert(3, "b");
        tree.insert(18, "yes");
        tree.insert(5, "no");
        tree.insert(7, "c");

        assertEquals(3, ((AVLTree.AVLNode) tree.getRoot().getLeft()).getSize());
        assertEquals(5, tree.size());
    }

    @Test
    void getRoot() {
    }
}