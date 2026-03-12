**Objetivo:** Corrigir o último commit realizado. Útil quando esquecemos de adicionar um arquivo ou quando digitamos a mensagem de commit com erro ortográfico.

**Passo a passo executado:**
Simulamos um cenário onde a mensagem do commit precisava ser alterada, e logo depois, um cenário onde um arquivo foi esquecido e precisava ser embutido no commit anterior sem gerar um novo registro no histórico.

```bash
# Inicializando o repositório
git init amend-kata
cd amend-kata

# Criando e comitando um arquivo
echo "Conteúdo" > file.txt
git add file.txt
git commit -m "Mensagem erada" # Simulação de erro de digitação

# 1. Corrigindo apenas a mensagem do último commit
git commit --amend -m "Mensagem corrigida"

# 2. Esqueci de adicionar um arquivo no commit anterior!
echo "Novo arquivo" > newfile.txt
git add newfile.txt

# Adicionando o 'newfile.txt' ao último commit sem alterar a mensagem
git commit --amend --no-edit

# Verificando o histórico (haverá apenas 1 commit limpo e corrigido)
git log --oneline
```