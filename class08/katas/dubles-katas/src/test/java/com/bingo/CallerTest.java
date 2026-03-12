package com.bingo;

import com.bingo.impl.CallerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallerTest {

    @Test
    @DisplayName("Garante que o Caller mantem o historico de numeros sorteados")
    void testarHistoricoDoCaller() {
        CallerImpl caller = new CallerImpl();

        int numero1 = caller.sortearNumero();
        int numero2 = caller.sortearNumero();

        assertEquals(2, caller.getHistoricoNumeros().size());
        assertTrue(caller.getHistoricoNumeros().contains(numero1));
        assertTrue(caller.getHistoricoNumeros().contains(numero2));
    }
}