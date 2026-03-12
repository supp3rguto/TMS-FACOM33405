# Tipos de Testes, Técnicas e Arsenal de Ferramentas

## 1. Avaliações Exploratórias
* Essa matriz de teste objetiva investigar as nuances orgânicas do comportamento da aplicação.
* **Modelos e Abordagens:** Emprega-se fortemente o fluxo baseado em missões, metodizado pelo SBTM (*Session-Based Test Management*), que aliado a parâmetros heurísticos cognitivos (como o guia clássico de Nielsen), norteia a exploração cega do usuário.
* **Apoio Tecnológico:** O mapeamento mental destas sessões ocorre através de plataformas dedicadas como XMind, Jira, TestRail e ferramentas rápidas de log como o Rapid Reporter.

## 2. Validações Estruturais e de Integração
* **Nível de Unidade:** Fragmentos de código sofrem isolamento forçado com *Mocks* e *Stubs* para verificação lógica em arcabouços como JUnit 5 (ambiente Java) ou pytest (ambiente Python).
* **Comunicação e APIs:** A integridade de fronteiras e persistência é simulada sem comprometer bancos de dados físicos, adotando o encapsulamento do Testcontainers. Barramentos e rotas HTTP são estressados através de engines analíticas como o Newman associado ao Postman.
* **E2E e Interações de Sistema:** A orquestração profunda do DOM (Document Object Model) e a navegação real são assumidas por robôs modernos, destacando-se bibliotecas como Cypress, Playwright e o clássico Selenium.

## 3. Comportamento e Aceite
* O trâmite final para garantir que o software entrega o valor de negócio concebido é feito utilizando pilares em BDD (*Behavior-Driven Development*).
* Os requisitos negociais são traduzidos por meio da gramática Gherkin (na estrutura Dado/Quando/Então) e postos em execução fluida atrelados ao motor de automação Cucumber.