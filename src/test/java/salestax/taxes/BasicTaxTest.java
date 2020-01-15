package salestax.taxes;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.ProductAttribute;
import salestax.taxes.BasicTax;

@RunWith(MockitoJUnitRunner.class)
public class BasicTaxTest extends AbstractTaxTest {

	@Before
	public void setUp() throws Exception {
		tax = new BasicTax(taxCalculator);
		when(item.grossPrice()).thenReturn(priceOf("10"));
	}
	
	@Test
	public void appliesToItemsWithoutExemption() throws Exception {
		when(item.attributes()).thenReturn(EnumSet.noneOf(ProductAttribute.class));
		
		boolean appliesToItem = tax.appliesTo(item);
		
		assertThat(appliesToItem, is(true));
	}
	@Test
	public void doesNotApplyToItemsWithExemption() throws Exception {
		when(item.attributes()).thenReturn(EnumSet.of(ProductAttribute.BASIC_TAX_EXEMPT));
		
		boolean appliesToItem = tax.appliesTo(item);
		
		assertThat(appliesToItem, is(false));
	}

}
