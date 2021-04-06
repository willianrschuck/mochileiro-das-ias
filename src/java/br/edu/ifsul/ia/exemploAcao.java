package br.edu.ifsul.ia;


import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;

public class exemploAcao extends DefaultInternalAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {

		NumberTerm plx = (NumberTerm) args[0];
		int num = (int) plx.solve();
		int mult = 1;
				
		for (int i = num; i > 1; i--) {
			mult *= i;
		}
		
		NumberTerm p2x = new NumberTermImpl(mult);
		
		return un.unifies(p2x, args[1]);
		
	}

}
