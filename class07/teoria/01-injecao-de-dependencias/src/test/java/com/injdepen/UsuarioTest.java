package com.injdepen;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Laboratório - Injeção de Dependências no JUnit")
public class UsuarioTest {

    private Usuario usuarioApp;

    @BeforeEach
    void prepararAmbiente(TestInfo detalhesDoTeste) {
        System.out.println(">> Iniciando a rotina: " + detalhesDoTeste.getDisplayName());
        usuarioApp = new Usuario("Guto Admin", "SenhaSuperSecreta123");
    }

    @Test
    @DisplayName("Garante que contas recém-criadas nascem desbloqueadas")
    void validaEstadoInicial(TestReporter relatorio) {
        relatorio.publishEntry("Auditoria", "Avaliando travas de segurança iniciais...");
        assertFalse(usuarioApp.isBloqueado(), "Erro: O usuário já nasceu bloqueado");
        assertEquals(0, usuarioApp.getQuantidadeFalhas());
    }

    @RepeatedTest(value = 3, name = "Simulando Invasão: Tentativa {currentRepetition} de {totalRepetitions}")
    @DisplayName("Testa mecanismo de defesa contra ataque de Força Bruta")
    void testaDefesaForcaBruta(RepetitionInfo infoRepeticao, TestReporter relatorio) {
        try {
            // tenta acessar com a senha errada
            usuarioApp.validarAcesso("senhaIncorreta");
            relatorio.publishEntry("Alerta", "Falha de acesso número " + infoRepeticao.getCurrentRepetition() + " registrada.");
        } catch (ExceededAttemptsException excecao) {
            // o sistema DEVE bloquear exatamente na 3ª tentativa
            assertEquals(3, infoRepeticao.getCurrentRepetition(), "O sistema bloqueou na quantidade de tentativas errada!");
            assertTrue(usuarioApp.isBloqueado(), "A conta deveria constar como bloqueada agora.");
        }
    }
}