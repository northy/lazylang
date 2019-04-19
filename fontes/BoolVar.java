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
    protected static boolean intToBool(int i) {
        return i==0 ? false : true;
    }
}
