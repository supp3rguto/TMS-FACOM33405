package com.webclient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes de Integracao (Servidor Jetty Real)")
public class WebClientIntegracaoTest {

    private static Server servidorJetty;
    private WebClient cliente = new WebClient();

    @BeforeAll
    static void iniciarServidor() throws Exception {
        servidorJetty = new Server(8090);

        Context contextoOk = new Context(servidorJetty, "/rota-sucesso");
        contextoOk.setHandler(new ManipuladorSucesso());

        Context contextoNotFound = new Context(servidorJetty, "/rota-erro");
        contextoNotFound.setHandler(new ManipuladorErro());

        servidorJetty.setStopAtShutdown(true);
        servidorJetty.start();
    }

    @AfterAll
    static void pararServidor() throws Exception {
        servidorJetty.stop();
    }

    @Test
    @DisplayName("Deve extrair o conteudo de uma requisicao HTTP 200 OK")
    void testaRequisicaoSucesso() throws Exception {
        String resultado = cliente.buscarConteudo(new URL("http://localhost:8090/rota-sucesso"));
        assertEquals("Conteudo Entregue Pelo Servidor", resultado);
    }

    // --- Classes Auxiliares do Servidor ---
    private static class ManipuladorSucesso extends AbstractHandler {
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            OutputStream saida = response.getOutputStream();
            ByteArrayISO8859Writer gravador = new ByteArrayISO8859Writer();
            gravador.write("Conteudo Entregue Pelo Servidor");
            gravador.flush();
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, gravador.size());
            gravador.writeTo(saida);
            saida.flush();
        }
    }

    private static class ManipuladorErro extends AbstractHandler {
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}