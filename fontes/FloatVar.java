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
        if (other.getType().equals("float")) {
            double tmp = (float)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        if (other.getType().equals("int")) {
            double tmp = (float)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo functions: " + this.getType() + " and " + other.getType());
        }
    }
}
