package com.example.edp

import android.os.Parcel
import android.os.Parcelable

data class user (
    val id:String="",
    val name:String="",
    val email:String="",
    val car:String="",
    val image:String="",
    val mobile:Long=0,
    val speed:Long=0,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(car)
        parcel.writeString(image)
        parcel.writeLong(mobile)
        parcel.writeLong(speed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<user> {
        override fun createFromParcel(parcel: Parcel): user {
            return user(parcel)
        }

        override fun newArray(size: Int): Array<user?> {
            return arrayOfNulls(size)
        }
    }
}