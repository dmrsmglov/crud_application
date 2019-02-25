package ru.damir.server.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ru.damir.server.domain.Post

interface PostsRepo : JpaRepository<Post, Int> {

    fun findPostById(id: Int): Post?
}
