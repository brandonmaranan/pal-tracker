package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

//    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;

    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){


        TimeEntry timeEntryRepo = timeEntryRepository.create(timeEntry);
        if (timeEntryRepo != null) {
            System.out.println("WHAT IS timeENtry Repo : " + timeEntryRepo.getProjectId());
        }
        return new ResponseEntity<>(timeEntryRepo, HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        System.out.println("id:" + id);
        TimeEntry timeEntryRepo = timeEntryRepository.find(id);
        if(timeEntryRepo == null) {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.OK);
        }

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> listEntry  = timeEntryRepository.list();
        return new ResponseEntity<>(listEntry, HttpStatus.OK);

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry)
    {
        TimeEntry timeEntryRepo = timeEntryRepository.update(id,timeEntry);
        if(timeEntryRepo == null)
        {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(timeEntryRepo, HttpStatus.OK);
        }

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }

}
