import java.util.HashMap;

//Classe EXCLUSIVA para TESTES
class Test {
    public static void main(String[] args) {
        HashMap<String,Var> a = new HashMap<String,Var>();
        Parser teste = new Parser();
        teste.parse("",a);
        ((Var)a.get("")).print();
    }
}
