package main.VendingMachineMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.DataObjects.*;


//This class have all actions user has to take like insert coin/select product from vending machine.
public class VendingMachineConsumerActions 
{	
	public boolean insertCoin() throws IOException		
	{    
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in) );
		String inputCoin = "";
		boolean insertStatus = true;
	    do
	    {
			System.out.println("Insert Coin or any character to finish coin insert activity ");
			inputCoin = bf.readLine();
			
			try
			{ 
				int insertedCoin = Integer.parseInt(inputCoin);
				if(validateCoin(insertedCoin) == true)
				{
					Basket.Instance().addCoins(insertedCoin);
					printTotalMoneyDeposited();
				}				
			}
            catch(NumberFormatException er)
			{ 
            	inputCoin = "Done";
            }
			
	    }
	    while(inputCoin!="Done");
	    
	    return insertStatus;
	}
	
	private void printTotalMoneyDeposited()
	{
		System.out.println("Total money deposited : " + Basket.Instance().getTotalValueOfInsertedCoins());
	}
	
	public boolean validateCoin(int insertedCoin)
	{
		boolean coinStatus = false;
		Coins[] coinValues = Coins.values();
	    for (Coins coin : coinValues) 
	    {
	           if(coin.getValue() == insertedCoin)
	           {
	        	   coinStatus = true;
	        	   break;
	           }
	    }
	    
	    if(coinStatus == false)
	    {
	    	System.out.println("Not a Valid Coin. Coins allowed : PENNY(1), NICKLE(5), DIME(10), QUARTER(25)");
	    }
	    
	    return coinStatus;
	}
	
	
	public String selectItem(String itemName) throws IOException
	{	
		
		if(itemName.equalsIgnoreCase("Coke") == false && itemName.equalsIgnoreCase("Pepsi") == false && itemName.equalsIgnoreCase("Soda") == false)
		{
			return "Please enter valid product name";
		}
		
		if(Products.Instance().getProducts().size() == 0)
		{
			return "Item not available, please select another item or cancel the transaction";
		}
				
		if(Products.Instance().retreiveProduct(itemName) == null || Products.Instance().getProductInventory(itemName) == 0) 
		{
			return "Item not available, please select another item or cancel the transaction";
		}
		
		Item itemDetails = Products.Instance().retreiveProduct(itemName);
		int balancAmount = Basket.Instance().getTotalValueOfInsertedCoins();			
		
		if (balancAmount < itemDetails.getPrice())
		{
			return "Insufficient balance, current balance is " + balancAmount + " Either Insert More Coins or Cancel the transaction";			
		}
		
		balancAmount = Basket.Instance().getTotalValueOfInsertedCoins() - itemDetails.getPrice();
		Products.Instance().removeProductFromInventory(itemDetails, 1);
		Basket.Instance().setTotalCoins(0);
		return "Please pickup your item and balance amount "  + balancAmount;
	}
	
	

	
	public String cancelAndRefund()
	{
		int balancAmount = Basket.Instance().getTotalValueOfInsertedCoins();
		String message = "Transaction Cancelled. Money Refunded: " + balancAmount;
		Basket.Instance().setTotalCoins(0);
		return message;
	}
	
}
