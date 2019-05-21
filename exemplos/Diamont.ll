int tamanho = readInt("Tamanho:");
int helpSpaces = tamanho ;
int controlador = 1;
for(int i = 1; i < tamanho; i += 2){
	for(int x = 0; x < helpSpaces; x += 1){
		print(" ");
	}
	for(int j = 0; j < i; j += 1){
		print("#");
	}
	helpSpaces -= 2;
	println("");
	for(int s = 0; s < controlador; s += 1){
		print(" ");
	}
	controlador += 1;
}

for(int i = tamanho; i > 0; i -= 2){
	for(int x = 0; x < helpSpaces; x += 1){
		print(" ");
	}
	for(int j = 0; j < i; j += 1){
		print("#");
	}
	helpSpaces += 2;
	println("");
	for(int s = controlador -2; s > 0; s -= 1){
		print(" ");
	}
	controlador -= 1;
}