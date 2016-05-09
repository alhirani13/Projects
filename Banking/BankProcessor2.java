/* Hirani, Altamshali
 * AH45675
 * TA: Jo
 * Unique Course Number: 16165
 * EE 422C - Assignment 2
 */
package Assignment2;

import javax.swing.JOptionPane;

public class BankProcessor2 {
		//An array of customers that will be using the bank
		public static Customer[] custList = new Customer[10];
		
		/*An array of customers' accounts: 
		 * First account in each customers' accounts array  == Checking Account
		 * Second account in each customers' accounts array == Primary Savings Account
		 * Third account in each customers' accounts array == Student Loan Repayments Account
		 * Fourth account in each customers' accounts array == Auto Loan Repayments Account
		*/
		public static BankAccount[][] bank = new BankAccount[10][4];
		
		public static void main(String[] args) {
			for(int r = 0; r < bank.length; r++)
			{
				custList[r] = new Customer("Customer", r+1, "12345 Street Lane");
				for(int c = 0; c < bank[r].length; c++)
				{
					bank[r][c] = new BankAccount(r+1, custList[r], 0.0);
				}
			}
			
			boolean cont = true; //boolean to see whether or not to continue to the next transaction
			while(cont)
			{
				//Show a dialog asking the user to type in a String:
				String inputValue = JOptionPane.showInputDialog("Please input a transaction");
				String[] inputLine = inputValue.split("\\s");
				/*for(String temp : inputLine)
				{
					System.out.println(temp);
				}
				*/
				if(isParsable(inputLine[0]))
				{
					int custNumber = Integer.parseInt(inputLine[0]);
					BankAccount acct1 = null, acct2 = null;;
					String inputTemp1 = "";
					String inputTemp2 = "";
					switch(inputLine.length)
					{
						case 5: inputTemp1 = inputLine[3];
						 	    inputTemp2 = inputLine[4];break;
						case 4: inputTemp1 = inputLine[3];break;
						case 3: inputTemp1 = inputLine[2];break;
						default: JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE); continue;

					}
					switch(inputTemp1.charAt(0))
					{
						case 'C': acct1 = bank[custNumber][0];break;
						case 'S': acct1 = bank[custNumber][1];break;
						case 'L': acct1 = bank[custNumber][2];break;
						case 'A': acct1 = bank[custNumber][3];break;
						default: JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE); continue;

					}
					if(!inputTemp2.equals(""))
					{
						switch(inputTemp2.charAt(0))
						{
							case 'C': acct2 = bank[custNumber][0];break;
							case 'S': acct2 = bank[custNumber][1];break;
							case 'L': acct2 = bank[custNumber][2];break;
							case 'A': acct2 = bank[custNumber][3];break;
							default: JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE); continue;

						}
					}
					//System.out.println(inputLine[1]);
					//System.out.println(inputLine[1]);
					switch(inputLine[1].charAt(0))
					{
						case 'D': acct1.deposit(Double.parseDouble(inputLine[2])); printTransaction(custNumber, Double.parseDouble(inputLine[2]), 'D', inputTemp1.charAt(0), 'Z', false); break;
						case 'W': if(acct1.getBalance() >= Double.parseDouble(inputLine[2]))
							{
								acct1.withdraw(Double.parseDouble(inputLine[2]));
								printTransaction(custNumber, Double.parseDouble(inputLine[2]), 'W', inputTemp1.charAt(0), 'Z', false);
							}
						else if(acct1 == bank[custNumber][0])
						{		
								if(acct1.getBalance() + bank[custNumber][1].getBalance()-20 >= Double.parseDouble(inputLine[2]))
								{
									bank[custNumber][1].withdraw(20);
									bank[custNumber][1].withdraw(Double.parseDouble(inputLine[2]) - acct1.getBalance());
									acct1.withdraw(acct1.getBalance());
									printTransaction(custNumber, Double.parseDouble(inputLine[2]), 'W', inputTemp1.charAt(0), 'Z', true);
								}
								else{
									JOptionPane.showMessageDialog(null, "Unable to Complete Transaction: Insufficient Funds", "Unable to Complete Transaction: Insufficient Funds", JOptionPane.ERROR_MESSAGE);
									continue;
								}
						}
						else{
							JOptionPane.showMessageDialog(null, "Unable to Complete Transaction: Insufficient Funds", "Unable to Complete Transaction: Insufficient Funds", JOptionPane.ERROR_MESSAGE);
							continue;
						}
							break;
							
						case 'I': if(acct1 != bank[custNumber][0]){
							if(acct1.getBalance() >= 1000) 
							{
								printTransaction(custNumber, acct1.getBalance() * .04, 'I', inputTemp1.charAt(0), 'Z', false);
								acct1.deposit(acct1.getBalance()*.04);
							}
						else{
							JOptionPane.showMessageDialog(null, "Unable to Complete Transaction: Insufficient Funds", "Unable to Complete Transaction: Insufficient Funds", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
							continue;
						}
							break;
							
						case 'T': if(acct1.getBalance() >= Double.parseDouble(inputLine[2]))
								{
									acct1.withdraw(Double.parseDouble(inputLine[2]));
									acct2.deposit(Double.parseDouble(inputLine[2]));
									printTransaction(custNumber, Double.parseDouble(inputLine[2]), 'T', inputTemp1.charAt(0), inputTemp2.charAt(0), false);
								}
						else{
							JOptionPane.showMessageDialog(null, "Unable to Complete Transaction: Insufficient Funds", "Unable to Complete Transaction: Insufficient Funds", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						break;
						
						case 'G': printTransaction(custNumber, acct1.getBalance(), 'G', inputTemp1.charAt(0), 'Z', false);
						break;//System.out.println(acct1.balance+""); break;
						
						default: JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE); continue;
					}
					//Need to work on method //printTransaction(custNumber, Double.parseDouble(inputLine[2]), inputLine[1].charAt(0), inputLine[3].charAt(0), inputLine[4].charAt(0) );
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Input", "Incorrect Input", JOptionPane.ERROR_MESSAGE); continue;
				}
				String contValue = "";
				while(!contValue.equals("n") && !contValue.equals("N") && !contValue.equals("Y") && !contValue.equals("y"))
				{
					//Show a dialog asking the user whether to continue to another transaction or not:
					contValue = JOptionPane.showInputDialog("Would you like to input another transaction? (Y/N)");
				}
				if(contValue.equals("N") || contValue.equals("n"))
				{
					cont = false;
					for(int r = 0; r < bank.length; r++)
					{
							System.out.println("Customer " + (r) +" Bank Account Summary:");
							System.out.println("Checking Account: "+ bank[r][0].getBalance());
							System.out.println("Primary Savings Account: "+ bank[r][1].getBalance());
							System.out.println("Student Loan Savings Account: "+ bank[r][2].getBalance());
							System.out.println("Auto Loan Savings Account: "+ bank[r][3].getBalance());
							System.out.print("\n");
					}
					
					
				}
				contValue = "";
			}
		}
	public static boolean isParsable(String input){
		    boolean parsable = true;
		    try{
		        Integer.parseInt(input);
		    }catch(NumberFormatException e){
		        parsable = false;
		    }
		    return parsable;
		}
	public static void printTransaction(int customer, double amount, char transaction, char account1, char account2, boolean fromSavings)
	{
		String accountOne = "";
		String accountTwo = "";
		switch(account1)
		{
			case 'C': accountOne = "Checking Account"; account1 = 0; break;
			case 'S': accountOne = "Primary Savings Account"; account1 = 1;break;
			case 'L': accountOne = "Student Loan Savings Account"; account1 = 2;break;
			case 'A': accountOne = "Auto Loan Savings Account"; account1 = 3;break;
		}
		switch(account2)
		{
			case 'Z': break;
			case 'C': accountTwo = "Checking Account"; account2 = 0;break;
			case 'S': accountTwo = "Primary Savings Account"; account2 = 1;break;
			case 'L': accountTwo = "Student Loan Savings Account"; account2 = 2;break;
			case 'A': accountTwo = "Auto Loan Savings Account"; account2 = 3;break;
		}
		System.out.print("Customer " + (customer) + " ");
		switch(transaction)
		{
		case 'D' : System.out.println("deposited $" + amount + " into their " + accountOne + " making their new balance: " + bank[customer][account1].getBalance() + ".");break;
		case 'W' : System.out.println("withdrew $" + amount + " from their " + accountOne + "making their new balance: " + bank[customer][account1].getBalance()+ ".");
			if(fromSavings) {
				System.out.println("\tCustomer " + customer+ "'s Primary Savings Account was withdrawn from to make up for deficit funds from Checking Account, incluuding a $20 overdraw fee. New balance of Customer " + customer + "'s Primary Savings Account is " + bank[customer][1].getBalance() + ".");
			}
			break;
		case 'I' : System.out.println("had $" + amount + " worth of interest applied to their " + accountOne+ ", making their new balance: " + (bank[customer][account1].getBalance()+amount)+ ".");break;
		case 'T' : System.out.println("transfered " + amount + " from their " + accountOne + " to their "+ accountTwo+ ". " + accountOne + " now has a balance of: " + + bank[customer][account1].getBalance()+ " and " + accountTwo +" now has a balance of: " + + bank[customer][account2].getBalance()+ "." );break;
		case 'G' : System.out.println("has $" + amount + " in their " + accountOne+ ".");
		}
	}

}
