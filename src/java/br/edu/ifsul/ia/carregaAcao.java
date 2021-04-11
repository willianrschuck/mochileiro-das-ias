package br.edu.ifsul.ia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

public class carregaAcao extends DefaultInternalAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		
		ListTerm listaTermosItens = (ListTerm) args[0];
		ListTerm listaTermosItensRequeridos = (ListTerm) args[1];
		double volumeJaOcupado = 0.0;
		double volumeMaximo = ((NumberTerm) args[2]).solve();
		double volumeDisponivel = 0.0; 
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		for (Term term : listaTermosItens) {
			
			ListTerm subList = (ListTerm) term;

			String nome = ((StringTerm) subList.get(0)).getString();
			Double volume = ((NumberTerm) subList.get(1)).solve();
			Double preco = ((NumberTerm) subList.get(2)).solve();
			String destinatario = ((StringTerm) subList.get(3)).getString();
			
			produtos.add(new Produto(nome, volume, preco, destinatario));
			
		}
		
		Set<String> destinatariosObrigatorios = new HashSet<String>();
				
		for (Term term : listaTermosItensRequeridos) {
			
			ListTerm subList = (ListTerm) term;
			volumeJaOcupado += ((NumberTerm) subList.get(1)).solve();
			String destinatario = ((StringTerm) subList.get(3)).getString();
			destinatariosObrigatorios.add(destinatario);
			
		}
		
		volumeDisponivel = volumeMaximo - volumeJaOcupado; 
		
		List<String> destinatarios = new ArrayList<String>();
		List<Double> volumes = new ArrayList<Double>();
		List<Double> precos = new ArrayList<Double>();
		List<String> nomes = new ArrayList<String>();
		
		
		for (Produto produto : produtos) {
			volumes.add(produto.getVolume());
			precos.add(produto.getPreco());
			nomes.add(produto.getNome());
			destinatarios.add(produto.getDestinatario());
		}
		
		int tamanhoPopulacao = 20;
		Double taxaMutacao = 0.01;
		int numeroGeracoes = 10000;
		
		AlgoritmoGenetico a = new AlgoritmoGenetico(tamanhoPopulacao);
		List<String> resultado = a.resolver(taxaMutacao, numeroGeracoes, volumes, precos, destinatarios, new ArrayList<String>(destinatariosObrigatorios), volumeDisponivel);
		ListTerm itensParaLevar = new ListTermImpl();
		
		itensParaLevar.add(listaTermosItensRequeridos);
		for (int i=0; i < produtos.size(); i++) {
			if (resultado.get(i).equals("1")) {
				itensParaLevar.add(listaTermosItens.get(i));
			}
		}
				
		return un.unifies(itensParaLevar, args[3]);
		
	}

}