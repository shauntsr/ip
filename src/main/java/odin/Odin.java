package odin;

import odin.task.Deadline;
import odin.task.Event;
import odin.task.TaskList;
import odin.task.ToDo;

import java.util.Scanner;

public class Odin {
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Error:\n   " + errorMessage);
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        // Introduction
        printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            printHorizontalLine();
            if (handleInput(input, taskList)) return;
            printHorizontalLine();
        }
    }

    public static boolean handleInput(String input, TaskList taskList) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String taskDetails = splitInput.length > 1 ? splitInput[1] : null;

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
            try {
                handleMark(input, taskList);
            } catch (NumberFormatException e) {
                printErrorMessage("Index should be an integer");
            } catch (IndexOutOfBoundsException e) {
                printErrorMessage("Index is not valid.");
            }
            break;
        case "unmark":
            try {
                handleUnmark(input, taskList);
            } catch (NumberFormatException e) {
                printErrorMessage("Index should be an integer");
            } catch (IndexOutOfBoundsException e) {
                printErrorMessage("Index is not valid.");
            }
            break;
        case "delete":
            try {
                handleDelete(input, taskList);
            } catch (NumberFormatException e) {
                printErrorMessage("Index should be an integer");
            } catch (IndexOutOfBoundsException e) {
                printErrorMessage("Index is not valid.");
            }
            break;
        case "todo":
            try {
                addTodo(taskList, taskDetails);
            } catch (NullPointerException e) {
                printErrorMessage("Missing todo item.");
            }
            break;
        case "deadline":
            try {
                addDeadline(taskList, taskDetails);
            } catch (NullPointerException e) {
                printErrorMessage("Missing deadline item.");
            } catch (IllegalTaskException e) {
                printErrorMessage(e.getMessage());
            }
            break;
        case "event":
            try {
                addEvent(taskList, taskDetails);
            } catch (NullPointerException e) {
                printErrorMessage("Missing event item.");
            } catch (IllegalTaskException e) {
                printErrorMessage(e.getMessage());
            }
            break;

        default:
            System.out.println("Easter egg found.");
        }
        return false;
    }

    private static void addTodo(TaskList taskList, String taskDetails) {
        if (taskDetails == null) {
            throw new NullPointerException();
        }

        ToDo toDo = new ToDo(taskDetails);
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
