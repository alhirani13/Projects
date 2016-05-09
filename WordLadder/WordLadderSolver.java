/*
    ADD YOUR HEADER HERE
 */

package Assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    /*
     * delcare class members
     */
	public static HashMap<Character, ArrayList<String>> dict = new HashMap<Character, ArrayList<String>>();
    public static ArrayList<String> usedWord = new ArrayList<String>();
    public static ArrayList<String> solutionList = new ArrayList<String>();
    public static Queue<WordNode> queue = new LinkedList<WordNode>();
    public static LinkedList<WordNode> ladder = new LinkedList<WordNode>();
    public static boolean result = false;
    
    public WordLadderSolver(String filename) {
		buildDict(filename);
	}
  
    /*
     * create the dictionary using HashMap and ArrayList
     */
    public static void buildDict(String filename){
        BufferedReader reader;

        //List<String> wordLadder = new ArrayList<String>();
        try{
            FileReader freader = new FileReader(filename);
            reader = new BufferedReader(freader);
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                if (s.length()!=0 && s != "" && s.charAt(0) != '*' && s.length() >=5) {
                    String newWord = s.toLowerCase().substring(0, 5);
                    char c = newWord.charAt(0);
                    if (dict.containsKey(c)) {
                        dict.get(c).add(newWord);
                    } else {
                        ArrayList<String> newWordList = new ArrayList<String>();
                        newWordList.add(newWord);
                        dict.put(c, newWordList);
                    }
                }
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

    /*
     * create the top node of star word and create the node of possible word from the top node
     * @param   startWord   the start word of the ladder
     * @param   endWord     the end word of the ladder
     */
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
        //clear all global variable each time
        usedWord.clear(); solutionList.clear(); queue.clear(); ladder.clear(); result=false;
    	WordNode topNode = new WordNode(startWord, 0);
        usedWord.add(startWord);
        queue.add(topNode);
    	ladder.add(topNode);
        WordNode getWordNode = MakeLadder(queue.poll(), endWord);
        getSolutionList(getWordNode);
        //the solution is list is add from end word to the star word, need to reverse
        Collections.reverse(solutionList);
		return solutionList;
    }

    /*
     * validate the solution list
     * @param   startWord   the start word of the ladder
     * @param   endWord     the end word of the ladder
     * @param   wordLadder  the solution of word list of the ladder
     */
    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
    	if(wordLadder.get(0).equals(startWord)){
	    	for(int i = 0; i < wordLadder.size()-1; i++) {
	    		if(!oneLetterApart(wordLadder.get(i), wordLadder.get(i+1))) {
	    			return false;
	    		}
	    	}
	    	if(wordLadder.get(wordLadder.size()-1).equals(endWord)) {
	    		return true;
	    	}
	        return false;
    	}
    	return false;
    }

    public ArrayList<String> findPossibleWords(String word)
    {
    	ArrayList<String> possibleWords = new ArrayList<String>();
        char[] arr = word.toCharArray();
        //go through each letter in the word
        for(int i=0; i<arr.length; i++){
            //change a-z for each word
            for(char c='a'; c<='z'; c++){
                char temp = arr[i];
                arr[i] = c;
                String newWord = new String(arr);
                if(dict.containsKey(newWord.charAt(0))) {
                    //check and prevent using word repeatedly
                    if (!newWord.equals(word) && dict.get(newWord.charAt(0)).contains(newWord) && !usedWord.contains(newWord)) {
                        possibleWords.add(newWord);
                    }
                }
                else{
                    System.err.println("Error!");
                }
                arr[i]=temp;
            }
        }
    	return possibleWords;
    }

    /*
     *  check the word if is in the dictionary
     *  @param  word    the word need to be checked
     */
    public static boolean checkWord(String word){
    	return dict.get(word.charAt(0)).contains(word);
    }

    /*
     * use the recursion to create the path to the end word
     * once found, return the node of the end word
     * @param   startWord   the start word of the ladder
     * @param   endWord     the end word of the ladder
     */
    public WordNode MakeLadder(WordNode startWord, String endWord){
        WordNode getWordNode = new WordNode();
        //get the possible that are one letter different from the previous word
    	List<String> temp = findPossibleWords(startWord.word);
        usedWord.addAll(temp);
        //no possible word to go through
        if (temp.isEmpty()){
            return null;
        }
        //get the end word, no need to go to next recursion, create node for end word and return
        else if (temp.contains(endWord)){
            result = true;
            return new WordNode(endWord, startWord.numSteps + 1, startWord);
        }
        //create node for all the possible word and add them to the queue
        for(String word : temp){
            WordNode child = new WordNode(word, startWord.numSteps + 1, startWord);
            queue.add(child);
        }
        //pop the queue to go the next step
        while(!queue.isEmpty()){
            WordNode nextWord = queue.remove();
            getWordNode = MakeLadder(nextWord,endWord);
            if (result){
                return getWordNode;
            }
        }
        /*if(getWordNode == null){
            throw new UnsupportedOperationException("No word ladder can be found between the two word!");
        }*/
        return getWordNode;
        /*catch(NoSuchElementException e){
            System.err.println ("No word ladder find between the two word!");
            e.printStackTrace();
            System.exit(-1);
        }*/



    }

    /*
     * get the each word on the path to the final word by getting the parent of the node
     * @param node  the node of the endword
     */
    public void getSolutionList (WordNode node){
    	WordNode temp = new WordNode();
    	if (node == null) {
			return;
		}
    	else{
    		solutionList.add(node.word);
    		temp = node.parent;
    		getSolutionList(temp);
    	}
    	return;
    }

    public void printSolu(ArrayList<String> solutionList){
        for(String word : solutionList){
            System.out.println(word);
        }

    }

    /*
     * check if two word are one letter different
     * @param one  the first word
     * @param two   the second word
     */
    public boolean oneLetterApart(String one, String two)
    {
    	boolean different = false;
    	
    	for(int i = 0; i < one.length(); i++) {
    		if(one.charAt(i) != two.charAt(i)) {
    			if(different) {
    				return false;
    			}
    			different = true;
    		}
    	}
    	if(different){
    		return true;
    	}
    	return false;
    }

    /*
     * construct the node class
     */
    class WordNode	{
        String word;
        int numSteps;
        WordNode parent;

        public WordNode(){
            this.word = "";
            this.numSteps = 0;
            this.parent = null;
        }
        
        public WordNode(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
            this.parent = null;
        }

        public WordNode(String word,int numSteps, WordNode parent){
            this.word = word;
            this.numSteps = numSteps;
            this.parent = parent;
        }
    }

}