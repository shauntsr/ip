package odin.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + DELIM + super.toFileString() + DELIM + from + DELIM + to;
    }

    public static Event fromFileString(String fileString) {
        String[] splitFileString = fileString.split(DELIM);
        Event event = new Event(splitFileString[2], splitFileString[3], splitFileString[4]);

        if (splitFileString[1].equals("1")) {
            event.setIsDone(true);
        }

        return event;
    }
}
