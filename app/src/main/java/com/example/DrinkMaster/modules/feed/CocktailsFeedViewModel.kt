package com.example.DrinkMaster.modules.feed

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.review.ReviewModel
import com.example.DrinkMaster.data.user.User
import com.example.DrinkMaster.data.user.UserModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CocktailsFeedViewModel() : ViewModel() {
    val reviews: LiveData<MutableList<Review>> = ReviewModel.instance.getAllReviews()
    val users: LiveData<MutableList<User>> = UserModel.instance.getAllUsers()
    val reviewsListLoadingState: MutableLiveData<ReviewModel.LoadingState> =
        ReviewModel.instance.reviewsListLoadingState

    fun reloadData() {
        UserModel.instance.refreshAllUsers()
        ReviewModel.instance.refreshAllReviews()
    }
    fun loadReviewsFromAssets(context: Context): MutableList<Review> {
        val reviews = mutableListOf<Review>()
        val json: String

        try {
            val inputStream = context.assets.open("reviews.json")
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val reviewMap = jsonObject.toMap()
                val review = Review.fromJSON(reviewMap)
                reviews.add(review)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return reviews
    }

    fun loadUsersFromAssets(context: Context): MutableList<User> {
        val users = mutableListOf<User>()
        val json: String

        try {
            val inputStream = context.assets.open("users.json")
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val userMap = jsonObject.toMap()
                val user = User.fromJSON(userMap)
                users.add(user)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return users
    }

    fun JSONObject.toMap(): Map<String, Any> {
        val keys = keys()
        val map = mutableMapOf<String, Any>()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = this.get(key)
            map[key] = value
        }
        return map
    }
}




//package com.example.DrinkMaster.modules.feed
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.DrinkMaster.data.review.Review
//import com.example.DrinkMaster.data.review.ReviewModel
//import com.example.DrinkMaster.data.user.User
//import com.example.DrinkMaster.data.user.UserModel
//import org.json.JSONArray
//import org.json.JSONObject
//import java.io.IOException
//
//
//
//
//
//class CocktailsFeedViewModel (private val context: Context) : ViewModel() {
//    // TODO: Implement the ViewModel
//
////    val reviews: LiveData<MutableList<Review>> = ReviewModel.instance.getAllReviews()
//private val _reviews = MutableLiveData<MutableList<Review>>()
//    val reviews: LiveData<MutableList<Review>> = _reviews
//    val users: LiveData<MutableList<User>> = UserModel.instance.getAllUsers()
//    val reviewsListLoadingState: MutableLiveData<ReviewModel.LoadingState> =
//        ReviewModel.instance.reviewsListLoadingState
//
//    init {
//        _reviews.value = loadReviewsFromAssets(context)
//    }
////
//    fun reloadData() {
//        UserModel.instance.refreshAllUsers()
//        ReviewModel.instance.refreshAllReviews()
//    }
//    fun loadReviewsFromAssets(context: Context): MutableList<Review> {
//        val reviews = mutableListOf<Review>()
//        val json: String
//
//        try {
//            val inputStream = context.assets.open("reviews.json")
//            json = inputStream.bufferedReader().use { it.readText() }
//            val jsonArray = JSONArray(json)
//
//            for (i in 0 until jsonArray.length()) {
//                val jsonObject = jsonArray.getJSONObject(i)
//                val reviewMap = jsonObject.toMap()
//                val review = Review.fromJSON(reviewMap)
//                reviews.add(review)
//            }
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//        }
//
//        return reviews
//    }
//    fun JSONObject.toMap(): Map<String, Any> {
//        val keys = keys()
//        val map = mutableMapOf<String, Any>()
//        while (keys.hasNext()) {
//            val key = keys.next()
//            val value = this.get(key)
//            map[key] = value
//        }
//        return map
//    }
//}
//
//
