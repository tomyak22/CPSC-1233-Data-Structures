import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Tom Fenyak (tjf0027@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  May 26 2018
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param a array to be searched
    * @return minimum value in array
    * @throws IllegalArgumentException if a is null
    */
   public static int min(int[] a) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int minValue = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] < minValue) {
            minValue = a[i];
         }
      }
      return minValue;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param a array to be searched
    * @return maximum value in array
    * @throws IllegalArgumentException
    */
   public static int max(int[] a) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
     
      int maxValue = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > maxValue) {
            maxValue = a[i];
         }
      }
      return maxValue;   
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a array to be searched
    * @param k k-selection value
    * @return kth minimum value in array
    * @throws IllegalArgumentException as specified above
    */
   public static int kmin(int[] a, int k) {
   
      if (a == null || a.length == 0 || k > a.length || k <= 0) {
         throw new IllegalArgumentException();
      }
   
      int distinctVal = 1;
      int[] b = Arrays.copyOf(a, a.length);
      int cLength = b.length;
   
      if (a.length == 1) {
         return a[0];
      }
   
      Arrays.sort(b);
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            distinctVal++;
         }
         else {
            cLength--;
         }
      }
   
      if (k > distinctVal) {
         throw new IllegalArgumentException();
      }
   
      int[] c = new int[cLength];
      Arrays.sort(b);
      c[0] = b[0];
      int cIndex = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[cIndex++] = b[i];
         }
      }
      int kmin = c[k - 1];
      return kmin;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a array to be searched through
    * @param k k-selection value
    * @return kth maximum value in array
    * @throws IllegalArgumentException as specified above
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k > a.length || k <= 0) {
         throw new IllegalArgumentException();
      }
     
      int distinctVal = 1;
      int[] b = Arrays.copyOf(a, a.length);
      int cLength = b.length;
     
      if (a.length == 1) {
         return a[0];
      }
     
      Arrays.sort(b);
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            distinctVal++;
         }
         else {
            cLength--;
         }
      }
     
      if (k > distinctVal) {
         throw new IllegalArgumentException();
      }
     
      int[] c = new int[cLength];
      Arrays.sort(b);
      c[0] = b[0];
      int cIndex = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[cIndex++] = b[i];
         }
      }
      int kmax = c[c.length - k];
      return kmax;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    *
    * @param a array to be searched through
    * @param low lower bound value of the range
    * @param high upper bound value of the range
    * @return array of elements that fit in the range
    * @throws IllegalArgumentException as specified above
    */
   public static int[] range(int[] a, int low, int high) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int arrayLength = 0;
      int[] b = {};
      int i = 0;
      for (int j = 0; j < a.length; j++) {
         if (a[j] >= low && a[j] <= high) {
            arrayLength++;
            b = new int[arrayLength];
         }
      }
      
      for (int j = 0; j < a.length; j++) {
         if (a[j] >= low && a[j] <= high) {
            b[i++] = a[j];
         }
      }
      
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *
    * @param a array to be searched through
    * @param key reference value
    * @return next greater value in array
    * @throws IllegalArgument as specified above
    */
   public static int ceiling(int[] a, int key) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int i = Integer.MAX_VALUE;
      for (int j = 0; j < a.length; j++) {
         if (a[j] < i && a[j] >= key) {
            i = a[j];
         }
      }
      
      if (i == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      }
      
      return i;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *
    * @param a array to be searched through
    * @param key reference value
    * @return next smaller value in array
    * @throws IllegalArgumentException as specified above
    */
   public static int floor(int[] a, int key) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int i = Integer.MIN_VALUE;
      for (int j = 0; j < a.length; j++) {
         if (a[j] > i && a[j] <= key) {
            i = a[j];
         }
      }
      
      if (i == Integer.MIN_VALUE) {
         throw new IllegalArgumentException();
      }
      
      return i;
   }

}
