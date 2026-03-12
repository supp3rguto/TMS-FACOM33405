package com.katas;

public class RomanNumeralConverter {

    private static final int[] VALORES_DECIMAIS = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    private static final String[] SIMBOLOS_ROMANOS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public String convert(int numeroAlvo) {
        if (numeroAlvo < 1 || numeroAlvo > 3999) {
            throw new IllegalArgumentException("Apenas números entre 1 e 3999 são aceitos.");
        }

        StringBuilder textoRomano = new StringBuilder();
        int valorRestante = numeroAlvo;

        for (int i = 0; i < VALORES_DECIMAIS.length; i++) {
            while (valorRestante >= VALORES_DECIMAIS[i]) {
                textoRomano.append(SIMBOLOS_ROMANOS[i]);
                valorRestante -= VALORES_DECIMAIS[i];
            }
        }

        return textoRomano.toString();
    }
}