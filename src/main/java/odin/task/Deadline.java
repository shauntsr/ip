package odin.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + DELIM + super.toFileString() + DELIM + by;
    }

    public static Deadline fromFileString(String fileString) {
        String[] splitFileString = fileString.split(DELIM);
        Deadline deadline = new Deadline(splitFileString[2], splitFileString[3]);

        if (splitFileString[1].equals("1")) {
            deadline.setIsDone(true);
        }

        return deadline;
    }
}
