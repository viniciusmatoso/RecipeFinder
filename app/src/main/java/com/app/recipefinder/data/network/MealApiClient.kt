package com.app.recipefinder.data.network

import com.app.recipefinder.data.model.Meal
import com.app.recipefinder.data.model.MealResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object MealApiClient {
    private val apiClient = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getRandomRecipe(): List<Meal>{
        val url = "https://www.themeldb.com/api/json/v1/1/random.php"
        val response = apiClient.get(url).body() as MealResponse
        return response.meals
    }

    suspend fun getSearchedRecipe(query: String): List<Meal>{
        val url = "https://www.themeldb.com/api/json/v1/1/search.php?s=$query"
        val response = apiClient.get(url).body() as MealResponse
        return response.meals
    }
}