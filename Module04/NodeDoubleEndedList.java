import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * NodeDoubleEndedList.java implements DoubleEndedList.
 * DoubleEndedList.java. Describes the abstract behavior of a double-ended
 * list. Elements can be inserted and deleted from either end of the list, but
 * not from any other location.
 *
 * @author Tom Fenyak
 * @version June 16 2018
 */
 
public class NodeDoubleEndedList<T> implements DoubleEndedList<T> {
 
   Node front;
   int size;
 
 /**
  * Creates an empty LinkedSet.
  */
   @SuppressWarnings("unchecked")
   public NodeDoubleEndedList() {
      front = null;
      size = 0;
   }
  
  /**
   * @return size of the list
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
    * Creates an iterator for the elements in the list.
    * @return iterator.
    */
   public Iterator<T> iterator() {
      return new DoubleEndedIterator();
   }
   
   /**
    * Adds element to front of list.
    * @throw IllegalArgumentException if element is null.
    */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
    
      Node current = new Node(element);
      current.next = front;
      front = current;
      size++;
   }
   
   /**
    * Adds element to the end of the list.
    * @throw IllegalArgumentException if element is null.
    */
   public void addLast(T element) {
      Node current = front;
      
      if (element == null) {
         throw new IllegalArgumentException();
      }
    
      if (isEmpty()) {
         addFirst(element);
         return;
      }
      
      else {
         for (int i = 0; i < size - 1; i++) {
            current = current.next;
         }
         current.next = new Node(element);
         size++;
      }
   }
   
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   public T removeFirst() {
      Node current = new Node();
      Node result = new Node();
   
      if (size == 0) {
         return null;
      }
   
      if (size == 1) {
         current = front;
         front = null;
         size--;
         return current.element;
      }
   
      result = front;
      current = front.next;
      front = front.next;
      size--;
      return result.element;
   }
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   public T removeLast() {
      Node current = front;
      Node result = new Node();
    
      if (size == 0) {
         return null;
      }
      
      else if (size == 1) {
         result = front;
         front = null;
         size--;
      }
      
      else {
         for (int i = 0; i < size - 2; i++) {
            current = current.next;
         }
      
         result = current.next;
         current.next = null;
         size--;
      }
      return result.element;
   }
   
   /**
    * Nested class that defines the Node class.
    */
   private class Node {
      T element;
      Node next;
    
      public Node() {
         element = null;
         next = null;
      }
    
    /**
     * Instantiates a node.
     */
      public Node(T e) {
         element = e;
         next = null;
      }
   }
    
   /**
    * Nested class that makes an iterator as only one top-level class
    * is permitted for DoubleEndedList.
    */
   private class DoubleEndedIterator implements Iterator<T> {
      public Node current = front;
    
    /**
     * Returns true if there is at least one more element in Iterator
     *
     * @return true if at least one more element
     */
      public boolean hasNext() {
         return current != null;
      }
     
     /**
      * Returns next element in Interation.
      *
      * @return next element
      * @throw NoSuchElementException if there are no more elements.
      */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         T result = current.element;
         current = current.next;
         return result;
      }
   }
      
 
}