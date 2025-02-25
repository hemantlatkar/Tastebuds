package com.example.tastebuds.ui.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tastebuds.R
import com.example.tastebuds.databinding.HomeScreenBinding
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.ui.adapter.RecipeListAdapter
import com.example.tastebuds.ui.viewmodel.RecipeViewModel
import com.example.tastebuds.utils.Constants
import com.example.tastebuds.ui.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {

    private lateinit var binding: HomeScreenBinding
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeListAdapter: RecipeListAdapter
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.loading.observe(this, { loading ->
            if (loading) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.GONE
            }
        })
        viewModel.errorMessage.observe(this, { errorMessage ->
            if(errorMessage.isNotEmpty()){
                NetworkUtils.showErrorMessage(this@HomeScreen,errorMessage)
            }
        })

        viewModel.recipes.observe(this, { recipes ->
            recipeListAdapter.setData(recipes)

            if (recipes.isNotEmpty()) {
                binding.lblTitle.visibility = View.VISIBLE
                adjustHeaderConstraints()
            }
        })
    }

    private fun adjustHeaderConstraints() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.main)
        constraintSet.clear(binding.lblHeader.id, ConstraintSet.BOTTOM)
        constraintSet.connect(binding.lblHeader.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.applyTo(binding.main)
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(this)
        recipeListAdapter = RecipeListAdapter(mutableListOf())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = recipeListAdapter
        recipeListAdapter.onItemClick = { selectedRecipe ->
            navigateToDetail(selectedRecipe)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItem >= totalItemCount - 2) {
                    isLoading = true
                    makeRequestCall()
                    Handler(Looper.getMainLooper()).postDelayed({ isLoading = false }, 2000)
                }
            }
        })
        makeRequestCall()
    }

    private fun makeRequestCall() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            NetworkUtils.showErrorMessage(this, getString(R.string.no_internet_connection))
        } else {
            viewModel.fetchRecipes()
        }
    }

    private fun navigateToDetail(meal: RecipesModel) {
        val intent = Intent(this, RecipeDetailsScreen::class.java).apply {
            putExtra(Constants.General.RECIPE_DATA, meal)
        }
        startActivity(intent)
    }
}