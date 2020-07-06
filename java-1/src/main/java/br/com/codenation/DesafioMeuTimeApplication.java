package br.com.codenation;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.codenation.entidades.Jogador;
import br.com.codenation.entidades.Time;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private Map<Long, Time> hashTimes = new HashMap<Long, Time>();
	private Map<Long, Jogador> hashJogadores = new HashMap<Long, Jogador>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {

		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		validaIdExiste(time);
		hashTimes.put(id, time);

	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {

		Jogador jogador = new Jogador(id, nome, idTime, dataNascimento, nivelHabilidade, salario);
		validaIdExiste(jogador);
		validaTimeNaoExiste(idTime);

		hashJogadores.put(id, jogador);

	}

	public void definirCapitao(Long idJogador) {
		validaJogadorNaoExiste(idJogador);

		Jogador jogador = hashJogadores.get(idJogador);
		jogador.setCapitao(true);
		hashJogadores.replace(jogador.getId(), jogador);

		Time time = hashTimes.get(jogador.getIdTime());
		Long idVelhoCapitao = time.getIdCapitao();

		if (idVelhoCapitao != 0) {
			Jogador velhoCapitao = hashJogadores.get(idVelhoCapitao);
			velhoCapitao.setCapitao(false);
			hashJogadores.replace(velhoCapitao.getId(), velhoCapitao);
		}

		time.setIdCapitao(idJogador);
		hashTimes.replace(time.getId(), time);

	}

	public Long buscarCapitaoDoTime(Long idTime) {
		validaTimeNaoExiste(idTime);

		Long idCapitao = hashTimes.get(idTime).getIdCapitao();
		if (idCapitao == 0) {
			throw new CapitaoNaoInformadoException();
		}

		return idCapitao;
	}

	public String buscarNomeJogador(Long idJogador) {
		validaJogadorNaoExiste(idJogador);

		return hashJogadores.get(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) {
		validaTimeNaoExiste(idTime);

		return hashTimes.get(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		validaTimeNaoExiste(idTime);
		List<Long> listaJogadores = new ArrayList<>();

		for (Jogador jogador : hashJogadores.values()) {
			if (jogador.getIdTime() == idTime) {
				listaJogadores.add(jogador.getId());
			}
		}
		Collections.sort(listaJogadores);
		return listaJogadores;
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		List<Long> listaJogadores = buscarJogadoresDoTime(idTime);

		Integer maior = Integer.MIN_VALUE;
		Long idMelhorJogador = 0L;
		Jogador jogador;

		for (Long idJogador : listaJogadores) {
			jogador = hashJogadores.get(idJogador);
			if (jogador.getNivelHabilidade() > maior) {
				maior = jogador.getNivelHabilidade();
				idMelhorJogador = jogador.getId();
			}
		}

		return idMelhorJogador;
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		List<Long> listaJogadores = buscarJogadoresDoTime(idTime);

		LocalDate dataMaisVelha = LocalDate.now();
		Long idJogadorMaisVelho = 0L;
		Jogador jogador;

		for (Long idJogador : listaJogadores) {
			jogador = hashJogadores.get(idJogador);
			int comparacao = jogador.getDataNascimento().compareTo(dataMaisVelha);

			if (comparacao < 0) {
				dataMaisVelha = jogador.getDataNascimento();
				idJogadorMaisVelho = jogador.getId();
			} else if (comparacao == 0) {
				if (jogador.getId() < idJogadorMaisVelho) {
					dataMaisVelha = jogador.getDataNascimento();
					idJogadorMaisVelho = jogador.getId();
				}
			}
		}
		return idJogadorMaisVelho;
	}

	public List<Long> buscarTimes() {
		List<Long> listaTimes = new ArrayList<Long>(hashTimes.keySet());
		Collections.sort(listaTimes);
		return listaTimes;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		List<Long> listaJogadores = buscarJogadoresDoTime(idTime);

		BigDecimal maiorSalario = new BigDecimal(0);
		Long idJogadorMaiorSalario = 0L;
		Jogador jogador;

		for (Long idJogador : listaJogadores) {
			jogador = hashJogadores.get(idJogador);
			int comparacao = jogador.getSalario().compareTo(maiorSalario);

			if (comparacao > 0) {
				maiorSalario = jogador.getSalario();
				idJogadorMaiorSalario = jogador.getId();
			} else if (comparacao == 0) {
				if (jogador.getId() < idJogadorMaiorSalario) {
					maiorSalario = jogador.getSalario();
					idJogadorMaiorSalario = jogador.getId();
				}
			}
		}
		return idJogadorMaiorSalario;
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		validaJogadorNaoExiste(idJogador);
		return hashJogadores.get(idJogador).getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> listaJogadores = new ArrayList<>(hashJogadores.values());
		List<Long> listaTop = new ArrayList<Long>();
		
		if (listaJogadores.size() == 0) {
			return listaTop;
		}
		
		Collections.sort(listaJogadores);
		
		for (int i = 0; i < top; i++) {
			listaTop.add(listaJogadores.remove(0).getId());
		}
		return listaTop;
	}

	private void validaIdExiste(Object obj) {
		String className = obj.getClass().getSimpleName();
		switch (className) {
		case "Time":
			if (hashTimes.containsKey(((Time) obj).getId()))
				throw new IdentificadorUtilizadoException();
			break;

		case "Jogador":
			if (hashJogadores.containsKey(((Jogador) obj).getId()))
				throw new IdentificadorUtilizadoException();
			break;
		}
	}

	private void validaTimeNaoExiste(Long id) {
		if (!hashTimes.containsKey(id))
			throw new TimeNaoEncontradoException();
	}

	private void validaJogadorNaoExiste(Long id) {
		if (!hashJogadores.containsKey(id))
			throw new JogadorNaoEncontradoException();
	}

}
