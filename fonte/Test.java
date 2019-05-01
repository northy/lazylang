//import java.util.HashMap;

class Test {
    public static void main(String[] args) {
        //HashMap<String,Var> a = new HashMap<String,Var>();
        //Parser.parse("a = 5 + 1 * 2;",a);
        
        Vector v = new Vector("a");
        for (int i=0; i<10; ++i) {
            v.append(new IntVar(i));
        }
        v.append(new BoolVar(false));
        v.append(new DoubleVar(3.14));
        Vector nv = v.copy();
        v.remove(2);
        v.remove(new IntVar(2));
        System.out.println(v.pop().getData());
        System.out.println(v.get(v.size().getData()-1).getData());
        v.insert(1,new FloatVar(1.618f));
        nv.print();
        v.print();
        nv.clear();
    }
}