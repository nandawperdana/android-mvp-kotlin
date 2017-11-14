package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.util.Log

import com.nandawperdana.kotlinmvp.AndroidApplication
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.RootResponseModel
import com.nandawperdana.kotlinmvp.api.sample.SampleResponse
import com.nandawperdana.kotlinmvp.domain.interactor.SampleInteractor
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter.MainView.ViewState.*
import com.nandawperdana.kotlinmvp.util.Enums

/**
 * Created by nandawperdana.
 */

class MainPresenterImpl(private val mView: MainPresenter.MainView) : MainPresenter, APICallListener {
    // A sample interactor, TODO: some interactors if needed.
    private val sampleInteractor: SampleInteractor = SampleInteractor(this)

    override fun presentState(state: MainPresenter.MainView.ViewState) {
        // user state logging
        Log.i(MainActivity::class.java.simpleName, state.name)
        when (state) {
            IDLE -> mView.showState(IDLE)
            LOADING -> mView.showState(LOADING)
            LOAD_SAMPLE ->
                if (AndroidApplication.getInstance.isConnected()) {
                    presentState(LOADING)
                    sampleInteractor.callAPIGetSample()
                } else {
                    mView.doRetrieveModel().errorMessage = mView.doRetrieveModel().context?.getString(R.string.message_no_internet)
                    presentState(ERROR)
                }
            SHOW_SAMPLE -> mView.showState(SHOW_SAMPLE)
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
            // sample response, TODO: Replace this with your own API call response
            Enums.APIRoute.GET_SAMPLE -> {
                mView.doRetrieveModel().sampleDomain.sampleResponse = responseModel as SampleResponse
                presentState(SHOW_SAMPLE)
            }
        }
    }

    override fun onAPICallFailed(route: Enums.APIRoute, throwable: Throwable) {
        mView.doRetrieveModel().errorMessage = throwable.message
        presentState(ERROR)
    }
}
