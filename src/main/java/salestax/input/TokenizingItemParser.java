package salestax.input;

import static salestax.core.BigDecimalPrice.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import salestax.Item;
import salestax.ItemParser;
import salestax.Price;
import salestax.core.ItemWithProduct;
import salestax.core.Product;

/**
 * parse input by splitting it around spaces.
 * Expects input of the form "([count] [name] at [price] )*"
 */
public class TokenizingItemParser implements ItemParser {

	private static final String TOKEN_DELIMITER = " ";
	private static final Object PRICE_DELIMITER = "at";
	private final Products products;

	public TokenizingItemParser(Products products) {
		this.products = products;
	}

	@Override
	public List<Item> itemsFrom(String input) {
		List<Item> items = new ArrayList<Item>();
		Queue<String> tokenQueue = new LinkedList<String>(Arrays.asList(input.split(TOKEN_DELIMITER)));
		while(!tokenQueue.isEmpty()) {
			ItemWithProduct item = consumeItem(tokenQueue);
			items.add(item);
		}
		return items;
	}

	private ItemWithProduct consumeItem(Queue<String> split) {
		consumeItemCount(split);
		String name = consumeName(split);
		Product product = productFor(name);
		Price price = consumePrice(split);
		ItemWithProduct item = new ItemWithProduct(price, product);
		return item;
	}

	private Product productFor(String name) {
		return products.productFor(name);
	}

	private Price consumePrice(Queue<String> list) {
		return priceOf(list.remove());
	}

	private String consumeName(Queue<String> list) {
		StringBuffer name = new StringBuffer();
		// FIXME ignoring product names containing " at " 
		while(true) {
			name.append(list.remove());
			if (list.peek().equals(PRICE_DELIMITER)) {
				list.remove();
				break;
			} else {
				name.append(TOKEN_DELIMITER);
			}
		}
		return name.toString();
	}

	private void consumeItemCount(Queue<String> list) {
		// could peek and check for number for better error handling
		list.remove();
	}


}
