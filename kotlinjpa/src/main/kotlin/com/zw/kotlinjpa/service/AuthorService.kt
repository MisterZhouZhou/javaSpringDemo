package com.zw.kotlinjpa.service

import com.zw.kotlinjpa.entify.Author

interface AuthorService {
    fun add(author: Author):Int
    fun update(author: Author): Int
    fun delete(id: Long): Int
    fun findAuthor(id: Long): Author?
    fun findAuthorList(): List<Author>
}