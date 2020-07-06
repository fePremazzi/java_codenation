package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

	private int MAX_VAGAS = 10;
	private int MAX_IDADE = 55;
	private List<Carro> listaCarrosEstacionados = new ArrayList<>();

	// max 10 vagas
	// informar placa, cor e motorista
	// OK mottorista != null (exception)
	// OK motorista.idade >18 && motorista.habilitacao != vazio (exception)
	// OK motorista.pontos < 21 (exception)
	// OK nao pode passar de 10 carros estacionados

	// se lotado:
	// primeiro a entrar sai para novo carro
	// 		porem só sai se tiver menos de 55, verifica a
	// 			ordem de entrada ate achar algume com menos de 55
	// 		se todos tiverem mais de 55 anos, carro nao estaciona (exception)

	public void estacionar(Carro carro) {
		validaCarro(carro);
		
		if (carrosEstacionados() < MAX_VAGAS) {
			
			listaCarrosEstacionados.add(carro);
			
		} else {
			
			for (Carro carEstacioando : listaCarrosEstacionados) {
				
				if(carEstacioando.getMotorista().getIdade() < MAX_IDADE) {
					listaCarrosEstacionados.remove(carEstacioando);
					listaCarrosEstacionados.add(carro);
					break;
				}
			}
			if (!listaCarrosEstacionados.contains(carro)) {
				throw new EstacionamentoException(null);
			}
			
		}

		
	}

	public int carrosEstacionados() {
		return listaCarrosEstacionados.size();
	}

	public boolean carroEstacionado(Carro carro) {
		for (Carro carEstacioando : listaCarrosEstacionados) {
			if(carEstacioando.equals(carro)) {
				return true;
			}
		}
		return false;
	}

	private void validaCarro(Carro carro) {
		if (carro.getMotorista() == null) {
			throw new EstacionamentoException(null);
		} else if (carro.getMotorista().getIdade() < 18) {
			throw new EstacionamentoException(null);
		} else if (carro.getMotorista().getPontos() > 20) {
			throw new EstacionamentoException(null);
		} else if (carro.getMotorista().getPontos() < 0) {
			throw new IllegalArgumentException();
		}

	}
}
