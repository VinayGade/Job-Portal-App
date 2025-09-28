package com.app.jobportal.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecruiterJobsDto {

    private Long totalCandidates;

    private Integer jobPostId;

    private String jobTitle;

    private JobLocation jobLocationId;

    private JobCompany jobCompanyId;
}
