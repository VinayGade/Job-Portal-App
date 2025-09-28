package com.app.jobportal.service;

import com.app.jobportal.entity.*;
import com.app.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public
    List<RecruiterJobsDto> getRecruiterJobs(int recruiter){

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
}
