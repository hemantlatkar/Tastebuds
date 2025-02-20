package com.example.tastebuds.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tastebuds.repository.RemoteRepository
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var observer: Observer<List<RecipesModel>>

    private lateinit var viewModel: RecipeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)

        viewModel = RecipeViewModel(repository)
        viewModel.recipes.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.recipes.removeObserver(observer)
    }

    @Test
    fun `fetchRecipes() should update LiveData when repository returns data`() = runTest {
        // Arrange
        val fakeRecipes = listOf(
            RecipesModel(
                idMeal = "52900",
                mealName = "Madeira Cake",
                drinkAlternate = null,
                strCategory = "Dessert",
                strArea = "British",
                description = "Pre-heat the oven to 180C/",
                ingredients = listOf(
                    IngredientUIModel("Butter", "175g", "g"),
                    IngredientUIModel("Caster Sugar", "175g", "g"),
                    IngredientUIModel("Eggs", "3", "g"),
                    IngredientUIModel("Self-raising Flour", "250g", "g"),
                    IngredientUIModel("Milk", "3 tbs", "g"),
                    IngredientUIModel("Lemon", "Zest of 1", "g"),
                    IngredientUIModel("Mixed Peel", "To Glaze", "g")
                ),
                imageUrl = "https://www.themealdb.com/images/media/meals/urtqut1511723591.jpg",
                youtubeUrl = "https://www.youtube.com/watch?v=-YDh4WEmK_E",
                source = "https://www.bbc.co.uk/food/recipes/madeiracake_73878",
                dateModified = null
            )
        )

         whenever(repository.getPopularRecipes()).thenReturn(Resource.Success(fakeRecipes))

         viewModel.fetchRecipes()
        advanceUntilIdle()

         val captor = argumentCaptor<List<RecipesModel>>()
        verify(observer, Mockito.times(1)).onChanged(captor.capture())

         assert(captor.lastValue == fakeRecipes)
    }

    @Test
    fun `fetchRecipes handles API failure gracefully`() = runTest {
         val errorMessage = "API error"
        whenever(repository.getPopularRecipes()).thenReturn(Resource.Error(errorMessage))

         viewModel.fetchRecipes()
        advanceUntilIdle()

         val captor = argumentCaptor<List<RecipesModel>>()
        verify(observer, Mockito.times(1)).onChanged(captor.capture())

         assert(captor.lastValue.isEmpty())
    }
}