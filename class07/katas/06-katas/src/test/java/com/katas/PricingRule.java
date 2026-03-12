package com.katas;

public class PricingRule {

    private final int precoNormal;
    private final int quantidadeParaDesconto;
    private final int precoComDesconto;
    private final boolean possuiPromocao;

    public PricingRule(int precoNormal) {
        this.precoNormal = precoNormal;
        this.quantidadeParaDesconto = 0;
        this.precoComDesconto = 0;
        this.possuiPromocao = false;
    }

    public PricingRule(int precoNormal, int quantidadeParaDesconto, int precoComDesconto) {
        this.precoNormal = precoNormal;
        this.quantidadeParaDesconto = quantidadeParaDesconto;
        this.precoComDesconto = precoComDesconto;
        this.possuiPromocao = true;
    }

    public int simularCusto(int quantidadeComprada) {
        if (!possuiPromocao) {
            return quantidadeComprada * precoNormal;
        }

        int pacotesPromocionais = quantidadeComprada / quantidadeParaDesconto;
        int unidadesAvulsas = quantidadeComprada % quantidadeParaDesconto;

        return (pacotesPromocionais * precoComDesconto) + (unidadesAvulsas * precoNormal);
    }
}