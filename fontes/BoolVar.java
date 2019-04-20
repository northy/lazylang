public class BoolVar extends Var {
    protected boolean data;

    //construtores
    public BoolVar() {
        this.type="bool";
    }

    public BoolVar(String name,boolean data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public BoolVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(IntVar.intToBool(data));
    }

    //getters
    @Override
    public Boolean getData() {
        return this.data;
    }

    //setters
    public void setData(boolean d) {
        this.data=d;
    }
    
    public void setData(int d) {
        this.data=IntVar.intToBool(d);
    }

    //métodos estáticos

    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("bool")) {
            return boolToInt((boolean)this.getData())-boolToInt((boolean)other.getData());
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException{
        if (other.getType().equals("bool")) {
            return (boolean)this.getData()&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return (boolean)this.getData()&&IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return (boolean)this.getData()&&IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return (boolean)this.getData()&&IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException{
        if (other.getType().equals("bool")) {
            return (boolean)this.getData()||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return (boolean)this.getData()||IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return (boolean)this.getData()||IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return (boolean)this.getData()||IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        return !((boolean)this.getData());
    }
}
