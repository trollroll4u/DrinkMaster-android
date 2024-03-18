package com.example.DrinkMaster.modules.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.cocktail.Cocktail

class SearchViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    var cocktails: MutableLiveData<MutableList<Cocktail>> = MutableLiveData()

    fun refreshCocktails(query: String) {
//        CocktailServiceClient.instance.searchCocktails(query) {
//            cocktails.postValue(it)
//        }
    }

    fun clearCocktails() {
        cocktails.postValue(mutableListOf())
    }
}