package edu.ucalgary.ensf409;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
import java.util.regex.Matcher;

public class Translator {

  private TranslationText translation;
  private String code;
  private static String REGEX = "^([a-z]{2})-([A-Z]{2})$";
  private static Pattern PATTERN = Pattern.compile(REGEX);

  /* getTranslation()
   * getter method
   */

  public TranslationText getTranslation() {
    return new TranslationText(translation);
  }

  /* translate()
   * Accepts a month number (e.g., 1 for January), a day number (e.g., 31 for
   * the 31st), and a year. Throws an IllegalArgumentException if monthNum or dayNum is 
   * not valid. Returns the formatted sentence as a String. Notice that the String
   * containing formatting uses numbered arguments - this is because some languages
   * will put the words in the sentence in a different order, but the translate()
   * method must be able to work without knowledge of the language structure.
   * Note: You do not have to check if a day is valid for a particular month/year;
   * February 31 or February 29, 2021 can both be accepted, but out of range values
   * e.g., month 15 day 40, are not valid and should be handled with an 
   * IllegalArgumentException. 
   */

  public String translate(int monthNum, int dayNum, int year) throws IllegalArgumentException {

    String returnStr = "";

    if (monthNum < 1 || monthNum > 12) {
      throw new IllegalArgumentException("Month number is invalid");
    }
    if (dayNum < 1 || dayNum > 31) {
      throw new IllegalArgumentException("Day number is invalid");
    }

    returnStr = String.format(translation.getSentence(), translation.getDay(dayNum - 1), translation.getMonth(monthNum - 1), year);

    return returnStr;
  }

  /* Constructor
   * Accepts a String of a two-letter language code, dash, and two-letter region
   * code, e.g., te-IN and throws an IllegalArgumentException if the language and
   * region code are not in the correct format. Language codes are ISO 639-1 and
   * region codes are ISO 3166, but this method only checks the format of the String, 
   * not the validity of the codes. It calls importTranslation().
   */

  public Translator(String code) throws IllegalArgumentException {
    Matcher match = PATTERN.matcher(code);

    if (!match.find()) {
      throw new IllegalArgumentException("Wrong code format!");
    }

    this.code = code;
    importTranslation();

  }

  /* importTranslation()
   * Calls deserialize() if the appropriate file exists, otherwise calls importFromText().
   * No arguments. Returns void.
   */

  public void importTranslation() {
    File file = new File(code + ".ser");

    if (file.exists()) {

      try {
        deserialize();
      } catch (IOException e) {
        System.out.println("IOException");
      } catch (Exception e) {
        System.out.println("Exception");
      }

    } else {
      try {
        importFromText();
      } catch (ArgFileNotFoundException e) {
        System.out.println("File not found, ending program");
        System.exit(1);
      } catch (IOException e) {
        System.out.println("IOException");
      } catch (Exception e) {
        System.out.println("Exception");
      }
    }
  }

  /* importFromText()
   * Reads in from a the two-letter language code, dash, two-letter region code text 
   * file, in the form of ab-XY.txt, and instantiates a TranslationText object with
   * the data. It can throw I/O exceptions. Throw a custom ArgFileNotFoundException
   * when the file isn't found. 
   * We expect the .txt file to be in a valid format. The file is expected to be in the same 
   * directory. The files en-US.txt and el-GR.txt are examples of a valid .txt files. They will 
   * always consist of the month names, one per line, followed by the day names, one per line, 
   * followed by the sentence containing formatting strings. This is the last line in the file. You
   * cannot make any assumptions about what will appear on each line, only that each line
   * will contain only one data element, and that it will not contain an empty line.
   * No arguments. Returns void.
   */

  public void importFromText() throws ArgFileNotFoundException, IOException {
    String[] months = new String[12];
    String[] days = new String[31];
    String sentence = "";

    Scanner read = null;
    File file = new File(code + ".txt");

    try {

      read = new Scanner(file, "UTF-8");

    } catch (FileNotFoundException e) {
      System.out.println("No file with name " + code + " and .txt or .ser extension was found");
      throw new ArgFileNotFoundException();
    }

    if (read != null) {
      //Reads the months
      for (int i = 0; i < 12; i++) {
        months[i] = read.nextLine();
      }

      //Reads the days
      for (int i = 0; i < 31; i++) {
        days[i] = read.nextLine();
      }

      //Reads final sentence
      sentence = read.nextLine();

    } else {
      System.out.println("read returned null");
    }

    this.translation = new TranslationText(months, days, sentence);
    read.close();
  }

  /* serialize()
   * Creates a serialized object file of the TranslationText object, with the
   * name format la-CO.ser, where la is the two-letter language code and CO is
   * the two-letter region code. An example of a serialized object file can be
   * found in the exercise directory as es-BO.ser
   * I/O exceptions can be thrown.
   * No arguments. Returns void.
   */

  public void serialize() throws IOException {
    ObjectOutputStream obj = null;
    String path = code + ".ser";

    try {
      obj = new ObjectOutputStream(new FileOutputStream(path));
      obj.writeObject(this.translation);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (obj != null) {
          obj.close();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /* deserialize()
   * Creates a TranslationText object from a .ser file. The files are named
   * xx-YY.ser, where xx is the two-letter language code and YY is the two-
   * letter region code. es-bo.ser is an example. It can throw I/O exceptions.
   * No arguments. Returns void.
   */

  public void deserialize() throws IOException, Exception {
    ObjectInputStream input = null;
    TranslationText tran = null;

    input = new ObjectInputStream(new FileInputStream(code + ".ser"));

    try {

      tran = (TranslationText) input.readObject();

    } catch (Exception e) {
      System.err.println("Generic error handler");
      e.printStackTrace();
    } finally {
      try {
        if (input != null) {
          input.close();
        }
      } catch (IOException e) {
        System.err.println("Error closing file.");
        System.exit(1);
      }
    }

    this.translation = tran;
  }
}

class ArgFileNotFoundException extends Exception {

  private static final long serialVersionUID = 1 L;

  public ArgFileNotFoundException() {
    super("Argument File Not Found");
  }
}
