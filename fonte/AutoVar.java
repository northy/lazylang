//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

import java.util.ArrayList;

public class AutoVar extends Var {
    protected Object data;

    //construtores
    public AutoVar() {
        this.type="auto";
        this.name="__tmp";
    }

    public AutoVar(String name) {
        this();
        this.setName(name);
    }

    //getters 
    public Object getData() {
        return this.data;
    }

    public void setData(Object d,String type) throws OperatorException {
        this.type = type;
        this.data = d;
    }

    @Override
    public void setData(Object d) {
        if (d instanceof Integer) {
            this.setData(d,"int");
        }
        else if (d instanceof Double) {
            this.setData(d,"float");
        }
        else if (d instanceof Boolean) {
            this.setData(d,"bool");
        }
        else if (d instanceof String) {
            this.setData(d,"str");
        }
        else if (d instanceof Character) {
            this.setData(d,"char");
        }
        else if (d instanceof ArrayList) {
            this.setData(d,"vector");
        }
        else {
            throw new RuntimeException("Fatal error, could not detect type");
        }
    }

    //métodos
    @Override
    public int compareTo(Var other) throws ArithmeticException {
        //todo
        return 0;
    }

    public Var copy() {
        Var v = new AutoVar();
        v.setName(this.getName());
        v.setData(this.getData());
        return v;
    }

    //métodos de variável
    @Override
	public void print() {
        if (this.getType().equals("vector")) {
            System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content:");
            for (int i=0; i<(int)this.size().getData(); ++i) {
                System.out.printf("Index [%d]: ",i);
                System.out.println(this.get(i).getData());
            }
        }
        else {
            super.print();
        }
    }

    public String concat(String other){
        return ((String)this.getData()).concat(other);
    }

    public int codePoint(int index){
        if (this.getType().equals("vector")) {
            int codeValue;
            codeValue = ((String)this.getData()).codePointAt(index);
            return codeValue;
        }
        throw new RuntimeException("Unexpected function call");
    }

    //TODO: finish string methods
    
    @SuppressWarnings("unchecked")
	public void concat(Var x) throws RuntimeException {
        if (this.getType().equals("vector")) {
            if (x.getType().equals("vector")) {
                ((ArrayList<Var>)this.getData()).addAll((ArrayList<Var>)x.getData());
            }
            else {
                throw new RuntimeException("Unsupported types for concat");
            }
        }
	}

	@SuppressWarnings("unchecked")
	public Vector concatNew(Var x) throws RuntimeException {
        if (this.getType().equals("vector")) {
            Vector v = new Vector();
            if (x.getType().equals("vector")) {
                v.getData().addAll((ArrayList<Var>)this.getData());
                v.getData().addAll((ArrayList<Var>)x.getData());
            }
            else {
                throw new RuntimeException("Unsupported types for concat");
            }
            return v;
        }
        throw new RuntimeException("Unexpected function call");
	}

	@SuppressWarnings("unchecked")
	public void append(Var x) {
        if (this.getType().equals("vector")) {
            ((ArrayList<Var>)this.getData()).add(x.copy());
        }
	}

	@SuppressWarnings("unchecked")
	public void appendPointer(Var x) {
        if (this.getType().equals("vector")) {
		    ((ArrayList<Var>)this.getData()).add(x);
        }
	}

	@SuppressWarnings("unchecked")
	public void insert(int i, Var x) {
        if (this.getType().equals("vector")) {
		    ((ArrayList<Var>)this.getData()).add(i,x);
        }
	}

	@SuppressWarnings("unchecked")
	public void removeByIndex(int i) {
        if (this.getType().equals("vector")) {
		    ((ArrayList<Var>)this.getData()).remove(i);
        }
	}

	@SuppressWarnings("unchecked")
	public void remove(Var x) {
        if (this.getType().equals("vector")) {
		    ((ArrayList<Var>)this.getData()).remove(x);
        }
	}

	@SuppressWarnings("unchecked")
	public void clear() {
        if (this.getType().equals("vector")) {
		    ((ArrayList<Var>)this.getData()).clear();
        }
	}

	@SuppressWarnings("unchecked")
	public Var pop() {
        if (this.getType().equals("vector")) {
            Var x = ((ArrayList<Var>)this.getData()).get((int)this.size().getData()-1);
            ((ArrayList<Var>)this.getData()).remove((int)this.size().getData()-1);
            return x;
        }
        throw new RuntimeException("Unexpected function call");
	}

	@SuppressWarnings("unchecked")
	public Var pop(int i) {
        if (this.getType().equals("vector")) {
            Var x = ((ArrayList<Var>)this.getData()).get(i);
            ((ArrayList<Var>)this.getData()).remove(i);
            return x;
        }
        throw new RuntimeException("Unexpected function call");
	}

	@SuppressWarnings("unchecked")
	public IntVar size() {
        if (this.getType().equals("vector")) {
		    return new IntVar(((ArrayList<Var>)this.getData()).size());
        }
        throw new RuntimeException("Unexpected function call");
	}

	@SuppressWarnings("unchecked")
	public Var get(int i) {
        if (this.getType().equals("vector")) {
		    return ((ArrayList<Var>)this.getData()).get(i);
        }
        throw new RuntimeException("Unexpected function call");
	}

	@SuppressWarnings("unchecked")
	public IntVar countOccurrences(Var x) {
        if (this.getType().equals("vector")) {
            int count=0;
            for (Var v:((ArrayList<Var>)this.getData())) {
                if (v.equals(x)) count++;
            }
            return new IntVar(count);
        }
        throw new RuntimeException("Unexpected function call");
	}
}
