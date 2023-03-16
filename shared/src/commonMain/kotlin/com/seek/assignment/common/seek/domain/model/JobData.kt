package com.seek.assignment.common.seek.domain.model

data class JobData(
    val jobId: Int,
    val jobTitle: String,
    val companyName: String,
    val jobDescription: String,
    val applicationStatus: String,
)
