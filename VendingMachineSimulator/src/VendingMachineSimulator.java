/** 
 * EN.605.201 Introduction to Java Programming
 * This is the main class for Vending Machine Simulator. 
 * At program startup, the vending machine is loaded with a variety of products 
 * in a variety of packaging. Also included is the cost of each item. 
 * The program is designed to load a different set of products from text files. At program startup, 
 * money is loaded into the vending machine.The money consists of different monetary objects 
 * for the specified currency, for example $1 bills, $5 bills, quarters, and dimes.
 * The program is designed to use different national currencies, Euro, Yen, and US dollars 
 * without changing source code. The money is maintained as paper bills and coins, not just amounts.
 * 
 * @version		1.0 January 27, 2020
 * @author		shanzalewis
 */

import java.io.*;
import java.util.*;

public class VendingMachineSimulator 
{
	public static Scanner input = new Scanner(System.in); 
    
	public static void main(String[] args) throws Exception 
	{
		String usd = "USD.txt";                  //US dollars text file
		String yen = "Yen.txt";                  //Yen text file
		String euro = "Euro.txt";                //Euro text file
		String bottles = "Row1 - Bottles.txt";   //drinks text file
		String bags = "Row2 - Bags.txt";         //snacks text file
		String paper_wrapper = "Row3 - Paper Wrapper.txt";  //candy text file
		String[]line = new String[100];          
		String info = null;                      
		int item_amount = 0;                     
		String[]s = new String [100];            
		String name = null;                      
		String cost = null;
		String amount = null;
		double item_cost = 0;
		String amt_rem = null;
		int amount_remaining = 0;
		int item_num =0;
		int money = 0;
		
		double total_bottle = 0;
		double total_bag = 0;
		double total_paper_wrapper = 0;
		double owed = 0;
		Bottles bottle_item = new Bottles();
		String denom = null;
		String quantity = null; 
		int currency_quantity = 0;
		int quantity_remaining = 0;
		String quant_rem = null;
		int new_quantity = 0; 
		double change = 0; 
		Bags bag_item = new Bags();
		PaperWrapper paper_wrapper_item = new PaperWrapper();
		double total = 0; 

		displayInventory(bottles, bags, paper_wrapper);
		getCommand(line,  info,  item_amount,  s,  bottles, 
				 bags,  paper_wrapper,  name,  cost,
	    		 amount,  item_cost, amt_rem,  item_num,
	    		 amount_remaining,  money,  total_bottle,  total_bag,  total_paper_wrapper,  owed,  bottle_item,  name,  cost,
	    		 item_amount,  item_num,	 denom,   currency_quantity, 
				 quantity_remaining,  bag_item, paper_wrapper_item, total);
	}
	
