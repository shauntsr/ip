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

public class Parser {
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

    private static void addTodo(TaskList taskList, String taskDetails) {
        if (taskDetails == null) {
            throw new NullPointerException("Missing task details.");
        }

        Todo toDo = new Todo(taskDetails);
        taskList.addTask(toDo);
        Ui.printTaskAddedMessage(taskList);
        Ui.printTaskCount(taskList);
    }

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

    private static void handleUnmark(String input, TaskList taskList) {
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(unmarkIndex);
        Ui.printTaskUnmarkedMessage(taskList, unmarkIndex);
    }

    private static void handleMark(String input, TaskList taskList) {
        int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markTask(markIndex);
        Ui.printTaskMarkedMessage(taskList, markIndex);
    }

    private static void handleDelete(String input, TaskList taskList) {
        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        Ui.printTaskDeletedMessage(taskList, deleteIndex);
        taskList.deleteTask(deleteIndex);
        Ui.printTaskCount(taskList);
    }

    private static void findTasks(TaskList taskList, String commandParams) {
        if (commandParams == null) {
            throw new NullPointerException("Query is missing.");
        }
        ArrayList<Integer> taskIndices = taskList.findTask(commandParams);
        Ui.printFoundTasks(taskList, taskIndices);
    }
}
