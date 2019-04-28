import java.util.ArrayList;
import java.util.HashMap;

class ForLoop implements Runnable {
    protected Expression midExp;
    protected ArrayList<Expression> expressions;
    
    //construtores
    public ForLoop(Expression mid, ArrayList<Expression> e, HashMap<String, Var> map) {
        this.setMidExp(mid);
        this.setExpressions(e);
    }

    //getters
    public ArrayList<Expression> getExpressions() {
        return this.expressions;
    }

    //setters

    public void setMidExp(Expression s) {
        this.midExp=s;
    }

    public void setExpressions(ArrayList<Expression> e) {
        this.expressions=e;
    }

    //métodos
    public void run() {
        while ((boolean)midExp.evaluateSelf().getData()) {
            for (Expression x:this.getExpressions()) {
                x.evaluateSelf();
            }
        }
    }
}