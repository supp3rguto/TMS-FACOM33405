# Setup Inicial do Ambiente Git

## 1. Instalação e Verificação do Binário
A instalação da ferramenta de versionamento Git foi realizada através do site oficial (`git-scm.com/download/win`). O processo seguiu as recomendações padrão (utilizando o *Git Bash* e as definições de codificação nativas).
Para validar o sucesso da instalação no *environment* do sistema operacional, corremos a verificação de versão:

```bash
$ git version
git version 2.50.1.windows.1
```

## 2. Configuração de Identidade (Global Settings)
O Git exige a identificação do autor para que os registos de alterações (*commits*) possuam rastreabilidade e autoria definida, algo crítico em equipas de desenvolvimento.

```bash
$git config --global user.name "supp3rguto"$ git config --global user.email "augustoortigosobarbosa@gmail.com"
```

## 3. Explorando a Documentação Embutida (Help)
O Git possui um manual interno robusto. Ao executar o comando base `git` ou `git help`, o sistema retorna os subcomandos organizados por contexto de uso:

* **Início de Área de Trabalho:** `clone`, `init`
* **Trabalho na Árvore Local:** `add`, `mv`, `restore`, `rm`
* **Exame de Histórico e Estado:** `bisect`, `diff`, `grep`, `log`, `show`, `status`
* **Evolução de Histórico:** `branch`, `commit`, `merge`, `rebase`, `reset`, `switch`, `tag`
* **Colaboração (Remotos):** `fetch`, `pull`, `push`