# Exploração de Dublês de Teste (Dummy, Fake e Spy)

## 1. Definições e Diferenças Arquiteturais

No ecossistema de testes de software, utilizamos "Dublês" para isolar o Sistema Sob Teste (SUT) de suas dependências externas. Dentre as categorias existentes, destacam-se:

* **Dummy Object (Objeto Fantoche):** É a forma mais rudimentar de dublê. Ele não possui comportamento, não retorna valores úteis e não gera impacto no teste. Sua única utilidade é preencher a lista de parâmetros (assinatura) de um método para que o compilador do Java não reclame.
* **Fake Object (Simulador Funcional):** Diferente do Dummy, o Fake realmente funciona. Ele possui lógica de negócio interna, porém implementada de forma otimizada, "mascarada" e não recomendada para produção. A diferença principal é que o Fake encurta o caminho (ex: usar um banco de dados rápido em memória `H2` no lugar de uma conexão de rede com um banco `Oracle` pesado).
* **Test Spy (Espião):** O Spy atua de forma furtiva. Ele encapsula o objeto real e intercepta as chamadas feitas a ele. Sua diferença primordial em relação aos outros é a capacidade de **telemetria**: o Spy memoriza internamente os metadados da execução (quantas vezes um método foi chamado, quais parâmetros foram injetados) para validação posterior.

## 2. Exemplos Práticos (Implementação)

**A) Exemplo de Fake Object (Processamento de Pagamentos):**
Em vez de bater na API real do cartão de crédito, criamos um Fake que aprova qualquer compra abaixo de 100 reais em memória.
```java
public class GatewayDePagamentoFake implements GatewayPagamento {
    @Override
    public boolean processarCobranca(String cartao, double valor) {
        // Lógica simplificada que simula o serviço real
        return valor < 100.00;
    }
}
```

**B) Exemplo de Test Spy (Auditoria de SMS):**
Queremos garantir que o serviço de alertas está mandando mensagens sem disparar SMS reais de custo elevado.

```java
public class ServicoSmsSpy implements ServicoSms {
    public int totalMensagensEnviadas = 0;
    public String ultimoNumeroDestino = "";

    @Override
    public void enviarSms(String telefone, String texto) {
        // O Spy não manda o SMS, ele apenas espiona e anota a interação
        this.totalMensagensEnviadas++;
        this.ultimoNumeroDestino = telefone;
    }
}

// No teste:
@Test
public void validaAuditoriaSms() {
    ServicoSmsSpy espião = new ServicoSmsSpy();
    AlarmeSistema alerta = new AlarmeSistema(espião);
    alerta.disparar("11999999999", "Servidor caiu!");
    
    assertEquals(1, espião.totalMensagensEnviadas);
    assertEquals("11999999999", espião.ultimoNumeroDestino);
}
```