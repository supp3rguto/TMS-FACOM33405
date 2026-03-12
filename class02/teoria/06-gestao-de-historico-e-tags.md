# Gestão Avançada de Histórico, Correções e Marcações (Tags)

## 1. Investigação do Histórico (Git Log)
A análise detalhada de históricos permite-nos entender a evolução de um ecossistema. Executando comandos de inspeção num repositório clonado, extraímos a seguinte telemetria:
* **Estatística do Commit:** Foram inseridas 54 linhas em 3 ficheiros alterados (com destaque para as adições no ficheiro `lib/simplegit.rb`).
* **Consulta Parametrizada Avançada:** Utilizando o repositório do próprio código-fonte do Git, executamos:
  `git log --pretty="%h - %s" --author=gitster --since="2008-10-01" --before="2008-11-01" --no-merges -- t/`
* **Interpretação:** Este filtro cirúrgico devolve-nos os *hashes* curtos e as descrições limpas dos *commits* feitos unicamente pelo utilizador "gitster" durante outubro de 2008, afetando especificamente o diretório de testes `t/` e omitindo qualquer *commit* sujo de mesclagem (merges).

## 2. Reescrevendo o Histórico (Amend & Restore)
A ferramenta fornece mecanismos de correção de rotas (Undo). 
* **Amend (Retificação Rápida):** Quando um *commit* recebe a mensagem errada, ou quando um ficheiro é esquecido no estágio de preparação, o `git commit --amend` "funde" os novos dados com o commit anterior, substituindo a assinatura do *hash*. No nosso laboratório, utilizámos este recurso para consertar a mensagem de envio e embutir o ficheiro `referencias.md` sem gerar ruído linear no *log*.
* **Restore (Retirada da Staging Area):** Usámos o comando `git restore --staged reparo.md` para remover um ficheiro acidentalmente colocado na área de preparação (Index), devolvendo-o para o *Working Directory* antes que se tornasse num *commit* definitivo.

## 3. Marcação de Versões (Git Tags)
As *Tags* atuam como ponteiros cristalizados que apontam para *commits* específicos, representando marcos importantes, como lançamentos (Releases).
* **Análise de Projeto Real (Spring Boot):** Utilizando os comandos de inspeção, localizámos a tag `v3.4.6`.
* **Detalhes do Metadado:** A inspeção profunda com `git show v3.4.7` revela a autoria da *release* (Stéphane Nicoll), a data e hora exatas (*timestamp*), a assinatura do PGP (segurança) e as modificações específicas no ficheiro `gradle.properties` que "carimbaram" a mudança de número de versão do pacote de *snapshot* para a *release* final.