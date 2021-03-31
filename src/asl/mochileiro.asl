

!start.

+!start : true <- .print("Welcome to the secret shop. I'm open all night.'").
   
+selecionarItensMochila(X, P): true <-
	.print("Hum...");
	br.edu.ifsul.ia.organizaAcao(X, P, S);
	.print("Aqui estão os itens mais benéficos que você pode levar nesta mochila.")
	.send(aventureiro, tell, entregarItens(S)).
