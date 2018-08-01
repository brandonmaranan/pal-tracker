package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    @Override
    public TimeEntry create(TimeEntry timeEntry) {


        Long id = timeEntries.size() + 1L;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(id, newTimeEntry);

        System.out.println(newTimeEntry);
        return newTimeEntry;


    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);


    }

    @Override
    public List<TimeEntry> find() {
//        return new ArrayList<>(timeEntries.values());
        return null;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
       // TimeEntry timeEnt = timeEntries.get(id);
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.replace(id, updatedEntry);
        return updatedEntry;


    }

    @Override
    public void delete(long id) {

            timeEntries.remove(id);

    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());

    }
}
