package ru.damir.server.controllers.request

import javax.validation.constraints.NotNull

class PostRequest (
        @field:NotNull
        var title: String,

        @field:NotNull
        var content : String
)
