import java.util.Scanner;

public class Odin {
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
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
            handleMark(input, taskList);
            break;
        case "unmark":
            handleUnmark(input, taskList);
            break;
        case "todo":
            addTodo(taskList, taskDetails);
            break;
        case "deadline":
            addDeadline(taskList, taskDetails);
            break;
        case "event":
            addEvent(taskList, taskDetails);
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

    private static void addDeadline(TaskList taskList, String taskDetails) {
        String[] splitDeadlineInput = taskDetails.split(" /by ");
        Deadline deadline = new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]);
        taskList.addTask(deadline);
    }

    private static void addEvent(TaskList taskList, String taskDetails) {
        String[] splitEventInput = taskDetails.split(" /from ");
        String[] splitEventTimings = splitEventInput[1].split(" /to ");
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

}
