package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;

		for (int i : elements) {
			soma += i;
		}
		return soma / elements.length;
	}

	public static int mode(int[] elements) {
		elements = ordenaArray(elements);
		int indice = 0;
		int moda = 1;
		int qtde = 1;
		for (int i = 1; i < elements.length; i++) {
			if (elements[i - 1] == elements[i])
				qtde++;

			if (qtde > moda) {
				moda = qtde;
				indice = i;
			} else {
				qtde = 1;
			}
		}
		return elements[indice];
	}

	public static int median(int[] elements) {
		elements = ordenaArray(elements);

		if (elements.length % 2 == 0) {
			int centro = elements.length / 2;
			return (elements[centro - 1] + elements[centro]) / 2;
		} else {
			return elements[(int) elements.length / 2];
		}
	}

	private static int[] ordenaArray(int[] arr) {
		Arrays.sort(arr);
		return arr;
	}
}