package Tests;

import org.testng.annotations.Test;

import main.DataObjects.*;
import main.VendingMachineMethods.VendingMachineAdminActions;

import java.util.HashMap;
import java.util.Map.Entry;

import org.testng.Assert;

public class TestVendingMachineAdminActions {
	
	VendingMachineAdminActions adminActions = new VendingMachineAdminActions();
	
  
  @Test
  public void verifyPriceForAllProductsInProductList() 
  {
	  boolean status = false;
	  adminActions.loadProducts();
	  HashMap<String, Item> productList = Products.Instance().getProducts();
	  for(Entry<String, Item> item : productList.entrySet())
	  {
		  if(item.getKey().equals("Coke") && item.getValue().getPrice()==25)
		  {
			  status = true;
		  }
				  
		  if(item.getKey().equals("Pepsi") && item.getValue().getPrice()==35)
		  {
			  status = true;
		  }
		  
		  if(item.getKey().equals("Soda") && item.getValue().getPrice()==45)
		  {
			  status = true;
		  }
	  }
	  Assert.assertTrue(status);
  }
  
  @Test
  public void verifyAllValidProductsAvailableInProductsList() 
  {
	  boolean status = false;
	  adminActions.loadProducts();
	  HashMap<String, Item> productList = Products.Instance().getProducts();
	  for(Entry<String, Item> item : productList.entrySet())
	  {
		  if(item.getKey().equals("Coke") || item.getKey().equals("Pepsi") || item.getKey().equals("Soda")) 
		  {
			  status = true;
		  }
	  }
	  Assert.assertTrue(status);
  }
  
  @Test
  public void verifyQuantityOfProductAfterLoadingProductIntoInventory() 
  {
	  Item item = new Item("Pepsi",35);
	  adminActions.loadInventory(item, 10);
	  int productCount = Products.Instance().getProductInventory(item.getName());
	  Assert.assertEquals(productCount, 10);
  }
  
  
  @Test
  public void verifyQuantityOfProductAfterRemovedProductFromInventory() 
  {
	  Products.Instance().setProductInventory("Soda",0);
	  Item item = new Item("Soda",45);
	  adminActions.loadInventory(item, 20);
	  Products.Instance().removeProductFromInventory(item, 15);
	  int productCount = Products.Instance().getProductInventory(item.getName());
	  Assert.assertEquals(productCount, 5);
  }
  
}
