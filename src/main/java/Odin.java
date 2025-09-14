import java.util.Scanner;

public class Odin {
    public static void printHorizontalLine() {
        System.out.println("__________________________________________");
    }

    public static void main(String[] args) {
        TaskList t = new TaskList();

        // Introduction
        printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            printHorizontalLine();
            if (handleInput(input, t)) return;
            printHorizontalLine();
        }
    }

    public static boolean handleInput(String input, TaskList t) {
        String command = input.split(" ")[0];

        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            printHorizontalLine();
            return true;
        case "list":
            System.out.println("These are your tasks.");
            t.printTasks();
            break;
        case "mark":
            int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            t.markTask(markIndex);
            break;
        case "unmark":
            int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            t.unmarkTask(unmarkIndex);
            break;
        default:
            t.addTask(input);
            System.out.println("added: " + input);
        }
        return false;
    }

}
