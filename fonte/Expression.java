//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

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

    public static Var evaluate(Object op, Object term) throws RuntimeException {
        try {
            return Expression.evaluate((ArithmeticOperator)op, (Var)term);
        }
        catch (Exception e) {}
        try {
            return Expression.evaluate((LogicalOperator)op, (Var)term);
        }
        catch (Exception e) {}
        try {
            return Expression.evaluate((CastOperator)op, (Var)term);
        }
        catch (Exception e) {}
        
        throw new RuntimeException("Failed expression evaluation (op: " + op.getClass().getSimpleName() +" | term: " + term.getClass().getSimpleName() + ")");
    }

    public static BoolVar evaluate(Var term1, ComparisonOperator op, Var term2) throws OperatorException {
        boolean res;
        if (op==ComparisonOperator.GT) {
            res = term1.compareTo(term2)>0 ? true : false;
        }
        else if (op==ComparisonOperator.LT) {
            res = term1.compareTo(term2)<0 ? true : false;
        }
        else if (op==ComparisonOperator.GE) {
            res = term1.compareTo(term2)>=0 ? true : false;
        }
        else if (op==ComparisonOperator.LE) {
            res = term1.compareTo(term2)<=0 ? true : false;
        }
        else if (op==ComparisonOperator.NE) {
            res = term1.compareTo(term2)!=0 ? true : false;
        }
        else if (op==ComparisonOperator.EQ) {
            res = term1.compareTo(term2)==0 ? true : false;
        }
        else {
            throw new OperatorException("Unexpected operator error");
        }
        return new BoolVar("__tmp",res);
    }

    public static BoolVar evaluate(Var term1, LogicalOperator op, Var term2) throws OperatorException {
        if (op==LogicalOperator.AND) {
            return new BoolVar("__tmp",term1.lAnd(term2));
        }
        else if (op==LogicalOperator.OR) {
            return new BoolVar("__tmp",term1.lOr(term2));
        }
        else { //NOT
            throw new OperatorException("NOT operator is not expected in this this evaluation");
        }
    }

    public static BoolVar evaluate(LogicalOperator op, Var term) throws OperatorException {
        if (op==LogicalOperator.NOT) {
            return new BoolVar("__tmp",term.lNot());
        }
        else { //NOT
            throw new OperatorException("Current operator is not expected in this this evaluation");
        }
    }

    public static Var evaluate(ArithmeticOperator op, Var term) throws OperatorException {
        if (op==ArithmeticOperator.SUB) {
            return term.mult(new IntVar(-1));
        }
        else {
            throw new OperatorException("Current operator is not expected in this this evaluation");
        }
    }

    public static Var evaluate(Var term1, ArithmeticOperator op, Var term2) throws OperatorException {
        if (op==ArithmeticOperator.ADD) {
            return term1.add(term2);
        }
        else if (op==ArithmeticOperator.SUB) {
            return term1.sub(term2);
        }
        else if (op==ArithmeticOperator.MULT) {
            return term1.mult(term2);
        }
        else if (op==ArithmeticOperator.DIV) {
            return term1.div(term2);
        }
        else if (op==ArithmeticOperator.MOD) {
            return term1.mod(term2);
        }
        else {
            throw new OperatorException("Unexpected operator");
        }
    }

    public static Var evaluate(Var term1, AssignmentOperator op, Var term2) throws OperatorException {
        if (op==AssignmentOperator.ASSIGN) {
            term1.setData(term2.getData());
        }
        else if (op==AssignmentOperator.ADD_ASSIGN) {
            term1.setData(term1.add(term2).getData());
        }
        else if (op==AssignmentOperator.SUB_ASSIGN) {
            term1.setData(term1.sub(term2).getData());
        }
        else if (op==AssignmentOperator.MULT_ASSIGN) {
            term1.setData(term1.mult(term2).getData());
        }
        else if (op==AssignmentOperator.DIV_ASSIGN) {
            term1.setData(term1.div(term2).getData());
        }
        else if (op==AssignmentOperator.MOD_ASSIGN) {
            term1.setData(term1.mod(term2).getData());
        }
        else {
            throw new OperatorException("Unexpected operator");
        }

        return term1;
    }

    public static Var evaluate(CastOperator op, Var term) throws OperatorException {
        if (op==CastOperator.INT) {
            return new IntVar(term.getData());
        }
        else if (op==CastOperator.FLOAT) {
            return new FloatVar(term.getData());
        }
        else if (op==CastOperator.BOOL) {
            return new BoolVar(term.getData());
        }
        else {
            throw new OperatorException("Unexpected operator");
        }
    }
}
