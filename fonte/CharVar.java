//Giovane Gonçalves da Silva <giovanegsilva@outlook.com>

package fonte;

public class CharVar extends Var {
    protected char data;

    //construtores
    public CharVar() {
        this.type="char";
    }
    
    public CharVar(String name){
        this();
        this.setName(name);
    }

    public CharVar(String name,char data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public CharVar(char data) {
       this("__tmp",data);
    }

    //getters
   @Override
	public Character getData() {
		return this.data;
	}
  
    //setters
    public void setData(char d) {
        this.data=d;
    }

    public void setData(Object d) throws OperatorException {
        try {
            this.data=((Character)d).charValue();
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("char")) {
            String char1 = "" + this.getData();
            String char2 = "" + other.getData();
            int __tmp = char1.codePointAt(0)-char2.codePointAt(0);
            return __tmp;
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    public boolean lAnd(Var other) throws OperatorException {
        if (other.getType().equals("char")){
            return false;
        }
        else{
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("char")){
            return false;
        }
        else{
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }

    }
    public boolean lNot() throws OperatorException {
    if (this.getType().equals("char")){
            return false;
        }
        else {    
        throw new OperatorException("Uncompatible type for logical or function: " + this.getType());
        }
    }
    @Override
    public Var copy(){
      return new CharVar(this.getName(),(char)this.getData());
    }
}

