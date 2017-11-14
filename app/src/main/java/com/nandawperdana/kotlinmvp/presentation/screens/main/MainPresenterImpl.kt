package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.util.Log

import com.nandawperdana.kotlinmvp.AndroidApplication
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.RootResponseModel
import com.nandawperdana.kotlinmvp.api.people.PeopleResponse
import com.nandawperdana.kotlinmvp.domain.interactor.PeopleInteractor
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter.MainView.ViewState.*
import com.nandawperdana.kotlinmvp.util.Enums

/**
 * Created by nandawperdana.
 */

class MainPresenterImpl(private val mView: MainPresenter.MainView) : MainPresenter, APICallListener {
    private val peopleInteractor: PeopleInteractor = PeopleInteractor(this)

    override fun presentState(state: MainPresenter.MainView.ViewState) {
        // user state logging
        Log.i(MainActivity::class.java.simpleName, state.name)
        when (state) {
            IDLE -> mView.showState(IDLE)
            LOADING -> mView.showState(LOADING)
            LOAD_PEOPLE ->
                if (AndroidApplication.getInstance.isConnected()) {
                    presentState(LOADING)
                    peopleInteractor.callAPIGetPeople()
                } else {
                    mView.doRetrieveModel().errorMessage = mView.doRetrieveModel().context?.getString(R.string.message_no_internet)
                    presentState(ERROR)
                }
            SHOW_PEOPLE -> mView.showState(SHOW_PEOPLE)
            SHOW_SCREEN_STATE -> mView.showState(SHOW_SCREEN_STATE)
            ERROR -> mView.showState(ERROR)
        }
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun onAPICallSucceed(route: Enums.APIRoute, responseModel: RootResponseModel) {
        when (route) {
            Enums.APIRoute.GET_PEOPLE -> {
                mView.doRetrieveModel().peopleDomain.peopleResponse = responseModel as PeopleResponse
                presentState(SHOW_PEOPLE)
            }
        }
    }

    override fun onAPICallFailed(route: Enums.APIRoute, throwable: Throwable) {
        mView.doRetrieveModel().errorMessage = throwable.message
        presentState(ERROR)
    }
}
