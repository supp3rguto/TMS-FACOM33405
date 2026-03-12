# Configuração de Ambiente Colaborativo (Múltiplos Remotos)

## 1. Conexão Cruzada de Repositórios
Para viabilizar o fluxo de trabalho em pares, disponibilizei a URL do meu repositório (`https://github.com/supp3rguto/TMS-FACOM33405.git`) para clonagem. 
Simultaneamente, adicionei o repositório remoto do parceiro ao meu ambiente local, criando um atalho chamado `colega`:
```bash
$ git remote add colega [https://github.com/outro-user/TMS2.git](https://github.com/outro-user/TMS2.git)
```

Ao executar `git remote -v`, confirmo que minha máquina agora rastreia dois servidores: o meu padrão (`origin`) e o do parceiro (`colega`).

## 2. Evolução Independente do Código

Trabalhando na minha frente isolada, modifiquei o título e a descrição do projeto no arquivo `readme.md` e consolidei a versão localmente:

```bash
$git add readme.md$ git commit -m "Adiciona instrução para praticar os comandos"
```

A saída do sistema (`1 file changed, 2 insertions(+)`) confirmou a geração do hash do commit.

## 3. Investigação de Estados Remotos

A vantagem de ter múltiplos remotos é poder inspecionar o trabalho de terceiros antes de integrá-lo.

* **Meu Remoto (`origin`):** Ao buscar atualizações com `git fetch origin` e executar o log comparativo (`git log origin/main..main`), confirmo que minha máquina está com o commit do "readme" represado localmente.
* **Remoto do Colega (`colega`):** Ao buscar os dados do parceiro (`git fetch colega`) e verificar o histórico cruzado (`git log colega/main..main`), noto a divergência: eu possuo a edição do `readme.md`, enquanto a árvore de desenvolvimento dele aponta para um commit diferente ("Corrige bug na tela de login"). Essa análise prévia com `git diff` é essencial para prever potenciais conflitos de integração.