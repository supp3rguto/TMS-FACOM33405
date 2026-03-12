package com.bingo.impl;

import com.bingo.interfaces.Caller;
import com.bingo.interfaces.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CallerImpl implements Caller {
    private List<Integer> historico = new ArrayList<>();
    private Random random = new Random();

    @Override
    public int sortearNumero() {
        int numero = random.nextInt(75) + 1; // Bingo tradicional vai até 75
        historico.add(numero);
        return numero;
    }

    @Override
    public List<Integer> getHistoricoNumeros() {
        return historico;
    }

    @Override
    public void anunciarVencedores(List<Card> vencedores) {
        System.out.println("Temos " + vencedores.size() + " vencedor(es)!");
    }
}