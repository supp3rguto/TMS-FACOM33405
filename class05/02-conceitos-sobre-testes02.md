# Conceitos sobre Testes (Parte 2)

## 1. A Cadeia de Falhas (Taxonomia do IEEE)
Para padronizar a engenharia de qualidade, a indústria utiliza diretrizes de base, como a IEEE 610.12-1990, que define com exatidão a relação causal dos problemas em software.
* **Defeito (Fault):** Constitui a raiz estrutural do problema. É basicamente um desvio na lógica ou sintaxe da codificação (ex: uso do operador `=` quando o esperado era `==`) e representa um problema estático, que fica adormecido nos arquivos do sistema.
* **Erro (Error):** Acontece unicamente no momento em que o código portador do defeito é ativado e processado. Refere-se a um estado interno incongruente observado durante o tempo de execução.
* **Falha (Failure):** Trata-se da manifestação empírica e externa. A falha consolida-se apenas quando o software projeta para o usuário final uma saída inconsistente e desalinhada com os requisitos de negócio acordados.

## 2. A Subjetividade do Termo "Bug"
* Na academia, artigos contemporâneos (como o *"[2402.08165] What is a 'bug'?"*) promovem uma análise sobre a semântica dessa terminologia.
* Pesquisas revelam que o uso da palavra "bug" no cotidiano corporativo é volátil e altamente subjetivo, alternando o seu peso dependendo de quem observa (a visão de um testador difere da visão de um executivo).
* Não obstante, para fins de padronização, a instituição global ISTQB correlaciona formalmente o jargão "bug" como o sinônimo exato e prático da palavra "defeito" (fault).