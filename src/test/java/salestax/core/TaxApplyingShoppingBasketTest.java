package salestax.core;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.Item;
import salestax.Price;
import salestax.ShoppingBasket;
import salestax.core.BigDecimalPrice;
import salestax.core.TaxApplyingShoppingBasket;
import salestax.core.TaxHandler;

@RunWith(MockitoJUnitRunner.class)
public class TaxApplyingShoppingBasketTest {

	private ShoppingBasket basket;
	@Mock
	private TaxHandler taxHandler;

	@Before
	public void setUp() throws Exception {
		basket = new TaxApplyingShoppingBasket(taxHandler);
	}

	@Test
	public void addedItemsShowUpInItems() {
		Item item = anItem("1.23");

		basket.addItem(item);
		
		assertThat(basket.items(), hasItem(item));
	}
	@Test
	public void totalCostSumsUpEachItem() throws Exception {
		basket.addItem(anItem("10"));
		basket.addItem(anItem("2"));
		basket.addItem(anItem("0.34"));
		
		Price totalCost = basket.totalCost();
		
		assertThat(totalCost, equalTo((Price)priceOf("12.34")));
	}
	@Test
	public void addItemCalculatesTaxOnItem() throws Exception {
		Item anItem = anItem("10");

		basket.addItem(anItem);
		
		verify(taxHandler).applyTaxes(anItem);
	}
	@Test
	public void totalTaxReturnsCalculatedTaxes() throws Exception {
		Item anItem = anItem("10");
		Price taxForItem = priceOf("1");
		when(anItem.taxAmount()).thenReturn(taxForItem);
		basket.addItem(anItem);
		
		Price taxes = basket.totalTaxes();
		
		assertThat(taxes, equalTo(taxForItem));
	}

	private Item anItem(final String price) {
		Item item = mock(Item.class);
		when(item.grossPrice()).thenReturn(new BigDecimalPrice(price));
		when(item.taxAmount()).thenReturn(BigDecimalPrice.ZERO);
		return item;
	}

}
