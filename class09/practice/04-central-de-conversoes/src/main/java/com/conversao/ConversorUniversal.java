package com.conversao;

public class ConversorUniversal {

    // --- CONVERSÃO DE COMPRIMENTO (Base: Metros) ---
    public double converterComprimento(double valor, String origem, String destino) {
        double valorEmMetros = converterParaMetros(valor, origem);
        return converterDeMetros(valorEmMetros, destino);
    }

    private double converterParaMetros(double valor, String unidade) {
        switch (unidade.toLowerCase()) {
            case "m": return valor;
            case "km": return valor * 1000.0;
            case "cm": return valor / 100.0;
            case "mi": return valor * 1609.34; // 1 milha = 1609.34 m
            case "in": return valor * 0.0254;  // 1 polegada = 0.0254 m
            default: throw new IllegalArgumentException("Unidade de comprimento não suportada: " + unidade);
        }
    }

    private double converterDeMetros(double metros, String unidade) {
        switch (unidade.toLowerCase()) {
            case "m": return metros;
            case "km": return metros / 1000.0;
            case "cm": return metros * 100.0;
            case "mi": return metros / 1609.34;
            case "in": return metros / 0.0254;
            default: throw new IllegalArgumentException("Unidade de comprimento não suportada: " + unidade);
        }
    }

    // --- CONVERSÃO DE PESO (Base: Gramas) ---
    public double converterPeso(double valor, String origem, String destino) {
        double valorEmGramas = converterParaGramas(valor, origem);
        return converterDeGramas(valorEmGramas, destino);
    }

    private double converterParaGramas(double valor, String unidade) {
        switch (unidade.toLowerCase()) {
            case "g": return valor;
            case "kg": return valor * 1000.0;
            case "lb": return valor * 453.592; // 1 libra = 453.592 g
            case "oz": return valor * 28.3495; // 1 onça = 28.3495 g
            default: throw new IllegalArgumentException("Unidade de peso não suportada: " + unidade);
        }
    }

    private double converterDeGramas(double gramas, String unidade) {
        switch (unidade.toLowerCase()) {
            case "g": return gramas;
            case "kg": return gramas / 1000.0;
            case "lb": return gramas / 453.592;
            case "oz": return gramas / 28.3495;
            default: throw new IllegalArgumentException("Unidade de peso não suportada: " + unidade);
        }
    }

    // --- CONVERSÃO DE TEMPERATURA ---
    public double converterTemperatura(double valor, String origem, String destino) {
        if (origem.equalsIgnoreCase(destino)) return valor;
        
        // Primeiro converte qualquer coisa para Celsius
        double emCelsius = 0.0;
        switch (origem.toUpperCase()) {
            case "C": emCelsius = valor; break;
            case "F": emCelsius = (valor - 32) * 5 / 9; break;
            case "K": emCelsius = valor - 273.15; break;
            default: throw new IllegalArgumentException("Unidade de temperatura inválida: " + origem);
        }

        // Depois de Celsius para o destino final
        switch (destino.toUpperCase()) {
            case "C": return emCelsius;
            case "F": return (emCelsius * 9 / 5) + 32;
            case "K": return emCelsius + 273.15;
            default: throw new IllegalArgumentException("Unidade de temperatura inválida: " + destino);
        }
    }
}