package com.moedas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("TDD - Conversor de Moedas (Usando Dublês)")
public class ConversorDeMoedasTest {

    private ServicoDeCambio mockApiB3;
    private ConversorDeMoedas conversor;

    @BeforeEach
    void iniciarMotor() {
        mockApiB3 = mock(ServicoDeCambio.class);
        conversor = new ConversorDeMoedas(mockApiB3);
    }

    @Test
    @DisplayName("Realiza conversao com taxa dinamica simulada pelo Mock")
    void testaConversaoComMock() {
        when(mockApiB3.obterTaxaDeCambio("USD", "BRL")).thenReturn(5.0);

        double resultado = conversor.converter(10.0, "USD", "BRL");

        assertEquals(50.0, resultado, 0.001);
        verify(mockApiB3, times(1)).obterTaxaDeCambio("USD", "BRL");
    }

    @Test
    @DisplayName("Lida com falhas na API externa")
    void testaFalhaDeApi() {
        when(mockApiB3.obterTaxaDeCambio("BRL", "JPY")).thenReturn(0.0);

        assertThrows(IllegalArgumentException.class, () -> {
            conversor.converter(100.0, "BRL", "JPY");
        });
    }
}