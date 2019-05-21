//Igor Andrey Ronsoni
<igorandrey@yahoo.com.br>
import java.util.ArrayList;
import java.util.HashMap;

public class Function{
	private String nameOfFunction,type;
	private HashMap<String,Var> parameters = new HashMap<String,Var>(), scope;
	ArrayList<String> parametersNames = new ArrayList<String>();
	private ArrayList<Object> content = new ArrayList<Object>();
	private Parser p;

	//Construtores	
	public Function(){
		p = new Parser(false);
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

	public Var run(ArrayList<Var> pars,HashMap<String,Function> functions) throws RuntimeException {
		scope = new HashMap<String,Var>(parameters);
		ArrayList<Object> content = getContent();

		for (int i=0; i<pars.size(); ++i) {
			if (!(scope.get(parametersNames.get(i)).getType().equals(pars.get(i).getType()))) throw new RuntimeException("Invalid type for parameter");
			scope.put(parametersNames.get(i),pars.get(i));
		}

		for (int i=getNumberOfParameters(); i<content.size(); i++) {
			if (content.get(i) instanceof ArrayList<?>) {
				Var r = p.parseBlock(Parser.objectToALObject(content.get(i)),scope,functions);
				if (r instanceof Var) return r;
			}
			else {
				String value = content.get(i).toString();
				if(value.startsWith("return")){
					value = value.substring(6,value.length());
					return Parser.evaluateStackByPriority(Parser.expressionStack(value,scope,functions),scope);
				}
				Parser.evaluateStackByPriority(Parser.expressionStack(value,scope,functions),scope);
			}
		}
		return null;
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
