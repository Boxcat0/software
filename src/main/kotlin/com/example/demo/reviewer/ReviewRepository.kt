package com.example.demo.reviewer

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ReviewRepository: CrudRepository<Review, Review> {
    @Query("select review, star, id from reviewtable")
    fun findReviews(): List<Review>
}