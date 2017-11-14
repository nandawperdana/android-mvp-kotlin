package com.nandawperdana.kotlinmvp.api.people

import android.os.Parcel
import android.os.Parcelable
import com.nandawperdana.kotlinmvp.util.createParcel

/**
 * Created by nandawperdana.
 */
open class PeopleModel(
        var id: String,
        var name: String,
        var email: String,
        var gender: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { PeopleModel(it) }
    }
}
