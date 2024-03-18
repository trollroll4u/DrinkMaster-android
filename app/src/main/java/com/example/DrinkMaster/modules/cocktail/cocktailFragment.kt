package com.example.DrinkMaster.modules.cocktail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.DrinkMaster.R
import com.squareup.picasso.Picasso



class cocktailFragment : Fragment() {


    private val args by navArgs<cocktailFragmentArgs>()
    private lateinit var viewModel: CocktailViewModel


    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root =  inflater.inflate(R.layout.fragment_cocktail, container, false)
        viewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)
        viewModel.setCocktail(args.chooseCocktail)

        cocktailsDetails(root)
        root.findViewById<Button>(R.id.AddReviewButton).setOnClickListener {
            val action = cocktailFragmentDirections.actionCocktailFragmentToCreateReview(viewModel.cocktailDetailsData!!)

            findNavController().navigate(action)
        }

        return root
    }

    fun cocktailsDetails(root: View) {
        val cocktailName: TextView = root.findViewById(R.id.coktailTitle)
        val cocktailIngredient: TextView = root.findViewById(R.id.coktailIngredient)
        val cocktailInstruction: TextView = root.findViewById(R.id.coktailInstruction)
        val cocktailImage : ImageView = root.findViewById(R.id.cocktailImage)

        viewModel.cocktailDetailsData?.let { cocktail ->
            cocktailName.text = cocktail.strDrink
            cocktailInstruction.text = cocktail.strInstructions
            cocktailIngredient.text = cocktail.strIngredients
            Picasso.get()
                .load(cocktail.strDrinkThumb)
                .into(cocktailImage)
            cocktailInstruction.movementMethod = ScrollingMovementMethod()
            cocktailIngredient.movementMethod = ScrollingMovementMethod()
        }
    }

}