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

            printHorizontalLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printHorizontalLine();
                return;
            }
            if (input.equals("list")) {
                System.out.println("These are your tasks.");
                t.printTasks();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                t.markTask(taskIndex);
            } else {
                t.addTask(input);
                System.out.println("added: " + input);
            }
            printHorizontalLine();
        }
   }
}
