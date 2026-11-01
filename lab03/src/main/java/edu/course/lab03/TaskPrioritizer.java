package edu.course.lab03;

public class TaskPrioritizer {
    private final PriorityPolicy priorityPolicy;

    public TaskPrioritizer(PriorityPolicy priorityPolicy) {
        if (priorityPolicy == null) {
            throw new IllegalArgumentException("Policy must not be null");
        }
        this.priorityPolicy = priorityPolicy;
    }

    public ProjectTask chooseHigherPriority(ProjectTask task1, ProjectTask task2) {
        if (task1 == null || task2 == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }

        int priority1 = priorityPolicy.calculatePriority(task1);
        int priority2 = priorityPolicy.calculatePriority(task2);

        if (priority1 >= priority2) {
            return task1;
        } else {
            return task2;
        }
    }
}
