str zero="Zero", outro="Outro", ok="Ok!", erro="Erro";
int i;
int j;
i=0;
j=i;

while (i<3) {
    if (i==0) {
        println(zero);
    }
    else {
        println(outro);
    }
    i+=1;
}

if (i==3) {
    if (j==0) {
        println(ok);
    }
    else {
        println(erro);
    }
}
