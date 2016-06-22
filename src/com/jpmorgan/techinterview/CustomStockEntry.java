package com.jpmorgan.techinterview;

/**
 * @author Sunil Bandari
 *
 */


public class CustomStockEntry {
  int STOCK_TYPE_COMMON=0, STOCK_TYPE_PREFERRED=1,STOCK_TYPE,lastDividend, fixedDividend,stockPrice,divedend, parValue;
 
  public int getStockPrice() {
	return stockPrice;
}

public void setStockPrice(int stockPrice) {
	this.stockPrice = stockPrice;
}

public int getDivedend() {
	return divedend;
}

public void setDivedend(int divedend) {
	this.divedend = divedend;
}

public CustomStockEntry(int lastDividend, int fixedDividend, int parValue){
	 this.STOCK_TYPE=STOCK_TYPE_PREFERRED;
	 this.lastDividend=lastDividend;
	 this.fixedDividend=fixedDividend;
	 this.parValue=parValue; 
  }
  
  public CustomStockEntry(int lastDividend, int parValue){
		 this.STOCK_TYPE=STOCK_TYPE_COMMON;
		 this.lastDividend=lastDividend;
		 this.parValue=parValue;		 
  }
  
  public int getlastDividend(){
	  return lastDividend;
  }
  
  public int getfixedDividend(){
	  return fixedDividend;
  }
  public int getparvalue(){
	  return parValue;
  }
  
  
	
}
