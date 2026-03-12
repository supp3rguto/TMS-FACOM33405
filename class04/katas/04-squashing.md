# Kata: Squashing (Compactação de Histórico)

## 1. O Excesso de Granularidade
Frequentemente, durante o desenvolvimento, criamos pequenos commits não significativos (ex: "Ops, corrige erro de digitação", "Adiciona linha"). Isso polui a árvore do projeto. Nosso objetivo era agrupar os últimos 5 commits em um único pacote lógico.

## 2. O Processo de Squash via Rebase Interativo
Para condensar o histórico, iniciamos uma reescrita interativa retrocedendo 5 passos:
```bash
$ git rebase -i HEAD~5
```

Na interface de edição, mantivemos o primeiro commit como `pick` e alteramos os subsequentes para `squash` (ou `s`). O Git em seguida consolidou as alterações, abriu o editor de texto contendo as mensagens originais, e nos permitiu criar uma **mensagem única e limpa**: *"Agrupamento de 5 commits"*. O comando `git log` confirmou que o histórico passou de ruidoso para extremamente limpo.

## 3. Limpeza Fina e Incorporação (Amend)

Após a compactação, percebemos que ainda havia um caractere residual indesejado (`\n`) no arquivo `file.txt`. Para remover isso sem gerar um novo commit que sujasse o nosso agrupamento recém-criado, executamos uma correção de rota:

1. Removemos o `\n` e adicionamos a alteração ao índice (`git add file.txt`).


2. Executamos uma emenda:

```bash
$ git commit --amend --no-edit
```

O comando `--amend` interceptou as novas alterações e as fundiu dentro do commit consolidado gerado pelo *squash*. O resultado final é um histórico elegante e linear.