package odin.task;

/**
 * Represents a basic task.
 * <p>
 * A {@code Todo} simply tracks a description and completion status,
 * inherited from {@link Task}. It is the simplest type of task.
 */
public class Todo extends Task {
    /**
     * Constructs a {@link Todo} with the given description.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do for display.
     * <p>
     * Format example:
     * <pre>
     * [T][X] Buy groceries
     * </pre>
     *
     * @return The formatted string showing type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do suitable for file storage.
     * <p>
     * Format example:
     * <pre>
     * T | 0 | Buy groceries
     * </pre>
     *
     * @return A formatted string containing the to-do’s type, status, and description.
     */
    @Override
    public String toFileString() {
        return "T" + DELIM + super.toFileString();
    }

    /**
     * Reconstructs a {@link Todo} object from its stored file representation.
     * <p>
     * Expected format:
     * <pre>
     * T | STATUS | DESCRIPTION
     * </pre>
     * where {@code STATUS} is {@code 1} if the to-do is done, and {@code 0} otherwise.
     *
     * @param fileString The line of text representing the to-do in the save file.
     * @return The reconstructed {@link Todo} object.
     */
    public static Todo fromFileString(String fileString) {
        String[] splitFileString = fileString.split(" \\| ");
        Todo toDo = new Todo(splitFileString[2]);

        if (splitFileString[1].equals("1")) {
            toDo.setIsDone(true);
        }

        return toDo;
    }
}
