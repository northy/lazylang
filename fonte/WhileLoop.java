import java.util.ArrayList;
import java.util.HashMap;

class ForLoop implements Runnable {
    protected String midExp;
    protected ArrayList<String> expressions;
    protected HashMap<String, Var> map;
    
    //construtores
    public ForLoop(String mid, ArrayList<String> e, HashMap<String, Var> map) {
        this.setMidExp(mid);
        this.setExpressions(e);
        this.setMap(map);
    }

    //getters
    public ArrayList<String> getExpressions() {
        return this.expressions;
    }

    //setters
    public void setMidExp(String s) {
        this.midExp=s;
    }

    public void setExpressions(ArrayList<String> e) {
        this.expressions=e;
    }

    public void setMap(HashMap<String, Var> map) {
        this.map=map;
    }

    //métodos
    public void run() {
        while ((boolean)Parser.parse(midExp,map).getData()) {
            for (String s:this.getExpressions()) {
                Parser.parse(s,map);
            }
        }
    }
}