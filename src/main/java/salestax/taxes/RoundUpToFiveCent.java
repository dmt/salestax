package salestax.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import salestax.Price;
import salestax.core.BigDecimalPrice;

/**
 * calculate a given percentage and round the result 
 * up to the nearest 5 cent.
 * 
 * It might make sense to move this into {@link Price}
 */
public class RoundUpToFiveCent implements TaxCalculator {

	private static final BigDecimal FIVE_CENT = BigDecimal.valueOf(0.05);
	private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
	private final BigDecimal taxRate;

	public RoundUpToFiveCent(int taxRate) {
		this.taxRate = BigDecimal.valueOf(taxRate);
	}

	@Override
	public Price calculateTax(Price grossPrice) {
		return new BigDecimalPrice(roundUp(percentageOf(grossPrice.amount())));
	}

	private BigDecimal roundUp(BigDecimal toRound) {
		return toRound.divide(FIVE_CENT, 0, RoundingMode.UP).multiply(FIVE_CENT);
	}

	private BigDecimal percentageOf(BigDecimal amount) {
		return amount.multiply(taxRate).divide(HUNDRED);
	}

}
