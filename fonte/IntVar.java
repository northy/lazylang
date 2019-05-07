public class IntVar extends Var {
    protected int data;

    //construtores
    public IntVar() {
        this.type="int";
    }
    
    public IntVar(String name){
        this();
        this.setName(name);
    }

    public IntVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public IntVar(int data) {
        this("__tmp",data);
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

    public void setData(Object d) throws OperatorException {
        try {
            this.data=(int)((Number) d).intValue();
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
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
            return IntVar.intToBool((int)this.getData())&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return IntVar.intToBool((int)this.getData())&&IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool((int)this.getData())&&IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToRoundedInt((float)other.getData());
            return IntVar.intToBool((int)this.getData())&&IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            return IntVar.intToBool((int)this.getData())||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            return IntVar.intToBool((int)this.getData())||IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpO = DoubleVar.doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool((int)this.getData())||IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpO = FloatVar.floatToRoundedInt((float)other.getData());
            return IntVar.intToBool((int)this.getData())||IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        return !(IntVar.intToBool((int)this.getData()));
    }

    @Override
    public Var add(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new IntVar("__tmp",(int)this.getData()+(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new FloatVar("__tmp",(float)(int)this.getData()+(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()+(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var sub(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new IntVar("__tmp",(int)this.getData()-(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new FloatVar("__tmp",(float)(int)this.getData()-(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()-(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
        }
    }
    
    @Override
    public Var mult(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new IntVar("__tmp",(int)this.getData()*(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new FloatVar("__tmp",(float)(int)this.getData()*(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()*(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var div(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()/(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()/(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()/(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var mod(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new IntVar("__tmp",(int)this.getData()%(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new FloatVar("__tmp",(float)(int)this.getData()%(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)(int)this.getData()%(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
        }
    }

    public Var copy() {
        return new IntVar(this.getName(),(int)this.getData());
    }

    //Static methods
    public static boolean intToBool(int i) {
        return i==0 ? false : true;
    }
}
// Revisado por Fernando
