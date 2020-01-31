package com.example.marcelopoblete;

public class Calculos extends User_register {


    public static double calcularIMC(double peso, double altura){

//      formula IMC
        double imc = peso/Math.pow(altura, altura);
        return imc;
    }

}
