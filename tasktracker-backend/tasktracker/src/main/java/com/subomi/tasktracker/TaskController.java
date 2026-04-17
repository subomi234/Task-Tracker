package com.subomi.tasktracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String status) {
        if (status != null) {
            return taskService.getTasksByStatus(status);
        }
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody AddTaskRequest request) {
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskService.addTask(request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,
                                           @RequestBody AddTaskRequest request) {
        Task task = taskService.updateTask(id, request.getDescription());
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (!taskService.deleteTask(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> markStatus(@PathVariable int id,
                                           @RequestBody StatusRequest request) {
        List<String> valid = List.of("todo", "in-progress", "done");
        if (!valid.contains(request.getStatus())) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskService.markStatus(id, request.getStatus());
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    // inner classes to represent request bodies
    public static class AddTaskRequest {
        private String description;
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class StatusRequest {
        private String status;
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}