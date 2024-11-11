package com.spancavil.myproject.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spancavil.myproject.job.Job;
import com.spancavil.myproject.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    //One company will have many jobs.
    //Con el "mappedBy" eliminamos la tabla intermedia que se genera POR DEFAULT.
    //Entonces la relación estará gobernada por el id de la company (que es la tabla madre)
    @OneToMany(mappedBy = "company")
    //Con JsonIgnore vamos a evitar el error de recursión infinita al obtener el JSON. Esto es porque Job llama
    //a Company, Company llama a Job y así sucesivamente.
    @JsonIgnore
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    public Company() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
