import { useState, useEffect } from "react"
import "./App.css"

function App() {
    const [tasks, setTasks] = useState([])
    const [newDescription, setNewDescription] = useState("")
    const [filter, setFilter] = useState("all")

    useEffect(() => {
        fetchTasks()
    }, [])

    function fetchTasks() {
        fetch("http://localhost:8080/tasks")
            .then(res => res.json())
            .then(data => setTasks(data))
    }

    function addTask() {
        if (newDescription.trim() === "") return
        fetch("http://localhost:8080/tasks", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ description: newDescription })
        })
            .then(res => res.json())
            .then(task => {
                setTasks([...tasks, task])
                setNewDescription("")
            })
    }

    function deleteTask(id) {
        fetch(`http://localhost:8080/tasks/${id}`, { method: "DELETE" })
            .then(() => setTasks(tasks.filter(t => t.id !== id)))
    }

    function markStatus(id, status) {
        fetch(`http://localhost:8080/tasks/${id}/status`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ status })
        })
            .then(res => res.json())
            .then(updated => setTasks(tasks.map(t => t.id === id ? updated : t)))
    }

    const filteredTasks = filter === "all"
        ? tasks
        : tasks.filter(t => t.status === filter)

    return (
        <div className="container">
            <h1>Task Tracker</h1>

            <div className="add-task">
                <input
                    type="text"
                    placeholder="New task description..."
                    value={newDescription}
                    onChange={e => setNewDescription(e.target.value)}
                    onKeyDown={e => e.key === "Enter" && addTask()}
                />
                <button onClick={addTask}>Add</button>
            </div>

            <div className="filters">
                {["all", "todo", "in-progress", "done"].map(f => (
                    <button
                        key={f}
                        className={filter === f ? "active" : ""}
                        onClick={() => setFilter(f)}
                    >{f}</button>
                ))}
            </div>

            <div className="task-list">
                {filteredTasks.length === 0 && <p>No tasks found.</p>}
                {filteredTasks.map(task => (
                    <div key={task.id} className={`task-card ${task.status}`}>
                        <div className="task-info">
                            <p className="task-description">{task.description}</p>
                            <span className={`badge ${task.status}`}>{task.status}</span>
                        </div>
                        <div className="task-actions">
                            <select
                                value={task.status}
                                onChange={e => markStatus(task.id, e.target.value)}
                            >
                                <option value="todo">todo</option>
                                <option value="in-progress">in-progress</option>
                                <option value="done">done</option>
                            </select>
                            <button
                                className="delete"
                                onClick={() => deleteTask(task.id)}
                            >Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default App