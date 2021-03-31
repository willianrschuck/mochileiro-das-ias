package br.edu.ifsul.ia;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoGenetico {

	private int tamanhoPopulacao;
	private List<Individuo> populacao = new ArrayList<Individuo>();
	private Individuo melhorIndividuo;
	
	public AlgoritmoGenetico(int tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}
	
	public void inicializaPopulacao(List<Double> pesos, List<Double> valores, Double limitePeso) {
		for (int i=0; i < tamanhoPopulacao; i++) {
			this.populacao.add(new Individuo(pesos, valores, limitePeso));
		}
		this.melhorIndividuo = this.populacao.get(0);
	}
	
	public void melhorIndividuo(Individuo individuo) {
		if (individuo.getNotaAvaliacao() > this.melhorIndividuo.getNotaAvaliacao()) {
			this.melhorIndividuo = individuo;
		}
	}
	
	public void ordenarPopulacao() {
		Collections.sort(this.populacao);
	}
	
	public Double somaAvaliacao() {
		Double soma = 0.0;
		for (Individuo individuo : populacao) {
			soma += individuo.getNotaAvaliacao();
		}
		return soma;
	}
	
	public int selecionaPai(Double somaAvaliacao) {
		int pai = -1;
		Double valorSorteado = Math.random() * somaAvaliacao;
		Double soma = 0.0;
		int i = 0;
		while (i < this.populacao.size() && soma < valorSorteado) {
			soma += this.populacao.get(i).getNotaAvaliacao();
			pai += 1;
			i += 1;
		}
		return pai;
	}
	
	public void visualizarGeracao() {
//		Individuo melhor = this.populacao.get(0);
//		System.out.println("Geracao: " + melhor.getGeracao() +
//		                  " Valor:" + melhor.getNotaAvaliacao() +
//		                  " Espaço:" + melhor.getPesos() +
//		                  " Cromossomo: " + melhor.getCromossomos());
	}
	
	public List<String> resolver(Double taxaMutacao, int numeroGeracoes, List<Double> pesos, List<Double> beneficios, Double limitePeso) {
		
		this.inicializaPopulacao(pesos, beneficios, limitePeso);
		

//		System.out.println("Melhor Solucao: " + melhorIndividuo.getGeracao() +
//                " Valor:" + melhorIndividuo.getNotaAvaliacao() +
//                " Espaço:" + melhorIndividuo.getPesos() +
//                " Cromossomo:" + melhorIndividuo.getCromossomos());	
		
		this.avaliarOrdenarEVisualizarPopulacao();
		
		for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
			
			Double somaAvaliacao = this.somaAvaliacao();
			List<Individuo> novaPopulacao = new ArrayList<Individuo>();
			
			for (int i = 0; i < populacao.size() / 2; i++) {
				int pai1 = this.selecionaPai(somaAvaliacao);
				int pai2 = this.selecionaPai(somaAvaliacao);
				
				List<Individuo> filhos = this.populacao.get(pai1).cruzar(this.populacao.get(pai2));
				
				novaPopulacao.add(filhos.get(0).mutar(taxaMutacao));
				novaPopulacao.add(filhos.get(1).mutar(taxaMutacao));
				
			}
			
			this.populacao = novaPopulacao;
			
			this.avaliarOrdenarEVisualizarPopulacao();
			
			Individuo melhorDestaGeracao = populacao.get(0);
			this.melhorIndividuo(melhorDestaGeracao);
			
		}
		
//		System.out.println("Melhor Solucao: " + melhorIndividuo.getGeracao() +
//                " Valor:" + melhorIndividuo.getNotaAvaliacao() +
//                " Espaço:" + melhorIndividuo.getPesos() +
//                " Cromossomo:" + melhorIndividuo.getCromossomos());	
		
		return this.melhorIndividuo.getCromossomos();
		
	}
	
	private void avaliarOrdenarEVisualizarPopulacao() {
		
		for (Individuo individuo : populacao) {
			individuo.avaliar();
		}
		
		this.ordenarPopulacao();
		this.visualizarGeracao();
		
	}
	
}
