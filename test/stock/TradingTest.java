package stock;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class TradingTest {
	// Test data
		
	private final double[] array = {1,2,3,4,5,6,7,8,9};
	private final double[] array2 = {1.55,2.22,3.33,4.44};
	private final int val=1;
	private final  String result = "1(1.55),4(4.44)";

	@Test
	final void testFindIndex() {
		
		assertEquals(0,Trading.findIndex(array, val));
		
	}

	@Test
	final void testTradeSelector() {
		assertEquals(result,Trading.tradeSelector(array2));
		
	}

}
