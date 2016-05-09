/*Hirani, Altamshali
 * Assignment 5 - Java Cars
 * 422C - Perry
 * TA: JO (TH 9:30-11)
 */

//Questions for TA
//Do All cars have to be Burnt Orange?
//Do cars have to have numbers or can we do names?
//
import java.applet.Applet; 
import java.awt.*; 
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class JavaCars extends Applet implements ActionListener, MouseListener {
	
	 boolean won;
	 boolean timed = false;
	 Graphics2D g2;
	// Color[] colors = {Color.black, Color.lightGray, Color.blue, Color.magenta, Color.cyan, Color.orange, 
			 //Color.darkGray, Color.pink, Color.gray, Color.red, Color.green, Color.yellow};
	 Color burntOrange = new Color(191, 87, 0);
	 int screenWidth = 1350;
	 int screenHeight = 650;
	 int carBodyWidth = 100;
	 int carBodyHeight = 40;
	 Car[] cars;
	 boolean initialized = false;
	 boolean raceStart = false;
	 RoundRectangle2D restart = new RoundRectangle2D.Double(1195, 5, 150, 55, 10, 10);
	 RoundRectangle2D start = new RoundRectangle2D.Double(5, 5, 150, 55, 10, 10);
	 BufferedImage img;
	
	 Graphics2D bufferGraphics;
	 Image offScreen; 
	 Timer updateTimer;
	 
	 StopWatch watch = new StopWatch();
	 
	 public void init(){
		 img = null;
		 try {
		     img = ImageIO.read(new File("C:\\Users\\AL Hirani\\Desktop\\EE 422C\\Assignment 5\\racetrack.jpg"));
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
		 }
		
		addMouseListener(this);
		setBackground(Color.WHITE);
		offScreen = createImage(screenWidth, screenHeight);
		bufferGraphics = (Graphics2D) offScreen.getGraphics();
		 // this.addActionListener(this);
		 updateTimer = new Timer(16, this);
		 updateTimer.start();
	 }
	 
	public void paint(Graphics g)    
	 {  
		
		bufferGraphics.clearRect(0, 0, screenWidth, screenHeight);
		 g2 = (Graphics2D)g;
		 Rectangle body;
		 Rectangle head;
		 Ellipse2D.Double frontTire, rearTire;
		 
		 bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 50));
		 bufferGraphics.setColor(Color.CYAN);
		 bufferGraphics.drawString("422C Kart", screenWidth/2-bufferGraphics.getFontMetrics().stringWidth("422C Kart")/2, 50);
		 
		 bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 25));
		 g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 25));
		 
		 bufferGraphics.setColor(Color.ORANGE);
		 bufferGraphics.fill(restart);
		 bufferGraphics.setColor(Color.WHITE);
		 bufferGraphics.drawString("Restart", (int)restart.getX() + (int)(.25*restart.getWidth()), (int)restart.getY() + (int)(1.5*bufferGraphics.getFont().getSize()));
		 
		 bufferGraphics.setColor(Color.GREEN);
		 bufferGraphics.fill(start);
		 bufferGraphics.setColor(Color.WHITE);
		 bufferGraphics.drawString("Start", (int)start.getX() + (int)(.32*start.getWidth()), (int)start.getY() + (int)(1.5*bufferGraphics.getFont().getSize()));
		 
		 
		 
		 for(int a = 0; a < 5; a++)
			 for(int b = 0; b < 5; b++){
				 bufferGraphics.drawImage(img,b*img.getWidth(),a*100 +70,this);
			 }
		 
		 
		 if(!initialized)
		 {
			 this.setSize(screenWidth, screenHeight);
			 cars = new Car[5];
			 won = false;
			 
			 for(int i = 0; i < cars.length; i++)
			 {
				 cars[i] = new Car(0, i*100 + 100, burntOrange, (int) (Math.random()*6) +1, ""  );
			 }
			 cars[0].setName("Mario"); cars[0].setColor(Color.RED);
			 cars[1].setName("Luigi"); cars[1].setColor(burntOrange);
			 cars[2].setName("Peach"); cars[2].setColor(Color.PINK);
			 cars[3].setName("Wario"); cars[3].setColor(new Color(160,32,240));
			 cars[4].setName("Yoshi"); cars[4].setColor(Color.GREEN);
			 initialized = true;
		 }
		 else  {
			 for(int i = 0; i < cars.length; i++)
			 {
				 cars[i].setSpeed((int)(Math.random()*7));
				 if(!won && raceStart)
					 cars[i].setX(cars[i].getX() + cars[i].getSpeed());
				 
				 if(cars[i].getX() >= (screenWidth-carBodyWidth))
				 {
					 watch.stop();
					 g2.drawString("Race Time: " + ((double)watch.getElapsedTime()/1000)+ " seconds", screenWidth/2 - g2.getFont().getSize()-100, screenHeight - g2.getFont().getSize()+25);
					 watch.reset();
					 cars[i].setX(screenWidth-carBodyWidth+50);
					 g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 50));
					 g2.setColor(Color.blue);
					 g2.drawString("Winner: " + cars[i].getName() + "!", screenWidth/2 - g2.getFont().getSize()-100, screenHeight - g2.getFont().getSize()+25);
					 won = true;
					 //updateTimer.stop();
					 break;
				 }
			 }
		 }
		 //Where car drawing happens
		 for(int i = 0; i < cars.length; i++){
			 body = new Rectangle(cars[i].getX(), cars[i].getY(), carBodyWidth, carBodyHeight); 
			 head = new Rectangle(cars[i].getX()+(int)carBodyWidth/4, cars[i].getY()-15, (int)carBodyWidth/2, (int) carBodyHeight/2);
			 rearTire = new Ellipse2D.Double(cars[i].getX()+ carBodyWidth/10, cars[i].getY()+carBodyHeight-2, 25, 25);       
			 frontTire   = new Ellipse2D.Double(cars[i].getX()+ carBodyWidth/1.5, cars[i].getY()+carBodyHeight-2, 25, 25); 
			 
			 bufferGraphics.setColor(cars[i].getColor());
			 bufferGraphics.fill(body);
			 bufferGraphics.fill(head);
			 bufferGraphics.setColor(Color.BLACK);
			 bufferGraphics.fill(rearTire);
			 bufferGraphics.fill(frontTire);
			 bufferGraphics.setColor(Color.WHITE);
			 bufferGraphics.drawString(cars[i].getName(), cars[i].getX()+(int)(carBodyWidth/5.5), cars[i].getY()+(int)(carBodyHeight/1.5));
		 }
		
		 if(!won){
			g2.drawImage(offScreen, 0, 0, this);
		 }
		 
		// g2.fill(body);
		 	
	 }
	
	public void update(Graphics g)
	{
		if(!won)
			paint(g);
	}
	public void actionPerformed(ActionEvent e) {
		if(!won)
			repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX() <= restart.getX() + restart.getWidth() && e.getX() >= restart.getX())
		{
			if(e.getY() <= restart.getY() + restart.getHeight() && e.getY() >= restart.getY())
			{
				won = false;
				initialized = false;
				raceStart = false;
				timed = false;
				watch.reset();
			}
		}
		if(e.getX() <= start.getX() + start.getWidth() && e.getX() >= start.getX())
		{
			if(e.getY() <= start.getY() + start.getHeight() && e.getY() >= start.getY())
			{
				if(!won){
					raceStart = true;
					watch.start();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
/*// create the car body       
		 Rectangle body = new Rectangle(100, 110, 60, 10);             
		 
		 // create the car tires       
		 Ellipse2D.Double frontTire = new Ellipse2D.Double(110, 120, 10, 10);       
		 Ellipse2D.Double rearTire   = new Ellipse2D.Double(140, 120, 10, 10);  
		 
		 // create the 4 points connecting the windshields and roof       
		 Point2D.Double r1 = new Point2D.Double(110, 110);       
		 Point2D.Double r2 = new Point2D.Double(120, 100);       
		 Point2D.Double r3 = new Point2D.Double(140, 100);       
		 Point2D.Double r4 = new Point2D.Double(150, 110);  
		 
		 // create the windshields and roof of the car       
		 Line2D.Double frontWindshield  = new Line2D.Double(r1, r2);       
		 Line2D.Double roofTop = new Line2D.Double(r2, r3);       
		 Line2D.Double rearWindshield  = new Line2D.Double(r3, r4);  
		 
		 // draw  all of the car parts on the screen           
		 g2.draw(body);       
		 g2.draw(frontTire);       
		 g2.draw(rearTire);       
		 g2.draw(frontWindshield);             
		 g2.draw(roofTop);             
		 g2.draw(rearWindshield);
*/
