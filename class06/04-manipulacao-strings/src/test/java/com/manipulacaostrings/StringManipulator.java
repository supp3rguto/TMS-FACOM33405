package com.manipulacaostrings;

public class StringManipulator {

    public String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    //conta o número de ocorrências de um caractere.
    public int countOccurrences(String str, char c) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

     //verifica se uma string é um palíndromo e ignora maiúsculas/minúsculas e caracteres não alfanuméricos.
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        // limpa a string (remove caracteres especiais/espaços)
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    public String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }
}