#

                    Super Simple Stock Market
                    
  Requirements

    Provide working source code that will:

    a. For a given stock:

    i.    Calculate the dividend yield.
    ii.   Calculate the P/E Ratio.
    iii.  Record a trade, with timestamp, quantity of shares, buy or sell indicator and price.
    iv.   Calculate Stock Price based on trades recorded in past 15 minutes.

    b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
    
  Sample Data:
  Global Beverage Corporation Exchange
  
StockSymbol 	Type 	    Last Dividend 	Fixed Dividend 	Par Value
  TEA        	Common      	0                         		100
  POP 	      Common 	      8                         		100
  ALE 	      Common 	      23 		                        60
  GIN 	      Preferred   	8           	  2%           	100
  JOE 	      Common      	13 		                        250

** All number values has given in pennies
    
  Notes:
    
      ** Since all values has given in pennies, Its better to take data type integer. 
      * Also we had two stock types here as common and preffered. We can use enums to define stock type but 
      it consumes bit more memory and consumes time compare to integer value. So, I had taken interger value to define stock type.
      
    
  
    Author
    Sunil Bandari
    
    

