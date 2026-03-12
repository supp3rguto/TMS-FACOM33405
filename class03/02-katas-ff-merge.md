# Kata: Fast-Forward Merge (Fusão de Avanço Rápido)

## 1. Criação do Feature Branch
Iniciamos isolando a nova funcionalidade em uma ramificação dedicada:
```bash
# Cria e já salta para a nova ramificação simultaneamente
$ git checkout -b feature/uppercase
```

## 2. Implementação da Mudança

O objetivo desta *feature* é alterar a formatação de uma saudação.

```bash
# Editamos o arquivo 'greeting.txt' alterando "hello" para "HELLO"
$git add greeting.txt$ git commit -m "Refatora greeting.txt para uppercase"
```

## 3. Verificando a Topologia dos Branches

Ao executar `git branch`, o asterisco (*) confirma que estamos ativos em `feature/uppercase`.
A análise via `git log --oneline --graph --all` mostra um histórico **linear**. O `feature/uppercase` possui um *commit* a mais ("Refatora greeting.txt..."), estando posicionado perfeitamente à frente do `main`.

## 4. Preparação e Execução do Merge

Para trazer a funcionalidade finalizada para o código de produção, retornamos à base:

```bash
$ git switch main
```

Ao executar `cat greeting.txt` neste momento, vemos o texto antigo em minúsculas, pois o `main` ainda não tem conhecimento da evolução.
Após revisar as diferenças com `git diff main feature/uppercase`, aplicamos a fusão:

```bash
$ git merge feature/uppercase
```

**Conceito aplicado:** Como o `main` não sofreu nenhuma alteração paralela enquanto a *feature* era desenvolvida, o Git não precisa criar um "Commit de Merge". Ele realiza um **Fast-Forward** (Avanço Rápido), simplesmente deslizando o ponteiro do `main` para frente, igualando-o ao topo do `feature/uppercase`.