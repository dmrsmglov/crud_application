package ru.damir.server.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.damir.server.controllers.request.PostRequest
import ru.damir.server.controllers.response.PostResponse
import ru.damir.server.domain.Post
import ru.damir.server.repositories.PostsRepo
import ru.damir.server.service.mapping.PostDtoMapper
import java.util.*


@Service
class PostService (private val postsRepo: PostsRepo,
                   private val mapper: PostDtoMapper){
    fun all(): List<PostResponse?> = transformToResponse(postsRepo.findAll())

    fun save(postRequest: PostRequest) = postsRepo.save(mapper.toModel(postRequest))

    private fun transformToResponse(list: Iterable<Post>) = list.map { mapper.fromModel(it) }.toList()

    fun findPostById(id: Int): PostResponse? = mapper.fromModel(postsRepo.findPostById(id))

    fun deletePostById(id: Int) {
        postsRepo.deleteById(id)
    }

    fun updatePost(postRequest: PostRequest) {
        val post = mapper.toModel(postRequest)
        post.id = postRequest.id
        postsRepo.save(post)
    }
}
