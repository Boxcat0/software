package com.example.demo.reviewer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Component
class ReviewService(@Autowired private val db: ReviewRepository){
    @Transactional
    fun findReviews(): List<Review> = db.findReviewsBy()
    fun reviewpost(review: Review)
    {
        db.save(review)
    }
    fun removing(review : Review){
        db.delete(review)
    }
    fun findGymByStar(star:Double?):List<Review>
    {
        return db.findReviewByStar(star)
    }
    fun findReviewStarById(reviews: List<Review>, id:String):List<Review>
    {
        val listReview = ArrayList<Review>()
        for(i in 0 .. reviews.size-1)
        {
            if(reviews[i].id == id)
            {
                listReview.add(reviews[i])
            }
        }
        return listReview
    }
    fun findReviewStarByGym(reviews: List<Review>, gym: String?): List<Review> {
        val listReview = ArrayList<Review>()
        for (i in 0..reviews.size-1) {
            if (reviews[i].gym == gym) {
                listReview.add(reviews[i])
            }
        }
        return listReview
    }
    fun findGymByid(reviews : List<Review>,gym:String?):Review//gym을 기반으로 헬스장 탐색
    {
        for(i in 0..reviews.size)
        {
            if(reviews[i].gym == gym)
            {
                return reviews[i]
            }
        }
        return Review(null,"null",null,"null",null)
    }
    fun findReviewByWord(review:List<Review>,reviews: String):List<Review>
    {
        val listReview = ArrayList<Review>()
        for(i in 0..review.size-1)
        {
           if(review[i].reviews.contains(reviews))
           {
               listReview.add(review[i])
           }
        }
        return listReview
    }
    fun SaveReview(review: Review) : Review
    {
        return db.save(review)
    }
}