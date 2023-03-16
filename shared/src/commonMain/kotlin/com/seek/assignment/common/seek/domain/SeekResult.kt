package com.seek.assignment.common.seek.domain

import com.seek.assignment.common.seek.domain.model.JobData

/**
 * Need to revisit this handling once the exporting of Kotlin code to Objective-C is improved by JetBrains
 * https://kotlinlang.org/docs/roadmap.html#roadmap-details
 * https://youtrack.jetbrains.com/issue/KT-42297?_gl=1*1c3qnja*_ga*Mjk0NjE3MzM1LjE2NjI1NTk2ODM.*_ga_9J976DJZ68*MTY3MzI0MzI1MS4yMC4wLjE2NzMyNDMyNTEuMC4wLjA.&_ga=2.182308135.1648799126.1673238317-294617335.1662559683
 */
sealed class SeekResult<out V: Any, out E: Throwable> {
    open val data: V? = null
    open val exception: E? = null

    val isSuccess: Boolean get() = !isFailure
    val isFailure: Boolean get() = exception != null
}

sealed class JobsListResult<out T: Any>: SeekResult<T, Throwable>() {
    data class Success(override val data: List<JobData>) : JobsListResult<List<JobData>>()
    data class Failure(override val exception: Throwable): JobsListResult<Nothing>()
}