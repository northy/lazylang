import java.util.ArrayList;
import java.util.HashMap;

public class Function{
	private String nameOfFunction,type;
	private HashMap<String,Var> parameters = new HashMap<String,Var>();
	private HashMap<String,ArrayList<Object>> scope = new HashMap<String,ArrayList<Object>>();

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
		this.scope.put(this.getName(),sco);
	}

	public void setParameters(String par){
		if(par.equals("")){
			this.parameters.put("",new StrVar());
			return;
		}

		Parser p = new Parser(false); 
		String variable = "";
		par += ",";

		
		for(int i = 0; i < par.length(); i++){
			
			variable += par.charAt(i);

			if(par.charAt(i) == ','){
				variable = variable.substring(0,variable.length()-1);
				variable += ";";
				p.parse(variable,this.parameters);
				variable = "";
			}				
		}
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

	public HashMap<String,ArrayList<Object>> getScope(){
		return this.scope;
	}

	public String getName(){
		return this.nameOfFunction;
	}

	public String getType(){
		return this.type;
	}
}
