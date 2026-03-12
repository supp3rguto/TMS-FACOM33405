package com.bingo.interfaces;

import java.util.List;

public interface Caller {
    // Gera os números em cada rodada
    int sortearNumero();

    // Mantém o histórico dos números já chamados
    List<Integer> getHistoricoNumeros();

    // Anuncia os cartões vencedores
    void anunciarVencedores(List<Card> vencedores);
}