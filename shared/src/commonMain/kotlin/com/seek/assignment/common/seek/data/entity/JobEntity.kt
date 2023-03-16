package com.seek.assignment.common.seek.data.entity

import com.seek.assignment.common.assets.AssetReader
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class JobEntity(
   val job_id: Int,
    val job_title: String,
    val company_name: String,
    val job_description: String,
    val application_status: String
) {
    companion object {
        fun getMockedJobPostings(): List<JobEntity> {
            val json: Json by lazy {
                Json { ignoreUnknownKeys = true }
            }
            return json.decodeFromString(AssetReader().getJsonString("seek_jobs"))
        }
    }
}
