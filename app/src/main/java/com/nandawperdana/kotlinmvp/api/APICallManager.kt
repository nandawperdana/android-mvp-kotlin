package com.nandawperdana.kotlinmvp.api

import com.nandawperdana.kotlinmvp.BuildConfig
import com.nandawperdana.kotlinmvp.api.people.PeopleResponse
import com.nandawperdana.kotlinmvp.api.people.PeopleService
import com.nandawperdana.kotlinmvp.util.Constant
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by nandawperdana.
 */
class APICallManager {
    var endPoint = Constant.Path.DEFAULT_URL_API
    var authorizationKey: String

    var peopleManager: PeopleManager

    init {
        if (BuildConfig.BUILD_RELEASE)
            endPoint = Constant.Path.DEFAULT_URL_API

        // enable logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        sRetrofit = Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()

        // registering API endpoint manager
        this.peopleManager = PeopleManager()

        this.authorizationKey = "2ef8838d-8638-4b75-9f52-a0d84992bf8e"
    }

    inner class PeopleManager {
        private val service by lazy {
            getService(PeopleService::class.java)
        }

        fun getPeople(): Flowable<PeopleResponse> {
            return service.getPeople("media", authorizationKey)
        }
    }

    companion object {
        var instance: APICallManager? = null
        private lateinit var sRetrofit: Retrofit

        /**
         * singleton class instance

         * @return APICallManager
         */
        val getInstance: APICallManager
            get() {
                if (instance == null) {
                    synchronized(APICallManager::class.java) {
                        if (instance == null) {
                            instance = APICallManager()
                        }
                    }
                }
                return instance!!
            }

        fun <T> getService(serviceClass: Class<T>): T {
            return sRetrofit.create(serviceClass)
        }
    }
}
