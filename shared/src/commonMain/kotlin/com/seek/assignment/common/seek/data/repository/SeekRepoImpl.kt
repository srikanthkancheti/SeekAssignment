package com.seek.assignment.common.seek.data.repository

import com.seek.assignment.common.seek.data.entity.JobEntity
import com.seek.assignment.common.seek.data.mapper.JobPostingsMapper
import com.seek.assignment.common.seek.data.network.SeekAPI
import com.seek.assignment.common.seek.domain.model.JobData
import com.seek.assignment.common.seek.domain.repository.SeekRepo
import com.seek.assignment.core.di.getKoinInstance

class SeekRepoImpl constructor(
    private val api: SeekAPI
) : SeekRepo {

    private val mapper: JobPostingsMapper = getKoinInstance()

    override suspend fun getJobPostings(): Result<List<JobData>> {
        val response = api.getJobPostings()
        if (!response.success) {
            return Result.failure(Exception("getJobPostings failed for the user"))
        }
        val jobs = JobEntity.getMockedJobPostings().map { jobPosting ->
            mapper.map(jobPosting)
        }
        if (jobs.isNotEmpty())
            return Result.success(jobs)

        return Result.failure(Exception("getApplianceFavorites failed for the user"))
    }
}