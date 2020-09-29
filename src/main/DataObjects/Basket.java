package main.DataObjects;

//Basket object to have total amount user has inserted
public class Basket 
{
	private int totalAmount;
	
	private Basket()
	{
		
	}

	private static Basket basketCoins = new Basket();
	
	public void clearCoinBasket(){
		totalAmount = 0;
	}
	
	public int getTotalValueOfInsertedCoins() {
		return totalAmount;
	}
	
	public static Basket Instance() {
		return basketCoins;
	}

	public static void setBasketCoins(Basket basketCoins) {
		Basket.basketCoins = basketCoins;
	}

	public int setTotalCoins(int coinValue) {
		return totalAmount = coinValue;
	}

	public void addCoins(int coin) 
	{	
		this.totalAmount = totalAmount + coin;
	}
}
