# Kata: Reset (Manipulação de Ponteiros)

## 1. O Estado Inicial
[cite_start]Antes de manipularmos o histórico, tínhamos três commits lineares empilhados na ramificação `main`, com o diretório e o *stage* completamente limpos.

## 2. Recuo Suave (`--soft`)
O objetivo do *reset* é mover o ponteiro `HEAD` para trás no tempo.
```bash
$ git reset --soft HEAD~1
```
**Impacto:** O comando recuou o histórico em 1 commit (para `f8a022b`), fazendo o commit mais recente (`e8ab81b`) sumir do log. No entanto, a área de trabalho permaneceu intacta e as modificações daquele commit desfeito foram mantidas seguras e já preparadas (*Staged*) para um novo commit. É ideal para quando apenas queremos refazer a mensagem do commit.

## 3. Recuo Misto (`--mixed`)

Este é o comportamento padrão do Git caso nenhuma flag seja passada.

```bash
$ git reset --mixed HEAD~1
```

* **Impacto:** Assim como o *soft*, ele move o `HEAD` para trás. A diferença está na *Staging Area*: o comando esvazia o *stage* completamente. As alterações do commit desfeito são devolvidas para o *Working Directory* como arquivos não rastreados ou não preparados.

## 4. Recuo Destrutivo (`--hard`)

O comando mais perigoso da trindade do reset.

```bash
$ git reset --hard HEAD~1
```

**Impacto:** Ao aplicar a flag `--hard`, o Git não apenas recua o histórico e limpa o *stage*, mas **apaga definitivamente** as alterações físicas do disco. Todo o trabalho contido no commit `e8ab81b` foi sumariamente destruído, alinhando a área de trabalho para ser um reflexo idêntico do commit antigo (`f8a022b`).