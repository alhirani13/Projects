/*Hirani, Altamshali
 * Assignment 5 - Java Cars
 * 422C - Perry
 * TA: JO (TH 9:30-11)
 */ 
import java.awt.*;
 
public class Car {
	
	private int x;
	private int y;
	private Color color;
	private int speed;
	private String name;
	
	public Car(int xLoc, int yLoc, Color col, int spd, String nm)
	{
		
		x = xLoc; y = yLoc;
		color = col;
		speed = spd;
		name = nm;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
