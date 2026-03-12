package com.bingo.interfaces;

import java.util.Set;

public interface Card {
    // se uma jogada gerou sucesso ou falha na marcação
    boolean marcarNumero(int numero);

    // estado corrente de suas posições
    Set<Integer> getNumerosMarcados();

    boolean isVencedor();
}