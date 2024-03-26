package com.example.DrinkMaster.data.cocktail

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailService {
    companion object {
        val instance: CocktailService = CocktailService()
    }

    private val apiService: CocktailApiService = RetrofitClient.retrofit.create(CocktailApiService::class.java)

    fun searchCocktail(strDrink: String, callback: (MutableList<Cocktail>) -> Unit) {
        val call: Call<DrinksResponse> = apiService.searchCocktail(strDrink)
        call.enqueue(object : Callback<DrinksResponse> {
            override fun onResponse(call: Call<DrinksResponse>, response: Response<DrinksResponse>) {
                if (response.isSuccessful) {
                    val cocktails: List<Cocktail>? = response.body()?.drinks
                    callback(cocktails?.toMutableList() ?: mutableListOf())
                } else {
                    throw Exception("Failed to fetch cocktails")
                }
            }

            override fun onFailure(call: Call<DrinksResponse>, t: Throwable) {
                throw Exception("Failed to fetch cocktails")
            }
        })
    }

    fun searchCocktailByID(DrinkID: String, callback: (MutableList<Cocktail>) -> Unit) {
        val call: Call<DrinksResponse> = apiService.searchCocktailbYID(DrinkID)
        call.enqueue(object : Callback<DrinksResponse> {
            override fun onResponse(call: Call<DrinksResponse>, response: Response<DrinksResponse>) {
                if (response.isSuccessful) {
                    val cocktails: List<Cocktail>? = response.body()?.drinks
                    callback(cocktails?.toMutableList() ?: mutableListOf())
                } else {
                    throw Exception("Failed to fetch cocktails")
                }
            }

            override fun onFailure(call: Call<DrinksResponse>, t: Throwable) {
                throw Exception("Failed to fetch cocktails")
            }
        })
    }
    object RetrofitClient {
        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
