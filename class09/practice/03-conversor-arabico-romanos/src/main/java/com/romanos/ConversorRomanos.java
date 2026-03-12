package com.romanos;

import java.util.HashMap;
import java.util.Map;

public class ConversorRomanos {

    private static final int[] VALORES_BASE = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SIMBOLOS_BASE = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String paraRomano(int numeroArabico) {
        if (numeroArabico <= 0 || numeroArabico >= 4000) {
            throw new IllegalArgumentException("Valor invalido.");
        }
        StringBuilder stringRomana = new StringBuilder();
        int saldoRestante = numeroArabico;
        for (int i = 0; i < VALORES_BASE.length; i++) {
            while (saldoRestante >= VALORES_BASE[i]) {
                stringRomana.append(SIMBOLOS_BASE[i]);
                saldoRestante -= VALORES_BASE[i];
            }
        }
        return stringRomana.toString();
    }

    public int paraArabico(String numeroRomano) {
        if (numeroRomano == null || numeroRomano.isEmpty() || !numeroRomano.matches("^[MDCLXVI]+$")) {
            throw new IllegalArgumentException("Numeral romano invalido.");
        }

        Map<Character, Integer> mapaValores = new HashMap<>();
        mapaValores.put('I', 1); mapaValores.put('V', 5); mapaValores.put('X', 10);
        mapaValores.put('L', 50); mapaValores.put('C', 100); mapaValores.put('D', 500); mapaValores.put('M', 1000);

        int total = 0;
        int valorAnterior = 0;

        for (int i = numeroRomano.length() - 1; i >= 0; i--) {
            int valorAtual = mapaValores.get(numeroRomano.charAt(i));
            if (valorAtual < valorAnterior) {
                total -= valorAtual;
            } else {
                total += valorAtual;
            }
            valorAnterior = valorAtual;
        }
        return total;
    }
}