package Assignment3;

public class Grocery extends Item {

	//variables, constructor here
	protected String perishable;
	
	public Grocery(String initName, float initPrice, int initQuantity, int initWeight, String perish){
		super(initName, initPrice, initQuantity, initWeight);
		perishable = perish;
	}
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float)(price * quantity); // price * number of items
		final_price += final_price*(.10); // add tax
		if(perishable.equals("P"))
		{
			float ship = (float)(20* weight)*quantity; // add shipping
			ship += ship*.20;
			final_price += ship;
		}
		else
		{
			final_price += (20* weight)*quantity; // add shipping
		}
		return final_price;
	}
	
	void printItemAttributes () 
	{
		if(perishable.equals("P"))
		{
			System.out.printf("%s %.2f %d %d P\nFinal Price for item(s): $%.2f\n", name, price, quantity, weight, calculatePrice() );
		}
		else
		{
			System.out.printf("%s %.2f %d %d NP\nFinal Price: $%.2f\n", name, price, quantity, weight, calculatePrice() );

		}
	}

	public String getPerishable() {
		return perishable;
	}

	public void setPerishable(String perishable) {
		this.perishable = perishable;
	}
	
}
