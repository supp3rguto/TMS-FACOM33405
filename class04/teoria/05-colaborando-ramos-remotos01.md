# Atividade: Fluxo de Trabalho com Ramificações Remotas (Parte 1)

## 1. Contextualização do Ambiente Descentralizado
Neste laboratório, simulamos a atuação de dois engenheiros de software (Usuário A e Usuário B) operando em repositórios remotos distintos, mas interligados. O objetivo é estabelecer linhas de desenvolvimento paralelas (branches) que possam ser compartilhadas pela rede.

## 2. Delegação de Tarefas e Criação de Ramos (Usuário A)
Para não comprometer a linha principal (`main`), o Usuário A isola o seu escopo de trabalho criando um novo ramo denominado `dev1` e transferindo o ponteiro de execução (`HEAD`) para ele:
```bash
$ git checkout -b dev1
```

Após realizar e consolidar as alterações no arquivo de documentação (`readme.md`), o Usuário A precisa disponibilizar esse ramo na nuvem. A submissão é feita estabelecendo o rastreamento primário (`upstream`):

```bash
$ git push -u origin dev1
```

*Nota:* A flag `-u` (set-upstream) é crucial na primeira submissão, pois ela vincula o ramo local `dev1` diretamente ao ramo correspondente no servidor `origin`, automatizando futuros comandos de sincronização.

## 3. Rastreamento Cruzado (Usuário B)

Para que o Usuário B possa auditar o código produzido pelo Usuário A, ele primeiro faz o download dos metadados daquele ambiente:

```bash
$ git fetch colegaA
```

Como o código baixado reside temporariamente no espaço de nomes remoto (`colegaA/dev1`), o Usuário B engatilha a criação de uma ramificação local espelhada para poder inspecionar os arquivos:

```bash
$ git checkout -b dev1_A colegaA/dev1
```

O console confirma a operação com a mensagem: *Branch 'dev1_A' set up to track remote branch 'dev1' from 'colegaA'*. Isso significa que o trabalho do Usuário A foi perfeitamente replicado na máquina do Usuário B para revisão.

## 4. Evolução Simultânea (Usuário B)

Retornando à sua própria linha principal, o Usuário B inicia sua própria frente de trabalho paralela (`dev2`), aplica modificações independentes, gera o *commit* e repete o processo de envio para a nuvem em seu próprio servidor (`origin`):

```bash
$ git checkout main
$git checkout -b dev2$ echo "Feature dev2 do Usuario B" >> readme.md
$git commit -am "Feature dev2 do Usuario B"$ git push -u origin dev2
```