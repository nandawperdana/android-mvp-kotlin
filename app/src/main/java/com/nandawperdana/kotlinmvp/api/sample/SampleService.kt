package com.nandawperdana.kotlinmvp.api.sample

import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * A sample API call service.
 * TODO: Replace this with your own API call service.
 * Created by nandawperdana.
 */
interface SampleService {
    @GET("sample")
    fun getSample(): Flowable<SampleResponse>
}