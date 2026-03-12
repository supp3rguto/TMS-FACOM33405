# Revisitando a Calculadora 01

## 1. Mapeamento de Novos Casos de Teste (Soma)
Com base na análise de requisitos (faixas de valores, extremos, exceções e decimais), a nova bateria de testes deve contemplar os seguintes cenários:

* **Cenários Nominais (Valores Válidos):**
  * `deveSomarDoisNumerosPositivos`: Valida a operação elementar (ex: 15 + 25 = 40).
  * `deveSomarDoisNumerosNegativos`: Valida a soma de dívidas/valores negativos (ex: -10 + -5 = -15).
  * `deveSomarComElementoNeutro`: Valida o comportamento da adição com o dígito zero.
* **Cenários de Fronteira (Valores Extremos):**
  * `deveLancarExcecaoEmOverflow`: Testa a soma de `Integer.MAX_VALUE + 1`. O sistema deve ser testado para não estourar a pilha silenciosamente, mas sim lançar uma `ArithmeticException`.
  * `deveLancarExcecaoEmUnderflow`: Testa a soma de `Integer.MIN_VALUE - 1`.
* **Cenários de Ponto Flutuante (Decimais):**
  * `deveSomarNumerosFracionados`: Testa valores monetários ou exatos, como `2.75 + 3.25 = 6.00`. Este teste obriga a uma refatorização da arquitetura do método.

## 2. Proposta de Refatorização (Testes e Código Funcional)

**Refatorização da Suíte de Testes:**
Em vez de sobrecarregar a classe de testes com dezenas de métodos usando o clássico `assertEquals`, a abordagem ideal é utilizar o **@ParameterizedTest** em conjunto com o **@CsvSource**. Isso permite agrupar todas as variações matemáticas num único método dinâmico. Adicionalmente, a adoção dos casadores **Hamcrest** (ex: `assertThat(resultado, is(equalTo(esperado)))`) eleva a legibilidade do teste, aproximando-o da linguagem natural.

**Refatorização do Código Funcional:**
Para que os testes de decimais passem (fase Green do TDD), a assinatura clássica `int somar(int a, int b)` tornar-se-á obsoleta. O código de produção deve ser refatorado para utilizar o tipo `double` (ou idealmente `BigDecimal` em cenários financeiros), passando a ser `double somar(double a, double b)`. Além disso, devem ser adicionados blocos `try-catch` ou verificações de limite (ex: `Math.addExact`) para lidar preventivamente com o problema de Overflow identificado nos testes de fronteira.