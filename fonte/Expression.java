public abstract class Expression {
    //métodos estáticos
    public static BoolVar evaluate(Var term1, ComparisonOperator op, Var term2) throws OperatorException {
        boolean res;
        switch (op) {
            case GT :
                res = term1.compareTo(term2)>0 ? true : false;
                break;
            case LT :
                res = term1.compareTo(term2)<0 ? true : false;
                break;
            case GE :
                res = term1.compareTo(term2)>=0 ? true : false;
                break;
            case LE :
                res = term1.compareTo(term2)<=0 ? true : false;
                break;
            case NE :
                res = term1.compareTo(term2)!=0 ? true : false;
                break;
            case EQ :
                res = term1.compareTo(term2)==0 ? true : false;
                break;
            default :
                throw new OperatorException("Unexpected operator error");
        }
        return new BoolVar("__tmp",res);
    }

    public static BoolVar evaluate(Var term1, LogicalOperator op, Var term2) throws OperatorException {
        switch (op) {
            case AND :
                return new BoolVar("__tmp",term1.lAnd(term2));
            case OR :
                return new BoolVar("__tmp",term1.lOr(term2));
            default : //NOT
                throw new OperatorException("NOT operator is not expected in this this evaluation");
        }
    }

    public static BoolVar evaluate(LogicalOperator op, Var term) throws OperatorException {
        switch (op) {
            case NOT :
                return new BoolVar("__tmp",term.lNot());
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

    public static void evaluate(Var term1, AssignmentOperator op, Var term2) throws OperatorException {
        switch (op) {
            case ASSIGN :
                term1.setData(term2.getData());
                break;
            case ADD_ASSIGN :
                term1.setData(term1.add(term2).getData());
                break;
            case SUB_ASSIGN :
                term1.setData(term1.sub(term2).getData());
                break;
            case MULT_ASSIGN :
                term1.setData(term1.mult(term2).getData());
                break;
            case DIV_ASSIGN :
                term1.setData(term1.div(term2).getData());
                break;
            case MOD_ASSIGN :
                term1.setData(term1.mod(term2).getData());
                break;
            default :
                throw new OperatorException("Unexpected operator");
        }
    }

    public static Var evaluate(CastOperator op, Var term) throws OperatorException {
        switch (op) {
            case INT :
                return new IntVar(term.getData());
            case FLOAT :
                return new FloatVar(term.getData());
            case BOOL :
                return new BoolVar(term.getData());
            default :
                throw new OperatorException("Unexpected operator");
        }
    }
}
