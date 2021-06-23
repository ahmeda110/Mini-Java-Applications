package edu.ucalgary.ensf409;

/**
 * @author Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 *
 * @version	1.3
 * @since	1.0
 */

import java.util.regex.*;

public class StringProcessor{
    
    private final String storedString;
    
    public StringProcessor(String input){ this.storedString = new String(input); }
    
    public String addTogetherMirror(String inputString) {
		
        String combined = storedString.trim() + inputString.trim();
        return converse(combined.toLowerCase());
    }
	
	public String converse(String input) {
	
		String result = "";
		if (input == null) { return input; }
	
	    for (int a = input.length() - 1; a >= 0; a--) { result = result + input.charAt(a); }
	    return result;
	}

    public static String idProcessing(String firstName, String lastName, String petName, int year) {
    	
    	if (year < 1000 || year > 2021) { throw new IllegalArgumentException("Invalid Year"); }
		
    	else {
    		isValid(firstName);
    		isValid(lastName);
    		isValid(petName);
    		
            if (firstName.length() < 2 || firstName.length() > 26) { throw new IllegalArgumentException("First Name: invalid length."); }
            if (lastName.length() < 2 || lastName.length() > 26) { throw new IllegalArgumentException("Last Name: invalid length."); }
            if (petName.length() < 2 || petName.length() > 26) { throw new IllegalArgumentException("Pet Name of invalid length."); }
            
    		String petID = new String(String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0)) + String.valueOf(petName.charAt(0)) + String.valueOf(year));
    	    return petID;
    	}
    }
	
	public static void isValid(String name){
		
	    final String REGEX = "^[A-Z](?!.*\\p{P}{2})([ \\p{P}\\p{L}]|[a-zA-Z]){0,24}[a-zA-Z]+$";
	    final Pattern PATTERN = Pattern.compile(REGEX);
	    Matcher matcher = PATTERN.matcher(name);
	    	
		if(!matcher.find() || name.contains("  ")) { throw new IllegalArgumentException("Invalid Name!"); }
	}

    public String secretCode(int offset) {
		
		String encodedString = new String();
    	if(offset < 0) { throw new IllegalArgumentException("Invalid Offset: Negative offset!"); }
        
        for (int i = 0; i < storedString.length(); i++){
        	if (Character.isLetter(storedString.charAt(i))) {
        		if(offset > 26){ offset = offset % 26; }
				
        		char x = (char) (storedString.charAt(i) + offset);
        		
        		if ((Character.isUpperCase(storedString.charAt(i)) && x > 'Z') || (Character.isLowerCase(storedString.charAt(i)) && x > 'z')) {
        			x = (char)(storedString.charAt(i) - (26 - offset));
        		}
        		encodedString += x;
        	}
        	else { encodedString += storedString.charAt(i); }
        }
        return encodedString;
    }

    public String getStoredString(){
        return this.storedString;
    }
}

