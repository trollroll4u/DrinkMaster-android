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
//            it.get(0).strIngredients= listOf(
//                it.get(0)?.strMeasure1 + " " + it.get(0)?.strIngredient1,
//                it.get(0)?.strMeasure2 + " " + it.get(0)?.strIngredient2,
//                it.get(0)?.strMeasure3 + " " + it.get(0)?.strIngredient3,
//                it.get(0)?.strMeasure4 + " " + it.get(0)?.strIngredient4,
//                it.get(0)?.strMeasure5 + " " + it.get(0)?.strIngredient5,
//                it.get(0)?.strMeasure6 + " " + it.get(0)?.strIngredient6,
//                it.get(0)?.strMeasure7 + " " + it.get(0)?.strIngredient7,
//                it.get(0)?.strMeasure8 + " " + it.get(0)?.strIngredient8,
//                it.get(0)?.strMeasure9 + " " + it.get(0)?.strIngredient9,
//                it.get(0)?.strMeasure10 + " " + it.get(0)?.strIngredient10,
//                it.get(0)?.strMeasure11 + " " + it.get(0)?.strIngredient11,
//                it.get(0)?.strMeasure12 + " " + it.get(0)?.strIngredient12,
//                it.get(0)?.strMeasure13 + " " + it.get(0)?.strIngredient13,
//                it.get(0)?.strMeasure14 + " " + it.get(0)?.strIngredient14,
//                it.get(0)?.strMeasure15 + " " + it.get(0)?.strIngredient15
//            ).toString().replace("[", "").replace("]", "").replace(", ", "\n")
            cocktailDetailsData.postValue(it)
//            cocktailDetailsData = it[0]
//            cocktailDetailsData?.strIngredients = listOf(
//                cocktailDetailsData?.strIngredient1,
//                cocktailDetailsData?.strIngredient2,
//                cocktailDetailsData?.strIngredient3,
//                cocktailDetailsData?.strIngredient4,
//                cocktailDetailsData?.strIngredient5,
//                cocktailDetailsData?.strIngredient6,
//                cocktailDetailsData?.strIngredient7,
//                cocktailDetailsData?.strIngredient8,
//                cocktailDetailsData?.strIngredient9,
//                cocktailDetailsData?.strIngredient10,
//                cocktailDetailsData?.strIngredient11,
//                cocktailDetailsData?.strIngredient12,
//                cocktailDetailsData?.strIngredient13,
//                cocktailDetailsData?.strIngredient14,
//                cocktailDetailsData?.strIngredient15
//            ).filterNotNull().toString().replace("[", "").replace("]", "").replace(", ", "\n")

        //            cocktailDetailsData.strIngredients= cocktailDetailsData.strIngredients.replace("null", "")
        }
 Log.i("CocktailViewModel","ohad" )
    }

}

