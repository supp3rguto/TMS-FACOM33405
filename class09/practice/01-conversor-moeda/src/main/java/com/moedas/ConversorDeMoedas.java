package com.moedas;

public class ConversorDeMoedas {

    private ServicoDeCambio servicoExterno; // Dependência que será mockada

    public ConversorDeMoedas(ServicoDeCambio servicoExterno) {
        this.servicoExterno = servicoExterno;
    }

    public double converter(double valor, String moedaOrigem, String moedaDestino) {
        if (moedaOrigem.equalsIgnoreCase(moedaDestino)) {
            return valor;
        }

        // Obtém a taxa dinâmica usando a interface externa
        double taxa = servicoExterno.obterTaxaDeCambio(moedaOrigem.toUpperCase(), moedaDestino.toUpperCase());

        if (taxa <= 0) {
            throw new IllegalArgumentException("Taxa de cambio indisponivel ou invalida.");
        }

        return valor * taxa;
    }
}