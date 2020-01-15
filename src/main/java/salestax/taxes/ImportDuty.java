package salestax.taxes;

import salestax.Item;
import salestax.ProductAttribute;
import salestax.Tax;

/**
 * ImportDuty matches on items with the attribute 
 * {@link ProductAttribute#IMPORTED}
 */
public class ImportDuty extends AbstractTax {

	public ImportDuty(TaxCalculator taxCalculator) {
		super(taxCalculator);
	}

	@Override
	public boolean appliesTo(Item item) {
		return item.attributes().contains(ProductAttribute.IMPORTED);
	}

	public static Tax atPercent(int percentage) {
		return new ImportDuty(new RoundUpToFiveCent(percentage));
	}

}
