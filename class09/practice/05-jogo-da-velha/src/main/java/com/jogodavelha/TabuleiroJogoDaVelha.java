package com.jogodavelha;

public class TabuleiroJogoDaVelha {

    public enum Peca { VAZIO, X, O }

    private Peca[][] grelha = new Peca[3][3];
    private Peca turnoAtual = Peca.X;

    public TabuleiroJogoDaVelha() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                grelha[l][c] = Peca.VAZIO;
            }
        }
    }

    public void realizarMovimento(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            throw new IllegalArgumentException("Movimento fora das bordas do tabuleiro.");
        }
        if (grelha[linha][coluna] != Peca.VAZIO) {
            throw new IllegalStateException("A celula informada ja esta ocupada!");
        }

        grelha[linha][coluna] = turnoAtual;
        turnoAtual = (turnoAtual == Peca.X) ? Peca.O : Peca.X;
    }

    public Peca obterVencedor() {
        for (int i = 0; i < 3; i++) {
            if (grelha[i][0] != Peca.VAZIO && grelha[i][0] == grelha[i][1] && grelha[i][1] == grelha[i][2]) return grelha[i][0];
            if (grelha[0][i] != Peca.VAZIO && grelha[0][i] == grelha[1][i] && grelha[1][i] == grelha[2][i]) return grelha[0][i];
        }
        if (grelha[0][0] != Peca.VAZIO && grelha[0][0] == grelha[1][1] && grelha[1][1] == grelha[2][2]) return grelha[0][0];
        if (grelha[0][2] != Peca.VAZIO && grelha[0][2] == grelha[1][1] && grelha[1][1] == grelha[2][0]) return grelha[0][2];

        return Peca.VAZIO;
    }

    public boolean isEmpate() {
        if (obterVencedor() != Peca.VAZIO) return false;
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (grelha[l][c] == Peca.VAZIO) return false;
            }
        }
        return true;
    }
}