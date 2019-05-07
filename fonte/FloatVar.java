public class FloatVar extends Var {
    protected float data;

    //construtores
    public FloatVar() {
        this.type="float";
    }
    
    public FloatVar(String name){
        this();
        this.setName(name);
    }

    public FloatVar(String name,float data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public FloatVar(float data) {
        this("__tmp",data);
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

    @Override
    public void setData(Object d) throws OperatorException {
        try {
            this.data=(float)((Number) d).floatValue();
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
            int tmpT = floatToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)&&(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = DoubleVar.doubleToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = floatToRoundedInt(this.getData());
            int tmpO = DoubleVar.doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = floatToRoundedInt(this.getData());
            int tmpO = DoubleVar.doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool(tmpT)&&IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        if (other.getType().equals("bool")) {
            int tmpT = floatToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)||(boolean)other.getData();
        }
        else if (other.getType().equals("int")) {
            int tmpT = floatToRoundedInt(this.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool((int)other.getData());
        }
        else if (other.getType().equals("double")) {
            int tmpT = floatToRoundedInt(this.getData());
            int tmpO = DoubleVar.doubleToRoundedInt((double)other.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool(tmpO);
        }
        else if (other.getType().equals("float")) {
            int tmpT = floatToRoundedInt(this.getData());
            int tmpO = floatToRoundedInt((float)other.getData());
            return IntVar.intToBool(tmpT)||IntVar.intToBool(tmpO);
        }
        else {
            throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        int tmpT = floatToRoundedInt(this.getData());
        return !(IntVar.intToBool(tmpT));
    }

    @Override
    public Var add(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(float)this.getData()+(float)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(float)this.getData()+(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(float)this.getData()+(float)(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var sub(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(float)this.getData()-(float)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(float)this.getData()-(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(float)this.getData()-(float)(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var mult(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(float)this.getData()*(float)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(float)this.getData()*(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(float)this.getData()*(float)(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var div(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(float)this.getData()/(float)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(float)this.getData()/(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(float)this.getData()/(float)(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public Var mod(Var other) throws OperatorException {
        if (other.getType().equals("int")) {
            return new DoubleVar("__tmp",(float)this.getData()%(float)(int)other.getData());
        }
        else if (other.getType().equals("float")) {
            return new DoubleVar("__tmp",(float)this.getData()%(float)other.getData());
        }
        else if (other.getType().equals("double")) {
            return new DoubleVar("__tmp",(float)this.getData()%(float)(double)other.getData());
        }
        else {
            throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
        }
    }

    public Var copy() {
        return new FloatVar(this.getName(),(float)this.getData());
    }

    //Static methods
    public static int floatToRoundedInt(float f) {
        return (float)f<0 ? (int)Math.floor(f) : (int)Math.ceil(f);
    }
}
// Revisado por Fernando
