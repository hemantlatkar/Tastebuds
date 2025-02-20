package com.example.tastebuds.repository

import com.example.tastebuds.remote.RemoteApiService
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.ui.utils.Utils.toPopularRecipeModel
import com.example.tastebuds.utils.Resource
import javax.inject.Inject

open class RemoteRepository @Inject constructor(private val apiService: RemoteApiService) {

    suspend fun getPopularRecipes(): Resource<List<RecipesModel>> {
        return try {
            val response = apiService.getPopularRecipes()
            if (response.isSuccessful && response.body() != null) {
                val remoteRecipes = response.body()!!.meals

                val localRecipes = remoteRecipes.map { it.toPopularRecipeModel() }

                Resource.Success(localRecipes)
            } else {
                Resource.Error("Failed to fetch recipes")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
