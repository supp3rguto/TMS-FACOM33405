package com.conversao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - Conversor Universal de Unidades (Completo)")
public class ConversorUniversalTest {

    private ConversorUniversal motor;

    @BeforeEach
    void setup() {
        motor = new ConversorUniversal();
    }

    @Test
    @DisplayName("Garante conversão de comprimentos do sistema métrico para inglês")
    void testaComprimentoMetricoParaIngles() {
        // 5 km para milhas (aprox 3.10686)
        assertEquals(3.10686, motor.converterComprimento(5, "km", "mi"), 0.001);
        // 1 polegada para centímetros (exato 2.54)
        assertEquals(2.54, motor.converterComprimento(1, "in", "cm"), 0.001);
    }

    @Test
    @DisplayName("Garante conversão de pesos com Onças e Libras")
    void testaPesosDiversos() {
        // 1 kg para libras (aprox 2.20462)
        assertEquals(2.20462, motor.converterPeso(1, "kg", "lb"), 0.001);
        // 16 onças devem ser aproximadamente 1 libra (453.592g)
        assertEquals(1.0, motor.converterPeso(16, "oz", "lb"), 0.01);
    }

    @Test
    @DisplayName("Garante a matemática das escalas térmicas Celsius, Fahrenheit e Kelvin")
    void testaTemperaturas() {
        // ponto de ebulição da água
        assertEquals(212.0, motor.converterTemperatura(100, "C", "F"), 0.001);
        // ponto de congelamento em kelvin
        assertEquals(273.15, motor.converterTemperatura(0, "C", "K"), 0.001);
        // fahrenheit para kelvin
        assertEquals(255.372, motor.converterTemperatura(0, "F", "K"), 0.001);
    }

    @Test
    @DisplayName("Deve bloquear o uso de siglas de unidades que não existem")
    void testaBloqueioDeLixo() {
        assertThrows(IllegalArgumentException.class, () -> {
            motor.converterComprimento(10, "m", "jardas");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            motor.converterTemperatura(100, "X", "C");
        });
    }
}