package odin.task;

public abstract class Task {
    public static final String DELIM = " | ";

    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toFileString() {
        String fileStatusIcon = isDone ? "1" : "0";
        return fileStatusIcon + DELIM + description;
    }
}
