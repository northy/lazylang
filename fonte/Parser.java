import java.util.HashMap;

import java.util.ArrayList;

public class Parser{
	//Métodos estáticos
	public static Var toVar(String value, HashMap<String,Var> variables) throws OperatorException {
		if (variables.get(value) != null) return variables.get(value);
		try {
			//int
			return new IntVar((int)Integer.valueOf(value));
		}
		catch (Exception e) {}
		try {
			//float
			return new FloatVar((double)Double.valueOf(value));
		}
		catch (Exception e) {}
		try {
			//boolean
			return new BoolVar(Boolean.parseBoolean(value));
		}
		catch (Exception e) {}
		try {
			//TODO: checar se está em aspas e retornar string
		}
		catch (Exception e) {}

		throw new OperatorException("Unable to transform \"" + value + "\" to a variable");
	}

	public static ArrayList<Object> expressionStack(String exp, HashMap<String, Var> variables) {
        int l = exp.length(), i=0;
        Object operator;
        exp+=" "; //Evitar NullPointerException
        ArrayList<Object> stack = new ArrayList<Object>();
        String parsing="";
        char c,c1;

        while (i<l) {
            parsing+=exp.charAt(i);
            
            operator=null;
            c=exp.charAt(i);
            c1=exp.charAt(i+1);

            if (c=='=' || c=='<' || c=='>' || c=='!' || c=='|' || c=='&' || c=='-' || c=='+' || c=='*' || c=='/' || c=='%' || i==l-1) {
				if (i!=l-1) parsing=parsing.substring(0, parsing.length()-1);
				//criação de variaveis
				if (parsing.contains("int")) {
					parsing=parsing.replaceAll("int", "");
					variables.put(parsing, new IntVar(parsing));
				}
				else if (parsing.contains("float")) {
					parsing=parsing.replaceAll("float", "");
					variables.put(parsing, new FloatVar(parsing));
				}
				else if (parsing.contains("bool")) {
					parsing=parsing.replaceAll("bool", "");
					variables.put(parsing, new BoolVar(parsing));
				}
				if (!parsing.equals("")) stack.add(Parser.toVar(parsing, variables));

                if (c=='=' && c1=='=') {
                    operator=ComparisonOperator.EQ;
                    i++;
                }
                else if (c=='<' && c1=='=') {
                    operator=ComparisonOperator.LE;
                    i++;
                }
                else if (c=='>' && c1=='=') {
					operator=ComparisonOperator.GE;
					i++;
                }
                else if (c=='!' && c1=='=') {
                    operator=ComparisonOperator.NE;
                    i++;
                }
                else if (c=='<') {
                    operator=ComparisonOperator.LT;
                }
                else if (c=='>') {
                    operator=ComparisonOperator.GT;
                }
                else if (c=='=') {
                    operator=AssignmentOperator.ASSIGN;
                }
                else if (c=='+' && c1=='=') {
                    operator=AssignmentOperator.ADD_ASSIGN;
                    i++;
                }
                else if (c=='-' && c1=='=') {
                    operator=AssignmentOperator.SUB_ASSIGN;
                    i++;
                }
                else if (c=='*' && c1=='=') {
                    operator=AssignmentOperator.MULT_ASSIGN;
                    i++;
                }
                else if (c=='/' && c1=='=') {
                    operator=AssignmentOperator.DIV_ASSIGN;
                    i++;
                }
                else if (c=='%' && c1=='=') {
                    operator=AssignmentOperator.MOD_ASSIGN;
                    i++;
                }
                else if (c=='-') {
                    operator=ArithmeticOperator.SUB;
                }
                else if (c=='+') {
                    operator=ArithmeticOperator.ADD;
                }
                else if (c=='*') {
                    operator=ArithmeticOperator.MULT;
                }
                else if (c=='/') {
                    operator=ArithmeticOperator.DIV;
                }
                else if (c=='%') {
                    operator=ArithmeticOperator.MOD;
                }
                else if (c=='|' && c1=='|') {
                    operator=LogicalOperator.OR;
                    i++;
                }
                else if (c=='&' && c1=='&') {
                    operator=LogicalOperator.AND;
                    i++;
                }
                else if (c=='!') {
                    operator=LogicalOperator.NOT;
                }

                if (operator instanceof Object) {
                    stack.add(operator);
                }

                parsing="";
            }

            i++;
        }
        
        return stack;
	}
	
