import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Tom Fenyak (tjf0027@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-07-06
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   private int kValue;
   private String workingText;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      kValue = K;
      workingText = sourceText;
   
      while (workingText.length() > kValue) {
         String kgram = getFirstKgram();
         String followingChars = "";
         for (int i = 0; i < workingText.length() - kValue; i++) {
            String instance = workingText.substring(i, i + kValue);
            if (instance.equals(kgram)) {
               char addChar = workingText.charAt(i + kValue);
               followingChars += addChar;
            }
         }
      
         if (!model.containsKey(kgram)) {
            model.put(kgram, followingChars);
         }
         workingText = workingText.substring(1, workingText.length());
      }
      workingText = sourceText;
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return workingText.substring(0, kValue);
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      int randomPoint = new Random().nextInt(workingText.length());
   
      while ((randomPoint + kValue) > workingText.length()) {
         randomPoint = new Random().nextInt(workingText.length());
      }
      return workingText.substring(randomPoint, randomPoint + kValue);
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      if (kgram.length() == 0 || !model.containsKey(kgram)) {
         return '\u0000';
      }
      
      String allChars = model.get(kgram);
      int charIndex = new Random().nextInt(allChars.length());
      char nextChar = allChars.charAt(charIndex);
      return nextChar;
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
