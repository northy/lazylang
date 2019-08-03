//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

import java.util.ArrayList;

public class Vector extends Var {
	protected ArrayList<Var> data;

    //construtores
    public Vector() {
		this.type="vector";
		this.data=new ArrayList<Var>();
    }

    public Vector(String name) {
        this();
        this.setName(name);
    }

    //getters
    @Override
    public ArrayList<Var> getData() {
        return this.data;
    }

    //setters
    public void setData(ArrayList<Var> d) throws OperatorException {
        this.data=d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(Object d) throws OperatorException {
		try {
			this.data = (ArrayList<Var>)d;
		} catch (Exception e) {
			throw new OperatorException("Unexpected data for vector assignment");
		}
	}

	//métodos
	public int compareTo(Var other) {
		try {
            return this.size().getData()-((Vector)other).size().getData();
        } catch (Exception e) {
            throw new ArithmeticException("Uncompatible types for compareTo function: " + this.getType() + " and " + other.getType());
        }
	}

	public void append(Var x) {
		this.getData().add(x.copy());
	}

	public void appendPointer(Var x) {
		this.getData().add(x);
	}

	public void insert(int i, Var x) {
		this.getData().add(i,x);
	}

	public void removeByIndex(int i) {
		this.getData().remove(i);
	}

	public void remove(Var x) {
		this.getData().remove(x);
	}

	public void clear() {
		this.getData().clear();
	}

	public Var pop() {
		Var x = this.getData().get((int)this.size().getData()-1);
		this.getData().remove((int)this.size().getData()-1);
		return x;
	}

	public Var pop(int i) {
		Var x = this.getData().get(i);
		this.getData().remove(i);
		return x;
	}

	public IntVar size() {
		return new IntVar(this.getData().size());
	}

	public Var get(int i) {
		return this.getData().get(i);
	}

	public IntVar countOccurrences(Var x) {
		int count=0;
		for (Var v:this.getData()) {
			if (v.equals(x)) count++;
		}
		return new IntVar(count);
	}

	public Vector copy() {
		Vector v = new Vector(this.getName());
		for (Var x:this.getData()) {
			v.append(x.copy());
		}
		return v;
	}

	@Override
	public void print() {
		System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content:");
		for (int i=0; i<(int)this.size().getData(); ++i) {
			System.out.printf("Index [%d]: ",i);
			System.out.println(this.get(i).getData());
		}
	}
}
