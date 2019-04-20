class Test {
    public static void main(String[] args) {
        Var a = new IntVar("a",0);
        Var b = new FloatVar("b",-0.4f);
        a.print();
        b.print();
        System.out.println(Expression.evaluate(a, LogicalOperator.AND, b));
        System.out.println(Expression.evaluate(a, LogicalOperator.OR, b));
        System.out.println(Expression.evaluate(LogicalOperator.NOT, a));
        System.out.println(Expression.evaluate(LogicalOperator.NOT, b));
    }
}
