public class Expression {
    //métodos estáticos
    public static boolean evaluate(Var term1, Operator op, Var term2) {
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
}
