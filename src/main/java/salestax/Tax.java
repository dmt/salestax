package salestax;

public interface Tax {

	/**
	 * calculate the amount of Tax for a given item and add it to its tax amount
	 */
	void applyTo(Item item);

	/**
	 * check whether this tax should be applied to the item
	 */
	boolean appliesTo(Item item);

}
