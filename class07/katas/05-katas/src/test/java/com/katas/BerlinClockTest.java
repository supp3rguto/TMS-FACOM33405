package com.katas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Bateria de Testes - Relógio de Berlim")
public class BerlinClockTest {

    private BerlinClock relogio;

    @BeforeEach
    void prepararRelogio() {
        relogio = new BerlinClock();
    }

    @Test
    @DisplayName("Deve traduzir 00:00:00 corretamente")
    void validaViradaDeDia() {
        String[] esperado = {"Y", "OOOO", "OOOO", "OOOOOOOOOOO", "OOOO"};
        assertArrayEquals(esperado, relogio.traduzirTempo("00:00:00"));
    }

    @Test
    @DisplayName("Deve traduzir a hora do almoço (13:17:01)")
    void validaHorarioAleatorio() {
        // 13h = 2 blocos de 5h (RR) + 3 individuais (RRR)
        // 17m = 3 blocos de 5m (YYR) + 2 individuais (YY)
        // 01s = impar (O)
        String[] esperado = {
                "O",
                "RROO",
                "RRRO",
                "YYROOOOOOOO",
                "YYOO"
        };
        assertArrayEquals(esperado, relogio.traduzirTempo("13:17:01"));
    }

    @Test
    @DisplayName("Deve traduzir o fim do dia (23:59:59)")
    void validaMinutoFinal() {
        String[] esperado = {
                "O",
                "RRRR",
                "RRRO",
                "YYRYYRYYRYY",
                "YYYY"
        };
        assertArrayEquals(esperado, relogio.traduzirTempo("23:59:59"));
    }

    @Test
    @DisplayName("Exceções para quebras de formato")
    void bloqueiaFormatosMalucos() {
        assertThrows(IllegalArgumentException.class, () -> relogio.traduzirTempo("12-34"));
        assertThrows(IllegalArgumentException.class, () -> relogio.traduzirTempo("99:99:99"));
        assertThrows(IllegalArgumentException.class, () -> relogio.traduzirTempo("Texto"));
        assertThrows(IllegalArgumentException.class, () -> relogio.traduzirTempo(null));
    }
}