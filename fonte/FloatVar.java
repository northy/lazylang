public class FloatVar extends Var {
    protected double data;

    //construtores
    public FloatVar() {
        this.type="float";
    }
    
    public FloatVar(String name){
        this();
        this.setName(name);
    }

    public FloatVar(String name,double data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public FloatVar(double data) {
        this("__tmp",data);
    }

    public FloatVar(Object data) {
        this("__tmp");
        this.setData(data);
    }

    //getters 
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
            this.data=((Number)d).doubleValue();
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        try {
            double tmp = ((Number)this.getData()).doubleValue()-((Number)other.getData()).doubleValue();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        } catch (Exception e) {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
    }

    public Var copy() {
        return new FloatVar(this.getName(),this.getData());
    }
}
