package br.edu.ifsul.ia;

import java.util.ArrayList;
import java.util.List;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ListTerm;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;
import jason.stdlib.foreach;

public class organizaAcao extends DefaultInternalAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		
		ListTerm listaDeTermos = (ListTerm) args[0];
		
		List<Item> itens = new ArrayList<Item>();
		
		for (Term term : listaDeTermos) {
			
			ListTerm subList = (ListTerm) term;

			String nome = ((StringTerm) subList.get(0)).getString();
			Double peso = ((NumberTerm) subList.get(1)).solve();
			Double beneficio = ((NumberTerm) subList.get(2)).solve();
			
			itens.add(new Item(nome, peso, beneficio));
			
		}
		
		List<Double> pesos = new ArrayList<Double>();
		List<Double> beneficios = new ArrayList<Double>();
		List<String> nomes = new ArrayList<String>();
		
		for (Item item : itens) {
			pesos.add(item.getPeso());
			beneficios.add(item.getBeneficio());
			nomes.add(item.getNome());
		}
		
		Double limite = 10.0;
		int tamanhoPopulacao = 20;
		Double taxaMutacao = 0.01;
		int numeroGeracoes = 100;
		
		AlgoritmoGenetico a = new AlgoritmoGenetico(tamanhoPopulacao);
		List<String> resultado = a.resolver(taxaMutacao, numeroGeracoes, pesos, beneficios, limite);
		for (int i=0; i < itens.size(); i++) {
			if (resultado.get(i).equals("1")) {
				System.out.println("Nome: " + itens.get(i).getNome());
			}
		}
		
		return true;
		
	}

}