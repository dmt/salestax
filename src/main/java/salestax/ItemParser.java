package salestax;

import java.util.List;

/**
 * ItemParsers are expected to know how to create {@link Item}s 
 * from a given input string 
 */
public interface ItemParser {

	List<Item> itemsFrom(String input);
}
