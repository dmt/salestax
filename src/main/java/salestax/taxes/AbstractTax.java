package salestax.taxes;

import salestax.Item;
import salestax.Price;
import salestax.Tax;

/**
 * abstract base class implementing applyTo
 * by calculating and adding tax from a {@link TaxCalculator} 
 */
public abstract class AbstractTax implements Tax {

	private final TaxCalculator taxCalculator;

	public AbstractTax(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	@Override
	public void applyTo(Item item) {
		Price taxAmount = taxCalculator.calculateTax(item.grossPrice());
		item.addToTaxAmount(taxAmount);
	}

}