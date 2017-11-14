package com.nandawperdana.kotlinmvp.domain.interactor

import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.APICallManager
import com.nandawperdana.kotlinmvp.util.Enums
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by nandawperdana.
 */
class PeopleInteractor(private var listener: APICallListener) : Interactor {
    fun callAPIGetPeople() {
        val route = Enums.APIRoute.GET_PEOPLE
        val call = APICallManager.getInstance.peopleManager.getPeople()
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