public class DoubleVar extends Var {
    protected double data;

    //construtores
    public DoubleVar() {
        this.type="double";
    }
    
    public DoubleVar(String name,double data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    //getters 
    @Override
    public Double getData() {
        return this.data;
    }

    //setters
    public void setData(double d) {
        this.data=d;
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("double")) {
            double tmp = (double)this.getData()-(double)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("float")) {
            double tmp = (double)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("int")) {
            double tmp = (double)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = doubleToInt(this.getData());
            return BoolVar.intToBool(tmpT)&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = doubleToInt(this.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = doubleToInt(this.getData());
            int tmpO = doubleToInt((double)other.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = doubleToInt(this.getData());
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = doubleToInt(this.getData());
            return BoolVar.intToBool(tmpT)||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = doubleToInt(this.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = doubleToInt(this.getData());
            int tmpO = doubleToInt((double)other.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = doubleToInt(this.getData());
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        int tmpT = doubleToInt(this.getData());
        return !(BoolVar.intToBool(tmpT));
    }

    //métodos estáticos
    public static int doubleToInt(double d) {
        return (double)d<0 ? (int)Math.floor(d) : (int)Math.ceil(d);
    }
}
