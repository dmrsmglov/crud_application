package ru.damir.client.presentation.createnewpost.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.presentation.createnewpost.view.CreateNewPostView
import ru.damir.client.repository.api.ApiProvider
import ru.damir.client.repository.model.Post

@InjectViewState
class CreateNewPostPresenter : MvpPresenter<CreateNewPostView>() {

    private val api = ApiProvider.api

    fun newPostCreate(title: String, content: String) {

        api.sendPost(Post(0, title, content)).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("POST", "unable to submit post to API.")
            }
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("POST", "post submitted to API.")
            }
        })
    }
}
