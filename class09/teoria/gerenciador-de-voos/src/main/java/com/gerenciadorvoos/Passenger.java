package com.gerenciadorvoos;

import java.util.Objects;

public class Passenger {
    private String nome;
    private boolean isVip;

    public Passenger(String nome, boolean isVip) {
        this.nome = nome;
        this.isVip = isVip;
    }

    public String getNome() { return nome; }
    public boolean isVip() { return isVip; }

    // Essencial para o TDD da Parte A (Impedir Duplicados)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(nome, passenger.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}