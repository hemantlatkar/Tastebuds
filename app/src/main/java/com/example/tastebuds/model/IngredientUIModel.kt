package com.example.tastebuds.model

import android.os.Parcel
import android.os.Parcelable

data class IngredientUIModel(
    val name: String,
    val imageUrl: String,
    val measure: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(measure)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<IngredientUIModel> {
        override fun createFromParcel(parcel: Parcel) = IngredientUIModel(parcel)
        override fun newArray(size: Int) = arrayOfNulls<IngredientUIModel?>(size)
    }
}