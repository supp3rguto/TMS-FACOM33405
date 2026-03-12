package com.katas;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Testes do Caixa de Supermercado (Checkout Kata)")
public class CheckoutTest {

    private static Map<String, PricingRule> bancoDeDados;
    private Checkout pdv;

    @BeforeAll
    static void carregarSistema() {
        // trocando os Skus e preços do código do seu amigo para X, Y, W, Z
        bancoDeDados = new HashMap<>();
        bancoDeDados.put("X", new PricingRule(40, 3, 100)); // 1 por 40, 3 por 100
        bancoDeDados.put("Y", new PricingRule(60, 2, 90));  // 1 por 60, 2 por 90
        bancoDeDados.put("W", new PricingRule(10));         // sem promo
        bancoDeDados.put("Z", new PricingRule(5));          // sem promo
    }

    @BeforeEach
    void abreCaixa() {
        pdv = new Checkout(bancoDeDados);
    }

    @Test
    @DisplayName("Garante que começar sem passar produtos gera R$ 0")
    void testaContaZerada() {
        assertEquals(0, pdv.fecharConta());
    }

    @Test
    @DisplayName("Soma direta de itens avulsos")
    void testaItensSimples() {
        pdv.passarItem("W"); // 10
        pdv.passarItem("Z"); // 5
        assertEquals(15, pdv.fecharConta());
    }

    @Test
    @DisplayName("Aciona promoção por limite exato (Produto X)")
    void testaPromocaoFechada() {
        pdv.passarItem("X");
        pdv.passarItem("X");
        pdv.passarItem("X");
        assertEquals(100, pdv.fecharConta()); // pega a regra de 3 por 100
    }

    @Test
    @DisplayName("Aciona promoção mais um excedente (Produto Y)")
    void testaPromocaoMaisAvulso() {
        pdv.passarItem("Y"); // 60
        pdv.passarItem("Y"); // +30 (fecha promo de 90)
        pdv.passarItem("Y"); // +60 avulso
        assertEquals(150, pdv.fecharConta());
    }

    @Test
    @DisplayName("Múltiplas baterias de promoções misturadas")
    void testaCarrinhoLotado() {
        pdv.passarItem("X");
        pdv.passarItem("Y");
        pdv.passarItem("X");
        pdv.passarItem("Z"); // 5
        pdv.passarItem("Y"); // Promo Y fecha aqui (90)
        pdv.passarItem("X"); // Promo X fecha aqui (100)

        // total = 100(X) + 90(Y) + 5(Z) = 195
        assertEquals(195, pdv.fecharConta());
    }

    @Test
    @DisplayName("Tratamento de código de barras sujo ou nulo")
    void testaLeituraInvalida() {
        assertThrows(IllegalArgumentException.class, () -> pdv.passarItem(""));
        assertThrows(IllegalArgumentException.class, () -> pdv.passarItem(null));
    }

    @Test
    @DisplayName("Alerta quando tenta passar algo fora do sistema")
    void testaProdutoFantasma() {
        assertThrows(IllegalArgumentException.class, () -> pdv.passarItem("TOMATE"));
    }
}