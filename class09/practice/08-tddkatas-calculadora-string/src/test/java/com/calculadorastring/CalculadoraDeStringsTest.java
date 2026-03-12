package com.calculadorastring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - String Calculator")
public class CalculadoraDeStringsTest {

    private CalculadoraDeStrings calculadora;

    @BeforeEach
    void iniciar() {
        calculadora = new CalculadoraDeStrings();
    }

    @Test
    @DisplayName("Deve retornar 0 para string vazia")
    void testaStringVazia() {
        assertEquals(0, calculadora.somar(""));
    }

    @Test
    @DisplayName("Soma de um unico numero retorna ele mesmo")
    void testaUmNumero() {
        assertEquals(5, calculadora.somar("5"));
    }

    @Test
    @DisplayName("Soma de dois numeros com virgula")
    void testaDoisNumeros() {
        assertEquals(15, calculadora.somar("7,8"));
    }

    @Test
    @DisplayName("Aceita quebras de linha como delimitador")
    void testaQuebraDeLinha() {
        assertEquals(6, calculadora.somar("1\n2,3"));
    }

    @Test
    @DisplayName("Suporta delimitadores customizados")
    void testaDelimitadorPersonalizado() {
        assertEquals(3, calculadora.somar("//;\n1;2"));
    }

    @Test
    @DisplayName("Bloqueia multiplos numeros negativos e exibe na mensagem")
    void testaRejeicaoNegativos() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.somar("2,-4,3,-5");
        });
        assertTrue(erro.getMessage().contains("[-4, -5]"));
    }
}