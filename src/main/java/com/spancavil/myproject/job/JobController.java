package com.spancavil.myproject.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    //Spring boot encontrará la implementación más adecuada para job service (en este caso JobServiceImpl)
    //De este modo sólo dependenderemos de la interfaz y no de la implementación concreta.
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById (@PathVariable long id) {
        Job job = jobService.findById(id);
        //return job != null ? job : "Job not found" ;
        return job != null ? new ResponseEntity<>(job, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }

    @PostMapping
    public ResponseEntity<String> createJob (@RequestBody  Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob (@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        return deleted ? new ResponseEntity<>("Deleted successfully", HttpStatus.OK): new ResponseEntity<>("No job with the id specified", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob (@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.updateJobById(id, job);
        return updated ? new ResponseEntity<>("Updated successfully", HttpStatus.OK): new ResponseEntity<>("No job with the id specified", HttpStatus.BAD_REQUEST);
    }
}
