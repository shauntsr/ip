package odin.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by.format(taskDateFormat) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + DELIM + super.toFileString() + DELIM + by;
    }

    public static Deadline fromFileString(String fileString) {
        String[] splitFileString = fileString.split(" \\| ");
        Deadline deadline = new Deadline(splitFileString[2], LocalDate.parse(splitFileString[3]));

        if (splitFileString[1].equals("1")) {
            deadline.setIsDone(true);
        }

        return deadline;
    }
}
