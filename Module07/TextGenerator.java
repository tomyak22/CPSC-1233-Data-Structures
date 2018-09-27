import java.io.File;
import java.io.IOException;

/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Tom Fenyak (tjf0027@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-07-06
 *
 */
public class TextGenerator {

   /** Drives execution. */
   public static void main(String[] args) {
   
      if (args.length < 3) {
         System.out.println("Usage: java TextGenerator k length input");
         return;
      }
   
      // No error checking! You may want some, but it's not necessary.
      int K = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      if ((K < 0) || (M < 0)) {
         System.out.println("Error: Both K and M must be non-negative.");
         return;
      }
   
      File text;
      try {
         text = new File(args[2]);
         if (!text.canRead()) {
            throw new Exception();
         }
      }
      catch (Exception e) {
         System.out.println("Error: Could not open " + args[2] + ".");
         return;
      }
   
   
      // instantiate a MarkovModel with the supplied parameters and
      // generate sample output text ...
      MarkovModel outputModel = new MarkovModel(K, text);
      String kgram = outputModel.getRandomKgram();
      
      String outputText = "" + kgram;
      
      while (outputText.length() < M) {
         char c = outputModel.getNextChar(kgram);
         outputText += c;
         kgram = kgram.substring(1, kgram.length()) + c;
      }
      
      if (outputText.length() > M) {
         outputText = outputText.substring(0, M);
      }
      
      System.out.println(outputText);
   
   }
}