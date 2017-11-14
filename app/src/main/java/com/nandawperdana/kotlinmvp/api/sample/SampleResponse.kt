package com.nandawperdana.kotlinmvp.api.sample

import com.nandawperdana.kotlinmvp.api.RootResponseModel

/**
 * Created by nandawperdana.
 * A sample response model from API.
 * TODO: Replace this with your own response model.
 */

data class SampleResponse(
        val message: String?
) : RootResponseModel() {

    constructor() : this(message = null)
}
