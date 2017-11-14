package com.nandawperdana.kotlinmvp.api

import com.nandawperdana.kotlinmvp.BuildConfig
import com.nandawperdana.kotlinmvp.api.sample.SampleResponse
import com.nandawperdana.kotlinmvp.api.sample.SampleService
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
    private var endPoint = Constant.Path.DEFAULT_URL_API
    lateinit var authorizationKey: String

    var sampleManager: SampleManager

    init {
        if (BuildConfig.BUILD_RELEASE)
            endPoint = Constant.Path.DEFAULT_URL_API

        // enable logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()

        // registering API endpoint manager
        this.sampleManager = SampleManager()
    }

    companion object {
        private var instance: APICallManager? = null
        private lateinit var retrofit: Retrofit

        /**
         * singleton class instance
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
            return retrofit.create(serviceClass)
        }
    }

    // region Service Managers
    /**
     * A sample API manager.
     * TODO: Replace this with your own API manager class
     */
    inner class SampleManager {
        private val service by lazy {
            getService(SampleService::class.java)
        }

        fun getSample(): Flowable<SampleResponse> {
            return service.getSample()
        }
    }
    //endregion
}
