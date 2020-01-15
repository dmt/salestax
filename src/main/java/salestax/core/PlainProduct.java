package salestax.core;

import java.util.EnumSet;

import salestax.ProductAttribute;

/**
 * indeed a very plain product. The base price should probably also end up here
 * if this would evolve further
 */
public class PlainProduct implements Product {

	private final EnumSet<ProductAttribute> attributes;
	private final String name;

	public PlainProduct(String name, EnumSet<ProductAttribute> attributes) {
		this.name = name;
		this.attributes = attributes;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public EnumSet<ProductAttribute> attributes() {
		return EnumSet.copyOf(attributes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PlainProduct other = (PlainProduct) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlainProduct [attributes=" + attributes + ", name=" + name
				+ "]";
	}

	
	

}
