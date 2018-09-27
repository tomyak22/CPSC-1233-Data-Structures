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
    *
    * @throw IllegalArgumentException.
    */
   public void add(T element){
      if (element == null) {
         throw new IllegalArgumentException();
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
    *
    * @return remove element that is removed from the array.
    */
   public T remove() {
    
      if (this.isEmpty()) {
         return null;
      }
    
      Random r = new Random();
    // Randomly selects element to remove and stores in rValue.
      int rValue = r.nextInt(size);
    // Store removed element in remove
      T remove = elements[rValue];
      elements[rValue] = null;
    
    // Move last element to replace "remove" if "remove" is not last element
      if (rValue != (size - 1)) {
         elements[rValue] = elements[size - 1];
         elements[size - 1] = null;
      }
      size--;
    // If array is less than 25% full, resize to 1/2 of current size.
      if (size > 0 && size < elements.length / 4) {
         resize(elements.length / 2);
      }
      return remove;
   }
   
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    *
    * @return sample a random element selected from array.
    */
   public T sample() {
      if (this.isEmpty()) {
         return null;
      }
   
      Random r = new Random();
      int rValue = r.nextInt(size);
      return elements[rValue];
   }
   
   /**
    * Creates an interator for the elements in the list
    */
   public Iterator<T> iterator() {
      return new ArrayIterator(elements, size);
   }
   
   /**
    * Resizes the array
    */
   private void resize(int capacity) {
      T[] a = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i++) {
         a[i] = elements[i];
      }
      elements = a;
   }
   
   /**
    * Nested class that makes an iterator as only one top-level class
    * is permitted for RandomizedList.
    */
   public class ArrayIterator<T> implements Iterator<T> {
      private T[] items;
      private int length;
    
    /**
     * Constructor for ArrayIterator.
     */
      public ArrayIterator(T[] elements, int sizeIn) {
         items = elements;
         length = sizeIn;
      }
      
      /**
       * Returns as long as there is a next element.
       */
      public boolean hasNext() {
         return (length > 0);
      }
       
       /**
        * Returns next item in list.
        *
        * @throw NoSuchElementException when there are no more elements
        */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
        
         Random r = new Random();
         int rValue = r.nextInt(length);
         T next = items[rValue];
        // Switch rValue with last item if it is not last item
         if (rValue != (length - 1)) {
            items[rValue] = items[length - 1];
            items[length - 1] = next;
         }
         length--;
         return next;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
   }
}