package ru.damir.client.presentation.postdetails.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.presentation.postdetails.view.PostDetailsView
import ru.damir.client.repository.api.ApiProvider
import ru.damir.client.repository.model.Post

@InjectViewState
class PostDetailsPresenter : MvpPresenter<PostDetailsView>() {

    var post: Post? = null

    val api = ApiProvider.api

    fun findPostById(postId: Int?) {

        if (postId != null) {
            api.getPost(postId).enqueue(object : Callback<Post> {

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("POST", "unable to find post by post id")
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    post = response.body()
                    viewState.injectPostDetailsInViews(post!!)
                }
            })
        }
    }

    fun deletePost() {

        if (post != null) {
            api.deletePostById(post!!.id).enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("POST", "unable to delete post by post id")
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.i("POST", "post deleted, post id = " + post!!.id)
                }
            })
        }
    }
}