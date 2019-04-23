import java.util.HashMap;

class Parser{
	protected String typ = "",data;
	protected int i = 0,result;
	IntVar variavel;
	
	//Contructor
	public Parser(String phrase){
		//Percorre a linha
		phrase = phrase.replaceAll(" ","");
		for(i = 0; i < phrase.length(); i++){
			typ = typ + phrase.substring(i,i+1);

			//Type Int
			if(typ.equals("int")){
				typ = "";
				for(i = i+1; i < phrase.length() - 1; i++){
					data = "";

					//Atribuição na instanciação //MUDAR
					if(phrase.substring(i,i+1).equals("=")){
						data = phrase.substring(i+1,phrase.length());
						if(data != ""){
							result = Integer.parseInt(data);
						}else{
							result = 0;
						}
						variavel = new IntVar(typ,result);	
					}
					//Término do comando //MUDAR
					else if(phrase.substring(i,i+1).equals(";")){
						result = Integer.parseInt(data);
						variavel = new IntVar(typ,result);
					}
					//Continua a adicionar character na variavel	
					else{
						typ += phrase.substring(i,i+1);
					}
				}
			}

			//Type Double
			if(typ.equals("double")){
				return;
			}

			//Type String
			if(typ.equals("string")){
				return; 
			}

			//Type ...
		}
	}

	public static void Parse(String type, String name){
		HashMap <String,String> variavel = new  HashMap<String,String>();
		variavel.put(type,name);
		return;
	}
}