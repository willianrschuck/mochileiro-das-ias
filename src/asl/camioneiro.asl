
produtos(
	[
		["Capacete", 5.0, 3.0, "Jose"],
		["Botas Arcanas", 7.0, 2.0, "Alencar"],
		["Rapieira Divina", 8.0, 4.0, "Alencar"],
		["Mascara da Loucura", 2.0, 2.0, "Alencar"],
		["Cota de Laminas", 4.0, 3.0, "Klaus Wagner"],
		["Egide do Imortal", 3.0, 5.0, "Klaus Wagner"],
		["Egide do Imortal", 7.0, 5.0, "Aninha"],
		["Mjollnir", 8.0, 2.0, "Epaminondas"]
	]
).

produtosQuePRECISO(
	[
		["Orquidea Malevolente", 1.0, 3.0, "Aninha"]
	]
).

volumeMaximo(10).

!start.

+!start : true <- 
   ?produtos(X);
   ?produtosQuePRECISO(R)
   ?volumeMaximo(V);
   .print("Fala ai, meu bom!")
   .print("Preciso carregar esse caminhao com um volume de ate ", V ,", mas quero obter o maior lucro possivel.")
   .print("O lucro e maior se os produtos carregados tiverem mais valor.")
   .print("Estes sao os produtos.")
   .print(X)
   .print("Pode me ajudar?")
   .send(carregador, tell, selecionarProdutos(X, R, V)).

+entregarItens(S): true <- .print("Obrigado. Levarei estes itens que me sugeriu ", S).