import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("int cuzao;",a);
        ((Var)a.get("cuzao")).print();
    }
}