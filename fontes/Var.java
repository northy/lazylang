public abstract class Var {
    protected String name;
    protected Object data;
    protected String type;

    //getters
    public Object getData() {
		return null;
    }
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

    //métodos
    public void print() {
        System.out.println("Variable name: " + this.getName() + " | Type: " + this.getType() + " | Content: " + this.getData());
    }
}
