package com.example.DrinkMaster.modules.editCocktailReview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.DrinkMaster.R
import com.example.DrinkMaster.databinding.FragmentEditCocktailReviewBinding
import com.squareup.picasso.Picasso


class editCocktailReview : Fragment() {


    private var _binding: FragmentEditCocktailReviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditCocktailReviewViewModel
    private val args by navArgs<edit_cocktail_reviewArgs>()
    val star1 = binding.star1
    val star2 = binding.star2
    val star3 = binding.star3
    val star4 = binding.star4
    val star5 = binding.star5

    private val imageSelectionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            try {
                val imageUri: Uri = result.data?.data!!
                val imageSize = getImageSize(imageUri)
                val maxCanvasSize = 5 * 1024 * 1024 // 5MB
                if (imageSize > maxCanvasSize) {
                    Toast.makeText(
                        requireContext(),
                        "Selected image is too large",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.selectedImageURI.postValue(imageUri)
                    viewModel.imageChanged = true
                    binding.cocktailPicButton.setImageURI(imageUri)
                }
            } catch (e: Exception) {
                Log.d("EditReview", "Error: $e")
                Toast.makeText(
                    requireContext(), "Error processing result", Toast.LENGTH_SHORT
                ).show()
            }
        }

    @RequiresExtension(extension = Build.VERSION_CODES.R, version = 2)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCocktailReviewBinding.inflate(inflater,container,false)
        val view = inflater.inflate(R.layout.fragment_edit_cocktail_review, container, false)

        viewModel = ViewModelProvider(this).get(EditCocktailReviewViewModel::class.java)

        initFields()
        defineUpdateButtonClickListener()
        definePickImageClickListener()

        return view
    }


    @RequiresExtension(extension = Build.VERSION_CODES.R, version = 2)
    private fun definePickImageClickListener() {
        binding.cocktailPicButton.setOnClickListener {
            defineImageSelectionCallBack()
        }
    }

    private fun defineUpdateButtonClickListener() {
        binding.saveButton.setOnClickListener {
            binding.saveButton.isClickable = false
            viewModel.updateReview {
                findNavController().navigate(R.id.action_edit_profile_to_profile)
                binding.saveButton.isClickable = true
            }
        }
    }

    private fun initFields() {
        val currentReview = args.selectedReview
        viewModel.loadReview(currentReview)

        var grade = currentReview.grade
        binding.editTextCocktailDescription.setText(currentReview.coktailDescription)

        when(grade) {
            in 1..5 -> {
                star1.setImageResource(if (grade >= 1) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
                star2.setImageResource(if (grade >= 2) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
                star3.setImageResource(if (grade >= 3) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
                star4.setImageResource(if (grade >= 4) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
                star5.setImageResource(if (grade >= 5) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
            }
        }


        binding.editTextCocktailDescription.addTextChangedListener {
            viewModel.cocktaildescription = it.toString().trim()
        }

        binding.star1.setOnClickListener{onStarClicked(star1)}
        binding.star2.setOnClickListener{onStarClicked(star2)}
        binding.star3.setOnClickListener{onStarClicked(star3)}
        binding.star4.setOnClickListener{onStarClicked(star4)}
        binding.star5.setOnClickListener{onStarClicked(star5)}


        viewModel.selectedImageURI.observe(viewLifecycleOwner) { uri ->
            Picasso.get().load(uri).into(binding.cocktailPicButton)
        }

        viewModel.descriptionError.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                binding.editTextCocktailDescription.error = it
        }

    }

    private fun onStarClicked(clickedStar: ImageView) {

        val clickedStarPosition = clickedStar.tag.toString().toInt()

        // Update the image of all stars according to the clicked star
        // Update the image of all stars according to the clicked star
        for (star in listOf(star1, star2, star3, star4, star5)) {
            val starPosition = star.tag.toString().toInt()
            // If the star is before or equal to the clicked star, set it to yellow, otherwise, set it to empty
            star.setImageResource(if (starPosition <= clickedStarPosition) R.drawable.baseline_filled_star_24 else R.drawable.baseline_empty_star_border_24)
        }
        viewModel.grade = clickedStarPosition
    }

    @SuppressLint("Recycle")
    private fun getImageSize(uri: Uri?): Long {
        val inputStream = requireContext().contentResolver.openInputStream(uri!!)
        return inputStream?.available()?.toLong() ?: 0
    }

    @RequiresExtension(extension = Build.VERSION_CODES.R, version = 2)
    private fun defineImageSelectionCallBack() {
        binding.cocktailPicButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            imageSelectionLauncher.launch(intent)
        }
    }

}