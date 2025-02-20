package com.example.tastebuds.model

import android.os.Parcel
import android.os.Parcelable

data class RecipesModel(
    val idMeal: String,
    val mealName: String,
    val drinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val description: String,
    val ingredients: List<IngredientUIModel>,
    val imageUrl: String?,
    val youtubeUrl: String?,
    val source: String?,
    val dateModified: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(IngredientUIModel)!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(mealName)
        parcel.writeString(drinkAlternate)
        parcel.writeString(strCategory)
        parcel.writeString(strArea)
        parcel.writeString(description)
        parcel.writeTypedList(ingredients)
        parcel.writeString(imageUrl)
        parcel.writeString(youtubeUrl)
        parcel.writeString(source)
        parcel.writeString(dateModified)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<RecipesModel> {
        override fun createFromParcel(parcel: Parcel) = RecipesModel(parcel)
        override fun newArray(size: Int) = arrayOfNulls<RecipesModel?>(size)
    }
}
