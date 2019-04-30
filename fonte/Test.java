import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        HashMap<String,Var> a = new HashMap<String,Var>();
        Parser.parse("a = 5 + 1 * 2;",a);
       /*
       	for (String i : a.keySet()){
  			((Var)a.get(i)).print();
		}
*/





       // Expression e = new Expression("int a = 2;",a);
        //e.evaluateSelf();
        //e.getMap().get("a").print();
    }
}