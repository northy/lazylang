
# **Lazylang**

Bem vindo a documentação de Lazylang, um interpretador construído em Java para a criação de uma nova linguagem. Aqui você aprenderá como usar as funcionalidades de Lazylang.

##  Sintaxe

A sintaxe de Lazylang foi herdada do Java, com blocos de código iniciando  com  "{" e terminando com "}", também as todas as funções devem estar entre parênteses, todo comando que não é função deve acabar com ";" , strings devem estar entre " ", e chars entre ' '.

## Tipos de dados

A linguagem conta com 6 tipos de dados, sendo eles: inteiro(int), números com ponto flutuante(float), booleano(bool), vetor(vector), string(str) e caractere(char), que devem ser usados na declaração de variáveis.

### Inteiros
São os números positivos e negativos sem vírgula, e devem ser declarados da seguinte forma:

    int variavel;
também podem receber um valor já na declaração, por exemplo:

    int variavel = 5;

### Float
São os números positivos e negativos que contém vírgula, e podem ser declarados da seguinte forma:

    float variavel;
também podem receber um valor já na declaração, por exemplo:

    float variavel = 5.0;

### Booleanos

São os valores: verdadeiro(true) e falso(false), e podem ser declarados da seguinte forma:

     bool variavel;
também podem receber um valor já na declaração, por exemplo:

    bool variavel = true;

### Vetor
O vetor é do tipo lista, não sendo necessário informar o tipo de dado na criação, ou seja, ele aceita diferentes tipos de dado no mesmo vetor, ele é declarado da seguinte forma:

     vector v;

#### Funções do vetor
O vetor conta com diversas funções para sua utilização.

#### Append
Adiciona elementos no final do vetor, o parâmetro entre parênteses é o elemento a ser adicionado.

    v.append(5);

#### Copy
Retorna uma cópia do vetor:

     v.copy();
#### Get

Retorna o que há na posição do vetor informada no parametro, seja v um vetor de 3 posições, contendo 1, 3 e 8 nessa ordem.

    v.get(0);

    Saída
    1

#### Pop
Remove o elemento da posição passada como parâmetro na função:

    v.pop(0);

Se não for passado nada, ele removerá a última:

    v.pop();

#### Clear
Remove todos os elementos do vetor:

     v.clear();

#### Insert
Adiciona um elemento em uma posição específica, o elemento a ser inserido é o primeiro parâmetro, e a posição é o segundo:

     v.insert(2,"abc");

#### Count

Percorre o vetor contando as ocorrências de um regex passado como parãmetro:

     v.count(2);

### Caractere
São variáveis formadas de um único caractere,e ele deve estar entre '' , pode ser declarado da seguinte forma:

     char variavel;
também podem receber um valor já na declaração, por exemplo:

    char variavel = 'c';
### String

São sequências de caracteres, que devem estar entre "", e podem ser declarados da seguinte forma:

     str variavel;
também podem receber um valor já na declaração, por exemplo:

    str variavel = "string";

## Funcionalidades

Apesar de ainda ser uma linguagem básica, Lazylang possui tudo o que é necessário ao programador: estruturas de controle de fluxo(condições), estruturas de repetição(laços), e funções de entrada e saída.

### Estruturas de controle de fluxo
As estruturas de controle de fluxo de Lazylang são basicamente iguais as do Java, sendo elas:
#### If
A função recebe uma condição como parâmetro, e se a condição passada ao if for verdadeira, o bloco de código será executado, do contrário, o bloco inteiro será ignorado.A função if possui o seguinte formato:

    if(condição) {
       comando;
       comando:
    }

#### Elif
Com funcionamento semelhante ao do if, o bloco elif precisa de uma condição, e se ela for verdadeira, o bloco será executado, entretanto, um elif só pode ser usado após um if, e será executado somente se o if anterior for falso. A função elif possui o seguinte formato:

    elif(condição) {
        comando;
        comando;
    }

#### Else

Assim como o elif, o else precisa ter um if anterior a ele, e ele precisa ser falso, entretanto diferentemente do if e do elif, o else não precisa de condição, ou seja, qualquer condição que não se encaixar em algum if ou elif anterior cairá no else. A função else possui o seguinte formato:

    else {
        comando;
        comando;
    }

### Estrututras de repetição
Lazylang possui duas estruturas de repetição(laço), sendo elas o for e o while.

#### For
O laço for possui a seguinte forma:

     for(int i = 0;i <= 10;i += 1){
         comando;
         comando;
     }
Dentro dos parênteses temos as condições do laço, a primeira coisa é o ponto de início, no exemplo definida por:

    int i = 0;

A segunda coisa é o ponto de parada, que pode ser enquanto a variável de controle for menor do que o ponto de parada(i<10), ou enquanto ela não for igual ao ponto de parada(i<=10), que no exemplo foi definida por:

     i < 10;
A terceira e última coisa do for é o incremento da variável, que pode ser de qualquer quantidade, no exemplo era incrementada de 1 em 1:

    i += 1

A variável de controle do for deve ser um inteiro e deve ser declarada como qualquer variável.

#### While

O laço while funciona diferente do laço for, pois ele funciona apenas com teste lógico em seu parâmetro, e possui o seguinte formato:

     int a = 0;
     while(a < 10){
         a += 1;
     }
O laço se repetirá enquanto o teste lógico for verdadeiro, no exemplo, quando o a for 10, o laço se encerrará.

### Entrada e Saída

LayLang possui alguns comandos de entrada e saída. O que facilita alguns processos.

#### Saída

Para saída, temos o print e o println.

##### *Print*
Escreve na tela o que for passado como parâmetro, seja o parametro int, float, bool, string ou char. Entretanto strings devem estar entre " " e char entre ' '. O print não quebra automáticamente a linha, e possui o seguinte formato:

    print(variável);

ou

    print("Mensagem");

##### *Println*

Escreve na tela como o print, a diferença entre eles é que o println quebra linha ao final do comando, possui o seguinte formato:

    println(variable);

ou

    println("Mensagem");

##### *Println*

Escreve na tela como o print, a diferença entre eles é que o println quebra linha ao final do comando, possui o seguinte formato:

    println(variable);

ou

    println("Mensagem");

#### Entrada

Para ler do teclado temos o input, o readInt, o readFloat e o readBool.

##### *input*
É usada para ler strings do teclado, a função pode ou não conter mensagem e possui o seguinte formato:

     variavel = input();

##### *readInt*

É usada para ler números inteiros do teclado, a função pode ou não conter mensagem e possui o seguinte formato:

     variavel = readInt();

##### *readFloat*

É usada para ler numeros de ponto flutuante do teclado, a função pode ou não conter mensagem e possui o seguinte formato:

     variavel = readFloat();

##### *readBool*
É usada para ler booleanos do teclado, a função pode ou não conter mensagem e possui o seguinte formato:

     variavel = readBool();
