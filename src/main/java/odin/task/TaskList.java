package odin.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(true);
    }

    public void unmarkTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(false);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(taskIndex);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
