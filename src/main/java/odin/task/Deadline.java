package odin.task;

import java.time.LocalDate;

/**
 * Represents a task that must be completed by a specific date.
 * <p>
 * A {@code Deadline} stores a single {@link LocalDate} value representing
 * the due date, in addition to the description and completion status
 * inherited from {@link Task}.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a {@link Deadline} with the given description and due date.
     *
     * @param description Description of the deadline task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline for display.
     * <p>
     * Format example:
     * <pre>
     * [D][ ] Submit report (by Oct 04 2025)
     * </pre>
     *
     * @return The formatted deadline string showing type, status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by.format(taskDateFormat) + ")";
    }

    /**
     * Returns a string representation of the deadline suitable for file storage.
     * <p>
     * Format example:
     * <pre>
     * D | 0 | Submit report | 2025-10-04
     * </pre>
     *
     * @return A formatted string containing the deadline’s type, status, description, and due date.
     */
    @Override
    public String toFileString() {
        return "D" + DELIM + super.toFileString() + DELIM + by;
    }

    /**
     * Reconstructs a {@link Deadline} object from its stored file representation.
     * <p>
     * Expected format:
     * <pre>
     * D | STATUS | DESCRIPTION | BY
     * </pre>
     * where {@code STATUS} is {@code 1} if the deadline is done, and {@code 0} otherwise.
     *
     * @param fileString The line of text representing the deadline in the save file.
     * @return The reconstructed {@link Deadline} object.
     * @throws java.time.format.DateTimeParseException if the stored date is not in ISO format.
     */
    public static Deadline fromFileString(String fileString) {
        String[] splitFileString = fileString.split(" \\| ");
        Deadline deadline = new Deadline(splitFileString[2], LocalDate.parse(splitFileString[3]));

        if (splitFileString[1].equals("1")) {
            deadline.setIsDone(true);
        }

        return deadline;
    }
}
