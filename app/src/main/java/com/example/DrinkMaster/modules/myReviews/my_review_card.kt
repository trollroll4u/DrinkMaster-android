package com.example.DrinkMaster.modules.myReviews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.DrinkMaster.R

class my_review_card : Fragment() {

    companion object {
        fun newInstance() = my_review_card()
    }

    private lateinit var viewModel: MyReviewCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_review_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyReviewCardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}