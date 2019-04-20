public class Expression {
    //métodos estáticos
    public static boolean evaluate(Var term1, ComparisonOperator op, Var term2) {
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
            default : //EQ
                return term1.compareTo(term2)==0 ? true : false;
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
}
