package com.zw.kotlinjpa.dao

import com.zw.kotlinjpa.entify.Author

interface AuthorDao {
    fun add(author: Author): Int
    fun update(author: Author): Int
    fun delete(id: Long): Int
    fun findAuthor(id: Long): Author?
    fun findAuthorList(): List<Author>

}