# Domínio do Framework Mockito

## 1. Análise de Funcionalidades Notáveis
O Mockito tornou-se o padrão da indústria devido à sua sintaxe extremamente fluida (*Sugar Syntax*) baseada no conceito de BDD (*Behavior-Driven Development*). Suas características mais atraentes são:
* **Ausência do estado de Replay:** Diferente do EasyMock, não precisamos "travar" o mock. Ele aceita o treinamento (`when`) de forma dinâmica a qualquer momento.
* **Integração com Anotações:** O uso de `@ExtendWith(MockitoExtension.class)`, associado aos decoradores `@Mock` e `@InjectMocks`, elimina dezenas de linhas de configuração braçal (Boilerplate).
* **Flexibilidade (Lenient):** O Mockito não explode o teste caso um mock configurado acabe não sendo utilizado, o que diminui a fragilidade dos testes diante de refatorações de código.

## 2. Exemplos de Aplicação (Três Abordagens Distintas)

**Exemplo 1: Treinamento de Retorno (Stubbing de Valores)**
Aqui simulamos um cenário em que forçamos uma API de cotação a retornar um valor específico, sem acessar a internet.
```java
@Test
void validaConversaoDeMoeda() {
    CotacaoAPI mockApi = mock(CotacaoAPI.class);
    // Dizendo ao Mockito o que retornar
    when(mockApi.getValorDolarHoje()).thenReturn(5.00);
    
    ConversorFinanceiro conversor = new ConversorFinanceiro(mockApi);
    assertEquals(50.00, conversor.realParaDolar(10.00));
}
```

**Exemplo 2: Auditoria de Interação (Spying / Verification)**
Utilizamos o Mockito para checar se o sistema disparou a ordem de deleção corretamente no banco de dados para um usuário inativo.

```java
@Test
void garanteDelecaoDeUsuarioInativo() {
    RepositorioUsuario mockRepo = mock(RepositorioUsuario.class);
    ServicoLimpeza limpeza = new ServicoLimpeza(mockRepo);
    
    limpeza.expurgarConta(99);
    
    // O Mockito audita silenciosamente se o método 'deletar' foi acionado 1 vez com o ID 99
    verify(mockRepo, times(1)).deletarPorId(99);
}
```

**Exemplo 3: Simulação de Anomalias e Exceções**
Neste cenário, avaliamos se a nossa regra de negócio consegue lidar de forma elegante com uma queda abrupta no servidor, sem quebrar o sistema inteiro.

```java
@Test
void validaTratamentoDeServidorForaDoAr() {
    ServicoDeNuvem mockNuvem = mock(ServicoDeNuvem.class);
    
    // Forçando o dublê a simular um Crash catastrófico
    when(mockNuvem.fazerUpload("arquivo.pdf")).thenThrow(new TimeoutException("Timeout na Nuvem"));
    
    ControladorDeArquivos controlador = new ControladorDeArquivos(mockNuvem);
    
    // Garantindo que a exceção estourou e foi propagada conforme esperado
    assertThrows(TimeoutException.class, () -> {
        controlador.salvarSeguro("arquivo.pdf");
    });
}
```