import java.util.Arrays;

/**
 @author     Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 @version    1.1
 @since      1.0
 */

//This class does string concatenation
class SimpleArrays {

  String[] array;

  /**
   * Creates a 4 element instance array as well as populating it
   * @param x the string to populate with
   */
  public SimpleArrays(String x) {
    this.array = new String[4];
    Arrays.fill(this.array, x);
  }

  //Creates a 4 element instance array as well as populating it
  public SimpleArrays() {
    this.array = new String[4];
    Arrays.fill(this.array, "Hello, ENSF 409");
  }

  /**
   * Concats array elements together separated by '#' from a given index till the end
   * @param index starting index
   * @return concatenated string
   */
  public String arrayConcat() {
    return this.arrayConcat(0);
  }

  public String arrayConcat(int index) {

    String concatenatedString = "";

    if (index < 0 || index > array.length - 1) {
      throw new ArrayIndexOutOfBoundsException("Index out of Bounds!");
    }

    for (int i = index; i < array.length; i++) {
      concatenatedString += array[i] + "#";
    }

    concatenatedString = concatenatedString.substring(0, concatenatedString.length() - 1);
    return concatenatedString;
  }

  /**
   * Concats array elements together separated by '#' from a given index till a given end
   * @param start starting index
   * @param end ending index
   * @return concatenated string
   */
  public String arrayCrop(int start, int end) {

    String result = "";

    if (start < 0 || start > array.length - 1 || end < 0 || end > array.length - 1) {
      return "Fail";
    } else if (start == end) {
      return "Match";
    } else if (end < start) {
      int temp = start;
      start = end;
      end = temp;
    }

    for (int i = start; i <= end; i++) {
      result += array[i] + "#";
    }

    result = result.substring(0, result.length() - 1);
    return result;
  }
}
