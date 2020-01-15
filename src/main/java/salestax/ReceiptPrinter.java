package salestax;

public interface ReceiptPrinter {
	static final String TOTAL_PREFIX = "Total: ";
	static final String TAXES_PREFIX = "Sales Taxes: ";

	/**
	 * Output the items and totals of the given basket
	 */
	void printReceipt(ShoppingBasket shoppingBasket);

}
