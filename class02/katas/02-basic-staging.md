**Objetivo:** Entender a diferença entre o diretório de trabalho (Working Directory) e a área de preparação (Staging Area/Index), adicionando arquivos de forma seletiva.

**Passo a passo executado:**
Criamos múltiplos arquivos simultaneamente para simular um ambiente de desenvolvimento real, mas preparamos (stage) e comitamos os arquivos separadamente.

```bash
# Inicializando o repositório
git init basic-staging
cd basic-staging

# Criando três arquivos de uma vez
echo "Linha 1" > file1.txt
echo "Linha 2" > file2.txt
echo "Linha 3" > file3.txt

# O 'git status' aqui mostra os 3 arquivos como 'Untracked' (não rastreados)
git status

# Adicionando apenas o file1.txt para a Staging Area e comitando
git add file1.txt
git commit -m "Adiciona file1.txt"

# Em seguida, adicionando os demais arquivos
git add file2.txt file3.txt
git commit -m "Adiciona file2.txt e file3.txt"
```