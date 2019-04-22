class Test {
    public static void main(String[] args) {
        Var a = new IntVar("a",1);
        Var b = new DoubleVar("b",-2.7);
        a.print();
        b.print();
        //a = (int)b;
        System.out.println();
        Expression.evaluate(a, AssignmentOperator.ASSIGN, Expression.evaluate(CastOperator.INT, b));
        a.print();
        b.print();
    }
}
