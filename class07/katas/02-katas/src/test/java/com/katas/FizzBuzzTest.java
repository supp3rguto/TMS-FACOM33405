package com.katas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Suíte de Testes - Jogo FizzBuzz")
public class FizzBuzzTest {

    private FizzBuzz jogo;

    @BeforeEach
    void iniciarInstancia() {
        jogo = new FizzBuzz();
    }

    @DisplayName("Garante o retorno de 'Fizz' para múltiplos de 3")
    @Test
    void validaMultiplosDeTres() {
        assertEquals("Fizz", jogo.play(3));
        assertEquals("Fizz", jogo.play(12));
        assertEquals("Fizz", jogo.play(99));
    }

    @DisplayName("Garante o retorno de 'Buzz' para múltiplos de 5")
    @Test
    void validaMultiplosDeCinco() {
        assertEquals("Buzz", jogo.play(5));
        assertEquals("Buzz", jogo.play(25));
        assertEquals("Buzz", jogo.play(50));
    }

    @DisplayName("Garante o retorno de 'FizzBuzz' para múltiplos simultâneos")
    @Test
    void validaMultiplosDeTresECinco() {
        assertEquals("FizzBuzz", jogo.play(15));
        assertEquals("FizzBuzz", jogo.play(60));
        assertEquals("FizzBuzz", jogo.play(90));
    }

    @DisplayName("Retorna o próprio valor numérico para casos comuns")
    @Test
    void validaCasosSemMúltiplos() {
        assertEquals("1", jogo.play(1));
        assertEquals("8", jogo.play(8));
        assertEquals("13", jogo.play(13));
        assertEquals("77", jogo.play(77));
    }

    @DisplayName("Teste parametrizado massivo com vários cenários de borda")
    @ParameterizedTest(name = "O envio do valor {0} deve resultar na string {1}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "18, Fizz",     // multiplo exclusivo de 3
            "19, 19",
            "35, Buzz",     // multiplo exclusivo de 5
            "150, FizzBuzz" // multiplo de ambos
    })
    void validacaoGeralEmMassa(int numeroEntrada, String resultadoEsperado) {
        assertEquals(resultadoEsperado, jogo.play(numeroEntrada));
    }
}