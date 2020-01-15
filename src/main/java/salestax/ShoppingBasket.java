package salestax;

import java.util.List;

public interface ShoppingBasket {

	List<Item> items();

	void addItem(Item item);

	/**
	 * @return the cost of all items, including taxes
	 */
	Price totalCost();

	/**
	 * @return the amount of taxes for all items
	 */
	Price totalTaxes();

}
