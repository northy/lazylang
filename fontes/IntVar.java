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
}
