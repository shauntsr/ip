package odin.ui;

import odin.task.Task;
import odin.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code Ui} class handles all user interface outputs for the Odin program.
 * It provides methods to display messages, errors, and task information on the console.
 */
public class Ui {
    /**
     * Prints a horizontal separator line to the console.
     */
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
    }

    /**
     * Prints an error message with a standard prefix.
     *
     * @param errorMessage The error message to display.
     */
    public static void printErrorMessage(String errorMessage) {
        System.out.println("Error:\n   " + errorMessage);
    }

    /**
     * Prints all tasks in the provided {@link TaskList}, each with its corresponding index.
     *
     * @param taskList The list of tasks to display.
     */
    public static void printTasks(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            Task currTask = taskList.getTask(i);
            int taskIndex = i + 1;
            System.out.println(taskIndex + ". " + currTask.toString());
        }
    }

    /**
     * Prints a message confirming that a new task has been successfully added.
     *
     * @param taskList The list containing the newly added task.
     */
    public static void printTaskAddedMessage(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        System.out.println("Successfully added:");
        System.out.println(taskList.getTask(taskCount - 1));
    }

    /**
     * Prints a message when a task has been marked as done.
     *
     * @param taskList  The task list containing the marked task.
     * @param taskIndex The index of the task that was marked.
     */
    public static void printTaskMarkedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Nice! Another task down!");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    /**
     * Prints a message when a task has been unmarked (set to not done).
     *
     * @param taskList  The task list containing the unmarked task.
     * @param taskIndex The index of the task that was unmarked.
     */
    public static void printTaskUnmarkedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Task unmarked. Please tell me it was a misinput.");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param taskList  The task list containing the deleted task.
     * @param taskIndex The index of the deleted task.
     */
    public static void printTaskDeletedMessage(TaskList taskList, int taskIndex) {
        System.out.println("Deleting task " + (taskIndex + 1) + "...");
        System.out.println("   " + taskList.getTask(taskIndex).toString());
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The task list whose size is being reported.
     */
    public static void printTaskCount(TaskList taskList) {
        System.out.println("You now have " + taskList.getTaskCount() + " tasks.");
    }

    /**
     * Displays the tasks that match a given search query.
     * If no matching tasks are found, prints a message indicating that.
     *
     * @param taskList    The list of tasks to search from.
     * @param taskIndices The indices of tasks that match the query.
     */
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

    /**
     * Prints the introductory message displayed when the program starts.
     */
    public static void printIntroMessage() {
        Ui.printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        Ui.printHorizontalLine();
    }
}
