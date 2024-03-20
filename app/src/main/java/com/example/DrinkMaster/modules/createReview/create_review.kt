package com.example.DrinkMaster.modules.createReview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.DrinkMaster.R

class create_review : Fragment() {

    companion object {
        fun newInstance() = create_review()
    }

    private lateinit var viewModel: CreateReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_review, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateReviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}