package Assignment3;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	protected String fragile;
	protected String state;
	
	public Electronics(String initName, float initPrice, int initQuantity, int initWeight, String frag, String sta){
		super(initName, initPrice, initQuantity, initWeight);
		fragile = frag;
		state = sta;
	}
		
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float)(price * quantity); // price * number of items
		if(!(state.equals("TX") || state.equals("NM") || state.equals("VA") || state.equals("AZ") || state.equals("AK") ))
		{
			final_price += final_price*(.10); // add tax
		}
		if(fragile.equals("F"))
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
		if(fragile.equals("F"))
		{
			System.out.printf("%s %.2f %d %d F %s\nFinal Price for item(s): $%.2f\n", name, price, quantity, weight, state.toUpperCase(), calculatePrice() );
		}
		else
		{
			System.out.printf("%s %.2f %d %d NF %s\nFinal Price for item(s): $%.2f\n", name, price, quantity, weight, state.toUpperCase(), calculatePrice() );

		}
	}
	//Implement calculate price/print methods as necessary

	public String getFragile() {
		return fragile;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
