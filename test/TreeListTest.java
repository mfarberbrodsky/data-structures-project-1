import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeListTest {

    @Test
    void retrieve() {
        TreeList lst = new TreeList();
        lst.insert(0, 5, "Hello");
        lst.insert(1, 7, "Goodbye");

        Item x;

        x = lst.retrieve(0);
        assertItemEquals(new Item(5, "Hello"), x);

        x = lst.retrieve(1);
        assertItemEquals(new Item(7, "Goodbye"), x);

        x = lst.retrieve(5);
        assertNull(x);
    }

    @Test
    void insert() {
        TreeList lst = new TreeList();

        int x;

        x = lst.insert(0, 5, "Hello");
        assertEquals(0, x);
        x = lst.insert(1, 7, "Goodbye");
        assertEquals(0, x);
        x = lst.insert(1, 9, "Yes");
        assertEquals(0, x);
        x = lst.insert(3, 11, "No");
        assertEquals(0, x);

        Item y;

        y = lst.retrieve(0);
        assertItemEquals(new Item(5, "Hello"), y);
        y = lst.retrieve(1);
        assertItemEquals(new Item(9, "Yes"), y);
        y = lst.retrieve(2);

        assertItemEquals(new Item(7, "Goodbye"), y);
    }

    @Test
    void delete() {
    }

    void assertItemEquals(Item expected, Item actual) {
        assertEquals(expected.getKey(), actual.getKey());
        assertEquals(expected.getInfo(), actual.getInfo());
    }
}