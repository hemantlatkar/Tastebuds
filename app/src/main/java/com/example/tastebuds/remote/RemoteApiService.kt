

package com.example.tastebuds.remote

import com.example.tastebuds.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApiService {

    @GET(Constants.EndPoint.POPULAR_RECIPES)
    suspend fun getPopularRecipes(): Response<RemoteDataModel>

}