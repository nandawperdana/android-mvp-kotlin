package com.nandawperdana.kotlinmvp.presentation.presenters

/**
 * Created by nandawperdana.
 */
interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     *
     * @param flag True to show, false to hide progress
     */
    fun showProgress(flag: Boolean)

    /**
     * This method is used for showing toast messages on the UI.
     *
     * @param message
     */
    fun showToast(message: String?)

    /**
     * This method is used for showing error messages on the UI via dialog.
     *
     * @param title
     * @param message
     */
    fun showError(title: String?, message: String?)
}
