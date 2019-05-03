public class CharVar extends Var {
    protected Character data;

    //construtores
    public CharVar() {
        this.type="char";
    }
    
    public CharVar(String name){
        this();
        this.setName(name);
    }

    public CharVar(String name,Character data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public CharVar(Character data) {
        this("__tmp",data);
    }

    //getters
   @Override
	public Character getData() {
		return this.data;
	}
    @override
    //setters
    public void setData(Character d) {
        this.data=d;
    }

    public void setData(Object d) throws OperatorException {
        try {
            this.data=(Character)((Character) d).charValue();
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {

        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException {

        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
 
    }


}
