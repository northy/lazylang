//Giovane Gonçalves da Silva <giovanegsilva@outlook.com>

public class StrVar extends Var{
	protected String data;

	public StrVar(){
		this.type = "str";
	}

	public StrVar(String name,String data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public StrVar(String name){
        this();
        this.name = name; 
        this.data = "";       
    }

    //getters
   @Override
	public String getData() {
		return this.data;
	}
    //setters
   
    public void setData(String d) {
        this.data=d;
    }
    public void setData(Object d) throws OperatorException {
        try {
            this.data=(String) d;
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }

    //métodos
    //retorna o valor da tabela unicode para o caractere selecionado
    public int codePoint(int index){
        int codeValue;
        codeValue = this.getData().codePointAt(index);
        return codeValue;
    }

    //concatena duas strings
    public String concat(String other){
        return this.getData().concat(other);
    }

    //verifica se a string é igual a um objeto
    @Override
    public int compareTo(Var other){
        if (other.getType()=="str"){
            if(this.equals(other.getData())==true){
                return 1;
            }
            else{
                return 0;    
            }
        }    
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    
    }

    //Retorna uma cópia da string
    @Override
    public StrVar copy() {
        StrVar s = new StrVar(this.getName());
        for (int i = 0;i<=(this.getData().length());i++) {
            s.concat(""+this.getData().charAt(i));
        }
        return s;
    }

    //retorna o caractere que há na posição passada como parâmetro
    public char content(int i)
    {
        return this.getData().charAt(i);
    }

    //verifica se a string termina com o caractere passado como parametro
    public boolean endsWith(char c){
        if (this.getData().charAt(this.getData().length())==c){
            return true;
        }
        else{
            return false;
        }
    }

    //verifica se duas strings são iguais
    public boolean equals(Var other){
        if ((this.getData()).equals(other.getData())){
            return true;
        }
        else{
            return false;
        }
    }

    //verifica se o caractere passado como parâmetro existe na string
    public boolean find(char c){
        int leng= this.getData().length();
        for (int i = 0; i < leng; i++){
            if (this.getData().charAt(i)==c){
                return true;
            }
        }
        return false;
    }

    //retorna o indice da primeira ocorrência do caractere passado como parametro, caso exista na string
    public int firstIndexOf(char c){
        int leng = this.getData().length();
        for(int i=0; i < leng; i++){
            if(this.getData().charAt(i)==c){
                return i;
            }
        }
        return -1;
    }

    //verifica se a string está vazia
    public boolean isEmpty(){
        if (this.len()==0){
            return true;
        }
        else{
            return false;
        }
    }

    //retorna o indice da primeira ocorrência do caractere passado como parametro, caso exista na string
    public int lastIndexOf(char c){
        int leng = this.getData().length()-1;
        for(int i=leng; i < -1; i--){
            if(this.getData().charAt(i)==c){
                return i;
            }
        }
       return -1;
    }

    //retorna o tamanho da string
    public int len(){
        return this.getData().length();
    }
    
    //substitui a primeira ocorrencia do primeiro caractere passado como parametro pelo segundo
    public String replace(char oldChar, char newChar){
        String nValue = new String();
        nValue.replace(oldChar , newChar);
        return nValue;
    }

    //retorna verdadeiro se a string iniciar com o caractere passado como parametro
    public boolean startsWith(char c){
        if (this.getData().charAt(0)==c){
            return true;
        }
        else{
            return false;
        }
    }
    //divide a string em um vetor de strings em cada regex(simbolo) passado para a função
    public String[] split(String regex){
            return this.getData().split(regex);
    }

    //retorna uma substring da string, a substring equivale a string inicial a partir do primeiro parametro, até no segundo
    public String substring(int startIndex,int endIndex){
        String subs = new String();
        for(int i = startIndex;i<=endIndex;i++){
            subs.concat(this.getData().charAt(i)+"");
        }
        return subs;
    }

    //converte a string num array de caracteres
    public char[] toCharArray()
    {
        int index = this.getData().length();
        char[] arrayChar = new char[index];
        for(int i = 0; i < index; i++){
            arrayChar[i] = this.getData().charAt(i);
        }
        return arrayChar;
    }

    //converte todos as maiúsculas para minúsculas
    public String toLower(){
        return this.getData().toLowerCase();
    }

    //converte todas as minúsculas para maiúsculas
    public String toUpper(){
        return this.getData().toUpperCase();
    } 

    //retorna uma copia da string sem espaços no inicio e no fim
    public String trim(){
        return this.getData().trim();
    }

    //retorna o valor string de um valor booleano
    public String valueofBoolean(boolean b){
        if (b == true){
            return "true";
        }
        else{
            return "false";
        }
    }

    //retorna o valor string de um caractere
    public String valueOfChar(char c){
        String string = new String(""+c);
        return string;
    }

    //retorna o valor string de um array de caractere
    public String valueOfArrayChar(char[] data){
        String string = new String();
        int leng = data.length;
        for (int i = 0; i < leng; i++){
            string.concat(""+data[i]);
        }
        return string;
    }
    
    //retorna o valor string de um valor double
    public String valueOfDouble(double n){
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    //retorna o valor string de um valor float
    public String valueOfFloat(float n){
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    //retorna o valor string de um valor inteiro
    public String valueOfInt(int n){        
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    //retorna o valor string de um objeto
    public String valueOfObject(Var o){
        String nValue = new String();
        nValue.valueOf(o);
        return nValue;
    }
}
