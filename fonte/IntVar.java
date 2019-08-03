//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

public class IntVar extends Var {
    protected int data;

    //construtores
    public IntVar() {
        this.type="int";
    }
    
    public IntVar(String name){
        this();
        this.setName(name);
    }

    public IntVar(String name,int data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public IntVar(int data) {
        this("__tmp",data);
    }

    public IntVar(Object data) {
        this("__tmp");
        this.setData(data);
    }

    //getters
	public Integer getData() {
		return this.data;
	}

    //setters
    public void setData(int d) {
        this.data=d;
    }

    public void setData(Object d) throws OperatorException {
        try {
            this.data=((Number)d).intValue();;
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
        return new IntVar(this.getName(),this.getData());
    }
}
