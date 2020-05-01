/**
 * Circular list
 *
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
     *
     * returns the item in the ith position if it exists in the list.
     * otherwise, returns null
     */
    public Item retrieve(int i) { 
    	if ((0 <= i) && (i < len))
    		return arr[(start + i) % maxLen];
    	else
    		return null;
    }

    /**
     * public int insert(int i, int k, String s)
     *
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
     */
    public int insert(int i, int k, String s) {
    	if ((0 <= i) && (i <= len) && (i < maxLen)) {
    		if ((len - i) <= i) {
    			for (int j = len - 1; j >= i ; j--) {
    				arr[(start + j + 1) % maxLen] = arr[(start + j) % maxLen];
    			}
    			
    		}
    		else {
    			for (int j = 0; j <= i-1 ; j++) {
    				arr[(start + j - 1) % maxLen] = arr[(start + j) % maxLen];
    			}
    			start = ((start - 1) % maxLen) ;
    		}
    		arr[(start + i) % maxLen] = new Item(k, s);
    		
    		len ++;
    		return 0;
    	}
    	
    	else
    		return -1;
    }

    /**
     * public int delete(int i)
     *
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
    	if ((0 <= i) && (i <= len - 1)) {
    		if ((len - i) <= i) {
    			for (int j = i; j < len - 1 ; j++) {
    				arr[(start + j) % maxLen] = arr[(start + j + 1) % maxLen];
    			}
    			
    			arr[(start + len - 1) % maxLen] = null;
    			
    		}
    		else {
    			for (int j = i-1; j >= 0 ; j--) {
    				arr[(start + j + 1) % maxLen] = arr[(start + j) % maxLen];
    			}
    			start = ((start + 1) % maxLen);
    		}
    		
    		len --;
    		return 0;
    	}
    	else {
    		return -1;
    	}
    }

}
 
 
 
