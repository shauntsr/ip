public class TaskList {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void addTask(String description) {
        taskList[taskCount] = new Task(description);
        taskCount++;
    }

    public void markTask(int taskIndex) {
        taskList[taskIndex].setIsDone(true);
    }

    public void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            Task currTask = taskList[i];
            System.out.println(i+1 + ". " + currTask.getStatusIcon() + " " +  currTask.getDescription());
        }
    }
}
