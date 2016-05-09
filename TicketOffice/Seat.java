package assignment6;

public class Seat {
	private String letter;
	private int number;
	private boolean taken = false;
	
	public Seat(String letter, int number)
	{
		this.letter = letter;
		this.number = number;
	}
	
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	
}
