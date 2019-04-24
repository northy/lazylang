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
				//Percorre subs
				for(int j = old; j < i-1; j++){
					subs += s.substring(j,j+1); 
					
					//Types
					//Type Int
					if(subs.equals("int")){
						k=old+j+2;
						varName = "";
						while(k < s.length() && (s.charAt(k)!=';' || s.charAt(k)!='=' || s.charAt(k)!=',')){
							varName += s.charAt(k-1);
							k++;
						}
					 	h.put(varName,new IntVar(varName));
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
		}
	}
}
