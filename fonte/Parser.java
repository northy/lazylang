import java.util.HashMap;

class Parser{
	public static void 	parse(String s, HashMap<String,Var> h){
		String subs = "",varName = "";
		int old = 0,k;
		//Percorre a linha
		s = s.replaceAll(" ","");
		for(int i = 0; i < s.length(); i++){
			//
			if(s.charAt(i) == ';'){

				//Percorre string s para descobrir se é um tipo primitivo
				for(int j = old; j < i-1; j++){
					subs += s.substring(j,j+1); 
					
					//Types
					//Type Int
					if(subs.equals("int")){
						k=old+j+1;
						varName = "";
						//Criação de variavel e atribuição
						while(k < s.length() && s.charAt(k) != ';'){
							if(s.charAt(k) != ',' &&  s.charAt(k) != '='){
								varName += s.charAt(k);				
							}
							if(s.charAt(k) == ',' || s.charAt(k) == '='){
								h.put(varName, new IntVar(varName));
								varName = "";
								if(s.charAt(k) == '='){
									break;
								}
							}
							k++;
						}
						//Atribuição do valor setado à variavel
						//System.out.print("Valor do k = " + k);
						while(k < s.length() && s.charAt(k) != ';'){	
							return;
							
						}					 	
					}

					//Type Double
					if(subs.equals("double")){
						return;
					}

					//Type String
					if(subs.equals("string")){
						return; 
					}
				}
			}
			//
		}
	}
}