	public static Var evaluateStackByPriority(ArrayList<Object> stack, HashMap<String,Var> variables) throws RuntimeException {
		int i=0;
		ArrayList<Integer> lasti = new ArrayList<Integer>();
		boolean rollback=false, debugging=true;

		while (stack.size()!=1 && i<stack.size()) {
			if (rollback) {
				i=lasti.get(lasti.size()-1);
				lasti.remove(lasti.size()-1);
				rollback=false;
			}

			//for debugging purposes
			if (debugging) {
				for (int j=0; j<stack.size(); ++j) {
					if (stack.get(j) instanceof Var) {
						System.out.print("Stack[" + j + "]: ");
						((Var)stack.get(j)).print();
					}
					else {
						System.out.println("Stack[" + j + "]: " + stack.get(j).getClass().getSimpleName());
					}
				}
				System.out.println();
			}

			//!b, int a
			if (i<stack.size() && stack.get(i) instanceof Enum && i+1<stack.size() && stack.get(i+1) instanceof Var) {
				Var res = Expression.evaluate(stack.get(i),stack.get(i+1));
				if (stack.get(i) instanceof CreationOperator) {

				}
				for (int j=0; j<2; ++j) stack.remove(i);
				stack.add(i,res);
				if (lasti.size()!=0) rollback=true;
				continue;
			}
			//a&&!c
			if (i<stack.size() && stack.get(i) instanceof Var && i+1<stack.size() && stack.get(i+1) instanceof Enum && i+2<stack.size() && stack.get(i+2) instanceof Enum && i+3<stack.size() && stack.get(i+3) instanceof Var) {
				lasti.add(i);
				i+=2;
				continue;
			}
			//a+b
			if (i<stack.size() && stack.get(i) instanceof Var && i+1<stack.size() && stack.get(i+1) instanceof Enum && i+2<stack.size() && stack.get(i+2) instanceof Var) {
				//a+b*c
				if (i+3<stack.size() && stack.get(i+3) instanceof Enum && i+4<stack.size() && stack.get(i+4) instanceof Var) {
					System.out.println("x");
					if (Expression.compareOperators(stack.get(i+1),stack.get(i+3))<0) {
						lasti.add(i);
						i+=2;
						continue;
					}
				}
				//a||b&&!c
				if (i+3<stack.size() && stack.get(i+3) instanceof Enum && i+4<stack.size() && stack.get(i+4) instanceof Enum && stack.get(i+5) instanceof Var) {
					if (Expression.compareOperators(stack.get(i+1),stack.get(i+3))<0) {
						lasti.add(i);
						i+=2;
						continue;
					}
				}
				Var res = Expression.evaluate(stack.get(i),stack.get(i+1),stack.get(i+2));
				for (int j=0; j<3; ++j) stack.remove(i);
				stack.add(i,res);
				if (lasti.size()!=0) rollback=true;
				continue;
			}
		}

		return (Var)stack.get(0);
	}
	
