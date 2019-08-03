//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

public abstract class Var {
    protected String name;
    protected String type;

    //getters
    public abstract Object getData();
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }

    //setters
    public void setName(String name) {
        this.name=name;
    }
    public abstract void setData(Object d);

    //métodos
    public void print() {
        System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content: " + this.getData());
    }

    public boolean equals(Var other) {
        return this.getData() == other.getData();
    }

    public abstract int compareTo(Var other);

    public boolean lAnd(Var other) throws OperatorException{
        boolean t=false, o=false, step=false;

        try {
            t=((Number)this.getData()).doubleValue() != 0;
            step=true;
        }
        catch (Exception e) {}
        try {
            t=(boolean)this.getData();
            step=true;
        }
        catch (Exception e) {
            if (!step) throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }

        step=false;

        try {
            o=((Number)other.getData()).doubleValue() != 0;
            step=true;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            step=true;
        }
        catch (Exception e) {
            if (!step) throw new OperatorException("Uncompatible types for logical and function: " + this.getType() + " and " + other.getType());
        }

        return t&&o;
    }

    public boolean lOr(Var other) throws OperatorException{
        boolean t=false, o=false, step=false;

        try {
            t=((Number)this.getData()).doubleValue() != 0;
            step=true;
        }
        catch (Exception e) {}
        try {
            t=(boolean)this.getData();
            step=true;
        }
        catch (Exception e) {
            if (!step) throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }

        step=false;

        try {
            o=((Number)other.getData()).doubleValue() != 0;
            step=true;
        }
        catch (Exception e) {}
        try {
            o=(boolean)other.getData();
            step=true;
        }
        catch (Exception e) {
            if (!step) throw new OperatorException("Uncompatible types for logical or function: " + this.getType() + " and " + other.getType());
        }

        return t||o;
    }

    public boolean lNot() {
        boolean t;

        try {
            t=((Number)this.getData()).doubleValue() != 0;
            return !t;
        }
        catch (Exception e) {}
        try {
            t=(boolean)this.getData();
            return !t;
        }
        catch (Exception e) {
            throw new OperatorException("Uncompatible type for logical not function: " + this.getType());
        }
    }

    public Var add(Var other) throws OperatorException {
        if (this instanceof StrVar || other instanceof StrVar) {
            String a = this.getData().toString();
            String b = other.getData().toString();
            return new StrVar("__tmp", a+b);
        }
        double t, o;
        try {
            t = ((Number)this.getData()).doubleValue();
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic add function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t+o);
    }

    public Var sub(Var other) throws OperatorException {
        double t, o;
        try {
            t = ((Number)this.getData()).doubleValue();
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic sub function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t-o);
    }
    
    public Var mult(Var other) throws OperatorException {
        if (this instanceof StrVar) {
            String a = this.getData().toString();
            double b = ((Number)other.getData()).doubleValue();
            String c = "";
            for (int i=0; i<b; ++i) {
                c+=a;
            }
            return new StrVar("__tmp", c);
        }
        if (other instanceof StrVar) {
            String a = other.getData().toString();
            double b = ((Number)this.getData()).doubleValue();
            String c = "";
            for (int i=0; i<b; ++i) {
                c+=a;
            }
            return new StrVar("__tmp", c);
        }
        double t, o;
        try {
            t = ((Number)this.getData()).doubleValue();
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic mult function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t*o);
    }

    public Var div(Var other) throws OperatorException {
        double t, o;
        try {
            t = ((Number)this.getData()).doubleValue();
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic div function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t/o);
    }

    public Var mod(Var other) throws OperatorException {
        double t, o;
        try {
            t = ((Number)this.getData()).doubleValue();
            o = ((Number)other.getData()).doubleValue();
        } catch (Exception e) {
            throw new OperatorException("Uncompatible types for arithmetic mod function: " + this.getType() + " and " + other.getType());
        }

        return new FloatVar("__tmp", t%o);
    }

    public abstract Var copy();

    public String toString() {
        return this.getData().toString();
    }
}
