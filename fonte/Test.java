import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("bool a = 1, b, c = false; int d, e = 14; double f = 1, g= 6, h;",a);
        ((Var)a.get("a")).print();
        ((Var)a.get("b")).print();
        ((Var)a.get("c")).print();
        ((Var)a.get("d")).print();
        ((Var)a.get("e")).print();
        ((Var)a.get("f")).print();
        ((Var)a.get("g")).print();
        ((Var)a.get("h")).print();
    }
}