package ru.damir.server.controllers

import org.springframework.hateoas.MediaTypes
import org.springframework.web.bind.annotation.*
import ru.damir.server.controllers.request.PostRequest
import ru.damir.server.controllers.response.PostResponse
import ru.damir.server.service.PostService
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/posts"], produces = [MediaTypes.HAL_JSON_VALUE])
class PostsController(private val postService: PostService) {

    @GetMapping("/all")
    fun displayAllPosts() : List<PostResponse>  {
        System.out.println("********request: display all********")
        return postService.all()
    }


    @PostMapping("/new")
    fun newPost(@Valid @RequestBody postRequest: PostRequest) {
        System.out.println("********request: save post********")
        postService.save(postRequest)
    }
}
