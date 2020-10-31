package worldofzuul;
import java.util.Random;

public class RandomEngine {
     private Random randomEngine;

     RandomEngine() {
          //Using current system time as seed for the random number generator
          randomEngine = new Random(System.currentTimeMillis());
     }

     public int getRandom(int min, int max) {
          return randomEngine.nextInt(max-min+1)+min;
     }

     public boolean getOutcome(int probabilityOfSuccess) {

          //successPercentage should be an integer between 0 and 100.

          if (probabilityOfSuccess < 0 || probabilityOfSuccess > 100) {
               System.out.println("Error: invalid input");
               return false;
          }

          if (getRandom(0, 100) < probabilityOfSuccess) {
               return true;
          }
          else {
               return false;
          }

     }
}
