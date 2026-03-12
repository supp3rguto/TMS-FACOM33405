package com.testesparam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Laboratório - Testes Parametrizados (Cenários em Massa)")
public class UsuarioTest {

    @DisplayName("Testa a rejeição de senhas inválidas via ValueSource")
    @ParameterizedTest(name = "Cenário de Segurança: A tentativa com a senha ''{0}'' deve falhar")
    @ValueSource(strings = {"senhaErrada", "guto123", "root", "admin", "12345"})
    void validaRejeicaoSenhasEmMassa(String senhaTestada) {
        Usuario usuario = new Usuario("Tester", "SenhaVerdadeira999");

        try {
            boolean liberado = usuario.validarAcesso(senhaTestada);
            assertFalse(liberado, "Alerta: O sistema liberou o acesso com a senha errada!");
        } catch (ExceededAttemptsException e) {
            fail("O bloqueio por tentativas não deveria ocorrer neste teste isolado.");
        }
    }

    @DisplayName("Validação múltipla com senhas certas e erradas usando CsvSource")
    @ParameterizedTest(name = "Senha inserida: {0} -> O resultado esperado é {1}")
    @CsvSource({
            "SenhaCorreta, true",
            "senhaIncorreta, false",
            "12345, false"
    })
    void validaCredenciaisMistas(String senhaInformada, boolean resultadoEsperado) {
        Usuario usuario = new Usuario("Admin", "SenhaCorreta");

        try {
            boolean resultadoObtido = usuario.validarAcesso(senhaInformada);
            assertEquals(resultadoEsperado, resultadoObtido);
        } catch (ExceededAttemptsException e) {
            fail("Bloqueio não esperado.");
        }
    }

    @DisplayName("Validação de Emails e Nomes via arquivo CSV externo")
    @ParameterizedTest(name = "Lendo CSV - Email: {0} | Nome: {1} | Deve ser Válido? {2}")
    @CsvFileSource(resources = "/validacao_usuarios.csv", numLinesToSkip = 1)
    void validaBateriaLendoArquivoCsv(String emailLido, String nomeLido, boolean esperadoValido) {
        Usuario usuario = new Usuario("LoginPadrao", "Senha123");
        boolean operacaoSucesso = true;

        try {
            usuario.alterarEmail(emailLido);
            usuario.alterarNome(nomeLido);
        } catch (IllegalArgumentException e) {
            // se o Regex da sua classe Usuario bloquear o email sem "@" ou o nome com números, ele lança a exceção e cai aqui, marcando a operação como falsa.
            operacaoSucesso = false;
        }

        assertEquals(esperadoValido, operacaoSucesso, "A validação via CSV retornou um resultado divergente do esperado.");
    }

    enum AlgoritmosSuportados {
        SHA_256("SHA-256"), MD5("MD5"), SHA_1("SHA-1");

        private final String nome;

        AlgoritmosSuportados(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    @DisplayName("Garante que algoritmos invalidos lancem excecao")
    @ParameterizedTest(name = "Algoritmo testado: {0}")
    @ValueSource(strings = {"ALGORITMO_FALSO", "SHA-999", "CRIPTOGRAFIA_X"})
    void validaExcecaoAlgoritmoDesconhecido(String algoritmoInvalido) {
        Usuario usuario = new Usuario("Login", "Senha123");

        assertThrows(java.security.NoSuchAlgorithmException.class, () -> {
            usuario.validarAcessoComAlgoritmoCustomizado("Senha123", algoritmoInvalido);
        });
    }

    @DisplayName("Testa autenticacao com multiplos algoritmos usando EnumSource")
    @ParameterizedTest(name = "Usando Algoritmo via Enum: {0}")
    @EnumSource(AlgoritmosSuportados.class)
    void validaAlgoritmosViaEnum(AlgoritmosSuportados algoritmoEnum) {
        Usuario usuario = new Usuario("Login", "Senha123");

        try {
            boolean sucesso = usuario.validarAcessoComAlgoritmoCustomizado("Senha123", algoritmoEnum.getNome());

            if (algoritmoEnum == AlgoritmosSuportados.SHA_256) {
                assertTrue(sucesso);
            } else {
                assertFalse(sucesso);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            fail("Algoritmos mapeados no Enum nao deveriam lancar excecao de algoritmo nao encontrado.");
        }
    }
}