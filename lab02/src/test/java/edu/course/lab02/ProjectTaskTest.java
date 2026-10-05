package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTaskTest {

    @Test
    void createsTaskWithValidData() {
        ProjectTask task = new ProjectTask("T-1", "Write tests", 5);

        assertEquals("T-1", task.getId());
        assertEquals("Write tests", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(5, task.getEstimatedHours());
    }

    @Test
    void throwsForNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Title", 5));
    }

    @Test
    void throwsForEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("", "Title", 5));
    }

    @Test
    void throwsForNullTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", null, 5));
    }

    @Test
    void throwsForEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "", 5));
    }

    @Test
    void throwsForNegativeEstimatedHours() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "Title", -1));
    }

    @Test
    void changesStatus() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void throwsForNullStatus() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);

        assertThrows(IllegalArgumentException.class, () -> task.changeStatus(null));
    }

    @Test
    void isCompletedReturnsTrueForDone() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);
        task.changeStatus(TaskStatus.DONE);

        assertTrue(task.isCompleted());
    }

    @Test
    void isCompletedReturnsFalseForNew() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);

        assertFalse(task.isCompleted());
    }

    @Test
    void increasesEstimate() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);

        task.increaseEstimate(3);

        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void throwsForNonPositiveIncrease() {
        ProjectTask task = new ProjectTask("T-1", "Title", 5);

        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(0));
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(-1));
    }
}
