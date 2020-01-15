package salestax;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MainTest {

	@Mock
	private ReceiptPrinter receiptPrinter;
	@Mock
	private ShoppingBasket shoppingBasket;
	@Mock
	private ItemParser itemParser;
	private Main main;
	private String inputString = "foo";

	@Before
	public void setupMain() {
		main = new Main(receiptPrinter, shoppingBasket, itemParser);
	}
	
	@Test
	public void printsAReceipt() throws Exception {
		main.printReceipt(inputString);
		
		verify(receiptPrinter).printReceipt(shoppingBasket);
	}
	@Test
	public void addsItemsFromInputToShoppingBasket() throws Exception {
		Item item = mock(Item.class);
		when(itemParser.itemsFrom(inputString)).thenReturn(Arrays.asList(item));
		
		main.printReceipt(inputString);
		
		verify(shoppingBasket).addItem(item);
	}
}
