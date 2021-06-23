import java.util.StringTokenizer;

/**
 @author     Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 @version    1.1
 @since      1.0
 */

// This program returns length of 2 strings without whitespace,
// ID processes, and secrete codes ingrediants
class JavaStrings{

	/**
	 * finds length of only characters in 2 strings combined.
	 * @param str1 first string
	 * @param str2 second string
	 * @return
	 */
	public int addTogether(String str1, String str2) {

		int length = 0;
		StringTokenizer tokens = new StringTokenizer(str1+str2);
		while (tokens.hasMoreTokens()) {
			length+=tokens.nextToken().length();
		}
		return length;
	}

	/**
	 * Creates an ID for a pet using the first letter of the owners first name
	 * ,last name, pet's name, and year of pets birth concatenated
	 *
	 * @param firstName Owner's first name
	 * @param lastName Owner's last name
	 * @param petName Pet's name
	 * @param year Pets birth year
	 * @return
	 */
	public String idProcessing (String firstName, String lastName, String petName, int year) {

		String label = "";
		label=
				firstName.substring(0,1).toUpperCase()+
						lastName.substring(0,1).toUpperCase()+
						petName.substring(0,1).toUpperCase()+
						year;


		return label;
	}

	/**
	 * Secret codes a given ingredient
	 *
	 * @param str Ingredient
	 * @return
	 */
	public String secretCode (String str) {

		String codedStr = str.toLowerCase().substring(0,3)
				.replaceAll("a", "z").replaceAll("e", "z")
				.replaceAll("i", "z").replaceAll("o", "z")
				.replaceAll("u", "z");

		return codedStr;

	}
}