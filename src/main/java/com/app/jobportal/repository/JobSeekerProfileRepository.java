package com.app.jobportal.repository;

import com.app.jobportal.entity.JobSeekerProfile;
import com.app.jobportal.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {
}
