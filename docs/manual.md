<img src="../assets/lazylang.png" alt="drawing" width="200"/>

[Português👈](manual-pt_BR.md)

# **Lazylang**

Welcome to Lazylang's documentation, an interpreter built in java, aiming to build a new language. Here you'll find out hout to use Lazylang.

##  Syntax

Lazylang's syntax was herded from Java, with blocks of code starting with "{" and ending with "}", also all functions must have their parameters inside parenthesis. Every command that doesn't start a new block of code must end with ";", strings must be between " " and chars between ' '.

## Data types

The language has 6 data types and one pseudo data type, those being: integer(int), floating point number(float), boolean(bool), vector(vector), string(str), character(char) and automatic (auto), and they are used in variable declarations.

### Automatic
This data type definition will try to deduce which type the user wants to use.

    auto variable;

### Integers
They are the positive and negative numbers without floating point, and they must be declared using:

    int variable;
they can also receive an value at declaration, e.g.:

    int variable = 5;

### Floats
They are the positive and negative numbers with floating point, and they must be declared using:

    float variable;
they can also receive an value at declaration, e.g.:

    float variable = 5.0;

### Booleans
They are the boolean values true and false, and they must be declared using:

    bool variable;
they can also receive an value at declaration, e.g.:

    bool variable = true;

### Vetor
A vector is a list type, and there's no need to specify the container type at creation time, i.e., it accepts different data types in the same vector, and they must be declared using:

    vector v;

#### Vector Functions
Vectors have the built in functions:

#### Append
Appends elements to the end of the vector. The parameter between parenthesis is the element being added.

    v.append(5);

#### Copy
Retrieves a copy of the vector.

    v.copy();

#### Get
Returns what's at the specified position. Can be replaced using the brackets [] operator.

Take a vector with 2 positions, [1,3]:

    v.get(0);

    Output
    1

    v[1];

    Output
    3

#### Pop
Removes the element at the given position:

    v.pop(0);

If nothing's passed, it removes the last element:

    v.pop();

#### Clear
Clears the vector's elements:

     v.clear();

#### Insert
Adds an element to a specific position, the position is the first parameter, and the element to be inserted is the second parameter:

     v.insert(2,"abc");

#### Count
Searches the vector counting the occurences of a element given as the parameter:

     v.count(2);

### Character
Special variables of a single character, and it must be between ' '. They must be declared using:

     char variable;
they can also receive an value at declaration, e.g.:

    char variable = 'c';

### String
They are sequences of characters, that must be between " ". They must be declared using:

     str variavel;
they can also receive an value at declaration, e.g.:

    str variable = "string";

## Functionalities
Despite being a basic language, Lazylang has everything that's necessary to the programmer: conditional blocks, loops, and input/output.

### Operators

Lazylang has logical, arithmetic, comparison and cast operators.

#### Arithmetic Operators

|Operator|Function|
|----| :----|
| % |modulo|
| / |division|
| * |multiplication|
| + |addition|
| - |subtraction|

#### Logical Operators

|Operator|Function|
|----| :----|
| ! | Not | 
| && | And |
| \|\|| Or |

#### Comparison Operators

|Operator|Function|
|----| :----|
| == | equal |
| <= | lesser/equal|
| >= | greater/equal
| < | lesser
| > | greater
| != | not equal

#### Cast Operators
Forces a data type to become another data type. casts exists for int, float and bool.

##### Int

     int(variavel);

##### Float

     float(variavel);

##### Bool

    bool(variavel);

#### Operator precedence
Operators have the following precedence order, 1 being the highest and 7 the lowest.

|Operator|Precedence|
|----| :----|
| % |1|
| / |1|
| * |1|
| + |2|
| - |2|
| == |3
| <= |3
| >= |3
| < |3
| > |3
| != |3
| ! |4
| && |5
| \|\||6|
| = |7|
| += |7|
| -= |7|
| *= |7|
| /= |7|
| %= |7|


### Conditional structures
Conditional structures on lazylang are basically the same as in java, being:

#### If
The function receives a condition as the parameter, and if the condition is true, the block of code will be executed, else, the block will be ignored. The function has the following syntax:

    if(condition) {
       command;
       command;
    }

#### Elif
Working similarly to if, the elif block takes a condition, and if it's true, the whole block will be executed. The elif block can only be used after an "if", and will only be executed if the preceding conditional was false. The function has the following syntax:

    elif(condition) {
        command;
        command;
    }

#### Else
Just like elif, the else block needs to have a preceding conditional, and it needs to be false, but else doesn't need a condition, i.e., it executes the block if the preceding condition was false. The function has the following syntax:

    else {
        command;
        command;
    }

### Loop structures
Lazylang has two looping structures, for and while.

#### For
The loop has the following syntax:

    for(int i = 0;i <= 10;i += 1){
        comando;
        comando;
    }

Inside the parenthesis we have the loop conditions, the first being the beggining assignment, defined as:

    int i = 0;

The second one is the end condition,

    i <= 10;

The third one is the loop step assignment:

    i += 1

#### While

While loops work only with a conditional on its argument.

The loop has the following syntax:

    int a = 0;
    while(a < 10){
        a += 1;
    }

The loop will repeat until the condition tests false.

### Input/Output

Lazylang has input and output commands:

#### Output
For output, lazylang has print and println.

##### *Print*
Outputs to stdout the elements passed as parameters. Print doesn't automaticly line break. Separators are spaces.

The syntax is as follows:

    print(variable);

or

    print("message1","message 2");

##### *Println*
Outputs to stdout just like print, but println does a line break at the end.

The syntax is as follows:

    println(variable);

or

    println("message1","message 2");

#### Input
For input, we have input, readInt, readFloat and readBool.

##### *input*
Used to input strings from stdin. Optional parameter is a message to be printed:

    variable = input();

##### *readInt*

Used to input integers from stdin. Optional parameter is a message to be printed:

    variable = readInt();

##### *readFloat*

Used to input floating point numbers from stdin. Optional parameter is a message to be printed:

    variable = readFloat();

##### *readBool*
Used to input booleans from stdin. Optional parameter is a message to be printed:

    variable = readBool();

