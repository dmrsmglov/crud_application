package ru.damir.server.controllers.request

data class PostRequest(
        var id: Int,
        var title: String,
        var content: String
)
