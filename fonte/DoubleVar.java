public class DoubleVar extends Var {
    protected double data;

    //construtores
    public DoubleVar() {
        this.type="double";
    }
    
    public DoubleVar(String name){
        this();
        this.setName(name);
    }

    public DoubleVar(String name,double data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public DoubleVar(double data) {
        this("__tmp",data);
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

    @Override
    public void setData(Object d) throws OperatorException {
        try {
            this.data=(double)((Number) d).doubleValue();
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
            int tmpT = doubleToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = doubleToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = doubleToRoundedInt(this.getData());
            int tmpO = doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = doubleToRoundedInt(this.getData());
            int tmpO = FloatVar.floatToRoundedInt((float)other.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = doubleToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = doubleToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = doubleToRoundedInt(this.getData());
            int tmpO = doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = doubleToRoundedInt(this.getData());
            int tmpO = FloatVar.floatToRoundedInt((float)other.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        int tmpT = doubleToRoundedInt(this.getData());
        return !(IntVar.intToBool(tmpT));
    }

    @Override
    public Var add(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)this.getData()+(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)this.getData()+(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)this.getData()+(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var sub(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)this.getData()-(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)this.getData()-(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)this.getData()-(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var mult(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)this.getData()*(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)this.getData()*(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)this.getData()*(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var div(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)this.getData()/(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)this.getData()/(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)this.getData()/(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var mod(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(double)this.getData()%(double)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(double)this.getData()%(double)(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(double)this.getData()%(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
        }
    }

    public Var copy() {
        return new DoubleVar(this.getName(),(double)this.getData());
    }

    //métodos estáticos
    public static int doubleToRoundedInt(double d) {
        return (double)d<0 ? (int)Math.floor(d) : (int)Math.ceil(d);
    }
}
// Revisado por Fernando
