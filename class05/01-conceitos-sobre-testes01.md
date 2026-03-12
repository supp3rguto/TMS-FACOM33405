# Conceitos sobre Testes (Parte 1)

## 1. Mapeamento Prático de Testes
A aplicação prática dos conceitos de validação foi exercitada através da construção de conjuntos de testes automatizados utilizando a linguagem Python e o framework `pytest`. 
* **Projetos Analisados:** As rotinas de validação envolveram os projetos *Back to the Bank* (asserções de saudações textuais), *Refueling* (validação de entradas fracionárias e tratamento de exceções), *Re-requesting a Vanity Plate* (verificação de regras de negócio em placas veiculares, como limites de caracteres e restrições de formatação), e *Testing my twttr* (tratamento de inserções em caixa alta, baixa e símbolos de pontuação).

## 2. Tradução para Conceitos Teóricos
Os scripts desenvolvidos implementaram abordagens diretas para blindar o código contra estados inconsistentes:
* **Tratamento de Exceções Críticas:** Foram codificadas rotinas como a `test_zero_division()` usando o construto `pytest.raises(ZeroDivisionError)` para antecipar falhas abruptas no tempo de execução ao forçar a entrada "1/0".
* **Prevenção de Erros de Valor:** A função `test_value_error()` foi instanciada para capturar anomalias na tipagem ou no formato esperado, isolando comportamentos indesejados caso o usuário injetasse textos alfabéticos ("cat/dog") em calculadoras fracionárias.
* **Validação do Caminho Feliz:** A assertividade do comportamento sistêmico esperado foi garantida na rotina `test_entrada_correta()`, cravando que frações logicamente válidas (ex: "3/4") sempre resultarão na conversão estipulada pelas regras de negócio ("75%").