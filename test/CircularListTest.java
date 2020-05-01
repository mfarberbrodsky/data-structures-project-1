import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CircularListTest {

    @Test
    void retrieve() {
        CircularList lst = new CircularList(7);
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
        CircularList lst = new CircularList(3);

        int x;

        x = lst.insert(0, 5, "Hello");
        assertEquals(0, x);
        x = lst.insert(1, 7, "Goodbye");
        assertEquals(0, x);
        x = lst.insert(1, 9, "Yes");
        assertEquals(0, x);
        x = lst.insert(3, 11, "No");
        assertEquals(-1, x);

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
        CircularList lst = new CircularList(10);

        int x;
        Item y;

        lst.insert(0, 5, "Hello");
        lst.insert(1, 7, "Goodbye");
        lst.insert(1, 9, "Yes");
        lst.insert(3, 11, "No");
        lst.insert(4, 1, "Bla");

        y = lst.retrieve(4);
        assertItemEquals(new Item(1, "Bla"), y);

        x = lst.delete(5);
        assertEquals(x, -1);

        x = lst.delete(4);
        assertEquals(x, 0);

        y = lst.retrieve(3);
        assertItemEquals(new Item(11, "No"), y);

        y = lst.retrieve(4);
        assertNull(y);

        x = lst.delete(1);
        assertEquals(x, 0);

        y = lst.retrieve(1);
        assertItemEquals(new Item(7, "Goodbye"), y);

        y = lst.retrieve(2);
        assertItemEquals(new Item(11, "No"), y);
    }

    void assertItemEquals(Item expected, Item actual) {
        assertEquals(expected.getKey(), actual.getKey());
        assertEquals(expected.getInfo(), actual.getInfo());
    }
}