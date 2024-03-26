package com.example.DrinkMaster.modules.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var searchRecyclerView: RecyclerView? = null
    private var _binding: FragmentSearchBinding? = null
    private var adapter: SearchAdapter? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        searchRecyclerView = binding.searchResultsLayout
        searchRecyclerView?.setHasFixedSize(true)
        searchRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = SearchAdapter(viewModel.cocktails.value)

        searchRecyclerView?.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            Log.d("TAG", "cocktails size ${it?.size}")
            adapter?.cocktails = it
            adapter?.notifyDataSetChanged()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.clearCocktails()
                viewModel.refreshCocktails(query)

                binding.SearchTextView.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (viewModel.cocktails.value?.isNotEmpty() == true)
                    binding.SearchTextView.visibility = View.GONE
                else
                    binding.SearchTextView.visibility = View.VISIBLE

                return false
            }
        })

        return view
    }


}
