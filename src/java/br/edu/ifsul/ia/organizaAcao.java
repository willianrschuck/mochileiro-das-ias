package br.edu.ifsul.ia;

import java.util.ArrayList;
import java.util.List;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

public class organizaAcao extends DefaultInternalAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		
		ListTerm listaDeTermos = (ListTerm) args[0];
		double volumeMaximo = ((NumberTerm) args[1]).solve();
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		for (Term term : listaDeTermos) {
			
			ListTerm subList = (ListTerm) term;

			String nome = ((StringTerm) subList.get(0)).getString();
			Double volume = ((NumberTerm) subList.get(1)).solve();
			Double preco = ((NumberTerm) subList.get(2)).solve();
			
			produtos.add(new Produto(nome, volume, preco));
			
		}
		
		List<Double> pesos = new ArrayList<Double>();
		List<Double> precos = new ArrayList<Double>();
		List<String> nomes = new ArrayList<String>();
		
		for (Produto produto : produtos) {
			pesos.add(produto.getTamanho());
			precos.add(produto.getPreco());
			nomes.add(produto.getNome());
		}
		
		int tamanhoPopulacao = 20;
		Double taxaMutacao = 0.01;
		int numeroGeracoes = 1000;
		
		AlgoritmoGenetico a = new AlgoritmoGenetico(tamanhoPopulacao);
		List<String> resultado = a.resolver(taxaMutacao, numeroGeracoes, pesos, precos, volumeMaximo);
		ListTerm itensParaLevar = new ListTermImpl();
		
		for (int i=0; i < produtos.size(); i++) {
			if (resultado.get(i).equals("1")) {
				itensParaLevar.add(listaDeTermos.get(i));
			}
		}
				
		return un.unifies(itensParaLevar, args[2]);
		
	}

}