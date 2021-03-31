package br.edu.ifsul.ia;


public class Item {

	private String nome;
	private Double peso;
	private Double beneficio;

	public Item(String nome, Double peso, Double beneficio) {
		super();
		this.nome = nome;
		this.peso = peso;
		this.beneficio = beneficio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Double beneficio) {
		this.beneficio = beneficio;
	}

}
