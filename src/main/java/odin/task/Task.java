package odin.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a description and completion status.
 * This is the abstract parent class for all specific task types.
 */
public abstract class Task {
    /**
     * Delimiter used in save file formatting.
     */
    public static final String DELIM = " | ";

    /**
     * Formatter for displaying task dates in a readable format.
     */
    protected static final DateTimeFormatter taskDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private String description;
    private boolean isDone = false;

    /**
     * Constructs a {@code Task} with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return {@code "[X]"} if the task is done, {@code "[ ]"} otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isDone {@code true} if the task is done, {@code false} otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The formatted task string showing its status and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the task for file storage.
     * The format is {@code STATUS | DESCRIPTION}, where {@code STATUS}
     * is {@code 1} if the task is done and {@code 0} otherwise.
     *
     * @return A formatted string containing the task's file representation.
     */
    public String toFileString() {
        String fileStatusIcon = isDone ? "1" : "0";
        return fileStatusIcon + DELIM + description;
    }
}
