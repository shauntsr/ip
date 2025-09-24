package odin.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Successfully added:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("You now have " + taskList.size() + " tasks.");
    }

    public void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(true);
        System.out.println("Nice! Another task down!");
        System.out.println("   " + taskList.get(taskIndex).toString());
    }

    public void unmarkTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskIndex).setIsDone(false);
        System.out.println("Please tell me it was a misinput.");
        System.out.println("   " + taskList.get(taskIndex).toString());
    }

    public void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            int taskIndex = i + 1;
            System.out.println(taskIndex + ". " + currTask.toString());
        }
    }
}
