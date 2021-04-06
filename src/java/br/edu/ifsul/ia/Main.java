package br.edu.ifsul.ia;


import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Produto> itens = new ArrayList<Produto>();
		itens.add(new Produto("Item 1", 5.0, 3.0));
		itens.add(new Produto("Item 2", 4.0, 3.0));
		itens.add(new Produto("Item 3", 7.0, 2.0));
		itens.add(new Produto("Item 4", 8.0, 4.0));
		itens.add(new Produto("Item 5", 4.0, 2.0));
		itens.add(new Produto("Item 6", 4.0, 3.0));
		itens.add(new Produto("Item 7", 6.0, 5.0));
		itens.add(new Produto("Item 8", 8.0, 2.0));
		
		
		List<Double> pesos = new ArrayList<Double>();
		List<Double> beneficios = new ArrayList<Double>();
		List<String> nomes = new ArrayList<String>();
		
		for (Produto item : itens) {
			pesos.add(item.getVolume());
			beneficios.add(item.getPreco());
			nomes.add(item.getNome());
		}
		
		Double limite = 3.0;
		int tamanhoPopulacao = 20;
		Double taxaMutacao = 0.01;
		int numeroGeracoes = 1000;
		
		AlgoritmoGenetico a = new AlgoritmoGenetico(tamanhoPopulacao);
		List<String> resultado = a.resolver(taxaMutacao, numeroGeracoes, pesos, beneficios, limite);
		for (int i=0; i < itens.size(); i++) {
			if (resultado.get(i).equals("1")) {
				System.out.println("Nome: " + itens.get(i).getNome());
			}
		}
		
	}
	
}
