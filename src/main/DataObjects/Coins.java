package main.DataObjects;


//Coins object to validate all valid coins
public enum Coins 
{
	PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
	
	int value;
	
	public int getValue() {
		return value;
	}
	
	Coins(int value)
	{
		this.value = value;
	}
	
}
