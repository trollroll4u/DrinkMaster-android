package com.example.DrinkMaster.modules.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.R
import com.example.DrinkMaster.data.cocktail.Cocktail
import com.squareup.picasso.Picasso

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cocktailImageView: ImageView?
    private val cocktailTitleView: TextView?

    init {
        cocktailImageView = itemView.findViewById(R.id.cocktailResultImage)
        cocktailTitleView = itemView.findViewById(R.id.cocktailResultTitle)
    }

    fun bind(cocktail: Cocktail?) {
        if (cocktail == null) {
            return
        }
        itemView.setOnClickListener {
//            val action = SearchFragmentDirections.actionSearchFragmentToCocktailFragment(cocktail)
            val action = SearchFragmentDirections.actionSearchFragmentToCocktailFragment(cocktail.idDrink)

            Navigation.findNavController(itemView).navigate(action)
        }

        Picasso.get()
            .load(cocktail.strDrinkThumb)
            .into(cocktailImageView)
        cocktailTitleView?.text = cocktail.strDrink
    }
}
