package com.example.demo.articlemake

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ArticleController(@Autowired private val articleRepository : ArticleRepository) {
    @GetMapping("/article/new")
    fun newArticleForm() : String{
        return "article"
    }

    @PostMapping("/articles/create")
    fun createArticle(form : ArticleForm):String{
        println(form.toString())
        val article : Article = form.toEntity()
        println(article.toString())
        val saved : Article = articleRepository.save(article)
        println(saved.toString())
        return ""
    }
}