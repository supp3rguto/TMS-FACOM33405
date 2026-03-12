**Objetivo:** Compreender o fluxo básico de inicialização de um repositório e o registro de alterações simples no histórico do Git.

**Passo a passo executado:**
Iniciamos criando o repositório e adicionando nosso primeiro arquivo de texto. Em seguida, fizemos alterações incrementais para observar como o Git empilha os *commits*.

```bash
# Inicializando o repositório e entrando na pasta
git init basic-commits
cd basic-commits

# Criando o primeiro arquivo e registrando no histórico
echo "Olá Katas" > hello.txt
git add hello.txt
git commit -m "Adiciona arquivo hello.txt"

# Modificando o arquivo existente e registrando a nova versão
echo "Segunda linha" >> hello.txt
git add hello.txt
git commit -m "Adiciona segunda linha ao hello.txt"

# Adicionando um arquivo totalmente novo
echo "Outro arquivo" > another.txt
git add another.txt
git commit -m "Adiciona another.txt"

# Verificando a árvore de histórico gerada
git log --oneline
```