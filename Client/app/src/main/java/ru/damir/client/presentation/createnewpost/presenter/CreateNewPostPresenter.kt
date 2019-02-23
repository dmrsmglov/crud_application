package ru.damir.client.presentation.createnewpost.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.presentation.createnewpost.view.CreateNewPostView
import ru.damir.client.repository.model.response.PostResponse
import ru.damir.client.repository.api.ApiProvider
import ru.damir.client.repository.model.request.NewPostRequest

@InjectViewState
class CreateNewPostPresenter : MvpPresenter<CreateNewPostView>() {

    private val api = ApiProvider.api

    fun newPostCreate(title: String, content: String) {

        api.sendPost(NewPostRequest(title, content)).enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.w("POST", "unable to submit post to API.")
            }
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                Log.i("POST", "post submitted to API. " + response.body()?.title)
            }
        })
    }
}
