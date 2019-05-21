int n = 13;
int c = 0;

for(int i = 1;i <= n;i += 1){
	if(n%i == 0){
	c += 1;
	}
}
if(c==2){
	println("É primo");
}
else{
	println("Não é primo");
}