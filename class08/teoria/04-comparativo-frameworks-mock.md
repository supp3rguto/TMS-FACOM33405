# Análise Comparativa de Frameworks (EasyMock vs JMock vs Mockito)

Com base nas pesquisas e implementações realizadas, consolido abaixo o comparativo estrutural de como cada framework aborda a criação de Dublês de Teste.

## 1. EasyMock
* **Paradigma:** Baseado fortemente em registro e reprodução estrita (Record-Replay-Verify).
* **Facilidade de Implementação:** Requer importações estáticas massivas e gera um código de teste muito mais burocrático e verboso. 
* **Criação de Dublês:** O mock é gerado localmente invocando o método construtor `createMock(Classe.class)`. Se esquecermos de invocar o comando `replay()` no meio do teste, a suíte inteira falha criticamente. É um framework que "cobra" muito do desenvolvedor.

## 2. JMock
* **Paradigma:** Estruturado ao redor de uma variável de contexto onipresente chamada `Mockery`.
* **Facilidade de Implementação:** É conhecido por introduzir blocos semânticos pesados chamados de *Expectations* (Expectativas). O desenvolvedor precisa abrir um bloco de chaves customizado para declarar as regras.
* **Criação de Dublês:** Todos os dublês não nascem por conta própria, eles precisam ser gerados a partir do cordão umbilical do contexto (ex: `context.mock(Interface.class)`). É visualmente ruidoso e engessado em comparação com tecnologias mais modernas.

## 3. Mockito (O Padrão da Indústria)
* **Paradigma:** Baseado em anotações nativas de Injeção e na linguagem Gherkin disfarçada (Given/When/Then).
* **Facilidade de Implementação:** É imensamente superior aos concorrentes. Ele oculta toda a complexidade técnica utilizando injeção via `@ExtendWith(MockitoExtension.class)`.
* **Criação de Dublês:** Facilita a modelagem em nível de atributo de classe através de anotações limpas como `@Mock` e `@Spy`. 
* **O Grande Diferencial:** Ele destrói o conceito de "Replay" existente no EasyMock. Você configura o dublê (`when`) e simplesmente o usa. Ele foca muito mais na verificação da interação após o fato ocorrido (`verify`), permitindo a escrita de testes altamente legíveis, robustos e adaptáveis à manutenção de código a longo prazo.