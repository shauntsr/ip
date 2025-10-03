package odin.storage;

import odin.exceptions.IllegalFileException;
import odin.task.Deadline;
import odin.task.Event;
import odin.task.Task;
import odin.task.TaskList;
import odin.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Storage} class manages saving and loading tasks
 * from a persistent file. It supports initialization of the save file,
 * parsing tasks from file strings, and writing updated task lists back to disk.
 */
public class Storage {
    static final String DEFAULT_FILE_PATH = "./data/tasks.txt";
    private String filePath;

    /**
     * Creates a {@code Storage} instance with the default file path.
     */
    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    /**
     * Creates a {@code Storage} instance with a custom file path.
     *
     * @param filePath The path to the file where tasks should be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a single line from the save file into a {@link Task}.
     *
     * @param fileString The raw string representation of a task from the file.
     * @return The reconstructed {@link Task} object.
     * @throws IllegalFileException If the string format is invalid or task type is unrecognized.
     */
    private Task handleFileString(String fileString) throws IllegalFileException {
        char taskType = fileString.charAt(0);

        return switch (taskType) {
            case 'T' -> Todo.fromFileString(fileString);
            case 'D' -> Deadline.fromFileString(fileString);
            case 'E' -> Event.fromFileString(fileString);
            default -> throw new IllegalFileException("Incorrect file format.");
        };

    }

    /**
     * Initialises the storage by ensuring the save file exists.
     * <ul>
     * <li> If the file exists, do nothing </li>
     * <li> If the file does not exist, creates a new directory (if needed) and save file. </li>
     * </ul>
     *
     * @throws IOException If
     */
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

    /**
     * Loads all tasks from the save file into a {@link TaskList}.
     *
     * @return A {@link TaskList} containing all tasks read from the file.
     * @throws FileNotFoundException If the save file does not exist.
     * @throws IllegalFileException  If a line in the file cannot be parsed into a task.
     */
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

    /**
     * Saves all tasks in the {@link TaskList} to the save file.
     * Each task is written in its serialized format on a new line.
     *
     * @param taskList The list of tasks to save.
     * @param listSize The number of tasks to save (useful if list is partially filled).
     * @throws IOException If the file cannot be written to.
     */
    public void saveTasks(TaskList taskList, int listSize) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (int i = 0; i < listSize; i++) {
            writer.write(taskList.getTask(i).toFileString());
            writer.newLine();
        }

        writer.close();
    }

}
