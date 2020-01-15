package salestax.taxes;

import salestax.Item;
import salestax.ProductAttribute;
import salestax.Tax;

/**
 * Basic Tax matches on items having no attribute 
 * {@link ProductAttribute#BASIC_TAX_EXEMPT}
 */
public class BasicTax extends AbstractTax implements Tax {

	public BasicTax(TaxCalculator taxCalculator) {
		super(taxCalculator);
	}

	@Override
	public boolean appliesTo(Item item) {
		return !item.attributes().contains(ProductAttribute.BASIC_TAX_EXEMPT);
	}

	public static Tax atPercent(int percentage) {
		return new BasicTax(new RoundUpToFiveCent(percentage));
	}

}
