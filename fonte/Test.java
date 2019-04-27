import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("double c,d = 1, e = 3;",a);
        ((Var)a.get("e")).print();
        ((Var)a.get("c")).print();
        ((Var)a.get("d")).print();
    }
}