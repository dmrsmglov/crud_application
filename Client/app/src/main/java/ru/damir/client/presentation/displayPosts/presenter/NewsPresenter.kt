package ru.damir.client.presentation.displayPosts.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.presentation.data.Post
import ru.damir.client.presentation.displayPosts.view.NewsView
import ru.damir.client.presentation.utils.ApiProvider

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    val api = ApiProvider.api

    fun fetchButtonClick(callback: (List<Post>) -> Unit) {
        api.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                System.out.println("*****response******")
                response.body()?.let {callback(it)}
            }
        })
    }
}
