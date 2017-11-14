package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.presentation.presenters.MainPresenter
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

        // load sample data from API, TODO: Replace this with your own data load.
        presenter.presentState(MainPresenter.MainView.ViewState.LOAD_SAMPLE)
    }

    private fun init() {
        this.model = MainViewModel(this)
        this.presenter = MainPresenterImpl(this)

        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent)
    }

    override fun showProgress(flag: Boolean) {
        swipeRefresh.isRefreshing = flag
    }

    override fun showState(viewState: MainPresenter.MainView.ViewState) {
        when (viewState) {
            IDLE -> showProgress(false)
            LOADING -> showProgress(true)
            SHOW_SAMPLE -> showSample()
            ERROR -> {
                presenter.presentState(IDLE)
                showDialog(null, doRetrieveModel().errorMessage)
            }
        }
    }

    override fun doRetrieveModel(): MainViewModel = this.model

    override fun onRefresh() {
        presenter.presentState(LOAD_SAMPLE)
    }

    // region View State Methods

    /**
     * A sample show method. Show data from API to this view.
     * TODO: Replace this with your own data show
     */
    private fun showSample() {
        presenter.presentState(IDLE)
    }
    //endregion
}
