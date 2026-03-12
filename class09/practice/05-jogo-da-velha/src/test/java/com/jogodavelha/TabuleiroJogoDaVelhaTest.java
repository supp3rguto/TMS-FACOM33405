package com.jogodavelha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - Jogo da Velha (Tic-Tac-Toe)")
public class TabuleiroJogoDaVelhaTest {

    private TabuleiroJogoDaVelha jogo;

    @BeforeEach
    void iniciarMotor() {
        jogo = new TabuleiroJogoDaVelha();
    }

    @Test
    @DisplayName("Impede jogadas em posicoes que nao existem")
    void bloqueiaForaDosLimites() {
        assertThrows(IllegalArgumentException.class, () -> jogo.realizarMovimento(5, 5));
    }

    @Test
    @DisplayName("Impede jogada em cima de outra peca")
    void bloqueiaCelulaOcupada() {
        jogo.realizarMovimento(1, 1);
        assertThrows(IllegalStateException.class, () -> jogo.realizarMovimento(1, 1));
    }

    @Test
    @DisplayName("Detecta vitoria fechando uma linha horizontal")
    void validaVitoriaPorLinha() {
        jogo.realizarMovimento(0, 0); // X
        jogo.realizarMovimento(1, 0); // O
        jogo.realizarMovimento(0, 1); // X
        jogo.realizarMovimento(1, 1); // O
        jogo.realizarMovimento(0, 2); // X vence

        assertEquals(TabuleiroJogoDaVelha.Peca.X, jogo.obterVencedor());
    }

    @Test
    @DisplayName("Detecta empate (Deu Velha)")
    void validaEmpate() {
        jogo.realizarMovimento(0, 0); // X
        jogo.realizarMovimento(0, 1); // O
        jogo.realizarMovimento(0, 2); // X
        jogo.realizarMovimento(1, 1); // O
        jogo.realizarMovimento(1, 0); // X
        jogo.realizarMovimento(1, 2); // O
        jogo.realizarMovimento(2, 1); // X
        jogo.realizarMovimento(2, 0); // O
        jogo.realizarMovimento(2, 2); // X

        assertTrue(jogo.isEmpate());
    }
}