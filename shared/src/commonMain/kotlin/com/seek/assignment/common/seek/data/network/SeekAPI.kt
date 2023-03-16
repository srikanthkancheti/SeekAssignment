package com.seek.assignment.common.seek.data.network

import com.seek.assignment.common.seek.data.entity.JobEntity
import com.seek.assignment.core.model.ApiResponse

interface SeekAPI {
    suspend fun getJobPostings(): ApiResponse<List<JobEntity>>
}

class SeekAPIImpl(
//    private val client: WebClient
) : SeekAPI {
    override suspend fun getJobPostings(): ApiResponse<List<JobEntity>> {
//        val response = client.makeJsonGet<List<JobEntity>>(
//            "/remote-control/api/v1/appliances/$userId/jobs", accessToken
//        )
//        if (!response.success) {
//            response.errorResponse?.message = "getJobPostings failed"
//        }
//        return response
        return ApiResponse(success = true, null, null)
    }

}