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

    val api = ApiProvider.api

    fun findPostById(postId: Int?) {

        if (postId != null) {
            api.getPost(postId).enqueue(object : Callback<Post> {

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("POST", "unable to find post by post id")
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    viewState.injectPostDetailsInViews(response.body()!!)
                }
            })
        }
    }
}