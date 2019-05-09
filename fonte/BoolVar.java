public class BoolVar extends Var {
    protected boolean data;

    //construtores
    public BoolVar() {
        this.type="bool";
    }

    public BoolVar(String name){
        this();
        this.setName(name);
    }

    public BoolVar(String name,boolean data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public BoolVar(boolean data) {
        this("__tmp",data);
    }

    public BoolVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(data != 0);
    }

    public BoolVar(int data) {
        this("__tmp",data);
    }

    public BoolVar(Object data) {
        this("__tmp");
        this.setData(data);
    }

    //getters
    public Boolean getData() {
        return this.data;
    }

    //setters
    @Override
    public void setData(Object d) throws OperatorException {
        try {
            this.data=((Number)d).doubleValue() != 0;
            return;
        }
        catch (Exception e) {}
        try {
            this.data=(boolean)d;
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }

    public void setData(boolean d) {
        this.data=d;
    }
    
    public void setData(int d) {
        this.data=d != 0;
    }

    //métodos estáticos

    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        try {
            return boolToInt(this.getData())-boolToInt((boolean)other.getData());
        } catch (Exception e) {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException{
        boolean o;

        try {
            o=(double)other.getData() != 0;
            return this.getData() && o;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            return this.getData() && o;
        }
        catch (Exception e) {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException{
        boolean o;

        try {
            o=(double)other.getData() != 0;
            return this.getData() || o;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            return this.getData() || o;
        }
        catch (Exception e) {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        return !(this.getData());
    }

    public Var copy() {
        return new BoolVar(this.getName(),this.getData());
    }
}
