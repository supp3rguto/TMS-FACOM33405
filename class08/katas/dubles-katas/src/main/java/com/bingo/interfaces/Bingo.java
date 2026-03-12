package com.bingo.interfaces;

public interface Bingo {
    void inicializarCartoes(int quantidade);

    void jogarRodada();

    boolean temVencedor();
}