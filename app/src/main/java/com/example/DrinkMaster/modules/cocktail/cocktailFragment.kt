package com.example.DrinkMaster.modules.cocktail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.R
import com.example.DrinkMaster.data.review.ReviewModel
import com.example.DrinkMaster.databinding.FragmentCocktailBinding
import com.example.DrinkMaster.databinding.FragmentCocktailsFeedBinding
import com.example.DrinkMaster.modules.feed.CocktailsFeedViewModel
import com.example.DrinkMaster.modules.feed.FeedRecycleAdapter
import com.squareup.picasso.Picasso



class cocktailFragment : Fragment() {



    private lateinit var viewModel: CocktailViewModel
    private val args by navArgs<cocktailFragmentArgs>()

    //***********************************************************

    private var _binding: FragmentCocktailBinding? = null

    private val binding get() = _binding!!
    private var reviewsRecyclerView: RecyclerView? = null
    private var adapter: FeedRecycleAdapter? = null






    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ohad","ohad1")
        _binding = FragmentCocktailBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)
        viewModel.setCocktail(args.chooseCocktail)
        Log.i("ohad","ohad11")

        reviewsRecyclerView = binding.Feed
        reviewsRecyclerView?.setHasFixedSize(true)
        reviewsRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = FeedRecycleAdapter(viewModel.reviews?.value, viewModel.users?.value)

        reviewsRecyclerView?.adapter = adapter

        viewModel.reviews?.observe(viewLifecycleOwner) {
            adapter?.reviews = it
            adapter?.notifyDataSetChanged()
        }

        viewModel.users?.observe(viewLifecycleOwner) {
            adapter?.users = it
            adapter?.notifyDataSetChanged()
        }

        binding.pullToRefresh.setOnRefreshListener {
            viewModel.reloadData()
        }

        viewModel.reviewsListLoadingState.observe(viewLifecycleOwner) { state ->
            binding.pullToRefresh.isRefreshing = state == ReviewModel.LoadingState.LOADING
        }

        viewModel.cocktailDetailsData.observe(viewLifecycleOwner) {
            Log.d("TAG", "cocktails size ${it?.size}")
            cocktailsDetails(root)

        }
            binding.AddReviewButton.setOnClickListener {
                viewModel.cocktailDetailsData?.let { cocktail ->

                    val action = cocktailFragmentDirections.actionCocktailFragmentToCreateReview()
                    Navigation.findNavController(root.findViewById<Button>(R.id.AddReviewButton))
                        .navigate(action)
//                findNavController().navigate(action)
                }
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

    fun cocktailsDetails(root: View) {
        val cocktailName: TextView = root.findViewById(R.id.coktailTitle)
        val cocktailIngredient: TextView = root.findViewById(R.id.coktailIngredient)
        val cocktailInstruction: TextView = root.findViewById(R.id.coktailInstruction)
        val cocktailImage : ImageView = root.findViewById(R.id.cocktailImage)


        viewModel.cocktailDetailsData?.let { cocktail ->
            cocktailName.text = cocktail.value?.get(0)?.strDrink
            cocktailInstruction.text = cocktail.value?.get(0)?.strInstructions
            cocktailIngredient.text = cocktail.value?.get(0)?.strIngredients
            Picasso.get()
                .load(cocktail.value?.get(0)?.strDrinkThumb)
                .into(cocktailImage)
            cocktailInstruction.movementMethod = ScrollingMovementMethod()
            cocktailIngredient.movementMethod = ScrollingMovementMethod()
        }
    }

}