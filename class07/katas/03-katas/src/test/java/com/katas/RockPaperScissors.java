package com.katas;

public class RockPaperScissors {

    public enum Jogada {
        PEDRA, PAPEL, TESOURA;

        public boolean venceDe(Jogada adversario) {
            return (this == PEDRA && adversario == TESOURA) ||
                    (this == TESOURA && adversario == PAPEL) ||
                    (this == PAPEL && adversario == PEDRA);
        }
    }

    public enum Resultado {
        VITORIA_P1, VITORIA_P2, EMPATE
    }

    public Resultado jogar(Jogada p1, Jogada p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Nenhum dos jogadores pode fazer uma jogada nula.");
        }

        if (p1 == p2) {
            return Resultado.EMPATE;
        }

        return p1.venceDe(p2) ? Resultado.VITORIA_P1 : Resultado.VITORIA_P2;
    }
}