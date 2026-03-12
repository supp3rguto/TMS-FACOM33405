# Aula 11 - Práticas e Ferramentas de Refatoração

Este documento apresenta a resolução das atividades práticas envolvendo a aplicação de refatorações de código, comparativo entre IDEs e a resolução de *Code Smells* em um projeto de Folha de Pagamento.

---

## Atividade 1: Comparativo de IDEs e Suporte a Refatoração

Foi realizada uma pesquisa sobre três das IDEs mais populares para desenvolvimento Java: **IntelliJ IDEA**, **Eclipse** e **NetBeans**. A tabela abaixo categoriza o suporte nativo dessas ferramentas às refatorações mais comuns da orientação a objetos:

| Categoria | Refatoração | IntelliJ IDEA | Eclipse | NetBeans |
| :--- | :--- | :---: | :---: | :---: |
| **Composição de Métodos** | *Extract Method* | Excelente | Excelente | Muito Bom |
| | *Inline Method* | Excelente | Muito Bom | Bom |
| **Movimentação de Recursos** | *Move Method / Class* | Excelente | Excelente | Muito Bom |
| | *Extract Class* | Muito Bom | Bom | Manual/Limitado |
| **Organização de Dados** | *Extract Constant* | Excelente | Excelente | Excelente |
| | *Rename* | Impecável | Excelente | Excelente |

---

## Atividade 2: Projeto de Exemplo e Code Smells

Para fugir dos exemplos comuns de locadora, criei um domínio de **Folha de Pagamento de Funcionários**. A classe original possuía os seguintes *Code Smells*:

1. **Long Method (Método Longo):** O método `calcularSalarioLiquido()` concentrava o cálculo de horas extras, descontos de INSS, imposto de renda e vales em um único bloco.
2. **Magic Numbers (Números Mágicos):** Taxas tributárias como `0.275` (IR) e `0.11` (INSS) estavam espalhadas pelo código sem explicação.
3. **Switch Statements:** O código usava `switch/case` para verificar o tipo do funcionário (CLT, Estagiário, PJ) e aplicar regras diferentes.

**Código com Smells (Antes da Refatoração):**
```java
public class CalculadoraSalarial {
    public double calcularSalarioLiquido(int tipoFuncionario, double salarioBase) {
        double imposto = 0;
        switch (tipoFuncionario) {
            case 1: // CLT
                imposto = (salarioBase * 0.11) + (salarioBase * 0.275);
                break;
            case 2: // PJ
                imposto = salarioBase * 0.06;
                break;
            case 3: // Estagiário
                imposto = 0.0;
                break;
        }
        return salarioBase - imposto;
    }
}
```

---

## Atividade 3: Cenários de Teste

Para garantir que a refatoração não alterasse o comportamento, os testes automatizados com JUnit foram criados **antes** da limpeza do código:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraSalarialTest {
    @Test
    void testaDescontoClt() {
        CalculadoraSalarial calc = new CalculadoraSalarial();
        // 1000 - (110 + 275) = 615.0
        assertEquals(615.0, calc.calcularSalarioLiquido(1, 1000.0)); 
    }

    @Test
    void testaDescontoEstagiario() {
        CalculadoraSalarial calc = new CalculadoraSalarial();
        // Estagiário não tem desconto
        assertEquals(1000.0, calc.calcularSalarioLiquido(3, 1000.0)); 
    }
}
```

*Os testes foram executados e passaram com sucesso (Barra Verde).*

---

## Atividade 4: Refatoração Manual

Tentar resolver o problema dos *Magic Numbers* (como o `0.275`) e o *Extract Method* puramente na mão exigiu as seguintes etapas:

1. Buscar no código onde o valor `0.275` era usado.
2. Criar manualmente um `private static final double TAXA_IR = 0.275;` no topo da classe.
3. Apagar o valor antigo e digitar o nome da constante.
4. Recortar o bloco do switch e jogar para um novo método `calcularImposto()`.
**Comentário sobre o processo:** É um procedimento lento, mecânico e muito propenso a erros de digitação (typos), exigindo a execução constante dos testes para garantir que nenhuma variável foi apagada por engano.

---

## Atividade 5: Refatoração Automática com a 1ª IDE (IntelliJ IDEA)

O processo de remover *Code Smells* utilizando os atalhos do IntelliJ foi drasticamente mais rápido e seguro:

1. **Extract Constant:** Ao selecionar o número `0.11` e pressionar `Ctrl+Alt+C`, a IDE sugeriu imediatamente a criação da constante `TAXA_INSS` e substituiu todas as ocorrências do arquivo sozinha.
2. **Extract Method:** Selecionando o bloco do `switch` e pressionando `Ctrl+Alt+M`, a IDE extraiu o método, detectou que precisaria receber o `tipoFuncionario` como parâmetro e retornar o valor do imposto, tudo sem quebrar a compilação.
**Resultado:** Os testes foram rodados em seguida e continuaram passando perfeitamente.

---

## Atividade 6: Refatoração Automática com a 2ª IDE (Eclipse) e Comparativo

Repeti o mesmo processo na IDE Eclipse:

1. Para extrair a constante, foi necessário selecionar o valor, clicar com o botão direito -> `Refactor` -> `Extract Constant` (ou usar o atalho `Alt+Shift+T`).
2. O Eclipse abre uma janela modal (*wizard*) para que o nome e a visibilidade da constante sejam definidos.

**Comparativo:** Ambas as ferramentas garantem a segurança do código sem quebrar a lógica. Contudo, o **Eclipse** interrompe o fluxo de pensamento do desenvolvedor com caixas de diálogo (wizards) que exigem confirmação. O **IntelliJ IDEA** faz a refatoração "in-place" (direto no editor de texto, sem pop-ups), mostrando-se superior em fluidez, agilidade e simplicidade durante a limpeza do código.