/**
 @author     Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 @version    1.1
 @since      1.0
 */

/*
 * Animal describes the color and legs of an animal
 */
	
class Animal {

	private int legs = 4;
	private String color;
	
	public Animal(String animalColor, int legsNumber) {
		this.color = animalColor;
		this.legs = legsNumber;
	}
	
	public Animal(String animalColor) {
		this.color = animalColor;
	}
	
	public int getLegs() {
		return legs;
	}
	
	public void setLegs(int legsNumber) {
		this.legs = legsNumber;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String animalColor) {
		this.color = animalColor;
	}
}
