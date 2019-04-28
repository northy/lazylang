import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap<String,Var> a = new HashMap<String,Var>();
        Parser.parse("bool b, c = false; int d, e = 14; double f = 1, g= 6, h;",a);
        Expression e = new Expression("int a = 2;",a);
        e.evaluateSelf();
        e.getMap().get("a").print();
    }
}