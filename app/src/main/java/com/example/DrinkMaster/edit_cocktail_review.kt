package com.example.DrinkMaster

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class edit_cocktail_review : Fragment() {

    companion object {
        fun newInstance() = edit_cocktail_review()
    }

    private lateinit var viewModel: EditCocktailReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_edit_cocktail_review, container, false)
        viewModel = ViewModelProvider(this).get(EditCocktailReviewViewModel::class.java)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditCocktailReviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}