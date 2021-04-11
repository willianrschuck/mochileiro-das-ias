

!start.

+!start : true <- .print("Carregador Jão se apresentando!").
   
+selecionarProdutos(X, R, P): true <-
	.print("Hum...");
	br.edu.ifsul.ia.carregaAcao(X, R, P, S);
	.print("Estou te encaminando uma lista com os produtos que acredito que trarão um maior lucro.")
	.send(camioneiro, tell, entregarItens(S)).
