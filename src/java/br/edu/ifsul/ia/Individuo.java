package br.edu.ifsul.ia;


import java.util.ArrayList;
import java.util.List;

public class Individuo implements Comparable<Individuo> {
	
	private List<Double> pesos = new ArrayList<Double>();
	private List<Double> beneficos = new ArrayList<Double>();
	private Double limitePeso;
	private Double notaAvaliacao;
	private Double pesoUsado;
	private int geracao;
	private List<String> cromossomos = new ArrayList<String>();
	
	public Individuo(List<Double> pesos, List<Double> beneficios, Double limitePeso) {
		super();
		this.pesos = pesos;
		this.beneficos = beneficios;
		this.limitePeso = limitePeso;
		this.notaAvaliacao = 0.0;
		this.pesoUsado = 0.0;
		this.geracao = 0;
		
		for (int i = 0; i < this.pesos.size(); i++) {
			if (java.lang.Math.random() < 0.5) {
				this.cromossomos.add("0");
			} else {
				this.cromossomos.add("1");		
			}
		}
		
	}
	
	public void avaliar() {
		Double nota = 0.0;
		Double somaPesos = 0.0;
		for (int i = 0; i < cromossomos.size(); i++) {
			if (this.cromossomos.get(i) == "1") {
				nota += beneficos.get(i);
				somaPesos += pesos.get(i);
			}
		}
		if (somaPesos > this.limitePeso) {
			nota = 1.0;
		}
		this.notaAvaliacao = nota;
		this.pesoUsado = somaPesos;
	}
	
	public void setCromossomos(List<String> cromossomos) {
		this.cromossomos = cromossomos;
	}
	
	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}
	
	public Double getNotaAvaliacao() {
		return notaAvaliacao;
	}
	
	public int getGeracao() {
		return geracao;
	}
	
	public List<Double> getBeneficos() {
		return beneficos;
	}
	
	public Double getLimitePeso() {
		return limitePeso;
	}
	
	public List<String> getCromossomos() {
		return cromossomos;
	}
	
	public List<Double> getPesos() {
		return pesos;
	}
	
	
	public List<Individuo> cruzar(Individuo outro) {
		
		int indexDeCorte = (int) Math.round(Math.random() * this.cromossomos.size());
		
		List<String> cromossomosFilhoUm = new ArrayList<String>();
		cromossomosFilhoUm.addAll(outro.cromossomos.subList(0, indexDeCorte));
		cromossomosFilhoUm.addAll(this.cromossomos.subList(indexDeCorte, this.cromossomos.size()));
		
		List<String> cromossomosFilhosDois = new ArrayList<String>();
		cromossomosFilhosDois.addAll(this.cromossomos.subList(0, indexDeCorte));
		cromossomosFilhosDois.addAll(outro.cromossomos.subList(indexDeCorte, this.cromossomos.size()));
		
		List<Individuo> filhos = new ArrayList<Individuo>();
		filhos.add(new Individuo(this.pesos, this.beneficos, this.limitePeso));
		filhos.add(new Individuo(this.pesos, this.beneficos, this.limitePeso));
		
		filhos.get(0).setCromossomos(cromossomosFilhoUm);
		filhos.get(0).setGeracao(this.geracao+1);
		filhos.get(1).setCromossomos(cromossomosFilhosDois);
		filhos.get(1).setGeracao(this.geracao+1);
		
		return filhos;
		
	}
	
	public Individuo mutar(Double taxaMutacao) {
		for (int i=0; i < this.cromossomos.size(); i++) {
			if (Math.random() < taxaMutacao) {
				if (this.cromossomos.get(i).equals("0")) {
					this.cromossomos.set(i, "1");
				} else {
					this.cromossomos.set(i, "0");
				}
			}
		}
		return this;
	}
	
	public int compareTo(Individuo o) {
		if (this.notaAvaliacao > o.getNotaAvaliacao()) {
			return -1;
		}
		if (this.notaAvaliacao < o.getNotaAvaliacao()) {
			return 1;
		}
		return 0;
	}

}
