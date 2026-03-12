# Kata: Merge Mergesort (Resolução Visual de Conflitos)

## 1. O Conflito Estrutural
Ao tentar mesclar uma implementação complexa (Mergesort), encontramos colisões no arquivo principal.
```bash
$ git merge Mergesort-Impl
# O Git pausa o merge devido a CONFLICT (content) no arquivo principal.
```

## 2. Abordagem com Ferramenta Visual (MergeTool)

Em vez de editar os marcadores de texto puro, utilizamos uma ferramenta de *diff* visual para lidar com o código de forma mais segura:

```bash
$ git mergetool --tool=vimdiff
```

* **Procedimento:** A interface do `vimdiff` exibe janelas lado a lado (LOCAL, BASE, REMOTE e MERGED).
* **Decisão:** Navegando pelas diferenças, decidimos absorver a implementação que vinha do ramo externo. Utilizando os atalhos do vimdiff (`diffget RE`), puxamos o bloco de código correto da janela *REMOTE* para a janela de resolução (*MERGED*).
* Salvamos o arquivo e saímos da ferramenta.

## 3. Finalizando a Operação

A ferramenta gráfica geralmente facilita o processo, mas ainda requer a confirmação final:

```bash
$ git status  # Confirma que a resolução foi detectada
$ git commit -m "Soluciona conflito de integracao do algoritmo Mergesort"
```