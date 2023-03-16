package com.seek.assignment.common.seek.domain.repository

import com.seek.assignment.common.seek.domain.model.JobData

interface SeekRepo {
    suspend fun getJobPostings(): Result<List<JobData>>
}