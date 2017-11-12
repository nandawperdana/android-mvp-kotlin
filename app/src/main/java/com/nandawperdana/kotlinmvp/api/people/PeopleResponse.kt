package com.nandawperdana.kotlinmvp.api.people

import android.os.Parcel
import android.os.Parcelable
import com.nandawperdana.kotlinmvp.api.RootResponseModel
import com.nandawperdana.kotlinmvp.util.createParcel

/**
 * Created by nandawperdana.
 */

data class PeopleResponse(
        val message: String?,
        val data: List<PeopleModel>?
) : RootResponseModel(), Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { PeopleResponse(it) }
    }

    private constructor(parcelIn: Parcel) : this(
            parcelIn.readString(),
            mutableListOf<PeopleModel>().apply {
                parcelIn.readTypedList(this, PeopleModel.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(message)
        dest.writeTypedList(data)
    }

    override fun describeContents() = 0

    constructor() : this(message = null, data = null)
}
