package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		// Use o Math.round apenas no final do método para arredondar o valor final.
		// Documentação do método:
		// https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		if (salarioBase >= 1039.00) {
			
			salarioBase = salarioBase - calcularInss(salarioBase);
			
			return Math.round(salarioBase - calculaIRRF(salarioBase));
		} else
			return Math.round(0.0);

	}

	// Exemplo de método que pode ser criado para separar melhor as
	// responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500)
			return salarioBase * 0.08;
		else if (salarioBase <= 4000)
			return salarioBase * 0.09;
		else
			return salarioBase * 0.11;
	}

	private double calculaIRRF(double salarioReferencia) {
		if (salarioReferencia <= 3000)
			return 0.0;
		else if (salarioReferencia <= 6000)
			return salarioReferencia * 0.075;
		else
			return salarioReferencia * 0.15;
	}

}
/*
 * Dúvidas ou Problemas? Manda e-mail para o meajuda@codenation.dev que iremos
 * te ajudar!
 */