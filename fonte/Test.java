class Test {
    public static void main(String[] args) {
        //a = 5
        //b = 3
        Var a = new IntVar("a",5);
        Var b = new IntVar("b",3);
        a.print();
        b.print();
        //a+b
        System.out.println();
        System.out.println(Expression.evaluate(a,ArithmeticOperator.ADD,b).getData());
    }
}