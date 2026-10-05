package edu.course.lab02;

public class ProjectTask {
    private final String id;
    private final String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, int estimatedHours) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id must not be null or empty");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title must not be null or empty");
        }
        if (estimatedHours < 0) {
            throw new IllegalArgumentException("estimatedHours must not be negative");
        }
        this.id = id;
        this.title = title;
        this.status = TaskStatus.NEW;
        this.estimatedHours = estimatedHours;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void changeStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("newStatus must not be null");
        }
        this.status = newStatus;
    }

    public boolean isCompleted() {
        return status == TaskStatus.DONE;
    }

    public void increaseEstimate(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("hours must be positive");
        }
        this.estimatedHours = this.estimatedHours + hours;
    }
}
