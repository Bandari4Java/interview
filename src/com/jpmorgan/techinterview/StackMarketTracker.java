/**
 * 
 */
package com.jpmorgan.techinterview;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Sunil Bandari
 *
 */
public class StackMarketTracker {
	HashMap<String, CustomStockEntry> stockEntryList;
	List<Integer> dividendList;
	List<TradeRecord> tradeRecords, tradeRecordsFilter;
 double tradedSum=0,quantitySum=0,volumeWeightedSum=0;
	
	long startTime;
	public StackMarketTracker(){
		stockEntryList = new HashMap<String, CustomStockEntry>();
		stockEntryList.put("TEA", new CustomStockEntry(0, 100));
		stockEntryList.put("POP", new CustomStockEntry(8, 100));
		stockEntryList.put("ALE", new CustomStockEntry(23, 60));
		stockEntryList.put("GIN", new CustomStockEntry(8,2, 100));
		stockEntryList.put("JOE", new CustomStockEntry(13, 250));
		
		startTime = new Date().getTime();		
		try {
			tradeRecords = new ArrayList<TradeRecord>();
			tradeRecords.add(new TradeRecord("TEA", 50, 0, 0, 382, new Date(startTime-4*1000*60)));
			tradeRecords.add(new TradeRecord("ALE", 125, 1, 0, 386, new Date(startTime-6*1000*60)));
			tradeRecords.add(new TradeRecord("POP", 221, 1, 0, 392, new Date(startTime-2*1000*60)));
			tradeRecords.add(new TradeRecord("GIN", 113, 0, 1, 393, new Date(startTime-1*1000*60)));
			tradeRecords.add(new TradeRecord("TEA", 458, 1, 0, 385, new Date(startTime)));
			tradeRecords.add(new TradeRecord("JOE", 420, 0, 0, 382, new Date(startTime-2*1000*60)));
			tradeRecords.add(new TradeRecord("TEA", 463, 1, 0, 386, new Date(startTime-7*1000*60)));
			tradeRecords.add(new TradeRecord("TEA", 192, 1, 0, 392, new Date(startTime-8*1000*60)));
			tradeRecords.add(new TradeRecord("JOE", 2, 0, 0, 393, new Date(startTime-1*1000*60)));
			tradeRecords.add(new TradeRecord("ALE", 35, 1, 0, 385, new Date(startTime)));
			tradeRecords.add(new TradeRecord("POP", 12, 0, 0, 382, new Date(startTime-9*1000*60)));
			tradeRecords.add(new TradeRecord("ALE", 90, 1, 0, 386, new Date(startTime-1*1000*60)));
			tradeRecords.add(new TradeRecord("GIN", 27, 1, 1, 392, new Date(startTime-2*1000*60)));
			tradeRecords.add(new TradeRecord("GIN", 11, 0, 1, 393, new Date(startTime-1*1000*60)));
			tradeRecords.add(new TradeRecord("POP", 34, 1, 0, 385, new Date(startTime)));
			
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addNewStock(String stock_name,int lastDividend, int fixedDividend, int parValue) throws InvalidParameterException{
		if(stockEntryList.containsKey(stock_name))
			throw new InvalidParameterException("Sorry given stock "+stock_name+" already exist");
		else
		 stockEntryList.put(stock_name, new CustomStockEntry(lastDividend,fixedDividend, parValue));
			
	  }
	
	public void addNewStock(String stock_name,int lastDividend, int parValue) throws InvalidParameterException{
		if(stockEntryList.containsKey(stock_name))
			throw new InvalidParameterException("Sorry given stock "+stock_name+" already exist");
		else
		 stockEntryList.put(stock_name, new CustomStockEntry(lastDividend,parValue));
			
	  }
	
	
	public void recordTrade(String stock_name, int quantity, int TRANSACTION_TYPE, int STOCK_TYPE, int stock_price, Date timestamp) throws InvalidParameterException{
		if(stockEntryList.containsKey(stock_name))
			tradeRecords.add(new TradeRecord(stock_name, quantity, TRANSACTION_TYPE, STOCK_TYPE, stock_price, timestamp));
		else 
			throw new InvalidParameterException("Sorry invalid stock name "+stock_name);
	}
	
	public double getDividendYield(String stackName, int price) throws InvalidParameterException{
		dividendList = new ArrayList<Integer>();
		if(stockEntryList.containsKey(stackName)&& checkPrice(price)){
			if(stockEntryList.get(stackName).STOCK_TYPE==0)
				return stockEntryList.get(stackName).getlastDividend()/price;
			else
				return stockEntryList.get(stackName).getfixedDividend()*stockEntryList.get(stackName).getparvalue()/(price*100);
		}
		else
			throw new InvalidParameterException("Sorry given stock "+stackName+" doesnot exist");
	}
	
	public double getPERatio(String stackName, int price) throws InvalidParameterException{
		return getDividendYield(stackName, price)/price;
	}
	
	public double getVolumeWeightedStockPrice(String stock_name) throws InvalidParameterException {
		tradeRecordsFilter = new ArrayList<TradeRecord>();
		if(stockEntryList.containsKey(stock_name)){
			 Date timePeriod = new Date((new Date().getTime())-5*1000*60);
			tradeRecordsFilter.addAll(tradeRecords.stream().filter((TradeRecord record)-> record.getStock_name().equals(stock_name)&&record.getTimestamp().compareTo(timePeriod)>0).collect(Collectors.toList()));
			if(tradeRecordsFilter.size()>0){
				tradeRecordsFilter.forEach(tradeRecord-> tradedSum+=tradeRecord.getQuantity()*tradeRecord.getStock_price());
				tradeRecordsFilter.forEach(tradeRecord-> quantitySum+=tradeRecord.getQuantity());
				
			return tradedSum/quantitySum;
			}
			else
				throw new InvalidParameterException("Given stock doest not have any trade records from past 5 Minutes, please try other stock");	
		}
		else 
			throw new InvalidParameterException("Invalid stock entry");
	}

	
	
public boolean checkPrice(int price) throws InvalidParameterException{
	if(price>0)
	return true;
	else
		throw new InvalidParameterException("Invalid price, should be more than zero");
}

public double getGBCEAllShareIndex() throws InvalidParameterException{
	
	for (Map.Entry<String, CustomStockEntry> entry : stockEntryList.entrySet()) {
		volumeWeightedSum+=getVolumeWeightedStockPrice(entry.getKey() );
		}
	
	return Math.pow(volumeWeightedSum, 1.0/stockEntryList.size());
}

}
