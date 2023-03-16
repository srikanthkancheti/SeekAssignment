package com.seek.assignment.common.seek.domain.usecase

import com.seek.assignment.common.seek.domain.JobsListResult
import com.seek.assignment.common.seek.domain.model.JobData
import com.seek.assignment.common.seek.domain.repository.SeekRepo

interface JobPostingsUseCase {
    suspend fun getJobPostings(): JobsListResult<List<JobData>>
}

class JobPostingsUseCaseImpl(private val seekRepo: SeekRepo): JobPostingsUseCase {
    override suspend fun getJobPostings(): JobsListResult<List<JobData>> {
        val response = seekRepo.getJobPostings()
        val jobPostings = response.getOrNull()

        return if (response.isSuccess && jobPostings != null) {
            JobsListResult.Success(data = jobPostings)
        } else {
            JobsListResult.Failure(Exception("no jobs found"))
        }
    }

}