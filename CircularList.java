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
     * 
     * O(1)
     */
    public Item retrieve(int i) {
        if ((0 <= i) && (i < len)) {
            return arr[getPos(i)];
        }
        else {  // i is illegal
            return null;
        }
    }

    /**
     * public int insert(int i, int k, String s)
     * <p>
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
     * 
     * O(min{ i+1 , n-i+1 }) 
     */
    public int insert(int i, int k, String s) {
        if ((0 <= i) && (i <= len) && (i < maxLen)) {  // i is legal
            if ((len - i) <= i) {
                for (int j = len - 1; j >= i; j--) {        // push the last (len - i) elements forwards
                    arr[getPos(j + 1)] = arr[getPos(j)];
                }

            } else {
                for (int j = 0; j <= i - 1; j++) {         // push the first i elements backwards
                    arr[getPos(j - 1)] = arr[getPos(j)];
                }
                start = ((start + maxLen - 1) % maxLen);  // update start point
            }
            arr[getPos(i)] = new Item(k, s);  // insert new item

            len++;
            return 0;
        } 
        
        else {    // i is illegal
            return -1;
        }
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     * 
     * O(min{ i+1 , n-i+1 }) 
     */
    public int delete(int i) {
        if ((0 <= i) && (i <= len - 1)) {    // i is legal
            if ((len - i) <= i) {
                for (int j = i; j < len - 1; j++) {   // push the last (len - i) elements backwards, override the i'th element
                    arr[getPos(j)] = arr[getPos(j + 1)];
                }
                arr[getPos(len - 1)] = null;
            } else {
                for (int j = i - 1; j >= 0; j--) {    // push the first i elements forwards, override the i'th element
                    arr[getPos(j + 1)] = arr[getPos(j)];
                }
                start = ((start + maxLen + 1) % maxLen); // update start point
            }
            len--;
            return 0;
        }
        
        else {      // i is illegal
            return -1;
        }
    }

    
    /**
     * private int getPos(int i)
     * <p>
     * insures position using modulu is positive (or 0)
     * returns position of the i'th item in circular list
     * 
     * O(1)
     */
    private int getPos(int i) { 
        int modulu = (start + i) % maxLen;
        if (modulu >= 0) {
            return modulu;
        } else {
            return modulu + maxLen;
        }
    }

}
 
 
 
