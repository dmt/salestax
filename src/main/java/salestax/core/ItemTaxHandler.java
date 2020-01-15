package salestax.core;

import java.util.List;

import salestax.Item;
import salestax.Tax;

public class ItemTaxHandler implements TaxHandler {

	private final List<Tax> taxes;

	public ItemTaxHandler(List<Tax> taxes) {
		this.taxes = taxes;
	}

	@Override
	public void applyTaxes(Item item) {
		for (Tax tax : taxes) {
			if (tax.appliesTo(item))
				tax.applyTo(item);
		}
	}

}
