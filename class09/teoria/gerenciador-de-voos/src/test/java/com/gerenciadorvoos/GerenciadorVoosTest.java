package com.gerenciadorvoos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD - Sistema de Gerenciamento de Voos")
public class GerenciadorVoosTest {

    @Test
    @DisplayName("Garante que o sistema bloqueia o duplo embarque do mesmo passageiro")
    void deveBloquearEmbarqueDuplo() {
        Flight voo = new EconomyFlight("TAP100", 50);
        Passenger guto = new Passenger("Guto", false);

        assertTrue(voo.adicionarPassageiro(guto), "Primeiro embarque deve ser permitido");

        // tentativa de duplicar
        assertFalse(voo.adicionarPassageiro(guto), "Segundo embarque do mesmo passageiro deve ser bloqueado");
        assertEquals(1, voo.getPassageiros().size(), "A lista não pode conter registros duplicados");
    }

    @Test
    @DisplayName("Impede a venda de bilhetes caso o voo atinja a lotação máxima")
    void deveRespeitarLotacaoMaxima() {
        Flight miniVoo = new BusinessFlight("TAP200", 2);
        Passenger vip1 = new Passenger("Elon", true);
        Passenger vip2 = new Passenger("Bill", true);
        Passenger vip3 = new Passenger("Guto", true);

        miniVoo.adicionarPassageiro(vip1);
        miniVoo.adicionarPassageiro(vip2);

        // voo já tem 2 passageiros. O terceiro deve ser bloqueado
        assertFalse(miniVoo.adicionarPassageiro(vip3), "Deve rejeitar passageiro excedente");
        assertEquals(2, miniVoo.getPassageiros().size(), "O limite do voo foi violado");
    }

    @Test
    @DisplayName("Garante regras do Voo Economico: Aceita todos, mas não remove VIPs")
    void testaRegrasVooEconomico() {
        Flight vooEconomico = new EconomyFlight("ECO99", 100);
        Passenger comum = new Passenger("João", false);
        Passenger vip = new Passenger("Guto", true);

        assertTrue(vooEconomico.adicionarPassageiro(comum));
        assertTrue(vooEconomico.adicionarPassageiro(vip));

        assertTrue(vooEconomico.removerPassageiro(comum), "Deveria conseguir remover passageiro comum");
        assertFalse(vooEconomico.removerPassageiro(vip), "Não deveria conseguir remover passageiro VIP de voo economico");
    }
}