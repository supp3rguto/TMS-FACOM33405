package com.katas;

public class FizzBuzz {
     //Aplica as regras matemáticas do jogo FizzBuzz - múltiplos de 3 viram Fizz, de 5 viram Buzz, ambos viram FizzBuzz.
    public String play(int valorInformado) {
        StringBuilder resultadoFinal = new StringBuilder();

        // logica aditiva (elimina a necessidade de checar módulo de 15)
        if (valorInformado % 3 == 0) {
            resultadoFinal.append("Fizz");
        }
        if (valorInformado % 5 == 0) {
            resultadoFinal.append("Buzz");
        }

        // se a string continua vazia, é porque não caiu em nenhuma regra acima
        if (resultadoFinal.length() == 0) {
            return String.valueOf(valorInformado);
        }

        return resultadoFinal.toString();
    }
}