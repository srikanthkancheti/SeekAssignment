package com.seek.assignment.common.seek.data.mapper

import com.seek.assignment.common.seek.data.entity.JobEntity
import com.seek.assignment.common.seek.domain.model.JobData

interface JobPostingsMapper {
    fun map(jobEntity: JobEntity): JobData
}

class JobPostingsMapperImpl: JobPostingsMapper {
    override fun map(jobEntity: JobEntity): JobData {
        return JobData(
            jobId = jobEntity.job_id,
            jobTitle = jobEntity.job_title,
            companyName = jobEntity.company_name,
            jobDescription = jobEntity.job_description,
            applicationStatus = jobEntity.application_status
        )
    }

}