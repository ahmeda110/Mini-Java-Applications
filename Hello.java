/**
 @author     Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 @version    1.1
 @since      1.0
 */


public class Hello {

	/**
	 * This program modifies a Hello World program by
	 * implementing many primitive data types such as boolean, char, byte,
	 * short, int, long, float, and double as well as the implementation
	 * of automatic and explicit casting.
	 * @param args optional command - unused
	 */
	public static void main(String[] args) {
		
		//Utilization of primitive data types
		boolean exampleVariable = true; //True/False
		char exampleCharacter = 'B'; // any single character, number or punctuation
		byte exampleByte = 100; // -128 - 127
		short exampleShort = 10000; // -32,768 - 32,767
		int exampleInteger = 100000; // -2^(31) - 2^(31)-1
		long exampleLong = 1000000; //-2^(63) - 2^(63)-1
		float exampleFloat = 8.3f; //1.4^(-45) - 3.4^(38)
		double exampleDouble = 1.2; // 4.9^(-324) - 1.8^(308)
		
		System.out.println("Hello? " + exampleVariable);
		System.out.println("long value subracted the int value = " + exampleLong + "-" + exampleInteger + " = " + (exampleLong-exampleInteger));
		System.out.println("Short value added to the Byte value = " + exampleShort + "+" + exampleByte + " = " + (exampleShort+exampleByte));
		System.out.println("Double value multiplied by the float value = " + exampleDouble + "*" + exampleFloat + " = " + (exampleDouble*exampleFloat));
		System.out.println("What is the answer to Q20? " + exampleCharacter);
		
		//Utilization of casting
		exampleDouble = exampleInteger; // automatic
		exampleInteger = (int)exampleDouble; //explicit
	}
}
	

	