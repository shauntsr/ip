package odin.task;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a collection of {@link Task} objects.
 * It provides methods for adding, removing, marking, unmarking, finding,
 * and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a new {@link Task} to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task by setting as done.
     *
     * @param taskIndex The index of the task to mark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(true);
    }

    /**
     * Unmarks a task by setting as not done.
     *
     * @param taskIndex The index of the task to unmark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void unmarkTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(false);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to delete.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(taskIndex);
    }

    /**
     * Finds all tasks in the list whose description contains the given query (case-insensitive).
     *
     * @param query The search query string.
     * @return A list of indices representing the positions of matching tasks.
     */
    public ArrayList<Integer> findTask(String query) {
        ArrayList<Integer> taskIndices = new ArrayList<Integer>();

        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                taskIndices.add(index);
            }
        }
        return taskIndices;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Retrieves a task at a given index.
     *
     * @param index The index of the task to retrieve.
     * @return The {@link Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
