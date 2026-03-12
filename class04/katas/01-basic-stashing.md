# Kata: Basic Stashing (Armazenamento Temporário)

## 1. O Problema: Interrupção de Contexto
Estávamos trabalhando em funcionalidades e o `git status` mostrava modificações na *Staging Area* (preparadas) e no *Working Directory* (não preparadas). De repente, surge uma demanda urgente (ex: correção de bug na produção) que exige um diretório limpo.

## 2. Salvando o Progresso (Git Stash)
Para não precisarmos fazer um *commit* de código incompleto, guardamos tudo "na gaveta":
```bash
$ git stash
```

O terminal exibe `Saved working directory and index on HEAD...`.
Ao rodar `git status` em seguida, o diretório está impecável (`working tree clean`). O histórico de *commits* (`git log`) não sofre alterações, pois o *stash* é uma estrutura de armazenamento temporária e invisível no log principal.

## 3. O Contexto Urgente

Com o diretório limpo, fomos capazes de atuar no incidente de forma cirúrgica:

```bash
# Corrigimos o arquivo bug.txt
$git add bug.txt$ git commit -m "Hotfix: Corrige bug critico"
```

## 4. O Retorno: Restaurando o Trabalho (A forma errada e a correta)

**A Tentativa Imprecisa (`git stash apply`):**
Se simplesmente rodarmos `git stash apply`, o Git recupera os arquivos modificados, mas **perde a segregação** da *Staging Area*. Tudo volta "bagunçado" para o *Working Directory*.

**A Recuperação Cirúrgica (`--index`):**
Restauramos o ambiente (desfazendo a bagunça com `git reset --hard HEAD`) e aplicamos a forma correta:

```bash
$ git stash apply --index
```

A *flag* `--index` é vital aqui: ela instrui o Git a restaurar não apenas as modificações do arquivo, mas a recolocar os arquivos exatos de volta na *Staging Area*, mantendo a separação idêntica ao que tínhamos antes da interrupção.

## 5. Limpeza de Memória

Uma vez que o contexto foi recuperado com sucesso, removemos o registro da gaveta de armazenamento para não acumular lixo no sistema local:

```bash
$ git stash drop
```