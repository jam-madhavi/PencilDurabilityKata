package com.kata.pencildurability;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(KataTestSuite.class);

      for (Failure failure : result.getFailures()) {
    	 System.out.println(failure.getTrace()); 
      }
      
     System.out.println("Tests run : " + result.getRunCount() 
    	  					+" Tests Failed :" + result.getFailureCount()
    	  					+ " Tests Passed :" + (result.getRunCount() - result.getFailureCount()));
      System.out.println("Test run : " + result.wasSuccessful());
   }
}
