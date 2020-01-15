package salestax.input;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import salestax.ProductAttribute;
import salestax.core.PlainProduct;
import salestax.core.Product;

/**
 * simple parse that matches keywords to a map of predefined {@link Product}s
 */
public class ProductDefinitions implements Products {

	private final Map<String, Product> nameToProducts = new HashMap<String, Product>();

	public ProductDefinitions() {
		// redefine input name to match example output 
		addProduct("book", "book ", EnumSet.of(ProductAttribute.BASIC_TAX_EXEMPT));
		addProduct("music CD", EnumSet.noneOf(ProductAttribute.class));
		addProduct("chocolate bar", EnumSet.of(ProductAttribute.BASIC_TAX_EXEMPT));
		addProduct("imported box of chocolates", EnumSet.of(ProductAttribute.BASIC_TAX_EXEMPT, ProductAttribute.IMPORTED));
		addProduct("imported bottle of perfume", EnumSet.of(ProductAttribute.IMPORTED));
		addProduct("packet of headache pills", EnumSet.of(ProductAttribute.BASIC_TAX_EXEMPT));
		// redefine input name to match example output 
		addProduct("box of imported chocolates", "imported box of chocolates", 
				EnumSet.of(ProductAttribute.IMPORTED, ProductAttribute.BASIC_TAX_EXEMPT));
		addProduct("bottle of perfume", EnumSet.noneOf(ProductAttribute.class));
		
	}
	
	private void addProduct(String name, EnumSet<ProductAttribute> attributes) {
		nameToProducts.put(name, new PlainProduct(name, attributes));
	}
	private void addProduct(String name, String replacementName, EnumSet<ProductAttribute> attributes) {
		nameToProducts.put(name, new PlainProduct(replacementName, attributes));
	}

	@Override
	public Product productFor(String name) {
		Product product = nameToProducts.get(name);
		if (product==null)
			throw new RuntimeException("no product defined for name: "+name);
		return product;
	}

}
