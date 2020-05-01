/**
 * Circular list
 * <p>
 * An implementation of a circular list with  key and info
 */

public class CircularList {

    private int maxLen;
    private Item[] arr;
    private int len = 0;
    private int start = 0;

    public CircularList(int maxLen) {
        this.maxLen = maxLen;
        this.arr = new Item[maxLen];
    }

    /**
     * public Item retrieve(int i)
     * <p>
     * returns the item in the ith position if it exists in the list.
     * otherwise, returns null
     */
    public Item retrieve(int i) {
        if ((0 <= i) && (i < len))
            return arr[getPos(i)];
        else
            return null;
    }

    /**
     * public int insert(int i, int k, String s)
     * <p>
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
     */
    public int insert(int i, int k, String s) {
        if ((0 <= i) && (i <= len) && (i < maxLen)) {
            if ((len - i) <= i) {
                for (int j = len - 1; j >= i; j--) {
                    arr[getPos(j + 1)] = arr[getPos(j)];
                }

            } else {
                for (int j = 0; j <= i - 1; j++) {
                    arr[getPos(j - 1)] = arr[getPos(j)];
                }
                start = ((start + maxLen - 1) % maxLen);
            }
            arr[getPos(i)] = new Item(k, s);

            len++;
            return 0;
        } else
            return -1;
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
        if ((0 <= i) && (i <= len - 1)) {
            if ((len - i) <= i) {
                for (int j = i; j < len - 1; j++) {
                    arr[getPos(j)] = arr[getPos(j + 1)];
                }
                arr[getPos(len - 1)] = null;
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    arr[getPos(j + 1)] = arr[getPos(j)];
                }
                start = ((start + maxLen + 1) % maxLen);
            }
            len--;
            return 0;
        } else {
            return -1;
        }
    }

    private int getPos(int i) {
        int modulu = (start + i) % maxLen;
        if (modulu >= 0) {
            return modulu;
        } else {
            return modulu + maxLen;
        }
    }

}
 
 
 
