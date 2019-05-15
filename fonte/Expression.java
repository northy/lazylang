import java.util.HashMap;

public abstract class Expression {
    //métodos estáticos
    public static int compareOperators(Object a, Object b) throws OperatorException {
        HashMap<Object,Integer> vectorOfPrecedence = new HashMap<Object,Integer>();
        vectorOfPrecedence.put(ArithmeticOperator.MOD,1);
        vectorOfPrecedence.put(ArithmeticOperator.DIV,1);
        vectorOfPrecedence.put(ArithmeticOperator.MULT,1);
        vectorOfPrecedence.put(ArithmeticOperator.ADD,2);
        vectorOfPrecedence.put(ArithmeticOperator.SUB,2);
        vectorOfPrecedence.put(ComparisonOperator.EQ,3);
        vectorOfPrecedence.put(ComparisonOperator.LE,3);
        vectorOfPrecedence.put(ComparisonOperator.GE,3);
        vectorOfPrecedence.put(ComparisonOperator.GT,3);
        vectorOfPrecedence.put(ComparisonOperator.LT,3);
        vectorOfPrecedence.put(ComparisonOperator.NE,3);
        vectorOfPrecedence.put(LogicalOperator.NOT,4);
        vectorOfPrecedence.put(LogicalOperator.AND,5);
        vectorOfPrecedence.put(LogicalOperator.OR,6);
        vectorOfPrecedence.put(AssignmentOperator.ASSIGN,7);
        vectorOfPrecedence.put(AssignmentOperator.ADD_ASSIGN,7);
        vectorOfPrecedence.put(AssignmentOperator.SUB_ASSIGN,7);
        vectorOfPrecedence.put(AssignmentOperator.MULT_ASSIGN,7);
        vectorOfPrecedence.put(AssignmentOperator.DIV_ASSIGN,7);
        vectorOfPrecedence.put(AssignmentOperator.MOD_ASSIGN,7);
        if (!(a instanceof Enum) || !(b instanceof Enum)) {
            throw new OperatorException("Unknown type: " + a.getClass().getSimpleName() + " or " + b.getClass().getSimpleName());
        }
        return vectorOfPrecedence.get(a)<vectorOfPrecedence.get(b) ? 1 : -1;
    }

    public static Var evaluate(Object term1, Object op, Object term2) throws RuntimeException {
        try {
            return Expression.evaluate((Var)term1, (ArithmeticOperator)op, (Var)term2);
        }
        catch (Exception e) {}
        try {
            return Expression.evaluate((Var)term1, (LogicalOperator)op, (Var)term2);
        }
        catch (Exception e) {}
        try {
            return Expression.evaluate((Var)term1, (AssignmentOperator)op, (Var)term2);
        }
        catch (Exception e) {}
        try {
            return Expression.evaluate((Var)term1, (ComparisonOperator)op, (Var)term2);
        }
        catch (Exception e) {}
        
        throw new RuntimeException("Failed expression evaluation (term1: " + term1.getClass().getSimpleName() + " | op: " + op.getClass().getSimpleName() +" | term2: " + term2.getClass().getSimpleName() + ")");
    }

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

    public static Var evaluate(Var term1, AssignmentOperator op, Var term2) throws OperatorException {
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

        return null;
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
