package com.senha;

public class ValidadorSenha {

    public boolean validar(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            return false;
        }

        // Regras do PDF
        boolean temTamanhoMinimo = senha.length() >= 8;
        boolean temMaiuscula = senha.matches(".*[A-Z].*");
        boolean temMinuscula = senha.matches(".*[a-z].*");
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temEspecial = senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        // A pegadinha do slide 11: Não permitir espaços
        boolean naoTemEspaco = !senha.contains(" ");

        return temTamanhoMinimo && temMaiuscula && temMinuscula && temNumero && temEspecial && naoTemEspaco;
    }
}