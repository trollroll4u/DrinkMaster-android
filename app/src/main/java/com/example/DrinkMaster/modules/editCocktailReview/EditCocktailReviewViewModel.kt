package com.example.DrinkMaster.modules.editCocktailReview

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.review.ReviewModel

class EditCocktailReviewViewModel : ViewModel() {
    var imageChanged = false
    var selectedImageURI: MutableLiveData<Uri> = MutableLiveData()
    var review: Review? = null

    var cocktaildescription: String? = null
    var grade: Int? = null
    var descriptionError = MutableLiveData("")
    var ratingError = MutableLiveData("")

    fun loadReview(review: Review) {
        this.review = review
        this.cocktaildescription = review.coktailDescription
        this.grade = review.grade

        ReviewModel.instance.getReviewImage(review.id) {
            selectedImageURI.postValue(it)
        }
    }

    fun updateReview(
        updatedReviewCallback: () -> Unit
    ) {
        if (validateReviewUpdate()) {
            val updatedReview = Review(
                review!!.id,
                review!!.coktailName,
                cocktaildescription!!,
                grade!!,
                review!!.userId,
            )

            ReviewModel.instance.updateReview(updatedReview) {
                if (imageChanged) {
                    ReviewModel.instance.updateReviewImage(review!!.id, selectedImageURI.value!!) {
                        updatedReviewCallback()
                    }
                } else {
                    updatedReviewCallback()
                }
            }
        }
    }

    private fun validateReviewUpdate(
    ): Boolean {
        if (cocktaildescription != null && cocktaildescription!!.isEmpty()) {
            descriptionError.postValue("Description cannot be empty")
            return false
        }
        if (grade == null) {
            ratingError.postValue("Rating cannot be empty")
            return false
        }
        return true
    }
}