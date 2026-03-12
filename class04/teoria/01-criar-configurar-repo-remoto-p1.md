# Criação e Configuração Inicial de Repositório Remoto

## 1. Configuração do Repositório na Nuvem
O primeiro passo para o desenvolvimento colaborativo é o estabelecimento de um servidor central. Criamos um repositório vazio no GitHub nomeado `meu_remoto` (ex: `https://github.com/supp3rguto/TMS-FACOM33405.git`).

## 2. Clonagem para o Ambiente Local
Para iniciar o trabalho, espelhamos o repositório remoto para a máquina local utilizando o comando de clonagem.
```bash
$ git clone [https://github.com/supp3rguto/TMS-FACOM33405.git](https://github.com/supp3rguto/TMS-FACOM33405.git)
```

## 3. Auditoria do Estado Inicial

Ao acessar o diretório recém-clonado, executamos uma bateria de verificações para atestar o estado do versionamento:

* **`git status`**: Retornou avisos de que não há commits a serem feitos e indicou a presença de arquivos não rastreados.
* **`git log`**: Confirmou a ausência de histórico (nenhum commit realizado até o momento).
* **`git diff`**: Não retornou saídas, visto que não há índice anterior para comparação.

## 4. Atualização Via Interface Web (GitHub)

Simulando uma alteração feita diretamente pelo portal web ou por outro colaborador, criamos um arquivo `README.md` diretamente na interface do GitHub, contendo a instrução básica: *"Como utilizar este repositório:"*.

## 5. Verificação de Divergência Local

Ao rodar `git status` no terminal local, o Git apontou que o diretório possui o `README.md` e a pasta `git-katas/` como *Untracked files* (arquivos não rastreados), aguardando a adição formal ao índice (`git add`).