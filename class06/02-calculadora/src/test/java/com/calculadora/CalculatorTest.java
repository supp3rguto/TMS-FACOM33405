package com.calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes da Calculadora Básica (Parte 1)")
public class CalculatorTest {

    @Test
    @DisplayName("Garante que a soma de dois números funciona")
    public void validaSomaBasica() {
        Calculator calc = new Calculator();
        assertEquals(40, calc.add(25, 15), 0, "A soma de 25 com 15 deveria ser 40");
    }

    @Test
    @DisplayName("Garante que a subtração retorna o valor correto")
    public void validaSubtracaoBasica() {
        Calculator calc = new Calculator();
        assertEquals(65, calc.subtract(100, 35), 0, "A subtração de 100 por 35 deve ser 65");
    }

    @Test
    @DisplayName("Verifica a multiplicação entre dois inteiros positivos")
    public void validaMultiplicacaoBasica() {
        Calculator calc = new Calculator();
        assertEquals(56, calc.multiply(8, 7), 0, "8 multiplicado por 7 tem que ser 56");
    }

    @Test
    @DisplayName("Verifica a divisão exata")
    public void validaDivisaoExata() {
        Calculator calc = new Calculator();
        assertEquals(20, calc.divide(80, 4), 0, "80 dividido por 4 precisa retornar 20");
    }

    @Test
    @DisplayName("Impede a divisão por zero lançando exceção")
    public void impedeDivisaoPorZero() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divide(25, 0);
        }, "O sistema deve lançar erro ao tentar dividir por zero");
    }
}