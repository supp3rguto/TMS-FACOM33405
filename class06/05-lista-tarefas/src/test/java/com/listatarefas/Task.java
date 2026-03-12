package com.listatarefas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

// enum para o filtro de status
enum TaskStatus {
    PENDING,
    COMPLETED
}

public class Task implements Serializable, Comparable<Task> {

    // ajuda serialização
    private static final long serialVersionUID = 1L;

    private String description;
    private TaskStatus status;
    private LocalDateTime creationDate;

    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.creationDate = LocalDateTime.now();
    }

    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setStatus(TaskStatus status) { this.status = status; }

     //define a ordenação padrão por data de criação.
    @Override
    public int compareTo(Task other) {
        return this.creationDate.compareTo(other.creationDate);
    }

     //métodos equals/hashcode para tratar "tarefas duplicadas".
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        // considera tarefas duplicadas se for a mesma descrição
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
