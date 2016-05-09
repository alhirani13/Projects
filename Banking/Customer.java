/* Hirani, Altamshali
 * AH45675
 * TA: Jo
 * Unique Course Number: 16165
 * EE 422C - Assignment 2
 */
package Assignment2;

public class Customer {
	
	protected String name; 
	protected int uniqueNumber;
	protected String address;
	
	public Customer()
	{
		name = "";
		uniqueNumber = 0;
		address = "";
	}
	public Customer(String n, int uN, String a)
	{
		name = n;
		uniqueNumber = uN;
		address = a;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	public void setUniqueNumber(int uN)
	{
		uniqueNumber = uN;
	}
	public void setAddress(String a)
	{
		address = a;
	}
	
	public String getName()
	{
		return name;
	}
	public int getUniqueNumber()
	{
		return uniqueNumber;
	}
	public String getAddress()
	{
		return address;
	}
	

}
