/*Hirani, Altamshali - AH45675
 */
import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.Scanner;
public class Mastermind {

	static String[] colors1 = {"B", "G", "O", "P", "R", "Y"};
	static String[] colors2 = {"B", "G", "O", "P", "R", "Y", "W", "M", "V"};
	static String[] colors3 = {"B", "G", "O", "P", "R", "Y", "W", "M", "V", "N", "K", "S"};
	static String[] colors;
	static int pegs;
	static String code = "";
	static int guesses;
	static String colorLetters = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner fred = new Scanner(System.in);
		System.out.print("Welcome to Mastermind.  Here are the rules. \n"
				+ "\nThis is a text version of the classic board game Mastermind. The computer will think of a secret code. "
				+ "\nThe code consists of a number, which you choose, of colored pegs. "
				+ "\nThe pegs may be one of the following colors: blue, green, orange, pink, red, or yellow, white, maroon, violet, navy, khaki, or silver. "
				+ "\nA color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.   "
				+ "\nAfter you make a valid guess the result (feedback) will be displayed. The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.  "
				+ "\nFor each peg in the guess that is the correct color, but is out of position, you get a white peg.  For each peg, which is fully incorrect, you get no feedback.  Only the first letter of the color is displayed. B for Blue, R for Red, and so forth. "
				+ "\nWhen entering guesses you only need to enter the first character of each color as a capital letter. "
				+ "\n\nYou have 12 guesses to figure out the secret code or you lose the game.  Are you ready to play? (Y/N): "
				);
		while(!fred.hasNext()){}
		while(fred.next().toLowerCase().equals("y"))
		{
			String input = "";
			colorLetters="";
			pegs = getInput(fred, "How long of a code would you like to play with? (1-10): ", 10);
			guesses = getInput(fred, "How many guesses would you like to have? (1-20): ", 20);
			int temp1 = getInput(fred, "How many colored pegs would you like to play with (Type 1 for 6 colors, Type 2 for 9 colors, Type 3 for 12 colors): ", 3);
			switch(temp1)
			{
				case 1: colors = colors1; break;
				case 2: colors = colors2; break;
				case 3: colors = colors3; break;
			}
			for(String s : colors)
			{
				colorLetters += s+", ";
			}
			colorLetters = colorLetters.substring(0,colorLetters.length()-2);
			System.out.println("\nGenerating secret code .... ");
			code = makeCode(pegs);
			//System.out.println(code);
			for(int i = guesses; i > 0; i--)
			{
				System.out.println("\n\nYou have " + i + " guesses left. "
						+ "\nWhat is your next guess? "
						+ "\nType in the characters (i.e. " + colorLetters + ") for your guess and press enter. "
						+ "\nEnter guess (Upper Case Letters Only): "
						);
				while(!fred.hasNext()){}
				input = fred.next();
				input = checkCodeInput(fred, input, pegs);
				boolean temp = matchFinder(input, code);
				if(temp)
				{
					System.out.print("\n\nAre you ready for another game (Y/N): ");
					while(!fred.hasNext()){}
					break;
				}
				else if(i == 1)
				{
					System.out.print("\n\nYou did not guess "+ code + " correctly with in the number of guesses provided. "
							+ "\nYou have been outsmarted at Mastermind. "
							+ "\nAre you ready for another game (Y/N): ");
					while(!fred.hasNext()){}
				}
			}
		}
		System.out.println("\n\nSee you next time! Come back when you're not afraid of a challenge!");
	
	}
	public static String makeCode(int pegs) {
		String code = "";
		for(int i = 0; i < pegs; i++)
		{
			code = code + colors[(int)(Math.random()*colors.length)];
		}
		return code;
	}
	public static boolean matchFinder(String input, String code)
	{
		System.out.print(input + " --> Result:  ");
		int blackPegs = 0;
		int whitePegs = 0;
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == code.charAt(i))
			{
				blackPegs++;
				input = input.substring(0, i) + "-" + input.substring(i+1);
				code = code.substring(0, i) + "-" + code.substring(i+1);
			}
		}
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) != '-')
			{
				if(code.indexOf(input.charAt(i)) != -1)
				{
					whitePegs++;
					int index = code.indexOf(input.charAt(i));
					code = code.substring(0, index) + "-" + code.substring(index+1);				
				}
			}
		}
		if(blackPegs == code.length())
		{
			System.out.println(code.length() + " black pegs – You win !! ");
			return true;
		}
		else if(blackPegs != 0 && whitePegs != 0)
			System.out.println(blackPegs + " black pegs and " +whitePegs + " white pegs ");
		else if (blackPegs != 0)
			System.out.println(blackPegs + " black pegs");
		else if(whitePegs != 0)
			System.out.println( whitePegs + " white pegs ");
		else if(whitePegs == 0 && blackPegs == 0)
			System.out.println("No Pegs");
		return false;
	}
	public static int getInput(Scanner fred, String output, int max)
	{
		System.out.print("\n" + output);
		while(!fred.hasNext()){}
		String input = fred.next();
		int num = 0;
		while(true){
			//boolean cont = true;
			try  
			{  
				num = Integer.parseInt(input);
			}  
			catch(NumberFormatException nfe)  
			{
				System.out.print("\nINVALID INPUT. " + output);
				while(!fred.hasNext()){}
				input = fred.next();
				continue;
			}
			if(num <= 0 || input.length() > 2 || num > max) //|| fred.hasNext())
			{
				System.out.print("\nINVALID INPUT. " + output);
				while(!fred.hasNext()){}
				input = fred.next();
			}
			else
				return num;
		}
		/*if(!Character.isDigit(input.charAt(0)) || !(input.length() == 1) || input.equals("0"))
		{
			while(!Character.isDigit(input.charAt(0)) || !(input.length() == 1) || input.equals("0"))
			{
				System.out.print("\nINVALID INPUT. " + output);
				while(!fred.hasNext()){}
				input = fred.next();
			}
		}
		*/
		//return num;
	}
	public static String checkCodeInput(Scanner fred, String input, int len)
	{
		boolean cont = false;
		while(!cont){
			cont = true;
			int temp = input.length();
			if(temp != len )
			{
				cont = false;
			}
			else if (cont)
			{
				for(char c : input.toCharArray())
				{
					if(colorLetters.indexOf(c) ==  -1)
					{
						cont = false;
						break;
					}
				}
			}
			if(!cont)
			{
				System.out.println("\nINVALID GUESS"
						+ "\nWhat is your next guess? "
						+ "\nType in the characters (i.e. " + colorLetters + ") for your guess and press enter. "
						+ "\nEnter guess (Upper Case Letters Only): "
						);
				while(!fred.hasNext()){}
				input = fred.next();
				
			}
			
			//if( )
		}
		return input;
	}
}
