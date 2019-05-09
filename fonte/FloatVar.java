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

    @Override
    public boolean lAnd(Var other) throws OperatorException {
        boolean t, o;
        t=this.getData() != 0;

        try {
            o=((Number)other.getData()).doubleValue() != 0;
            return t && o;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            return t && o;
        }
        catch (Exception e) {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lOr(Var other) throws OperatorException {
        boolean t, o;
        t=this.getData() != 0;

        try {
            o=((Number)other.getData()).doubleValue() != 0;
            return t || o;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            return t || o;
        }
        catch (Exception e) {
            throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }
    }

    @Override
    public boolean lNot() {
        return !(this.getData() != 0);
    }

    @Override
    public Var add(Var other) throws OperatorException {
        double t, o;
        t = this.getData().doubleValue();
        try {
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t+o);
    }

    @Override
    public Var sub(Var other) throws OperatorException {
        double t, o;
        t = this.getData().doubleValue();
        try {
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t-o);
    }

    @Override
    public Var mult(Var other) throws OperatorException {
        double t, o;
        t = this.getData().doubleValue();
        try {
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t*o);
    }

    @Override
    public Var div(Var other) throws OperatorException {
        double t, o;
        t = this.getData().doubleValue();
        try {
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t/o);
    }

    @Override
    public Var mod(Var other) throws OperatorException {
        double t, o;
        t = this.getData().doubleValue();
        try {
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t%o);
    }

    public Var copy() {
        return new FloatVar(this.getName(),this.getData());
    }
}
