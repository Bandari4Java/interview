package com.jpmorgan.techinterview;

import java.util.Date;


public class TradeRecord {
	private String stock_name;
	private int STOCK_TYPE,TRANSACTION_TYPE,TRANSACTION_TYPE_BUY=0,TRANSACTION_TYPE_SELL=1,stock_price,quantity;
	public String getStock_name() {
		return stock_name;
	}

	public int getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}

	public int getStock_price() {
		return stock_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	private Date timestamp;
	
	public TradeRecord(){
		
	}
	
	public TradeRecord(String stock_name, int quantity, int TRANSACTION_TYPE, int STOCK_TYPE, int stock_price, Date timestamp) throws InvalidParameterException {
		if(checkPrice(stock_price)&&checkPrice(quantity))
		{
			this.quantity = quantity;
			this.TRANSACTION_TYPE = TRANSACTION_TYPE;
			this.stock_price = stock_price;	
			this.stock_name = stock_name;
			this.timestamp = timestamp;
			this.STOCK_TYPE=STOCK_TYPE;
		
		}
	}
	
	public boolean checkPrice(int price) throws InvalidParameterException{
		if(price>0)
		return true;
		else
			throw new InvalidParameterException("Invalid price, should be more than zero");
	}

}
