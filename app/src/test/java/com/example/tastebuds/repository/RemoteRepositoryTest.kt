package com.example.tastebuds.repository

import com.example.tastebuds.remote.Meals
import com.example.tastebuds.remote.RemoteDataModel
import com.example.tastebuds.remote.RemoteApiService
import com.example.tastebuds.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: RemoteApiService

    private lateinit var repository: RemoteRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = RemoteRepository(apiService)
    }

    @Test
    fun `getPopularRecipes returns success`() = runBlocking {
        val mockRemoteDataModel = RemoteDataModel(
            meals = listOf(
                Meals(
                    idMeal = "52900",
                    strMeal = "Madeira Cake",
                    strDrinkAlternate = null,
                    strCategory = "Dessert",
                    strArea = "British",
                    strInstructions = "Pre-heat the oven to 180C/350F/Gas 4...",
                    strMealThumb = "https://www.themealdb.com/images/media/meals/urtqut1511723591.jpg",
                    strTags = "Cake,Light,Baking,Desert",
                    strYoutube = "https://www.youtube.com/watch?v=-YDh4WEmK_E",
                    strIngredient1 = "Butter",
                    strIngredient2 = "Caster Sugar",
                    strIngredient3 = "Eggs",
                    strIngredient4 = "Self-raising Flour",
                    strIngredient5 = "Milk",
                    strIngredient6 = "Lemon",
                    strIngredient7 = "Mixed Peel",
                    strMeasure1 = "175g",
                    strMeasure2 = "175g",
                    strMeasure3 = "3",
                    strMeasure4 = "250g",
                    strMeasure5 = "3 tbs",
                    strMeasure6 = "Zest of 1",
                    strMeasure7 = "To Glaze",
                    strSource = "https://www.bbc.co.uk/food/recipes/madeiracake_73878"
                )
            )
        )
        whenever(apiService.getPopularRecipes()).thenReturn(Response.success(mockRemoteDataModel))

        val result = repository.getPopularRecipes()

        assertTrue(result is Resource.Success)
        assertEquals(1, (result as Resource.Success).data?.size)
        assertEquals("Madeira Cake", result.data!![0].mealName)
    }

    @Test
    fun `getPopularRecipes returns error`() = runBlocking {
        whenever(apiService.getPopularRecipes()).thenReturn(Response.error(404, okhttp3.ResponseBody.create(null, "")))

        val result = repository.getPopularRecipes()

        assertTrue(result is Resource.Error)
        assertEquals("Failed to fetch recipes", (result as Resource.Error).message)
    }
}