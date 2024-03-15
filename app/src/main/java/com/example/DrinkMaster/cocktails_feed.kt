package com.example.DrinkMaster

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class cocktails_feed : Fragment() {

    companion object {
        fun newInstance() = cocktails_feed()
    }

    private lateinit var viewModel: CocktailsFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktails_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CocktailsFeedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}