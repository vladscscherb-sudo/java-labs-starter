package edu.course.lab03;

public class StatusPriorityPolicy implements PriorityPolicy{
    @Override
    public int calculatePriority(ProjectTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }
        if (task.getStatus() == TaskStatus.DONE) {
            return 0;
        }
        return 100;
    }
}
