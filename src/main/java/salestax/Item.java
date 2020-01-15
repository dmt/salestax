package salestax;

import java.util.EnumSet;

import salestax.core.TaxHandler;


/**
 * Item represents items in a shopping cart.
 * The amount of tax will be calculated by a {@link TaxHandler}
 * so isn't expected to set on instantiation 
 */
public interface Item {

	/**
	 * @return return the price of the item before taxes. Never null 
	 */
	Price grossPrice();

	/**
	 * As taxes are added the result of this 
	 * call can vary.
	 * @return the current calculated tax amount
	 */
	Price taxAmount();

	/**
	 * add a calculated amount of tax to the item
	 * @param price
	 */
	void addToTaxAmount(Price price);

	/**
	 * @return a set of attributes describing the product contained
	 */
	EnumSet<ProductAttribute> attributes();

	/**
	 * @return a String suitable for printing to the receipt
	 */
	String toOutputString();
	
}
