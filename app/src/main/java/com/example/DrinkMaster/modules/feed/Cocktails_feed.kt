package com.example.DrinkMaster.modules.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.databinding.FragmentCocktailsFeedBinding
import com.example.DrinkMaster.modules.feed.FeedRecycleAdapter
import com.example.DrinkMaster.data.review.ReviewModel

class Feed : Fragment() {
    private var reviewsRecyclerView: RecyclerView? = null
    private var adapter: FeedRecycleAdapter? = null
    private var _binding: FragmentCocktailsFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CocktailsFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("ohad","ohad1")
        _binding = FragmentCocktailsFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[CocktailsFeedViewModel::class.java]

        reviewsRecyclerView = binding.cocktailsFeed
        reviewsRecyclerView?.setHasFixedSize(true)
        reviewsRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = FeedRecycleAdapter(viewModel.reviews.value, viewModel.users.value)

        reviewsRecyclerView?.adapter = adapter

        viewModel.reviews.observe(viewLifecycleOwner) {
            adapter?.reviews = it
            adapter?.notifyDataSetChanged()
        }

        viewModel.users.observe(viewLifecycleOwner) {
            adapter?.users = it
            adapter?.notifyDataSetChanged()
        }

        binding.pullToRefresh.setOnRefreshListener {
            viewModel.reloadData()
        }

        viewModel.reviewsListLoadingState.observe(viewLifecycleOwner) { state ->
            binding.pullToRefresh.isRefreshing = state == ReviewModel.LoadingState.LOADING
        }

        return view
    }


    override fun onResume() {
        super.onResume()
        viewModel.reloadData()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

































//package com.example.DrinkMaster.modules.feed
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.get
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.DrinkMaster.R
//import com.example.DrinkMaster.data.review.Review
//import com.example.DrinkMaster.data.review.ReviewModel
//import com.example.DrinkMaster.data.user.User
//import com.example.DrinkMaster.data.user.UserModel
//import com.example.DrinkMaster.databinding.FragmentCocktailsFeedBinding
//
//
//class Cocktails_feed : Fragment() {
////
////    private lateinit var reviewsRecyclerView: RecyclerView
////    private var adapter: FeedRecycleAdapter? = null
//    private var _binding: FragmentCocktailsFeedBinding? = null
//    private val binding get() = _binding!!
////    private lateinit var viewModel: CocktailsFeedViewModel
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Log.i("ohad","hereee")
//        return inflater.inflate(R.layout.fragment_cocktails_feed, container, false)
////        _binding = FragmentCocktailsFeedBinding.inflate(inflater, container, false)
////        val view = binding.root
//
////
////        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
////            override fun <T : ViewModel> create(modelClass: Class<T>): T {
////                return CocktailsFeedViewModel(requireContext()) as T
////            }
////        }).get(CocktailsFeedViewModel::class.java)
////        reviewsRecyclerView = binding.cocktailsFeed
////        reviewsRecyclerView?.setHasFixedSize(true)
////        reviewsRecyclerView?.layoutManager = LinearLayoutManager(this.context)
////
////        adapter = viewModel.reviews.value?.let { viewModel.users.value?.let { it1 ->
////            FeedRecycleAdapter(it,
////                it1
////            )
////        } }
////
////        reviewsRecyclerView?.adapter = adapter
////
////
////        viewModel.reviews.observe(viewLifecycleOwner) {
////            adapter?.reviews = it
////            adapter?.notifyDataSetChanged()
////        }
////
////        viewModel.users.observe(viewLifecycleOwner) {
////            adapter?.users = it
////            adapter?.notifyDataSetChanged()
////        }
////
////        binding.pullToRefresh.setOnRefreshListener {
////            viewModel.reloadData()
////        }
////
////        viewModel.reviewsListLoadingState.observe(viewLifecycleOwner) { state ->
////            binding.pullToRefresh.isRefreshing = state == ReviewModel.LoadingState.LOADING
////        }
//
////        return view
//    }
//
//    override fun onResume() {
//        super.onResume()
////        viewModel.reloadData()
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//
//}