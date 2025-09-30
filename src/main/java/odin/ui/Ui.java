package odin.ui;

import odin.task.Task;
import odin.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Error:\n   " + errorMessage);
    }

    public static void printTasks(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            Task currTask = taskList.getTask(i);
            int taskIndex = i + 1;
            System.out.println(taskIndex + ". " + currTask.toString());
        }
    }

    public static void printTaskAddedMessage(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        System.out.println("Successfully added:");
        System.out.println(taskList.getTask(taskCount - 1));
    }

    public static void printTaskMarkedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Nice! Another task down!");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    public static void printTaskUnmarkedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Please tell me it was a misinput.");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    public static void printTaskDeletedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Deleting task " + (taskIndex + 1) + "...");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    public static void printTaskCount(TaskList taskList) {
        System.out.println("You now have " + taskList.getTaskCount() + " tasks.");
    }

    public static void printFoundTasks(TaskList taskList, ArrayList<Integer> taskIndices) {
        System.out.println("Querying...");
        if (taskIndices.isEmpty()) {
            System.out.println("Could not find matching task.");
            return;
        }

        System.out.println(taskIndices.size() + " matching task(s) were found in your list.");
        int count = 0;
        for (int taskIndex : taskIndices) {
            System.out.println((count + 1) + ". " + taskList.getTask(taskIndex));
            count++;
        }
    }

    public static void printIntroMessage() {
        Ui.printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        Ui.printHorizontalLine();
    }
}
