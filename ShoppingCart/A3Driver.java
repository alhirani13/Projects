package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver {
	
	//array of operation strings to use when parsing input
	static String [] states = new String[] {
		"ak", "al", "ar", "az", "ca", "co", "ct", "dc", "de", 
		"fl", "ga", "hi", "ia", "id", "il", "in", "ks", "ky", 
		"la", "ma", "md", "me", "mi", "mn", "mo", "ms", "mt", 
		"nc", "nd", "ne", "nh", "nj", "nm", "nv", "ny", "oh", 
		"ok", "or", "pa", "ri", "sc", "sd", "tn", "tx", "ut", 
		"va", "vt", "wa", "wi", "wv", "wy"};
	
	static String [] operations = new String []{
		"insert", "search", "delete", "update",
		"print"
	};
	
	static String [] category = new String []{
		"groceries", "electronics", "clothing"	
	};
	
	static String [] perish = new String []{
			"p", "np"
	};
	
	static String [] fragility = new String []{
			"f", "nf"
	};
	//array of the states that have no sales tax that
	//is used when parsing input
	static String [] taxless = new String []{
		"tx", "nm", "va", "az", "ak"
	};
	static void invalidTran(int line){
		System.out.format("Invalid transaction at line %d\n", line);
	}
	
	//test to see if string only contains characters from alphabet
	static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z0-9]+");
	}
	
	//tests to see if characters resolve to an integer
	public static boolean isInteger(String str) {
		if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    for (int i = 0; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	//tests to see if the cost in the transaction string is either 
	//a float or integer
	static boolean goodAmount(String prospect){
		if(prospect.contains(".")){
			String [] checkdec = prospect.split("[.]");
			if(checkdec.length > 2){
				return false;
			}
			for(int i = 0; i < checkdec.length; i++){
				if(!isInteger(checkdec[i]) ){
					return false;
				}
			}
			return true;
		}else{
			if(isInteger(prospect)){
				return true;
			}else return false;
		}
	}
	//Puts item in correct spot in ArrayList dependent on Alpha order based on Item Name
	static void sortObjectIn(Item item, ArrayList<Item> shoppingCart)
	{
		for(int i = 0; i < shoppingCart.size(); i++)
		{
			int compare = shoppingCart.get(i).getName().toLowerCase().compareTo(item.getName().toLowerCase());
			if(compare == 0 || compare >= 1)
			{
				shoppingCart.add(i, item);
				//System.out.println("I am putting in "+ item.getName());
				return;
			}
			else if(shoppingCart.size()-1 == i)
			{
				shoppingCart.add(item);
				//System.out.println("I am putting in "+ item.getName());
				return;
			}
		}
		shoppingCart.add(item);
		//System.out.println("I am putting in "+ item.getName());
	}
	//method that handles insert operation
	static void doInsert(String [] tran, int line,  ArrayList<Item> shoppingCart){
		if(!isMember(category, tran[1])){
			invalidTran(line);
			return;
		}
		float initAmount;
		int initQuan, initWeight;
		Item item;
		
		
		if(tran[1].equals("groceries")){
			//check to see if the transaction is in a 
			//valid form for groceries
			if(tran.length != 7){
				invalidTran(line);
				return;
			}
			//makes sure the third token is a name
			if(!isAlpha(tran[2])){
				invalidTran(line);
				return;
			}
			//tests if 4th token is a float or integer
			if(!goodAmount(tran[3])){
				invalidTran(line);
				return;
			}
			initAmount = Float.parseFloat(tran[3]);
			//test if 5th and 6th token are integers
			if(!isInteger(tran[4]) || !isInteger(tran[5])){
				invalidTran(line);
				return;
			}
			initQuan = Integer.parseInt(tran[4]);
			initWeight = Integer.parseInt(tran[5]);
			if(!isMember(perish, tran[6])){
				invalidTran(line);
				return;
			}
			item = new Grocery(tran[2], initAmount, initQuan, initWeight, tran[6].toUpperCase());
			sortObjectIn(item, shoppingCart);
			return;
			
		}else if(tran[1].equals("electronics")){
			//check to see if the transaction is in a 
			//valid form for electronics
			if(tran.length != 8){
				invalidTran(line);
				
				return;
			}
			//makes sure the third token is a name
			if(!isAlpha(tran[2])){
				invalidTran(line);
				
				return;
			}
			//tests if 4th token is a float or integer
			if(!goodAmount(tran[3])){
				invalidTran(line);
				
				return;
			}
			initAmount = Float.parseFloat(tran[3]);
			//test if 5th and 6th token are integers
			if(!isInteger(tran[4]) || !isInteger(tran[5])){
				invalidTran(line);
				return;
			}
			initQuan = Integer.parseInt(tran[4]);
			initWeight = Integer.parseInt(tran[5]);
			
			//test if 7th token is fragile or non fragile
			if(!isMember(fragility, tran[6])){
				invalidTran(line);
				return;
			}
			//test if 8th token could be a state
			if(!isAlpha(tran[7]) && !isMember(states, tran[7])){
				invalidTran(line);
				return;
			}
			item = new Electronics(tran[2], initAmount, initQuan, initWeight, tran[6].toUpperCase(), tran[7].toUpperCase());
			sortObjectIn(item, shoppingCart);
			return;
			
		}else if(tran[1].equals("clothing")){
			//check to see if the transaction is in a 
			//valid form for clothing
			if(tran.length != 6){
				invalidTran(line);
				return;
			}
			if(!isAlpha(tran[2])){
				invalidTran(line);
				return;
			}
			//tests if 4th token is a float or integer
			if(!goodAmount(tran[3])){
				invalidTran(line);
				return;
			}
			initAmount = Float.parseFloat(tran[3]);
			//test if 5th and 6th token are integers
			if(!isInteger(tran[4]) || !isInteger(tran[5])){
				invalidTran(line);
				return;
			}
			initQuan = Integer.parseInt(tran[4]);
			initWeight = Integer.parseInt(tran[5]);
			item = new Clothing(tran[2], initAmount, initQuan, initWeight);
			sortObjectIn(item, shoppingCart);
			return;
		}
	}
	
	//method that handles insert operation
	static void doSearch(String [] tran, int line, ArrayList<Item> cart){
		if(tran.length == 2 && isAlpha(tran[1]))
		{
			int spot = cart.size()-1;
			String item = tran[1];
			int found = 0;
			while(spot >= 0)
			{
				if(cart.get(spot).getName() == item)
				{
					found += cart.get(spot).getQuantity();
				}
					spot--;
			}
			System.out.println("Searched & Found " + found + " many occurances of " + item + "." );
		}
		else
		{
			invalidTran(line);
		}
	}
	
	//method that handles insert operation
	static void doDelete(String [] tran, int line, ArrayList<Item> cart){
		if(tran.length == 2 && isAlpha(tran[1]))
		{
			int spot = cart.size()-1;
			String item = tran[1];
			int deleted = 0;
			while(spot >= 0)
			{
				if(cart.get(spot).getName().equals(item))
				{
					cart.remove(spot);
					deleted++;
				}
					spot--;
			}
			System.out.println("Searched & deleted " + deleted + " many occurances of " + item + "." );
		}
		else
		{
			invalidTran(line);
		}
	}
	
	//method that handles insert operation
	static void doUpdate(String [] tran, int line, ArrayList<Item> cart){
		if(tran.length == 3 && isAlpha(tran[1]) && isInteger(tran[2]))
		{
			int spot = cart.size();
			int temp = 0;
			String item = tran[1];
			int quant = Integer.parseInt(tran[2]);
			while(temp < spot)
			{
				if(cart.get(temp).getName().equals(item))
				{
					cart.get(temp).setQuantity(quant);
				}
				temp++;
				break;
			}
			System.out.println("Searched & Updated quantity of " + item + " to "+ quant + "." );
		}
		else
		{
			invalidTran(line);
		}
	}
	
	//method that handles insert operation
	static void doPrint(String [] tran, int line, ArrayList<Item> cart){
		if(tran.length == 1)
		{
			double cartTotal = 0;
			for(Item item : cart)
			{
				cartTotal+= item.calculatePrice();
				item.printItemAttributes();
			}
			System.out.printf("The total for the shopping cart, including tax and shipping costs, is $%.2f.\n", cartTotal);
		}
	}
	
	//tests to see if a String next is a member of stringArray
	public static boolean isMember(String [] stringArray, String next){
		for(int i = 0; i < stringArray.length; i++){
			if (stringArray[i].equals(next.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	//handles one transaction
	public static void transaction(String tran, int line, ArrayList<Item> shoppingCart){
		//breaks up the line (separating by spaces from file
		// into separate words that will be parsed
		String[] tokens = tran.split(" +");
		if(!isMember(operations, tokens[0])){
			invalidTran(line);	
		}
		if(tokens[0].equals("insert")){
			doInsert(tokens, line, shoppingCart);
		}else if(tokens[0].equals("search")){
			doSearch(tokens, line, shoppingCart);
		}else if(tokens[0].equals("delete")){
			doDelete(tokens, line, shoppingCart);
		}else if(tokens[0].equals("update")){
			doUpdate(tokens, line, shoppingCart);
		}else if(tokens[0].equals("print")){
			doPrint(tokens, line, shoppingCart);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>();
		//used in transaction routine to say what line of the input
		//file is put in bad input for a transaction
		int whatLine = 1;
		// Open file; file name specified in args (command line)
		//args[0] = "shoppingCart.txt";
		try{
			FileReader freader = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(freader);
			// Parse input, take appropriate actions.
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				transaction(s, whatLine, shoppingCart);
				whatLine += 1;
			}
		}
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		// Parse input, take appropriate actions.


		// General code example for how to iterate an array list. You will have
		// to modify this heavily, to suit your needs.
		/*Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) {
			Item temp = i.next();
			temp.calculatePrice();
			temp.printItemAttributes();
			// This (above) works because of polymorphism: a determination is
			// made at runtime,
			// based on the inherited class type, as to which method is to be
			// invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined
			// in Grocery.
		
		}
		*/
	}

}
