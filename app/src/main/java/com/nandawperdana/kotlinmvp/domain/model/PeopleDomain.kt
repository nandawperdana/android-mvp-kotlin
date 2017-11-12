package com.nandawperdana.kotlinmvp.domain.model

import com.nandawperdana.kotlinmvp.api.people.PeopleResponse

/**
 * Created by nandawperdana.
 */

class PeopleDomain {
    private var peopleResponse: PeopleResponse

    init {
        this.peopleResponse = PeopleResponse()
    }

    fun getPeopleResponse(): PeopleResponse? {
        return peopleResponse
    }

    fun setPeopleResponse(peopleResponse: PeopleResponse) {
        this.peopleResponse = peopleResponse
    }
}
