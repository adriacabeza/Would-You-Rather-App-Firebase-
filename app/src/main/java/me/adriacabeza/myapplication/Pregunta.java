package me.adriacabeza.myapplication;



/**
 * Created by usuario on 30/01/2018.
 */

public class Pregunta {
    public String opcion1;
    public int int1;
    public String opcion2;
    public int int2;

public Pregunta(){};

public Pregunta(String opcion1, String opcion2,int int1, int int2){
    this.opcion1= opcion1;
    this.opcion2 = opcion2;
    this.int1= int1;
    this.int2= int2;
}

    public String getOpcion1(){
        return opcion1;
    }

    public int getInt1(){
        return int1;
    }
    public String getOpcion2(){
        return opcion2;
    }

    public int getInt2(){
        return int2;
    }

    public void setOpcion1(String opcion1){
        this.opcion1 = opcion1;
    }

    public void setint1(int opcion1){
        this.int1 = opcion1;
    }


    public void setint2(int opcion1){
        this.int2 = opcion1;
    }

    public void setOpcion2(String opcion2){
        this.opcion2 = opcion2;
    }
}

