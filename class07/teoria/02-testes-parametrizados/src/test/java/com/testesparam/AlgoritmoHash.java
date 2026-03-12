package com.testesparam;

public enum AlgoritmoHash {
    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256");

    private final String nomeAlgoritmo;

    AlgoritmoHash(String nome) {
        this.nomeAlgoritmo = nome;
    }

    public String getNome() {
        return nomeAlgoritmo;
    }
}
