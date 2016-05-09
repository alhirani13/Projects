package Assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assign4Driver
{
    private static final String DICT_FILE = "A4words.dat";
    private static final String WORD_FORM = "[a-z]{5}";
    private static final String VALID_INPUT = String.format(" *%s +%s *", WORD_FORM, WORD_FORM);
    public static void main(String[] args) throws Exception {

        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver("A4words.dat");
        String filename = args[0];
        System.out.println("*********");
        try {
        	//WordLadderSolver.buildDict(DICT_FILE);
            BufferedReader reader;
            FileReader freader = new FileReader(filename);
            reader = new BufferedReader(freader);

            // read lines from file
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                long startTime = System.currentTimeMillis();
                try {
                    //check the format of input line
                    if(!s.matches(VALID_INPUT)) {
                        throw new InvalidInputException("The input format is invalid.");
                    }
                    else{
                        String[] inputWords = s.split(" ");
                        System.out.println(inputWords[0]+"--->"+ inputWords[1]);
                        //neither two words is in the dict
                        if(!WordLadderSolver.checkWord(inputWords[0]) && !WordLadderSolver.checkWord(inputWords[1])){
                            throw new InvalidInputException("Both words cannot be found in the dictionary!");
                        }
                        if(!WordLadderSolver.checkWord(inputWords[0])){
                            throw new InvalidInputException("Start word cannot be found in the dictionary!");
                        }
                        if(!WordLadderSolver.checkWord(inputWords[1])){
                            throw new InvalidInputException("Start word cannot be found in the dictionary!");
                        }
                        if(inputWords[0].equals(inputWords[1])){
                            throw new InvalidInputException("The end word is the same to the start word, no ladder needed!");
                        }
                        List<String> wordLadder = wordLadderSolver.computeLadder(inputWords[0], inputWords[1]);
                        //no ladder get
                        if (wordLadder.size() == 0){
                            throw new NoSuchLadderException("No word ladder founded!");
                        }
                        //two word are one letter different, no ladder needed
                        else if (wordLadder.size() == 2){
                            throw new NoSuchLadderException("No word ladder needed between the words!");
                        }
                        //print the solution ladder
                        else{
                        	for (String word : wordLadder){
                                System.out.println(word);
                            }
                        }
                        //boolean correct = wordLadderSolver.validateResult(inputWords[0], inputWords[1], wordLadder);
                        //System.out.println("The word ladder is " + correct);
                    }
                }
                catch(InvalidInputException e){
                    //System.setOut(System.err);
                    System.setErr(System.out);
                    System.err.println(e.getMessage());
                   // System.out.println("Invalid Input Exception Thrown");
                    //throw new Exception("Invalid input!");
                }
                catch(NoSuchLadderException e){
                    //System.setOut(System.err);
                    System.setErr(System.out);
                    System.err.println(e.getMessage());
                }
                long endTime   = System.currentTimeMillis();
                double totalTime = endTime - startTime;
                System.out.println(totalTime);
                System.out.println("*********");
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.err.println ("Error: File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
        catch (IOException e)
        {
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    

   
    
}