package com.example.DrinkMaster.modules.myReviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.review.ReviewModel
import com.example.DrinkMaster.data.user.User
import com.example.DrinkMaster.data.user.UserModel

class ProfileReviewsViewModel : ViewModel() {


    val reviews: LiveData<MutableList<Review>> = ReviewModel.instance.getMyReviews()
    val user: LiveData<User> = UserModel.instance.getCurrentUser()
    val reviewsListLoadingState: MutableLiveData<ReviewModel.LoadingState> =
        ReviewModel.instance.reviewsListLoadingState

    fun reloadData() {
        UserModel.instance.refreshAllUsers()
        ReviewModel.instance.refreshAllReviews()
    }
}