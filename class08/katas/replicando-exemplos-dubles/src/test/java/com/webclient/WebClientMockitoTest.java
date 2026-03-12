package com.webclient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Isolados (Usando Mockito)")
public class WebClientMockitoTest {

    @Mock
    private URL urlMock;

    @Mock
    private HttpURLConnection conexaoMock;

    @Test
    @DisplayName("Garante que o WebClient consegue ler dados usando Mocks do Mockito")
    void testaLeituraViaMockito() throws IOException {
        // 1. Arrange (Preparação)
        String textoSimulado = "Retorno Simulado pelo Mockito com Sucesso";
        InputStream fluxoSimulado = new ByteArrayInputStream(textoSimulado.getBytes());

        // Ensina os Mocks a comportarem-se como queremos (Stubbing)
        // Quando o openConnection() for chamado na URL mockada, devolvemos a conexão mockada
        when(urlMock.openConnection()).thenReturn(conexaoMock);

        // quando pedirem o InputStream da conexão mockada, devolvemos o nosso texto falso
        when(conexaoMock.getInputStream()).thenReturn(fluxoSimulado);

        WebClient cliente = new WebClient();

        // 2. act (ação), no caso passa a url falsa (mockada) para o método real
        String resultado = cliente.buscarConteudo(urlMock);

        // 3. assert - verificação
        assertEquals(textoSimulado, resultado);

        // EXTRA: com o mockito podemos verificar se os métodos foram realmente invocados pelo código de produção (spying)
        verify(urlMock).openConnection();
        verify(conexaoMock).setDoInput(true);
        verify(conexaoMock).getInputStream();
    }
}