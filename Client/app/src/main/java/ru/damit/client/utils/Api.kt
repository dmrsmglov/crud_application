package ru.damit.client.utils

import retrofit2.Call
import retrofit2.http.*
import ru.damit.client.data.Post

interface Api {

    @POST(value = "/posts/new")
    @FormUrlEncoded
    fun sendPost(@Field("title") title: String, @Field("content") content : String) : Call<Post>

    @GET("/posts/{id}")
    fun getPost(@Path(value = "id") id: Int): Call<Post>

    @GET("/posts/all")
    fun getAllPosts() : Call<List<Post>>
}
