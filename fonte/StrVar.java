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

    public StrVar(String data) {
        this("__tmp",data);
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
            this.data=(String)((String) d).toString();
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }
    //métodos

public int codePoint(int index){
    int codeValue;
    codeValue = this.getData().codePointAt(index);
    return codeValue;
}

public String concat(String other){
        return this.getData().concat(other);
}

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

    public char content(int i)
    {
        return this.getData().charAt(i);
    }

    public boolean endsWith(char c){
        if (this.getData().charAt(this.getData().length())==c){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean equals(Var other){
        if ((this.getData()).equals(other.getData())){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        if (this.len()==0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int len(){
        return this.getData().length();
    }
 
    public String replace(char oldChar, char newChar){
        String nValue = new String();
        nValue.replace(oldChar , newChar);
        return nValue;
    }

    public boolean startsWith(String string, char c){
        if (this.getData().charAt(0)==c){
            return true;
        }
        else{
            return false;
        }
    }

    public String[] split(String regex){
            return this.getData().split(regex);
    }

    public String substring(int startIndex,int endIndex){
        String subs = new String();
        for(int i = startIndex;i<endIndex;i++){
            subs.concat(this.getData().charAt(i)+"");
        }
        return subs;
    }

    public char[] toCharArray()
    {
        int index = this.getData().length();
        char[] arrayChar = new char[index];
        for(int i = 0; i < index; i++){
            arrayChar[i] = this.getData().charAt(i);
        }
        return arrayChar;
    }

    public String toLower(){
        return this.getData().toLowerCase();
    }

    public String toUpper(){
        return this.getData().toUpperCase();
    } 

    public String trim(){
        return this.getData().trim();
    }
    public String valueofBoolean(boolean b){
        if (b == true){
            return "true";
        }
        else{
            return "false";
        }
    }

    public String valueOfChar(char c){
        String string = new String(""+c);
        return string;
    }

    public String valueOfArrayChar(char[] data){
        String string = new String();
        int leng = data.length;
        for (int i = 0; i < leng; i++){
            string.concat(""+data[i]);
        }
        return string;
    }
    
    public String valueOfDouble(double n){
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    public String valueOfFloat(float n){
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    public String valueOfInt(int n){        
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    public String valueOfLong(long n){
        String nValue = new String();
        nValue.valueOf(n);
        return nValue;
    }

    public String valueOfObject(Var o){
        String nValue = new String();
        nValue.valueOf(o);
        return nValue;
    }
}