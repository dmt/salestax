package salestax.core;


import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.Item;
import salestax.Tax;
import salestax.core.ItemTaxHandler;
import salestax.core.TaxHandler;

@RunWith(MockitoJUnitRunner.class)
public class ItemTaxHandlerTest {

	@Mock
	private Item item;
	@Mock
	private Tax tax;
	private TaxHandler taxHandler;

	@Before
	public void setUp() throws Exception {
		taxHandler = new ItemTaxHandler(Arrays.asList(tax));
	}

	@Test
	public void appliesTaxesToItems() throws Exception {
		when(tax.appliesTo(item)).thenReturn(true);

		taxHandler.applyTaxes(item);
		
		verify(tax).applyTo(item);
	}
	@Test
	public void onlyAppliesTaxesToMatchingItems() throws Exception {
		when(tax.appliesTo(item)).thenReturn(false);

		taxHandler.applyTaxes(item);
		
		verify(tax, never()).applyTo(item);
	}
}
