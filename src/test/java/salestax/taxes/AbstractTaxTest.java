package salestax.taxes;

import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import org.junit.Test;
import org.mockito.Mock;

import salestax.Item;
import salestax.Price;
import salestax.Tax;
import salestax.taxes.TaxCalculator;

public abstract class AbstractTaxTest {
	@Mock
	Item item;
	@Mock
	TaxCalculator taxCalculator;
	Tax tax;

	@Test
	public void applyCalculatesTaxAmountForItem() throws Exception {
		
		tax.applyTo(item);
		
		verify(taxCalculator).calculateTax(item.grossPrice());
	}
	@Test
	public void applyAddsTaxAmounttoItem() throws Exception {
		Price taxAmount = priceOf("1");
		when(taxCalculator.calculateTax(item.grossPrice())).thenReturn(taxAmount);
		
		tax.applyTo(item);
		
		verify(item).addToTaxAmount(taxAmount);
	}

}
