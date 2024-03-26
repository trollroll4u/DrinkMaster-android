package com.example.DrinkMaster.data.cocktail

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

data class Cocktail(
    val idDrink: String,
    // Title
    val strDrink: String,
    // Instructions
    var strInstructions: String,
    // Image
    val strDrinkThumb: String,

    // Ingredients
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,

    // Measures
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
) : Serializable {
    var strIngredients: String
    val strMeasures: String

    init {
        val ingredients = listOf(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13,
            strIngredient14,
            strIngredient15
        ).filterNotNull()

        val measures = listOf(
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            strMeasure5,
            strMeasure6,
            strMeasure7,
            strMeasure8,
            strMeasure9,
            strMeasure10,
            strMeasure11,
            strMeasure12,
            strMeasure13,
            strMeasure14,
            strMeasure15
        ).filterNotNull()

        strIngredients = ingredients.joinToString(", ")
        strMeasures = measures.joinToString(", ")
    }
}
data class DrinksResponse(
    val drinks: List<Cocktail>
)
interface CocktailApiService {
    @GET("search.php")
    fun searchCocktail(
        @Query("s") strDrink: String
    ): Call<DrinksResponse>

    @GET("lookup.php")
    fun searchCocktailbYID(
        @Query("i") DrinkID: String
    ): Call<DrinksResponse>
}

