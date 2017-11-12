package com.nandawperdana.kotlinmvp.api.people

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nandawperdana.
 */
interface PeopleService {
    @GET("people.json")
    fun getPeople(@Query("alt") alt: String,
                  @Query("token") token: String): Flowable<PeopleResponse>
}