# Implementação de Dublês com EasyMock

## 1. Análise do Framework EasyMock
O EasyMock é um framework clássico que exige do desenvolvedor o respeito a um ciclo de vida muito estrito para o mock, dividido em 3 fases: **Record** (Gravar expectativas), **Replay** (Reproduzir/Ativar) e **Verify** (Validar).

## 2. Implementação e Captura de Saídas
Abaixo, apresento a codificação de um cenário de teste para um `ProcessadorDePedidos`, utilizando as ferramentas de importação estática do EasyMock.

```java
import static org.easymock.EasyMock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessadorDePedidosEasyMockTest {

    private EstoqueService mockEstoque;
    private ProcessadorDePedidos sut; // System Under Test

    @BeforeEach
    public void prepararAmbiente() {
        // 1. Criação explícita do Mock
        mockEstoque = createMock(EstoqueService.class);
        sut = new ProcessadorDePedidos(mockEstoque);
    }

    @Test
    public void testaBaixaDeEstoqueComSucesso() {
        // FASE 1: RECORD (Gravando a expectativa do comportamento)
        expect(mockEstoque.verificarDisponibilidade("Notebook", 1)).andReturn(true);
        mockEstoque.removerItem("Notebook", 1);
        expectLastCall().once(); // Espera que a remoção seja chamada exatamente 1 vez

        // FASE 2: REPLAY (Travando o mock e deixando-o pronto para uso)
        replay(mockEstoque);

        // AÇÃO (Executando o método real do SUT)
        boolean statusPedido = sut.finalizarCompra("Notebook", 1);

        // FASE 3: VERIFY (Asserções e auditoria)
        assertTrue(statusPedido);
        verify(mockEstoque); // Audita se o mock foi invocado conforme as regras gravadas
    }
}
```

## 3. Recursos Utilizados

* **`createMock(Class)`**: Instancia a interface gerando um dublê vazio.
* **`expect(...).andReturn(...)`**: Ensina ao mock o que ele deve responder caso determinado método seja chamado com aqueles parâmetros exatos.
* **`replay(...)`**: É a chave do EasyMock. Muda o estado do mock de "fase de configuração" para "fase de escuta". Sem isso, o teste lança uma exceção imediata.
* **`verify(...)`**: Checa se todas as previsões registradas no `expect` realmente aconteceram no mundo real durante a execução do método.