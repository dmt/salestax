package salestax.core;

import java.util.EnumSet;

import salestax.ProductAttribute;

public interface Product {

	String name();

	/**
	 * @return attributes describing this product
	 */
	EnumSet<ProductAttribute> attributes();
	/**
	 * products are considered equal if they have the same name and attributes
	 */
	@Override
	boolean equals(Object obj);
	@Override
	int hashCode();
}
