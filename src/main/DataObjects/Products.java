package main.DataObjects;

import java.util.HashMap;

//Product object has List of all Items and its inventory details and methods to add/remove products from inventory. 
public class Products 
{	
	HashMap<String,Item> products_map = new HashMap<String,Item>();
	HashMap<String,Integer> product_Inventory = new HashMap<String,Integer>();
	
	private Products()
	{
		
	}
	
	private static Products products_instance = new Products(); 

	public static Products Instance() {
		return products_instance;
	}
	
	public int getProductInventory(String itemName) {
		return product_Inventory.get(itemName);
	}
	
	public void setProductInventory(String itemName, int value) {
		this.product_Inventory.put(itemName, value);
	}


	public HashMap<String, Item> getProducts() {
		return products_map;
	}
	
	public Item retreiveProduct(String itemName) {
		return products_map.get(itemName);
	}

	public void setProducts(Item item) {
		this.products_map.put(item.getName(), item);
	}
	
	public void addProductToInventory(Item item, int quantity) {
		
		try
		{
			int total_quantity;
			if(product_Inventory!=null && product_Inventory.containsKey(item.getName()))
			{
				total_quantity  = quantity + product_Inventory.get(item.getName());
			}
			else
				total_quantity = quantity;
			
			this.product_Inventory.put(item.getName(), total_quantity);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void removeProductFromInventory(Item item, int quantity) 
	{
		try
		{
			int total_quantity =  product_Inventory.get(item.getName()) - quantity;
			this.product_Inventory.put(item.getName(), total_quantity);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void clearInventory()
	{
		products_map.clear();
		product_Inventory.clear();
	}
	
}
