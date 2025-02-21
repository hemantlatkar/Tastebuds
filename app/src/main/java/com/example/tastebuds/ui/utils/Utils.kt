package com.example.tastebuds.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.tastebuds.BuildConfig
import com.example.tastebuds.R
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.remote.Meals

object Utils {

    fun loadImage(imageView: ImageView, imageUrl: String?) {
        if (imageUrl.isNullOrEmpty()) {
            imageView.setImageResource(R.drawable.img_placeholder)
            return
        }

        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_error_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(withCrossFade(factory))
            .into(imageView)
    }

    fun Meals.toPopularRecipeModel(): RecipesModel {
        val ingredientsUrl = BuildConfig.INGREDIENTS_BASE_URL

        val ingredientsList = listOf(
            strIngredient1 to strMeasure1,
            strIngredient2 to strMeasure2,
            strIngredient3 to strMeasure3,
            strIngredient4 to strMeasure4,
            strIngredient5 to strMeasure5,
            strIngredient6 to strMeasure6,
            strIngredient7 to strMeasure7,
            strIngredient8 to strMeasure8,
            strIngredient9 to strMeasure9,
            strIngredient10 to strMeasure10,
            strIngredient11 to strMeasure11,
            strIngredient12 to strMeasure12,
            strIngredient13 to strMeasure13,
            strIngredient14 to strMeasure14,
            strIngredient15 to strMeasure15,
            strIngredient16 to strMeasure16,
            strIngredient17 to strMeasure17,
            strIngredient18 to strMeasure18,
            strIngredient19 to strMeasure19,
            strIngredient20 to strMeasure20
        )

        val ingredients = ingredientsList
            .filter { it.first?.isNotEmpty() == true }
            .map { (ingredient, measure) ->
                IngredientUIModel(
                    name = ingredient!!,
                    imageUrl = "$ingredientsUrl$ingredient.png",
                    measure = measure.orEmpty()
                )
            }

        return RecipesModel(
            idMeal = idMeal,
            mealName = strMeal,
            drinkAlternate = strDrinkAlternate,
            strCategory = strCategory,
            strArea = strArea,
            description = strInstructions,
            ingredients = ingredients,
            imageUrl = strMealThumb,
            youtubeUrl = strYoutube,
            source = strSource,
            dateModified = dateModified,
        )
    }
}