package com.calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes da Calculadora Completa (Parte 2)")
public class CalculatorTest {

    @Test
    @DisplayName("Valida operação de soma")
    public void verificaSoma() {
        Calculator calc = new Calculator();
        assertEquals(75, calc.add(50, 25), 0);
    }

    @Test
    @DisplayName("Valida operação de subtração")
    public void verificaSubtracao() {
        Calculator calc = new Calculator();
        assertEquals(30, calc.subtract(80, 50), 0);
    }

    @Test
    @DisplayName("Valida operação de multiplicação")
    public void verificaMultiplicacao() {
        Calculator calc = new Calculator();
        assertEquals(42, calc.multiply(6, 7), 0);
    }

    @Test
    @DisplayName("Valida operação de divisão")
    public void verificaDivisao() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.divide(45, 3), 0);
    }

    @Test
    @DisplayName("Tratamento de erro na divisão por zero")
    public void verificaDivisaoPorZero() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divide(99, 0);
        });
    }

    @Test
    @DisplayName("Calcula potência corretamente")
    public void verificaPotenciacao() {
        Calculator calc = new Calculator();
        assertEquals(64, calc.power(4, 3), 0, "4 elevado a 3 deve ser 64");
        assertEquals(6.25, calc.power(2.5, 2), 0.001);
        assertEquals(1, calc.power(42, 0), 0, "Todo número elevado a 0 é 1");
    }

    @Test
    @DisplayName("Calcula raiz quadrada corretamente")
    public void verificaRaizQuadrada() {
        Calculator calc = new Calculator();
        assertEquals(8, calc.squareRoot(64), 0);
        assertEquals(1.3, calc.squareRoot(1.69), 0.001);
        assertEquals(0, calc.squareRoot(0), 0);
    }

    @Test
    @DisplayName("Impede raiz quadrada de números negativos")
    public void verificaRaizDeNumeroNegativo() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.squareRoot(-25);
        }, "Raiz de negativo deve lançar exceção");
    }

    @Test
    @DisplayName("Comportamento com valores limites numéricos (Double)")
    public void verificaValoresLimites() {
        Calculator calc = new Calculator();
        assertEquals(Double.POSITIVE_INFINITY, calc.multiply(Double.MAX_VALUE, 5), 0);
        assertEquals(1.3407807929942596E154, calc.squareRoot(Double.MAX_VALUE), 0);
    }
}