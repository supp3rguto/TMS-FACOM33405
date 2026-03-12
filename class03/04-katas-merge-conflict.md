# Kata: Merge Conflict (Lidando com Conflitos de Fusão)

## 1. O Incidente (A Falha no Merge)
A tentativa de fundir duas linhas de tempo onde o **mesmo arquivo** foi alterado na **mesma linha** gera um bloqueio de segurança do Git:
```bash
$ git merge merge-conflict-branch1
# Saída do Git:
# Auto-merging greeting.txt
# CONFLICT (content): Merge conflict in greeting.txt
# Automatic merge failed; fix conflicts and then commit the result.
```

## 2. Diagnóstico da Situação

Ao executar o comando `git status`, o sistema acusa o status de *Unmerged paths* e aponta o arquivo `greeting.txt` como `both modified` (modificado por ambos). O Git pausa o processo de merge e exige intervenção humana.

## 3. Resolução Manual no Editor de Código

Ao abrir o arquivo `greeting.txt` em um editor (como Vim ou VS Code), encontramos a sintaxe de marcação de conflito inserida pelo Git:

```text
<<<<<<< HEAD
Olá, mundo! (Versão atual no main)
=======
Hello, world! (Versão advinda do branch em merge)
>>>>>>> merge-conflict-branch1
```

**Ação corretiva:** A resolução consiste em apagar os marcadores de controle (`<<<<<<<`, `=======`, `>>>>>>>`) e definir qual será a versão final do texto (ou uma mescla de ambas). O arquivo foi salvo com o conteúdo final unificado.

## 4. Conclusão da Fusão

Após a intervenção humana, sinalizamos ao Git que o conflito foi sanado e concluímos o empacotamento:

```bash
$git add greeting.txt$ git commit -m "Resolve conflito de saudacao no greeting.txt"
```

O `git log --graph` confirma que um novo *Commit de Merge* foi gerado, fechando o "loop" da bifurcação e restaurando a linearidade do histórico do projeto.