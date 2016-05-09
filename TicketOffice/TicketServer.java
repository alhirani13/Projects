package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer {
	
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;

	public static void start(int portNumber) throws IOException {
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer();
		Thread t = new Thread(serverThread);
		t.start();
	}	
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	//Has which row will have the next available seat
	static int rowSeat;
	static Seat[][] seating;
	
	public void run() {
		// TODO 422C
		ServerSocket serverSocket;
		try {
			rowSeat = 0;
			seating = new Seat[27][28];
			for(int i = 0; i < 26; i++)
			{
				for(int j = 0; j < 28; j++)
				{
					seating[i][j] = new Seat((String)((char)('A' + i)+""), 101+j);
					seating[26][j] = new Seat("AA", 101+j);
				}
			}
			serverSocket = new ServerSocket(TicketServer.PORT);
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				if(in.readLine().equals("requesting ticket"))
				{
					int j = -1;
					for(int i = 0; i < 27; i++)
					{
						j = bestAvailableSeat(seating[i]);
						if(j!=-1)
						{
							rowSeat = i;
							break;
						}
					}
					if(j!=-1)
					{
						markAvailableSeatTaken(seating[rowSeat][j]);
						writer.println(printTicketSeat(seating[rowSeat][j]));
					}
					else
					{
						writer.println("null");
						clientSocket.close();
					}
						
				}
				writer.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//When this method is called, pass the row of rowSeat as parameter
		//if this method returns -1, check to see is rowSeat < 27
		//if rowSeat is not < 27, add one to 27 and call method again
		public int bestAvailableSeat(Seat[] a) {
			if(a[a.length-1].isTaken())
				return -1;
			else{
				for(int i = 7; i < 21; i++)
				{
					if(!a[i].isTaken())
						return i;
				}
				for(int i = 0; i < 7; i++)
				{
					if(!a[i].isTaken())
						return i;
				}
				for(int i = 21; i < 28; i++)
				{
					if(!a[i].isTaken())
						return i;
				}
			}
			return -1;
		}
		
		public void markAvailableSeatTaken(Seat a)
		{
			a.setTaken(true);
		}
		
		public String printTicketSeat(Seat a)
		{
			return (String)(a.getLetter() + " " + a.getNumber());
		}
}



