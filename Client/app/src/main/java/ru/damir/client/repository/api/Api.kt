package ru.damir.client.repository.api

import retrofit2.Call
import retrofit2.http.*
import ru.damir.client.repository.model.Post

interface Api {

    @POST(value = "/posts/new")
    fun sendPost(@Body post: Post): Call<Unit>

    @GET("/posts/search/{id}")
    fun getPost(@Path(value = "id") id: Int): Call<Post>

    @GET("/posts/all")
    fun getAllPosts(): Call<List<Post>>

    @POST("/posts/delete/{id}")
    fun deletePostById(@Path(value = "id") id: Int) : Call<Unit>
}
