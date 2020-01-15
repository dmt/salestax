package salestax.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import salestax.Price;

public class BigDecimalPrice implements Price {

	public static final Price ZERO = new BigDecimalPrice(BigDecimal.ZERO);
	private final BigDecimal amount;
	private final DecimalFormat format = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

	public static Price priceOf(String amount) {
		return new BigDecimalPrice(amount);
	}
	
	public BigDecimalPrice(String amount) {
		this.amount = new BigDecimal(amount);
	}
	
	public BigDecimalPrice(BigDecimal amount) {
		this.amount = BigDecimal.ZERO.add(amount);
	}

	@Override
	public Price add(Price other) {
		return new BigDecimalPrice(amount.add(other.amount()));
	}

	@Override
	public BigDecimal amount() {
		return amount;
	}

	@Override
	public String toString() {
		return format.format(amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		BigDecimalPrice other = (BigDecimalPrice) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (amount.compareTo(other.amount)!=0)
			return false;
		return true;
	}

	
}
