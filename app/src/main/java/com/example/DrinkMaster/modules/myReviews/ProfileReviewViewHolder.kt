package com.example.DrinkMaster.modules.myReviews

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.R
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.user.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class ProfileReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val reviewImage: ImageView?
    val profileImage: ImageView?
    val profileName: TextView?
    val CoktailName: TextView?
    val CoktailDescription: TextView?
    val ReviewGrade: TextView?
    val editButton: Button
    val deleteButton: Button


    init {
        reviewImage = itemView.findViewById(R.id.profileReviewCardImage)
        profileImage = itemView.findViewById(R.id.ProfileImageView)
        profileName = itemView.findViewById(R.id.ProfileName)
        CoktailName = itemView.findViewById(R.id.CoktailName)
        CoktailDescription = itemView.findViewById(R.id.CoktailDescription)
        ReviewGrade = itemView.findViewById(R.id.ReviewGrade)
        editButton = itemView.findViewById(R.id.EditButton)
        deleteButton = itemView.findViewById(R.id.DeleteButton)
    }

    @SuppressLint("SetTextI18n")
    fun bind(
        review: Review?,
        reviewer: User?,
        editReviewClickListener: () -> Unit,
        deleteReviewClickListener: () -> Unit
    ) {
        Log.d("TAG", "review ${review?.grade}")
        Picasso.get()
            .load(review?.reviewImage)
            .into(reviewImage)
        Picasso.get()
            .load(reviewer?.profileImageUrl)
            .into(profileImage)
        val reviewerName = "${reviewer?.firstName ?: ""} ${reviewer?.lastName ?: ""}"
        profileName?.text = reviewerName
        CoktailName?.text = review?.coktailName
        CoktailDescription?.text = review?.coktailDescription
        ReviewGrade?.text = "Grade: ${review?.grade} ★"
        deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(itemView.context)
                .setTitle("Delete Review")
                .setMessage("Do you want to delete this Review?")
                .setNeutralButton("Cancel") { _, _ ->
                }
                .setPositiveButton("Delete") { _, _ ->
                    deleteReviewClickListener()
                }
                .show()
        }
        editButton.setOnClickListener {
            editReviewClickListener()
        }
    }
}