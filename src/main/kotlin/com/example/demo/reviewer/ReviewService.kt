package com.example.demo.reviewer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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
    fun changing(review : Review, change : String)
    {
        review.reviews = change
        db.save(review)
    }
    fun findGymByid(reviews : List<Review>,gym:String?):Review//gym을 기반으로 헬스장 탐색
    {
        for(i in 0..reviews.size)
        {
            if(reviews[i].gym == gym)
            {
                return reviews[i]
                break
            }
        }
        return Review(null,"null",null,"null",null)
    }
    fun SaveReview(review: Review) : Review
    {
        return db.save(review)
    }
}