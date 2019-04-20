class Test {
    public static void main(String[] args) {
        Var a = new FloatVar("a",4.5f);
        Var b = new DoubleVar("b",2);
        a.print();
        b.print();
        System.out.println(Expression.evaluate(a,ArithmeticOperator.ADD,b).getData());
        System.out.println(Expression.evaluate(a,ArithmeticOperator.SUB,b).getData());
        System.out.println(Expression.evaluate(a,ArithmeticOperator.MULT,b).getData());
        System.out.println(Expression.evaluate(a,ArithmeticOperator.DIV,b).getData());
        System.out.println(Expression.evaluate(a,ArithmeticOperator.MOD,b).getData());
    }
}
