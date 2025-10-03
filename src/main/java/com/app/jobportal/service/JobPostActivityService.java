package com.app.jobportal.service;

import com.app.jobportal.entity.*;
import com.app.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){

        List<IRecruiterJobs> recruiterJobs = jobPostActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for(IRecruiterJobs recruiterJob : recruiterJobs){

            JobLocation jobLocation = new JobLocation(
                    recruiterJob.getLocationId(),
                    recruiterJob.getCity(),
                    recruiterJob.getState(),
                    recruiterJob.getCountry()
            );

            JobCompany jobCompany = new JobCompany(recruiterJob.getCompanyId(), recruiterJob.getName(), "");

            recruiterJobsDtoList.add(new RecruiterJobsDto(
                    recruiterJob.getTotalCandidates(),
                    recruiterJob.getJobPostId(),
                    recruiterJob.getJobTitle(), jobLocation, jobCompany));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() ->
                new RuntimeException("job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        if(Objects.isNull(searchDate)) {
            return jobPostActivityRepository.searchWithoutDate(job, location, remote, type);
        }else {
            return jobPostActivityRepository.search(job, location, remote, type, searchDate);
        }
    }
}