	/*
     * Reads and prints drinks text file
     * @param		String bottles   Calls Bottles text file
     */
	public static void displayBottles(String bottles)
	{
		try (Reader reader = new InputStreamReader(
				new FileInputStream(bottles), "UTF-16"))
		{
			int i;
			while ((i = reader.read()) != -1) System.out.print((char) i);
		} catch(IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}
	
	/*
     * Reads and prints snacks text file
     * @param		String bags   Calls Bags text file
     */
	public static void displayBags(String bags)
	{
		try (Reader reader = new InputStreamReader(
				new FileInputStream(bags), "UTF-16"))
		{
			int i;
			while ((i = reader.read()) != -1) System.out.print((char) i);
		} catch(IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}
	
	/*
     * Reads and prints candy text file
     * @param		String paper_wrapper   Calls Paper Wrapper text file
     */
	public static void displayPaperWrapper(String paper_wrapper) 
	{
		try (Reader reader = new InputStreamReader(
				new FileInputStream(paper_wrapper), "UTF-16"))
		{
			int i;
			while ((i = reader.read()) != -1) System.out.print((char) i);
		} catch(IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}
	
	/*
     * Displays the current vending machine inventory
     */
	public static void displayInventory(String bottles,	String bags, String paper_wrapper) throws Exception
	{		
		displayBottles(bottles);
		System.out.println();
		
		displayBags(bags);
		System.out.println();
		
		displayPaperWrapper(paper_wrapper);
		System.out.println();
	}
	
	/**
     * Prompts user to choose Soda, Snacks or Candy. User enters selection.
     * @param	String []line
     * @param	String info
     * @param	int item_amount
     * @param	String[] 
     * @param	String bottles
     * @param	String bags
     * @param	String paper_wrapper
     * @param	String name
     * @param	String cost
     * @param	String amount
     * @param	double item_cost
     * @param	String amt_rem
     * @param	int item_num
     * @param	int amount_remaining
     * @param	int money
     * @param	double total_bottle
     * @param	double total_bag
     * @param	double total_paper_wrapper 
     * @param	double owed
     * @param	Bottles bottle_item
     * @param	String denom
     * @param	String quantity
     * @param	int currency_quantity
     * @param	int quantity_remaining
     * @param	String quant_rem
     * @param	int new_quantity
     * @param	double change
     * @param	Bags bag_item
     * @param	PaperWrapper paper_wrapper_item
     * @param	double total
     */
	public static void getCommand(String []line, String info, int item_amount, String[] s, String bottles, 
			String bags, String paper_wrapper, String name, String cost, String amount, double item_cost,
			String amt_rem, int item_num, int amount_remaining, int money, double total_bottle, double total_bag, 
			double total_paper_wrapper, double owed, Bottles bottle_item, String denom,  String quantity, 
			int currency_quantity, int quantity_remaining, String quant_rem, int new_quantity, double change, 
			Bags bag_item, PaperWrapper paper_wrapper_item,double total)throws Exception
	{
		System.out.println();
		System.out.println("1 - Soda, 2 - Snacks, 3 - Candy");   //prints options
		System.out.print("Select Row: ");
		int row = input.nextInt();                               //stores user selection
		
		if ((row >= 1) || (row <= 3))                            //switches between options based on user selection
		{
			switch (row)
			{
			    case 1:
			    	bottle_item = new Bottles(line, s, info, name, cost, amount, item_cost, //calls new Bottle class
			    			item_amount, amt_rem,bottles, item_num, amount_remaining);
			        bottle_item.calcItemCurr(money, total_bottle,  total_bag,  total_paper_wrapper, owed,
			        		line, info, s, bottle_item, name, cost, amount, item_cost, item_amount, amt_rem,
			        		bottles, item_num, amount_remaining, denom,  quantity, currency_quantity, quantity_remaining, 
			        		quant_rem, new_quantity, change, bag_item, paper_wrapper_item, paper_wrapper, total);
			    	break;
			    case 2:
			    	bag_item = new Bags(line, s, info, name, cost, amount, item_cost,       //calls new Bags class
			    			item_amount, amt_rem,bottles, item_num,	amount_remaining);
			    	bag_item.calcItemCurr(money, total_bottle,  total_bag,  total_paper_wrapper, owed,
			        		line, info, s, bottle_item, name, cost, amount, item_cost, item_amount, amt_rem,
			        		bottles, item_num, amount_remaining, denom,  quantity, currency_quantity, quantity_remaining, 
			        		quant_rem, new_quantity, change, bag_item, paper_wrapper_item, paper_wrapper, total);
			    	break;
			    case 3:
			    	paper_wrapper_item = new PaperWrapper(line, s, info, name, cost, amount, //calls new Paper_Wrapper class
			    			item_cost, item_amount, amt_rem,bottles, item_num,	amount_remaining);
			        paper_wrapper_item.calcItemCurr(money, total_bottle,  total_bag,  total_paper_wrapper, owed,
			        		line, info, s, bottle_item, name, cost, amount, item_cost, item_amount, amt_rem,
			        		bottles, item_num, amount_remaining, denom,  quantity, currency_quantity, quantity_remaining, 
			        		quant_rem, new_quantity, change, bag_item, paper_wrapper_item, paper_wrapper, total);  
			    	break;
			}
		} else {
			//catches invalid entry and prompts for valid selection
			System.out.println("1 - Soda, 2 - Snacks, 3 - Candy" + "\nPlease enter a valid row option: ");  
			displayInventory(bottles, bags, paper_wrapper);
			getCommand(line, info, item_amount, s, bottles, bags, paper_wrapper, name, cost, amount, item_cost, 
					amt_rem, item_num, amount_remaining, money, total_bottle, total_bag, total_paper_wrapper,
					owed, bottle_item, name, cost, item_amount, item_num, denom, currency_quantity,
					quantity_remaining, bag_item, paper_wrapper_item, total);
	    }
	}
}
