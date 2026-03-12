package com.katas;

import java.util.HashSet;
import java.util.Set;

public class BingoBoard {
    private final int TAMANHO = 5;
    private final int[][] matrizNumeros;
    private final boolean[][] celulasMarcadas;

    public BingoBoard(int[][] configuracaoInicial, boolean possuiCentroLivre) {
        if (configuracaoInicial == null || configuracaoInicial.length != TAMANHO) {
            throw new IllegalArgumentException("O tabuleiro exige uma matriz 5x5 estrita.");
        }

        matrizNumeros = new int[TAMANHO][TAMANHO];
        celulasMarcadas = new boolean[TAMANHO][TAMANHO];
        Set<Integer> detectorDeDuplicatas = new HashSet<>();

        for (int linha = 0; linha < TAMANHO; linha++) {
            if (configuracaoInicial[linha].length != TAMANHO) throw new IllegalArgumentException("Linha inválida.");
            for (int col = 0; col < TAMANHO; col++) {
                int valor = configuracaoInicial[linha][col];
                if (!detectorDeDuplicatas.add(valor)) {
                    throw new IllegalArgumentException("Regra violada: números duplicados não são permitidos.");
                }
                matrizNumeros[linha][col] = valor;
                celulasMarcadas[linha][col] = false;
            }
        }

        if (possuiCentroLivre) {
            celulasMarcadas[2][2] = true;
        }
    }

    public void anunciarNumero(int numeroSorteado) {
        for (int l = 0; l < TAMANHO; l++) {
            for (int c = 0; c < TAMANHO; c++) {
                if (matrizNumeros[l][c] == numeroSorteado) {
                    celulasMarcadas[l][c] = true;
                }
            }
        }
    }

    public boolean conquistouBingo() {
        return verificaLinhas() || verificaColunas() || verificaDiagonais();
    }

    private boolean verificaLinhas() {
        for (int l = 0; l < TAMANHO; l++) {
            boolean completou = true;
            for (int c = 0; c < TAMANHO; c++) if (!celulasMarcadas[l][c]) completou = false;
            if (completou) return true;
        }
        return false;
    }

    private boolean verificaColunas() {
        for (int c = 0; c < TAMANHO; c++) {
            boolean completou = true;
            for (int l = 0; l < TAMANHO; l++) if (!celulasMarcadas[l][c]) completou = false;
            if (completou) return true;
        }
        return false;
    }

    private boolean verificaDiagonais() {
        boolean diagPrincipal = true, diagSecundaria = true;
        for (int i = 0; i < TAMANHO; i++) {
            if (!celulasMarcadas[i][i]) diagPrincipal = false;
            if (!celulasMarcadas[i][TAMANHO - 1 - i]) diagSecundaria = false;
        }
        return diagPrincipal || diagSecundaria;
    }
}