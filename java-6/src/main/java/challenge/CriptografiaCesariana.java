package challenge;

public class CriptografiaCesariana implements Criptografia {

	@Override
	public String criptografar(String texto) {
		
		if(texto.isEmpty())
			throw new IllegalArgumentException();

		int nCasas = 3;
		char[] normal = texto.toLowerCase().toCharArray();
		char[] cifrado = new char[normal.length];

		for (int i = 0; i < normal.length; i++) {
			int ascCharacter = (int) normal[i] - 97;
			
			if (ascCharacter >= 0 && ascCharacter < 26) 
				cifrado[i] = (char) (97 + (ascCharacter + nCasas) % 25);
			else
				cifrado[i] = (char) (ascCharacter + 97);
		}
		return new String(cifrado);
	}

	@Override
	public String descriptografar(String texto) {
		
		if(texto.isEmpty())
			throw new IllegalArgumentException();

		int nCasas = 3;
		char[] cifrado = texto.toLowerCase().toCharArray();
		char[] output = new char[cifrado.length];

		for (int i = 0; i < cifrado.length; i++) {
			int ascCharacter = (int) cifrado[i];

			if (ascCharacter > 96 && ascCharacter < 123) {
				int novaPosicao = ascCharacter - nCasas;
				
				if (novaPosicao < 97) 
					output[i] = (char) (26 + novaPosicao);
				else 
					output[i] = (char) novaPosicao;
				
			} else {
				output[i] = cifrado[i];
			}
		}

		return new String(output);

	}
}
