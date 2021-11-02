package com.example.challenge1

import android.os.Parcel
import android.os.Parcelable

class Guide(
    var startDate: String?, var endDate: String?, var name: String?, var url: String?, var venue: String?,
    var icon: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(venue)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Guide> {
        override fun createFromParcel(parcel: Parcel): Guide {
            return Guide(parcel)
        }

        override fun newArray(size: Int): Array<Guide?> {
            return arrayOfNulls(size)
        }
    }
}