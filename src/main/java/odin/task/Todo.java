package odin.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + DELIM + super.toFileString();
    }

    public static Todo fromFileString(String fileString) {
        String[] splitFileString = fileString.split(" \\| ");
        Todo toDo = new Todo(splitFileString[2]);

        if (splitFileString[1].equals("1")) {
            toDo.setIsDone(true);
        }

        return toDo;
    }
}
