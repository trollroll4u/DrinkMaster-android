package com.example.DrinkMaster.modules.feed

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.DrinkMaster.R
import com.example.DrinkMaster.data.review.Review
import com.example.DrinkMaster.data.user.User

class FeedRecycleAdapter(var reviews: MutableList<Review>?, var users: MutableList<User>?) :

    RecyclerView.Adapter<FeedReviewViewHolder>() {

    override fun getItemCount(): Int {
        return reviews?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.coktail_feed_card, parent, false)
        return FeedReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedReviewViewHolder, position: Int) {
        val review = reviews?.get(position)
        Log.d("TAG", "reviews size ${reviews?.size}")
        holder.bind(review, users?.find { it.id == review?.userId })
    }
}