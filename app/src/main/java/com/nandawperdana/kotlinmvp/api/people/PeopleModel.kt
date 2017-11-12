package com.nandawperdana.kotlinmvp.api.people

import android.os.Parcel
import android.os.Parcelable
import com.nandawperdana.kotlinmvp.util.createParcel
import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by nandawperdana.
 */
@RealmClass
open class PeopleModel(
        var id: Int,
        var first_name: String,
        var last_name: String,
        var profile_pic: String,
        var url: String
) : RealmObject(), Parcelable {

    constructor() : this(
            0, "", "", "", ""
    )

    private constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(profile_pic)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { PeopleModel(it) }
    }

}
