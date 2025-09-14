public class TaskList {
    static final int MAX_TASKS = 100;
    private Task[] taskList = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void addTask(Task task) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("Successfully added: ");
        System.out.println(taskList[taskCount - 1]);
        System.out.println("You now have " + taskCount + " tasks.");
    }

    public void markTask(int taskIndex) {
        taskList[taskIndex].setIsDone(true);
        System.out.println("Nice! Another task down!");
        System.out.println("   " + taskList[taskIndex].toString());
    }

    public void unmarkTask(int taskIndex) {
        taskList[taskIndex].setIsDone(false);
        System.out.println("Please tell me it was a misinput.");
        System.out.println("   " + taskList[taskIndex].toString());
    }

    public void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            Task currTask = taskList[i];
            int taskIndex = i + 1;
            System.out.println(taskIndex + ". " + currTask.toString());
        }
    }
}
