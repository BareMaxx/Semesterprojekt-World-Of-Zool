package gameplay;
import java.util.Random;

public class RandomEngine {
     private Random randomEngine;

     public RandomEngine() {
          randomEngine = new Random(System.currentTimeMillis());
     }

     public int getRandom(int min, int max) {
          return randomEngine.nextInt(max - min + 1) + min;
     }

     public boolean getOutcome(int probabilityOfSuccess, int outOff) {
          if (probabilityOfSuccess < 0 || probabilityOfSuccess > outOff) {
               System.out.println("Error: invalid probability input");
               return false;
          }

          return (getRandom(0, outOff) < probabilityOfSuccess);
     }
}