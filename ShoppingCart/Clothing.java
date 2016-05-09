package Assignment3;

public class Clothing extends Item 
{

	// variables, constructors as necessary
		public Clothing(String initName, float initPrice, int initQuantity, int initWeight){
			super(initName, initPrice, initQuantity, initWeight);
		}
	
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float)(price * quantity); // price * number of items
		final_price += final_price*(.10); // add tax
		final_price += (20* weight)*quantity; // add shipping
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.printf("%s %.2f %d %d\nFinal Price for item(s): $%.2f\n", name, price, quantity, weight, calculatePrice() );
	}
	
}
