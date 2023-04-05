public class PA3_Java {
/* Multiplies matrix 1 by matrix 2
*/
   public static float[][] matrix_mult(float[][] mat1, float[][] mat2) {
      float[][] result = new float[mat1.length][mat2.length];
      return result;
   }
/* Create the transition matrix from the given observation points
*/
   public static float[][] calc_transition_matrix(String observation_string) {
      float[][] transition_matrix = new float[2][2];
      return transition_matrix;
   }
/* Generates the forecast for the next 7 days given the transition matrix and
the current weather
The forecast should be a 2x7 matrix where each row is a forecast for a day
*/
   public static float[][] generate_forecast(float[][] transition_matrix, char
   curr_weather) {
      float[][] forecast = new float[7][2];
      return forecast;
   }
/* Generates the climate prediction (i.e., steady state vector) given the
transition matrix, current
weather, and precision
*/
   public static float[] generate_climate_prediction(float[][] transition_matrix,
   char curr_weather, float precision) {
      float[] steady_state = new float[2];
      return steady_state;
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
/* Print the steady state vector containing the climate prediction
*/
   public static void print_steady_state(float[] steady_state) {
      System.out.println(steady_state[0]);
      System.out.println(steady_state[1]);
   }
   public static void main(String[] args) {
      String observation = "DDRRRDRDDD";
      float precision = 0.01f;
   }
}