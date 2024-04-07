package com.example.DrinkMaster.modules.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.cocktail.Cocktail
import com.example.DrinkMaster.data.cocktail.CocktailService

class SearchViewModel : ViewModel() {
    var cocktails: MutableLiveData<MutableList<Cocktail>> = MutableLiveData()

    fun refreshCocktails(query: String) {
        CocktailService.instance.searchCocktail(query) {
            cocktails.postValue(it)
        }
    }
    fun clearCocktails() {
        cocktails.postValue(mutableListOf())
    }
}
