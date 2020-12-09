package gameplay;
import java.util.Random;

// Class that spits out random numbers etc.
public class RandomEngine {
     private Random randomEngine;

     // Seed the random function with the computers current time in milliseconds
     public RandomEngine() {
          randomEngine = new Random(System.currentTimeMillis());
     }

     // Return a random value between inclusive min and inclusive max
     public int getRandom(int min, int max) {
          return randomEngine.nextInt(max - min + 1) + min;
     }

     // Return whether or not a random value is within the given range
     public boolean getOutcome(int probabilityOfSuccess, int outOff) {
          if (probabilityOfSuccess < 0 || probabilityOfSuccess > outOff) {
               System.out.println("Error: invalid probability input");
               return false;
          }

          return (getRandom(0, outOff) < probabilityOfSuccess);
     }
}