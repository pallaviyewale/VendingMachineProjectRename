package main.DataObjects;

//Item object to have its name and price
public class Item
{
	private String itemName;
	private int itemPrice;
	
	public Item(String itemmame, int itemprice)
	{
		this.itemName = itemmame;
		this.itemPrice = itemprice;
	}
	
	public String getName() {
		return itemName;
	}

	public int getPrice() {
		return itemPrice;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
}
