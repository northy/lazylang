import java.util.HashMap;
class Vector{
	public static void main(String args[]){
		
		/*
		*varName = 1;
		*varName = a;
		*varName += 1     -=     /=     *=;
		*    ||  += a     -=     /=     *=;
		*varName++     --;
		*
		*/
		IntVar a = new IntVar("a",2);
		IntVar b = new IntVar("b",3);
		Expression.evaluate(a,AssignmentOperator.ADD_ASSIGN,b);
		a.getData().print();
	}
}