# Monitoramento de Estados, Diferenças e Operações de Sistema de Arquivos

## 1. Análise Criteriosa com o comando Diff
O arquivo fonte `Hello.java` foi modificado para executar um laço de repetição (loop) que imprime 10 vezes no console. Antes de consolidar a mudança, utilizamos os mecanismos de auditoria visual do Git.

```bash
$ git status
# Retorna 'modified: hello.java' em vermelho (Working Directory)

$ git diff hello.java
```

O comando `git diff` exibiu a saída baseada no formato *Unified Diff Format*, apontando a vermelho (sinal de `-`) o fecho antigo da chaveta, e a verde (sinal de `+`) o novo código recém-inserido do laço `for`. Esta verificação pré-commit é uma das melhores práticas na engenharia de software para garantir que não é submetido código indesejado para o repositório.

**Registo da alteração:**

```bash
$git add hello.java$ git commit -m "Refatora hello.java para executar rotina de impressao 10x"
```

## 2. Movimentação e Renomeação de Arquivos

No Git, renomear um ficheiro é, a nível concetual, o mesmo que movê-lo (`mv`).

```bash
# Renomeando de .txt para markdown
$git mv hello.txt hello.md$ git commit -m "Altera extensao do documento para markdown (hello.md)"
```

O Git rastreia de forma inteligente (através de heurística) a similaridade do ficheiro e acusa a operação como um `rename (100%)`, sem quebrar o histórico de conteúdo daquele ficheiro.

## 3. Remoção Rastreável (git rm)

Para apagar um ficheiro do disco e notificar imediatamente o sistema de controlo de versões de que ele deve ser removido do rastreamento futuro:

```bash
$git rm hello.md$ git commit -m "Remove hello.md em definitivo do projeto"
```