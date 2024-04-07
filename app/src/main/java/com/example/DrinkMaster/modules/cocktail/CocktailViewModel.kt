package com.example.DrinkMaster.modules.cocktail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.cocktail.Cocktail
import com.example.DrinkMaster.data.cocktail.CocktailService

class CocktailViewModel : ViewModel() {
    var cocktailDetailsData: MutableLiveData<MutableList<Cocktail>> = MutableLiveData()
    var cocktailID : String = ""

    fun setCocktail(cocktailID: String) {
        this.cocktailID = cocktailID

        CocktailService.instance.searchCocktailByID(cocktailID){
            it.get(0).strIngredients = listOf(
                it.get(0)?.strMeasure1?.let { measure -> it.get(0)?.strIngredient1?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure2?.let { measure -> it.get(0)?.strIngredient2?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure3?.let { measure -> it.get(0)?.strIngredient3?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure4?.let { measure -> it.get(0)?.strIngredient4?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure5?.let { measure -> it.get(0)?.strIngredient5?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure6?.let { measure -> it.get(0)?.strIngredient6?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure7?.let { measure -> it.get(0)?.strIngredient7?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure8?.let { measure -> it.get(0)?.strIngredient8?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure9?.let { measure -> it.get(0)?.strIngredient9?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure10?.let { measure -> it.get(0)?.strIngredient10?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure11?.let { measure -> it.get(0)?.strIngredient11?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure12?.let { measure -> it.get(0)?.strIngredient12?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure13?.let { measure -> it.get(0)?.strIngredient13?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure14?.let { measure -> it.get(0)?.strIngredient14?.let { ingredient -> "$measure $ingredient" } },
                it.get(0)?.strMeasure15?.let { measure -> it.get(0)?.strIngredient15?.let { ingredient -> "$measure $ingredient" } }
            ).filterNotNull().joinToString("\n")
            cocktailDetailsData.postValue(it)
  }
    }

}

