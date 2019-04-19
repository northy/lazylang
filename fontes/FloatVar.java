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
}
