package salestax;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import salestax.core.ItemTaxHandler;
import salestax.core.TaxApplyingShoppingBasket;
import salestax.core.TaxHandler;
import salestax.input.ProductDefinitions;
import salestax.input.TokenizingItemParser;
import salestax.output.StreamReceiptPrinter;
import salestax.taxes.BasicTax;
import salestax.taxes.ImportDuty;

/**
 * main entry point. Expects a single argument containing the items 
 * to be printed.
 * 
 * Prints to sysout by default
 *
 * @see ProductDefinitions 
 */
public class Main {

	private final ReceiptPrinter receiptPrinter;
	private final ShoppingBasket shoppingBasket;
	private final ItemParser itemParser;
	private static final List<Tax> taxes = Arrays.asList(BasicTax.atPercent(10), ImportDuty.atPercent(5));

	public Main() {
		this(System.out);
	}
	public Main(PrintStream stream) {
		TaxHandler taxHandler = new ItemTaxHandler(taxes);
		this.receiptPrinter = new StreamReceiptPrinter(System.out);
		this.shoppingBasket = new TaxApplyingShoppingBasket(taxHandler);
		this.itemParser = new TokenizingItemParser(new ProductDefinitions());
	}
	
	public Main(ReceiptPrinter receiptPrinter, ShoppingBasket shoppingBasket, ItemParser itemParser) {
		this.receiptPrinter = receiptPrinter;
		this.shoppingBasket = shoppingBasket;
		this.itemParser = itemParser;
	}

	public static void main(String[] args) {
		Main main = new Main();
		if (args.length!=1) {
			printUsage();
			System.exit(1);
		}
		// assuming input is quoted
		main.printReceipt(args[0]);
	}

	private static void printUsage() {
		System.err.println("expected single quoted parameter");
	}

	public void printReceipt(String input) {
		List<Item> items = itemParser.itemsFrom(input);
		for (Item item : items) {
			shoppingBasket.addItem(item);
		}
		receiptPrinter.printReceipt(shoppingBasket);
	}
}
