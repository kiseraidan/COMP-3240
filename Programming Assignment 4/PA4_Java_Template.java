public class PA4_Java_Template {   
   /* Perform one instance of the Monty Hall Problem
    *
    * should_switch: set to true if running an experiment where the contestant should
    *    switch their guess every time. Set to false if not
    *
    * Returns true if the contestant selected the door with the car behind it. Returns
    *    false otherwise.
    */
   public static boolean run_trial(boolean should_switch) {
      return false;
   }
   
   /* Execute an entire experiment (i.e., execute the specified number of trials)
    * and return the desired results
    * 
    * num_trials: number of trials to execute in this experiment
    * should_switch: set to true if running an experiment where the contestant should
    *    switch their guess every time. Set to false if not
    *
    * Returns the percentage of games won (i.e., number of wins / number of trials)
    */
   public static double run_experiment(int num_trials, boolean should_switch) {
      return -1.0
   }
   
   /* This is a stub that you can use to test the rest of the program. The code in this
    * method will not be executed during grading, so you don't need to worry about user
    * input.
    */
   public static void main(String[] args) {
      int num_trials = 1000;
      boolean should_switch = true;
      
      double prob = PA4_Java.run_experiment(num_trials, should_switch);
   }
}