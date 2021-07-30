  package edu.ucalgary.ensf409;
  import java.util.regex.Pattern;
  import java.util.regex.Matcher;

  public class DayMemory {
    /* main()
     * Accept a command-line argument which specifies a translation file.
     * The argument should be in the form of a two-letter language code,
     * followed by a dash and a two-letter region code, e.g., en-US
     * which corresponds with files en-US.txt and en-US.ser
     * If no argument is specified, it throws a custom exception,
     * CommandArgumentNotProvidedException. Additional arguments are
     * ignored.
     */

    private static String REGEX = "^([a-z]{2})-([A-Z]{2})$";
    private static Pattern PATTERN = Pattern.compile(REGEX);

    public static void main(String[] args) throws CommandArgumentNotProvidedException {
      Matcher match = null;
      String matcher = "";
      Translator trans = null;

      if (args.length == 0) {
        throw new CommandArgumentNotProvidedException();
      } else {
        match = PATTERN.matcher(args[0]);

        while (match.find()) {
          matcher = match.group();
        }

        trans = new Translator(matcher);

        System.out.println(trans.translate(3, 3, 5676));
        System.out.println(trans.translate(1, 1, 2020));
        System.out.println(trans.getTranslation());

      }
    }

  }

  class CommandArgumentNotProvidedException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1 L;

    public CommandArgumentNotProvidedException() {
      super("Command Argument Not Provided");
    }
  }
