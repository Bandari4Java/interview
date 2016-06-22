package com.jpmorgan.techinterview;

import static org.junit.Assert.*;

import org.junit.Test;


public class StackMarketTrackerTest {
	
	StackMarketTracker tracker = new StackMarketTracker();
	
	

	@Test(expected = InvalidParameterException.class)
	public void testgetDividendYieldExceptionCheck() throws InvalidParameterException{		
		tracker.getDividendYield("XYZ", 20);		
	}
	
	
	@Test
	public void testgetDividendYield() throws InvalidParameterException{		
		assertEquals(1.0, tracker.getDividendYield("ALE", 20),0.0);	
		assertNotEquals(0.4524, tracker.getDividendYield("GIN", 841));
	}
	
	@Test
	public void testgetPERatio() throws InvalidParameterException{	
		assertEquals(0.05, tracker.getPERatio("ALE", 20),0.0);		
		assertNotEquals(0.34, tracker.getPERatio("GIN", 158));
	}
	
	@Test
	public void testgetVolumeWeightedStockPrice() throws InvalidParameterException{	
		assertEquals(385.72, tracker.getVolumeWeightedStockPrice("ALE"),0.0);		
		assertNotEquals(0.0, tracker.getVolumeWeightedStockPrice("GIN"));
	}
	
	@Test
	public void testgetGBCEAllShareIndex() throws InvalidParameterException{	
		assertEquals(4.542549690484658, tracker.getGBCEAllShareIndex(),0.0);		
		assertNotEquals(0.0, tracker.getGBCEAllShareIndex());
	}
}
