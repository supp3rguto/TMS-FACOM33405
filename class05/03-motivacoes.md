# Motivações para Testes de Software

## 1. O Custo da Imprudência: O Colapso Ambulatorial (1992)
* **Contexto do Incidente:** Em 1992, o sistema informatizado de despacho de ambulâncias em Londres protagonizou uma das maiores catástrofes em TI.
* **Causas Fundamentais:** O software foi colocado em produção carregado de defeitos críticos ignorados e, mais grave ainda, sem a adoção de nenhuma simulação de sobrecarga.
* **A Consequência:** A arquitetura falhou em cascata sob tráfego real, criando um atraso massivo nos atendimentos que, segundo relatórios, contribuiu dezenas de perdas humanas. O evento tornou-se o caso de estudo definitivo sobre a irresponsabilidade da ausência de testes em aplicações críticas de missão.

## 2. A Engenharia Preventiva: O E-commerce da Walmart
* **Contexto do Sucesso:** Contrastando com o cenário anterior, campanhas mastodônticas de vendas, como a Black Friday, exigem que a rede da Walmart adote protocolos defensivos rigorosos.
* **Tática de Qualidade:** A corporação implementou testagem de carga em altíssima escala para estressar a própria plataforma, forçando a detecção prematura de limites e gargalos na infraestrutura.
* **Resultados e Benefícios:** O processo analítico permitiu à engenharia aplicar contingências arquiteturais (como alocação de CDNs, ajustes finos no banco de dados e estratégias profundas de cache), assegurando que o sistema operasse de forma totalmente ilibada e resiliente frente a uma avalanche de acessos reais.