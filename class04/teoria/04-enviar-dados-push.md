# Integração e Submissão de Dados Locais

## 1. Estabelecimento do Ecossistema
Este laboratório simula um cenário real onde o **Usuário A** e o **Usuário B** interagem descentralizadamente. Ambos possuem seus próprios repositórios clonados (`origin`) e configuraram o repositório do parceiro como um *remote* secundário (ex: `colegaA` e `colegaB`).

## 2. Recepção e Integração (Fetch + Merge)
Estando com o ambiente local limpo (`git status`), o Usuário A inicia o processo de absorção das contribuições do parceiro.
```bash
# Baixa silenciosamente a árvore de metadados do repositório do colega
$ git fetch colegaB

# Costura o branch 'main' do colega para dentro do 'main' ativo localmente
$ git merge colegaB/main
```

Com isso, o ambiente de desenvolvimento local do Usuário A agora concentra a união das duas linhas de trabalho.

## 3. Análise da Topologia e Envio Definitivo (Push)

Para checar o alinhamento das *branches*, utiliza-se o comando de verificação verbosa:

```bash
$ git branch -vv
```

A saída deste comando revela uma dinâmica interessante: a *branch* local `main` agora está atualizada com a `colegaB/main`, porém, devido a essa incorporação de novos códigos, ela passou a ficar **à frente (ahead)** da `origin/main`.

Para fechar o ciclo colaborativo e atualizar o seu próprio servidor com a versão final e unificada, o Usuário A engatilha o comando de submissão:

```bash
# Empurra as modificações locais para o ramo 'main' do remoto 'origin'
$ git push origin main
```