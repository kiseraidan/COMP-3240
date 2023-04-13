import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.util.*;
/**
* Class PA4_Java solves Monty Hall Problem with Monte Carlo Simulations.
* 
* @author Aidan Kiser - COMP3240
* @author Daniel Tauritz
* @version 7 April 2023
*/
public class PA4_Java {   
   private static ThreadLocalRandom random = ThreadLocalRandom.current();

   /* Perform one instance of the Monty Hall Problem
    *
    * should_switch: set to true if running an experiment where the contestant should
    *    switch their guess every time. Set to false if not
    *
    * Returns true if the contestant selected the door with the car behind it. Returns
    *    false otherwise.
    */
   public static boolean run_trial(boolean should_switch) {
      int[] doors = {0, 0, 0}; // 0 represents a goat, 1 represents a car
      int carPosition = random.nextInt(0, 3);
      int chosenDoor = random.nextInt(0, 3);
      int openedDoor = PA4_Java.openDoor(chosenDoor, carPosition);
   
      // If the contestant switches, they will choose the other unopened door
      if (should_switch) {
         chosenDoor = 3 - (chosenDoor + openedDoor);
      }

      return carPosition == chosenDoor;
   }

   /* Open a door that is not the chosen door and not the door with the car behind it
    *
    * chosenDoor: the door that the contestant chose
    * carPosition: the door that the car is behind
    *
    * Returns the door that the host opens
    */
    public static int openDoor(int chosenDoor, int carPosition) {
      int openedDoor = -1;
      if (chosenDoor == carPosition) {
         do {
            openedDoor = random.nextInt(0, 3);
         } while (openedDoor == carPosition);
      } else {
         openedDoor = 3 - (carPosition + chosenDoor);
      }
      return openedDoor;
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
      int wins = 0;
      for (int i = 0; i < num_trials; i++) {
         if (PA4_Java.run_trial(should_switch)) {
            wins++;
         }
      }
      return (double) wins / num_trials;
   }
   
   /* This is a stub that you can use to test the rest of the program. The code in this
    * method will not be executed during grading, so you don't need to worry about user
    * input.
    */
   public static void main(String[] args) {
      int num_trials = 10000;
   
      double probSwitch = PA4_Java.run_experiment(num_trials, true);
      System.out.println("Probability of winning with switching " + probSwitch);
   
      double probNoSwitch = PA4_Java.run_experiment(num_trials, false);
      System.out.println("Probability of winning without switching " + probNoSwitch);
   }
}