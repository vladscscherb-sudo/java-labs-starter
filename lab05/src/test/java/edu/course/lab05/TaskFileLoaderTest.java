package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {

    @Test
    void readsNumbersAndCatchesBadLine(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(3, lines.size());

        int validCount = 0;
        for (String line : lines) {
            try {
                Integer.parseInt(line);
                validCount++;
            } catch (NumberFormatException e) {
                assertEquals("bad", line);
            }
        }
        assertEquals(2, validCount);
    }

    @Test
    void loadsValidFileWithUnicodeText(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("valid.csv");
        Files.write(file, List.of(
                "id;title;category;estimatedHours;status",
                "T-101;Сделать релиз;backend;8;READY",
                "T-102;Prepare UI;frontend;5;IN_PROGRESS"
        ), StandardCharsets.UTF_8);

        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals(0, result.getErrors().size());
        assertEquals("Сделать релиз", result.getTasks().get(0).getTitle());
        assertEquals(TaskStatus.READY, result.getTasks().get(0).getStatus());
    }

    @Test
    void collectsErrorsWithoutStoppingReading(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("mixed.csv");
        Files.write(file, List.of(
                "id;title;category;estimatedHours;status",
                "T-1;Valid;backend;8;READY",
                "T-2;BadNumber;backend;abc;READY",
                "T-3;BadStatus;backend;5;UNKNOWN",
                "T-4;;backend;5;READY",
                "T-5;TooFewColumns;backend;5"
        ), StandardCharsets.UTF_8);

        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getTasks().size());
        assertEquals("T-1", result.getTasks().get(0).getId());

        assertEquals(4, result.getErrors().size());

        assertEquals(3, result.getErrors().get(0).getLineNumber());
        assertTrue(result.getErrors().get(0).getMessage().contains("Invalid number"));

        assertEquals(4, result.getErrors().get(1).getLineNumber());
        assertTrue(result.getErrors().get(1).getMessage().contains("Unknown status"));

        assertEquals(5, result.getErrors().get(2).getLineNumber());
        assertTrue(result.getErrors().get(2).getMessage().contains("Fields must not be empty"));

        assertEquals(6, result.getErrors().get(3).getLineNumber());
        assertTrue(result.getErrors().get(3).getMessage().contains("Invalid number of columns"));
    }

    @Test
    void throwsDataLoadingExceptionForMissingFile() {
        Path missingFile = Path.of("this_file_does_not_exist_12345.csv");
        TaskFileLoader loader = new TaskFileLoader();

        DataLoadingException exception = assertThrows(
                DataLoadingException.class,
                () -> loader.load(missingFile)
        );

        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof IOException);
    }
}