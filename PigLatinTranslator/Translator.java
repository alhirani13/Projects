/* Hirani, Altamshali
 * AH45675
 * EE 422C - Assignment 1
 */

package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.*;

public class Translator 
{
	
	public static void main (String args[]) 
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile (args[0]);
		
	}

	/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String pigLatin = translator.translate(s);
				System.out.println(pigLatin);
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
	}
	
	/******************************************************************************
	* Method Name: translate                                                      *
	* Purpose: Converts String inputString into piglatin based on rules specified *
	*          in your assignment write-up.                                       *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	
	public String translate (String inputString) 
	{ 
		String outputString = new String(inputString); // Copies input to output and prints it. 
		String[] line = outputString.split(" ");
		outputString = "";
		
		String begPunc = "";
		String endPunc = "";
		char firstChar = 0;
		for(String s : line)
		{
			if(s.length() == 0)
			{
				continue;
			}
			for(int i = s.length()-1; i >= 0; i--)
			{
				char spotEnd = s.charAt(i) ;
				if(spotEnd == '(' || spotEnd == ')' || spotEnd == '.' || spotEnd == '!' || spotEnd == '?' || spotEnd == ';' || spotEnd == ':' || spotEnd == ',')
				{
					
				}
				else
				{
					endPunc = s.substring(i+1);
					s = s.substring(0,i+1);
					break;
				}
			}
			boolean charac = false;
			boolean outPrint = false;
			String stringTemp = "";
			for(int i = 0; i < s.length(); i++){
				char temp = s.charAt(i);
				if( temp == '(' || temp == ')' || temp == '.' || temp == '!' ||temp == '?' ||temp == ';' ||temp == ':' || temp == ',')
				{
					if(charac)
					{
						outputString += s + " " + endPunc;
						outPrint = true;
						break;
					}
				}
				else if(s.toUpperCase().charAt(i) >= 'A' && s.toUpperCase().charAt(i) <= 'Z' )
				{
					if(charac != true)
					{
						charac = true;
						stringTemp = s.substring(i);
						begPunc = s.substring(0,i);
					}
					else if(i == s.length()-1)
					{
						s= stringTemp;
					}
				}
				else if(temp != 39)
				{
					outputString += s + " " + endPunc;
					outPrint = true;
					break;
				}
			}
			firstChar = s.toUpperCase().charAt(0);
			if(s.indexOf("-") != -1)
			{
				outPrint = false;
				outputString = outputString.substring(0,outputString.length()-s.length()-1);
				for(String h : s.split("-"))
				{
					String q = translate(h);
					q = q.substring(0, q.indexOf(" "));
					outputString += q + "-";
				}
				outputString = outputString.substring(0, outputString.length()-1);
			}
			else if(!outPrint && firstChar == 65 || firstChar == 69 || firstChar == 73 || firstChar == 79 || firstChar == 85)
			{
				outputString += s + "yay";
			}
			else if( !outPrint && firstChar >= 65 && firstChar <= 90 || firstChar == 39)
			{
				int totalCons = 0;
				char tempChar = s.toUpperCase().charAt(0);
				if(tempChar == 'Y')
				{
					totalCons++;
					tempChar = s.toUpperCase().charAt(1);
				}
				while(tempChar != 65 && tempChar != 69 && tempChar != 73 && tempChar != 79 && tempChar != 85 && tempChar != 89)
				{
					
					totalCons++;
					if(s.length() <= totalCons)
					{
						break;
					}
					tempChar = s.toUpperCase().charAt(totalCons);
				}
				if(s.charAt(totalCons-1) == 39)
				{
					totalCons--;
				}
				outputString += s.substring(totalCons, s.length()) + s.substring(0, totalCons) + "ay";
			}
			if(!outPrint && outputString.lastIndexOf(" ") != -1)
			{
				outputString = outputString.substring(0, outputString.lastIndexOf(" ")) + begPunc + outputString.substring(outputString.lastIndexOf(" "), outputString.length()) + endPunc + " ";
			}
			else if(!outPrint)
			{
				outputString =  begPunc + outputString + endPunc + " ";

			}
			begPunc = "";
			endPunc = "";
			outPrint = false;
		}
		return outputString;
	}
	
}
/*String[] lineTwo = s.split("[.)(:;!?]");
int spot = 0;
for( int i = 0; i < lineTwo.length; i++)
{
	if(lineTwo[i].length() != 0)
	{
		lineTwo[spot] = lineTwo[i];
		spot++;
	}
}
if( spot > 1 && lineTwo[1].toUpperCase().charAt(0) > 'A' && lineTwo[1].toUpperCase().charAt(0) < 'Z')
{
	outputString += s + " ";
}
else
{
	begPunc = s.substring(0, s.indexOf(lineTwo[0]));
	endPunc = s.substring(s.indexOf(lineTwo[0]) + lineTwo[0].length());
	s =  lineTwo[0];
	System.out.println(s);
}
/*
//System.out.println(s);
//String[] lineTwo = s.split("\\w");
if(lineTwo.length > 0)
{
	begPunc = lineTwo[0];
}
if(lineTwo.length > 1)
{
	endPunc = lineTwo[lineTwo.length-1];
}
if(begPunc != "" )
{
	if(endPunc != "")
		{
		s= s.substring(begPunc.length(), s.indexOf(endPunc));
		}
	else
	{
		s= s.substring(begPunc.length(), s.length());
	}
}

/*for(String temp: lineTwo)
{
	System.out.println(temp);
}
/*char lastChar = s.charAt(s.length()-1); 
if( lastChar != 39 && lastChar < 48 || (lastChar > 57 && lastChar < 65) || (lastChar > 90 && lastChar < 97) || lastChar > 122 )
{
	begPunc = lastChar + "";
	s = s.substring(0, s.length()-1);
}
else
{
	begPunc = "";
}
/*for(char q: s.toCharArray())
{
	if( q < 65 || (q > 90 && q < 97) || q > 122)
	{
		outputString += s + " ";
		break;
	}
}
*/

