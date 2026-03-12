package com.bingo;

import com.bingo.impl.CardImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    @Test
    @DisplayName("Garante que o cartao marca os numeros corretamente")
    void testarMarcacaoDeNumero() {
        CardImpl cartao = new CardImpl(Set.of(10, 20, 30));

        // numero que nao tem no cartao
        boolean sucesso1 = cartao.marcarNumero(15);
        assertFalse(sucesso1);

        // numero que tem no cartao
        boolean sucesso2 = cartao.marcarNumero(20);
        assertTrue(sucesso2);
        assertTrue(cartao.getNumerosMarcados().contains(20));

        // não venceu (ainda)
        assertFalse(cartao.isVencedor());
    }
}