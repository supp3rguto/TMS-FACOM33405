package com.bingo.impl;

import com.bingo.interfaces.Card;
import java.util.HashSet;
import java.util.Set;

public class CardImpl implements Card {
    private Set<Integer> numerosDoCartao;
    private Set<Integer> numerosMarcados;

    public CardImpl(Set<Integer> numerosDoCartao) {
        this.numerosDoCartao = numerosDoCartao;
        this.numerosMarcados = new HashSet<>();
    }

    @Override
    public boolean marcarNumero(int numero) {
        if (numerosDoCartao.contains(numero)) {
            numerosMarcados.add(numero);
            return true; // sucesso na marcação
        }
        return false; // falha na marcação
    }

    @Override
    public Set<Integer> getNumerosMarcados() {
        return numerosMarcados;
    }

    @Override
    public boolean isVencedor() {
        return numerosMarcados.size() == numerosDoCartao.size();
    }
}