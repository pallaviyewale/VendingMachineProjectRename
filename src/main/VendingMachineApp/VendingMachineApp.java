package main.VendingMachineApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.VendingMachineMethods.VendingMachineConsumerActions;
import main.VendingMachineMethods.VendingMachineAdminActions;


//This is a main class of application. It shows the display on console and takes the input from console and calls appropriate action. 
public class VendingMachineApp 
{
	public static void main(String args[]) throws IOException
	{		
		VendingMachineConsumerActions consumerActions = new VendingMachineConsumerActions();
		VendingMachineAdminActions adminActions = new VendingMachineAdminActions();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in) );
		int input;
		
		do
		{
			System.out.println("\n---------------------------------------------------------------");
			System.out.println("\nAdmin Tasks");
			System.out.println("Select '1' to Reset the Vending Machine");
			
			System.out.println("\n\nConsumer Tasks");
			System.out.println("Select '2' to Insert coin");
			System.out.println("Select '3' to Select the Product");
			System.out.println("Select '4' to Cancel and Refund");
			System.out.println("Select '0' to Exit ");
			System.out.println("Select the Activity:");
			String ActivityNum= bf.readLine();
			
			input = Integer.parseInt(ActivityNum);
			String message = ""; 
			
			switch(input)
			{
			case 1: 
				message = adminActions.resetVendingMachine();
				System.out.println(message);
				adminActions.printInventory();
				break;
				
			case 2:
				consumerActions.insertCoin();
				break;
				
			case 3:
				System.out.println("Select item - "
						+ "			\nSoda : 25P "
						+ "			\nPepsi : 35P"
						+ "			\nCoke : 45P");
				BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
				String itemName= bfr.readLine();
				message = consumerActions.selectItem(itemName);
				System.out.println(message);
				break;
				
			case 4:
				message = consumerActions.cancelAndRefund();
				System.out.println(message);
				break;
				
			case 0: 
			default:
				break;
			}
		}while(input!=0);
	}

}