# Kata: Basic Branching (Ramificação Básica)

## 1. Verificando e Criando Ramificações
O primeiro passo em qualquer fluxo de trabalho isolado é criar uma ramificação (*branch*). Isso garante que o código principal não seja afetado por experimentos.
```bash
# Lista as ramificações atuais
$ git branch
# Cria uma nova ramificação chamada 'mybranch'
$ git branch mybranch
# Muda o contexto (HEAD) para a nova ramificação
$ git switch mybranch
```

*Saída esperada:* `Switched to branch 'mybranch'`. O asterisco no comando `git branch` agora apontará para `mybranch`.

## 2. Observando o Workspace (Ambiente de Trabalho)

**O que muda no `git status`?** Apenas a linha de cabeçalho, que passará de `On branch main` para `On branch mybranch`.
**O que muda no Workspace?** Absolutamente nada. Como acabamos de criar o *branch*, tanto `main` quanto `mybranch` são ponteiros idênticos apontando para o mesmo *commit* no tempo.

## 3. Consolidando Alterações Isoladas (mybranch)

Criamos um arquivo novo para existir unicamente nesta linha do tempo:

```bash
$echo "supp3rguto" > file1.txt$ git add file1.txt
$ git commit -m "Adiciona file1.txt no mybranch"
```

Ao rodar `git log --oneline --graph`, vemos que o `HEAD` avançou no `mybranch`, mas o `main` ficou para trás no commit "Teste inicial".

## 4. Criando um Histórico Divergente

Ao retornar para o `main`, notamos que o arquivo `file1.txt` **desaparece** do diretório. Isso ocorre porque o Git reescreve o diretório de trabalho para refletir o estado exato da ramificação ativa.

```bash
$ git switch main
$echo "Outro arquivo" > file2.txt$ git add file2.txt
$ git commit -m "Adiciona file2.txt no main"
```

**Análise do Histórico Divergente (`git log --oneline --graph --all`):**
O gráfico revela uma bifurcação clara. A partir do *commit* pai comum ("Teste inicial"), a história se dividiu: um caminho seguiu para `mybranch` contendo `file1.txt`, e o outro caminho seguiu para `main` contendo `file2.txt`.

## 5. Comparação Final

Para auditar as diferenças estruturais entre as duas linhas do tempo antes de qualquer fusão, utilizamos o comparador de diff:

```bash
$ git diff mybranch main
```

A saída deste comando evidencia de forma explícita que o `main` possui o arquivo `file2.txt` (sinalizado com `+`), enquanto carece do arquivo `file1.txt` (que existe apenas no `mybranch`).