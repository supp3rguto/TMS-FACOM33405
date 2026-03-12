package com.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - Fizz Buzz")
public class GeradorFizzBuzzTest {

    private GeradorFizzBuzz fizzBuzz;

    @BeforeEach
    void instanciar() {
        fizzBuzz = new GeradorFizzBuzz();
    }

    @Test
    @DisplayName("Numeros comuns retornam como String")
    void testaNumerosComuns() {
        assertEquals("1", fizzBuzz.processarNumero(1));
        assertEquals("2", fizzBuzz.processarNumero(2));
        assertEquals("4", fizzBuzz.processarNumero(4));
    }

    @Test
    @DisplayName("Multiplos exclusivos de 3 retornam Fizz")
    void testaMultiplosDeTres() {
        assertEquals("Fizz", fizzBuzz.processarNumero(3));
        assertEquals("Fizz", fizzBuzz.processarNumero(9));
    }

    @Test
    @DisplayName("Multiplos exclusivos de 5 retornam Buzz")
    void testaMultiplosDeCinco() {
        assertEquals("Buzz", fizzBuzz.processarNumero(5));
        assertEquals("Buzz", fizzBuzz.processarNumero(25));
    }

    @Test
    @DisplayName("Multiplos de 3 e 5 simultaneamente retornam FizzBuzz")
    void testaMultiplosDeAmbos() {
        assertEquals("FizzBuzz", fizzBuzz.processarNumero(15));
        assertEquals("FizzBuzz", fizzBuzz.processarNumero(60));
    }
}