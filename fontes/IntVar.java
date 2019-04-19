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
        if (other.getType().equals("float")) {
            double tmp = (int)this.getData()-(float)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        if (other.getType().equals("int")) {
            double tmp = (int)this.getData()-(int)other.getData();
            return tmp<0 ? (int)Math.floor(tmp) : (int)Math.ceil(tmp);
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo functions: " + this.getType() + " and " + other.getType());
        }
    }
}
