import java.util.HashMap;

class Parser{
	
	public static void variableCreation(String type,String s,HashMap<String,Var> hash){
		String varName = "";
		int init; 
			
		//Type Int
		if(type.equals("int")){
			String value = "0";
			int control;
			init = 3;
			//Criação de variavel e atribuição
			while(init < s.length() && s.charAt(init) != ';'){
				if(s.charAt(init) != ',' &&  s.charAt(init) != '='){
					varName += s.charAt(init);
				}
				if(s.charAt(init) == '='){
					value = "";
					control = init + 1;
					
					//Percorre o valor e add ele a uma variavel
					for(int l = init + 1; s.charAt(control) != ',' && s.charAt(control) != ';'; l++){
						value += s.charAt(l);								
						control++;
					}
					init = control;
				}
				if(s.charAt(init) == ',' || s.charAt(init) == ';' || s.charAt(init + 1) == ';'){
					hash.put(varName, new IntVar(varName,Integer.parseInt(value)));
					varName = "";
					value = "0";
				}
				init++;
			}
			return;				 	
		}

		//Type Double
		if(type.equals("double")){	
			String value = "0.0";
			int control;

			init = 6;

			//Criação de variavel e atribuição
			while(init < s.length() && s.charAt(init) != ';'){
				if(s.charAt(init) != ',' &&  s.charAt(init) != '='){
					varName += s.charAt(init);
				}
				if(s.charAt(init) == '='){
					value = "";
					control = init + 1;

					//Percorre o valor e add ele a uma variavel
					for(int l = init + 1; s.charAt(control) != ',' && s.charAt(control) != ';'; l++){
						value += s.charAt(l);								
						control++;
					}
					init = control;
				}
				if(s.charAt(init) == ',' || s.charAt(init) == ';' || s.charAt(init + 1) == ';'){
					hash.put(varName, new DoubleVar(varName,Double.parseDouble(value)));
					varName = "";
					value = "0.0";
				}
				init++;
			}
			return;
		}

		//Type Bool
		if(type.equals("bool")){
			String value = "0";
			int control,size;

			init = 4;

			//Criação de variavel e atribuição
			while(init < s.length() && s.charAt(init) != ';'){
				if(s.charAt(init) != ',' &&  s.charAt(init) != '='){
					varName += s.charAt(init);
				}
				if(s.charAt(init) == '='){
					value = "";
					control = init + 1;
					//Percorre o valor e add ele a uma variavel
					for(int l = init + 1; s.charAt(control) != ',' && s.charAt(control) != ';'; l++){
						value += s.charAt(l);								
						control++;
					}
					init = control;
				}
				if(s.charAt(init) == ',' || s.charAt(init) == ';' || s.charAt(init + 1) == ';'){
					if(value.equals("false")){
						hash.put(varName, new BoolVar(varName,false));
					}else if(value.equals("true")){
						hash.put(varName, new BoolVar(varName,true));
					}else if(value.charAt(0) == '0'){
						hash.put(varName, new BoolVar(varName,Integer.parseInt(value)));
					}else{
						hash.put(varName, new BoolVar(varName,Integer.parseInt(value)));
					}
					varName = "";
					value = "0";
				}
				init++;
			}
			return;
		}

		//Type String
		if(type.equals("string")){
			return; 
		}
	}

	public static void structureCondition(String s,HashMap<String,Var> hash){
		return;
	}

	public static void structureRepetition(String s,HashMap<String,Var> hash){
		return;
	}

	public static void parse(String s, HashMap<String,Var> h){
		String subs = "",type = "";
		int old = 0;

		//Percorre a linha
		s = s.replaceAll(" ","");
		for(int i = 0; i < s.length(); i++){
			subs += s.substring(i,i+1);

			if(s.charAt(i) == ';'){
				
				for(int j = old; j < i; j++){
					type += s.substring(j,j+1);
					if(type.equals("int") || type.equals("double") || type.equals("bool")){
						variableCreation(type,subs,h);
						subs = "";
						old = i+1;
						type = "";
						break;
					}
					//
				}
			}
		}
	}
}
