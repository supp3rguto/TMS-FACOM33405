package com.webclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class StubHttpURLConnection extends HttpURLConnection {
    private boolean leituraPermitida = true;

    public StubHttpURLConnection(URL url) {
        super(url);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!leituraPermitida) {
            throw new ProtocolException("Leitura bloqueada nas configuracoes do stub!");
        }
        // Este é o texto "falso" que o nosso Dublê vai devolver para o teste
        String textoSimulado = "Retorno Simulado pelo Stub com Sucesso";
        return new ByteArrayInputStream(textoSimulado.getBytes());
    }

    @Override
    public void connect() throws IOException { }

    @Override
    public void disconnect() { }

    @Override
    public boolean usingProxy() {
        return false;
    }
}