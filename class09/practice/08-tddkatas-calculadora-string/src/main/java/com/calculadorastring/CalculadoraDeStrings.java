package com.calculadorastring;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeStrings {

    public int somar(String expressao) {
        if (expressao == null || expressao.trim().isEmpty()) {
            return 0;
        }

        String delimitador = ",|\n";
        String numerosParaSomar = expressao;

        if (expressao.startsWith("//")) {
            int indiceQuebra = expressao.indexOf("\n");
            delimitador = expressao.substring(2, indiceQuebra);
            numerosParaSomar = expressao.substring(indiceQuebra + 1);
        }

        String[] fatias = numerosParaSomar.split(delimitador);
        int total = 0;
        List<Integer> invalidosNegativos = new ArrayList<>();

        for (String fatia : fatias) {
            int valorConvertido = Integer.parseInt(fatia.trim());
            if (valorConvertido < 0) {
                invalidosNegativos.add(valorConvertido);
            }
            if (valorConvertido <= 1000) {
                total += valorConvertido;
            }
        }

        if (!invalidosNegativos.isEmpty()) {
            throw new IllegalArgumentException("Numeros negativos nao sao permitidos: " + invalidosNegativos);
        }

        return total;
    }
}