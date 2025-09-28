package com.app.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String experienceLevel;
    String yearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="job_seeker_profile")
    JobSeekerProfile jobSeekerProfile;
}
