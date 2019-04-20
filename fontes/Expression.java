public class Expression {
    //métodos estáticos
    public static boolean evaluate(Var term1, ComparisonOperator op, Var term2) throws OperatorException {
        switch (op) {
            case GT :
                return term1.compareTo(term2)>0 ? true : false;
            case LT :
                return term1.compareTo(term2)<0 ? true : false;
            case GE :
                return term1.compareTo(term2)>=0 ? true : false;
            case LE :
                return term1.compareTo(term2)<=0 ? true : false;
            case NE :
                return term1.compareTo(term2)!=0 ? true : false;
            case EQ :
                return term1.compareTo(term2)==0 ? true : false;
            default :
                throw new OperatorException("Unexpected operator error");
        }
    }

    public static boolean evaluate(Var term1, LogicalOperator op, Var term2) throws OperatorException {
        switch (op) {
            case AND :
                return term1.lAnd(term2);
            case OR :
                return term1.lOr(term2);
            default : //NOT
                throw new OperatorException("NOT operator is not expected in this this evaluation");
        }
    }

    public static boolean evaluate(LogicalOperator op, Var term) throws OperatorException {
        switch (op) {
            case NOT :
                return term.lNot();
            default : //NOT
                throw new OperatorException("Current operator is not expected in this this evaluation");
        }
    }

    public static Var evaluate(Var term1, ArithmeticOperator op, Var term2) throws OperatorException {
        switch (op) {
            case ADD :
                return term1.add(term2);
            case SUB :
                return term1.sub(term2);
            case MULT :
                return term1.mult(term2);
            case DIV :
                return term1.div(term2);
            case MOD :
                return term1.mod(term2);
            default :
                throw new OperatorException("Unexpected operator");
        }
    }
}
