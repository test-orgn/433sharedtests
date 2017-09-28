package cmsc433.p1.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmsc433.p1.*;

public class testMulti {
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
        // Before each test, reset the Server, by re-initializing its instance.
        Constructor<ServerPrinter> serverConstructor = ServerPrinter.class.getDeclaredConstructor((Class<ServerPrinter>[])null);
        serverConstructor.setAccessible(true);

        Field serverInstance = AuctionServer.class.getDeclaredField("instance");
        serverInstance.setAccessible(true);

        serverInstance.set(null, serverConstructor.newInstance((Object[])null));
    }

    @After
    public void tearDown() throws Exception
    {
    }

	@Test
	public void test() {
		
		AuctionServer theServer = AuctionServer.getInstance();
        
		Seller iSellStuff = new Seller(theServer, "i sell stuff", 1, 5, 9); // seller is selling 1 thing only here
		Bidder iBuyStuff = new ConservativeBidder(theServer, "i buy", 150, 5, 5, 50);
		Bidder iBuyStuff2 = new AggressiveBidder(theServer, "i buy agg", 250, 5, 2, 50);
	    
		Thread sellThread = new Thread(iSellStuff);
		Thread buyThread = new Thread(iBuyStuff);
		Thread buy2Thread = new Thread(iBuyStuff2);
	    
		sellThread.start();
		buyThread.start();
		buy2Thread.start();
	    
		try {
		    sellThread.join();
		    buyThread.join();
		    buy2Thread.join();
		} catch (InterruptedException e) {
		    System.err.println("Failed to join threadz");
		    assert(1 == 0);
		}
	    
		assert(theServer.uncollectedRevenue() == 0);
		
		System.out.println("server's rev is: " + theServer.revenue());
		System.out.println("server's uncollected rev is: " + theServer.uncollectedRevenue());
		System.out.println("server's number of sold items: " + theServer.soldItemsCount());
		System.out.println("status of item 0: " + theServer.checkBidStatus("i buy", 0));
		System.out.println("item 0 cost: " + theServer.itemPrice(0));
		System.out.println("item 0 has no bids on it: " + theServer.itemUnbid(0));

	}

}
