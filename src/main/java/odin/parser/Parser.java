package odin.parser;

import odin.exceptions.IllegalTaskException;
import odin.storage.Storage;
import odin.task.Deadline;
import odin.task.Event;
import odin.task.TaskList;
import odin.task.Todo;
import odin.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The {@code Parse} class handles parsing of user input commands
 */
public class Parser {
    /**
     * Handles a single user input by parsing the command keyword
     * and executing the corresponding actions.
     *
     * @param input    The raw input string entered by the user.
     * @param taskList The current list of tasks.
     * @param storage  The storage object used to handle persistence of tasks.
     * @return {@code true} If the command was "bye" (to exit the program), {@code false} otherwise.
     */
    public static boolean handleInput(String input, TaskList taskList, Storage storage) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String commandParams = splitInput.length > 1 ? splitInput[1] : null;

        try {
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                Ui.printHorizontalLine();
                return true;
            case "list":
                System.out.println("These are your tasks.");
                Ui.printTasks(taskList);
                break;
            case "mark":
                handleMark(input, taskList);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "unmark":
                handleUnmark(input, taskList);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "delete":
                handleDelete(input, taskList);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "todo":
                addTodo(taskList, commandParams);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "deadline":
                addDeadline(taskList, commandParams);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "event":
                addEvent(taskList, commandParams);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "find":
                findTasks(taskList, commandParams);
                break;
            default:
                System.out.println("Easter egg found.");
            }
        } catch (IllegalTaskException | IOException | NullPointerException e) {
            Ui.printErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printErrorMessage("Index should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            Ui.printErrorMessage("Index is not valid.");
        } catch (DateTimeParseException e) {
            Ui.printErrorMessage("Time should be of format yyyy-MM-dd");
        }

        return false;
    }

    /**
     * Adds a new Todo to the task list.
     *
     * @param taskList    The tasklist to add the new task to.
     * @param taskDetails The description of the Deadline task.
     * @throws NullPointerException If taskDetails is {@code null}
     */
    private static void addTodo(TaskList taskList, String taskDetails) {
        if (taskDetails == null) {
            throw new NullPointerException("Missing task details.");
        }

        Todo toDo = new Todo(taskDetails);
        taskList.addTask(toDo);
        Ui.printTaskAddedMessage(taskList);
        Ui.printTaskCount(taskList);
    }

    /**
     * Adds a new Deadline to the task list.
     *
     * @param taskList    The tasklist to add the new task to.
     * @param taskDetails The description of the Deadline task.
     * @throws NullPointerException If taskDetails is {@code null}
     * @throws IllegalTaskException If input format is invalid.
     */
    private static void addDeadline(TaskList taskList, String taskDetails) throws IllegalTaskException {
        if (taskDetails == null) {
            throw new NullPointerException("Missing task details.");
        }

        String[] splitDeadlineInput = taskDetails.split(" /by ");
        if (splitDeadlineInput.length != 2) {
            throw new IllegalTaskException("odin.task.Deadline should follow the format: TASK /by DEADLINE");
        }
        Deadline deadline = new Deadline(splitDeadlineInput[0], LocalDate.parse(splitDeadlineInput[1]));
        taskList.addTask(deadline);
        Ui.printTaskAddedMessage(taskList);
        Ui.printTaskCount(taskList);
    }

    /**
     * Adds a new Event to the task list.
     *
     * @param taskList    The tasklist to add the new task to.
     * @param taskDetails The description of the Event task.
     * @throws NullPointerException If taskDetails is {@code null}
     * @throws IllegalTaskException If input format is invalid.
     */
    private static void addEvent(TaskList taskList, String taskDetails) throws IllegalTaskException {
        if (taskDetails == null) {
            throw new NullPointerException("Missing task details.");
        }

        String[] splitEventInput = taskDetails.split(" /from ");
        if (splitEventInput.length < 2) {
            throw new IllegalTaskException("Missing start time.");
        }
        String[] splitEventTimings = splitEventInput[1].split(" /to ");
        if (splitEventTimings.length < 2) {
            throw new IllegalTaskException("Missing end time.");
        }
        LocalDate from = LocalDate.parse(splitEventTimings[0]);
        LocalDate to = LocalDate.parse(splitEventTimings[1]);
        Event event = new Event(splitEventInput[0], from, to);
        taskList.addTask(event);
        Ui.printTaskAddedMessage(taskList);
        Ui.printTaskCount(taskList);
    }

    /**
     * Marks a task as not done by unmarking it.
     *
     * @param input    The full input string containing the command and index.
     * @param taskList The task list
     * @throws NumberFormatException     if the provided index is not an integer.
     * @throws IndexOutOfBoundsException if the index is not valid for the task list.
     *
     */
    private static void handleUnmark(String input, TaskList taskList) {
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(unmarkIndex);
        Ui.printTaskUnmarkedMessage(taskList, unmarkIndex);
    }

    /**
     * Marks a task as done by marking it.
     *
     * @param input    The full input string containing the command and index.
     * @param taskList The task list
     * @throws NumberFormatException     if the provided index is not an integer.
     * @throws IndexOutOfBoundsException if the index is not valid for the task list.
     */
    private static void handleMark(String input, TaskList taskList) {
        int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markTask(markIndex);
        Ui.printTaskMarkedMessage(taskList, markIndex);
    }

    /**
     * Deletes a task from tasklist.
     *
     * @param input    The full input string containing the command and index.
     * @param taskList The task list
     * @throws NumberFormatException     If the provided index is not an integer.
     * @throws IndexOutOfBoundsException If the index is not valid for the task list.
     */
    private static void handleDelete(String input, TaskList taskList) {
        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        Ui.printTaskDeletedMessage(taskList, deleteIndex);
        taskList.deleteTask(deleteIndex);
        Ui.printTaskCount(taskList);
    }

    /**
     * Finds all tasks in the task list that match the given search query.
     *
     * @param taskList      The task list to search through.
     * @param commandParams The search keyword to look for.
     * @throws NullPointerException If {@code commandParams} is {@code null}.
     */
    private static void findTasks(TaskList taskList, String commandParams) {
        if (commandParams == null) {
            throw new NullPointerException("Query is missing.");
        }
        ArrayList<Integer> taskIndices = taskList.findTask(commandParams);
        Ui.printFoundTasks(taskList, taskIndices);
    }
}
