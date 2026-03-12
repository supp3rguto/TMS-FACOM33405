**Objetivo:** Utilizar Tags (etiquetas) para marcar pontos específicos e importantes da história do projeto, como o lançamento de uma nova versão (ex: v1.0).

**Passo a passo executado:**
Neste exercício, criamos um arquivo base, fizemos um commit e utilizamos o comando de tag para "congelar" aquela versão com um identificador de fácil leitura, facilitando buscas futuras.

```bash
# Inicializando o repositório
git init git-tag
cd git-tag

# Criando arquivo da aplicação
echo "Versão 1.0 da aplicação" > app.txt
git add app.txt
git commit -m "Lançamento inicial"

# Criando uma tag leve apontando para o commit atual
git tag v1.0

# Verificando se a tag foi criada com sucesso
git tag
git log --oneline --decorate
```