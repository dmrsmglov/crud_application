package ru.damir.client.repository.api

import retrofit2.Call
import retrofit2.http.*
import ru.damir.client.repository.model.request.NewPostRequest
import ru.damir.client.repository.model.response.PostResponse

interface Api {

    @POST(value = "/posts/new")
    fun sendPost(@Body post: NewPostRequest) : Call<PostResponse>

    @GET("/posts/{id}")
    fun getPost(@Path(value = "id") id: Int): Call<PostResponse>

    @GET("/posts/all")
    fun getAllPosts() : Call<List<PostResponse>>
}
