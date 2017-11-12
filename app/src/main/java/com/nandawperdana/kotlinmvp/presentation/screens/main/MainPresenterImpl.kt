package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.util.Log

import com.nandawperdana.kotlinmvp.AndroidApplication
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.RootResponseModel
import com.nandawperdana.kotlinmvp.api.people.PeopleResponse
import com.nandawperdana.kotlinmvp.domain.interactor.PeopleInteractor
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter
import com.nandawperdana.kotlinmvp.util.Enums

/**
 * Created by nandawperdana.
 */

class MainPresenterImpl(private val mView: MainPresenter.MainView) : MainPresenter, APICallListener {
    private val peopleInteractor: PeopleInteractor = PeopleInteractor(this)

    override fun presentState(state: MainPresenter.MainView.ViewState) {
        Log.i("STATE: ", state.toString())
        when (state) {
            MainPresenter.MainView.ViewState.IDLE -> mView.showState(MainPresenter.MainView.ViewState.IDLE)
            MainPresenter.MainView.ViewState.LOADING -> mView.showState(MainPresenter.MainView.ViewState.LOADING)
            MainPresenter.MainView.ViewState.LOAD_PEOPLE ->
                // loading from API or calculations
                if (AndroidApplication.getInstance.isConnected()) {
                    presentState(MainPresenter.MainView.ViewState.LOADING)
                    peopleInteractor.callAPIGetPeople()
                } else {
                    mView.doRetrieveModel().errorMessage = mView.doRetrieveModel().context?.getString(R.string.message_no_internet)
                    presentState(MainPresenter.MainView.ViewState.ERROR)
                }
            MainPresenter.MainView.ViewState.SHOW_PEOPLE -> mView.showState(MainPresenter.MainView.ViewState.SHOW_PEOPLE)
            MainPresenter.MainView.ViewState.SHOW_SCREEN_STATE -> mView.showState(MainPresenter.MainView.ViewState.SHOW_SCREEN_STATE)
            MainPresenter.MainView.ViewState.ERROR -> mView.showState(MainPresenter.MainView.ViewState.ERROR)
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun onError(message: String) {
        mView.doRetrieveModel().errorMessage = message
        presentState(MainPresenter.MainView.ViewState.ERROR)
    }

    override fun onAPICallSucceed(route: Enums.APIRoute, responseModel: RootResponseModel) {
        when (route) {
            Enums.APIRoute.GET_PEOPLE -> {
                mView.doRetrieveModel().peopleResponse = responseModel as PeopleResponse
                presentState(MainPresenter.MainView.ViewState.SHOW_PEOPLE)
            }
        }
    }

    override fun onAPICallFailed(route: Enums.APIRoute, throwable: Throwable) {
        mView.doRetrieveModel().errorMessage = throwable.message
        onError(throwable.message.toString())
    }
}
