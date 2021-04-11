

!start.

+!start : true <- .print("Carregador Jao se apresentando!").
   
+selecionarProdutos(X, R, P): true <-
	.print("Hum...");
	br.edu.ifsul.ia.carregaAcao(X, R, P, S);
	.print("Estou te encaminando uma lista com os produtos que acredito que trarao um maior lucro.")
	.send(camioneiro, tell, entregarItens(S)).
