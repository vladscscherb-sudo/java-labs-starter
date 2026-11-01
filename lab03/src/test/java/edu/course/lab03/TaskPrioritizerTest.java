package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskPrioritizerTest {

    @Test
    void effortPolicy_ChoosesTaskWithMoreHours() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        ProjectTask shortTask = new ProjectTask("T-1", "Short", 2);
        ProjectTask longTask = new ProjectTask("T-2", "Long", 10);

        ProjectTask result = prioritizer.chooseHigherPriority(shortTask, longTask);
        assertEquals("T-2", result.getId());
    }

    @Test
    void effortPolicy_ReturnsFirstIfEqual() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        ProjectTask task1 = new ProjectTask("T-1", "First", 5);
        ProjectTask task2 = new ProjectTask("T-2", "Second", 5);

        ProjectTask result = prioritizer.chooseHigherPriority(task1, task2);
        assertEquals("T-1", result.getId());
    }

    @Test
    void statusPolicy_ChoosesUnfinishedTask() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());

        ProjectTask doneTask = new ProjectTask("T-1", "Done", 5);
        doneTask.changeStatus(TaskStatus.DONE);

        ProjectTask newTask = new ProjectTask("T-2", "New", 5);

        ProjectTask result = prioritizer.chooseHigherPriority(doneTask, newTask);
        assertEquals("T-2", result.getId());
    }

    @Test
    void statusPolicy_ReturnsFirstIfBothDone() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());

        ProjectTask task1 = new ProjectTask("T-1", "First", 5);
        task1.changeStatus(TaskStatus.DONE);
        ProjectTask task2 = new ProjectTask("T-2", "Second", 5);
        task2.changeStatus(TaskStatus.DONE);

        ProjectTask result = prioritizer.chooseHigherPriority(task1, task2);
        assertEquals("T-1", result.getId());
    }

    @Test
    void polymorphismTest_ChangesPolicyWithoutChangingPrioritizer() {
        ProjectTask taskShort = new ProjectTask("T-1", "Short", 2);
        ProjectTask taskLong = new ProjectTask("T-2", "Long", 10);

        ProjectTask doneLong = new ProjectTask("T-3", "DoneLong", 10);
        doneLong.changeStatus(TaskStatus.DONE);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals("T-2", prioritizer.chooseHigherPriority(taskShort, taskLong).getId());

        prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());
        assertEquals("T-1", prioritizer.chooseHigherPriority(taskShort, doneLong).getId());
    }
}
