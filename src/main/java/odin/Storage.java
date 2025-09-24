package odin;

public class Storage {
    static final String DEFAULT_FILE_PATH = "./data/tasks.txt";
    private String filePath;

    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }


}
