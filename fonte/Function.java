import java.util.ArrayList;
import java.util.HashMap;

public class Function{
	private String nameOfFunction,type;
	private HashMap<String,Var> parameters = new HashMap<String,Var>();
	private ArrayList<Object> scope = new ArrayList<Object>();

	//Construtores	
	public Function(){
		this.type = "function";
	}

	public Function(String name,String par,ArrayList<Object> scope){
		this();
		this.setName(name);
		this.setScope(scope);
		this.setParameters(par);
	}

	public void setName(String name){
		this.nameOfFunction = name;
	}

	public void setScope(ArrayList<Object> sco){
		this.scope = sco;
	}

	public void setParameters(String par){}

	public Object returns(Object value){
		return value;
	}

	public HashMap<String,Var> getParameters(){
		return this.parameters;
	}

	public int getNumberOfParameters(){
		return this.parameters.size();
	}

	public void run(HashMap<String,Function> functions) {
		ArrayList<Object> scope = getScope();
		for (int i=0; i<scope.size(); ++i) {
			if (scope.get(i) instanceof ArrayList<?>) {
				Parser p = new Parser(false);
				p.parseBlock((ArrayList<Object>)scope.get(i),parameters,functions);
			}
			else {
				Parser.evaluateStackByPriority(Parser.expressionStack((String)scope.get(i),parameters,functions),parameters);
			}
		}
	}

	public ArrayList<Object> getScope(){
		return this.scope;
	}

	public String getName(){
		return this.nameOfFunction;
	}

	public String getType(){
		return this.type;
	}
}
