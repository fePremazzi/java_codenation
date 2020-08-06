package br.com.codenation.paymentmethods;

public class TransferPayment implements PriceStrategy {

	@Override
	public Double calculate(Double price) {
		return price * 0.92;
	}

}
