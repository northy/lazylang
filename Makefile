JC = javac
JFLAGS = -d fonte/
.SUFFIXES: .java .class

OPERATORS = fonte/ArithmeticOperator.java \
			fonte/AssignmentOperator.java \
			fonte/CastOperator.java \
			fonte/ComparisonOperator.java \
			fonte/LogicalOperator.java

VARS = fonte/BoolVar.java \
	   fonte/CharVar.java \
	   fonte/FloatVar.java \
	   fonte/IntVar.java \
	   fonte/StrVar.java \
	   fonte/Var.java \
	   fonte/Vector.java

CORE = fonte/Expression.java \
	   fonte/Function.java \
	   fonte/Main.java \
	   fonte/OperatorException.java \
	   fonte/Parser.java

OPERATORSO = fonte/ArithmeticOperator.class \
			fonte/AssignmentOperator.class \
			fonte/CastOperator.class \
			fonte/ComparisonOperator.class \
			fonte/LogicalOperator.class

VARSO = fonte/BoolVar.class \
	   fonte/CharVar.class \
	   fonte/FloatVar.class \
	   fonte/IntVar.class \
	   fonte/StrVar.class \
	   fonte/Var.class \
	   fonte/Vector.class

COREO = fonte/Expression.class \
	   fonte/Expression$1.class \
	   fonte/Function.class \
	   fonte/Main.class \
	   fonte/OperatorException.class \
	   fonte/Parser.class

CLASSES = $(OPERATORSO) $(VARSO) $(COREO)

.java.class:
	$(JC) $(JFLAGS) $(OPERATORS) $(VARS) $(CORE)

default: jar

jar: $(CLASSES)
	echo "Main-Class: Main" > manifest.txt
	echo "Manifest-Version: 1.0" >> manifest.txt
	echo "Class-Path: fonte/" >> manifest.txt
	echo "Specification-Title: Lazylang Interpreter" >> manifest.txt
	echo "Specification-Version: 1.0" >> manifest.txt

	jar cvmf manifest.txt lazylang.jar $(CLASSES)

clean:
	rm fonte/*.class
	rm manifest.txt
