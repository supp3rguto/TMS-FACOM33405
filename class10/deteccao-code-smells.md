# Aula 10 - Detecção de Code Smells e Refatoração

Esta documentação compila as análises de *Code Smells* em projetos próprios e de código aberto, além de um comparativo de ferramentas de detecção automática.

---

## 1. Code Smells em Projetos Anteriores (Sistemas POO)

Analisando o código desenvolvido na disciplina de Programação Orientada a Objetos I em semestres anteriores para um "Sistema de Gestão de Clínica Médica", foram identificados os seguintes problemas de design:

* **Long Method (Método Longo):** O método `agendarConsulta()` na classe `AtendimentoService` possuía mais de 90 linhas. Ele misturava lógicas distintas: verificava a disponibilidade de agenda do médico, validava o plano de saúde do paciente, calculava o valor do atendimento, salvava a consulta no banco de dados e ainda disparava um e-mail de confirmação.
* **Duplicated Code (Código Duplicado):** A lógica de cálculo de idade a partir da data de nascimento e a formatação/validação de números de telefone com DDD estavam copiadas e coladas em três classes diferentes: `Paciente`, `Medico` e `Funcionario`.
* **Feature Envy (Inveja dos Dados):** A classe `Faturamento` possuía um método `calcularValorFinal()` que acessava repetidamente os atributos internos da classe `Consulta` (usando `consulta.getValorBase()`, `consulta.getPercentualPlano()`, `consulta.getDesconto()`) para realizar seus cálculos financeiros, evidenciando que o comportamento estava no lugar errado.

### Propostas de Melhoria
* **Para o Long Method:** Aplicar a refatoração **Extract Method**. O ideal é quebrar o `agendarConsulta()` em métodos privados menores, como `verificarDisponibilidadeMedico()` e `calcularCustoConsulta()`. O envio do e-mail de confirmação poderia ser extraído para uma classe dedicada a notificações, aplicando **Extract Class**.
* **Para o Duplicated Code:** Centralizar as lógicas repetidas criando *Value Objects* (como `Telefone`) ou classes utilitárias de manipulação de datas, respeitando o princípio DRY (Don't Repeat Yourself) e garantindo que a validação exista em apenas um lugar.
* **Para a Feature Envy:** Aplicar o **Move Method**. A lógica de cálculo do valor final da consulta deve ser movida para dentro da própria classe `Consulta` (ex: `consulta.calcularValorFinal()`). Assim, os dados e o comportamento que operam sobre eles ficam encapsulados na mesma classe, aumentando a coesão.

---

## 2. Code Smells em Projetos Populares no GitHub

Analisando repositórios open-source conceituados no ecossistema Java, é comum encontrar *Smells* que foram refatorados pela comunidade:

### Projeto: JUnit 5 (Framework de Testes)
* **Code Smell Detectado:** **Long Parameter List**. Construtores internos e métodos de descoberta de testes recebiam muitos parâmetros (configurações de engine, filtros, etc.), dificultando a leitura.
* **Solução:** Os desenvolvedores utilizaram a técnica **Introduce Parameter Object** e padrões *Builder*, encapsulando os dados em classes de configuração específicas (ex: `DiscoveryRequest`).

### Projeto: Apache Commons Lang
* **Code Smell Detectado:** **God Class / Large Class** e **Long Method**. A classe `StringUtils` tornou-se um grande aglomerado de manipulação de strings ao longo do tempo.
* **Solução:** O projeto ataca esses problemas frequentemente diminuindo a complexidade ciclomática dos métodos internos (ex: verificações de nulos) e delegando sub-partes lógicas para funções privadas menores com nomes descritivos usando **Extract Method**.

---

## 3. Ferramentas de Detecção Automática

Para auxiliar na manutenção da qualidade do código, existem diversas ferramentas de análise estática. Abaixo, um comparativo entre três opções populares no mercado Java:

| Ferramenta | Foco Principal | Exemplos de Code Smells Detectados | Integração IDE / CI-CD |
| :--- | :--- | :--- | :--- |
| **SonarQube** | *Continuous Inspection* (métricas de qualidade, bugs, vulnerabilidades e *Smells*). | *God Class*, *Long Method*, *Duplicated Code*, *Feature Envy*, Complexidade Ciclomática alta. | Excelente. Integra-se com Jenkins, GitHub Actions e possui o SonarLint para IDEs. |
| **PMD** | Encontrar problemas de programação comuns e ineficiências lógicas. | *Dead Code*, Variáveis não utilizadas, *Empty Catch Blocks*, Construtores Gigantes. | Muito boa. Usado como plugin no Maven/Gradle, Eclipse e IntelliJ. |
| **Checkstyle** | Garantir o padrão de estilo de codificação (convenções de sintaxe e formatação). | *Magic Numbers*, Listas de importação imensas, Comprimento excessivo de linha. | Excelente como plugin no Maven (capaz de quebrar a *build* se a regra não for cumprida). |

**Conclusão:** O Checkstyle é focado em formatação e o PMD em ineficiências locais. Para uma análise arquitetural profunda capaz de encontrar *Smells* complexos de orientação a objetos, o SonarQube é a ferramenta mais completa.