package com.example.DrinkMaster.data.review

import android.content.Context
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.DrinkMaster.DrinkMasterApplication
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.io.Serializable

@Entity
data class Review(
    @PrimaryKey
    val id: String,
    val coktailName: String,
    val coktailDescription: String,
    val grade: Int,
    val userId: String,
    var isDeleted: Boolean = false,
    var reviewImage: String? = null,
    var timestamp: Long? = null,
) : Serializable {

    companion object {
        var lastUpdated: Long
            get() {
                return DrinkMasterApplication.Globals
                    .appContext?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
                    ?.getLong(REVIEW_LAST_UPDATED, 0) ?: 0
            }
            set(value) {
                DrinkMasterApplication.Globals
                    ?.appContext
                    ?.getSharedPreferences("TAG", Context.MODE_PRIVATE)?.edit()
                    ?.putLong(REVIEW_LAST_UPDATED, value)?.apply()
            }

        const val ID_KEY = "id"
        const val USER_ID_KEY = "userId"
        const val GRADE_KEY = "grade"
        const val LAST_UPDATED_KEY = "timestamp"
        const val COCKTAIL_DESCRIPTION_KEY = "coktailDescription"
        const val COCKTAIL_NAME_KEY = "cocktailName"
        const val IS_DELETED_KEY = "is_deleted"
        private const val REVIEW_LAST_UPDATED = "review_last_updated"

        fun fromJSON(json: Map<String, Any>): Review {
            Log.d(
                "Review",
                "fromJSON: $json score: ${json[GRADE_KEY]} scoreInt: ${(json[GRADE_KEY] as? Long) ?: 0}"
            )
            val id = json[ID_KEY] as? String ?: ""
            val grade = (json[GRADE_KEY] as? Long)?.toInt() ?: 0
            val cocktailDescription = json[COCKTAIL_DESCRIPTION_KEY] as? String ?: ""
            val cocktailName = json[COCKTAIL_NAME_KEY] as? String ?: ""
            val isDeleted = json[IS_DELETED_KEY] as? Boolean ?: false
            val userId = json[USER_ID_KEY] as? String ?: ""
            val review = Review(id, cocktailName , cocktailDescription, grade, userId, isDeleted)

            val timestamp: Timestamp? = json[LAST_UPDATED_KEY] as? Timestamp
            timestamp?.let {
                review.timestamp = it.seconds
            }

            return review
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                USER_ID_KEY to userId,
                GRADE_KEY to grade,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
                COCKTAIL_DESCRIPTION_KEY to coktailDescription,
                COCKTAIL_NAME_KEY to coktailName,
                IS_DELETED_KEY to isDeleted
            )
        }

    val deleteJson: Map<String, Any>
        get() {
            return hashMapOf(
                IS_DELETED_KEY to true,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
            )
        }

    val updateJson: Map<String, Any>
        get() {
            return hashMapOf(
                GRADE_KEY to grade,
                COCKTAIL_DESCRIPTION_KEY to coktailDescription,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
            )
        }
}