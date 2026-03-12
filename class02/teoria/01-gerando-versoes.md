# Estratégias e Padrões de Versionamento de Software

## 1. Estratégia de Identificação de Versões
A estratégia mais recomendada e adotada pela indústria de software contemporânea é o **Versionamento Semântico (SemVer - Semantic Versioning)**, estruturado no formato `MAJOR.MINOR.PATCH` (ex: `1.0.0`).
**Justificativa para adoção:**
* **Previsibilidade:** Fornece clareza imediata aos desenvolvedores e usuários sobre a natureza das mudanças (se quebram a aplicação, se adicionam recursos ou se apenas corrigem bugs).
* **Automação:** É perfeitamente compatível com pipelines de CI/CD e gerenciadores de pacotes (como npm, Maven, pip).
* **Gestão de Dependências:** Evita o temido "Dependency Hell", garantindo que as atualizações de bibliotecas não quebrem o software.

## 2. A Primeira Versão Pública
**Versão:** `1.0.0`
**Justificativa:** Segundo as diretrizes do SemVer, o desenvolvimento inicial (fase de prototipação e testes internos) acontece na versão `0.x.x`. A liberação da versão `1.0.0` é um marco oficial que indica que a API pública do software está estável e pronta para o uso em ambiente de produção.

## 3. Evolução: Nova Funcionalidade
**Nova Versão:** `1.1.0`
**Justificativa:** A adição de uma nova funcionalidade que mantém a retrocompatibilidade (ou seja, não quebra o que já funcionava) exige o incremento do dígito do meio (`MINOR`), enquanto o dígito de correções (`PATCH`) é zerado.

## 4. Histórico de Evolução (Exemplo Prático 1)
Partindo da versão `1.1.0`, o sistema passou por uma série de atualizações:
1. Três correções de bugs em série: O `PATCH` é incrementado (`1.1.1`, `1.1.2`, `1.1.3`).
2. Duas novas funcionalidades compatíveis: O `MINOR` é incrementado sequencialmente e o `PATCH` zera (`1.2.0`, `1.3.0`).
3. Mais duas correções de bugs: O `PATCH` volta a subir (`1.3.1`, `1.3.2`).
**A versão final e mais recente neste cenário é a `1.3.2`.**

## 5. Histórico de Evolução Crítica (Exemplo Prático 2)
Uma nova versão exigiu mudanças estruturais na API quebrando a compatibilidade:
* **Mudança Crítica (Breaking Change):** O `MAJOR` é incrementado para `2.0.0`.
* **Adição de Funcionalidade:** O `MINOR` sobe para `2.1.0`.
* **Duas Correções de Bugs:** O `PATCH` sobe para `2.1.1` e, logo após, para `2.1.2`.
O histórico linear desse ciclo fica documentado como: `2.0.0` → `2.1.0` → `2.1.1` → `2.1.2`.

## 6. Nomenclatura de Fases de Lançamento (Release Candidates)
Além da numeração, o mercado utiliza sufixos para denotar a maturidade da versão:
* **Alpha (`-alpha`):** (Ex: *Python 3.12.0a1*). Versão de testes primária, altamente instável, com foco em encontrar falhas estruturais.
* **Beta (`-beta`):** (Ex: *Ubuntu 24.04 Beta*). O escopo de funcionalidades está fechado (Feature Freeze), mas ainda contém bugs conhecidos. Liberada para um público maior testar.
* **Release Candidate (`-rc`):** (Ex: *Linux Kernel 6.8-rc1*). Considerada praticamente estável e com potencial para ser a versão final, pendente apenas de testes de regressão finais.