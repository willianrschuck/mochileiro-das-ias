	package br.edu.ifsul.ia;
	
	
	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;
	
	public class Individuo implements Comparable<Individuo> {
		
		private List<Double> volumes = new ArrayList<Double>();
		private List<Double> precos = new ArrayList<Double>();
		private List<String> destinatarios = new ArrayList<String>();
		private Double limiteVolume;
		private Double notaAvaliacao;
		@SuppressWarnings("unused")
		private Double volumeUsado;
		private int geracao;
		private List<String> cromossomos = new ArrayList<String>();
		private List<String> destinatariosObrigatorios;
		
		public Individuo(List<Double> volumes, List<Double> valor, List<String> destinatarios, List<String> destinatariosObrigatorios, Double limiteVolumes) {
			super();
			this.volumes = volumes;
			this.precos = valor;
			this.destinatarios = destinatarios;
			this.destinatariosObrigatorios = destinatariosObrigatorios;
			this.limiteVolume = limiteVolumes;
			this.notaAvaliacao = 0.0;
			this.volumeUsado = 0.0;
			this.geracao = 0;
			
			for (int i = 0; i < this.volumes.size(); i++) {
				if (java.lang.Math.random() < 0.5) {
					this.cromossomos.add("0");
				} else {
					this.cromossomos.add("1");		
				}
			}
			
		}
		
		public void avaliar() {
			Double nota = 0.0;
			Double somaVolumes = 0.0;
			String destinatario;
			Set<String> destinatariosDosItens = new HashSet<String>(); 
			destinatariosDosItens.addAll(destinatariosObrigatorios);
			for (int i = 0; i < cromossomos.size(); i++) {
				
				if (this.cromossomos.get(i) == "1") {
					
					nota += precos.get(i);
					somaVolumes += volumes.get(i);
					destinatario = destinatarios.get(i);
					
					if (!destinatariosDosItens.contains(destinatario)) {
						destinatariosDosItens.add(destinatario);
					}
					
				}
				
			}
			if (somaVolumes > this.limiteVolume) {
				nota = 1.0;
			}
			this.notaAvaliacao = nota - (destinatariosDosItens.size() - 1);
			if (this.notaAvaliacao < 1.0) {
				notaAvaliacao = 1.0;
			}
			this.volumeUsado = somaVolumes;
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
			return precos;
		}
		
		public Double getLimitePeso() {
			return limiteVolume;
		}
		
		public List<String> getCromossomos() {
			return cromossomos;
		}
		
		public List<Double> getVolumes() {
			return volumes;
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
			filhos.add(new Individuo(this.volumes, this.precos, this.destinatarios, this.destinatariosObrigatorios, this.limiteVolume));
			filhos.add(new Individuo(this.volumes, this.precos, this.destinatarios, this.destinatariosObrigatorios, this.limiteVolume));
			
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
