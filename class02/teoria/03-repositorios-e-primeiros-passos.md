# Inicialização e Manipulação de Repositórios

## 1. Exploração de Projetos Open Source no GitHub
A análise de repositórios públicos ajuda-nos a compreender a adoção de padrões arquiteturais no mundo real. Durante a atividade, foram selecionados e analisados os seguintes projetos de relevância global:
* **Spring Boot** (`spring-projects/spring-boot`): Framework fundamental para o ecossistema Java, focado na criação de aplicações e APIs corporativas robustas.
* **FreeCodeCamp** (`freeCodeCamp/freeCodeCamp`): Uma das maiores plataformas educacionais open-source, mantida por uma comunidade massiva de programadores.
* **ChatGPT Desktop App** (`lencx/ChatGPT`): Excelente estudo de caso sobre como empacotar aplicações web para desktop nativo utilizando a tecnologia Electron.

## 2. Clonagem de Repositório Remoto
O ato de clonar um projeto permite realizar a transferência integral não apenas do código-fonte, mas de todo o histórico de alterações (*commits*) e ramificações (*branches*).
```bash
# Criar um diretório para organizar os projetos
$mkdir projetos_git$ cd projetos_git

# Executar a clonagem do repositório escolhido
$ git clone [https://github.com/spring-projects/spring-boot](https://github.com/spring-projects/spring-boot)
```

*Observação estrutural:* Após a execução, o Git gera um diretório local chamado `spring-boot/`. Dentro dele reside todo o ecossistema do projeto e a pasta oculta `.git`, que contém os metadados e o banco de dados do repositório.

## 3. Inicializar um Repositório Local (Do Zero)

Para projetos criados localmente, precisamos de instruir o Git a começar a monitorizar a pasta.

```bash
$ mkdir hellogit
$ cd hellogit
$ git init
```

*Nota Técnica:* O comando `git init` é o responsável por injetar o subdiretório oculto `.git` na pasta. A partir deste momento, o motor de versionamento local está ativo e pronto a registar o histórico.

## 4. O Ciclo de Vida dos Ficheiros: De Untracked para Tracked

Foi criada uma estrutura inicial em Java. Neste primeiro momento, os ficheiros são criados no estado "Não Rastreado" (*Untracked*). Usamos o comando `add` para movê-los para a Área de Preparação (*Staging Area*) e o `commit` para consolidá-los no histórico.

```bash
# Adicionar o diretório e os seus ficheiros à Staging Area
$ git add hello/

# Verificar as mudanças a serem submetidas (Opcional, mas recomendado)
$ git status
# A saída indicará: "new file: hello/pom.xml" e "new file: hello/src/main/java/com/mycompany/hello/Hello.java"

# Consolidar a versão inicial no banco de dados do Git
$ git commit -m "Commit inicial: Estrutura base do projeto e ficheiro Hello.java"
```

## 5. Atualização de Código e Fluxo Contínuo

O ficheiro base foi modificado para exibir a clássica mensagem "Hello World" utilizando a interface gráfica do Java (`JOptionPane`).

```java
// Conteúdo do ficheiro Hello.java modificado
import javax.swing.JOptionPane;

public class Hello {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hello World");
    }
}
```

Após guardar as modificações, seguimos o fluxo padrão de versionamento para registar esta evolução no código:

```bash
# O Git identifica a modificação no diretório de trabalho
$ git status
# A saída indicará: "modified: hello/src/main/java/com/mycompany/hello/Hello.java"

# Preparar a alteração
$ git add hello/src/main/java/com/mycompany/hello/Hello.java

# Registar a nova versão no histórico
$ git commit -m "Implementa exibição de Hello World via JOptionPane"
```