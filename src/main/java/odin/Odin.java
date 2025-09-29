package odin;

import odin.task.Deadline;
import odin.task.Event;
import odin.task.TaskList;
import odin.task.Todo;
import odin.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Odin {

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList;

        try {
            storage.init();
            taskList = storage.loadTasks();
        } catch (IllegalFileException | IOException e) {
            Ui.printErrorMessage(e.getMessage());
            return;
        }

        // Introduction
        Ui.printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        Ui.printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            Ui.printHorizontalLine();
            if (handleInput(input, taskList, storage)) return;
            Ui.printHorizontalLine();
        }
    }

    public static boolean handleInput(String input, TaskList taskList, Storage storage) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String taskDetails = splitInput.length > 1 ? splitInput[1] : null;

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
                addTodo(taskList, taskDetails);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "deadline":
                addDeadline(taskList, taskDetails);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            case "event":
                addEvent(taskList, taskDetails);
                storage.saveTasks(taskList, taskList.getTaskCount());
                break;
            default:
                System.out.println("Easter egg found.");
            }
        } catch (IllegalTaskException | IOException e) {
            Ui.printErrorMessage(e.getMessage());
        } catch (NullPointerException e) {
            Ui.printErrorMessage("Missing item to be added.");
        } catch (NumberFormatException e) {
            Ui.printErrorMessage("Index should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            Ui.printErrorMessage("Index is not valid.");
        }

        return false;
    }

    private static void addTodo(TaskList taskList, String taskDetails) {
        if (taskDetails == null) {
            throw new NullPointerException();
        }

        Todo toDo = new Todo(taskDetails);
        taskList.addTask(toDo);
        Ui.printTaskAddedMessage(taskList);
    }

    private static void addDeadline(TaskList taskList, String taskDetails) throws IllegalTaskException {
        if (taskDetails == null) {
            throw new NullPointerException();
        }

        String[] splitDeadlineInput = taskDetails.split(" /by ");
        if (splitDeadlineInput.length != 2) {
            throw new IllegalTaskException("odin.task.Deadline should follow the format: TASK /by DEADLINE");
        }
        Deadline deadline = new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]);
        taskList.addTask(deadline);
        Ui.printTaskAddedMessage(taskList);
    }

    private static void addEvent(TaskList taskList, String taskDetails) throws IllegalTaskException {
        if (taskDetails == null) {
            throw new NullPointerException();
        }

        String[] splitEventInput = taskDetails.split(" /from ");
        if (splitEventInput.length < 2) {
            throw new IllegalTaskException("Missing start time.");
        }
        String[] splitEventTimings = splitEventInput[1].split(" /to ");
        if (splitEventTimings.length < 2) {
            throw new IllegalTaskException("Missing end time.");
        }
        Event event = new Event(splitEventInput[0], splitEventTimings[0], splitEventTimings[1]);
        taskList.addTask(event);
        Ui.printTaskAddedMessage(taskList);
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

}
