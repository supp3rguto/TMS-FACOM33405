package com.boliche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - The Bowling Game")
public class PartidaDeBolicheTest {

    private PartidaDeBoliche jogo;

    @BeforeEach
    void montarPista() {
        jogo = new PartidaDeBoliche();
    }

    private void rolarVezes(int repeticoes, int pinos) {
        for (int i = 0; i < repeticoes; i++) {
            jogo.registrarJogada(pinos);
        }
    }

    @Test
    @DisplayName("Jogo sofrivel (nenhum pino derrubado)")
    void testaJogoZerado() {
        rolarVezes(20, 0);
        assertEquals(0, jogo.calcularPlacarFinal());
    }

    @Test
    @DisplayName("Derruba apenas 1 pino em todas as jogadas")
    void testaJogoDeUmPino() {
        rolarVezes(20, 1);
        assertEquals(20, jogo.calcularPlacarFinal());
    }

    @Test
    @DisplayName("Acerta um Spare e calcula o bonus da proxima bola")
    void testaUmSpare() {
        jogo.registrarJogada(5);
        jogo.registrarJogada(5); // Spare!
        jogo.registrarJogada(3);
        rolarVezes(17, 0);
        assertEquals(16, jogo.calcularPlacarFinal());
    }

    @Test
    @DisplayName("Acerta um Strike e ganha bonus nas duas proximas bolas")
    void testaUmStrike() {
        jogo.registrarJogada(10); // Strike!
        jogo.registrarJogada(3);
        jogo.registrarJogada(4);
        rolarVezes(16, 0);
        assertEquals(24, jogo.calcularPlacarFinal());
    }

    @Test
    @DisplayName("Jogo Perfeito! Somente strikes")
    void testaJogoPerfeito() {
        rolarVezes(12, 10);
        assertEquals(300, jogo.calcularPlacarFinal());
    }
}