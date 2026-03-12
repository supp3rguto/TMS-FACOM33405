# Kata: Basic Revert (Desfazendo com Segurança)

## 1. A Filosofia do Revert
Diferente do `git reset`, que volta no tempo apagando e reescrevendo a história do projeto (o que é proibido em *branches* públicos compartilhados com a equipe), o `git revert` oferece uma forma segura de desfazer um erro, criando a "antimatéria" do commit problemático.

## 2. Executando a Reversão
Tendo um commit indesejado em nosso histórico imediato, executamos:
```bash
$ git revert HEAD~1
```

* **A Mecânica:** O Git analisa as alterações exatas introduzidas no commit especificado (`f8a022b`) e tenta aplicar o reverso exato na área de trabalho.

## 3. O Resultado no Histórico

O Git abriu o editor solicitando uma mensagem para justificar a reversão.
Após confirmarmos, o histórico (`git log`) não foi apagado. Em vez disso, um **novo commit** (ex: `e7d1623`) foi adicionado de forma linear no topo do histórico (`HEAD`). O *Working Directory* e o *Stage* permaneceram limpos. Esta abordagem preserva a auditoria e garante que o erro e a sua respectiva correção estejam documentados publicamente na linha do tempo.