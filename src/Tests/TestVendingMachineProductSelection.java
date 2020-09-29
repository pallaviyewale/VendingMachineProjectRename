package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import main.DataObjects.*;
import main.VendingMachineMethods.VendingMachineAdminActions;
import main.VendingMachineMethods.VendingMachineConsumerActions;

public class TestVendingMachineProductSelection 
{
	VendingMachineConsumerActions consumerActions = new VendingMachineConsumerActions();
	VendingMachineAdminActions adminActions = new VendingMachineAdminActions();
	  
  @Test
  public void verifyUserCantSelectInValidProduct() throws IOException 
  {
	  String message = consumerActions.selectItem("Lime");
	  Assert.assertEquals(message, "Please enter valid product name");
  }
  
  @Test
  public void verifyUserGotSelectedProduct() throws IOException 
  {
	  Basket.Instance().setTotalCoins(0);
	  Item item = new Item("Coke",25);
	  adminActions.loadInventory(item,1);
	  Basket.Instance().addCoins(25);
	  String message = consumerActions.selectItem("Coke");
	  Assert.assertEquals(message, "Please pickup your item and balance amount 0");
  }
  
  @Test
  public void verifyUserGotSelectedProductWithRemainingAmount() throws IOException 
  {
	  Basket.Instance().setTotalCoins(0);
	  Item item = new Item("Soda",25);
	  adminActions.loadInventory(item,1);
	  Basket.Instance().addCoins(25);
	  Basket.Instance().addCoins(25);
	  String message = consumerActions.selectItem("Soda");
	  Assert.assertEquals(message, "Please pickup your item and balance amount 5");
  }
  
  @Test
  public void verifyUserCantSelectItemBecauseOfInsufficientBalance() throws IOException 
  {
	  Basket.Instance().setTotalCoins(0);
	  Item item = new Item("Soda",25);
	  adminActions.loadInventory(item,1);
	  Basket.Instance().addCoins(25);
	  String message = consumerActions.selectItem("Pepsi");
	  Assert.assertEquals(message, "Insufficient balance, current balance is 25 Either Insert More Coins or Cancel the transaction");
  }
  
  @Test
  public void verifyUserCanCancelTransactionAndGetRefund() throws IOException 
  {
	  Basket.Instance().setTotalCoins(0);
	  Item item = new Item("Pepsi",25);
	  adminActions.loadInventory(item,1);
	  Basket.Instance().addCoins(25);
	  String message = consumerActions.cancelAndRefund();
	  Assert.assertEquals(message, "Transaction Cancelled. Money Refunded: 25");
  }
  
}
