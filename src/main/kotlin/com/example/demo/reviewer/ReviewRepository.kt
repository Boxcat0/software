package com.example.demo.reviewer

import com.example.demo.account.Account
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository: JpaRepository<Review, Review> {
    @Query("select reviews from review where id = id")
    fun findReviewById(id:String): List<Review>
    @Query("select reviews from review where gym = gym")
    fun findReviewsByGym(gym: String?) : List<Review>
    @Query("select reviews, star, id, gym from review")
    fun findReviewsBy(): List<Review>
    @Query("select reviews, star, id, gym from review where star = star")
    fun findReviewByStar(star :Double?): List<Review>
    /*@Query("select reviews, star, id, gym from review where reviews like CONCAT('%',reviews,'%')")
    fun findReviewByWord(reviews:String):List<Review>*/
}