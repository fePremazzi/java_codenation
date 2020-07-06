package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {

		boolean isFim = false;
		List<Integer> listFibonacci = new ArrayList<>();
		listFibonacci.add(new Integer(0));
		listFibonacci.add(new Integer(1));

		while (!isFim) {
			Integer proximo = listFibonacci.get(listFibonacci.size() - 1) + listFibonacci.get(listFibonacci.size() - 2);
			listFibonacci.add(proximo);
			
			if (listFibonacci.get(listFibonacci.size()-1) > 350)
				isFim = true;			
		}

		return listFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
		
		if(fibonacci().contains(a))
			return true;
		else
			return false;
	}

}