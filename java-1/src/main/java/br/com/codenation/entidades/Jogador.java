package br.com.codenation.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador extends EntidadePadrao implements Comparable<Jogador>{
	
	private Long idTime;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;
	private boolean isCapitao;
	
	public Jogador(Long id, String nome, Long idTime, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		super(id, nome);
		this.idTime = idTime;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
		this.isCapitao = false;
	}
	public Long getIdTime() {
		return idTime;
	}
	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}
	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public boolean isCapitao() {
		return isCapitao;
	}
	public void setCapitao(boolean isCapitao) {
		this.isCapitao = isCapitao;
	}
	
	@Override
	public int compareTo(Jogador o) {
		int c = o.getNivelHabilidade().compareTo(this.getNivelHabilidade());
		if (c == 0) {
			return (int)(this.getId() - o.getId());
		}
		return c;
	}
	
	

}
