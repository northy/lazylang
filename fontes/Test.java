class Test {
    public static void main(String[] args) {
        Var a = new FloatVar("a",4);
        Var b = new DoubleVar("b",2);
        a.print();
        b.print();
        System.out.println("SOMA");
        Expression.evaluate(a, AssignmentOperator.ADD_ASSIGN, b);
        a.print();
        b.print();
        System.out.println("SUBTRAÇÃO");
        Expression.evaluate(a, AssignmentOperator.SUB_ASSIGN, b);
        a.print();
        b.print();
        System.out.println("MULTIPLICAÇÃO");
        Expression.evaluate(a, AssignmentOperator.MULT_ASSIGN, b);
        a.print();
        b.print();
        System.out.println("DIVISÃO");
        Expression.evaluate(b, AssignmentOperator.DIV_ASSIGN, a);
        a.print();
        b.print();
        System.out.println("MÓDULO");
        Expression.evaluate(a, AssignmentOperator.MOD_ASSIGN, b);
        a.print();
        b.print();
    }
}
