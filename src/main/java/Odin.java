import java.util.Scanner;

public class Odin {
    public static void printHorizontalLine(){
        System.out.println("__________________________________________");
    }
    public static void main(String[] args) {
        TaskList t = new TaskList();

        printHorizontalLine();
        System.out.println("Hello! I'm Odin");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                printHorizontalLine();
                t.printTasks();
                printHorizontalLine();
            } else {
                t.addTask(input);
                printHorizontalLine();
                System.out.println("added: " + input);
                printHorizontalLine();
            }
        }

        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
