import java.lang.Math
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
/**
* Class PA3_Java computes matrices to output weather predictions.
* 
* @author Aidan Kiser - COMP3240
* @author Daniel Tauritz
* @version 5 April 2023
*/
public class PA3_Java {

/* Multiplies matrix 1 by matrix 2
*/
   public static float[][] matrix_mult(float[][] mat1, float[][] mat2) {
      int rowsA = mat1.length;
      int colsA = mat1[0].length;
      int colsB = mat2[0].length;
      float[][] result = new float[rowsA][colsB];
      
      for (int i = 0; i < rowsA; i++) {
         for (int j = 0; j < colsB; j++) {
            for (int k = 0; k < colsA; k++) {
               result[i][j] += mat1[i][k] * mat2[k][j];
            }
         }
      }
      
      return result;
   }

/* Create the transition matrix from the given observation points
*/
   public static float[][] calc_transition_matrix(String observation_string) {
      // Defines the transition matrix
      float[][] transition_matrix = new float[2][2];
      
      // Count transitions between states
      int state1Count = 0;
      int state2Count = 0;
      for (int i = 1; i < observation_string.length(); i++) {
         char prev = observation_string.charAt(i-1);
         char curr = observation_string.charAt(i);
         if (prev == 'D' && curr == 'D') {
            transition_matrix[0][0]++;
            state1Count++;
         } else if (prev == 'D' && curr == 'R') {
            transition_matrix[0][1]++;
            state1Count++;
         } else if (prev == 'R' && curr == 'D') {
            transition_matrix[1][0]++;
            state2Count++;
         } else if (prev == 'R' && curr == 'R') {
            transition_matrix[1][1]++;
            state2Count++;
         }
      }
      
      if (transition_matrix[0][0] == 0 || transition_matrix[0][1] == 0 || transition_matrix[1][0] == 0 || transition_matrix[1][1] == 0) {
         throw new IllegalArgumentException("There must be a transition from D to R, D to D, R to R, and R to D");
      }
      
      // Normalize the transition matrix
      if (state1Count > 0) {
         transition_matrix[0][0] /= state1Count;
         transition_matrix[0][1] /= state1Count;
      }
      if (state2Count > 0) {
         transition_matrix[1][0] /= state2Count;
         transition_matrix[1][1] /= state2Count;
      }
      
      return transition_matrix;
   }

/* Generates the forecast for the next 7 days given the transition matrix and
the current weather
The forecast should be a 2x7 matrix where each row is a forecast for a day
*/
   public static float[][] generate_forecast(float[][] transition_matrix, char
   curr_weather) {
      // Defines the forecast matrix
      float[][] forecast = new float[7][2];
      
      // Initialize the current weather state
      int currState = curr_weather == 'D' ? 0 : 1;

      float[][] original_tMatrix = transition_matrix;
      
      // Compute the forecast for each day
      for (int i = 0; i < 7; i++) {
         
         // Update the forecast for the current day
         forecast[i][0] = transition_matrix[currState][0];
         forecast[i][1] = transition_matrix[currState][1];

         // Matrix multiplication to generate the days
         transition_matrix = matrix_mult(transition_matrix, original_tMatrix);

         // Update the current weather state
         double rand = Math.random();
         if (rand < transition_matrix[currState][0]) {
            currState = 0;
         } else {
            currState = 1;
         }
      }
      
      return forecast;
   }

/* Generates the climate prediction (i.e., steady state vector) given the
transition matrix, current
weather, and precision
*/
   public static float[] generate_climate_prediction(float[][] transition_matrix,
   char curr_weather, float precision) {
      
      // Define the intitial guess vector and error
      float[] guess = {0.5f, 0.5f};
      float error = 0.1f;
   
      // Initialize the current weather state
      int currState = curr_weather == 'D' ? 0 : 1;
   
      // Iteratively update the guess vector until convergence
      while (error > precision) {
         // Compute the next guess vector
         float[] steady_state = new float[2];
         for (int i = 0; i < 2; i++) {
            steady_state[i] = 0.0f;
            for (int j = 0; j < 2; j++) {
               steady_state[i] += transition_matrix[j][i] * guess[j];
            }
         }
      
         // Compute the error between the current and next guess vectors
         error = 0.0f;
         for (int i = 0; i < 2; i++) {
            error += Math.abs(steady_state[i] - guess[i]);
         }
      
         // Update the guess vector and the current state
         guess = steady_state;
         currState = curr_weather == 'D' ? 0 : 1;
      }
   
      return guess;
   }

/* Print the forecasted weather predictions
*/
   public static void print_predictions(float[][] forecast) {
   // Print first line
      System.out.println("[[" + forecast[0][0] + "," + forecast[0][1] + "],");
   // Print middle 5 lines
      for (int i = 1; i < forecast.length - 1; i++) {
         System.out.println(" [" + forecast[i][0] + "," + forecast[i][1] + "],");
      }
   // Print the last line
      System.out.println(" [" + forecast[6][0] + "," + forecast[6][1] + "]]");
   }

   static public void validateUserInput(String observation, double precision) {
      for (int i = 0; i < observation.length(); i++) {
         if (observation.charAt(i) != 'D' && observation.charAt(i) != 'R') {
            throw new IllegalArgumentException("Observation must only contain Ds and Rs");
         }
         // We will validate all matrix construction in the original matrix calculation method.
      } 
      if (precision  < 0 || precision > 0.1) {
         throw new IllegalArgumentException("precision must be between 0 and 0.1");
      }
      if (observation.length() == 0) {
         throw new IllegalAccessError("Observation must be more than one character");
      }
   }


/* Print the steady state vector containing the climate prediction
*/
   public static void print_steady_state(float[] steady_state, float precision) {
      int decimalPlaces = (int) Math.ceil(-Math.log10(precision));
      String format = "%." + decimalPlaces + "f";
      String roundedNumber1 = String.format(format, 3.14159265f);
      float roundedFloat1 = Float.parseFloat(roundedNumber1);

      String roundedNumber2 = String.format(format, steady_state[1]);
      float roundedFloat2 = Float.parseFloat(roundedNumber2);

      System.out.println(roundedFloat1);
      System.out.println(roundedFloat2);
   }
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);  // Create a Scanner object
   
      System.out.println("Enter a sequence of Ds and Rs with the last character being the current weather: ");
      String observation = scanner.nextLine();  // Read user input

      System.out.println("Please begin by entering the degree of precision between 0 and 0.1: ");
      float precision = scanner.nextFloat();
      validateUserInput(observation, precision);

      //System.out.print("Enter current weather (D/R): ");
      char curr_weather = observation.charAt(observation.length()-1);
      
      // Calc the transition matrix from the observation string
      float[][] transition_matrix = calc_transition_matrix(observation);
      
      // Generate the forecast for the next 7 days
      float[][] forecast = generate_forecast(transition_matrix, curr_weather);

      // Print the forecasted weather predictions
      print_predictions(forecast);

      float[] steady_state_vector = generate_climate_prediction(transition_matrix, curr_weather, precision);
      print_steady_state(steady_state_vector, precision);
   
      String observation1 = "DDRRRDRDDD";
      String observation2 = "RRRRRDDRRRRRRRR";
      float precision1 = 0.01f;
   }
}
