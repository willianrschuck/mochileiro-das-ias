
produtos(
	[
		["Capacete", 5.0, 3.0, "José"],
		["Botas Arcanas", 7.0, 2.0, "Alencar"],
		["Rapieira Divina", 8.0, 4.0, "Alencar"],
		["Máscara da Loucura", 2.0, 2.0, "Alencar"],
		["Cota de Lâminas", 4.0, 3.0, "Klaus Wagner"],
		["Égide do Imortal", 3.0, 5.0, "Klaus Wagner"],
		["Égide do Imortal", 7.0, 5.0, "Aninha"],
		["Mjollnir", 8.0, 2.0, "Epaminondas"]
	]
).

produtosQuePRECISO(
	[
		["Orquídea Malevolente", 1.0, 3.0, "Aninha"]
	]
).

volumeMaximo(10).

!start.

+!start : true <- 
   ?produtos(X);
   ?produtosQuePRECISO(R)
   ?volumeMaximo(V);
   .print("Fala aí, meu bom!")
   .print("Preciso carregar esse caminhão com um volume de até ", V ,", mas quero obter o maior lucro possível.")
   .print("O lucro é maior se os produtos carregados tiverem mais valor.")
   .print("Estes são os produtos.")
   .print(X)
   .print("Pode me ajudar?")
   .send(carregador, tell, selecionarProdutos(X, R, V)).

+entregarItens(S): true <- .print("Obrigado. Levarei estes itens que me sugeriu ", S).