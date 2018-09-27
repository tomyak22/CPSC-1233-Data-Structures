import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * ArrayRandomizedList.java. Class that creates a list with order that is
 * defined as "random" by using arrays. 
 * Order described by a randomized list is "random" in the sense that the
 * element accessed by either the sample or remove method is selected 
 * uniformly at random from the current elements in the list. In addition,
 * an iterator on a randomized list will sequentially access each element 
 * in som uniformly random sequence. Simultaneous iterators on the same 
 * randomized list are independent of each other. that is, they will with
 * high probability have different iteration sequences. Worst-case time
 * complexity must be O(1).
 *
 * @author  Tom Fenyak.
 * @version June 15 2018.
 */
 
public class ArrayRandomizedList<T> implements RandomizedList<T>{
 
   private T[] elements;
   private int size;
   private static final int DEFAULT_CAPACITY = 10;
 
   public ArrayRandomizedList() {
      this(DEFAULT_CAPACITY);
   }
   
   /**
    * Constructor for class.
    */
   @SuppressWarnings("unchecked")
    public ArrayRandomizedList(int capacity) {
      elements = (T[]) new Object[capacity];
      size = 0;
   }
   
   /**
    * @return size of the list.
    */
   public int size() {
      return size;
   }
    
    /**
     * @return true if the list is empty with no elements.
     */
   public boolean isEmpty() {
      return size == 0;
   }
   
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   public void add(T element){
      if (element == null) {
         throw new IllegalArguementException();
      }
    
    // If array is full, double the size
      if (size == elements.length) {
         resize(elements.length * 2);
      }
    
      elements[size] = element;
      size++;
   }
   
         /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
}