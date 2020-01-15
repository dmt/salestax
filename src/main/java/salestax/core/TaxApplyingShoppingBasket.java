package salestax.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import salestax.Item;
import salestax.Price;
import salestax.ShoppingBasket;

/**
 * ShoppingBasket implementation that calculates and adds taxes
 * to items as soon as they are added
 *
 */
public class TaxApplyingShoppingBasket implements ShoppingBasket {

	private List<Item> items = new ArrayList<Item>();
	private final TaxHandler taxHandler;
	private Price totalCost = BigDecimalPrice.ZERO;
	private Price taxes = BigDecimalPrice.ZERO;
	public TaxApplyingShoppingBasket(TaxHandler taxHandler) {
		this.taxHandler = taxHandler;
	}

	@Override
	public List<Item> items() {
		return Collections.unmodifiableList(items);
	}

	/**
	 * add an item to this shopping basket and apply taxes
	 */
	@Override
	public void addItem(Item item) {
		items.add(item);
		taxHandler.applyTaxes(item);
		addItemToTotals(item);
	}

	private void addItemToTotals(Item item) {
		totalCost = totalCost.add(item.taxAmount()).add(item.grossPrice());
		taxes = taxes.add(item.taxAmount());
	}

	@Override
	public Price totalCost() {
		return totalCost;
	}

	@Override
	public Price totalTaxes() {
		return taxes;
	}

}
