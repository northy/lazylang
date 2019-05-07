import java.util.HashMap;
import java.util.ArrayList;

public class Parse{
	
	//Instanciação de variavel e atribuição de valor à mesma caso seja passado
	private static void variableCreation(String type,String expression,HashMap<String,Var> variables){
		String varName = "",value = "";
		int init = 0,selectionType = 0,control; 
		
		//Verifica qual o tipo primitivo	
		//Type Int
		if(type.equals("int")){
			value = "0";
			init = 3;
			selectionType = 1;
		}
		//Type Double
		else if(type.equals("double")){
			value = "0.0";
			init = 6;
			selectionType = 2;
		}
		//Type Boolean
		else if(type.equals("bool")){
			value = "0";
			init = 4;
			selectionType = 3;
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
			//Descobre se há instanciação de outra variavel na mesma linha
			if(expression.charAt(init) == ',' || expression.charAt(init) == ';' || expression.charAt(init + 1) == ';'){
				
				//Atribuição de valor e instanciação de variavel do tipo INT
				if(selectionType == 1){
					variables.put(varName, new IntVar(varName));
					Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new IntVar(Integer.parseInt(value)));
					value = "0";
				}
				//Atribuição de valor e instanciação de variavel do tipo DOUBLE
				else if(selectionType == 2){
					variables.put(varName, new DoubleVar(varName));
					Expression.evaluate(variables.get(varName), AssignmentOperator.ASSIGN,new DoubleVar(Double.parseDouble(value)));
					value = "0.0";
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
					value = "0";
				}
				varName = "";
			}
			init++;
		}
	}

	//Implementar esse método
	private static void structureCondition(String s,HashMap<String,Var> hash){
		return;
	}

	//Implementar esse método
	private static void structureRepetition(String s,HashMap<String,Var> hash){
		return;
	}

	//Retorna qual tem a precedencia mais alta
	private static int precedence(String operator1,String operator2){
		
		//retorna 0 caso não haja operadores suficientes para comparação ou ambos são iguais
		if(operator1.equals(operator2) || operator1.equals("") || operator2.equals("")){
			return 0;
		}
		//Precedencia de operadores
		String[] prece = new String[18];
		//**
		prece[0] = "(";//Abre parenteses
		prece[1] = ")";//Fecha parenteses
		prece[2] = "*";//Multiplicação
		prece[3] = "/";//Divisão
		prece[4] = "%";//Modulo
		prece[5] = "+";//Adição
		prece[6] = "-";//Subtração
		prece[7] = "+=";//Adição de ... na variavel
		prece[8] = "-=";//Subtração de ... na variavel
		prece[9] = "<";//Menor que
		prece[10] = "<=";//Menor ou igual que
		prece[11] = ">";//Maior que 
		prece[12] = ">=";//Maior ou igual que
		prece[13] = "==";//Igual a 
		prece[14] = "!=";//Diferente de
		prece[15] = "&&";//And
		prece[16] = "||";//Or
		prece[17] = "!";//Not
		//**

		int indexOperator1 = 0, indexOperator2 = 0;
		for(int i = 0; i < 18; i++){
			if(operator1.equals(prece[i])){
				indexOperator1 = i;
			}
			if(operator2.equals(prece[i])){
				indexOperator2 = i;
			}
		}

		//Compara qual tem a precedencia maior
		//Retorna 1 caso o operador2 tiver maior procedencia
		if(indexOperator1 > indexOperator2){
			return 1;
		}
		//Retorna 2 caso o operador 1 tiver maior procedencia
		if(indexOperator1 <= indexOperator2){
			return 2;
		}
		return 3;
	}

	//Verefica se há parenteses na expressõa e se houver verefica se todos estão fechados corretamente
	private static boolean relativesAreRight(String express){
		int parenteses = 0;

		for(int i = 0; i < express.length() -1; i++){
			if(express.charAt(i) == '('){
				parenteses++;
			}else{
				parenteses--;
			}
		}

		//Retorna true se tudo estiver correto
		if(parenteses == 0){
			return true;
		}
		return false;
	}

	//Retorna um valor de quais execuções foram feitas primeirasm  (prioridade)
	private static void parsePriority(String expression,HashMap<String,Var> hash){
		int control = 0;
		
		if(expression.charAt(expression.length() - 1) != ';'){
			expression += ";";
		}
		if(relativesAreRight(expression)){
				String value = "";
				boolean continuar = true;
				control = 0;
				
				//Percore toda a expressão descobrindo qual operação será realizada primeiro
				
		}
	}
	

	public Parse(String s, HashMap<String,Var> h){
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
						
						break;
					}
				}
			}
		}
	}
}
