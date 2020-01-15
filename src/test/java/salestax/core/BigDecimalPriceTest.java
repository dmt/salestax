package salestax.core;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static salestax.core.BigDecimalPrice.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import salestax.Price;
import salestax.core.BigDecimalPrice;

public class BigDecimalPriceTest {

	private static final String AMOUNT = "1.12";
	private Price price;

	@Before
	public void setUp() throws Exception {
		price = new BigDecimalPrice(AMOUNT);
	}

	@Test
	public void staticHelperBuildsPriceOfAmount() throws Exception {
		Price staticBuildPrice = BigDecimalPrice.priceOf(AMOUNT);
		Price newedPrice = new BigDecimalPrice(AMOUNT);
		
		assertThat(staticBuildPrice, equalTo(newedPrice));
	}
	@Test
	public void addPriceReturnsNewPriceSummed() throws Exception {
		Price sum = price.add(priceOf("2.23"));
		
		assertThat(sum.amount(), equalTo(BigDecimal.valueOf(3.35)));
	}
	@Test
	public void toStringContainsAmount() throws Exception {
		String priceString = price.toString();
		
		assertThat(priceString, equalTo(AMOUNT));
	}
	@Test
	public void toStringUsesTwoFractionDigits() throws Exception {
		String priceString = priceOf("0.5").toString();

		
		assertThat(priceString, equalTo("0.50"));
	}
	@Test
	public void equalsComparesAmount() throws Exception {
		Price anotherPrice = new BigDecimalPrice(AMOUNT);
		
		assertThat(anotherPrice, equalTo(price));
	}
	@Test
	public void equalsIsFalseForDifferentAmount() throws Exception {
		Price anotherPrice = priceOf("3.34");
		
		assertThat(anotherPrice, not(equalTo(price)));
	}
}
