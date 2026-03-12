package com.manipulacaostrings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Testes Unitários - Manipulação de Strings")
public class StringManipulatorTest {

    private StringManipulator utilitarioString;

    @BeforeEach
    void prepararAmbiente() {
        utilitarioString = new StringManipulator();
    }

    @Test
    @DisplayName("Garante a inversão correta de palavras curtas")
    void validaInversaoDePalavraCurta() {
        assertEquals("tinUJ", utilitarioString.reverse("JUnit"));
    }

    @Test
    @DisplayName("Inversão de string vazia deve retornar vazio")
    void validaInversaoDeStringVazia() {
        assertEquals("", utilitarioString.reverse(""));
    }

    @Test
    @DisplayName("Passar Null para inversão deve retornar Null")
    void validaInversaoDeNulo() {
        assertNull(utilitarioString.reverse(null));
    }

    @Test
    @DisplayName("Garante inversão de símbolos e pontuações")
    void validaInversaoDeCaracteresEspeciais() {
        assertEquals("!@#", utilitarioString.reverse("#@!"));
    }

    @Test
    @DisplayName("Verifica contagem exata de uma letra específica na palavra")
    void verificaContagemDeCaracteres() {
        assertEquals(3, utilitarioString.countOccurrences("abacate", 'a'));
        assertEquals(0, utilitarioString.countOccurrences("abacate", 'x'));
    }

    @Test
    @DisplayName("Contagem em string vazia deve ser zero")
    void verificaContagemEmStringVazia() {
        assertEquals(0, utilitarioString.countOccurrences("", 'b'));
    }

    @Test
    @DisplayName("Contagem em valor nulo deve resultar em zero")
    void verificaContagemEmNulo() {
        assertEquals(0, utilitarioString.countOccurrences(null, 'y'));
    }

    @Test
    @DisplayName("Valida detecção de palíndromos simples")
    void verificaPalindromoSimples() {
        assertTrue(utilitarioString.isPalindrome("radar"));
        assertFalse(utilitarioString.isPalindrome("Guto"));
    }

    @Test
    @DisplayName("Valida palíndromo ignorando pontuação e espaços")
    void verificaPalindromoComCaracteresEspeciais() {
        assertTrue(utilitarioString.isPalindrome("A cara rajada da jararaca"));
    }

    @Test
    @DisplayName("Valida palíndromos muito longos")
    void verificaPalindromoExtenso() {
        String textoLongo = "A base do teto desaba";
        assertTrue(utilitarioString.isPalindrome(textoLongo));
    }

    @Test
    @DisplayName("Strings nulas não podem ser validadas como palíndromos")
    void rejeitaNuloComoPalindromo() {
        assertFalse(utilitarioString.isPalindrome(null));
    }

    @Test
    @DisplayName("Strings vazias são consideradas palíndromos por definição")
    void aceitaVazioComoPalindromo() {
        assertTrue(utilitarioString.isPalindrome(""));
    }

    @Test
    @DisplayName("Valida conversão total para letras maiúsculas")
    void verificaConversaoMaiuscula() {
        assertEquals("TESTE UNITARIO", utilitarioString.toUpperCase("Teste Unitario"));
        assertEquals("CAIXA ALTA", utilitarioString.toUpperCase("CAIXA ALTA"));
        assertEquals("", utilitarioString.toUpperCase(""));
        assertNull(utilitarioString.toUpperCase(null));
    }

    @Test
    @DisplayName("Valida conversão total para letras minúsculas")
    void verificaConversaoMinuscula() {
        assertEquals("framework", utilitarioString.toLowerCase("FrameWork"));
        assertEquals("minusculo", utilitarioString.toLowerCase("MINUSCULO"));
        assertEquals("", utilitarioString.toLowerCase(""));
        assertNull(utilitarioString.toLowerCase(null));
    }
}