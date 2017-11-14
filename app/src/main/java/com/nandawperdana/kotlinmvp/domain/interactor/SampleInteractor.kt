package com.nandawperdana.kotlinmvp.domain.interactor

import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.APICallManager
import com.nandawperdana.kotlinmvp.util.Enums
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * A sample interactor.
 * TODO: Replace this with your own interactor
 * Created by nandawperdana.
 */
class SampleInteractor(private var listener: APICallListener) : Interactor {
    fun callAPIGetSample() {
        val route = Enums.APIRoute.GET_SAMPLE
        val call = APICallManager.getInstance.sampleManager.getSample()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        call.subscribe(
                { response ->
                    listener.onAPICallSucceed(route, response)
                },
                { error ->
                    listener.onAPICallFailed(route, error)
                })
    }
}