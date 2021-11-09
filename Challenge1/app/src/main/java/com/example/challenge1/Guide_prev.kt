package com.example.challenge1

import android.os.Parcel
import android.os.Parcelable

class Guide_prev(
    private var startDate: String?, private var endDate: String?, var name: String?, private var url: String?, private var venue: String?,
    private var icon: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


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

    companion object CREATOR : Parcelable.Creator<Guide_prev> {
        override fun createFromParcel(parcel: Parcel): Guide_prev {
            return Guide_prev(parcel)
        }

        override fun newArray(size: Int): Array<Guide_prev?> {
            return arrayOfNulls(size)
        }
    }
}