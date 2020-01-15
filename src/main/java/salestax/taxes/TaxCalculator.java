package salestax.taxes;

import salestax.Price;

public interface TaxCalculator {

	Price calculateTax(Price grossPrice);

}
