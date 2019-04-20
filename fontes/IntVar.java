public class IntVar extends Var {
    protected int data;

    //construtores
    public IntVar() {
        this.type="int";
    }
    
    public IntVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    //getters
    @Override
	public Integer getData() {
		return this.data;
	}

    //setters
    public void setData(int d) {
        this.data=d;
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("double")) {
            double tmp = (int)this.getData()-(double)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("float")) {
            double tmp = (int)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else if (other.getType().equals("int")) {
            double tmp = (int)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lAnd(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            return BoolVar.intToBool((int)this.getData())&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return BoolVar.intToBool((int)this.getData())&&BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return BoolVar.intToBool((int)this.getData())&&BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return BoolVar.intToBool((int)this.getData())&&BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            return BoolVar.intToBool((int)this.getData())||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return BoolVar.intToBool((int)this.getData())||BoolVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToInt((double)other.getData());
            return BoolVar.intToBool((int)this.getData())||BoolVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToInt((float)other.getData());
            return BoolVar.intToBool((int)this.getData())||BoolVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        return !(BoolVar.intToBool((int)this.getData()));
    }
}
