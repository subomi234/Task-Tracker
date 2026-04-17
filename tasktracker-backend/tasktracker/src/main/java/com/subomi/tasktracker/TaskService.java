package com.subomi.tasktracker;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskService {

    private final TaskStorage taskStorage;

    public TaskService(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
        taskStorage.initFile();
        taskStorage.load();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskStorage.getTasks().values());
    }

    public List<Task> getTasksByStatus(String status) {
        List<Task> filtered = new ArrayList<>();
        for (Task t : taskStorage.getTasks().values()) {
            if (t.getStatus().equals(status)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public Task addTask(String description) {
        int newId = taskStorage.getLastTask() + 1;
        taskStorage.setLastTask(newId);
        Task task = new Task(newId, description);
        taskStorage.getTasks().put(newId, task);
        taskStorage.save();
        return task;
    }

    public Task updateTask(int id, String description) {
        Task task = taskStorage.getTasks().get(id);
        if (task == null) return null;
        task.updateDescription(description);
        taskStorage.save();
        return task;
    }

    public boolean deleteTask(int id) {
        if (taskStorage.getTasks().remove(id) == null) return false;
        taskStorage.save();
        return true;
    }

    public Task markStatus(int id, String status) {
        Task task = taskStorage.getTasks().get(id);
        if (task == null) return null;
        task.updateStatus(status);
        taskStorage.save();
        return task;
    }
}