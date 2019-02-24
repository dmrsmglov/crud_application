package ru.damir.server.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.damir.server.domain.Post

interface PostsRepo : JpaRepository<Post, Long> {
    fun findPostById(id: Int): Post?
}
