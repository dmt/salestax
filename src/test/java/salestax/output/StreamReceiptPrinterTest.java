package salestax.output;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import static salestax.core.BigDecimalPrice.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import salestax.Item;
import salestax.ReceiptPrinter;
import salestax.ShoppingBasket;
import salestax.output.StreamReceiptPrinter;

@RunWith(MockitoJUnitRunner.class)
public class StreamReceiptPrinterTest {

	private static final String ITEM_OUTPUT = "foo";
	@Mock
	private ShoppingBasket shoppingBasket;
	@Mock
	private Item item;
	private ReceiptPrinter printer;
	private ByteArrayOutputStream baos;

	@Before
	public void setUpPrinter() throws Exception {
		baos = new ByteArrayOutputStream();
		printer = new StreamReceiptPrinter(new PrintStream(baos));
		when(item.toOutputString()).thenReturn(ITEM_OUTPUT);
	}

	@Test
	public void printReceiptPrintsItemToStream() throws Exception {
		when(shoppingBasket.items()).thenReturn(Arrays.asList(item));
		
		printer.printReceipt(shoppingBasket);
		
		assertThat(baos.toString("UTF-8"), containsString(ITEM_OUTPUT));
	}
	@Test 
	public void printsTotalCost() throws Exception {
		when(shoppingBasket.totalCost()).thenReturn(priceOf("1.23"));
		
		printer.printReceipt(shoppingBasket);

		assertThat(baos.toString("UTF-8"), containsString(ReceiptPrinter.TOTAL_PREFIX+"1.23"));
	}
	@Test 
	public void printsTaxes() throws Exception {
		when(shoppingBasket.totalTaxes()).thenReturn(priceOf("3.45"));
		
		printer.printReceipt(shoppingBasket);

		assertThat(baos.toString("UTF-8"), containsString(ReceiptPrinter.TAXES_PREFIX+"3.45"));
	}

}
