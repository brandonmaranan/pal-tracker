package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

//    @Autowired
    //private TimeEntryRepository timeEntryRepository;
    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            CounterService counter,
            GaugeService gauge
    ) {
        this.timeEntriesRepo = timeEntriesRepo;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){


        TimeEntry timeEntryRepo = timeEntriesRepo.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntriesRepo.list().size());
        if (timeEntryRepo != null) {
            System.out.println("WHAT IS timeENtry Repo : " + timeEntryRepo.getProjectId());
        }
        return new ResponseEntity<>(timeEntryRepo, HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        System.out.println("id:" + id);
        TimeEntry timeEntryRepo = timeEntriesRepo.find(id);
        if(timeEntryRepo == null) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.OK);
        }

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> listEntry  = timeEntriesRepo.list();
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(listEntry, HttpStatus.OK);

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry)
    {
        TimeEntry timeEntryRepo = timeEntriesRepo.update(id,timeEntry);
        if(timeEntryRepo == null)
        {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.NOT_FOUND);
        }
        else {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.OK);
        }

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){
        timeEntriesRepo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntriesRepo.list().size());
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }

}
