package com.boliche;

public class PartidaDeBoliche {
    private int[] pinosDerrubados = new int[21];
    private int jogadaAtual = 0;

    public void registrarJogada(int quantidadePinos) {
        pinosDerrubados[jogadaAtual++] = quantidadePinos;
    }

    public int calcularPlacarFinal() {
        int pontuacaoTotal = 0;
        int cursor = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(cursor)) {
                pontuacaoTotal += 10 + pinosDerrubados[cursor + 1] + pinosDerrubados[cursor + 2];
                cursor++;
            } else if (isSpare(cursor)) {
                pontuacaoTotal += 10 + pinosDerrubados[cursor + 2];
                cursor += 2;
            } else {
                pontuacaoTotal += pinosDerrubados[cursor] + pinosDerrubados[cursor + 1];
                cursor += 2;
            }
        }
        return pontuacaoTotal;
    }

    private boolean isStrike(int cursor) {
        return pinosDerrubados[cursor] == 10;
    }

    private boolean isSpare(int cursor) {
        return pinosDerrubados[cursor] + pinosDerrubados[cursor + 1] == 10;
    }
}