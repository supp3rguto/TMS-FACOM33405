# Kata: Detached Head (Cabeça Desanexada)

## 1. Entendendo o Estado Detached HEAD
No Git, o `HEAD` é um ponteiro que normalmente aponta para o nome do *branch* atual (ex: `main`). O estado "Detached HEAD" ocorre quando forçamos esse ponteiro a apontar diretamente para o *hash* de um commit antigo, desconectando-o de qualquer ramificação.

## 2. Viajando no Tempo
Para simular a auditoria de um código antigo, verificamos o histórico com `git log --oneline` e voltamos para um commit específico no passado (substitua `<hash>` pelo hash real do exercício):
```bash
$ git checkout <hash-do-commit-antigo>
```

Neste momento, o Git emite um longo aviso alertando que estamos em *'detached HEAD' state*. Podemos olhar arquivos, fazer testes e compilar o projeto exatamente como ele era naquela data.

## 3. A Armadilha do Commit Isolado

Mesmo desanexados, o Git permite que façamos alterações e commits:

```bash
$echo "Alteração no passado" > teste.txt$ git add teste.txt
$ git commit -m "Commit feito em Detached HEAD"
```

O problema ocorre ao retornarmos para o fluxo principal:

```bash
$ git switch main
```

Ao fazermos isso, o Git avisa que estamos deixando um commit para trás sem nenhum *branch* apontando para ele. Se o coletor de lixo (*Garbage Collector*) do Git rodar, esse commit será **apagado permanentemente**, pois está órfão.

## 4. O Resgate (Salvando a Linha do Tempo)

Para não perdermos a alteração feita no passado, precisamos ancorá-la a uma nova ramificação. Retornamos ao hash daquele commit órfão e criamos um branch retroativo:

```bash
$ git branch feature/recuperada <hash-do-commit-orgao>
```

Agora, o trabalho experimental está salvo de forma segura em `feature/recuperada`, podendo ser mesclado (merge) ao projeto principal no futuro.