public class Assignment2_Java_Template {
   /* This method should accept the number to test and the maximum number of iterations
    * to try before halting execution. If num is NOT magic (or the maximum number
    * of iterations has been reached), return (-1 * num) (i.e., the negative of num).
    * If num IS magic, return the number of iterations it took to reduce num to 1.
    * 
    * Remember that a number is magic if it can be reduced to 1 by dividing it by 2 if
    * it is even or multiplying it by 3 and adding 1 if it is odd.
    */ 
   public static int IsMagic(int num, int max_iterations) {
      
      boolean isMagic = false;
      int interations = 0;

      while (num != 1 && iterations < max_iterations) {
         if (num % 2 == 0) {
            num = num / 2;
         } else {
            num = num * 3 + 1;
         }
         iterations++;
      }

      if (num )

   }
   
   /* This method should be used to check if each number in the range [start, stop]
    * is a magic number. If all numbers in the range are magic, return the number of
    * iterations that it took to reduce the number passed into "stop" to 1. If you 
    * find a number that is NOT magic, this method should return the negative of
    * that number.
    */
   public static int TestRange(int start, int stop, int max_iterations) {
      return 0;
   }
   
   public static void main(String[] args) {
      int start = 5;
      int stop = 20;
      int max_iterations = 500;
      
      int result = TestRange(start, stop, max_iterations);
      System.out.println(result);
   }
}