public class TaskList {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void addTask(String description) {
        taskList[taskCount] = new Task(description);
        taskCount++;
    }

    public void markTask(int taskIndex) {
        taskList[taskIndex].setIsDone(true);
        System.out.println("Nice! Another task down!");
        System.out.println("   " + taskList[taskIndex].getDisplayString());
    }

    public void unmarkTask(int taskIndex) {
        taskList[taskIndex].setIsDone(false);
        System.out.println("Please tell me it was a misinput.");
        System.out.println("   " + taskList[taskIndex].getDisplayString());
    }

    public void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            Task currTask = taskList[i];
            System.out.println(i+1 + ". " + currTask.getDisplayString());
        }
    }
}
