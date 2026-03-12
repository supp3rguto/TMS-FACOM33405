package com.listatarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validações do Gerenciador de Tarefas (TaskManager)")
public class TaskManagerTest {

    private TaskManager gerenciador;
    private Task tarefaA;
    private Task tarefaB;

    @BeforeEach
    void setupAmbiente() {
        gerenciador = new TaskManager();
        tarefaA = new Task("Estudar JUnit");
        tarefaB = new Task("Fazer o relatorio");
    }

    @Test
    @DisplayName("Garante a inclusão de uma nova task na lista")
    void validaInclusaoDeTarefa() {
        gerenciador.addTask(tarefaA);
        assertEquals(1, gerenciador.getTasks().size());
        assertTrue(gerenciador.getTasks().contains(tarefaA));
    }

    @Test
    @DisplayName("Sistema deve barrar a inserção de tarefas duplicadas")
    void validaBloqueioDeTarefasRepetidas() {
        Task tarefaRepetida = new Task("Estudar JUnit");
        gerenciador.addTask(tarefaA);
        gerenciador.addTask(tarefaRepetida);

        assertEquals(1, gerenciador.getTasks().size(), "Falhou: Permitiu adicionar task com a mesma descrição");
    }

    @Test
    @DisplayName("Garante a remoção bem-sucedida de uma tarefa existente")
    void validaExclusaoDeTarefa() {
        gerenciador.addTask(tarefaA);
        gerenciador.addTask(tarefaB);

        gerenciador.removeTask(tarefaA);

        assertEquals(1, gerenciador.getTasks().size());
        assertFalse(gerenciador.getTasks().contains(tarefaA));
        assertTrue(gerenciador.getTasks().contains(tarefaB));
    }

    @Test
    @DisplayName("Verifica a alteração de status para CONCLUÍDA")
    void verificaMarcacaoDeConclusaoIndividual() {
        gerenciador.addTask(tarefaA);
        gerenciador.markTaskAsCompleted(tarefaA);

        assertEquals(TaskStatus.COMPLETED, tarefaA.getStatus());
    }

    @Test
    @DisplayName("Garante o comando de concluir todas as pendências de uma vez")
    void verificaMarcacaoDeConclusaoGeral() {
        gerenciador.addTask(tarefaA);
        gerenciador.addTask(tarefaB);

        gerenciador.markAllAsCompleted();

        assertTrue(gerenciador.getTasks().stream().allMatch(t -> t.getStatus() == TaskStatus.COMPLETED));
    }

    @Test
    @DisplayName("Valida as regras do filtro de busca por status")
    void validaFiltrosDeStatus() {
        gerenciador.addTask(tarefaA);
        gerenciador.addTask(tarefaB);
        gerenciador.markTaskAsCompleted(tarefaA);

        List<Task> listaPendentes = gerenciador.filterByStatus(TaskStatus.PENDING);
        List<Task> listaConcluidas = gerenciador.filterByStatus(TaskStatus.COMPLETED);

        assertEquals(1, listaPendentes.size());
        assertEquals("Fazer o relatorio", listaPendentes.get(0).getDescription());

        assertEquals(1, listaConcluidas.size());
        assertEquals("Estudar JUnit", listaConcluidas.get(0).getDescription());
    }

    @Test
    @DisplayName("Valida algoritmo de ordenação cronológica das tarefas")
    void verificaOrdenacaoPorDataDeCriacao() throws InterruptedException {
        Task taskPrimaria = new Task("Primeiro a ser feito");
        gerenciador.addTask(taskPrimaria);

        Thread.sleep(15); // Pausa de segurança

        Task taskSecundaria = new Task("Segundo a ser feito");
        gerenciador.addTask(taskSecundaria);

        // Desorganizando de propósito
        gerenciador.getTasks().clear();
        gerenciador.addTask(taskSecundaria);
        gerenciador.addTask(taskPrimaria);

        List<Task> listaOrdenada = gerenciador.getTasksSortedByCreationDate();

        assertEquals("Primeiro a ser feito", listaOrdenada.get(0).getDescription());
        assertEquals("Segundo a ser feito", listaOrdenada.get(1).getDescription());
    }

    @Test
    @DisplayName("Garante integridade de leitura e escrita de dados em arquivo físico")
    void validaPersistenciaDeDadosNoDisco(@TempDir Path diretorioTemporario) throws IOException, ClassNotFoundException {
        File arquivoDump = diretorioTemporario.resolve("meus_dados.dat").toFile();

        // 1. Popula e salva no disco
        gerenciador.addTask(tarefaA);
        gerenciador.addTask(tarefaB);
        gerenciador.markTaskAsCompleted(tarefaA);
        gerenciador.saveTasksToFile(arquivoDump.getAbsolutePath());

        // 2. Cria instância zerada
        TaskManager gerenciadorRestaurado = new TaskManager();
        assertNotEquals(2, gerenciadorRestaurado.getTasks().size(), "Instância nova já deveria nascer limpa");

        // 3. Puxa os dados do disco
        gerenciadorRestaurado.loadTasksFromFile(arquivoDump.getAbsolutePath());

        // 4. Bateria de verificações de integridade
        assertEquals(2, gerenciadorRestaurado.getTasks().size());
        assertEquals("Estudar JUnit", gerenciadorRestaurado.getTasks().get(0).getDescription());
        assertEquals(TaskStatus.COMPLETED, gerenciadorRestaurado.getTasks().get(0).getStatus());
        assertEquals("Fazer o relatorio", gerenciadorRestaurado.getTasks().get(1).getDescription());
        assertEquals(TaskStatus.PENDING, gerenciadorRestaurado.getTasks().get(1).getStatus());
    }
}