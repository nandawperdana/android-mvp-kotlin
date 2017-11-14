package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.content.Context
import com.nandawperdana.kotlinmvp.domain.model.SampleDomain

/**
 * Created by nandawperdana.
 */

class MainViewModel(var context: Context?) {
    var errorMessage: String? = null
    var sampleDomain: SampleDomain = SampleDomain()
}