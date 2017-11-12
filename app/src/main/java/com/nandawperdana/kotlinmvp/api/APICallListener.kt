package com.nandawperdana.kotlinmvp.api

import com.nandawperdana.kotlinmvp.util.Enums

/**
 * Created by nandawperdana.
 */
interface APICallListener {
    fun onAPICallSucceed(route: Enums.APIRoute, responseModel: RootResponseModel)

    fun onAPICallFailed(route: Enums.APIRoute, throwable: Throwable)
}
