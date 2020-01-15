package salestax.core;

import java.util.EnumSet;

import salestax.Item;
import salestax.Price;
import salestax.ProductAttribute;

public class ItemWithProduct implements Item {

	private Price grossPrice;
	private Price taxAmount = BigDecimalPrice.ZERO;
	private final Product product;

	/**
	 * construct a new item with the given product and gross price 
	 */
	public ItemWithProduct(Price price, Product product) {
		this.grossPrice = price;
		this.product = product;
	}

	@Override
	public Price grossPrice() {
		return grossPrice;
	}

	@Override
	public Price taxAmount() {
		return taxAmount;
	}

	@Override
	public void addToTaxAmount(Price price) {
		taxAmount = taxAmount.add(price);
	}
	
	@Override
	public EnumSet<ProductAttribute> attributes() {
		return product.attributes();
	}

	@Override
	public String toOutputString() {
		// FIXME items aren't aggregated so the amount of items is fixed at 1
		return String.format("1 %s: %s", product.name(), grossPrice.add(taxAmount));
	}

	@Override
	public String toString() {
		return "ItemWithProduct [grossPrice=" + grossPrice + ", taxAmount="
				+ taxAmount + ", product=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((grossPrice == null) ? 0 : grossPrice.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((taxAmount == null) ? 0 : taxAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemWithProduct other = (ItemWithProduct) obj;
		if (grossPrice == null) {
			if (other.grossPrice != null)
				return false;
		} else if (!grossPrice.equals(other.grossPrice))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		return true;
	}


}
