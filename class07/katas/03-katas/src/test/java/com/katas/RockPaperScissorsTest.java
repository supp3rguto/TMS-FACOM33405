package com.katas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Suíte de Testes - Pedra, Papel e Tesoura")
public class RockPaperScissorsTest {

    private RockPaperScissors partida;

    @BeforeEach
    void iniciarPartida() {
        partida = new RockPaperScissors();
    }

    @DisplayName("Garante que jogadas iguais resultem em empate")
    @Test
    void verificaEmpates() {
        assertEquals(RockPaperScissors.Resultado.EMPATE, partida.jogar(RockPaperScissors.Jogada.PEDRA, RockPaperScissors.Jogada.PEDRA));
        assertEquals(RockPaperScissors.Resultado.EMPATE, partida.jogar(RockPaperScissors.Jogada.PAPEL, RockPaperScissors.Jogada.PAPEL));
        assertEquals(RockPaperScissors.Resultado.EMPATE, partida.jogar(RockPaperScissors.Jogada.TESOURA, RockPaperScissors.Jogada.TESOURA));
    }

    @DisplayName("Valida todas as combinações onde o Jogador 1 é o vencedor")
    @ParameterizedTest(name = "J1 ({0}) amassa J2 ({1})")
    @CsvSource({
            "PEDRA, TESOURA",
            "TESOURA, PAPEL",
            "PAPEL, PEDRA"
    })
    void verificaVitoriasP1(RockPaperScissors.Jogada j1, RockPaperScissors.Jogada j2) {
        assertEquals(RockPaperScissors.Resultado.VITORIA_P1, partida.jogar(j1, j2));
    }

    @DisplayName("Valida todas as combinações onde o Jogador 2 é o vencedor")
    @ParameterizedTest(name = "J2 ({0}) amassa J1 ({1})")
    @CsvSource({
            "PEDRA, PAPEL",
            "PAPEL, TESOURA",
            "TESOURA, PEDRA"
    })
    void verificaVitoriasP2(RockPaperScissors.Jogada j1, RockPaperScissors.Jogada j2) {
        assertEquals(RockPaperScissors.Resultado.VITORIA_P2, partida.jogar(j1, j2));
    }

    @DisplayName("O sistema deve barrar trapaças (jogadas nulas)")
    @Test
    void verificaBloqueioDeNulos() {
        assertThrows(IllegalArgumentException.class, () -> partida.jogar(RockPaperScissors.Jogada.PAPEL, null));
        assertThrows(IllegalArgumentException.class, () -> partida.jogar(null, RockPaperScissors.Jogada.PEDRA));
        assertThrows(IllegalArgumentException.class, () -> partida.jogar(null, null));
    }
}