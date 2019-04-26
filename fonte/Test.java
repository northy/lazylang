import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("int ab = 44,c,d = 51;",a);
        ((Var)a.get("ab")).print();
        ((Var)a.get("c")).print();
        ((Var)a.get("d")).print();
    }
}