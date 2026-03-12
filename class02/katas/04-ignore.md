**Objetivo:** Configurar o Git para ignorar arquivos que não devem ser versionados (como logs, arquivos temporários, senhas e binários compilados) usando o arquivo *.gitignore*.

**Passo a passo executado:**
Simulamos um projeto em Python que gera arquivos temporários e logs de debug que não queremos enviar para o repositório remoto.

```bash
# Inicializando o repositório
git init ignore-kata
cd ignore-kata

# Criando os arquivos do projeto (código fonte, logs, arquivos temporários)
echo "print('App rodando')" > app.py
echo "erro linha 45" > debug.log
mkdir tmp
echo "temp data" > tmp/temp.txt

# Criando e configurando o arquivo .gitignore
echo "*.log" > .gitignore
echo "tmp/" >> .gitignore

# Verificando o status
# Note que apenas 'app.py' e '.gitignore' aparecerão como Untracked. 
# O Git agora está cego para 'debug.log' e para a pasta 'tmp/'.
git status

# Registrando apenas os arquivos válidos
git add .
git commit -m "Adiciona app principal e regras de ignore"
```