package com.senha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - Validador de Senhas Fortes")
public class ValidadorSenhaTest {

    private ValidadorSenha validador;

    @BeforeEach
    void iniciar() {
        validador = new ValidadorSenha();
    }

    @Test
    @DisplayName("Deve rejeitar senhas nulas ou vazias")
    void testaVazio() {
        assertFalse(validador.validar(""));
        assertFalse(validador.validar(null));
    }

    @Test
    @DisplayName("Deve rejeitar senhas com menos de 8 caracteres")
    void testaTamanhoMinimo() {
        assertFalse(validador.validar("Guto@12")); // 7 caracteres
    }

    @Test
    @DisplayName("Deve rejeitar senhas sem letras maiusculas")
    void testaSemMaiuscula() {
        assertFalse(validador.validar("senhaforte@123"));
    }

    @Test
    @DisplayName("Deve rejeitar senhas sem caracteres especiais")
    void testaSemEspecial() {
        assertFalse(validador.validar("SenhaForte1234"));
    }

    @Test
    @DisplayName("Garante a aprovacao de uma senha que atende todos os criterios")
    void testaSenhaPerfeita() {
        assertTrue(validador.validar("Sup3rGuto@2026!"));
    }

    @Test
    @DisplayName("Deve rejeitar senhas que contenham espacos em branco")
    void testaBloqueioDeEspacos() {
        assertFalse(validador.validar("Guto @2026!")); // Tem espaço no meio
    }
}