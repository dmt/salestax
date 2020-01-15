package salestax;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import salestax.core.ItemTaxHandler;
import salestax.core.TaxApplyingShoppingBasket;
import salestax.core.TaxHandler;
import salestax.input.ProductDefinitions;
import salestax.input.TokenizingItemParser;
import salestax.output.StreamReceiptPrinter;
import salestax.taxes.BasicTax;
import salestax.taxes.ImportDuty;

public class MainIntegrationTest {

	private ByteArrayOutputStream baos;
	private Main main;

	@Before
	public void setUp() throws Exception {
		baos = new ByteArrayOutputStream();
		TaxHandler taxHandler = new ItemTaxHandler(Arrays.asList(BasicTax.atPercent(10), ImportDuty.atPercent(5)));
		main = new Main(new StreamReceiptPrinter(new PrintStream(baos)), 
				new TaxApplyingShoppingBasket(taxHandler), new TokenizingItemParser(new ProductDefinitions()));
	}

	@Test
	public void inputExample1() throws Exception {
		main.printReceipt("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85");

		String output = baos.toString("UTF-8");
		
		assertThat(output, equalTo("1 book : 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83"));
	}
	@Test
	public void inputExample2() throws Exception {
		main.printReceipt("1 imported box of chocolates at 10.00 1 imported bottle of perfume at 47.50");

		String output = baos.toString("UTF-8");
		
		assertThat(output, equalTo("1 imported box of chocolates: 10.50 1 imported bottle of perfume: 54.65 Sales Taxes: 7.65 Total: 65.15"));
	}
	@Test
	public void inputExample3() throws Exception {
		main.printReceipt("1 imported bottle of perfume at 27.99 1 bottle of perfume at 18.99 1 packet of headache pills at 9.75 1 box of imported chocolates at 11.25");

		String output = baos.toString("UTF-8");
		
		assertThat(output, equalTo("1 imported bottle of perfume: 32.19 1 bottle of perfume: 20.89 1 packet of headache pills: 9.75 1 imported box of chocolates: 11.85 Sales Taxes: 6.70 Total: 74.68"));
	}
}
