package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;


    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry( long projectId, long userId, LocalDate date, int hours) {
        this(0, projectId, userId, date, hours);
    }

    public TimeEntry() {
        this(0, 0, LocalDate.now(), 0);
    }


    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeEntry)) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return id == timeEntry.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return new StringBuffer().append("id=").append(this.id).append("projectId=").append(this.projectId)
                .append("userId=").append(this.userId).append("date=").append(this.date).append("hours=").append(this.hours).toString();
    }
}
