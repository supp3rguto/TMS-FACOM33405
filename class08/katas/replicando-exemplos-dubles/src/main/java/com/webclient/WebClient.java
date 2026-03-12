package com.webclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {

    public String buscarConteudo(URL urlRequisicao) {
        StringBuilder respostaConteudo = new StringBuilder();
        try {
            HttpURLConnection conexao = (HttpURLConnection) urlRequisicao.openConnection();
            conexao.setDoInput(true);
            InputStream fluxoDados = conexao.getInputStream();

            byte[] buffer = new byte[2048];
            int bytesLidos;
            while ((bytesLidos = fluxoDados.read(buffer)) != -1) {
                respostaConteudo.append(new String(buffer, 0, bytesLidos));
            }
            fluxoDados.close();

        } catch (IOException erro) {
            throw new RuntimeException("Falha ao capturar conteudo da web", erro);
        }
        return respostaConteudo.toString();
    }
}