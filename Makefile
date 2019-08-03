default: compile

compile : $(wildcard fonte/*.java)
	javac $(wildcard fonte/*.java)

jar: $(wildcard fonte/*.class)
	echo "Main-Class: fonte.Main" > manifest.txt
	echo "Manifest-Version: 1.0" >> manifest.txt
	echo "Class-Path: fonte" >> manifest.txt
	echo "Specification-Title: Lazylang Interpreter" >> manifest.txt
	echo "Specification-Version: 1.0" >> manifest.txt

	jar cvmf manifest.txt lazylang.jar $(wildcard fonte/*.class)

clean:
	rm fonte/*.class
	rm manifest.txt
