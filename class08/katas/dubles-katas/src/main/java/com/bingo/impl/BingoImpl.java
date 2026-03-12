package com.bingo.impl;

import com.bingo.interfaces.Bingo;
import com.bingo.interfaces.Caller;
import com.bingo.interfaces.Card;

import java.util.ArrayList;
import java.util.List;

public class BingoImpl implements Bingo {
    private Caller caller;
    private List<Card> cartoes;
    private List<Card> vencedores;

    // recebe o caller por injeção para facilitar o mock nos testes
    public BingoImpl(Caller caller, List<Card> cartoes) {
        this.caller = caller;
        this.cartoes = cartoes;
        this.vencedores = new ArrayList<>();
    }

    @Override
    public void inicializarCartoes(int quantidade) {
        // lógica simplificada: em um jogo real criaríamos os cartões aqui
        System.out.println(quantidade + " cartões prontos para o jogo.");
    }

    @Override
    public void jogarRodada() {
        int numeroSorteado = caller.sortearNumero();

        for (Card cartao : cartoes) {
            cartao.marcarNumero(numeroSorteado);
            if (cartao.isVencedor()) {
                vencedores.add(cartao);
            }
        }

        if (!vencedores.isEmpty()) {
            caller.anunciarVencedores(vencedores);
        }
    }

    @Override
    public boolean temVencedor() {
        return !vencedores.isEmpty();
    }
}