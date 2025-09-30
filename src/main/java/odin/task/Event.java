package odin.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(taskDateFormat) + " to: " + to.format(taskDateFormat) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + DELIM + super.toFileString() + DELIM + from + DELIM + to;
    }

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
