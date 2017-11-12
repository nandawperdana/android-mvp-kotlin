package com.nandawperdana.kotlinmvp.presentation.presenters

import com.nandawperdana.kotlinmvp.presentation.screens.main.MainViewModel

/**
 * Created by nandawperdana.
 */
interface MainPresenter : BasePresenter {
    interface MainView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum class ViewState {
            IDLE, LOADING, SHOW_SCREEN_STATE, LOAD_PEOPLE, SHOW_PEOPLE, ERROR
        }

        enum class ScreenState {
            SCREEN_PEOPLE, SCREEN_BLANK
        }

        /**
         * This method is to show the current state of this screen
         *
         * @param viewState
         */
        fun showState(viewState: ViewState)

        /**
         * This function return the model that was belong to this screen
         *
         * @return
         */
        fun doRetrieveModel(): MainViewModel
    }

    /**
     * This method is used for present the current state of this screen
     *
     * @param state
     */
    fun presentState(state: MainView.ViewState)
}
