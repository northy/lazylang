public class FloatVar extends Var {
    protected float data;

    //construtores
    public FloatVar() {
        this.type="float";
    }
    
    public FloatVar(String name,float data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    //getters
    @Override
    public Float getData() {
        return this.data;
    }

    //setters
    public void setData(float d) {
        this.data=d;
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("double")) {
            double tmp = (float)this.getData()-(double)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("float")) {
            double tmp = (float)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("int")) {
            double tmp = (float)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = floatToInt(this.getData());
            return BoolVar.intToBool(tmpT)&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = DoubleVar.doubleToInt(this.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = floatToInt(this.getData());
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = floatToInt(this.getData());
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return BoolVar.intToBool(tmpT)&&BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = floatToInt(this.getData());
            return BoolVar.intToBool(tmpT)||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = floatToInt(this.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = floatToInt(this.getData());
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = floatToInt(this.getData());
            int tmpO = floatToInt((float)other.getData());
            return BoolVar.intToBool(tmpT)||BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        int tmpT = floatToInt(this.getData());
        return !(BoolVar.intToBool(tmpT));
    }

    //Static methods
    public static int floatToInt(float f) {
        return (float)f<0 ? (int)Math.floor(f) : (int)Math.ceil(f);
    }
}
