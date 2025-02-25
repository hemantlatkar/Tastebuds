package com.example.tastebuds.ui.screens

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tastebuds.databinding.RecipeDetailsScreenBinding
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.ui.adapter.DetailScreenAdapter
import com.example.tastebuds.utils.Constants
import com.example.tastebuds.ui.utils.Utils

class RecipeDetailsScreen : AppCompatActivity() {

    private lateinit var binding: RecipeDetailsScreenBinding
    private lateinit var ingredientsAdapter: DetailScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        binding = RecipeDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getParcelableExtra<RecipesModel>(Constants.General.RECIPE_DATA)
        recipe?.let { setRecipeDetails(it) }
    }

    private fun setRecipeDetails(recipe: RecipesModel) {
        Utils.loadImage(binding.imgImage, recipe.imageUrl)

        binding.tvRecipeName.text = recipe.mealName
        binding.tvRecipeDescription.text = recipe.description

        binding.tvRecipeDescription.text = recipe.description
        binding.tvTime.text = generateRandomTime()

        if (recipe.ingredients.isNullOrEmpty()) {
            binding.txtSecondTitle.visibility = View.GONE
            binding.txtCount.visibility = View.GONE
        } else {
            binding.txtCount.text = "${recipe.ingredients.size} Item"
            setupRecyclerView(recipe.ingredients)
        }

        binding.ivClose.setOnClickListener { finish() }
        binding.imgFavorite.setOnClickListener { it.isSelected = !it.isSelected }

        //binding.lblTime.setOnClickListener { openUrl(recipe.source) }
        binding.btnViewYoutube.setOnClickListener { openUrl(recipe.youtubeUrl) }
    }

    private fun setupRecyclerView(ingredients: List<IngredientUIModel>) {
        ingredientsAdapter = DetailScreenAdapter(ingredients)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecipeDetailsScreen)
            adapter = ingredientsAdapter
            isNestedScrollingEnabled = false
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun openUrl(url: String?) {
        url?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    fun generateRandomTime(): String {
        val timeOptions = listOf(10, 15, 20, 25, 30, 35, 40, 45)
        return " ${timeOptions.random()} Min"
    }

}