	//Instanciação de variavel e atribuição de valor à mesma caso seja passado
	private static void variableCreation(String type,String expression,HashMap<String,Var> variables){
		String varName = "",value = "";
		int init = 0,selectionType = 0,control;
		
		//Verifica qual o tipo primitivo	
		//Type Int
		if(type.equals("int")){
			init = 3;
			selectionType = 1;
		}
		//Type Double
		else if(type.equals("double")){
			init = 6;
			selectionType = 2;
		}
		//Type Boolean
		else if(type.equals("bool")){
			init = 4;
			selectionType = 3;
		}
		//String type
		else if(type.equals("str")){
			init = 3;
			selectionType = 4;
		}

		//Leitura do nome da variavel e atribuição de valor 
		while(init < expression.length() && expression.charAt(init) != ';'){
			//Descobre o nome da variavel
			if(expression.charAt(init) != ',' &&  expression.charAt(init) != '='){
				varName += expression.charAt(init);
			}
			//Verefica se há uma atribuição de valor a variavel
			if(expression.charAt(init) == '='){
				value = "";
				control = init + 1;
				
				//Percorre o valor e add ele a uma variavel
				for(int l = init + 1; expression.charAt(control) != ',' && expression.charAt(control) != ';'; l++){
					value += expression.charAt(l);								
					control++;
				}
				init = control;
			}
			//Add as variaveis nos HashMap e atribui os valores às variaveis
			if(expression.charAt(init) == ',' || expression.charAt(init) == ';' || expression.charAt(init + 1) == ';'){
				
				//Atribuição de valor e instanciação de variavel do tipo INT
				if(selectionType == 1){
					//Verifica se existe valor em VALUE e add valor à variavel
					if(value.equals("")){
						value = "0";
					}
					variables.put(varName, new IntVar(varName));
					Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new IntVar(Integer.parseInt(value)));
				}
				//Atribuição de valor e instanciação de variavel do tipo DOUBLE
				else if(selectionType == 2){
					//Verifica se existe valor em VALUE e add valor à variavel
					if(value.equals("")){
						value = "0";
					}
					variables.put(varName, new FloatVar(varName));
					Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new FloatVar(Double.parseDouble(value)));
				}
				//Atribuição de valor e instanciação de variavel do tipo BOOLEAN
				else if(selectionType == 3){
					if(value.equals("false") || value.equals("0")){
						variables.put(varName, new BoolVar(varName));
						Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new BoolVar(false));
					}
					else if(value.equals("true")){
						variables.put(varName, new BoolVar(varName));
						Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new BoolVar(true));
					}
					else{
						variables.put(varName, new BoolVar(varName));
						Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new BoolVar(true));
					}
				}
				else if(selectionType == 4){	
					variables.put(varName,new StrVar(varName));
					Expression.evaluate(variables.get(varName),AssignmentOperator.ASSIGN,new StrVar(value));
				}
				varName = "";
				value = "0";

			}
			init++;
		}
	}

	//Implementar esse método
	private static void structureCondition(String expression,HashMap<String,Var> variables){
		return;
	}

	//Implementar esse método
	private static void structureRepetition(String expression,HashMap<String,Var> variables){
		return;
	}

	//Retorna um valor do tipo Var independente da expressão //IMPLEMENTAR... OBS: Vou começar à implementar // commit apenas para pegar a ultima atualização
	private static Var parsePriority(String[] vector,String expression,HashMap<String,Var> variables){

		if(expression.charAt(expression.length() -1) != ';'){
			expression += ";";
		}
		if(parenthesesAreRight(expression)){
				
			//Variaveis de controle
			ArrayList<Object> stacks = new ArrayList<Object>();
			String varName = "",operator = "";
			int index = 0;

			//Percorre a expressão
			while(index != 0){
				//Verefica se o character é um operador
				if(isOperator(vector,Character.toString(expression.charAt(index)))){
					if(expression.charAt(index) == ';'){
							break;
					}
					else if(operator.equals("")){
						operator = Character.toString(expression.charAt(index));
						stacks.add(varName);

						//Verefica se o operador possui mais de um character
						if(isOperator(vector,Character.toString(expression.charAt(index + 1)))){
							operator += expression.charAt(index + 1);
						}
						stacks.add(operator);
						varName = "";
						index++;
					}
					else if(!operator.equals("")){
						if(whoPrecede(vector,operator,Character.toString(expression.charAt(index)))){
							
						}
					}

				}
				varName += expression.charAt(index);
				index++;
			}
		}
		return new IntVar("a");
	}


	//Métodos para pequenas execuções 
	//Retorna se o character é um operador com precedencia
	private static boolean isOperator(String[] vector,String character){
		for(int i = 0; i < vector.length; i++ ){
			if(character.equals(vector[i])){
				return true;
			}
		}
		return false;
	}
	
	//Retorna qual tem a precedencia mais alta
	private static boolean whoPrecede(String[] vector,String operator1,String operator2){
		
		//Retorna 0 caso não haja operadores suficientes para comparação ou ambos são iguais
		if(!operator1.equals(operator2) && !operator1.equals("") && !operator2.equals("")){
				
			//Verifica qual nivel de precedencia cada operador tem
			int indexOperator1 = 0, indexOperator2 = 0;
			for(int i = 0; i < vector.length; i++){
				if(operator1.equals(vector[i])){
					indexOperator1 = i;
				}
				if(operator2.equals(vector[i])){
					indexOperator2 = i;
				}
			}

			//Compara qual tem a precedencia maior
			//Retorna true caso o operator2 tiver maior procedencia
			if(indexOperator1 > indexOperator2){
				return true;
			}
		}
		//Retorna false caso o operator1 tiver maior procedencia
		return false;
	}

	//Verefica se há parenteses na expressõa e se houver verefica se todos estão fechados corretamente
	private static boolean parenthesesAreRight(String expression){
		int parenteses = 0;

		for(int i = 0; i < expression.length() -1; i++){
			if(expression.charAt(i) == '('){
				parenteses++;
			}
			else if(expression.charAt(i) == ')'){
				parenteses--;
			}
		}

		//Retorna true se tudo estiver correto
		if(parenteses == 0){
			return true;
		}
		return false;
	}
	
	public void parse(String expression, HashMap<String,Var> mapOfVariables){
		int old = 0,sizeOfVector = 21;
		String subString = "", variable = "", operator = "", vectorOfPrecedence[] = new String[sizeOfVector];

		//Precedencia de operadores
		//**
		vectorOfPrecedence[0] = "(";//Abre parenteses
		vectorOfPrecedence[1] = ")";//Fecha parenteses
		vectorOfPrecedence[2] = "!";//Not
		vectorOfPrecedence[3] = "*";//Multiplicação
		vectorOfPrecedence[4] = "/";//Divisão
		vectorOfPrecedence[5] = "%";//Modulo
		vectorOfPrecedence[6] = "+";//Adição
		vectorOfPrecedence[7] = "-";//Subtração
		vectorOfPrecedence[8] = "++";//Add variavel em +1
		vectorOfPrecedence[9] = "--";//Subtrai a variavel em -1
		vectorOfPrecedence[10] = "+=";//Adição de ... na variavel
		vectorOfPrecedence[11] = "-=";//Subtração de ... na variavel
		vectorOfPrecedence[12] = "<";//Menor que
		vectorOfPrecedence[13] = "<=";//Menor ou igual que
		vectorOfPrecedence[14] = ">";//Maior que 
		vectorOfPrecedence[15] = ">=";//Maior ou igual que
		vectorOfPrecedence[16] = "==";//Igual a 
		vectorOfPrecedence[17] = "!=";//Diferente de
		vectorOfPrecedence[18] = "&&";//And
		vectorOfPrecedence[19] = "||";//Or
		vectorOfPrecedence[20] = "=";//Recebe o valor
		//**
		
		//Percorre a linha
		expression = expression.replaceAll(" ","");
		for(int i = 0; i < expression.length(); i++){
			subString += expression.substring(i, i+1);

			if(expression.charAt(i) == ';'){
				for(int j = old; j < i; j++){
					variable += expression.substring(j,j+1);

					//Instanciação das variaveis
					if(variable.equals("int") || variable.equals("double") || variable.equals("bool") || variable.equals("str")){
						variableCreation(variable,subString,mapOfVariables);
						subString = "";
						old = i+1;
						variable = "";
						break;
					}
				}
			}
		}
	}
}
