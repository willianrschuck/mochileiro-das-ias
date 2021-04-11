package br.edu.ifsul.ia;


public class Produto {

	private String nome;
	private Double volume;
	private Double valor;
	private String destinatario;

	public Produto(String nome, Double peso, Double beneficio, String destinatario) {
		super();
		this.nome = nome;
		this.volume = peso;
		this.valor = beneficio;
		this.destinatario = destinatario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getPreco() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
