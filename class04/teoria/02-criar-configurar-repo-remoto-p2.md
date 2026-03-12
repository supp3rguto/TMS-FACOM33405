# Sincronização e Gestão de Divergências

## 1. Análise de Referências Locais e Remotas
Para entender o mapeamento do projeto, verificamos o panorama geral de rastreamento:
```bash
# Apresenta as alterações pendentes no Working Directory
$ git status 

# Desenha o grafo de commits unindo a visão local e remota
$ git log --oneline --graph --all 

# Lista as URLs dos servidores configurados para push e fetch
$ git remote -v 
```

## 2. Atualização do Ambiente Local (Pull)

Como alterações foram feitas na nuvem, precisamos sincronizar nossa máquina.
Primeiro, o `git fetch origin` baixa os metadados. Em seguida, o `git pull origin main` consolida e aplica fisicamente as mudanças remotas no nosso diretório de trabalho.

## 3. Implementação e Registro (Commit Local)

Editamos o arquivo `readme.md` inserindo uma nova diretriz (*"1. Leia o Livro Pro Git para revisar os comandos"*).

```bash
$git add readme.md$ git commit -m "Adiciona lembrete para ler o livro Pro Git"
```

## 4. Observando o Descompasso (Ahead of origin)

Ao consultar o `git status` e o log logo após o commit, o Git nos informa um dado crucial: nossa ramificação local está **1 commit à frente** (`ahead`) do repositório remoto. Isso significa que temos progresso não compartilhado.

## 5. Auditoria de Divergências

Para visualizar matematicamente o que nós temos que o servidor não tem (ou vice-versa), utilizamos os comandos de intervalo de commits:

```bash
# Mostra o log específico dos commits que diferem entre o remoto e o local
$ git log origin/main..main 

# Exibe a diferença de código (diff) exata dessas divergências
$ git diff origin/main..main 
```