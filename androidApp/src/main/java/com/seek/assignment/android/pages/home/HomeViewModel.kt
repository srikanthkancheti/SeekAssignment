package com.seek.assignment.android.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seek.assignment.common.seek.domain.model.JobData
import com.seek.assignment.common.seek.domain.usecase.JobPostingsUseCase
import com.seek.assignment.core.di.getKoinInstance
import com.seek.assignment.core.model.StringKey
import com.seek.assignment.core.service.LanguageService
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class HomeViewModel(
    savedState: SavedStateHandle
) : ViewModel(), HomeViewModelInterface {

    private val jobPostingsUseCase: JobPostingsUseCase = getKoinInstance()
    private val languageService: LanguageService = getKoinInstance()
    private var jobPostings: MutableList<JobData> = mutableListOf()

    override var viewState: HomeViewState by mutableStateOf(updateViewState())

    override var showInfoMessage: HomeViewAction.ShowInfoMessage by mutableStateOf(
        HomeViewAction.ShowInfoMessage()
    )

    init {
        getJobPostings()
    }
    override fun getJobPostings()= viewModelScope.launch {
        viewState = viewState.copy(progress = true)
        delay(500L)
        val jobsResult = jobPostingsUseCase.getJobPostings()
        if (jobsResult.isSuccess) {
            jobPostings.clear()
            jobsResult.data?.let { jobPostings.addAll(it) }
            viewState = viewState.copy(progress = false, jobPostings = jobPostings)
        } else {
            showMessageAndStopProgress(jobsResult.exception?.message)
        }
    }

    private fun updateViewState(
        jobsData: List<JobData>? = null
    ): HomeViewState = if (jobsData == null || jobsData.isEmpty()) {
        HomeViewState()
    } else {
        viewState.copy(
            progress = false,
            jobPostings = jobsData
        )
    }

    private fun showMessageAndStopProgress(message: String?) {
        showInfoMessage = showInfoMessage.copy(
            message = message.orEmpty(),
            needsProcessing = true,
            actionLabel = languageService.getResourceString(StringKey.okButton),
        )
        viewState = viewState.copy(progress = false)
    }
}