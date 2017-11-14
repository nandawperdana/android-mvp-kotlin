package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter.MainView.ScreenState.SCREEN_BLANK
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter.MainView.ScreenState.SCREEN_PEOPLE
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter.MainView.ViewState.*
import com.nandawperdana.kotlinmvp.presentation.screens.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainPresenter.MainView, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var model: MainViewModel
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        // load data from API
        presenter.presentState(MainPresenter.MainView.ViewState.LOAD_PEOPLE)
    }

    private fun init() {
        this.model = MainViewModel(this)
        this.presenter = MainPresenterImpl(this)

        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = PeopleAdapter(doRetrieveModel().listPeople)
    }

    override fun showProgress(flag: Boolean) {
        swipeRefresh.isRefreshing = flag
    }

    override fun showState(viewState: MainPresenter.MainView.ViewState) {
        when (viewState) {
            IDLE -> showProgress(false)
            LOADING -> showProgress(true)
            SHOW_SCREEN_STATE -> showScreenState()
            SHOW_PEOPLE -> showPeople()
            ERROR -> {
                presenter.presentState(IDLE)
                showDialog(null, doRetrieveModel().errorMessage)
            }
        }
    }

    override fun doRetrieveModel(): MainViewModel = this.model

    override fun onRefresh() {
        presenter.presentState(LOAD_PEOPLE)
    }

    // region View State Methods
    private fun showScreenState() {
        when (doRetrieveModel().screenState) {
            SCREEN_BLANK -> {
                textViewBlank.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            SCREEN_PEOPLE -> {
                textViewBlank.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun showPeople() {
        doRetrieveModel().setListPeople()
        if (doRetrieveModel().listPeople.isEmpty())
            doRetrieveModel().screenState = SCREEN_BLANK
        else {
            // show the data
            recyclerView.adapter.notifyDataSetChanged()
            doRetrieveModel().screenState = SCREEN_PEOPLE
        }
        presenter.presentState(SHOW_SCREEN_STATE)
        presenter.presentState(IDLE)
    }
    //endregion
}
