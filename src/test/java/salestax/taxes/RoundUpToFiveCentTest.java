package salestax.taxes;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static salestax.core.BigDecimalPrice.*;

import org.junit.Before;
import org.junit.Test;

import salestax.Price;
import salestax.taxes.RoundUpToFiveCent;

public class RoundUpToFiveCentTest {

	private RoundUpToFiveCent roundUpToFiveCent;
	private Price price;

	@Before
	public void setUp() throws Exception {
		roundUpToFiveCent = new RoundUpToFiveCent(10);
	}

	@Test
	public void doesSimplePercentCalc() throws Exception {
		price = priceOf("100");
		
		Price tenPercent = roundUpToFiveCent.calculateTax(price);
		
		assertThat(tenPercent, equalTo(priceOf("10")));
	}
	@Test
	public void roundsTenPercentOf20CentTo5Cent() throws Exception {
		price = priceOf("0.20");
		
		Price roundedPercentage = roundUpToFiveCent.calculateTax(price);
		
		assertThat(roundedPercentage, equalTo(priceOf("0.05")));
	}
	@Test
	public void roundsTenPercentOf60CentTo10Cent() throws Exception {
		price = priceOf("0.60");
		
		Price roundedPercentage = roundUpToFiveCent.calculateTax(price);
		
		assertThat(roundedPercentage, equalTo(priceOf("0.10")));
	}
}
