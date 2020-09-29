package Tests;

import org.testng.annotations.Test;

import main.DataObjects.Basket;
import main.VendingMachineMethods.VendingMachineConsumerActions;
import org.testng.Assert;

public class TestVendingMachineConsumerActions 
{
	VendingMachineConsumerActions consumerActions = new VendingMachineConsumerActions();
	
	  @Test
	  public void validateAllValidCoinsCanBeInsertedInVendingMachine()
	  {
		  boolean status = true;
		  status = consumerActions.validateCoin(1);
		  status = consumerActions.validateCoin(5);
		  status = consumerActions.validateCoin(10);
		  status = consumerActions.validateCoin(25);
		  Assert.assertTrue(status);
	  }
	  
	  @Test
	  public void ValidateValidCoin10()
	  {
		  boolean status = consumerActions.validateCoin(10);
		  Assert.assertTrue(status);
	  }
	  
	  @Test
	  public void validateInValidCoinThatShouldNotBeAccepted()
	  {
		  boolean status = consumerActions.validateCoin(15);
		  Assert.assertFalse(status);
	  }
	  
	  @Test
	  public void validateInValidCoin0ThatShouldNotBeAccepted()
	  {
		  boolean status = consumerActions.validateCoin(0);
		  Assert.assertFalse(status);
	  }
	  
	  @Test
	  public void validateMinusNumberThatShouldNotBeAccepted()
	  {
		  boolean status = consumerActions.validateCoin(-1);
		  Assert.assertFalse(status);
	  }
	  
	  @Test
	  public void validateAndInsertCoinInVendingMachine()
	  {
		  Basket.Instance().setTotalCoins(0);
		  if(consumerActions.validateCoin(5))
		  {
			Basket.Instance().addCoins(5);
		  }
				
		  int totalAmount = Basket.Instance().getTotalValueOfInsertedCoins();
		  Assert.assertEquals(totalAmount, 5);
	  }
	  
	  
	  @Test
	  public void verifyTotalAmountOfInsertedCoins() 
	  {
		  Basket.Instance().setTotalCoins(0);

		  if(consumerActions.validateCoin(5))
		  {
			Basket.Instance().addCoins(5);
		  }
		  
		  if(consumerActions.validateCoin(10))
		  {
			Basket.Instance().addCoins(10);
		  }
		  
		  if(consumerActions.validateCoin(25))
		  {
			Basket.Instance().addCoins(25);
		  }
		  int totalAmount = Basket.Instance().getTotalValueOfInsertedCoins();
		  Assert.assertEquals(totalAmount, 40);
	  }
	  
	  @Test
	  public void verifyCancelTransactionFunctionality() 
	  {
		  if(consumerActions.validateCoin(10))
		  {
			Basket.Instance().addCoins(10);
		  }
		  
		  if(consumerActions.validateCoin(25))
		  {
			Basket.Instance().addCoins(25);  
		  }
		  
		  String message = consumerActions.cancelAndRefund();
		  
		  Assert.assertEquals(message, "Transaction Cancelled. Money Refunded: 40");
		  
		  
	  }
		  

}
