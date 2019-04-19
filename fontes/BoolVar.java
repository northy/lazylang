public class BoolVar extends Var {
    protected boolean data;

    //construtores
    public BoolVar() {
        this.type="bool";
    }
    public BoolVar(String name,boolean data) {
        this();
        this.setName(name);
        this.setData(data);
    }
    public BoolVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(intToBool(data));
    }

    //getters
    @Override
    public Boolean getData() {
        return this.data;
    }

    //setters
    public void setData(boolean d) {
        this.data=d;
    }
    public void setData(int d) {
        this.data=intToBool(d);
    }

    //métodos
    public static boolean intToBool(int i) {
        return i==0 ? false : true;
    }
    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        if (other.getType().equals("bool")) {
            return boolToInt((boolean)this.getData())-boolToInt((boolean)other.getData());
        }
        else {
            throw new ArithmeticException("Uncompatible types for compareTo functions: " + this.getType() + " and " + other.getType());
        }
    }
}
