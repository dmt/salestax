package salestax;

import java.math.BigDecimal;

/**
 * Wrapper around underlying representation of price amounts. 
 */
public interface Price {

	/**
	 * Add the other to this and return the result as a new Price
	 */
	Price add(Price other);

	/**
	 * @return the underlying amount as a BigDecimal
	 */
	BigDecimal amount();
	
	/**
	 * Prices are considered equal if the amounts they represent are equal 
	 */
	@Override
	boolean equals(Object obj);

	@Override
	public int hashCode();
}
