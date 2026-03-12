package com.webclient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes Isolados (Usando Stub Personalizado)")
public class WebClientStubTest {

    @BeforeAll
    static void configurarFabricaDeStubs() {
        // intercepta todas as conexões de url do java e injeta o nosso duble
        URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
    }

    @Test
    @DisplayName("Garante que o WebClient consegue ler dados de um Duble (Stub)")
    void testaLeituraViaStub() throws Exception {
        WebClient cliente = new WebClient();
        String resultado = cliente.buscarConteudo(new URL("http://site-ficticio.com"));

        assertEquals("Retorno Simulado pelo Stub com Sucesso", resultado);
    }

    // classes auxiliares para interceptar a conexão
    private static class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }
    }

    private static class StubHttpURLStreamHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL url) {
            return new StubHttpURLConnection(url);
        }
    }
}