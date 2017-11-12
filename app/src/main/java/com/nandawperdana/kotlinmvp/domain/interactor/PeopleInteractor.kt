package com.nandawperdana.kotlinmvp.domain.interactor

import com.nandawperdana.kotlinmvp.api.APICallListener
import com.nandawperdana.kotlinmvp.api.APICallManager
import com.nandawperdana.kotlinmvp.api.people.PeopleModel
import com.nandawperdana.kotlinmvp.api.people.PeopleResponse
import com.nandawperdana.kotlinmvp.util.Enums
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

/**
 * Created by nandawperdana.
 */
class PeopleInteractor(private var listener: APICallListener) : Interactor {

    private var people = RealmList<PeopleModel>()
    private val realm = Realm.getDefaultInstance()

    fun callAPIGetPeople() {
        val route = Enums.APIRoute.GET_PEOPLE
        val call = APICallManager.getInstance.peopleManager.getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        call.subscribe(
                { response ->
                    listener.onAPICallSucceed(route, response)
                    people = RealmList(*response.data!!.toTypedArray())
                    insertData()
                },
                { error ->
                    if (getPeople().size == 0)
                        listener.onAPICallFailed(route, error)
                    else {
                        getData()
                        val peopleResponse = PeopleResponse(message = null, data = getPeople())
                        listener.onAPICallSucceed(route, peopleResponse)
                    }
                })
    }


    private fun getPeople(): RealmList<PeopleModel> {
        return people
    }

    private fun insertData() {
        realm.executeTransaction({
            if (realm.where(PeopleModel::class.java).count() != people.size.toLong()) {
                clearData()
                realm.beginTransaction()
                for (item in people) {
                    if (realm.where(PeopleModel::class.java).equalTo("id", item.id).count() == 0L) {
                        realm.copyToRealmOrUpdate(people)
                    }
                }
            }
        })
    }

    private fun getData() {
        val results = realm.where(PeopleModel::class.java).findAll()
        people.addAll(results.subList(0, results.size))
    }

    private fun clearData() {
        realm.delete(PeopleModel::class.java)
        realm.commitTransaction()
    }
}