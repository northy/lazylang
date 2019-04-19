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
        if (other.getType().equals("float")) {
            double tmp = (double)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        if (other.getType().equals("int")) {
            double tmp = (double)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo functions: " + this.getType() + " and " + other.getType());
        }
    }
}
