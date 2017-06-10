package view;

import java.io.Serializable;
import java.text.DecimalFormat;


/**
 * Created by Travis on 6/7/17.
 */
@SuppressWarnings("serial")
public class Item implements Serializable
{
	 private String name;
	 private double price;
	    
	 public Item(String the_name, String the_price)
	 {
		 name = the_name;
		 
		 double temp = Double.parseDouble(the_price);
		 
		 price = temp;
	 }
	 
	 @Override
	public String toString()
	 {
		 DecimalFormat df = new DecimalFormat("#.00"); 
		 
		 return "Item Name: " + name + ", Price: $" + df.format(price);
	 }
	    
	 public String getName()
	 {
	     return name;
	 }
	
 	 public double getPrice()
 	 {
	     return price;
	 }
	
	 public void setName(String newName)
	 {
		 name = newName;
	 }
	
	 public void setPrice(Double newPrice)
	 {
		 price = newPrice;
	 }
}
