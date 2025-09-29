package odin;

import odin.exceptions.IllegalFileException;
import odin.parser.Parser;
import odin.storage.Storage;
import odin.task.TaskList;
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
        Ui.printIntroMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            Ui.printHorizontalLine();
            if (Parser.handleInput(input, taskList, storage)) return;
            Ui.printHorizontalLine();
        }
    }

}
