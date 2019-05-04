import java.util.HashMap;
/***** Passando para o Giovane *//// obs: Não funciona corretamente
class Parser{
	
	//Em caso de ter um tipo primitivo na linha de execução, esta função atribui a variavel a seu tipo e o valor vaso estejam na mesma linha
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
					hash.put(varName, new IntVar(varName));
					Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new IntVar(Integer.parseInt(value)));
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
					hash.put(varName, new DoubleVar(varName));
					Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new DoubleVar(Double.parseDouble(value)));
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
						hash.put(varName, new BoolVar(varName));
						Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new BoolVar(false));
					}else if(value.equals("true")){
						hash.put(varName, new BoolVar(varName));
						Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new BoolVar(true));
					}else if(value.charAt(0) == '0'){
						hash.put(varName, new BoolVar(varName));
						Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new IntVar(Integer.parseInt(value)));
					}else{
						hash.put(varName, new BoolVar(varName));
						Expression.evaluate(hash.get(varName), AssignmentOperator.ASSIGN,new IntVar(Integer.parseInt(value)));
					}
					varName = "";
					value = "0";
				}
				init++;
			}
			return;
		}
		
		//Type String
		if(type.equals("str")){
			System.out.println("Funciona"); 
		}
	}

	//********** Falta impplementar ***********\\
	/*
	public static void alterationVariable(String s, String operator,HashMap<String,Var> hash){
		String nameOfVariable = "",value = "";
		int control = 0;
		while(s.charAt(control) != '+' && s.charAt(control) != '-' && s.charAt(control) != '/' && s.charAt(control) != '*' && s.charAt(control) != '='){
			nameOfVariable += s.charAt(control);
			control++;

		}

		if(operator.equals("+")){
			if(s.charAt(control++) == '='){
				for(int i = control + 1; s.charAt(control) != ';'; i++){
					value += s.charAt(i);
				}
				//priority(nameOfVariable,operator,value,hash);
			}else{
				return;
			}

		}
	}
	*/

	public static void structureCondition(String s,HashMap<String,Var> hash){
		return;
	}

	public static void structureRepetition(String s,HashMap<String,Var> hash){
		return;
	}

	//Retorna uma lista de quais execuçoes devem ser feitas primeiras (prioridade)
	public static HashMap<String,String> priority(String expression){
		HashMap<String,String> primary = new HashMap<String,String>();
		
		//Configuration of name -- #@pri@#_ -- where has _ exist one number of line. Ex: #@pri@#1, #@pri@#2 ... 
		String configName = "#@pri@#",express = "",init = "";
		int control = 0,parenteses = 0,quant = 0,pri = 0;
		
		//Percorre o valor para ver se existe prioridade, caso exista, descobre se todos os parenteses estão fechados		
		while(expression.charAt(control) != ';'){
			if(expression.charAt(control) == '('){
				parenteses++;
				quant++;
			}
			if(expression.charAt(control) == ')'){
				parenteses--;
			}
			control++;
		}
		//Verifica se há erros na quantidade de parenteses
		express = expression;
		if(parenteses == 0){
			control = 0;
			//while(){
			///
			//	control++;
	//		}
			
		}else{
			//Deve retornar um error
			return primary;
		}
		return primary;
	}
	

	public static void parse(String s, HashMap<String,Var> h){
		String subs = "",variable = "",operator = "";
		int old = 0;

		//Percorre a linha
		s = s.replaceAll(" ","");
		for(int i = 0; i < s.length(); i++){
			subs += s.substring(i,i+1);

			if(s.charAt(i) == ';'){
				
				for(int j = old; j < i; j++){
					variable += s.substring(j,j+1);

					//Instanciação das variaveis
					if(variable.equals("int") || variable.equals("double") || variable.equals("bool") || variable.equals("str")){
						variableCreation(variable,subs,h);
						subs = "";
						old = i+1;
						variable = "";
						break;
					}
					//*****   Falta implementar *******\
					//Possivel alteração/atribuição de valor a variavel
					operator += subs.charAt(j + 1);
					//if(operator.equals("-") || operator.equals("+") || operator.equals("*") || operator.equals("/") || operator.equals("=")){
						///HashMap<String,String> pri = new HashMap<String,String>();
						///pri = priority("5+1*2;");
						///System.out.println(pri.values());
					///	return;
					//}
				}
			}
		}
	}
}
