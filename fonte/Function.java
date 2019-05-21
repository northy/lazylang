import java.util.ArrayList;
import java.util.HashMap;

public class Function{
	private String nameOfFunction,type;
	private HashMap<String,Var> parameters = new HashMap<String,Var>(), scope;
	ArrayList<String> parametersNames = new ArrayList<String>();
	private ArrayList<Object> content = new ArrayList<Object>();

	//Construtores	
	public Function(){
		this.type = "function";
	}

	public void setName(String name){
		this.nameOfFunction = name;
	}

	public void setContent(ArrayList<Object> con){
		this.content = con;
	}

	public void addParameter(String par,HashMap<String,Function> functions){
		Var ret = Parser.evaluateStackByPriority(Parser.expressionStack(par,parameters,functions),parameters);
		parametersNames.add(ret.getName());
	}

	public Object returns(Object value){
		return value;
	}

	public HashMap<String,Var> getParameters(){
		return this.parameters;
	}

	public int getNumberOfParameters(){
		return this.parameters.size();
	}

	public void run(ArrayList<Var> pars,HashMap<String,Function> functions) throws RuntimeException {
		scope = new HashMap<String,Var>(parameters);
		ArrayList<Object> content = getContent();

		for (int i=0; i<pars.size(); ++i) {
			if (!(scope.get(parametersNames.get(i)).getType().equals(pars.get(i).getType()))) throw new RuntimeException("Invalid type for parameter");
			scope.put(parametersNames.get(i),pars.get(i));
		}

		for (int i=getNumberOfParameters(); i<content.size(); ++i) {
			if (content.get(i) instanceof ArrayList<?>) {
				Parser p = new Parser(false);
				p.parseBlock(Parser.objectToALObject(content.get(i)),scope,functions);
			}
			else {
				Parser.evaluateStackByPriority(Parser.expressionStack((String)content.get(i),scope,functions),scope);
			}
		}
	}

	public ArrayList<Object> getContent(){
		return this.content;
	}

	public String getName(){
		return this.nameOfFunction;
	}

	public String getType(){
		return this.type;
	}
}
