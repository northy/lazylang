import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("bool c = 1,d,e = false;",a);
        ((Var)a.get("e")).print();
        ((Var)a.get("c")).print();
        ((Var)a.get("d")).print();
    }
}