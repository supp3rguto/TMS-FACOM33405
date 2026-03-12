# Fluxo de Trabalho com Ramificações Remotas (Parte 2)

## 1. Atualizações Contínuas e Sincronização
No cenário de desenvolvimento ativo, as atualizações são frequentes. O Usuário B realiza duas novas edições em sequência no `readme.md`, empilhando *commits*, e submete a carga atualizada para o servidor:
```bash
$ git push origin main
```

Para manter a base de código alinhada, o Usuário A executa o tracionamento e a fusão simultânea do ramo principal do colega para dentro do seu diretório de trabalho:

```bash
$ git pull colegaB main
```

## 2. O Paradigma do Hotfix (Correções a Quente)

Situações emergenciais exigem intervenções isoladas e rápidas. O Usuário A detecta a necessidade de um reparo e instila um novo ramo chamado `fixes`.

```bash
$git checkout -b fixes$ echo "Correcao rapida feita pelo Usuario A" >> readme.md
$ git commit -am "Aplica correcao no ramo fixes"
```

Antes da integração final, o ramo de reparo é enviado ao repositório remoto (`origin`) por razões de backup e transparência de equipe:

```bash
$ git push origin fixes
```

## 3. Ciclo de Vida da Ramificação: Fusão e Expurgo

Uma vez que o reparo (`fixes`) foi revisado, ele deve ser incorporado à linha do tempo oficial e, subsequentemente, destruído para não inflar a árvore de versionamento.
O Usuário A salta para a ramificação principal e realiza a costura:

```bash
$ git checkout main
$ git merge fixes
$ git push origin main
```

Com o código principal atualizado e enviado à nuvem, inicia-se o protocolo de higienização do repositório. O ponteiro de trabalho local é suprimido:

```bash
$ git branch -d fixes
```

Por fim, a referência alocada no servidor remoto também é deletada, encerrando permanentemente o ciclo de vida daquela ramificação:

```bash
$ git push origin --delete fixes
```