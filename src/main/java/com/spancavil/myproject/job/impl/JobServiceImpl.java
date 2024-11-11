package com.spancavil.myproject.job.impl;

import com.spancavil.myproject.job.Job;
import com.spancavil.myproject.job.JobRepository;
import com.spancavil.myproject.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//Son implementaciones de la interfaz JobService para generar un acoplamiento lo más débil posible entre
// clases
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    //private List<Job> jobs = new ArrayList<>();
    //Con la L le especificamos que es del type Long
    //private Long nextId = 1L;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isEmpty()) return false;
        else {
            jobRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job jobToUpdate) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isEmpty()) return false;
        else {
            Job job = jobOptional.get();
            job.setDescription(jobToUpdate.getDescription());
            job.setLocation(jobToUpdate.getLocation());
            job.setTitle(jobToUpdate.getTitle());
            job.setMaxSalary(jobToUpdate.getMaxSalary());
            job.setMinSalary(jobToUpdate.getMinSalary());

            /*
            for (Job jobToUpdate: jobs) {
                if (jobToUpdate.getId().equals(id)) {
                    jobToUpdate.setDescription(job.getDescription());
                    jobToUpdate.setLocation(job.getLocation());
                    jobToUpdate.setTitle(job.getTitle());
                    jobToUpdate.setMaxSalary(job.getMaxSalary());
                    jobToUpdate.setMinSalary(job.getMinSalary());
                }
            }*/
            jobRepository.save(job);
            return true;
        }
    }
}
