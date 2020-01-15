package salestax.core;

import salestax.Item;

public interface TaxHandler {

	/**
	 * calculate and add taxes to the given 
	 * item 
	 */
	void applyTaxes(Item anItem);

}
