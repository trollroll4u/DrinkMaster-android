package com.example.DrinkMaster.modules.cocktail

import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.cocktail.Cocktail

class CocktailViewModel : ViewModel() {
    var cocktailDetailsData: Cocktail? = null

    fun setCocktail(cocktail: Cocktail) {
        cocktailDetailsData = cocktail
    }
    fun getCocktail() : Cocktail? {
        return cocktailDetailsData
    }

}

