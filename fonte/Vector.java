import java.util.ArrayList;

class Vector extends Var {
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

	//métodos
	public int compareTo(Var other) {
		return 0; //TODO
	}

	public void append(Var x) {
		this.getData().add(x);
	}

	public void insert(int i, Var x) {
		this.getData().add(i,x);
	}

	public void remove(int i) {
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

	public IntVar count(Var x) {
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