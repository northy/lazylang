import java.util.HashMap;

class Parser{
	
	//Em caso de ter um tipo primitivo na linha de execução, esta função atribui a variavel a seu tipo e o valor vaso estejam na mesma linha
	private static void variableCreation(String type,String s,HashMap<String,Var> hash){
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

	private static void structureCondition(String s,HashMap<String,Var> hash){
		return;
	}

	public static void structureRepetition(String s,HashMap<String,Var> hash){
		return;
	}

	//Retorna qual tem a precedencia mais alta
	private static int precedence(String operator1,String operator2){
		
		//retorna 0 caso não haja operadores suficientes para comparação ou ambos são iguais
		if(operator1.equals(operator2) || operator1.equals("") || operator2.equals("")){
			return 0;
		}
		//Precedencia de operadores
		String[] prece = new String[20];
		//**
		prece[0] = "(";//Abre parenteses
		prece[1] = ")";//Fecha parenteses
		prece[2] = "*";//Multiplicação
		prece[3] = "/";//Divisão
		prece[4] = "%";//Modulo
		prece[5] = "+";//Adição
		prece[6] = "-";//Subtração
		prece[7] = "++";//Adição da variavel em +1
		prece[8] = "--";//Subtração da variavel em -1
		prece[9] = "+=";//Adição de ... na variavel
		prece[10] = "-=";//Subtração de ... na variavel
		prece[11] = "<";//Menor que
		prece[12] = "<=";//Menor ou igual que
		prece[13] = ">";//Maior que 
		prece[14] = ">=";//Maior ou igual que
		prece[15] = "==";//Igual a 
		prece[16] = "!=";//Diferente de
		prece[17] = "&&";//And
		prece[18] = "||";//Or
		prece[19] = "!";//Not
		//**

		int indexOperator1 = 0, indexOperator2 = 0;
		for(int i = 0; i < 20; i++){
			if(operator1.equals(prece[i])){
				indexOperator1 = i;
			}
			if(operator2.equals(prece[i])){
				indexOperator2 = i;
			}
		}

		//Compara qual tem a precedencia maior
		//retorna 1 caso o operador2 tiver maior procedencia
		if(indexOperator1 >= indexOperator2){
			return 1;
		}
		//retorna 2 caso o operador 1 tiver maior procedencia
		if(indexOperator1 < indexOperator2){
			return 2;
		}
		return 3;
	}

	//Retorna uma lista de quais execuções devem ser feitas primeiras (prioridade)
	private static HashMap<String,String> priority(String express){
		HashMap<String,String> precedence = new HashMap<String,String>();
		
		int control = 0,parenteses = 0,quant = 0;
		
		if(express.charAt(express.length() - 1) != ';'){
			express += ";";
		}

		//Percorre o valor para ver se existe prioridade, caso exista, descobre se todos os parenteses estão fechados		
		while(express.charAt(control) != ';'){
			if(express.charAt(control) == '('){
				parenteses++;
				quant++;
			}
			if(express.charAt(control) == ')'){
				parenteses--;
			}
			control++;
		}

		//Verifica se há erros na quantidade de parenteses
		if(parenteses == 0){
			
			//Configuration of name -- #@_ -- where has _ exist one number of line. Ex: #@0, #@1 ... 
			String value = "",configName = "#@",operator = "";
			int pri = 0,initExpress = 0,initSecond = 0,endSecond = 0;
			boolean continuar = true;
			control = 0;
			
			//Percore toda a expressão descobrindo qual operação será realizada primeiro
			while(continuar){
				
				//Prioridade em resolver o que esta entre parenteses
				if(express.charAt(control) == '('){
					break;//Falta implementar
				}

				//Caso encontre um operador
				if(express.charAt(control) == '+' || express.charAt(control) == '-' || //Continua na proxima linha 
					express.charAt(control) == '*' || express.charAt(control) == '/' || express.charAt(control) == '%'){
					
					//Verifica se não é um somador ou subtrator da propria variavel
					if(express.charAt(control + 1) == '+' || express.charAt(control + 1) == '-'){
						break;
					}
					else{

						//Verifica se na expressão salva em value ja existe um operador
						if(operator.equals("")){
							operator = Character.toString(express.charAt(control));
							initSecond = control +1;
						}else{
							endSecond = control -1;
							
							//Verifica a precendencia dos operadores
							if(precedence(operator,Character.toString(express.charAt(control))) == 1){
								value = express.substring(initSecond,endSecond + 1);
								initExpress = initSecond;

							//Atribui a expressão para o HashMap
							}else{
								precedence.put(configName + pri,value);

								//Aprimorar essa execução
								express = express.replace(value,configName + pri);
								control = 0;

								pri++;
								value = "";	
								operator = "";					
							}

						}
					}
				}

				//Verifica se terminou a execução da linha
				if(express.charAt(control) == ';'){
					precedence.put(configName + Integer.toString(pri),value);

					//Aprimorar essa execução/**
					express = express.replace(value,configName + Integer.toString(pri));
					//**

					continuar = !express.equals(configName + Integer.toString(pri) + ";");
					control = 0;
					pri++;
					value = "";	
					operator = "";
				}

				//Caso nenhum dos casos seja executado ele salva o character em value para futura comparação
				else{

					//Verifica se a variavel value esta vazia para demarcar onde começa a expressão
					if(value.equals("")){
						initExpress = control;
					}

					value += express.charAt(control);
					control++;
				}
			}
			
		}else{
			//Deve retornar um error
			return precedence;
		}
		return precedence;
	}
	

	public static void parse(String s, HashMap<String,Var> h){
		String subs = "",variable = "",operator = "";
		int old = 0;

		//Percorre a linha
		s = s.replaceAll(" ","");
		for(int i = 0; i < s.length(); i++){
			subs += s.substring(i, i+1);

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
	
					//Possivel alteração/atribuição de valor a variavel
					operator = Character.toString(variable.charAt(j));
					if(operator.equals("-") || operator.equals("+") || operator.equals("*") || operator.equals("/") || operator.equals("=")){
						
						HashMap<String,String> pri = new HashMap<String,String>();
						pri = priority(subs);
						for(String args : pri.values()){
							System.out.println(args);
						}
						break;
					}
				}
			}
		}
	}
}
