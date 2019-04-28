import java.util.ArrayList;
import java.util.HashMap;

class ForLoop implements Runnable {
    protected Expression preExp,midExp,postExp;
    protected ArrayList<Expression> expressions;
    
    //construtores
    public ForLoop(Expression pre, Expression mid, Expression post, ArrayList<Expression> e, HashMap<String, Var> map) {
        this.setPreExp(pre);
        this.setMidExp(mid);
        this.setPostExp(post);
        this.setExpressions(e);
    }

    //getters
    public ArrayList<Expression> getExpressions() {
        return this.expressions;
    }

    //setters
    public void setPreExp(Expression s) {
        this.preExp=s;
    }

    public void setMidExp(Expression s) {
        this.midExp=s;
    }

    public void setPostExp(Expression s) {
        this.postExp=s;
    }

    public void setExpressions(ArrayList<Expression> e) {
        this.expressions=e;
    }

    //métodos
    public void run() {
        preExp.evaluateSelf();
        while ((boolean)midExp.evaluateSelf().getData()) {
            for (Expression x:this.getExpressions()) {
                x.evaluateSelf();
            }
            postExp.evaluateSelf();
        }
    }
}