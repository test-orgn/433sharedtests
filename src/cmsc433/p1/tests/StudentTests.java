package cmsc433.p1.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import cmsc433.p1.*;

public class StudentTests {
    
    @Test
    public void bestTest1() {
	AuctionServer theServer = AuctionServer.getInstance();
        
	Seller iSellStuff = new Seller(theServer, "i sell stuff", 1, 3, 9);
	Bidder iBuyStuff = new ConservativeBidder(theServer, "i buy", 50, 5, 5, 90);
    
	Thread sellThread = new Thread(iSellStuff);
	Thread buyThread = new Thread(iBuyStuff);
    
	sellThread.start();
	buyThread.start();
    
	try {
	    sellThread.join(500);
	    buyThread.join(500);
	} catch (InterruptedException e) {
	    System.err.println("Failed to join threadz");
	    assert(1 == 0);
	}
    
	assert(theServer.uncollectedRevenue() == 0);
	System.out.println("server's rev is " + theServer.revenue());
    }
}
