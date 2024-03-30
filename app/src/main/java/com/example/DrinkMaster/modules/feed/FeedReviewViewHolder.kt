package com.example.DrinkMaster.modules.feed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.R
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.user.User
import com.squareup.picasso.Picasso

class FeedReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val reviewImageView: ImageView? = itemView.findViewById(R.id.profileReviewCardImage)
    val profileImageView: ImageView? = itemView.findViewById(R.id.ProfileImageView)
    val profileName: TextView? = itemView.findViewById(R.id.ProfileName)
    val coktailName: TextView? = itemView.findViewById(R.id.CoktailName)
    val coktailDescription: TextView? = itemView.findViewById(R.id.CoktailDescription)
    val reviewGrade: TextView? = itemView.findViewById(R.id.ReviewGrade)

    fun bind(review: Review?, user: User?) {
        if (review != null && user != null) {
            Picasso.get()
                .load(review.reviewImage)
                .into(reviewImageView)
            Picasso.get()
                .load(user.profileImageUrl)
                .into(profileImageView)
            val userName = "${user.firstName} ${user.lastName}"
            profileName?.text = userName
            coktailName?.text = review.coktailName
            coktailDescription?.text = review.coktailDescription
            reviewGrade?.text = "Rating: ${review.grade} ★"
        }
    }
}




//package com.example.DrinkMaster.modules.feed
//
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.DrinkMaster.R
//import com.example.DrinkMaster.data.review.Review
//import com.example.DrinkMaster.data.user.User
//import com.squareup.picasso.Picasso
//
//class FeedReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    val reviewImageView: ImageView?
//    val profileImageView: ImageView?
//    val profileName: TextView?
//    val coktailName: TextView?
//    val coktailDescription: TextView?
//    val reviewGrade: TextView?
//
//
//    init {
//        reviewImageView = itemView.findViewById(R.id.CardImage)
//        profileImageView = itemView.findViewById(R.id.ProfileImageView)
//        profileName = itemView.findViewById(R.id.ProfileName)
//        coktailName = itemView.findViewById(R.id.CoktailName)
//        coktailDescription = itemView.findViewById(R.id.CoktailDescription)
//        reviewGrade = itemView.findViewById(R.id.ReviewGrade)
//    }
//
//    fun bind(review: Review?, reviewer: User?) {
//        Log.d("TAG", "review ${review?.grade}")
//        Picasso.get()
//            .load(review?.reviewImage)
//            .into(reviewImageView)
//        Picasso.get()
//            .load(reviewer?.profileImageUrl)
//            .into(profileImageView)
//        val reviewerName = "${reviewer?.firstName ?: ""} ${reviewer?.lastName ?: ""}"
//        profileName?.text = reviewerName
//        coktailName?.text = review?.coktailName
//        coktailDescription?.text = review?.coktailDescription
//        reviewGrade?.text = "Rating: ${review?.grade} ★"
//    }
//}