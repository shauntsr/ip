package odin.task;

public class ToDo extends Task {
    public ToDo(String description) {
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

    public static ToDo fromFileString(String fileString) {
        String[] splitFileString = fileString.split(DELIM);
        ToDo toDo = new ToDo(splitFileString[2]);

        if (splitFileString[1].equals("1")) {
            toDo.setIsDone(true);
        }

        return toDo;
    }
}
