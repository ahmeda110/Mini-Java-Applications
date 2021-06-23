package edu.ucalgary.ensf409;
import java.io.*;

/* TranslationText
 * Serializable representation of the data file. Has the serialVersionUID of 19.
 * No method in this class throws an exception.
*/

public class TranslationText implements Serializable{

	private String sentence;
	private String[] months;
	private String[] days;
	
	static final long serialVersionUID = 19L;

	/* getSentence()
	 * Getter method, returns String
	 */
	public String getSentence() {
		String senStr = "";
		
		for (int i = 0; i < sentence.length(); i++) {
			senStr += sentence.charAt(i);
		}
		
		return senStr;
	}

	/* getMonths()
	 * Getter method, returns String[]
	 */
	 
	public String[] getMonths() {
		String[] newMonth = new String[months.length];
		
		for (int i = 0; i < months.length; i++) {
			newMonth[i] = months[i];
		}
		
		return newMonth;
	}

	/* getDays()
	 * Getter method, returns String[]
	 */
	 
	public String[] getDays() {
		String[] newDay = new String[days.length];
		
		for (int i = 0; i < days.length; i++) {
			newDay[i] = days[i];
		}
		
		return newDay;
	}

	/* getMonth()
	 * Accepts an integer 0-11 corresponding to an index in the months array,
	 * and returns the value at that index.
	 */

	public String getMonth(int index) {
		if (index > months.length || index < 0) {
			System.out.println("Out of bounds");
			return "Out of bounds";
		}
		
		return months[index];
	}

	/* getDay()
	 * Accepts an integer 0-30 corresponding to an index in the day array,
	 * and returns the value at that index.
	 */

	public String getDay(int index) {
		if (index > days.length || index < 0) {
			System.out.println("Out of bounds");
			return "Out of bounds";
		}
		
		return days[index];
	}

	/* Constructor
	 * Accepts a String array of months, a String array of days, and a String 
	 * containing a sentence with formatting.
	 */
	
	public TranslationText(String[] months, String[] days, String sentence) {
		
		this.months = new String[12];
		this.days = new String[31];
		
		for (int i = 0; i < months.length; i++) {
			this.months[i] = months[i];
		}
		
		for (int i = 0; i < days.length; i++) {
			this.days[i] = days[i];
		}
		
		this.sentence = sentence;
	}
	
	public TranslationText(TranslationText copy) {
		this.months = copy.getMonths();
		this.days = copy.getDays();
		this.sentence = copy.getSentence();
	}
}