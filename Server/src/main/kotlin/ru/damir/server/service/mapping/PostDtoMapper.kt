package ru.damir.server.service.mapping

import org.springframework.stereotype.Component
import ru.damir.server.controllers.request.PostRequest
import ru.damir.server.controllers.response.PostResponse
import ru.damir.server.domain.Post

@Component
class PostDtoMapper {
    fun fromModel(model: Post) =
            PostResponse(
                    id = model.id,
                    title = model.title,
                    content = model.content)

    fun toModel(postRequest: PostRequest) =
            Post(
                    title = postRequest.title,
                    content = postRequest.content)
}
