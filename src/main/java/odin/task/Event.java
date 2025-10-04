package odin.task;

import java.time.LocalDate;

/**
 * Represents an event task that occurs within a specific time range.
 * <p>
 * An {@code Event} stores a start date ({@code from}) and an end date ({@code to})
 * in addition to the task description and completion status inherited from
 * {@link Task}.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an {@link Event} with the given description and time period.
     *
     * @param description Description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event for display.
     * <p>
     * Format example:
     * <pre>
     * [E][X] Project meeting (from: Oct 01 2025 to: Oct 02 2025)
     * </pre>
     *
     * @return The formatted event string showing type, status, description, and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(taskDateFormat) + " to: " + to.format(taskDateFormat) + ")";
    }

    /**
     * Returns a string representation of the event suitable for file storage.
     * <p>
     * Format example:
     * <pre>
     * E | 1 | Project meeting | 2025-10-01 | 2025-10-02
     * </pre>
     *
     * @return A formatted string containing the event’s type, status, description, and date range.
     */
    @Override
    public String toFileString() {
        return "E" + DELIM + super.toFileString() + DELIM + from + DELIM + to;
    }

    /**
     * Reconstructs an {@link Event} object from its stored file representation.
     * <p>
     * Expected format:
     * <pre>
     * E | STATUS | DESCRIPTION | FROM | TO
     * </pre>
     * where {@code STATUS} is {@code 1} if the event is done, and {@code 0} otherwise.
     *
     * @param fileString The line of text representing the event in the save file.
     * @return The reconstructed {@link Event} object.
     * @throws java.time.format.DateTimeParseException if the stored dates are not in ISO format.
     */
    public static Event fromFileString(String fileString) {
        String[] splitFileString = fileString.split(" \\| ");
        LocalDate from = LocalDate.parse(splitFileString[3]);
        LocalDate to = LocalDate.parse(splitFileString[4]);
        Event event = new Event(splitFileString[2], from, to);

        if (splitFileString[1].equals("1")) {
            event.setIsDone(true);
        }

        return event;
    }
}
