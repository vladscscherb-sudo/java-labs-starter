package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {

    @Test
    void logicallyEqualKeysFindSameValue() {
        IssueRegistry registry = new IssueRegistry();
        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("Fix bug"));

        IssueId differentInstance = new IssueId("PROJ", 1);
        assertEquals("Fix bug", registry.get(differentInstance).getTitle());
    }

    @Test
    void addingWithEqualKeyReplacesValueAndSizeDoesNotGrow() {
        IssueRegistry registry = new IssueRegistry();
        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("Task 1"));

        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("Task 2"));

        assertEquals(1, registry.size());
        assertEquals("Task 2", registry.get(new IssueId("PROJ", 1)).getTitle());
    }

    @Test
    void collisionWithSameHashCodePreservesDifferentValues() {
        class CollisionKey {
            String id;
            CollisionKey(String id) { this.id = id; }
            @Override public boolean equals(Object o) { return o instanceof CollisionKey && ((CollisionKey)o).id.equals(id); }
            @Override public int hashCode() { return 42; }
        }

        Map<CollisionKey, String> map = new HashMap<>();
        CollisionKey key1 = new CollisionKey("A");
        CollisionKey key2 = new CollisionKey("B");

        map.put(key1, "ValueA");
        map.put(key2, "ValueB");

        assertEquals("ValueA", map.get(key1));
        assertEquals("ValueB", map.get(key2));
    }

    @Test
    void comparisonWithNullAndOtherTypeReturnsFalse() {
        IssueId id = new IssueId("PROJ", 1);
        assertFalse(id.equals(null));
        assertFalse(id.equals("Какая-то строка"));
    }

    @Test
    void mutableKeyProblem() {
        class MutableKey {
            public int value;
            MutableKey(int value) { this.value = value; }
            @Override public boolean equals(Object o) { return o instanceof MutableKey && ((MutableKey)o).value == value; }
            @Override public int hashCode() { return value; }
        }

        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey(10);
        map.put(key, "Data");
        key.value = 20;
        assertNull(map.get(key));
    }
}