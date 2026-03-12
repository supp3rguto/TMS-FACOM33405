package com.romanos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes do Conversor de Numerais Romanos")
public class ConversorRomanosTest {

    private final ConversorRomanos conversor = new ConversorRomanos();

    // testes para romano
    @Test
    @DisplayName("Deve converter numeros simples para romanos")
    void testaConversaoArabicoParaRomanoSimples() {
        assertEquals("I", conversor.paraRomano(1));
        assertEquals("V", conversor.paraRomano(5));
        assertEquals("X", conversor.paraRomano(10));
        assertEquals("L", conversor.paraRomano(50));
        assertEquals("C", conversor.paraRomano(100));
        assertEquals("D", conversor.paraRomano(500));
        assertEquals("M", conversor.paraRomano(1000));
    }

    @Test
    @DisplayName("Deve converter numeros com regras de subtracao para romanos")
    void testaConversaoArabicoParaRomanoComSubtracao() {
        assertEquals("IV", conversor.paraRomano(4));
        assertEquals("IX", conversor.paraRomano(9));
        assertEquals("XL", conversor.paraRomano(40));
        assertEquals("XC", conversor.paraRomano(90));
        assertEquals("CD", conversor.paraRomano(400));
        assertEquals("CM", conversor.paraRomano(900));
    }

    @Test
    @DisplayName("Deve converter numeros complexos para romanos")
    void testaConversaoArabicoParaRomanoComplexos() {
        assertEquals("XIV", conversor.paraRomano(14));
        assertEquals("MCMXCIV", conversor.paraRomano(1994));
        assertEquals("MMMCMXCIX", conversor.paraRomano(3999));
    }

    @Test
    @DisplayName("Deve lancar excecao ao tentar converter arabicos invalidos")
    void testaExcecaoArabicoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> conversor.paraRomano(0));
        assertThrows(IllegalArgumentException.class, () -> conversor.paraRomano(-5));
        assertThrows(IllegalArgumentException.class, () -> conversor.paraRomano(4000));
    }

    // testes para arabico
    @Test
    @DisplayName("Deve converter letras individuais para arabicos")
    void testaConversaoRomanoParaArabicoSimples() {
        assertEquals(1, conversor.paraArabico("I"));
        assertEquals(5, conversor.paraArabico("V"));
        assertEquals(10, conversor.paraArabico("X"));
        assertEquals(50, conversor.paraArabico("L"));
    }

    @Test
    @DisplayName("Deve aplicar regra de subtracao ao converter romanos")
    void testaConversaoRomanoParaArabicoComSubtracao() {
        assertEquals(4, conversor.paraArabico("IV"));
        assertEquals(9, conversor.paraArabico("IX"));
        assertEquals(40, conversor.paraArabico("XL"));
        assertEquals(900, conversor.paraArabico("CM"));
    }

    @Test
    @DisplayName("Deve converter strings romanas complexas para arabico")
    void testaConversaoRomanoParaArabicoComplexo() {
        assertEquals(1987, conversor.paraArabico("MCMLXXXVII"));
        assertEquals(3999, conversor.paraArabico("MMMCMXCIX"));
    }

    @Test
    @DisplayName("Deve lancar excecao ao receber string romana invalida")
    void testaExcecaoRomanoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> conversor.paraArabico(""));
        assertThrows(IllegalArgumentException.class, () -> conversor.paraArabico(null));
        assertThrows(IllegalArgumentException.class, () -> conversor.paraArabico("X1V")); // Numero no meio
        assertThrows(IllegalArgumentException.class, () -> conversor.paraArabico("XP")); // Letra invalida
    }
}