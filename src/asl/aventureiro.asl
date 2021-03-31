

itens(
	[
		["Item 1", 5.0, 3.0],
		["Item 2", 4.0, 3.0],
		["Item 3", 7.0, 2.0],
		["Item 4", 8.0, 4.0],
		["Item 5", 4.0, 2.0],
		["Item 6", 4.0, 3.0],
		["Item 7", 6.0, 5.0],
		["Item 8", 8.0, 2.0]
	]
).


!start.


+!start : true <- 
   ?itens(X);
   br.edu.ifsul.ia.organizaAcao(X);
   .print(X).
  
