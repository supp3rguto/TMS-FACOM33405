# Kata: Rebase Branch (Reescrevendo a História)

## 1. Análise Topológica Inicial
Temos dois ramos (`main` e `uppercase`) que divergiram a partir de um ancestral comum. Ambos possuem *commits* únicos e o histórico atual é ramificado (em forma de Y).

## 2. A Operação de Rebase
Em vez de criar um *commit de merge* que polui o histórico com bifurcações, optamos por reescrever a base da nossa funcionalidade para o topo atualizado do projeto:
```bash
$ git switch uppercase
$ git rebase main
```

**A Engenharia do Rebase (O que acontece por baixo dos panos):**

1. O Git "pausa" e guarda na memória os *commits* exclusivos do ramo `uppercase`.
2. Ele "rebobina" o ramo `uppercase` até o ponto de divergência original.
3. Ele "avança" (fast-forward) o ramo `uppercase` para incorporar todas as atualizações recentes que ocorreram no `main`.
4. Por fim, ele "reaplica" (replay) os *commits* que estavam guardados na memória, um a um, no topo desse novo histórico.

## 3. O Merge Pós-Rebase (Limpo e Linear)

A grande vantagem do `rebase` aparece agora. Como "reescrevemos a história" para parecer que o trabalho em `uppercase` começou *depois* que o `main` foi atualizado, a fusão torna-se trivial.

```bash
$ git switch main
$ git merge uppercase
```

Nenhum conflito e nenhum commit de merge é gerado. O Git realiza um simples **Fast-Forward**, resultando em um histórico perfeitamente linear e limpo, ideal para projetos escaláveis.