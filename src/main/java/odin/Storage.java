package odin;

import odin.task.Deadline;
import odin.task.Event;
import odin.task.Task;
import odin.task.TaskList;
import odin.task.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    static final String DEFAULT_FILE_PATH = "./data/tasks.txt";
    private String filePath;

    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Task handleFileString(String fileString) throws IllegalFileException {
        char taskType = fileString.charAt(0);

        return switch (taskType) {
            case 'T' -> ToDo.fromFileString(fileString);
            case 'D' -> Deadline.fromFileString(fileString);
            case 'E' -> Event.fromFileString(fileString);
            default -> throw new IllegalFileException("Incorrect file format.");
        };

    }

    public void init() throws IOException {
        File file = new File(filePath);

        // If file exists, nothing to do
        if (file.exists()) {
            System.out.println("Save file found.");
            return;
        }

        System.out.println("No save file found. Starting new save file.");
        File parentFile = file.getParentFile();
        parentFile.mkdirs();
        if (file.createNewFile()) {
            System.out.println("New save file created at " + file.getPath());
        }
    }

    public TaskList loadTasks() throws FileNotFoundException, IllegalFileException {
        File file = new File(filePath);

        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String fileString = scanner.nextLine();
            Task task = handleFileString(fileString);
            taskList.addTask(task);
        }

        return taskList;
    }

    public void saveTasks(TaskList taskList, int listSize) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (int i = 0; i < listSize; i++) {
            writer.write(taskList.getTask(i).toFileString());
            writer.newLine();
        }

        writer.close();
    }

}
