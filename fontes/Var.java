public abstract class Var {
    protected String name;
    protected Object data;
    protected String type;

    //getters
    public Object getData() {
		return null;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }

    //setters
    public void setName(String name) {
        this.name=name;
    }

    //métodos
    public void print() {
        System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content: " + this.getData());
    }

    public boolean equals(Object other) {
        return this.getData() == ((Var) other).getData();
    }

    public int compareTo(Var other) {
        return 0;
    }

    public boolean lAnd(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
    }

    public boolean lOr(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
    }
    
    public boolean lNot() throws OperatorException {
        throw new OperatorException("Uncompatible type for logical or function: " + this.getType());
    }

    public Var add(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
    }

    public Var sub(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
    }

    public Var div(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
    }

    public Var mult(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
    }

    public Var mod(Var other) throws OperatorException {
        throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
    }
}
