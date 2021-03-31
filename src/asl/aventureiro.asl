
itens(
	[
		["Capacete", 5.0, 3.0],
		["Orquídea Malevolente", 4.0, 3.0],
		["Botas Arcanas", 7.0, 2.0],
		["Rapieira Divina", 8.0, 4.0],
		["Máscara da Loucura", 4.0, 2.0],
		["Cota de Lâminas", 4.0, 3.0],
		["Égide do Imortal", 6.0, 5.0],
		["Mjollnir", 8.0, 2.0]
	]
).

pesoMaximo(10).

!start.

+!start : true <- 
   ?itens(X);
   ?pesoMaximo(P);
   .print("Olá mochileiro, preciso que organize os itens que levarei em minha mochila na próxima aventura.")
   .print("Estes são os itens que tenho comigo no momento...")
   .print(X)
   .send(mochileiro, tell, selecionarItensMochila(X, P)).

+entregarItens(S): true <- .print("Obrigado. Levarei estes itens que me sugeriu ", S).