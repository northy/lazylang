class Test {
    public static void main(String[] args) {
        Var a = new IntVar("a",2);
        Var b = new IntVar("b",4);
        a.print();
        b.print();
        System.out.println(Expression.evaluate(a, ComparisonOperator.NE, b));
    }
}
