/** 
 * Directions:
 * A positive integer is “magic” if, and only if, it can be reduced to 1 by
 * repeatedly dividing it by 2 if it’s even or multiplying it by 3 and then
 * adding 1 if it’s odd. So, for example, 3 is magic because 3 reduces first to
 * 10 (3 · 3 + 1), then to 5 (10/2), then to 16 (5 · 3 + 1), then to 8 (16/2),
 * then to 4 (8/2), then to 2 (4/2), and finally to 1 (2/2). The magic numbers
 * hypothesis states that all positive integers are magic, or, formally:
 * ∀x ∈ Z, MAGIC(x) where MAGIC(x) is the predicate “x is magic”. Write a
 * Python 3 or Java program to automate the search for a counter example to
 * the magic numbers hypothesis for user definable ranges of positive integers.
 * 
 * @author Aidan Kiser
 * @version 24 February 2023
 */
public class Assignment2_Java {
   /* This method should accept the number to test and the maximum number of iterations
    * to try before halting execution. If num is NOT magic (or the maximum number
    * of iterations has been reached), return (-1 * num) (i.e., the negative of num).
    * If num IS magic, return the number of iteration s it took to reduce num to 1.
    * 
    * Remember that a number is magic if it can be reduced to 1 by dividing it by 2 if
    * it is even or multiplying it by 3 and adding 1 if it is odd.
    */ 
   public static int IsMagic(int num, int max_iterations) {
      
      // Iteration counter
      int iterations = 0;
   
      /** 
       * While loop finds if the current num is a magic number, and if it is,
       * it returns the iteration counter.
       */
      while (num != 1 && iterations < max_iterations) {
         
         if (num % 2 == 0) {
            num = num / 2;
         }
         
         else {
            num = num * 3 + 1;
         }
      
         iterations++;
      }
   
      // If the num is not magic, returns -1.
      if (iterations == max_iterations && num != 1) {
         return -1 * num;
      }
   
      else {
         return iterations;
      }
   }
   
   /* This method should be used to check if each number in the range [start, stop]
    * is a magic number. If all numbers in the range are magic, return the number of
    * iterations that it took to reduce the number passed into "stop" to 1. If you 
    * find a number that is NOT magic, this method should return the negative of
    * that number.
    */
   public static int TestRange(int start, int stop, int max_iterations) {
      
      // Sets the max iterations
      int maxIterations = 0;
   
      // Loops through range
      for (int i = start; i <= stop; i++) {
         
         // Sets iterations to a call of IsMagic
         int iterations = IsMagic(i, max_iterations);
      
         // If not magic, return the number of iterations
         if (iterations < 0) { 
            return iterations;
         }
         
         // Compute the max iterations for stop
         else {
            maxIterations = IsMagic(stop, max_iterations);
         }
      }
   
      return maxIterations;
   }
   
   public static void main(String[] args) {
      int start1 = 5;
      int stop1 = 20;
      int max_iterations1 = 500;
      int expected1 = 7;
      
      int result1 = TestRange(start1, stop1, max_iterations1);
      System.out.println(result1);
      System.out.println(expected1);
      System.out.println();

      int start2 = 2;
      int stop2 = 3;
      int max_iterations2 = 500;
      int expected2 = 7;
      
      int result2 = TestRange(start2, stop2, max_iterations2);
      System.out.println(result2);
      System.out.println(expected2);
      System.out.println();

      int start3 = 245;
      int stop3 = 248;
      int max_iterations3 = 500;
      int expected3 = 109;
      
      int result3 = TestRange(start3, stop3, max_iterations3);
      System.out.println(result3);
      System.out.println(expected3);
      System.out.println();

      int start4 = 30;
      int stop4 = 58;
      int max_iterations4 = 500;
      int expected4 = 19;
      
      int result4 = TestRange(start4, stop4, max_iterations4);
      System.out.println(result4);
      System.out.println(expected4);
   }
}