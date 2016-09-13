package com.toan_itc.data.libs.reactivenetwork;

public class Preconditions {
  /**
   * Validation method, which checks if an object is null
   *
   * @param object to verify
   * @param message to be thrown in exception
   */
  public static void checkNotNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Validation method, which checks if a string is null or empty
   *
   * @param string to verify
   * @param message to be thrown in exception
   */
  public static void checkNotNullOrEmpty(String string, String message) {
    if (string == null || string.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Validation method, which checks is an integer number i positie
   *
   * @param number integer to verify
   * @param message to be thrown in exception
   */
  public static void checkPositive(int number, String message) {
    if (number <= 0) {
      throw new IllegalArgumentException(message);
    }
  }
}


