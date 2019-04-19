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
}
