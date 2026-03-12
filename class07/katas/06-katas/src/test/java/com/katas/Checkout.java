package com.katas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.HashSet;

public class Checkout {

    private final Map<String, PricingRule> tabelaDePrecos;
    private final List<String> esteiraDeItens;

    public Checkout(Map<String, PricingRule> tabelaDePrecos) {
        this.tabelaDePrecos = tabelaDePrecos;
        this.esteiraDeItens = new ArrayList<>();
    }

    public void passarItem(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("O leitor falhou ao ler o código de barras.");
        }
        if (!tabelaDePrecos.containsKey(sku)) {
            throw new IllegalArgumentException("Produto não cadastrado no sistema: " + sku);
        }

        esteiraDeItens.add(sku);
    }

    public int fecharConta() {
        int valorFinal = 0;
        Set<String> itensUnicos = new HashSet<>(esteiraDeItens);

        for (String item : itensUnicos) {
            int frequenciaCart = Collections.frequency(esteiraDeItens, item);
            PricingRule regra = tabelaDePrecos.get(item);
            valorFinal += regra.simularCusto(frequenciaCart);
        }

        return valorFinal;
    }

    public void esvaziar() {
        this.esteiraDeItens.clear();
    }
}