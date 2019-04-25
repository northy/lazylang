import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap a = new HashMap<String,Var>();
        Parser.parse("int aaa,bbb,ccc = 1,2,3;",a);
        ((Var)a.get("aaa")).print();
        ((Var)a.get("bbb")).print();
        ((Var)a.get("ccc")).print();
    }
}