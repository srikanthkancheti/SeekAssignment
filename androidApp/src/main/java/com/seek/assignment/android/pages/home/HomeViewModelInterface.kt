package com.seek.assignment.android.pages.home

import kotlinx.coroutines.Job

/**
 * Public interface of ViewModel exposed to View.
 */
interface HomeViewModelInterface {
    var viewState: HomeViewState
    var showInfoMessage: HomeViewAction.ShowInfoMessage
    fun getJobPostings(): Job
}

/**
 * List of actions that View should be able to process.
 * ViewModel triggers the necessity to process Action by setting [needsProcessing] field to true.
 * After View processed such action it changes [needsProcessing] field to false.
 * Usually processed inside processViewModelActions method in View.
 */
sealed class HomeViewAction(open val needsProcessing: Boolean) {
    /**
     * An action to show general message to the user in form of i.e. Snackbar
     * @param actionLabel text used specifically for action button in snackbar
     */
    data class ShowInfoMessage(
        override val needsProcessing: Boolean = false,
        val message: String = "",
        val actionLabel: String = "",
    ) : HomeViewAction(needsProcessing)
}