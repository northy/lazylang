import java.util.ArrayList;
import java.util.HashMap;

class ForLoop implements Runnable {
    protected String preExp,midExp,postExp;
    protected ArrayList<String> expressions;
    protected HashMap<String, Var> map;
    
    //construtores
    public ForLoop(String pre, String mid, String post, ArrayList<String> e, HashMap<String, Var> map) {
        this.setPreExp(pre);
        this.setMidExp(mid);
        this.setPostExp(post);
        this.setExpressions(e);
        this.setMap(map);
    }

    //getters
    public ArrayList<String> getExpressions() {
        return this.expressions;
    }

    //setters
    public void setPreExp(String s) {
        this.preExp=s;
    }

    public void setMidExp(String s) {
        this.midExp=s;
    }

    public void setPostExp(String s) {
        this.postExp=s;
    }

    public void setExpressions(ArrayList<String> e) {
        this.expressions=e;
    }

    public void setMap(HashMap<String, Var> map) {
        this.map=map;
    }

    //métodos
    public void run() {
        Parser.parse(preExp,map);
        while ((boolean)Parser.parse(midExp,map).getData()) {
            for (String s:this.getExpressions()) {
                Parser.parse(s,map);
            }
            Parser.parse(postExp,map);
        }
    }
}