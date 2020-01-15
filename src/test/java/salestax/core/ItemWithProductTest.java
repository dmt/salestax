package salestax.core;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.Item;
import salestax.ProductAttribute;

@RunWith(MockitoJUnitRunner.class)
public class ItemWithProductTest {
	private static final String PRODUCT_NAME = "chocolate";
	
	@Mock
	private Product product;

	private Item item;

	@Before
	public void setUpProduct() {
		item = new ItemWithProduct(priceOf("100"), product);
		when(product.name()).thenReturn(PRODUCT_NAME);
	}
	
	@Test
	public void addingTaxAddsToTaxAmount() throws Exception {
		
		item.addToTaxAmount(priceOf("10.00"));
		assertThat(item.taxAmount(), is(priceOf("10")));

		item.addToTaxAmount(priceOf("5.00"));
		assertThat(item.taxAmount(), is(priceOf("15")));
	}
	
	@Test
	public void outputStringContainsComputedPrice() throws Exception {
		item.addToTaxAmount(priceOf("10"));
		
		String toString = item.toOutputString();
		
		assertThat(toString, containsString("110.00"));
	}
	@Test
	public void outputStringContainsProductName() throws Exception {
		
		String toString = item.toOutputString();
		
		assertThat(toString, containsString(PRODUCT_NAME));
	}
	@Test
	public void equalsComparesOnProperties() throws Exception {
		Item anotherItem = new ItemWithProduct(priceOf("100"), product);
		
		assertThat(anotherItem, equalTo(item));
	}
	@Test
	public void attributesDelegatesToProduct() throws Exception {
		EnumSet<ProductAttribute> attributes = item.attributes();

		assertThat(attributes, equalTo(product.attributes()));
	}
}
