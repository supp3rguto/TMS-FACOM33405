# Kata: 3-Way-Merge (Fusão de Três Vias)

## 1. Evolução do Ramo Secundário ('greeting')
```bash
$git switch -c greeting$ echo "Olá, mundo!" > greeting.txt
$git add greeting.txt$ git commit -m "Adiciona saudacao em portugues no greeting.txt"
```

## 2. Evolução Paralela do Ramo Principal ('main')

Simulando o trabalho de outro desenvolvedor ou uma atualização paralela, o `main` também avança independentemente:

```bash
$ git switch main
$echo "Este é um repositório para o kata 3-way merge" > README.md$ git add README.md
$ git commit -m "Adiciona documentacao README.md"
```

## 3. A Divergência Histórica

Diferente do cenário de *Fast-Forward*, agora temos históricos excludentes.
Ao rodar `git log --oneline --graph --all`, visualizamos claramente um "Y" no histórico. Os ramos `main` e `greeting` se separaram de um ancestral comum e ambos possuem *commits* únicos que o outro desconhece.

## 4. Resolvendo o Merge de Três Vias

Como a ramificação base (`main`) andou para frente, o Git não pode simplesmente avançar o ponteiro. Ele precisa costurar as histórias.

```bash
# Estando no 'main', trazemos o trabalho do 'greeting'
$ git merge greeting
```

Neste cenário, o Git aciona o algoritmo de **3-Way Merge**. Ele compara três pontos: o *commit* no topo do `main`, o *commit* no topo do `greeting` e o *commit* ancestral comum. Se os arquivos alterados não entrarem em conflito (como neste caso, onde arquivos diferentes foram tocados), o Git cria automaticamente um **Commit de Fusão** (*Merge Commit*), unificando as histórias de forma limpa.