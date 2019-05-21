int i;
int j;
i = 0;
j = i;
while(i < 3) {
	if(i == 0){
		println("Zero");
	}	
	else{
		println("Outro");
	}
	i+=1;
}
if(i == 3) {
	if(j == 0){
	println("Ok!");
	}
	else {
	println("Erro");
	}
}