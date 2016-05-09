package assignment6;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;
/*
	@Test
	public void basicServerTest() {
		try {
			TicketServer.start(16789);
		} catch (Exception e) {
			fail();
		}
		TicketClient client = new TicketClient();
		client.requestTicket();
	}

	@Test
	public void testServerCachedHardInstance() {
		try {
			TicketServer.start(16790);
		} catch (Exception e) {
			fail();
		}
		TicketClient client1 = new TicketClient("localhost", "c1");
		TicketClient client2 = new TicketClient("localhost", "c2");
		client1.requestTicket();
		client2.requestTicket();
		
	}

	@Test
	public void twoNonConcurrentServerTest() {
		try {
			TicketServer.start(16791);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1");
		TicketClient c2 = new TicketClient("nonconc2");
		TicketClient c3 = new TicketClient("nonconc3");
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
	}

	@Test
	public void twoConcurrentServerTest() {
		try {
			TicketServer.start(16792);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1");
		final TicketClient c2 = new TicketClient("conc2");
		final TicketClient c3 = new TicketClient("conc3");
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	*/
	@Test
	public void TicketOfficeTest() {
		try {
			TicketServer.start(16793);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("Booth1");
		TicketClient c2 = new TicketClient("Booth2");
		TicketClient c3 = new TicketClient("Booth3");
		
		Thread t1 = new Thread() {
			public void run() {
				int rando = (int)((Math.random() * 901) + 100);
				while(rando>0)
				{
					c1.requestTicket();
					rando--;
					if(rando == 1)
					{
						rando = (int)((Math.random() * 901) + 100);
					}
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				int rando = (int)((Math.random() * 901) + 100);
				while(rando>0)
				{
					c2.requestTicket();	
					rando--;
					if(rando == 1)
					{
						rando = (int)((Math.random() * 901) + 100);
					}
				}
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				int rando = (int)((Math.random() * 901) + 100);
				while(rando>0)
				{
					c3.requestTicket();
					rando--;
					if(rando == 1)
					{
						rando = (int)((Math.random() * 901) + 100);
					}
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
