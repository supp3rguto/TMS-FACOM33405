package com.listatarefas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {

    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void markTaskAsCompleted(Task task) {
        this.tasks.stream()
                .filter(t -> t.equals(task))
                .findFirst()
                .ifPresent(t -> t.setStatus(TaskStatus.COMPLETED));
    }

    public void markAllAsCompleted() {
        this.tasks.forEach(t -> t.setStatus(TaskStatus.COMPLETED));
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return this.tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksSortedByCreationDate() {
        return this.tasks.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void saveTasksToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.tasks);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTasksFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.tasks = (List<Task>) ois.readObject();
        }
    }
}
