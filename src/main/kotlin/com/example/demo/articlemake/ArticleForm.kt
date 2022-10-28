package com.example.demo.articlemake

class ArticleForm{
    private lateinit var title : String
    private lateinit var content: String
    fun ArticleForm(title: String, content: String){
        this.title = title
        this.content = content
    }
    override fun toString(): String{
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}'
    }

    fun toEntity(): Article{
        return Article(null, title, content)
    }
}