package ru.damir.client.data.repository

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.data.api.ApiProvider
import ru.damir.client.data.model.Post

class Repo {

    private val api = ApiProvider.api

    fun updatePost(post: Post) {
        api.updatePost(post).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("POST", "unable to update post.")
            }
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("POST", "post updated.")
            }
        })
    }

    fun sendPost(post: Post) {
        api.sendPost(post).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("POST", "unable to submit post to API.")
            }
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("POST", "post submitted to API.")
            }
        })
    }

    fun updatePostList() : List<Post>? {
        var newPostList : List<Post>? = null
        api.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) =
                t.printStackTrace()

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.i("POST_LIST_SIZE", response.body()?.size.toString())
                newPostList = response.body()
            }
        })
        return newPostList
    }

    fun findPostById(postId : Int?) : Post? {
        var post : Post? = null
        if (postId != null) {
            api.getPost(postId).enqueue(object : Callback<Post> {

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("POST", "unable to find post by post id")
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    post = response.body()
                }
            })
        }
        return post
    }

    fun deletePost(post: Post) {
        api.deletePostById(post.id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("POST", "unable to delete post by post id")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("POST", "post deleted, post id = " + post.id)
            }
        })
    }
}

