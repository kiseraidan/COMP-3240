/**
* Class Validator validates logic expressions.
* 
* @author Aidan Kiser - COMP3240
* @author Daniel Tauritz
* @version 4 February 2023
*/

// DO NOT DELETE THIS LINE!!!
package com.gradescope.validator;
import java.util.Hashtable;

// Notes on how to use evaluator():
// Call the evaluator with Evaluator.evaluate(<premise>, <variable_dict>). "premise"
// is a single string defining the premise or conclusion to test. "variable_dict" is a
// Hashtable<Character, Boolean>() object with the variable name as the key and true/false
// as the value. 

// The only valid operators for premise strings are '^' (and), 'V' (or--CAPITAL v), '~' (not),
// and '>' (implies), and you can use parentheses to override the order of operations as usual.
// All variables should be lowercase letters and each should only be one character long. Finally,
// do not include spaces in the string.

// For example, if you want to test the premise 'p implies q or not r', you should use 'p>qV~r' as
// your premise string.


public class Validator {
   // All of the logic to complete this assignment should be written in this function.
   // This method accepts two things: An array of strings called premiseList and a 
   // single string called conclusion. These strings should be formatted according to 
   // the structure definded above. Also, this needs to return a boolean variable: true if
   // the argument form is valid, and false if it is not valid.
   
   /**
   * Class validate accepts two parameters: a parmeter of type String[]
   * representing the list of premises to be validated, and a parameter
   * of type String representing the conclusion to be evaluated.
   *
   * @param premiseList The list of premises.
   * @param conclusion The conclusion of the argument.
   * @return A boolean true if the argument is valid, false if it is not.
   */
   public boolean validate(String[] premiseList, String conclusion) {
      
      // Creating the Hashtable vars to store truth values.
      Hashtable<Character, Boolean> vars = new Hashtable<Character, Boolean>();
      boolean[] values = { false, true };
      boolean isValid = false;

      /* 
      * Loops through premiseList and evaluates each premise using the
      * Evaluator class. If any premise is false, the argument is not valid
      * and returns false.
      *
      * If all the premises are true, then the conclusion is evaluated
      * using the Evaluator class. 
      */
      for (char c = 'A'; c <= 'Z'; c++) {
         for (boolean value : values) {
            vars.clear();
            vars.put(c, value);
            isValid = true;
            
            /*
             * If the conclusion is false, the argument is not valid and the
             * method returns false.
             */
            for (String premise : premiseList) {
               
               try {
                  boolean premiseValue = Evaluator.evaluate(premise, vars);
                  
                  if (!premiseValue) {
                     isValid = false;
                     break;
                  }
               } 
               
               catch (Exception e) {
                  isValid = false;
                  break;
               }
            }
            
            if (isValid) {
               break;
            }
         }
         
         if (!isValid) {
            break;
         }
      }
      
      return isValid;
   }
}