package com.example.demo.articlemake

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Article(
        @Id
        @GeneratedValue
        var number : Long?,
        @Column
        var title : String,
        @Column
        var content : String
){
    fun GetArticle(number: Long, title : String, content: String){
        this.number = number
        this.title = title
        this.content = content
    }
    override fun toString(): String{
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}'
    }
}

