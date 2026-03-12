package com.katas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BingoBoardTest {

    private int[][] gerarCartelaSequencial() {
        int[][] cartela = new int[5][5];
        int contador = 10;
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) cartela[l][c] = contador++;
        }
        return cartela;
    }

    @Test
    public void validaVitoriaPorLinhaHorizontal() {
        int[][] config = gerarCartelaSequencial();
        BingoBoard jogo = new BingoBoard(config, false);

        // marcando toda a linha 3 (índice 2)
        for (int coluna = 0; coluna < 5; coluna++) {
            jogo.anunciarNumero(config[2][coluna]);
        }
        assertTrue(jogo.conquistouBingo(), "Falha: O bingo horizontal não foi detectado!");
    }

    @Test
    public void validaVitoriaPorColunaVertical() {
        int[][] config = gerarCartelaSequencial();
        BingoBoard jogo = new BingoBoard(config, false);

        // marcando toda a coluna 4 -índice 3
        for (int linha = 0; linha < 5; linha++) {
            jogo.anunciarNumero(config[linha][3]);
        }
        assertTrue(jogo.conquistouBingo(), "Falha: O bingo vertical não foi detectado!");
    }

    @Test
    public void validaVitoriaPelaDiagonalSecundaria() {
        int[][] config = gerarCartelaSequencial();
        BingoBoard jogo = new BingoBoard(config, false);

        for (int i = 0; i < 5; i++) jogo.anunciarNumero(config[i][4 - i]);
        assertTrue(jogo.conquistouBingo());
    }

    @Test
    public void validaQueCartelaIncompletaNaoGanha() {
        int[][] config = gerarCartelaSequencial();
        BingoBoard jogo = new BingoBoard(config, false);

        jogo.anunciarNumero(config[0][0]);
        jogo.anunciarNumero(config[4][4]);
        jogo.anunciarNumero(config[2][2]);
        assertFalse(jogo.conquistouBingo(), "Falha: Detectou bingo sem a cartela estar completa!");
    }

    @Test
    public void validaRegraDoCoringaCentral() {
        int[][] config = gerarCartelaSequencial();
        BingoBoard jogo = new BingoBoard(config, true); // centro já vem marcado

        jogo.anunciarNumero(config[2][0]);
        jogo.anunciarNumero(config[2][1]);
        // ignora o centro [2][2] pois é coringa
        jogo.anunciarNumero(config[2][3]);
        jogo.anunciarNumero(config[2][4]);

        assertTrue(jogo.conquistouBingo(), "A regra do espaço central gratuito falhou.");
    }
}