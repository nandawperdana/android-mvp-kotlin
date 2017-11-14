package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.content.Context
import com.nandawperdana.kotlinmvp.api.people.PeopleModel
import com.nandawperdana.kotlinmvp.domain.model.PeopleDomain
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter

/**
 * Created by nandawperdana.
 */

class MainViewModel(var context: Context?) {
    var errorMessage: String? = null
    var screenState: MainPresenter.MainView.ScreenState? = null
    var peopleDomain: PeopleDomain = PeopleDomain()
    var listPeople: ArrayList<PeopleModel> = ArrayList()

    fun setListPeople() {
        listPeople.clear()
        for (data in peopleDomain.peopleResponse.data!!) {
            listPeople.add(data)
        }
    }
}