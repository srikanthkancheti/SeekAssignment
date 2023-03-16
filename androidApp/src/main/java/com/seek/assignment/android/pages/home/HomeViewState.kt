package com.seek.assignment.android.pages.home

import com.seek.assignment.common.seek.domain.model.JobData

/**
 * All the data that is being exposed to ArticleView
 */
data class HomeViewState(
    val progress: Boolean = false,
    val jobPostings: List<JobData> = listOf()
)
