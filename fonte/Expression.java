import java.util.HashMap;

public class Expression {
    private String expression;
    HashMap<String, Var> map;
    Parser parse = new Parser();

    //construtores
    public Expression(String e, HashMap<String, Var> m) {
        this.setExpression(e);
        this.setMap(m);
    }

    //getters
    public String getExpression() {
        return this.expression;
    }

    public HashMap<String,Var> getMap() {
        return this.map;
    }

    //setters
    public void setExpression(String e) {
        this.expression=e;
    }

    public void setMap(HashMap<String, Var> map) {
        this.map=map;
    }

    //métodos
    public void evaluateSelf() {
        parse.parse(expression,map);
    }

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
                return new IntVar(term.getName(),((Number)term.getData()).intValue());
            case DOUBLE :
                return new DoubleVar(term.getName(),((Number)term.getData()).doubleValue());
            case FLOAT :
                return new FloatVar(term.getName(),((Number)term.getData()).floatValue());
            case BOOL :
                try {
                    return new BoolVar(term.getName(),DoubleVar.doubleToRoundedInt((double)term.getData()));
                }
                catch (Exception e) {}
                
                try {
                    return new BoolVar(term.getName(),(boolean)term.getData());
                }
                catch (Exception e) {
                    throw new OperatorException("Unable to cast to type",e);
                }
            default :
                throw new OperatorException("Unexpected operator");
        }
    }
}
