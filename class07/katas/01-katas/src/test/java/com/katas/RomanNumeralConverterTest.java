package com.katas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Suíte de Testes - Conversão de Números Romanos")
public class RomanNumeralConverterTest {

    private RomanNumeralConverter conversorRomano;

    @BeforeEach
    void prepararInstancia() {
        conversorRomano = new RomanNumeralConverter();
    }

    @DisplayName("Valida a conversão dos símbolos literais")
    @ParameterizedTest(name = "O número {0} deve gerar o símbolo {1}")
    @CsvSource({
            "1, I", "5, V", "10, X", "50, L",
            "100, C", "500, D", "1000, M"
    })
    void verificaConversoesBase(int valorEntrada, String simboloEsperado) {
        assertEquals(simboloEsperado, conversorRomano.convert(valorEntrada));
    }

    @DisplayName("Valida numerais que exigem repetição de caracteres")
    @Test
    void verificaCasosDeRepeticao() {
        assertEquals("II", conversorRomano.convert(2));
        assertEquals("XXX", conversorRomano.convert(30));
        assertEquals("CC", conversorRomano.convert(200));
    }

    @DisplayName("Valida números que caem nas regras de subtração (ex: 4, 9)")
    @ParameterizedTest(name = "Teste de subtração: {0} vira {1}")
    @CsvSource({
            "4, IV", "9, IX", "40, XL",
            "90, XC", "400, CD", "900, CM"
    })
    void verificaRegrasSubtrativas(int valorEntrada, String simboloEsperado) {
        assertEquals(simboloEsperado, conversorRomano.convert(valorEntrada));
    }

    @DisplayName("Valida combinações extensas e anos")
    @ParameterizedTest(name = "Conversão de {0} para {1}")
    @CsvSource({
            "44, XLIV",
            "98, XCVIII",
            "2024, MMXXIV",
            "3888, MMMDCCCLXXXVIII"
    })
    void verificaConversoesComplexas(int valorEntrada, String simboloEsperado) {
        assertEquals(simboloEsperado, conversorRomano.convert(valorEntrada));
    }

    @DisplayName("Garante que exceções são disparadas em entradas fora do escopo")
    @Test
    void verificaComportamentoDeExcecoes() {
        assertThrows(IllegalArgumentException.class, () -> conversorRomano.convert(0),
                "Deveria bloquear o zero, pois não existe em algarismo romano");

        assertThrows(IllegalArgumentException.class, () -> conversorRomano.convert(-25),
                "Deveria bloquear números negativos");

        assertThrows(IllegalArgumentException.class, () -> conversorRomano.convert(5000),
                "Deveria bloquear entradas que superam o limite de 3999");
    }
}