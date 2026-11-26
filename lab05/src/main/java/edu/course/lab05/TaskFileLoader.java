package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskFileLoader {

    private static final String EXPECTED_HEADER = "id;title;category;estimatedHours;status";

    public LoadResult load(Path path) {
        List<Task> validTasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();
        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("cannot read " + path, e);
        }

        if (lines.isEmpty() || !lines.get(0).trim().equals(EXPECTED_HEADER)) {
            throw new DataLoadingException("invalid or missing header in file: " + path, null);
        }

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            parseLine(line, i + 1, validTasks, errors);
        }

        return new LoadResult(validTasks, errors);
    }

    private void parseLine(String line, int lineNumber, List<Task> tasks, List<ValidationError> errors) {
        String[] parts = line.split(";", -1);

        if (parts.length != 5) {
            errors.add(new ValidationError(lineNumber, "ALL", "Invalid number of columns: " + parts.length));
            return;
        }

        String id = parts[0].trim();
        String title = parts[1].trim();
        String category = parts[2].trim();
        String hoursStr = parts[3].trim();
        String statusStr = parts[4].trim();

        if (id.isEmpty() || title.isEmpty() || category.isEmpty() || hoursStr.isEmpty() || statusStr.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "ALL", "Fields must not be empty"));
            return;
        }

        int hours;
        try {
            hours = Integer.parseInt(hoursStr);
            if (hours < 0) {
                errors.add(new ValidationError(lineNumber, "estimatedHours", "Hours cannot be negative"));
                return;
            }
        } catch (NumberFormatException e) {
            errors.add(new ValidationError(lineNumber, "estimatedHours", "Invalid number: " + hoursStr));
            return;
        }

        TaskStatus status;
        try {
            status = TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError(lineNumber, "status", "Unknown status: " + statusStr));
            return;
        }

        tasks.add(new Task(id, title, category, hours, status));
    }
}
