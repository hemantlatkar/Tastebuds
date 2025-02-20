package com.example.tastebuds.network

import com.example.tastebuds.BuildConfig
import com.example.tastebuds.remote.RemoteApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ApiServiceTest {

    private lateinit var apiService: RemoteApiService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TASTEBUDS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(RemoteApiService::class.java)
    }

    @Test
    fun `API returns popular recipes`() = runBlocking {
         val response = apiService.getPopularRecipes()

         assertNotNull(response)
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())

        val RemoteDataModel = response.body()
        assertNotNull(RemoteDataModel?.meals)
        assertTrue(RemoteDataModel!!.meals.isNotEmpty())
    }
}