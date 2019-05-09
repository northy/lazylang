public abstract class Var {
    protected String name;
    protected String type;

    //getters
    public abstract Object getData();
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
    public abstract void setData(Object d);

    //métodos
    public void print() {
        System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content: " + this.getData());
    }

    public boolean equals(Var other) {
        return this.getData() == other.getData();
    }

    public abstract int compareTo(Var other);

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

    public abstract Var copy();
}
