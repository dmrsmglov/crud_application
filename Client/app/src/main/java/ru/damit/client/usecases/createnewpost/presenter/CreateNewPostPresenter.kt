package ru.damit.client.usecases.createnewpost.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damit.client.usecases.createnewpost.view.CreateNewPostView
import ru.damit.client.data.Post
import ru.damit.client.utils.ApiProvider

@InjectViewState
class CreateNewPostPresenter : MvpPresenter<CreateNewPostView>() {

    private val api = ApiProvider.api

    fun newPostCreate(title: String, content: String) {
        api.sendPost(title, content).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("POST", "unable to submit post to API.")
            }
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.i("POST", "post submitted to API. " + response.body()?.title)
            }
        })
    }
}
