package com.fizzbuzz;

public class GeradorFizzBuzz {

    public String processarNumero(int numero) {
        StringBuilder resposta = new StringBuilder();

        if (numero % 3 == 0) resposta.append("Fizz");
        if (numero % 5 == 0) resposta.append("Buzz");

        if (resposta.length() == 0) {
            return String.valueOf(numero);
        }

        return resposta.toString();
    }
}