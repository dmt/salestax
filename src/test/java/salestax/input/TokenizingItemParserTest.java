package salestax.input;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.Item;
import salestax.ItemParser;
import salestax.core.ItemWithProduct;
import salestax.core.Product;

@RunWith(MockitoJUnitRunner.class)
public class TokenizingItemParserTest {

	private ItemParser parser;
	@Mock
	private Products products;
	@Mock
	private Product book;
	@Mock
	private Product musicCd;

	@Before
	public void setUp() throws Exception {
		parser = new TokenizingItemParser(products);
	}

	@Test
	public void setsAttributesFromParser() throws Exception {
		when(products.productFor("book")).thenReturn(book);
		
		List<Item> items = parser.itemsFrom("1 book at 1.00");
		
		Item newBook = new ItemWithProduct(priceOf("1.00"), book);
		assertThat(items, hasItem(equalTo(newBook)));
	}

	@Test
	public void parsesOneItemInString() throws Exception {
		when(products.productFor("book")).thenReturn(book);
		
		List<Item> items = parser.itemsFrom("1 book at 1.00");
		
		Item newBook = new ItemWithProduct(priceOf("1.00"), book);
		assertThat(items, hasItem(equalTo(newBook)));
	}
	@Test
	public void parsesMultiWordProductNameInString() throws Exception {
		when(products.productFor("music cd")).thenReturn(musicCd);
		
		List<Item> items = parser.itemsFrom("1 music cd at 2.00");
		
		Item cd = new ItemWithProduct(priceOf("2.00"), musicCd);
		assertThat(items, hasItem(equalTo(cd)));
	}
	@SuppressWarnings("unchecked")
	@Test
	public void parsesMultipleItems() throws Exception {
		when(products.productFor("book")).thenReturn(book);
		when(products.productFor("music cd")).thenReturn(musicCd);
		
		List<Item> items = parser.itemsFrom("1 music cd at 2.00 1 book at 1.00");
		
		Item cd = new ItemWithProduct(priceOf("2.00"), musicCd);
		Item newBook = new ItemWithProduct(priceOf("1.00"), book);
		assertThat(items, hasItems(equalTo(newBook), equalTo(cd)));
	}
	
}
