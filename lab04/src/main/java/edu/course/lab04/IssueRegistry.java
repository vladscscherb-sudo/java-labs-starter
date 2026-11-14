package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public class IssueRegistry {
    private final Map<IssueId, ProjectTask> tasks = new HashMap<>();

    public void addOrReplace(IssueId id, ProjectTask task) {
        tasks.put(id, task);
    }

    public ProjectTask get(IssueId id) {
        return tasks.get(id);
    }

    public void remove(IssueId id) {
        tasks.remove(id);
    }

    public boolean contains(IssueId id) {
        return tasks.containsKey(id);
    }

    public int size() {
        return tasks.size();
    }
}