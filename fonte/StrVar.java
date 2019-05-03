public class StrVar extends Var{
	protected String data;

	public StrVar(){
		this.type = "string";
	}
	public CharVar(String name,String data) {
        this();
        this.setName(name);
        this.setData(data);
    }

    public CharVar(String data) {
        this("__tmp",data);
    }

    //getters
   @Override
	public String getData() {
		return this.data;
	}
    @override
    //setters
    public void setData(String d) {
        this.data=d;
    }

    public void setData(Object d) throws OperatorException {
        try {
            this.data=(String)((String) d).stringValue();
            return;
        }
        catch (Exception e) {
            throw new OperatorException("Unable to cast value to variable",e);
        }
    }
}