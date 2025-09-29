package odin;

import odin.task.Deadline;
import odin.task.Event;
import odin.task.TaskList;
import odin.task.Todo;

import java.io.IOException;
import java.util.Scanner;

public class Odin {
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Error:\n   " + errorMessage);
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList;

        try {
            storage.init();
            taskList = storage.loadTasks();
        } catch (IllegalFileException | IOException e) {
            printErrorMessage(e.getMessage());
            return;
        }

        // Introduction
        printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            printHorizontalLine();
            if (handleInput(input, taskList, storage)) return;
            printHorizontalLine();
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
                printHorizontalLine();
                return true;
            case "list":
                System.out.println("These are your tasks.");
                taskList.printTasks();
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
            printErrorMessage(e.getMessage());
        } catch (NullPointerException e) {
            printErrorMessage("Missing item to be added.");
        } catch (NumberFormatException e) {
            printErrorMessage("Index should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("Index is not valid.");
        }

        return false;
    }

    private static void addTodo(TaskList taskList, String taskDetails) {
        if (taskDetails == null) {
            throw new NullPointerException();
        }

        Todo toDo = new Todo(taskDetails);
        taskList.addTask(toDo);
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
    }

    private static void handleUnmark(String input, TaskList taskList) {
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(unmarkIndex);
    }

    private static void handleMark(String input, TaskList taskList) {
        int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markTask(markIndex);
    }

    private static void handleDelete(String input, TaskList taskList) {
        int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.deleteTask(markIndex);
    }

}
