package Assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import Assignment4.WordLadderSolver;

public class WordLadderTestCase {
	
	public Assignment4Interface junitTestCase = null;
	List<String> wordList = new ArrayList<>();
	String startWord;
	String endWord;

	@Before
	public void setUp() throws Exception {
		junitTestCase = new WordLadderSolver("A4words.dat");
	}
	
	
	/*
	 * test the validation of the ladder
	 */
	@Test
	public void testValidateGood() throws NoSuchLadderException{
		/*
		 * good ladder
		 */
		wordList.clear();
		wordList.add("heads");
		wordList.add("heals");
		wordList.add("teals");
		wordList.add("tells");
		wordList.add("tolls");
		wordList.add("toils");
		wordList.add("tails");
		assertTrue(junitTestCase.validateResult(wordList.get(0), wordList.get(wordList.size()-1), wordList));
	}
	
	@Test
	public void testValidateBad1() throws NoSuchLadderException{
		/*
		 * bad ladder two or more letter different
		 */
		wordList.clear();
		wordList.add("heads");
		wordList.add("teals");//two letter change
		wordList.add("tells");
		wordList.add("tolls");
		wordList.add("toils");
		wordList.add("tails");
		assertFalse(junitTestCase.validateResult(wordList.get(0), wordList.get(wordList.size()-1), wordList));
	}
	
	public void testValidateBad2() throws NoSuchLadderException{
		wordList.clear();
		wordList.add("heads");
		wordList.add("tells");//gap here
		wordList.add("tolls");
		wordList.add("toils");
		wordList.add("tails");
		assertFalse(junitTestCase.validateResult(wordList.get(0), wordList.get(wordList.size()-1), wordList));
		
		
	}
	
	/*
	 * test two good word ladders
	 */
	@Test
	public void testComputeLadderGood1() throws NoSuchLadderException{
		startWord = "heads";
		endWord = "tails";
		wordList = junitTestCase.computeLadder(startWord, endWord);
		assertTrue(junitTestCase.validateResult(startWord, endWord, wordList));
		
	}
	
	@Test
	public void testComputeLadderGood2() throws NoSuchLadderException{
		startWord = "heads";
		endWord = "tails";
		wordList = junitTestCase.computeLadder(startWord, endWord);
		assertTrue(junitTestCase.validateResult(startWord, endWord, wordList));
	}
	
	/*
	 * test two words with no ladders
	 */
	@Test
	public void testComputeLadderBad1() throws NoSuchLadderException{
		startWord = "altas";
		endWord = "zebra";
		assertFalse(junitTestCase.computeLadder(startWord, endWord) == null);
	}
	
	/*
	 * test two words not illegal
	 */
	@Test
	public void testComputeLadderBad2() throws NoSuchLadderException{
		startWord = "ryan";
		endWord = "joe";
		assertFalse(junitTestCase.computeLadder(startWord, endWord) == null);
	}
}