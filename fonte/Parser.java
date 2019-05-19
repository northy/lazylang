import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser{
	//Atributos
	String curString;
	ArrayList<Object> curArray;
	boolean shellMode;
	int depth, lastIfResult;
	static int tempCount=0;

	//Construtor
	public Parser() {
		lastIfResult=-1;
		curString = "";
		depth=0;
		curArray=new ArrayList<Object>();
	}
	public Parser(boolean s) {
		this();
		this.shellMode=s;
	}

	//Métodos
	public void parse(String str, HashMap<String,Var> variables) {
		boolean debugging=true;
		int i;
		String tmp = "";
		ArrayList<Object> tmpArray = new ArrayList<Object>();
		ArrayList<Object> tmpArray2 = new ArrayList<Object>();

		str=str.replaceAll("\\s+(?=((\\[\\\"]|[^\\\"])*\"(\\[\\\"]|[^\\\"])*\")*(\\[\\\"]|[^\\\"])*$)", "");
		str=str.trim();
		for (char c : str.toCharArray()) {
			if (c!=';' && c!='{' && c!='}') {
				curString+=c;
			}
			else {
				if (c==';' && curString.startsWith("for")) {
					curString+=';';
					continue;
				}
				if (curString.startsWith("if")) {
					tmpArray.add("if");
					depth++;
					i=3;
					while (curString.charAt(i) != ')') {
						tmp+=str.charAt(i);
						i++;
					}
					curString=curString.replace(curString.subSequence(0,i+1),"");
					tmpArray.add(tmp);
					if (depth==1) curArray=tmpArray;
					else {
						tmpArray2=curArray;
						for (i=0; i<depth-2; ++i) tmpArray2=((ArrayList<Object>)tmpArray2.get(tmpArray2.size()-1));
						tmpArray2.add(tmpArray);
					}
					tmpArray = new ArrayList<Object>();
					tmpArray2 = new ArrayList<Object>();
				}
				if (curString.startsWith("elif")) {
					tmpArray.add("elif");
					depth++;
					i=5;
					while (curString.charAt(i) != ')') {
						tmp+=str.charAt(i);
						i++;
					}
					curString=curString.replace(curString.subSequence(0,i+1),"");
					tmpArray.add(tmp);
					if (depth==1) curArray=tmpArray;
					else {
						tmpArray2=curArray;
						for (i=0; i<depth-2; ++i) tmpArray2=((ArrayList<Object>)tmpArray2.get(tmpArray2.size()-1));
						tmpArray2.add(tmpArray);
					}
					tmpArray = new ArrayList<Object>();
					tmpArray2 = new ArrayList<Object>();
				}
				if (curString.startsWith("else")) {
					tmpArray.add("else");
					depth++;
					i=4;
					curString=curString.replace(curString.subSequence(0,i),"");
					if (depth==1) curArray=tmpArray;
					else {
						tmpArray2=curArray;
						for (i=0; i<depth-2; ++i) tmpArray2=((ArrayList<Object>)tmpArray2.get(tmpArray2.size()-1));
						tmpArray2.add(tmpArray);
					}
					tmpArray = new ArrayList<Object>();
					tmpArray2 = new ArrayList<Object>();
				}
				else if (curString.startsWith("while")) {
					lastIfResult=-1;
					tmpArray.add("while");
					depth++;
					i=6;
					while (curString.charAt(i) != ')') {
						tmp+=str.charAt(i);
						i++;
					}
					curString=curString.replace(curString.subSequence(0,i+1),"");
					tmpArray.add(tmp);
					if (depth==1) curArray=tmpArray;
					else {
						tmpArray2=curArray;
						for (i=0; i<depth-2; ++i) tmpArray2=((ArrayList<Object>)tmpArray2.get(tmpArray2.size()-1));
						tmpArray2.add(tmpArray);
					}
					tmpArray = new ArrayList<Object>();
					tmpArray2 = new ArrayList<Object>();
				}
				else if (curString.startsWith("for")) {
					lastIfResult=-1;
					tmpArray.add("for");
					depth++;
					i=4;
					while (curString.charAt(i) != ')') {
						if (curString.charAt(i)==';') {
							tmpArray.add(tmp);
							tmp="";
						}
						else {
							tmp+=str.charAt(i);
						}
						i++;
					}
					tmpArray.add(tmp);
					curString=curString.replace(curString.subSequence(0,i+1),"");
					if (depth==1) curArray=tmpArray;
					else {
						tmpArray2=curArray;
						for (i=0; i<depth-2; ++i) tmpArray2=((ArrayList<Object>)tmpArray2.get(tmpArray2.size()-1));
						tmpArray2.add(tmpArray);
					}
					tmpArray = new ArrayList<Object>();
					tmpArray2 = new ArrayList<Object>();
				}
				else if (depth!=0 && c==';') {
					tmpArray=curArray;
					for (i=0; i<depth-1; ++i) tmpArray=((ArrayList<Object>)tmpArray.get(tmpArray.size()-1));
					tmpArray.add(curString);
					curString="";
				}
				else if (depth==1 && c=='}') {
					this.parseBlock(curArray, variables);
					curArray=new ArrayList<Object>();
				}
				else if (curArray.size()==0) {
					lastIfResult=-1;
					Var ret=null;
					try {
						ret = Parser.evaluateStackByPriority(Parser.expressionStack(curString,variables), variables);
					}
					catch (Exception e) {
						System.out.println("ERROR " + Main.curLine + ": " + e.getMessage());
						if (debugging) e.printStackTrace();
						if (!this.shellMode) {
							System.exit(1);
						}
					}
					finally {
						if (this.shellMode && ret instanceof Var) System.out.println(ret.getData()); 
						curString="";
					}
				}
				if (c=='}') depth=Math.max(0,depth-1);
			}
		}
		if(!(curString.equals("")) || curArray.size()!=0) Main.shellPrefix="...";
		else Main.shellPrefix="Prelude>";
	}

	public void parseBlock(ArrayList<Object> o, HashMap<String, Var> variables) throws RuntimeException {
		Var v;
		int tmp;

		if (o.get(0).equals("if")) {
			v = Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(1), variables),variables);
			if (!(((BoolVar)Expression.evaluate(CastOperator.BOOL, v)).getData())) {
				lastIfResult=0;
				return;
			}
			for (int i=2; i<o.size(); ++i) {
				if (o.get(i) instanceof ArrayList<?>) {
					tmp=lastIfResult;
					lastIfResult=-1;
					parseBlock((ArrayList<Object>)o.get(i),variables);
					lastIfResult=tmp;
				}
				else {
					Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(i), variables),variables);
				}
			}
			lastIfResult=1;
		}
		else if (o.get(0).equals("elif")) {
			if (lastIfResult==-1) throw new RuntimeException("Don't use elif block without an if block");
			if (lastIfResult==1) return;
			v = Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(1), variables),variables);
			if (!(((BoolVar)Expression.evaluate(CastOperator.BOOL, v)).getData())) {
				lastIfResult=0;
				return;
			}
			for (int i=2; i<o.size(); ++i) {
				if (o.get(i) instanceof ArrayList<?>) {
					tmp=lastIfResult;
					lastIfResult=-1;
					parseBlock((ArrayList<Object>)o.get(i),variables);
					lastIfResult=tmp;
				}
				else {
					Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(i), variables),variables);
				}
			}
			lastIfResult=1;
		}
		else if (o.get(0).equals("else")) {
			if (lastIfResult==-1) throw new RuntimeException("Don't use else block without an if block");
			if (lastIfResult==1) {lastIfResult=-1; return;}
			for (int i=1; i<o.size(); ++i) {
				if (o.get(i) instanceof ArrayList<?>) {
					parseBlock((ArrayList<Object>)o.get(i),variables);
				}
				else {
					Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(i), variables),variables);
				}
			}
			lastIfResult=-1;
		}
		else if (o.get(0).equals("while")) {
			for (;;) {
				v = Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(1), variables),variables);
				if (!(((BoolVar)Expression.evaluate(CastOperator.BOOL, v)).getData())) break;
				for (int i=2; i<o.size(); ++i) {
					if (o.get(i) instanceof ArrayList<?>) {
						parseBlock((ArrayList<Object>)o.get(i),variables);
					}
					else {
						Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(i), variables),variables);
					}
				}
			}
		}
		else if (o.get(0).equals("for")) {
			if (o.size()<4) throw new RuntimeException("Insuficient arguments for 'for' block");
			Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(1), variables),variables);
			for (;;) {
				v = Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(2), variables),variables);
				if (!(((BoolVar)Expression.evaluate(CastOperator.BOOL, v)).getData())) break;
				for (int i=4; i<o.size(); ++i) {
					if (o.get(i) instanceof ArrayList<?>) {
						parseBlock((ArrayList<Object>)o.get(i),variables);
					}
					else {
						Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(i), variables),variables);
					}
				}
				Parser.evaluateStackByPriority(Parser.expressionStack((String)o.get(3), variables),variables);
			}
		}
		else {
			throw new RuntimeException("Unknown block type: " + o.get(0));
		}
	}

	//Métodos estáticos
	public static int countStringOcurrences(String s, String f) {
		return s.length()-s.replace(f, "").length();
	}

	public static Var toVar(String value, HashMap<String,Var> variables) throws RuntimeException {
		if (variables.get(value) instanceof Var) return variables.get(value);
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
		//boolean
		if (value.equals("true") || value.equals("false"))return new BoolVar(Boolean.parseBoolean(value));
		try {
			//TODO: checar se está em aspas e retornar string
		}
		catch (Exception e) {}

		if (value.contains(".") && !(Character.isDigit(value.charAt(value.indexOf('.')-1)))) {
			//x.y()
			String name="", function="";
			Var object;
			ArrayList<Var> parameters = new ArrayList<Var>();
			int i=0;

			while (value.charAt(i)!='.') {
				name+=value.charAt(i);
				i++;
			}
			i++;
			object=Parser.toVar(name,variables);
			name="";
			while (value.charAt(i)!='(') {
				function+=value.charAt(i);
				i++;
			}
			i++;
			while (i<value.length() && value.charAt(i)!=')') {
				name+=value.charAt(i);
				if (value.charAt(i+1)==',' || (value.charAt(i+1)==')' && Parser.countStringOcurrences(value, "(")==Parser.countStringOcurrences(value, ")"))) {
					parameters.add(Parser.evaluateStackByPriority(Parser.expressionStack(name, variables), variables));
					name="";
					i++;
				}
				i++;
			}

			//vector.x()
			if (object instanceof Vector) {
				if (function.equals("get")) {
					return ((Vector)object).get(((IntVar)parameters.get(0)).getData());
				}
				if (function.equals("append")) {
					((Vector)object).append((Var)parameters.get(0));
				}
				if (function.equals("pop") && parameters.size()==0) {
					return ((Vector)object).pop();
				}
				else if (function.equals("pop")) {
					return ((Vector)object).pop(((IntVar)parameters.get(0)).getData());
				}
				if (function.equals("clear")) {
					((Vector)object).clear();
				}
				if (function.equals("copy")) {
					return ((Vector)object).copy();
				}
				if (function.equals("insert")) {
					((Vector)object).insert(((IntVar)parameters.get(0)).getData(), (Var)parameters.get(1));
				}
				if (function.equals("count")) {
					return ((Vector)object).countOccurrences((Var)parameters.get(0));
				}
			}

			return null;
		}

		if (value.contains("(") && value.contains(")") && Character.isAlphabetic(value.charAt(value.indexOf('(')-1))) {
			//x()
			String name="", function="";
			ArrayList<Var> parameters = new ArrayList<Var>();
			int i=0;

			while (value.charAt(i)!='(') {
				function+=value.charAt(i);
				i++;
			}
			i++;
			while (i<value.length() && value.charAt(i)!=')') {
				name+=value.charAt(i);
				if (value.charAt(i+1)==',' || (value.charAt(i+1)==')' && Parser.countStringOcurrences(value, "(")==Parser.countStringOcurrences(value, ")"))) {
					parameters.add(Parser.evaluateStackByPriority(Parser.expressionStack(name, variables), variables));
					name="";
					i++;
				}
				i++;
			}

			//functions from lazylang
			if (function.equals("print")) {
				for (i=0; i<parameters.size(); ++i) {
					System.out.print(parameters.get(i).getData());
					if (i!=parameters.size()-1) System.out.print(" ");
				}
			}
			if (function.equals("println")) {
				for (i=0; i<parameters.size(); ++i) {
					System.out.print(parameters.get(i).getData());
					if (i!=parameters.size()-1) System.out.print(" ");
				}
				System.out.println();
			}
			if (function.equals("input")) {
				for (i=0; i<parameters.size(); ++i) {
					System.out.print(parameters.get(i).getData());
					if (i!=parameters.size()-1) System.out.print(" ");
				}
				Scanner s = new Scanner(System.in);
				StrVar r = new StrVar();
				if (s.hasNextLine()) r.setData(s.nextLine());
				return r;
			}
			if (function.equals("int")) {
				return Expression.evaluate(CastOperator.INT,parameters.get(0));
			}
			if (function.equals("float")) {
				return Expression.evaluate(CastOperator.FLOAT,parameters.get(0));
			}
			if (function.equals("bool")) {
				return Expression.evaluate(CastOperator.BOOL,parameters.get(0));
			}
			return null;
		}
		
		if (value.contains("[") && value.contains("]") && Character.isAlphabetic(value.charAt(value.indexOf('[')-1))) {
			//vector position
			String name="",index="";
			for (int i=0; i<value.length(); ++i) {
				if (value.charAt(i)!='[') name+=value.charAt(i);
				else {
					int j = i+1;
					while (value.charAt(j)!=']') {
						index+=value.charAt(j);
						++j;
					}
					break;
				}
			}
			return ((Vector)variables.get(name)).get(Integer.parseInt(index));
		}

		throw new OperatorException("Unable to transform \"" + value + "\" to a variable");
	}

	public static ArrayList<Object> expressionStack(String exp, HashMap<String, Var> variables) throws RuntimeException{
		int i=0;
        Object operator;
        exp+=" "; //Evitar NullPointerException
        ArrayList<Object> stack = new ArrayList<Object>();
        String parsing="";
        char c,c1;

        while (i<exp.length()-1) {
            parsing+=exp.charAt(i);
            
            operator=null;
            c=exp.charAt(i);
            c1=exp.charAt(i+1);

            if (c=='(' || c==')' || c=='=' || c=='<' || c=='>' || c=='!' || c=='|' || c=='&' || c=='-' || c=='+' || c=='*' || c=='/' || c=='%' || i==exp.length()-2) {
				if (Parser.countStringOcurrences(parsing, "(")!=Parser.countStringOcurrences(parsing, ")")) {
					//Same number of open and closed parenthesis
					i++;
					continue;
				}
				if (i!=exp.length()-2 && c!='(' && c!=')') parsing=parsing.substring(0, parsing.length()-1);
				if (parsing.contains("(") && (parsing.indexOf('(')-1<0 || Character.isAlphabetic(parsing.charAt(parsing.indexOf('(')-1)))) {
					//Evaluate parenthesis first;
					String tmpName;
					int j=parsing.indexOf('('), oldJ;
					ArrayList<Integer> parenthesisIndexes = new ArrayList<Integer>();
					parenthesisIndexes.add(j);

					while (parenthesisIndexes.size()!=0) {
						j++;
						if (parsing.charAt(j)=='(') {
							parenthesisIndexes.add(j);
							continue;
						}
						if (parsing.charAt(j)==')') {
							oldJ=parenthesisIndexes.remove(parenthesisIndexes.size()-1);
							if (oldJ-1>=0 && Character.isAlphabetic(parsing.charAt(oldJ-1))) continue;
							else {
								tmpName="__tmp"+tempCount++;
								variables.put(tmpName, Parser.evaluateStackByPriority(Parser.expressionStack((String)parsing.subSequence(oldJ+1, j), variables), variables));
								StringBuffer buff = new StringBuffer(parsing).replace(oldJ, j+1,tmpName);
								parsing=buff.toString();
							}
						}
					}
				}
				//criação de variaveis
				if (parsing.startsWith("int") && parsing.charAt(3)!='(') {
					parsing=parsing.replaceFirst("int", "");
					if (!(Character.isLetter(parsing.charAt(0)))) throw new RuntimeException("Variable name can't start with digits");
					variables.put(parsing, new IntVar(parsing));
				}
				else if (parsing.startsWith("float") && parsing.charAt(5)!='(') {
					parsing=parsing.replaceFirst("float", "");
					if (!(Character.isLetter(parsing.charAt(0)))) throw new RuntimeException("Variable name can't start with digits");
					variables.put(parsing, new FloatVar(parsing));
				}
				else if (parsing.startsWith("bool") && parsing.charAt(4)!='(') {
					parsing=parsing.replaceFirst("bool", "");
					if (!(Character.isLetter(parsing.charAt(0)))) throw new RuntimeException("Variable name can't start with digits");
					variables.put(parsing, new BoolVar(parsing));
				}
				else if (parsing.contains("vector")) {
					parsing=parsing.replaceAll("vector", "");
					variables.put(parsing, new Vector(parsing));
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
		
		if (stack.size()==0) throw new RuntimeException("Stack size is 0, maybe there is no expression to evaluate?");
        return stack;
	}
	
	public static Var evaluateStackByPriority(ArrayList<Object> stack, HashMap<String,Var> variables) throws RuntimeException {
		int i=0;
		ArrayList<Integer> lasti = new ArrayList<Integer>();
		boolean rollback=false, debugging=false;

		//for debugging purposes
		if (debugging) {
			System.out.println("Initial stack");
			for (int j=0; j<stack.size(); ++j) {
				if (stack.get(j) instanceof Var) {
					System.out.print("Stack[" + j + "]: ");
					((Var)stack.get(j)).print();
				}
				else if (stack.get(j)!=null) {
					System.out.println("Stack[" + j + "]: " + stack.get(j).getClass().getSimpleName());
				}
			}
			System.out.println();
		}

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
					else if (stack.get(j)!=null) {
						System.out.println("Stack[" + j + "]: " + stack.get(j).getClass().getSimpleName());
					}
				}
				System.out.println();
			}

			//!b, int a
			if (i<stack.size() && stack.get(i) instanceof Enum && i+1<stack.size() && stack.get(i+1) instanceof Var) {
				Var res = Expression.evaluate(stack.get(i),stack.get(i+1));
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
}
