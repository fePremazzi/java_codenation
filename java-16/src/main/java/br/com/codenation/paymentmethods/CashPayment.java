package br.com.codenation.paymentmethods;

public class CashPayment implements PriceStrategy {

	@Override
	public Double calculate(Double price) {
		return price * 0.90;
	}

}
