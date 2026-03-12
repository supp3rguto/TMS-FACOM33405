package com.katas;

public class BerlinClock {

    public String[] traduzirTempo(String formatoTradicional) {
        if (formatoTradicional == null || !formatoTradicional.matches("\\d{2}:\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Tempo deve ser no formato HH:mm:ss");
        }

        String[] split = formatoTradicional.split(":");
        int horas = Integer.parseInt(split[0]);
        int minutos = Integer.parseInt(split[1]);
        int segundos = Integer.parseInt(split[2]);

        if (horas > 24 || minutos > 59 || segundos > 59) {
            throw new IllegalArgumentException("Valores de tempo fora da realidade.");
        }

        return new String[]{
                calculaPulsarSegundos(segundos),
                calculaBlocosDeCincoHoras(horas),
                calculaHorasIndividuais(horas),
                calculaBlocosDeCincoMinutos(minutos),
                calculaMinutosIndividuais(minutos)
        };
    }

    private String calculaPulsarSegundos(int s) {
        return (s % 2 == 0) ? "Y" : "O";
    }

    private String calculaBlocosDeCincoHoras(int h) {
        int blocosAtivos = h / 5;
        return construtorLuzes(blocosAtivos, 4, "R");
    }

    private String calculaHorasIndividuais(int h) {
        int blocosAtivos = h % 5;
        return construtorLuzes(blocosAtivos, 4, "R");
    }

    private String calculaBlocosDeCincoMinutos(int m) {
        int blocosAtivos = m / 5;
        StringBuilder painel = new StringBuilder();
        for (int i = 1; i <= 11; i++) {
            if (i <= blocosAtivos) {
                painel.append((i % 3 == 0) ? "R" : "Y");
            } else {
                painel.append("O");
            }
        }
        return painel.toString();
    }

    private String calculaMinutosIndividuais(int m) {
        int blocosAtivos = m % 5;
        return construtorLuzes(blocosAtivos, 4, "Y");
    }

    private String construtorLuzes(int ligadas, int total, String cor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            sb.append(i < ligadas ? cor : "O");
        }
        return sb.toString();
    }
}