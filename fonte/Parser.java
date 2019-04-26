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
						String value = "0";
						int control;

						//Criação de variavel e atribuição
						while(k < s.length() && s.charAt(k) != ';'){
							if(s.charAt(k) != ',' &&  s.charAt(k) != '='){
								varName += s.charAt(k);
							}
							if(s.charAt(k) == '='){
								value = "";
								control = k+1;
								//Percorre o valor e add ele a uma variavel
								for(int l = k+1; s.charAt(control) != ',' && s.charAt(control) != ';'; l++){
									value += s.charAt(l);								
									control++;
								}
								k = control;
							}
							if(s.charAt(k) == ',' || s.charAt(k) == ';'){
								h.put(varName, new IntVar(varName,Integer.parseInt(value)));
								varName = "";
								value = "0";
							}
							k++;
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
