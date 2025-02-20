package com.example.tastebuds.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastebuds.repository.RemoteRepository
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RemoteRepository,

) : ViewModel() {

    private val _recipes = MutableLiveData<List<RecipesModel>>()
    val recipes: LiveData<List<RecipesModel>> = _recipes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun fetchRecipes() {
        _loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
        try {
                val resource = repository.getPopularRecipes()
                when (resource) {
                    is Resource.Success -> {
                        val newRecipes = resource.data ?: emptyList()
                        val currentList = _recipes.value ?: emptyList()
                        _recipes.postValue(currentList + newRecipes)
                    }
                    is Resource.Error -> {
                        _errorMessage.postValue(resource.message ?: "Something went wrong!")
                        _recipes.postValue(emptyList())
                    }
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "An unknown error occurred")
                _recipes.postValue(emptyList())
            } finally {
                _loading.postValue(false)
            }
        }
    }


}