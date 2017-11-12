package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.content.Context
import com.nandawperdana.kotlinmvp.api.people.PeopleModel
import com.nandawperdana.kotlinmvp.api.people.PeopleResponse
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter

/**
 * Created by nandawperdana.
 */

class MainViewModel(var context: Context?) {
    var errorMessage: String? = null
    var screenState: MainPresenter.MainView.ScreenState? = null
    lateinit var peopleResponse: PeopleResponse
    var listPeople: ArrayList<PeopleModel> = ArrayList()

    fun setListPeople() {
        // better using repository data from model response before show to view
        listPeople.clear()
        for (data in peopleResponse.data!!) {
            listPeople.add(data)
        }
    }
}