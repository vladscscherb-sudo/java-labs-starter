package edu.course.lab04;

import java.util.Objects;

public final class IssueId {
    private final String projectCode;
    private final int number;

    public IssueId(String projectCode, int number) {
        if (projectCode == null || projectCode.isEmpty() || number <= 0) {
            throw new IllegalArgumentException("Invalid IssueId");
        }
        this.projectCode = projectCode;
        this.number = number;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueId issueId = (IssueId) o;
        return number == issueId.number && Objects.equals(projectCode, issueId.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, number);
    }
}
