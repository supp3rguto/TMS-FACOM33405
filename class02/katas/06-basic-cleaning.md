**Objetivo:** Limpar a área de trabalho (Working Directory) removendo arquivos não rastreados (untracked) de forma rápida e segura.

**Passo a passo executado:**
Às vezes geramos muitos arquivos temporários ou de teste localmente e queremos "limpar a casa" e voltar o diretório ao estado exato do último commit. O comando *git clean* é a ferramenta certa para isso.

```bash
# Inicializando o repositório
git init basic-cleaning
cd basic-cleaning

# Criando arquivos base e registrando
echo "Arquivo 1" > file1.txt
git add file1.txt
git commit -m "Adiciona file1.txt"

# Criando arquivos não rastreados (lixo)
echo "Arquivo temporario 1" > temp1.txt
echo "Arquivo temporario 2" > temp2.txt

# Verificando o que será deletado ANTES de executar (modo dry-run de segurança)
git clean -n

# Executando a limpeza de fato para arquivos não rastreados
git clean -f

# Verificando o status (o diretório estará limpo novamente)
git status
```