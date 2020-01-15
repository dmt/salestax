package salestax.output;

import java.io.PrintStream;

import salestax.Item;
import salestax.ReceiptPrinter;
import salestax.ShoppingBasket;

/**
 * print shopping basket contents to a given stream
 */
public class StreamReceiptPrinter implements ReceiptPrinter {

	private static final char DELIMITER = ' ';
	private final PrintStream out;

	public StreamReceiptPrinter(PrintStream out) {
		this.out = out;
	}

	@Override
	public void printReceipt(ShoppingBasket shoppingBasket) {
		for (Item item : shoppingBasket.items()) {
			out.print(item.toOutputString());
			out.print(DELIMITER);
		}
		out.print(TAXES_PREFIX+shoppingBasket.totalTaxes());
		out.print(DELIMITER);
		out.print(TOTAL_PREFIX+shoppingBasket.totalCost());
	}

}
