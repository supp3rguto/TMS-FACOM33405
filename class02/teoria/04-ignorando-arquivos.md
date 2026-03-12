
# Controle de Rastreamento: Ocultação e Arquivos Ignorados

## 1. Análise de Padrões no Arquivo de Exclusão (.gitignore / .ignore)
O arquivo de configuração de ignorados dita as regras globais de exclusão do repositório, garantindo que arquivos de sistema, binários ou dados temporários não sejam versionados acidentalmente.
Interpretação da estrutura apresentada na atividade:

* `!lib.a` → **Exceção à Regra:** O ponto de exclamação atua como uma negação. Ele garante que o arquivo `lib.a` seja rastreado obrigatoriamente, anulando regras genéricas anteriores que o afetariam.
* `/TODO` → **Caminho Absoluto Local:** Ignora especificamente o arquivo (ou diretório) `TODO` que está exatamente na raiz do projeto, mas não ignoraria um arquivo `TODO` dentro de uma subpasta.
* `build/` → **Diretórios de Compilação:** Ignora a pasta `build` e absolutamente todo o seu conteúdo de forma recursiva (muito comum para ignorar saídas de compiladores).
* `doc/*.txt` → **Wildcard de Nível Único:** Ignora arquivos de texto (`.txt`), mas estritamente aqueles que estiverem no primeiro nível do diretório `doc/` (não afeta subpastas de `doc/`).
* `doc/**/*.pdf` → **Wildcard Recursivo (Glob):** Os dois asteriscos indicam recursividade. Ele ignora arquivos `.pdf` localizados em `doc/` ou em *qualquer* nível de subdiretório dentro dele.

## 2. Execução Prática: Manipulando Arquivos Temporários
Para validar o comportamento do Git, simulamos a criação de um arquivo temporário de sistema (`hello.tmp`).

```bash
$ touch hello.tmp
$ git status
```

Ao verificar o *status*, o Git acusa a presença do arquivo na seção `Untracked files`. Ele avisa que encontrou um item novo, mas que ainda não está sob monitoramento.

## 3. Implementando a Regra de Ignorar

Para evitar "sujar" o histórico do repositório, o arquivo de configuração de exclusões (tratado como `.ignore` nesta atividade) foi editado.

```bash
# Adicionamos a regra genérica para qualquer arquivo temporário no final do arquivo
$ echo "*.tmp" >> .ignore

# Validação do status após a aplicação da regra
$ git status
```

Após essa alteração, ao rodar `git status`, o `hello.tmp` desaparece da listagem de arquivos não rastreados. O motor do Git agora está oficialmente cego para ele.

## 4. Auditoria de Regras de Exclusão

Para confirmar que o arquivo foi descartado de propósito (e não por um erro de diretório), rodamos a ferramenta de auditoria de *ignore*:

```bash
$ git check-ignore -v hello.tmp
.ignore:1:*.tmp      hello.tmp
```

*A resposta do terminal confirma detalhadamente o sucesso da operação: a regra `*.tmp`, contida na linha 1 do arquivo `.ignore`, foi a responsável por interceptar o arquivo `hello.tmp`.*