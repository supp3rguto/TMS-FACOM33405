# Dinâmica Colaborativa com Repositórios Remotos e Branches

## 1. Fluxo de Criação e Rastreamento Local-Remoto (Usuário A)
A colaboração eficiente com múltiplos programadores exige a utilização de ramificações (branches) descentralizadas. 
O Usuário A (conectado ao repositório `origin`) inicia uma nova frente de trabalho isolada (ramo `dev1`), realiza modificações no documento `readme.md` e consolida a entrega.

```bash
# Cria o ramo local 'dev1' e transita para ele em simultâneo
$ git checkout -b dev1

# Edita o ficheiro, adiciona à Staging Area (index) e regista o commit
$echo "Modificacao do Usuario A no ramo dev1" >> readme.md$ git add readme.md
$ git commit -m "Feature dev1 do Usuario A"

# Envia o novo ramo para o servidor remoto e cria a ligação de rastreio (tracking)
$ git push -u origin dev1
```

## 2. Sincronização e Busca (Fetch e Tracking) pelo Colega

Quando existem repositórios remotos de terceiros configurados (ex: o computador do Usuário B enxerga o remoto `colegaA`), é necessário descarregar os metadados antes de os integrar no código.
O comando `git fetch` descarrega toda a árvore de histórico e ramificações criadas remotamente, mas **não altera** os ficheiros do ambiente de trabalho local de forma abrupta.

Para que o Usuário B possa analisar e continuar o trabalho no código do colega, este cria um ramo de rastreio (*tracking branch*):

```bash
# Descarrega os dados do remoto 'colegaA' (sem aplicar fusão)
$ git fetch colegaA

# Cria um ramo local 'dev1_A' diretamente ligado ao ramo remoto 'dev1' do colega
$ git checkout -b dev1_A colegaA/dev1
```

*Observação: Ao executar isto, o Usuário B fica com uma cópia espelhada exata do trabalho do Usuário A na sua própria máquina.*

## 3. Desenvolvimento Paralelo (Usuário B)

Para garantir que as funcionalidades não se sobrepõem, o Usuário B repete o processo base, mas criando agora o seu próprio ramo independente (`dev2`).

```bash
# Regressa à linha principal antes de ramificar
$ git checkout main

# Cria a nova frente de trabalho
$git checkout -b dev2$ echo "Feature dev2 do Usuario B" >> readme.md
$git add readme.md$ git commit -m "Feature dev2 do Usuario B"
```

## 4. Fusão e Consolidação Completa (Pull)

Se o Usuário B avançar o código através de múltiplos *commits* e enviar para o seu servidor público, o Usuário A necessita de obter esse pacote de atualizações para manter o projeto alinhado.

```bash
# O comando 'pull' é um agregador: engatilha um 'fetch' nos bastidores e, de seguida, 
# executa o 'merge', fundindo o ramo do colega na linha temporal atual.
$ git pull colegaB main
```

## 5. O Fluxo de Correção Rápida (Hotfix Flow) e Limpeza

A criação de ramos paralelos é vital para tratar de interrupções urgentes (bugs em produção) sem comprometer o código em desenvolvimento. O Usuário A cria o ramo `fixes`, efetua a reparação, submete para a *cloud* (backup) e, após validar a correção no ramo principal, o ciclo encerra-se eliminando as ramificações temporárias.

```bash
# 1. Cria e repara no ramo isolado 'fixes'
$git checkout -b fixes$ echo "Correcao rapida feita pelo Usuario A" >> readme.md
$git add readme.md$ git commit -m "Aplica correcao no ramo fixes"
$ git push origin fixes

# 2. Regressa ao 'main' e incorpora a correção definitiva
$ git checkout main
$ git merge fixes
$ git push origin main

# 3. Higiene do Repositório: Elimina os ponteiros do ramo já fundido
$git branch -d fixes                # Elimina o ramo no ambiente local$ git push origin --delete fixes     # Elimina o ramo no servidor remoto
```